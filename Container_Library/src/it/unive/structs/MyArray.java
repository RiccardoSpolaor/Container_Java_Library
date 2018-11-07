package it.unive.structs;

import it.unive.MyIterable;
import it.unive.MyIterator;
import it.unive.util.IterableMethodManager;
import it.unive.util.IterableReflectionManager;


public class MyArray<E> implements MyIterable<E> {

    private E[] array;

    public MyArray(int size){
        array = (E[]) new Object[size];
    }

    public MyArray(E ... elements){
        array = elements;
    }

    public int size(){
        return array.length;
    }

    public E getAt(int position) throws IndexOutOfBoundsException{
        if (position < 0 || position >= size())
            throw new IndexOutOfBoundsException("position not available" + position);
        else
            return array[position];
    }


    public void addAt(int position, E element) throws IndexOutOfBoundsException {
        array[position] = element;
    }

    @Override
    public MyIterator<E> getIterator() {
        return new MyIterator<E> (){

            private int pos = 0;

            @Override
            public boolean hasNext() {
                return this.pos < MyArray.this.size();
            }

            @Override
            public E next() {
                return MyArray.this.getAt(this.pos++);
            }
        };
    }

    public static <E> MyArray<E> longerArray (MyArray <E> array, int size) {
        MyArray<E> copyArray = array;
        array = new MyArray<E>(array.size()*size);
        for (int i = 0; i < copyArray.size() ;i++)
            array.addAt(i, copyArray.getAt(i));
        return array;
    }

    @Override public String toString () {

        StringBuilder returnString = new StringBuilder();
        if (this.size()==0)
            returnString.append(String.format("%s: EMPTY \n", this.getClass().getSimpleName()));
        else {
            returnString.append(String.format("%s of \"%s\" Objects:\n", this.getClass().getSimpleName(), IterableReflectionManager.getGenericParameterName(this)));
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

    public int hashCode () {
        return IterableMethodManager.getHashCode(this);
    }
}