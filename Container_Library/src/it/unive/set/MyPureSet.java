package it.unive.set;

import it.unive.map.MyMap;
import it.unive.map.MyPureMap;
import it.unive.MyIterable;
import it.unive.MyIterator;
import it.unive.structs.MyMapEntry;
import it.unive.util.IterableMethodManager;
import it.unive.util.IterableReflectionManager;

public class MyPureSet<E> implements MySet<E> {

    protected MyMap<E, Object> innerMap;
    protected final Object nullValue = null;

    public MyPureSet() {
        innerMap = new MyPureMap<E, Object>();
    }

    public MyPureSet(E element) {
        innerMap = new MyPureMap<E, Object>();
        this.add (element);
    }

    public MyPureSet(E... elements) {
        innerMap = new MyPureMap<E, Object>();
        this.add (elements);
    }

    public MyPureSet(MyIterable<? extends E> iterable) {
        innerMap = new MyPureMap<E, Object>();
        this.addAll (iterable);
    }

    @Override
    public void add(E element) {
        innerMap.put (element, nullValue);
    }

    @Override
    public boolean remove(E element) {
        return innerMap.removeByKey(element);
    }

    @Override
    public int getSize() {
        return innerMap.getSize();
    }

    @Override
    public void clear() {
        innerMap = new MyPureMap<>();

    }

    @Override
    public MyIterator<E> getIterator() {
        return new MyIterator<E>() {

            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < MyPureSet.this.innerMap.getSize();
            }

            @Override
            public E next() {
                MyIterator <MyMapEntry<? extends E,?>> iterator = MyPureSet.this.innerMap.getIterator();
                int innerPos = 0;
                while (iterator.hasNext() && innerPos < pos) {
                    innerPos++;
                    iterator.next();
                }
                this.pos++;
                return iterator.next().getKey();
            }
        };
    }

    @Override public String toString () {
        StringBuilder returnString = new StringBuilder();
        if (this.isEmpty())
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
