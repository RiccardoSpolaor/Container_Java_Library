package it.unive;

public interface MyComparator <E> {

    public enum Comparation {
        FIRST_LOWER, EQUAL, FIRST_BIGGER
    }

    public Comparation compare (E firstElement, E secondElement);

}
