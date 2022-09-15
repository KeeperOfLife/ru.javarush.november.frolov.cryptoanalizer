package ru.javarush.cryptoanalizer.exo;

import java.util.List;


public interface CaesarsCipher {
   String LINE = "======================";

     void choiceActionMessage();

    List<Character> encrypt(List<Character> origin);

    List<Character> decrypt(List<Character> cipherText);

    void bruteForce(List<Character> cipherText);

    int keyCalculation(int key);

    int offsetCalculationCipher(int index);

    List<Character> readFile();

    void writeFile(List<Character> fileToWrite);
}
