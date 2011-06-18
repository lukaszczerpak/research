package eu.czerpak.lambdaj;

import ch.lambdaj.demo.Db;
import ch.lambdaj.demo.Sale;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.perf4j.LoggingStopWatch;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ch.lambdaj.Lambda.*;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 6/17/11
 * Time: 8:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class SelectTest
{
    public static final int COUNT = 100000;
    public static final int TEST_COUNT = 20;
    public static final int THREADS = 20;
    private final Db db = Db.getInstance();

    @Test(invocationCount = TEST_COUNT, threadPoolSize = THREADS)
    public void testGuava() throws Exception
    {
        LoggingStopWatch stopWatch = new LoggingStopWatch();
        stopWatch.start("guava");
        for (int i = 0; i < COUNT; i++) {
            Collection<Sale> filtered = Collections2.filter(db.sales, new Predicate<Sale>()
            {
                public boolean apply(Sale sale)
                {
                    return sale.getCar().getBrand().equals("Ferrari");
                }
            });
            assertEquals(filtered.size(), 3);
        }
        stopWatch.stop();
    }

    @Test(invocationCount = TEST_COUNT, threadPoolSize = THREADS)
    public void testJava() throws Exception
    {
        LoggingStopWatch stopWatch = new LoggingStopWatch();
        stopWatch.start("java");
        for (int i = 0; i < COUNT; i++) {
            List<Sale> salesOfAFerrari = new ArrayList<Sale>();
            for (Sale sale : db.sales) {
                if (sale.getCar().getBrand().equals("Ferrari")) {
                    salesOfAFerrari.add(sale);
                }
            }
            assertEquals(salesOfAFerrari.size(), 3);
        }
        stopWatch.stop();
    }

    @Test(invocationCount = TEST_COUNT, threadPoolSize = THREADS)
    public void testLambdaj() throws Exception
    {
        LoggingStopWatch stopWatch = new LoggingStopWatch();
        stopWatch.start("lambdaj");
        for (int i = 0; i < COUNT; i++) {
            List<Sale> salesOfAFerrari = select(db.sales, having(on(Sale.class).getCar().getBrand(), equalTo("Ferrari")));
            assertEquals(salesOfAFerrari.size(), 3);
        }
        stopWatch.stop();
    }
}
