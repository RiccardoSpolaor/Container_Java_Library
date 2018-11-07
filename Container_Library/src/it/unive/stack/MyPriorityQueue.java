package it.unive.stack;

import it.unive.list.MyArrayList;
import it.unive.MyIterable;
import it.unive.MyIterator;
import it.unive.util.IterableReflectionManager;

public class MyPriorityQueue<E> extends MyFIFOQueue<E> {

    public enum PriorityState {
        EQUALITY_PRIORITY, //Default
        DIFFERENCE_PRIORITY
    }

    protected PriorityState priorityState;

    public MyPriorityQueue() {
        super();
        this.priorityState =PriorityState.EQUALITY_PRIORITY;
    }

    public MyPriorityQueue(PriorityState priorityState) {
        super();
        this.priorityState = priorityState;
    }

    public MyPriorityQueue(E element) {
        super(element);
        this.priorityState = PriorityState.EQUALITY_PRIORITY;
    }

    public MyPriorityQueue(PriorityState priorityState, E element) {
        super(element);
        this.priorityState = priorityState;
    }

    public MyPriorityQueue(E... elements) {
        this.priorityState = PriorityState.EQUALITY_PRIORITY;
        super.size = 0;
        innerArray = new MyArrayList<>();
        add(elements);
    }

    public MyPriorityQueue(PriorityState priorityState, E... elements) {
        this.priorityState = priorityState;
        super.size = 0;
        innerArray = new MyArrayList<>();
        add(elements);
    }

    public MyPriorityQueue(MyIterable<? extends E> iterable) {
        this.priorityState = PriorityState.EQUALITY_PRIORITY;
        super.size = 0;
        super.innerArray = new MyArrayList<>();
        this.addAll(iterable);
    }

    public MyPriorityQueue(PriorityState priorityState, MyIterable<? extends E> iterable) {
        this.priorityState = priorityState;
        super.size = 0;
        super.innerArray = new MyArrayList<>();
        this.addAll(iterable);
    }

    @Override
    public void add(E element) {
        addByPriorityState(element);
        size++;
    }


    protected void addByPriorityState(E element) {

        boolean flag = false;

        int counter = 1;

        int i = 0;
        while (i < super.innerArray.getSize()) {
            try {
                if (element.equals(super.innerArray.getAt(i))) {
                    counter++;
                    super.innerArray.removeAt(i);
                } else
                    i++;
            } catch (NullPointerException exception) {
                if (super.innerArray.getAt(i) == null) {
                    counter++;
                    super.innerArray.removeAt(i);
                } else
                    i++;
            }
        }

        int innerCounter = 0;
        E comparedElement = null;
        int insertPosition = 0;

        if (this.priorityState == PriorityState.EQUALITY_PRIORITY) {
            try {
                comparedElement = innerArray.getAt(0);
            } catch (IndexOutOfBoundsException ex) {
                flag = true;
            }
            for (i = 0; !flag && i <= innerArray.getSize(); i++) {
                if (i == innerArray.getSize()) {
                    if (counter >= innerCounter)
                        insertPosition = i;
                } else {
                    try {
                        if (comparedElement.equals(innerArray.getAt(i))) {
                            innerCounter++;
                        } else {
                            comparedElement = innerArray.getAt(i);
                            if (counter >= innerCounter)
                                insertPosition = i;
                            innerCounter = 1;
                        }
                    } catch (NullPointerException exception) {
                        if (innerArray.getAt(i) == null)
                            innerCounter++;
                        else {
                            comparedElement = innerArray.getAt(i);
                            if (counter >= innerCounter)
                                insertPosition = i;
                            innerCounter = 1;
                        }
                    }
                }
            }

            if (flag)
                insertPosition = innerArray.getSize();
        }

        if (this.priorityState == PriorityState.DIFFERENCE_PRIORITY) {
            innerCounter = 1;
            try {
                comparedElement = innerArray.getAt(0);
            } catch (Exception ex) {
                flag = true;
            }

            for (i = 0; !flag && i < innerArray.getSize(); i++) {
                try {
                    if (i > 0 && innerArray.getAt(i).equals(comparedElement)) {
                        innerCounter++;
                    } else {
                        comparedElement = innerArray.getAt(i);
                        if (innerCounter >= counter) {
                            insertPosition = i;
                        } else {
                            insertPosition = i - innerCounter + 1;
                            flag = true;
                        }
                        innerCounter = 1;
                    }
                } catch (NullPointerException exception) {
                    if (i > 0 && comparedElement == null) {
                        innerCounter++;
                    } else {
                        comparedElement = innerArray.getAt(i);
                        if (innerCounter >= counter) {
                            insertPosition = i;
                        } else {
                            insertPosition = i - innerCounter + 1;
                            flag = true;
                        }
                        innerCounter = 1;
                    }
                }
            }
            if (!flag) {
                if (innerCounter > counter)
                    insertPosition = innerArray.getSize();
                else
                    insertPosition = innerArray.getSize() - innerCounter + 1;
            }
        }

        for (i = 0; i < counter; i++) {
            super.innerArray.addAt(insertPosition, element);
        }
    }


    public void setPriorityState(PriorityState priorityState) {
        if (this.priorityState != priorityState) {
            this.priorityState = priorityState;
            MyArrayList<E> copyArray = new MyArrayList<>(super.innerArray);
            int copySize = getSize();
            this.clear();
            for (int i = 0; i < copySize; i++)
                this.add(copyArray.getAt(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        if (this.isEmpty())
            returnString.append(String.format("%s: EMPTY \n", this.getClass().getSimpleName()));
        else {
            returnString.append(String.format("%s of \"%s\" Objects with \"%s\" priority:\n", this.getClass().getSimpleName(),
                    IterableReflectionManager.getGenericParameterName(this), this.priorityState));
            MyIterator<E> iterator = this.getIterator();
            while (iterator.hasNext()) {
                returnString.append(String.format("\t* %s%c\n", iterator.next(), iterator.hasNext() ? ';' : '.'));
            }
        }
        return returnString.toString();
    }
}
