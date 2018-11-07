package it.unive._main;

import it.unive.MyComparator;
import it.unive.MyContainer;
import it.unive.list.MyArrayList;
import it.unive.list.MyCircularNodeList;
import it.unive.list.MyNodeList;
import it.unive.map.MyPureMap;
import it.unive.set.MyPureSet;
import it.unive.set.MySortedPureSet;
import it.unive.stack.MyFIFOQueue;
import it.unive.stack.MyLIFOStack;
import it.unive.stack.MyPriorityQueue;
import it.unive.structs.MyMapEntry;

import java.util.Random;

public class UserContainerOperations {
    static IterableTester.ParameterType getParameterByUserChoice(int inputNumber) throws IllegalArgumentException {
        switch (inputNumber) {
            case 1: return IterableTester.ParameterType.NUMBER;
            case 2: return IterableTester.ParameterType.INTEGER;
            case 3: return IterableTester.ParameterType.DOUBLE;
            case 4: return IterableTester.ParameterType.CHARACTER;
            case 5: return IterableTester.ParameterType.MAPENTRY;
            case 6: return IterableTester.ParameterType.OBJECT;
            default: throw new IllegalArgumentException();
        }
    }

    static MyContainer<?> getContainerByUserChoice(int inputNumber, IterableTester.ParameterType parameter) {
        switch (inputNumber) {
            case 1: {
                return new MyArrayList<>();
            }
            case 2: {
                return new MyNodeList<>();
            }
            case 3: {
                return new MyCircularNodeList<>();
            }
            case 4: {
                return new MyPureSet<>();
            }
            case 5: {
                return getSortedSet(parameter);
            }
            case 6: {
                return new MyLIFOStack<>();
            }
            case 7: {
                return new MyFIFOQueue<>();
            }
            case 8: {
                return new MyPriorityQueue<>();
            }
            case 9: {
                return new MyPureMap<>();
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    private static MySortedPureSet<?> getSortedSet (IterableTester.ParameterType parameter) throws IllegalArgumentException{
        switch (parameter) {
            case NUMBER: {
                return new MySortedPureSet<Number>(new MyComparator<Number>() {
                    @Override
                    public Comparation compare(Number firstElement, Number secondElement) {
                        if (firstElement.equals(secondElement))
                            return Comparation.EQUAL;
                        else if (firstElement.doubleValue() > secondElement.doubleValue())
                            return Comparation.FIRST_BIGGER;
                        else
                            return Comparation.FIRST_LOWER;
                    }
                });
            }
            case INTEGER: {
                return new MySortedPureSet<Integer>(new MyComparator<Integer>() {
                    @Override
                    public Comparation compare(Integer firstElement, Integer secondElement) {
                        if (firstElement.equals(secondElement))
                            return Comparation.EQUAL;
                        else if (firstElement > secondElement)
                            return Comparation.FIRST_BIGGER;
                        else
                            return Comparation.FIRST_LOWER;
                    }
                });
            }
            case DOUBLE: {
                return new MySortedPureSet<Double>(new MyComparator<Double>() {
                    @Override
                    public Comparation compare(Double firstElement, Double secondElement) {
                        if (firstElement.equals(secondElement))
                            return Comparation.EQUAL;
                        else if (firstElement > secondElement)
                            return Comparation.FIRST_BIGGER;
                        else
                            return Comparation.FIRST_LOWER;
                    }
                });
            }
            case CHARACTER: {
                return new MySortedPureSet<Character>(new MyComparator<Character>() {
                    @Override
                    public Comparation compare(Character firstElement, Character secondElement) {
                        if (firstElement.equals(secondElement))
                            return Comparation.EQUAL;
                        else if (firstElement > secondElement)
                            return Comparation.FIRST_BIGGER;
                        else
                            return Comparation.FIRST_LOWER;
                    }
                });
            }
            case MAPENTRY: {
                return new MySortedPureSet<MyMapEntry<? extends Integer, ? extends Character>>
                        (new MyComparator<MyMapEntry<? extends Integer, ? extends Character>>() {
                            @Override
                            public Comparation compare(MyMapEntry<? extends Integer, ? extends Character> firstElement,
                                                       MyMapEntry<? extends Integer, ? extends Character> secondElement) {
                                if (firstElement.getKey().equals(secondElement.getKey()))
                                    return Comparation.EQUAL;
                                else if (firstElement.getKey() > secondElement.getKey())
                                    return Comparation.FIRST_BIGGER;
                                else
                                    return Comparation.FIRST_LOWER;
                            }
                        });
            }
            case OBJECT: {
                return new MySortedPureSet<Object>(new MyComparator<Object>() {
                    @Override
                    public Comparation compare(Object firstElement, Object secondElement) {
                        if (firstElement.equals(secondElement))
                            return Comparation.EQUAL;
                        else if (firstElement.hashCode() > secondElement.hashCode())
                            return Comparation.FIRST_BIGGER;
                        else
                            return Comparation.FIRST_LOWER;
                    }
                });
            }

            default: {
                throw new IllegalArgumentException();
            }
        }
    }

    static void addSingle (MyContainer<?> container, IterableTester.ParameterType parameter) throws IllegalArgumentException {
        switch (parameter) {
            case NUMBER: {
                Number elementToAdd = RandomObjectFactory.getRandomNumber();
                System.out.println("* Aggiunta di un elemento: " + elementToAdd);
                ((MyContainer<Number>) container).add(elementToAdd);
                break;
            }
            case INTEGER: {
                Integer elementToAdd = RandomObjectFactory.getRandomInteger();
                System.out.println("* Aggiunta di un elemento: " + elementToAdd);
                ((MyContainer<Integer>) container).add(elementToAdd);
                break;
            }
            case DOUBLE: {
                Double elementToAdd = RandomObjectFactory.getRandomDouble();
                System.out.println("* Aggiunta di un elemento: " + elementToAdd);
                ((MyContainer<Double>) container).add(elementToAdd);
                break;
            }
            case CHARACTER: {
                Character elementToAdd = RandomObjectFactory.getRandomCharacter();
                System.out.println("* Aggiunta di un elemento: " + elementToAdd);
                ((MyContainer<Character>) container).add(elementToAdd);
                break;
            }
            case MAPENTRY: {
                MyMapEntry<? extends Integer, ? extends Character> elementToAdd = RandomObjectFactory.getRandomEntry();
                System.out.println("* Aggiunta di un elemento: " + elementToAdd);
                ((MyContainer<MyMapEntry<? extends Integer, ? extends Character>>) container).add(elementToAdd);
                break;
            }
            case OBJECT: {
                Object elementToAdd = RandomObjectFactory.getRandomObject();
                System.out.println("* Aggiunta di un elemento: " + elementToAdd);
                ((MyContainer<Object>) container).add(elementToAdd);
                break;
            }

            default: {
                throw new IllegalArgumentException("Tipo non consentito.");
            }
        }
        System.out.println(String.format("Il container dopo l'aggiunta è:\n%s", container));
    }

    static void addMultiple (MyContainer<?> container, IterableTester.ParameterType parameter) throws IllegalArgumentException {
        switch (parameter) {
            case NUMBER: {
                Number[] elementsToAdd = new Number[]{RandomObjectFactory.getRandomNumber(), RandomObjectFactory.getRandomNumber(), RandomObjectFactory.getRandomNumber()};
                System.out.print("* Aggiunta di più elementi: ");
                for (Number x : elementsToAdd) {
                    System.out.print(x + " ");
                }
                System.out.println();
                ((MyContainer<Number>) container).add(elementsToAdd);
                break;
            }
            case INTEGER: {
                Integer[] elementsToAdd = new Integer[]{RandomObjectFactory.getRandomInteger(), RandomObjectFactory.getRandomInteger(), RandomObjectFactory.getRandomInteger()};
                System.out.print("* Aggiunta di più elementi: ");
                for (Integer x : elementsToAdd) {
                    System.out.print(x + " ");
                }
                System.out.println();
                ((MyContainer<Integer>) container).add(elementsToAdd);
                break;
            }
            case DOUBLE: {
                Double[] elementsToAdd = new Double[]{RandomObjectFactory.getRandomDouble(), RandomObjectFactory.getRandomDouble(), RandomObjectFactory.getRandomDouble()};
                System.out.print("* Aggiunta di più elementi: ");
                for (Double x : elementsToAdd) {
                    System.out.print(x + " ");
                }
                System.out.println();
                ((MyContainer<Double>) container).add(elementsToAdd);
                break;
            }
            case CHARACTER: {
                Character[] elementsToAdd = new Character[]{RandomObjectFactory.getRandomCharacter(), RandomObjectFactory.getRandomCharacter(), RandomObjectFactory.getRandomCharacter()};
                System.out.print("* Aggiunta di più elementi: ");
                for (Character x : elementsToAdd) {
                    System.out.print(x + " ");
                }
                System.out.println();
                ((MyContainer<Character>) container).add(elementsToAdd);
                break;
            }
            case MAPENTRY: {
                MyMapEntry<? extends Integer, ? extends Character>[] elementsToAdd =
                        new MyMapEntry[]{RandomObjectFactory.getRandomEntry(), RandomObjectFactory.getRandomEntry(), RandomObjectFactory.getRandomEntry()};
                System.out.print("* Aggiunta di più elementi: ");
                for (MyMapEntry x : elementsToAdd) {
                    System.out.print(x + " ");
                }
                System.out.println();
                ((MyContainer<MyMapEntry>) container).add(elementsToAdd);
                break;
            }
            case OBJECT: {
                Object[] elementsToAdd = new Object[]{RandomObjectFactory.getRandomObject(), RandomObjectFactory.getRandomObject(), RandomObjectFactory.getRandomObject()};
                System.out.print("* Aggiunta di più elementi: ");
                for (Object x : elementsToAdd) {
                    System.out.print(x + " ");
                }
                System.out.println();
                ((MyContainer<Object>) container).add(elementsToAdd);
                break;
            }

            default: {
                throw new IllegalArgumentException("Tipo non consentito.");
            }
        }
        System.out.println(String.format("Il container dopo l'aggiunta è:\n%s", container));
    }

    static void addIterator (MyContainer<?> container, IterableTester.ParameterType parameter) throws IllegalArgumentException {
        switch (parameter) {
            case NUMBER: {
                MyContainer<Number> otherContainer = new MyArrayList<>(RandomObjectFactory.getRandomNumber(), RandomObjectFactory.getRandomNumber(), RandomObjectFactory.getRandomNumber());
                System.out.println(String.format("* Aggiunta di elementi da un container del tipo: \n%s", otherContainer));
                ((MyContainer<Number>) container).addAll(otherContainer);
                break;
            }
            case INTEGER: {
                MyContainer<Integer> otherContainer = new MyPureSet<>(RandomObjectFactory.getRandomInteger(), RandomObjectFactory.getRandomInteger(), RandomObjectFactory.getRandomInteger());
                System.out.println(String.format("* Aggiunta di elementi da un container del tipo: \n%s", otherContainer));
                ((MyContainer<Integer>) container).addAll(otherContainer);
                break;
            }
            case DOUBLE: {
                MyContainer<Double> otherContainer = new MyLIFOStack<>(RandomObjectFactory.getRandomDouble(), RandomObjectFactory.getRandomDouble(), RandomObjectFactory.getRandomDouble());
                System.out.println(String.format("* Aggiunta di elementi da un container del tipo: \n%s", otherContainer));
                ((MyContainer<Double>) container).addAll(otherContainer);
                break;
            }
            case CHARACTER: {
                MyContainer<Character> otherContainer = new MyPriorityQueue<Character>(RandomObjectFactory.getRandomCharacter(), RandomObjectFactory.getRandomCharacter(), RandomObjectFactory.getRandomCharacter());
                System.out.println(String.format("* Aggiunta di elementi da un container del tipo: \n%s", otherContainer));
                ((MyContainer<Character>) container).addAll(otherContainer);
                break;
            }
            case MAPENTRY: {
                MyContainer<MyMapEntry<? extends Integer, ? extends Character>> otherContainer =
                        new MyPureMap<>(RandomObjectFactory.getRandomEntry(), RandomObjectFactory.getRandomEntry(), RandomObjectFactory.getRandomEntry());
                System.out.println(String.format("* Aggiunta di elementi da un container del tipo: \n%s", otherContainer));
                ((MyContainer<MyMapEntry>) container).addAll(otherContainer);
                break;
            }

            case OBJECT: {
                MyContainer<Object> otherContainer = new MyNodeList<>(RandomObjectFactory.getRandomObject(), RandomObjectFactory.getRandomObject(), RandomObjectFactory.getRandomObject());
                System.out.println(String.format("* Aggiunta di elementi da un container del tipo: \n%s", otherContainer));
                ((MyContainer<Object>) container).addAll(otherContainer);
                break;
            }

            default: {
                throw new IllegalArgumentException("Tipo non consentito.");
            }
        }
        System.out.println(String.format("Il container dopo l'aggiunta è:\n%s", container));
    }

    static void containsSingle (MyContainer<?> container, IterableTester.ParameterType parameter) throws IllegalArgumentException {

        Random random = new Random();

        switch (parameter) {
            case NUMBER: {
                Number elementToCheck = RandomObjectFactory.getRandomNumber();
                System.out.print("* Il container contiene l'elemento: ");
                System.out.println(elementToCheck + "?");
                System.out.println(((MyContainer<Number>) container).contains(elementToCheck)?"\tSì\n":"\tNo\n");
                break;
            }
            case INTEGER: {
                Integer elementToCheck = RandomObjectFactory.getRandomInteger();
                System.out.print("* Il container contiene l'elemento: ");
                System.out.println(elementToCheck + "?");
                System.out.println(((MyContainer<Integer>) container).contains(elementToCheck)?"\tSì\n":"\tNo\n");
                break;
            }
            case DOUBLE: {
                Double elementToCheck = RandomObjectFactory.getRandomDouble();
                System.out.print("* Il container contiene l'elemento: ");
                System.out.println(elementToCheck + "?");
                System.out.println(((MyContainer<Double>) container).contains(elementToCheck)?"\tSì\n":"\tNo\n");
                break;
            }
            case CHARACTER: {
                Character elementToCheck = RandomObjectFactory.getRandomCharacter();
                System.out.print("* Il container contiene l'elemento: ");
                System.out.println(elementToCheck + "?");
                System.out.println(((MyContainer<Character>) container).contains(elementToCheck)?"\tSì\n":"\tNo\n");
                break;
            }
            case MAPENTRY: {
                MyMapEntry<? extends Integer,? extends Character> elementToCheck = RandomObjectFactory.getRandomEntry();
                System.out.print("* Il container contiene l'elemento: ");
                System.out.println(elementToCheck + "?");
                System.out.println(((MyContainer<MyMapEntry<? extends Integer, ? extends  Character>>) container).
                        contains(elementToCheck)?"\tSì\n":"\tNo\n");
                break;
            }
            case OBJECT: {
                Object elementToCheck = RandomObjectFactory.getRandomObject();
                System.out.print("* Il container contiene l'elemento: ");
                System.out.println(elementToCheck + "?");
                System.out.println(((MyContainer<Object>) container).contains(elementToCheck)?"\tSì\n":"\tNo\n");
                break;
            }

            default: {
                throw new IllegalArgumentException("Tipo non consentito.");
            }
        }
    }

    static void containsMultiple (MyContainer<?> container, IterableTester.ParameterType parameter) throws IllegalArgumentException {
        Random random = new Random();

        switch (parameter) {
            case NUMBER: {
                Number[] elementsToCheck = new Number[]{RandomObjectFactory.getRandomNumber(), RandomObjectFactory.getRandomNumber()};
                System.out.print("* Il container contiene tutti gli elementi: ");
                for (Number x : elementsToCheck) {
                    System.out.print(x + " ");
                }
                System.out.println(((MyContainer<Number>) container).contains(elementsToCheck)?"\tSì\n":"\tNo\n");
                break;
            }
            case INTEGER: {
                Integer[] elementsToCheck = new Integer[]{RandomObjectFactory.getRandomInteger(), RandomObjectFactory.getRandomInteger()};
                System.out.print("* Il container contiene tutti gli elementi: ");
                for (Integer x : elementsToCheck) {
                    System.out.print(x + " ");
                }
                System.out.println();
                System.out.println(((MyContainer<Integer>) container).contains(elementsToCheck)?"\tSì\n":"\tNo\n");
                break;
            }
            case DOUBLE: {
                Double[] elementsToCheck = new Double[]{RandomObjectFactory.getRandomDouble(), RandomObjectFactory.getRandomDouble()};
                System.out.print("* Il container contiene tutti gli elementi: ");
                for (Double x : elementsToCheck) {
                    System.out.print(x + " ");
                }
                System.out.println();
                System.out.println(((MyContainer<Double>) container).contains(elementsToCheck)?"\tSì\n":"\tNo\n");
                break;
            }
            case CHARACTER: {
                Character[] elementsToCheck = new Character[]{RandomObjectFactory.getRandomCharacter(), RandomObjectFactory.getRandomCharacter()};
                System.out.print("* Il container contiene tutti gli elementi: ");
                for (Character x : elementsToCheck) {
                    System.out.print(x + " ");
                }
                System.out.println();
                System.out.println(((MyContainer<Character>) container).contains(elementsToCheck)?"\tSì\n":"\tNo\n");
                break;
            }
            case MAPENTRY: {
                MyMapEntry<? extends  Integer, ? extends Character>[] elementsToCheck =
                        new MyMapEntry[] {RandomObjectFactory.getRandomEntry(), RandomObjectFactory.getRandomEntry()};
                System.out.print("* Il container contiene tutti gli elementi: ");
                for (MyMapEntry<? extends  Integer, ? extends Character> x : elementsToCheck) {
                    System.out.print(x + " ");
                }
                System.out.println();
                System.out.println(((MyContainer<MyMapEntry<? extends  Integer, ? extends Character>>) container).contains(elementsToCheck)?"\tSì\n":"\tNo\n");
                break;
            }
            case OBJECT: {
                Object[] elementsToCheck = new Object[] {RandomObjectFactory.getRandomObject(), RandomObjectFactory.getRandomObject()};
                System.out.print("* Il container contiene tutti gli elementi: ");
                for (Object x : elementsToCheck) {
                    System.out.print(x + " ");
                }
                System.out.println();
                System.out.println(((MyContainer<Object>) container).contains(elementsToCheck)?"\tSì\n":"\tNo\n");
                break;
            }

            default: {
                throw new IllegalArgumentException("Tipo non consentito.");
            }
        }
    }

    static void containsIterator (MyContainer<?> container, IterableTester.ParameterType parameter) throws IllegalArgumentException {
        switch (parameter) {
            case NUMBER: {
                MyContainer<Number> otherContainer = new MyNodeList<>(RandomObjectFactory.getRandomNumber(), RandomObjectFactory.getRandomNumber());
                System.out.println(String.format("\"* Il container contiene tutti gli elementi del container del tipo: \n%s", otherContainer));
                System.out.println(((MyContainer<Number>) container).containsAll(otherContainer)?"\tSì\n":"\tNo\n");
                break;
            }
            case INTEGER: {
                MyContainer<Integer> otherContainer = new MySortedPureSet<>(new MyComparator<Integer>() {
                    @Override
                    public Comparation compare(Integer firstElement, Integer secondElement) {
                        if (firstElement == secondElement)
                            return Comparation.EQUAL;
                        else if (firstElement > secondElement)
                            return Comparation.FIRST_BIGGER;
                        else
                            return Comparation.FIRST_LOWER;
                    }
                }, RandomObjectFactory.getRandomInteger(), RandomObjectFactory.getRandomInteger());
                System.out.println(String.format("\"* Il container contiene tutti gli elementi del container del tipo: \n%s", otherContainer));
                System.out.println(((MyContainer<Integer>) container).containsAll(otherContainer)?"\tSì\n":"\tNo\n");
                break;
            }
            case DOUBLE: {
                MyContainer<Double> otherContainer = new MyFIFOQueue<>(RandomObjectFactory.getRandomDouble(), RandomObjectFactory.getRandomDouble());
                System.out.println(String.format("\"* Il container contiene tutti gli elementi del container del tipo: \n%s", otherContainer));
                System.out.println(((MyContainer<Double>) container).containsAll(otherContainer)?"\tSì\n":"\tNo\n");
                break;
            }
            case CHARACTER: {
                MyContainer<Character> otherContainer = new MyLIFOStack<>(RandomObjectFactory.getRandomCharacter(), RandomObjectFactory.getRandomCharacter());
                System.out.println(String.format("\"* Il container contiene tutti gli elementi del container del tipo: \n%s", otherContainer));
                System.out.println(((MyContainer<Character>) container).containsAll(otherContainer)?"\tSì\n":"\tNo\n");
                break;
            }
            case MAPENTRY: {
                MyContainer<MyMapEntry<? extends Integer, ? extends Character>> otherContainer =
                        new MyPureMap<>(RandomObjectFactory.getRandomEntry(), RandomObjectFactory.getRandomEntry(), RandomObjectFactory.getRandomEntry());
                System.out.println(String.format("\"* Il container contiene tutti gli elementi del container del tipo: \n%s", otherContainer));
                System.out.println(((MyContainer<MyMapEntry>) container).containsAll(otherContainer)?"\tSì\n":"\tNo\n");
                break;
            }
            case OBJECT: {
                MyContainer<Object> otherContainer = new MyCircularNodeList<>(RandomObjectFactory.getRandomObject(), RandomObjectFactory.getRandomObject());
                System.out.println(String.format("\"* Il container contiene tutti gli elementi del container del tipo: \n%s", otherContainer));
                System.out.println(((MyContainer<Object>) container).containsAll(otherContainer)?"\tSì\n":"\tNo\n");
                break;
            }

            default: {
                throw new IllegalArgumentException("Tipo non consentito.");
            }
        }
    }

    static void removeSingle (MyContainer<?> container, IterableTester.ParameterType parameter) throws IllegalArgumentException {
        boolean removed = false;
        switch (parameter) {
            case NUMBER: {
                System.out.print("* Prova di rimozione di un elemento: ");
                Number elementToRemove = RandomObjectFactory.getRandomNumber();
                System.out.println(elementToRemove);
                removed = ((MyContainer<Number>) container).remove(elementToRemove);
                break;
            }
            case INTEGER: {
                System.out.print("* Prova di rimozione di un elemento: ");
                Integer elementToRemove = RandomObjectFactory.getRandomInteger();
                System.out.println(elementToRemove);
                removed = ((MyContainer<Integer>) container).remove(elementToRemove);
                break;
            }
            case DOUBLE: {
                System.out.print("* Prova di rimozione di un elemento: ");
                Double elementToRemove = RandomObjectFactory.getRandomDouble();
                System.out.println(elementToRemove);
                removed = ((MyContainer<Double>) container).remove(elementToRemove);
                break;
            }
            case CHARACTER: {
                System.out.print("* Prova di rimozione di un elemento: ");
                Character elementToRemove = RandomObjectFactory.getRandomCharacter();
                System.out.println(elementToRemove);
                removed = ((MyContainer<Character>) container).remove(elementToRemove);
                break;
            }
            case MAPENTRY: {
                System.out.print("* Prova di rimozione di un elemento: ");
                MyMapEntry<? extends Integer, ? extends Character> elementToRemove = RandomObjectFactory.getRandomEntry();
                System.out.println(elementToRemove);
                removed = ((MyContainer<MyMapEntry>) container).remove(elementToRemove);
                break;
            }
            case OBJECT: {
                System.out.print("* Prova di rimozione di un elemento: ");
                Object elementToRemove = RandomObjectFactory.getRandomObject();
                System.out.println(elementToRemove);
                removed = ((MyContainer<Object>) container).remove(elementToRemove);
                break;
            }

            default: {
                throw new IllegalArgumentException("Tipo non consentito.");
            }
        }
        if (removed)
            System.out.println(String.format("Rimozione riuscita, risultato:\n%s", container));
        else
            System.out.println("Elemento non presente, rimozione fallita\n");
    }

    static void removeMultiple (MyContainer<?> container, IterableTester.ParameterType parameter) throws IllegalArgumentException {
        boolean removed = false;
        switch (parameter) {
            case NUMBER: {
                System.out.print("* Prova di rimozione di più elementi: ");
                Number[] elementsToRemove = new Number[]{RandomObjectFactory.getRandomNumber(), RandomObjectFactory.getRandomNumber(), RandomObjectFactory.getRandomNumber()};
                for (Number x : elementsToRemove) {
                    System.out.print(x + " ");
                }
                System.out.println();
                removed = ((MyContainer<Number>) container).remove(elementsToRemove);
                break;
            }
            case INTEGER: {
                System.out.print("* Prova di rimozione di più elementi: ");
                Integer[] elementsToRemove = new Integer[]{RandomObjectFactory.getRandomInteger(), RandomObjectFactory.getRandomInteger(), RandomObjectFactory.getRandomInteger()};
                for (Integer x : elementsToRemove) {
                    System.out.print(x + " ");
                }
                System.out.println();
                removed = ((MyContainer<Integer>) container).remove(elementsToRemove);
                break;
            }
            case DOUBLE: {
                System.out.print("* Prova di rimozione di più elementi: ");
                Double[] elementsToRemove = new Double[]{RandomObjectFactory.getRandomDouble(), RandomObjectFactory.getRandomDouble(), RandomObjectFactory.getRandomDouble()};
                for (Double x : elementsToRemove) {
                    System.out.print(x + " ");
                }
                System.out.println();
                removed = ((MyContainer<Double>) container).remove(elementsToRemove);
                break;
            }
            case CHARACTER: {
                System.out.print("* Prova di rimozione di più elementi: ");
                Character[] elementsToRemove = new Character[]{RandomObjectFactory.getRandomCharacter(), RandomObjectFactory.getRandomCharacter(), RandomObjectFactory.getRandomCharacter()};
                for (Character x : elementsToRemove) {
                    System.out.print(x + " ");
                }
                System.out.println();
                removed = ((MyContainer<Character>) container).remove(elementsToRemove);
                break;
            }
            case MAPENTRY: {
                System.out.print("* Prova di rimozione di più elementi: ");
                MyMapEntry<? extends IterableTester, ? extends Character>[] elementsToRemove =
                        new MyMapEntry[]{RandomObjectFactory.getRandomEntry(), RandomObjectFactory.getRandomEntry(), RandomObjectFactory.getRandomEntry()};
                for (MyMapEntry x : elementsToRemove) {
                    System.out.print(x + " ");
                }
                System.out.println();
                removed = ((MyContainer<MyMapEntry>) container).remove(elementsToRemove);
                break;
            }
            case OBJECT: {
                System.out.print("* Prova di rimozione di più elementi: ");
                Object[] elementsToRemove = new Object[]{RandomObjectFactory.getRandomObject(), RandomObjectFactory.getRandomObject(), RandomObjectFactory.getRandomObject()};
                for (Object x : elementsToRemove) {
                    System.out.print(x + " ");
                }
                System.out.println();
                removed = ((MyContainer<Object>) container).remove(elementsToRemove);
                break;
            }

            default: {
                throw new IllegalArgumentException("Tipo non consentito.");
            }
        }
        if (removed)
            System.out.println(String.format("Rimozione riuscita, risultato:\n%s", container));
        else
            System.out.println("Elemento non presente, rimozione fallita\n");

    }

    static void removeIterator (MyContainer<?> container, IterableTester.ParameterType parameter) throws IllegalArgumentException {

        boolean removed = false;

        switch (parameter) {
            case NUMBER: {
                System.out.println("* Prova di rimozione di più elementi presenti nel container del tipo:");
                MyContainer<Number> otherContainer = new MyPriorityQueue<>(RandomObjectFactory.getRandomNumber(), RandomObjectFactory.getRandomNumber(), RandomObjectFactory.getRandomNumber());
                System.out.println(otherContainer);
                removed = ((MyContainer<Number>) container).removeAll(otherContainer);
                break;
            }
            case INTEGER: {
                System.out.println("* Prova di rimozione di più elementi presenti nel container del tipo:");
                MyContainer<Integer> otherContainer = new MyPureSet<>(RandomObjectFactory.getRandomInteger(), RandomObjectFactory.getRandomInteger(), RandomObjectFactory.getRandomInteger());
                System.out.println(otherContainer);
                removed = ((MyContainer<Integer>) container).removeAll(otherContainer);
                break;
            }
            case DOUBLE: {
                System.out.println("* Prova di rimozione di più elementi presenti nel container del tipo:");
                MyContainer<Double> otherContainer = new MyCircularNodeList<>(RandomObjectFactory.getRandomDouble(), RandomObjectFactory.getRandomDouble(), RandomObjectFactory.getRandomDouble());
                System.out.println(otherContainer);
                removed = ((MyContainer<Double>) container).removeAll(otherContainer);
                break;
            }
            case CHARACTER: {
                System.out.println("* Prova di rimozione di più elementi presenti nel container del tipo:");
                MyContainer<Character> otherContainer = new MyLIFOStack<>(RandomObjectFactory.getRandomCharacter(), RandomObjectFactory.getRandomCharacter(), RandomObjectFactory.getRandomCharacter());
                System.out.println(otherContainer);
                removed = ((MyContainer<Character>) container).removeAll(otherContainer);
                break;
            }
            case MAPENTRY: {
                System.out.println("* Prova di rimozione di più elementi presenti nel container del tipo:");
                MyContainer<MyMapEntry<? extends Integer, ? extends Character>> otherContainer =
                        new MyPureMap<>(RandomObjectFactory.getRandomEntry(), RandomObjectFactory.getRandomEntry(), RandomObjectFactory.getRandomEntry());
                System.out.println(otherContainer);
                removed = ((MyContainer<MyMapEntry>) container).removeAll(otherContainer);
                break;
            }
            case OBJECT: {
                System.out.println("* Prova di rimozione di più elementi presenti nel container del tipo:");
                MyContainer<Object> otherContainer = new MyArrayList<>(RandomObjectFactory.getRandomObject(), RandomObjectFactory.getRandomObject(), RandomObjectFactory.getRandomObject());
                System.out.println(otherContainer);
                removed = ((MyContainer<Object>) container).removeAll(otherContainer);
                break;
            }

            default: {
                throw new IllegalArgumentException("Tipo non consentito.");
            }
        }
        if (removed)
            System.out.println(String.format("Rimozione riuscita, risultato:\n%s", container));
        else
            System.out.println("Elemento non presente, rimozione fallita\n");
    }
}
