package it.unive.stack;

import it.unive.list.MyArrayList;
import it.unive.MyIterable;
import it.unive.MyIterator;
import it.unive.util.IterableMethodManager;
import it.unive.util.IterableReflectionManager;

import java.util.EmptyStackException;

public class MyLIFOStack<E> implements MyStack<E> {

    protected MyArrayList <E> innerArray;
    protected int size;

    public MyLIFOStack() {
        this.size = 0;
        innerArray = new MyArrayList<>();
    }

    public MyLIFOStack(E element) {
        this.size = 0;
        innerArray = new MyArrayList<>();
        add(element);
    }


    public MyLIFOStack(E... elements) {
        size = 0;
        innerArray = new MyArrayList<>();
        add(elements);
    }

    public MyLIFOStack(MyIterable<? extends E> iterable) {
        size = 0;
        innerArray = new MyArrayList<>();
        addAll(iterable);
    }

    @Override
    public void add(E element) {
        innerArray.add(element);
        size++;
    }

    @Override
    public int getSize() {
        return this.size;
    }


    @Override
    public E pop() throws EmptyStackException {
        E returnElement = this.peek();
        innerArray.removeAt(getSize() - 1);
        this.size--;
        return returnElement;
    }


    @Override
    public E peek() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return innerArray.getAt(getSize() - 1);
    }

    @Override
    public void clear() {
        innerArray = new MyArrayList<>();
        this.size = 0;
    }

    @Override
    public MyIterator<E> getIterator() {
        return innerArray.getIterator();
    }

    @Override public String toString () {

        StringBuilder returnString = new StringBuilder();
        if (this.isEmpty())
            returnString.append(String.format("%s: EMPTY \n", this.getClass().getSimpleName()));
        else {
            returnString.append(String.format("%s of \"%s\" Objects:\n", this.getClass().getSimpleName(),
                    IterableReflectionManager.getGenericParameterName(this)));
            MyIterator <E> iterator = this.getIterator();
            while (iterator.hasNext()) {
                returnString.append(String.format("\t* %s%c\n",iterator.next(),iterator.hasNext()? ';':'.'));
            }
        }
        return returnString.toString();
    }

    @Override
    public boolean equals (Object o) {
        return IterableMethodManager.isEqual(this,o);
    }

    @Override
    public  int hashCode () {
        return IterableMethodManager.getHashCode(this);
    }
}
