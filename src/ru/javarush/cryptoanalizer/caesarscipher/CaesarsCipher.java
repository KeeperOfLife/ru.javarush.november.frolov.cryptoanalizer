package ru.javarush.cryptoanalizer.caesarscipher;

import ru.javarush.cryptoanalizer.dialoginterface.Message;


import java.util.Arrays;
import java.util.List;


public interface CaesarsCipher {
     List<Character> RU_ALPHABET = Arrays.asList('а', 'б', 'в', 'г', 'д', 'е',
            'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц',
            'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З',
            'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
            'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '(', '.', ',', '"', ':', '-', '!', '&', ')');

    /*
     В разработке/in development
     List<Character> ENG_ALPHABET = new ArrayList<>();
     */
    List<Character> encrypt(List<Character> origin, List<Character> alphabet, Message lang);
    List<Character> decrypt(List<Character> cipherText, List<Character> alphabet, Message lang);
    void bruteForce(List<Character> cipherText, List<Character> alphabet, Message lang);
}
