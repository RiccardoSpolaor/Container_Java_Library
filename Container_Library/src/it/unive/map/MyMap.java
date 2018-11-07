package it.unive.map;

import it.unive.MyContainer;
import it.unive.MyIterable;
import it.unive.MyIterator;
import it.unive.structs.MyArray;
import it.unive.structs.MyMapEntry;


public interface MyMap<K,E> extends MyContainer <MyMapEntry<? extends K, ? extends E>> {


    void put(K key, E element);

    @Override
    default void add (MyMapEntry<? extends K, ? extends E> entry) {
        try {
            this.put(entry.getKey(), entry.getElement());
        }
        catch (NullPointerException exception) {
            this.put(null,null);
        }
    }

    @Override
    default void add (MyMapEntry<? extends K, ? extends E>... entries) {
        MyContainer.super.add (entries);
    }


    default boolean putIfAbsent(K key, E element) {
        if (!this.containsKey(key)) {
            this.put(key, element);
            return true;
        }
        else
            return false;
    }


    default boolean addIfAbsent(MyMapEntry<? extends K, ? extends E>... entries) {
        boolean result = false;
        MyArray <MyMapEntry <? extends K, ? extends E>> array = new MyArray<>(entries);
        for (int i = 0; i < array.size(); i++) {
            result = putIfAbsent (array.getAt(i).getKey(), array.getAt(i).getElement()) || result;
        }
        return result;
    }


    default boolean addAllIfAbsent(MyIterable <? extends MyMapEntry<? extends K, ? extends E>> iterable) {
        boolean result = false;
        MyIterator <? extends MyMapEntry <? extends K, ? extends E>> iterator;
        iterator = iterable.getIterator();
        while (iterator.hasNext()) {
            MyMapEntry<? extends K, ? extends E> next = iterator.next();
            result = this.putIfAbsent(next.getKey(), next.getElement()) || result;
        }
        return result;
    }


    default boolean replaceElement(K key, E element) {
        if (this.containsKey(key)) {
            put(key, element);
            return true;
        }
        else
            return false;
    }


    default boolean replaceElements (MyMapEntry<? extends K, ? extends E>... entries) {
        boolean result = false;
        MyArray <MyMapEntry <? extends K, ? extends E>> array = new MyArray<>(entries);
        for (int i = 0; i < array.size(); i++) {
            result = replaceElement(array.getAt(i).getKey(), array.getAt(i).getElement()) || result;
        }
        return result;
    }


    default boolean replaceAllElements (MyIterable <? extends MyMapEntry<? extends K, ? extends E>> iterable) {
        boolean result = false;
        MyIterator<? extends MyMapEntry<? extends K, ? extends E>> iterator;
        iterator = iterable.getIterator();
        while (iterator.hasNext()) {
            MyMapEntry<? extends K, ? extends E> next = iterator.next();
            result = this.replaceElement(next.getKey(), next.getElement()) || result;
        }
        return result;
    }


    default void addKey (K key) {
        this.put (key, null);
    }


    default void addKeys (K... keys) {
        MyArray<K> array = new MyArray<>(keys);
        for (int i = 0; i< getSize(); i++)
            this.put(array.getAt(i),null);
    }


    default void addAllKeys (MyIterable<K> iterable) {
        MyIterator<K> iterator = iterable.getIterator();
        while (iterator.hasNext())
            this.put(iterator.next(),null);
    }

    E getValue(K key) throws IllegalArgumentException;

    @Override
    boolean remove (MyMapEntry<? extends K, ? extends E> entry);

    @Override
    default boolean remove (MyMapEntry<? extends K, ? extends E>... entries) {
        return MyContainer.super.remove(entries);
    }


    boolean removeByKey (K key);


    default boolean removeByKeys (K... keys) {
        MyArray <K> array = new MyArray<>(keys);
        boolean result = false;

        for (int i = 0; i < array.size(); i++)
            result = removeByKey(array.getAt(i)) || result;

        return result;
    }


    default boolean removeAllByKeys (MyIterable <? extends K> iterable) {
        boolean result = false;
        MyIterator<? extends K> iterator = iterable.getIterator();
        while (iterator.hasNext()) {
            result = removeByKey(iterator.next()) || result;
        }
        return result;
    }


    boolean removeByElement(E element);


    default boolean removeByElements(E... elements){
        MyArray <E> array = new MyArray<>(elements);
        boolean result = false;

        for (int i = 0; i < array.size(); i++)
            result = removeByElement(array.getAt(i)) || result;

        return result;
    }


    default boolean removeAllByElements(MyIterable <? extends E> iterable) {
        boolean result = false;
        MyIterator<? extends E> iterator = iterable.getIterator();
        while (iterator.hasNext()) {
            result = removeByElement(iterator.next()) || result;
        }
        return result;
    }

    @Override
    default public boolean contains (MyMapEntry<? extends K, ? extends E> entry) {
        return MyContainer.super.contains(entry);
    }

    @Override
    default public boolean contains (MyMapEntry<? extends K, ? extends E>... entries) {
        return MyContainer.super.contains(entries);
    }


    default public boolean containsKey (K key){
        MyIterator<MyMapEntry<? extends K,? extends E>> iterator = this.getIterator();
        try {
            while (iterator.hasNext()) {
                if (iterator.next().getKey().equals(key))
                    return true;
            }
        } catch (NullPointerException exception) {
            if (key== null)
                return true;
        }
        return false;
    }


    default public boolean containsKeys (K... keys){
        boolean result = true;
        MyArray<K> array = new MyArray<>(keys);

        if (array.size() == 0)
            result = false;
        else {
            for (int i = 0; i < array.size() && result; i++) {
                result = this.containsKey(array.getAt(i));
            }
        }
        return result;
    }


    default public boolean containsAllKeys (MyIterable<? extends K> iterable){
        boolean result = false;
        MyIterator <? extends K> iterator = iterable.getIterator();

        while (iterator.hasNext()){
            result = this.containsKey (iterator.next());
            if (!result)
                return false;
        }
        return result;
    }


    default public boolean containsElement (E element){
        MyIterator<MyMapEntry<? extends K,? extends E>> iterator = this.getIterator();
            try {
                while (iterator.hasNext()) {
                    if (iterator.next().getElement().equals(element))
                        return true;
                }
            } catch (NullPointerException exception) {
                if (element== null)
                    return true;
            }
        return false;
    }


    default public boolean containsElements (E... elements){
        boolean result = true;
        MyArray<E> array = new MyArray<>(elements);

        if (array.size() == 0)
            result = false;
        else {
            for (int i = 0; i < array.size() && result; i++) {
                result = this.containsElement(array.getAt(i));
            }
        }
        return result;
    }


    default public boolean containsAllElements (MyIterable<? extends E> iterable){
        boolean result = false;
        MyIterator <? extends E> iterator = iterable.getIterator();

        while (iterator.hasNext()){
            result = this.containsElement(iterator.next());
            if (!result)
                return false;
        }
        return result;
    }
}
