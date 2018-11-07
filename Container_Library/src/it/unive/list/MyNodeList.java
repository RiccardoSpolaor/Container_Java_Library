package it.unive.list;

import it.unive.MyIterable;
import it.unive.MyIterator;
import it.unive.structs.MyNode;
import it.unive.util.IterableMethodManager;
import it.unive.util.IterableReflectionManager;


public class MyNodeList<E> implements MyList<E> {

    protected MyNode<E> head;

    protected int size;


    public MyNodeList(){
        this.head = null;
        this.size = 0;
    }


    public MyNodeList(E element){
        this.size = 0;
        add (element);
    }


    public MyNodeList(E...elements){
        this.size = 0;
        add(elements);
    }


    public MyNodeList(MyIterable <? extends E> iterable) {
        this. size = 0;
        addAll (iterable);
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public E getAt(int position) throws IndexOutOfBoundsException{
        if (position < 0 || position > this.size)
            throw new IndexOutOfBoundsException("position not available" + position);
        else{
            MyNode<E> cursor = this.head;
            while (position-- != 0){
                cursor = cursor.getNext();
            }
            return cursor.getValue();
        }
    }

    @Override
    public void setAt(int position, E element) throws IndexOutOfBoundsException {
        if (position < 0 || position > this.size)
            throw new IndexOutOfBoundsException("position not available" + position);
        else {
            if (position == 0)
                head.setValue(element);
            else{
                MyNode<E> copyHead = this.head;
                for (int i = 0; i < position; i++) {
                    head  = head.getNext();
                }
                head.setValue(element);
                this.head = copyHead;
            }
        }
    }

    @Override
    public void addAt(int position, E element) throws IndexOutOfBoundsException {
        if (position < 0 || position > this.size)
            throw new IndexOutOfBoundsException("position not available" + position);
        else {
            if (position == 0){
                head = new MyNode<>(element, head);
            }
            else {
                MyNode<E> copyHead = this.head;
                for (int i = 0; i < position - 1; i++) {
                    head  = head.getNext();
                }
                head.setNext(new MyNode<>(element, head.getNext()));
                this.head = copyHead;
            }
            this.size++;
        }
    }

    @Override
    public void removeAt(int position) {
        if (position < 0 || position >= this.size)
            throw new IndexOutOfBoundsException("position not available" + position);
        else {
            if (position == 0){
                this.head = head.getNext();
            }
            else {
                MyNode<E> copyHead = head;
                for (int i = 0; i < position - 1; i++){
                    this.head = this.head.getNext();
                }

                head.setNext(head.getNext().getNext());
                this.head = copyHead;
            }
            this.size--;
        }
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
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

    @Override
    public int hashCode () {
        return IterableMethodManager.getHashCode(this);
    }
}

