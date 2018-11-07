package it.unive.structs;

public class MyMapEntry <K, E> {

    private final K key;
    private E element;

    public MyMapEntry () {
        this (null,null);
    }

    public MyMapEntry(K key, E element) {
        this.key = key;
        this.setElement (element);
    }

    public K getKey () {
        return this.key;
    }

    public E getElement () {
        return this.element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    @Override
    public boolean equals (Object object){
        if (object instanceof MyMapEntry) {
            MyMapEntry entry = (MyMapEntry) object;

            return entry.key.equals(this.key) && entry.element.equals(this.element);
        }
        else
            return false;
    }

    @Override
    public String toString () {
        return String.format("[%s: Key = %s; Element = %s]", this.getClass().getSimpleName(), this.key, this.element);
    }

    public int hashCode () {
        return 127 * (key==null ? 1 : key.hashCode()) + 31 *(element==null ? 1 : element.hashCode());
    }
}
