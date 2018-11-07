package it.unive._main;

import it.unive.structs.MyMapEntry;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomObjectFactory {
    static Number getRandomNumber() {
        Random random = new Random();
        int numberType = random.nextInt(3);
        if (numberType==0)
            return random.nextInt(20);
        else if (numberType == 1) {
            return getRandomDouble();
        }
        else {
            return getRandomFloat();
        }
    }

    static Integer getRandomInteger() {
        return new Random().nextInt(20);
    }

    static Double getRandomDouble() {
        Random random = new Random();
        DecimalFormat format = new DecimalFormat("#,#");
        String stringToChange = format.format(random.nextDouble() * 20);
        return Double.valueOf(stringToChange);
    }

    private static Float getRandomFloat () {
        Random random = new Random();
        DecimalFormat format = new DecimalFormat("#,#");
        String stringToChange = format.format(random.nextDouble() * 20);
        return Float.parseFloat(stringToChange);
    }

    static Character getRandomCharacter() {
        Random random = new Random();
        int charOffset = random.nextInt(24);
        return (char)('a' + charOffset);
    }

    static Object getRandomObject() {
        Random random = new Random();
        int numberType = random.nextInt(3);
        if (numberType==0)
            return getRandomNumber();
        else if (numberType==1){
            return getRandomCharacter();
        }
        else
            return getRandomEntry();
    }

    static MyMapEntry<? extends Integer, ? extends Character> getRandomEntry() {
        Random random = new Random();
        Integer key = random.nextInt(20);
        Character element = getRandomCharacter();
        return new MyMapEntry<>(key,element);
    }
}
