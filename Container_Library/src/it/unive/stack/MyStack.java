package it.unive.stack;

import it.unive.MyContainer;

import java.util.EmptyStackException;

public interface MyStack<E> extends MyContainer<E> {


    public E pop () throws EmptyStackException;


    public E peek () throws EmptyStackException;


    @Override
    default public boolean remove(E element) {
        return peek().equals(element) && pop().equals(element);
    }

}
