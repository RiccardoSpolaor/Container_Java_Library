package it.unive;

import it.unive.stack.MyStack;
import it.unive.structs.MyArray;


public interface MyContainer <E> extends MyIterable<E>{

    public int getSize();

    default public boolean isEmpty() {
        return this.getSize()==0;
    }

    public void add (E element);

    default public void addAll (MyIterable <? extends E> iterable) {
        MyIterator<? extends E> iterator = iterable.getIterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
    }

    default public void add (E ... elements){
        MyArray<E> array = new MyArray<>(elements);
        for (int i = 0; i < array.size(); i++) {
            add(array.getAt(i));
        }
    }


    public boolean remove (E element);


    default boolean removeAll (MyIterable<? extends E> iterable) {
        MyIterator<? extends E> iterator = iterable.getIterator();
        boolean result=false;

        if (this instanceof MyStack) {
            boolean flag = false;
            while (!flag) {
                boolean innerResult = false;
                while (iterator.hasNext() && !innerResult) {
                    innerResult = remove(iterator.next());
                }
                if (innerResult) {
                    iterator = iterable.getIterator();
                }
                else
                    flag = true;
                result = result || innerResult;
            }

        }

        else {
            while (iterator.hasNext()) {
                result = remove(iterator.next()) || result;
            }
        }

        return result;
    }


    default boolean remove (E... elements) {
        MyArray <E> array = new MyArray<> (elements);
        boolean result = false;

        if (this instanceof MyStack) {
            boolean flag = false;
            while (!flag) {
                boolean innerResult = false;
                for (int i = 0; i<array.size() && !innerResult;i++)
                    innerResult = remove(array.getAt(i));
                if (innerResult) {
                    array = new MyArray<> (elements);
                }
                else
                    flag = true;
                result = result || innerResult;
            }
        }

        else {
            for (int i = 0; i < array.size(); i++)
                result = remove(array.getAt(i)) || result;
        }

        return result;
    }


    default public boolean contains (E element){
        MyIterator<E> iterator = this.getIterator();

        while (iterator.hasNext()){
            try {
                if (iterator.next().equals(element))
                    return true;
            } catch (NullPointerException exception) {
                if (element == null)
                    return true;
            }
        }
        return false;
    }


    default public boolean contains(E... elements){
        boolean result = true;
        MyArray<E> array = new MyArray<>(elements);

        if (array.size() == 0)
            result = false;
        else {
            for (int i = 0; i < array.size() && result; i++) {
                result = this.contains(array.getAt(i));
            }
        }
        return result;
    }


    default public boolean containsAll (MyIterable<? extends E> iterable){
        boolean result = false;
        MyIterator<? extends E> iterator = iterable.getIterator();

        while (iterator.hasNext()){
            result = this.contains(iterator.next());
            if (!result)
               return result;
        }

        return result;
    }


    void clear();
}
