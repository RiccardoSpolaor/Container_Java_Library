package it.unive.list;

import it.unive.MyContainer;
import it.unive.MyIterable;
import it.unive.MyIterator;
import it.unive.structs.MyArray;


public interface MyList<E> extends MyContainer<E> {

    public E getAt(int position) throws IndexOutOfBoundsException;


    public void setAt(int position, E element) throws IndexOutOfBoundsException;


    public void addAt(int position, E element) throws IndexOutOfBoundsException;


    default public void addAt(int position, E...elements) throws IndexOutOfBoundsException {

        MyArray<E> array = new MyArray<>(elements);
        for (int i = 0; i < array.size(); i++){
            addAt(position++, array.getAt(i));
        }
    }


    default public void addAllAt(int position, MyIterable<? extends E> iterable) throws IndexOutOfBoundsException {
        MyIterator<? extends E> iterator = iterable.getIterator();

        while(iterator.hasNext()){
            addAt(position++, iterator.next());
        }
    }

    @Override
    default public void add (E element) {
        addAt(this.getSize(), element);
    }

    @Override
    default public boolean remove(E element) {
        boolean removed = false;
        int i = 0;

        while (i < this.getSize()) {
            try {
                if (this.getAt(i).equals(element)) {
                    removeAt(i);
                    removed = true;
                } else
                    i++;
            } catch (NullPointerException exception) {
                if (element==null) {
                    removeAt(i);
                    removed = true;
                } else
                    i++;
            }
        }
        return removed;
    }


    public void removeAt (int position) throws IndexOutOfBoundsException;

    @Override
    default public MyIterator<E> getIterator() {
        return new MyIterator<E>() {

            private int position = 0;

            @Override
            public boolean hasNext() {
                return this.position < MyList.this.getSize();
            }

            @Override
            public E next() {
                try {
                    return MyList.this.getAt(position++);
                } catch (IndexOutOfBoundsException ex){
                    throw new IndexOutOfBoundsException();
                }
            }
        };
    }

}
