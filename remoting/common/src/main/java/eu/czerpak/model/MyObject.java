package eu.czerpak.model;

import org.apache.commons.lang.RandomStringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/29/11
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyObject implements Serializable
{
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private String field9;
    private String field10;
    private String field11;
    private String field12;
    private String field13;
    private String field14;

    private Date date;
    private BigDecimal decimal1;
    private BigDecimal decimal2;
    private BigDecimal decimal3;

    public String getField1()
    {
        return field1;
    }

    public void setField1(String field1)
    {
        this.field1 = field1;
    }

    public String getField2()
    {
        return field2;
    }

    public void setField2(String field2)
    {
        this.field2 = field2;
    }

    public String getField3()
    {
        return field3;
    }

    public void setField3(String field3)
    {
        this.field3 = field3;
    }

    public String getField4()
    {
        return field4;
    }

    public void setField4(String field4)
    {
        this.field4 = field4;
    }

    public String getField5()
    {
        return field5;
    }

    public void setField5(String field5)
    {
        this.field5 = field5;
    }

    public String getField6()
    {
        return field6;
    }

    public void setField6(String field6)
    {
        this.field6 = field6;
    }

    public String getField7()
    {
        return field7;
    }

    public void setField7(String field7)
    {
        this.field7 = field7;
    }

    public String getField8()
    {
        return field8;
    }

    public void setField8(String field8)
    {
        this.field8 = field8;
    }

    public String getField9()
    {
        return field9;
    }

    public void setField9(String field9)
    {
        this.field9 = field9;
    }

    public String getField10()
    {
        return field10;
    }

    public void setField10(String field10)
    {
        this.field10 = field10;
    }

    public String getField11()
    {
        return field11;
    }

    public void setField11(String field11)
    {
        this.field11 = field11;
    }

    public String getField12()
    {
        return field12;
    }

    public void setField12(String field12)
    {
        this.field12 = field12;
    }

    public String getField13()
    {
        return field13;
    }

    public void setField13(String field13)
    {
        this.field13 = field13;
    }

    public String getField14()
    {
        return field14;
    }

    public void setField14(String field14)
    {
        this.field14 = field14;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public BigDecimal getDecimal1()
    {
        return decimal1;
    }

    public void setDecimal1(BigDecimal decimal1)
    {
        this.decimal1 = decimal1;
    }

    public BigDecimal getDecimal2()
    {
        return decimal2;
    }

    public void setDecimal2(BigDecimal decimal2)
    {
        this.decimal2 = decimal2;
    }

    public BigDecimal getDecimal3()
    {
        return decimal3;
    }

    public void setDecimal3(BigDecimal decimal3)
    {
        this.decimal3 = decimal3;
    }

    public MyObject fillWithRandomData()
    {
        this.field1 = RandomStringUtils.randomAscii(100);
        this.field2 = RandomStringUtils.randomAscii(100);
        this.field3 = RandomStringUtils.randomAscii(100);
        this.field4 = RandomStringUtils.randomAscii(100);
        this.field5 = RandomStringUtils.randomAscii(100);
        this.field6 = RandomStringUtils.randomAscii(100);
        this.field7 = RandomStringUtils.randomAscii(100);
        this.field8 = RandomStringUtils.randomAscii(100);
        this.field9 = RandomStringUtils.randomAscii(100);
        this.field10 = RandomStringUtils.randomAscii(100);
        this.field11 = RandomStringUtils.randomAscii(100);
        this.field12 = RandomStringUtils.randomAscii(100);
        this.field13 = RandomStringUtils.randomAscii(100);
        this.field14 = RandomStringUtils.randomAscii(100);

        this.date = new Date();

        this.decimal1 = BigDecimal.ONE;
        this.decimal2 = BigDecimal.TEN;
        this.decimal3 = BigDecimal.valueOf(12.34);

        return this;
    }
}
