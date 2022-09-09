package ru.javarush.cryptoanalizer.utils;

import ru.javarush.cryptoanalizer.dialog.MainDialog;
import ru.javarush.cryptoanalizer.dialog.RuDialog;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Cipher {
    private static final List<Character> RU_ALPHABET = Arrays.asList('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н',
            'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н',
            'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '(', '.', ',', '"', ':', '-', '!', '&', ')');



    public List<Character> readFile() {
        List<Character> originalText = new ArrayList<>();
        File filePath;

        RuDialog.pathMessage();

        while (true) {
            Scanner console = new Scanner(System.in);
            Path path = Path.of(console.nextLine());
            filePath = path.toFile();
            if (filePath.exists()) {
                break;
            } else {
                RuDialog.pathInvalid();
            }
        }


        try (Reader readFile = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(readFile)) {

            while (bufferedReader.ready()) {
                StringBuilder builder = new StringBuilder(bufferedReader.readLine());
                for (char c : builder.substring(0).toCharArray()) {
                    originalText.add(c);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return originalText;
    }

    public List<Character> cipher(List<Character> origin) {
        List<Character> cipherChar = new ArrayList<>();
        RuDialog.keyMessage();
        int key = new Scanner(System.in).nextInt();
        int shift = keyCalculation(key);
        for (char c : origin) {
            if (RU_ALPHABET.contains(c)) {
                c = (RU_ALPHABET.get(offsetCalculationCipher(RU_ALPHABET.indexOf(c) + shift)));
                cipherChar.add(c);
            } else {
                cipherChar.add(c);
            }
        }
        System.out.println(cipherChar);
        return cipherChar;
    }

    private int keyCalculation(int key) {
        if (key > RU_ALPHABET.size()) {
            return key %= RU_ALPHABET.size();
        } else {
            return key;
        }
    }

    private int offsetCalculationCipher(int index) {
        int shift = index;
        if (index >= RU_ALPHABET.size()) {
            shift = index - RU_ALPHABET.size();
        }
        return shift;
    }

    private int offsetCalculationDeCipher(int index) {
        int shift = index;
        if (index < 0) {
            shift = index + RU_ALPHABET.size();
        }
        return shift;
    }
}

