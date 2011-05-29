package eu.czerpak.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: lukes
 * Date: 5/29/11
 * Time: 9:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyObjectListHolder
{
    // very bad singleton implementation, but doesn't matter this time ;)
    private static MyObjectListHolder instance = new MyObjectListHolder();

    private final List<MyObject> list10;
    private final List<MyObject> list100;
    private final List<MyObject> list500;
    private final List<MyObject> list1000;

    private MyObjectListHolder()
    {
        list10 = createList(10);
        list100 = createList(100);
        list500 = createList(500);
        list1000 = createList(1000);
    }

    private static List<MyObject> createList(int size)
    {
        ArrayList<MyObject> list = new ArrayList<MyObject>();
        for (int i = 0; i < size; i++) {
            list.add(new MyObject().fillWithRandomData());
        }
        return list;
    }

    public static MyObjectListHolder getInstance()
    {
        return instance;
    }

    public List<MyObject> getList10()
    {
        return list10;
    }

    public List<MyObject> getList100()
    {
        return list100;
    }

    public List<MyObject> getList500()
    {
        return list500;
    }

    public List<MyObject> getList1000()
    {
        return list1000;
    }
}
