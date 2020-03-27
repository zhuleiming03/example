package example.java.collect.service;

import java.util.ArrayList;
import java.util.List;

public class CatService {

    public static <T> List<T> getSafeList(List<T> list,T t) {
        List<T> copyList = new ArrayList<>();
        copyList.addAll(list);
        copyList.add(t);
        return copyList;
    }

    public static <T> List<T> getUnSafeList(List<T> list,T t) {
        list.add(t);
        return list;
    }
}
