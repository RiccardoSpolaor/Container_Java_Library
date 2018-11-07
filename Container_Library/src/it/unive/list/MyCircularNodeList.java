package it.unive.list;

import it.unive.MyIterable;
import it.unive.MyIterator;
import it.unive.structs.MyNode;

public class MyCircularNodeList <E> extends MyNodeList<E> {
    protected MyNode<E> loopFirstNode;

    public MyCircularNodeList(){
        this.size = 0;
        loopFirstNode = null;
        this.head = null;
    }

    public MyCircularNodeList(E element){
        this.size = 0;
        loopFirstNode = null;
        this.head = null;
        add (element);
    }

    public MyCircularNodeList(int firstLoopPosition, E element) throws IndexOutOfBoundsException {
        try {
            this.size = 0;
            loopFirstNode = null;
            this.head = null;
            add(element);
            this.setFirstNodePositionOfLoop(firstLoopPosition);
        } catch (IndexOutOfBoundsException exception) {
            this.head=null;
            this.size=0;
            loopFirstNode = null;
            throw exception;
        }
    }


    public MyCircularNodeList (E...elements){
        this.size = 0;
        loopFirstNode = null;
        this.head = null;
        add(elements);
    }


    public MyCircularNodeList (int firstLoopPosition, E...elements) throws IndexOutOfBoundsException {
        try {
            this.size = 0;
            loopFirstNode = null;
            this.head = null;
            add(elements);
            this.setFirstNodePositionOfLoop(firstLoopPosition);
        } catch (IndexOutOfBoundsException exception) {
            this.head=null;
            this.size=0;
            loopFirstNode = null;
            throw exception;
        }
    }


    public MyCircularNodeList(MyIterable<? extends E> iterable) {
        this. size = 0;
        loopFirstNode = null;
        this.head = null;
        addAll (iterable);
    }

    public MyCircularNodeList(int firstLoopPosition, MyIterable<? extends E> iterable) {
        try {
            this.size = 0;
            loopFirstNode = head;
            this.head = null;
            addAll (iterable);
            this.setFirstNodePositionOfLoop(firstLoopPosition);

        } catch (IndexOutOfBoundsException exception) {
            this.head=null;
            this.size=0;
            loopFirstNode = null;
            throw exception;
        }
    }

    @Override
    public void addAt(int position, E element) throws IndexOutOfBoundsException {
        if (position < 0 || position > this.size)
            throw new IndexOutOfBoundsException("position not available" + position);
        else {
            if (position == 0){
                if (this.getSize()==0) {
                    head = new MyNode<>(element, loopFirstNode);
                    loopFirstNode=head;
                }
                else
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


    public void setFirstNodePositionOfLoop(int position) throws IndexOutOfBoundsException {
        if (position<0||position>= getSize())
            throw new IndexOutOfBoundsException();
        else {
            MyNode<E> copyHead = this.head;
            for (int i = 0; i < getSize(); i++) {
                if (i==position)
                    loopFirstNode = head;
                if (i< getSize()-1)
                    head = head.getNext();
            }
            head.setNext(loopFirstNode);
            this.head = copyHead;
        }
    }

    public int getFirstNodePositionOfLoop() {
        MyNode<E> copyHead = this.head;
        int returnPosition = 0;
        try {
            while (!copyHead.equals(loopFirstNode)) {
                copyHead = copyHead.getNext();
                returnPosition++;
            }
        } catch (NullPointerException exception) {
            return 0;
        }
        return returnPosition;
    }


    public static interface MyCircularListIterator<E> extends MyIterator <E> {
        public void triggerLoop();
        public void stopLoop();
    }

    private enum LoopState {OFF,ON};

    @Override
    public MyCircularListIterator <E> getIterator() {
        return new MyCircularListIterator<E>() {

            private LoopState state = LoopState.OFF;
            private int position = 0;
            private boolean passed = false;

            @Override
            public void triggerLoop() {
                state = LoopState.ON;
            }

            @Override
            public void stopLoop() {
                state = LoopState.OFF;
            }

            @Override
            public boolean hasNext() {
                if (this.state== LoopState.OFF)
                    return this.position != MyCircularNodeList.this.getFirstNodePositionOfLoop() || !passed;
                else
                    return MyCircularNodeList.this.getSize() != 0;
            }

            @Override
            public E next() {
                try {
                    if (position==MyCircularNodeList.this.getFirstNodePositionOfLoop())
                        passed=true;
                    E returnValue = MyCircularNodeList.this.getAt(position++);
                    if (position==MyCircularNodeList.this.size) {
                        position=MyCircularNodeList.this.getFirstNodePositionOfLoop();
                    }
                    return returnValue;
                } catch (IndexOutOfBoundsException ex){
                    throw new IndexOutOfBoundsException();
                }
            }
        };
    }
}
