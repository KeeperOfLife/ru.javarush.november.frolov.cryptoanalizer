package ru.javarush.cryptoanalizer.caesarscipher;

import ru.javarush.cryptoanalizer.dialoginterface.Message;
import ru.javarush.cryptoanalizer.utils.Calculations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cipher implements CaesarsCipher {

    public List<Character> encrypt(List<Character> origin, List<Character> alphabet, Message lang) {
        List<Character> cipherChar = new ArrayList<>();
        Calculations calculations = new Calculations();
        int key = 0;

        lang.enterKeyMess();
        while (true) {
            Scanner console = new Scanner(System.in);
            if (console.hasNextInt()) {
                key = console.nextInt();
                break;
            } else if (console.toString().equalsIgnoreCase("EXIT")) {
                break;
            } else {
                lang.keyWarningMess();
            }
        }

        int shift = calculations.keyCalculation(key, alphabet);
        for (char c : origin) {
            if (alphabet.contains(c)) {
                int match = calculations.offsetCalculationCipher(alphabet.indexOf(c) + shift, alphabet);
                c = (alphabet.get(match));
                cipherChar.add(c);
            } else {
                cipherChar.add(c);
            }
        }
        return cipherChar;
    }

    public List<Character> decrypt(List<Character> cipherText, List<Character> alphabet, Message lang) {
        List<Character> deCipherChar = new ArrayList<>();
        Calculations calculations = new Calculations();
        int key = 0;

        lang.enterKeyMess();
        while (true) {
            Scanner console = new Scanner(System.in);
            if (console.hasNextInt()) {
                key = console.nextInt();
                break;
            } else if (console.toString().equalsIgnoreCase("EXIT")) {
                break;
            } else {
                lang.keyWarningMess();
            }
        }

        int shift = calculations.keyCalculation(key, alphabet);

        for (char c : cipherText) {
            if (alphabet.contains(c)) {
                int match = calculations.offsetCalculationCipher(alphabet.indexOf(c) - shift, alphabet);
                c = (alphabet.get(match));
                deCipherChar.add(c);
            } else {
                deCipherChar.add(c);
            }
        }
        return deCipherChar;
    }

    public void bruteForce(List<Character> cipherText,List<Character> alphabet, Message lang) {
        Calculations calculations = new Calculations();

        for (int key = 1; key <= alphabet.size(); key++) {
            StringBuilder builder = new StringBuilder();
            int shift = calculations.keyCalculation(key, alphabet);
            for (char c : cipherText) {
                if (alphabet.contains(c)) {
                    int match = calculations.offsetCalculationCipher(alphabet.indexOf(c) - shift,alphabet);
                    c = (alphabet.get(match));
                    builder.append(c);
                } else {
                    builder.append(c);
                }
            }
            if (builder.toString().contains(" в ") && builder.toString().contains(" и ") && builder.lastIndexOf(".") != -1) {
                lang.matchMess(shift, builder);
                System.out.println(Message.LINE);
            }
        }
    }
}
