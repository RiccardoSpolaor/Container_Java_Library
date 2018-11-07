package it.unive.set;

import it.unive.map.MyPureMap;
import it.unive.map.MyMap;
import it.unive.MyComparator;
import it.unive.MyComparator.*;
import it.unive.MyIterable;
import it.unive.MyIterator;
import it.unive.structs.MyMapEntry;

public class MySortedPureSet<E> extends MyPureSet<E> {


    protected MyComparator<? super E> comparator;

    private Comparation orderWay = Comparation.FIRST_LOWER;

    public MySortedPureSet(MyComparator<? super E> comparator) {
        super ();
        this.comparator = comparator;
    }

    public MySortedPureSet(MyComparator<? super E> comparator, E element) {
        super (element);
        this.comparator = comparator;
    }

    public MySortedPureSet(MyComparator<? super E> comparator, E... elements) {
        this.comparator = comparator;
        super.innerMap = new MyPureMap<E, Object>();
        this.add (elements);
    }

    public MySortedPureSet(MyComparator<? super E> comparator, MyIterable<? extends E> collection) {

        this.comparator = comparator;
        super.innerMap = new MyPureMap<E, Object>();
        this.addAll (collection);
    }

    @Override
    public void add (E element) {
        MyMap<E, Object> copyMap = super.innerMap;
        MyIterator <MyMapEntry<? extends E,?>> compareIterator = copyMap.getIterator();
        super.innerMap = new MyPureMap<>();
        boolean added = false;
        while (compareIterator.hasNext()) {
            E comparedElement = compareIterator.next().getKey();
            try {
                if (!added && comparator.compare(element, comparedElement) == this.orderWay) {
                    super.innerMap.add(new MyMapEntry<>(element, super.nullValue),
                            new MyMapEntry<>(comparedElement, super.nullValue));
                    added = true;
                } else
                    super.innerMap.put(comparedElement, super.nullValue);
            } catch (NullPointerException e) {
                if (element==null) {
                    if (comparedElement != null) {
                        super.innerMap.add(new MyMapEntry<>(null, super.nullValue),
                                new MyMapEntry<>(comparedElement, super.nullValue));
                        added = true;
                    } else
                        super.innerMap.put(null, null);
                }
                else
                    super.innerMap.put(null, null);
            }
        }
        if (!added)
            super.innerMap.put (element, super.nullValue);
    }

    public void changeSortOrder() {

        if (orderWay == Comparation.FIRST_LOWER)
            orderWay = Comparation.FIRST_BIGGER;
        else
            orderWay = Comparation.FIRST_LOWER;

        MyMap<E, Object> copyMap = super.innerMap;
        MyIterator <MyMapEntry<? extends E,?>> copyIterator = copyMap.getIterator();
        this.clear();
        while (copyIterator.hasNext())
            this.add(copyIterator.next().getKey());
    }

    public E getFirstElement() throws IllegalAccessException {
        if (this.isEmpty())
            throw new IllegalAccessException();
        else
            return super.innerMap.getIterator().next().getKey();
    }

    public E getLastElement() throws IllegalAccessException {
        if (this.isEmpty())
            throw new IllegalAccessException();
        else {
            MyIterator <MyMapEntry<? extends E,?>> cursorIterator = super.innerMap.getIterator();
            E returnElement = null;
            while (cursorIterator.hasNext()) {
                returnElement = cursorIterator.next().getKey();
            }
            return returnElement;
        }
    }
}
