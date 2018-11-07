package it.unive.util;

import it.unive.MyIterable;
import it.unive.MyIterator;

public class IterableReflectionManager {

    public static String getGenericParameterName (MyIterable<?> iterable) {
        Class <?> returnClass = IterableReflectionManager.getGenericParameterClass(iterable);
        return returnClass == null ? null : returnClass.getSimpleName();
    }

    private static Class<?> getGenericParameterClass(MyIterable<?> iterable) {
        MyIterator<?> iterator = iterable.getIterator();
        Class <?> cl = null;
        boolean found = false;
        while (iterator.hasNext() && !found) {
            try {
                cl = iterator.next().getClass();
                while (cl != null && !found) {
                    found = IterableReflectionManager.isGenericParameterClass(iterable.getIterator(), cl);
                    if (!found)
                        cl = cl.getSuperclass();
                }
            } catch (NullPointerException ignored) {}
        }
        return cl;
    }

    private static boolean isGenericParameterClass(MyIterator<?> iterator, Class<?> cl) {
        boolean returnResult = true;
        while (iterator.hasNext()&& returnResult) {
            try {
                returnResult = cl.isAssignableFrom(iterator.next().getClass());
            } catch (NullPointerException ignored) { }
        }
        return returnResult;
    }
}
