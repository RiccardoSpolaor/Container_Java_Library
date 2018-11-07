package it.unive.util;

import it.unive.MyIterable;
import it.unive.MyIterator;

public class IterableMethodManager {

    public static boolean isEqual(MyIterable<?> iterable, Object o) {
        return (o instanceof MyIterable) && (iterable.hashCode() == o.hashCode());
    }

    public static int getHashCode(MyIterable<?> iterable) {
        MyIterator <?> iterator = iterable.getIterator();
        int hashCode = 1;
        while (iterator.hasNext()) {
            Object element = iterator.next();
            hashCode = hashCode + (element==null ? 0 : element.hashCode());
        }
        return  hashCode*127;
    }

}
