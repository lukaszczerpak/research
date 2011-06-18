package eu.czerpak.lambdaj;

import ch.lambdaj.demo.Db;
import ch.lambdaj.demo.Sale;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import org.perf4j.LoggingStopWatch;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.on;
import static org.testng.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 6/17/11
 * Time: 8:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExtractTest
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
            Collection<String> names = Collections2.transform(db.sales, new Function<Sale, String>()
            {
                public String apply(Sale sale)
                {
                    return sale.getCar().getName();
                }
            });
            assertEquals(names.size(), 56);
        }
        stopWatch.stop();
    }

    @Test(invocationCount = TEST_COUNT, threadPoolSize = THREADS)
    public void testJava() throws Exception
    {
        LoggingStopWatch stopWatch = new LoggingStopWatch();
        stopWatch.start("java");
        for (int i = 0; i < COUNT; i++) {
            List<String> names = new ArrayList<String>();
            for (Sale sale : db.sales) {
                names.add(sale.getCar().getName());
            }
            assertEquals(names.size(), 56);
        }
        stopWatch.stop();
    }

    @Test(invocationCount = TEST_COUNT, threadPoolSize = THREADS)
    public void testLambdaj() throws Exception
    {
        LoggingStopWatch stopWatch = new LoggingStopWatch();
        stopWatch.start("lambdaj");
        for (int i = 0; i < COUNT; i++) {
            List<String> names = extract(db.sales, on(Sale.class).getCar().getName());
            assertEquals(names.size(), 56);
        }
        stopWatch.stop();
    }
}
