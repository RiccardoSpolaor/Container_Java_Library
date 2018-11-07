package it.unive.map;

import it.unive.list.MyArrayList;
import it.unive.list.MyNodeList;
import it.unive.MyIterator;
import it.unive.structs.MyMapEntry;
import it.unive.util.IterableMethodManager;
import it.unive.util.IterableReflectionManager;

public class MyPureMap<K, E> implements MyMap<K, E> {

    protected MyNodeList<MyMapEntry<K, E>> innerList;
    protected int size;

    public MyPureMap(){
        innerList = new MyNodeList<>();
        this.size = 0;
    }

    public MyPureMap(K key, E element){
        innerList = new MyNodeList<>();
        this.size = 0;
        this.put(key, element);
    }

    public MyPureMap(MyMapEntry<? extends K, ? extends E> ... elements){
        innerList = new MyNodeList<>();
        this.size = 0;
        this.add(elements);
    }

    public MyPureMap(MyMap<? extends K, ? extends  E> map){
        innerList = new MyNodeList<>();
        this.size = 0;
        this.addAll(map);
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void clear() {
        innerList = new MyNodeList<>();
        this.size = 0;
    }


    @Override
    public void put(K key, E element) {
        boolean found = false;

        for (int i = 0; i < this.getSize() && !found; i++){
            try {
                if (innerList.getAt(i).getKey().equals(key)) {
                    innerList.setAt(i, new MyMapEntry<>(key, element));
                    found = true;
                }
            } catch (NullPointerException e) {
                if (key==null) {
                    innerList.setAt(i, new MyMapEntry<>(null, element));
                    found = true;
                }
            }
        }

        if (!found){
            innerList.add(new MyMapEntry<>(key, element));
            this.size++;
        }
    }

    @Override
    public E getValue(K key) throws IllegalArgumentException {
        for (int i = 0; i < this.getSize(); i++){
            if (innerList.getAt(i).getKey().equals(key))
                return innerList.getAt(i).getElement();
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean remove (MyMapEntry<? extends K, ? extends E> entry) {
        boolean result = false;

        for (int i = 0; i < this.getSize() && !result; i++){
            if(innerList.getAt(i).equals(entry)){
                innerList.removeAt(i);
                result = true;
                this.size--;
            }
        }
        return result;
    }


    @Override
    public boolean removeByKey (K key) {
        boolean result = false;

        for (int i = 0; i < this.getSize() && !result; i++){
            try {
                if (innerList.getAt(i).getKey().equals(key)) {
                    innerList.removeAt(i);
                    result = true;
                    this.size--;
                }
            } catch (NullPointerException e) {
                if (key == null) {
                    innerList.removeAt(i);
                    result = true;
                    this.size--;
                }
            }
        }
        return result;
    }

    @Override
    public boolean removeByElement(E element) {
        boolean result = false;

        int i = 0;
        while (i < this.getSize()){
            try {
                if (innerList.getAt(i).getElement().equals(element)) {
                    innerList.removeAt(i);
                    this.size--;
                    result = true;
                }
                else
                    i++;
            } catch (NullPointerException exception) {
                if (element == null) {
                    innerList.removeAt(i);
                    this.size--;
                    result = true;
                }
                else
                    i++;
            }
        }
        return result;
    }

    @Override
    public MyIterator<MyMapEntry<? extends K,? extends E>> getIterator() {

        return new MyIterator<MyMapEntry<? extends K,? extends E>>() {

            private int pos = 0;

            @Override
            public MyMapEntry<K, E> next() {
                return MyPureMap.this.innerList.getAt(pos++);
            }

            @Override
            public boolean hasNext() {
                return pos < MyPureMap.this.innerList.getSize();
            }
        };
    }

    @Override
    public String toString () {
        StringBuilder returnString = new StringBuilder();
        if (this.isEmpty())
            returnString.append(String.format("%s: EMPTY \n", this.getClass().getSimpleName()));
        else {
            MyArrayList<K> keyList = new MyArrayList<>();
            MyArrayList<E> elementList = new MyArrayList<>();
            MyIterator <MyMapEntry<? extends K,? extends E>> iterator = this.getIterator();
            while (iterator.hasNext()) {
                MyMapEntry<? extends K,? extends E> entry = iterator.next();
                keyList.add(entry.getKey());
                elementList.add(entry.getElement());
            }
            returnString.append(String.format("%s of \"%s\" Keys and \"%s\" Elements:\n", this.getClass().getSimpleName(),
                    IterableReflectionManager.getGenericParameterName(keyList), IterableReflectionManager.getGenericParameterName(elementList)));
            iterator = this.getIterator();
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
