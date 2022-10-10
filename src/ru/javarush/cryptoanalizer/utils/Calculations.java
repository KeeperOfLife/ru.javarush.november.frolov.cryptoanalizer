package ru.javarush.cryptoanalizer.utils;

import java.util.List;

public class Calculations {

   public int keyCalculation(int key, List<Character> alphabet){
        if (key > alphabet.size()) {
            key %= alphabet.size();
        }
       return key;
   }

   public int offsetCalculationCipher(int index, List<Character> alphabet){
        int shift = index;
        if (index >= alphabet.size()) {
            shift = index - alphabet.size();
        } else if (index < 0) {
            shift = index + alphabet.size();
        }
        return shift;
    }
}
