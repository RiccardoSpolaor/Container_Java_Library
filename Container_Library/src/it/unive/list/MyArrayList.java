package it.unive.list;

import it.unive.MyIterable;
import it.unive.MyIterator;
import it.unive.structs.MyArray;
import it.unive.util.IterableMethodManager;
import it.unive.util.IterableReflectionManager;

public class MyArrayList<E> implements MyList<E> {

    protected int size;

    protected MyArray<E> innerArray;

    private static final int TWO = 2;

    public MyArrayList (){
        this.size = 0;
        this.innerArray = new MyArray<>(5);
    }

    public MyArrayList(E element){
        this.size=0;
        this.innerArray = new MyArray<>(5);
        add(element);
    }

    public MyArrayList(E... elements){
        this.size=0;
        this.innerArray = new MyArray<>(elements.length<3 ? 5 : elements.length*2);
        add(elements);
    }

    public MyArrayList (MyIterable<? extends E> iterable) {
        this.size=0;
        this.innerArray = new MyArray<>(5);
        addAll(iterable);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void addAt(int position, E element) throws IndexOutOfBoundsException {
        if (position < 0 || position > this.getSize())
            throw new IndexOutOfBoundsException();
        else {
            MyArray<E> copyArray = new MyArray<>(innerArray.size());
            if (copyArray.size() == getSize())
                copyArray = MyArray.longerArray(copyArray, TWO);

            int j = 0;
            for (int i = 0; i <= this.getSize(); i++) {
                if (i == position) {
                    copyArray.addAt(j, element);
                    j++;
                }
                if (i != this.getSize()) {
                    copyArray.addAt(j, innerArray.getAt(i));
                }
                j++;
            }
            this.innerArray = copyArray;
            this.size++;
        }
    }

    @Override
    public E getAt(int position) throws IndexOutOfBoundsException {
        if(position < 0 || position >= getSize() )
            throw new IndexOutOfBoundsException("position not available " + position);
        else
            return innerArray.getAt(position);
    }

    @Override
    public void setAt(int position, E element) throws IndexOutOfBoundsException {
        if (position < 0 || position >= this.getSize()) {
            throw new IndexOutOfBoundsException("position not available" + position);
        }
        else{
            innerArray.addAt(position, element);
        }
    }

    @Override
    public void removeAt(int position) {
        if (position < 0 || position >= this.getSize()){
            throw new IndexOutOfBoundsException("position not available" + position);
        }
        else {
            if (getSize() == 1) {
                this.clear();
            }
            else {
                MyArray<E> copyArray = new MyArray<>(innerArray.size());
                int j = 0;
                for (int i = 0; i < this.getSize(); i++) {
                    if (i != position) {
                        copyArray.addAt(j, innerArray.getAt(i));
                        j++;
                    }
                }
                this.innerArray = copyArray;
                this.size--;
            }
        }
    }

    @Override
    public void clear() {
        innerArray = new MyArray<>(5);
        this.size = 0;
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

    public int hashCode () {
        return IterableMethodManager.getHashCode(this);
    }
}
