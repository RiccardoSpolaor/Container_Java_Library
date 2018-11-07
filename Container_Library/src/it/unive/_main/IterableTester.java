package it.unive._main;

import it.unive.MyContainer;

import java.util.Scanner;

class IterableTester {

    private static final String smallSeparator = "-----------------------------------------";
    private static final String mediumSeparator = "=========================================";
    private static final String bigSeparator = String.format("%s\n%s", smallSeparator, smallSeparator);

    private static ParameterType parameter = null;
    private static MyContainer<?> container = null;

    private static int numberOfTry = 1;

    public enum ParameterType {
        NUMBER,
        INTEGER,
        DOUBLE,
        CHARACTER,
        MAPENTRY,
        OBJECT
    }

    public static void tester() {
        Scanner input = new Scanner (System.in);
        do {
            userContainerTypeSet();
            authomaticContainerTester();
            parameter = null;
            container = null;
            System.out.println("Scrivere \"s\" per uscire, qualsiasi altra stringa per ripetere");
        } while (!input.next().equals("s"));
    }

    private static void userContainerTypeSet() {

        Scanner input = new Scanner(System.in);
        int chosenContainer;
        int chosenParameter;

        System.out.println("Scegliere il tipo di container");
        System.out.println("\t1) ArrayList");
        System.out.println("\t2) NodeList");
        System.out.println("\t3) CircularNodeList");
        System.out.println("\t4) Set");
        System.out.println("\t5) SortedSet");
        System.out.println("\t6) LIFOStack");
        System.out.println("\t7) FIFOQueue");
        System.out.println("\t8) PriorityQueue");
        System.out.println("\t9) Map");


        System.out.println();

        do {
            System.out.println("Scegliere un numero da 1 a 9");
            try {
                chosenContainer = input.nextInt();
            } catch (Exception exception) {
                chosenContainer = -1;
                input = new Scanner(System.in);
            }
        } while (chosenContainer<1||chosenContainer>9);

        if (chosenContainer!=9) {
            System.out.println("Scegliere il tipo parametrico del container");
            System.out.println("\t1) Number");
            System.out.println("\t2) Integer");
            System.out.println("\t3) Double");
            System.out.println("\t4) Character");
            System.out.println("\t5) MapEntry");
            System.out.println("\t6) Object");


            do {
                System.out.println("Scegliere un numero da 1 a 6");
                try {
                    chosenParameter = input.nextInt();
                } catch (Exception exception) {
                    chosenParameter = -1;
                    input = new Scanner(System.in);
                }
            } while (chosenParameter<1||chosenParameter>6);
        }

        else
            chosenParameter = 5;

        parameter = UserContainerOperations.getParameterByUserChoice(chosenParameter);
        container = UserContainerOperations.getContainerByUserChoice(chosenContainer, parameter);

    }

    private static void authomaticContainerTester() {
        System.out.println(String.format("================PROVA %d:===============\n", numberOfTry++));
        System.out.println(String.format("Il container di partenza è:\n%s",container));

        System.out.println("Verifica di aggiunta del container:\n");
        System.out.println(container);

        UserContainerOperations.addSingle(container, parameter);
        System.out.println(smallSeparator);

        UserContainerOperations.addMultiple(container, parameter);
        System.out.println(smallSeparator);

        UserContainerOperations.addIterator(container, parameter);
        System.out.println(mediumSeparator);

        System.out.println("Verifica di contenuto del container:\n");
        System.out.println(container);

        UserContainerOperations.containsSingle(container, parameter);
        System.out.println(smallSeparator);

        UserContainerOperations.containsMultiple(container, parameter);
        System.out.println(smallSeparator);

        UserContainerOperations.containsIterator(container, parameter);
        System.out.println(mediumSeparator);


        System.out.println("Verifica di rimozione del container:\n");
        System.out.println(container);

        UserContainerOperations.removeSingle(container, parameter);
        System.out.println(smallSeparator);

        UserContainerOperations.removeMultiple(container, parameter);
        System.out.println(smallSeparator);

        UserContainerOperations.removeIterator(container, parameter);
        System.out.println(bigSeparator);
    }

}
