package ru.javarush.cryptoanalizer.utils;

import ru.javarush.cryptoanalizer.exo.CaesarsCipher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RuCrypt implements CaesarsCipher {

    Path src;

    final List<Character> RU_ALPHABET = Arrays.asList('а', 'б', 'в', 'г', 'д', 'е',
            'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц',
            'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З',
            'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
            'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '(', '.', ',', '"', ':', '-', '!', '&', ')');


    @Override
    public void choiceActionMessage() {
        System.out.println("Выберите действие:");
        System.out.println();
        System.out.println("Нажмите 1 для шифрования текстового файла");
        System.out.println("Нажмите 2 для расшифровки текстового файла");
        System.out.println("Нажмите 3 для взлома зашифрованного файла без ключа");
        System.out.println("Нажмите 4 чтобы выйти из приложения");
    }

    public List<Character> encrypt(List<Character> origin) {
        List<Character> cipherChar = new ArrayList<>();
        int key = 0;


        System.out.println("Введите ключ (число на которое сдвинуть символы):");

        while (true) {
            Scanner console = new Scanner(System.in);
            if (console.hasNextInt()) {
                key = console.nextInt();
                break;
            } else {
                System.err.println("Некорректно введен ключ, попробуйте снова");
            }
        }

        int shift = keyCalculation(key);

        for (char c : origin) {
            if (RU_ALPHABET.contains(c)) {
                c = (RU_ALPHABET.get(offsetCalculationCipher(RU_ALPHABET.indexOf(c) + shift)));
                cipherChar.add(c);
            } else {
                cipherChar.add(c);
            }

        }
        return cipherChar;
    }

    public List<Character> decrypt(List<Character> cipherText) {
        List<Character> deCipherChar = new ArrayList<>();
        int key = 0;

        System.out.println("Введите ключ для расшифровки файла или введите \"EXIT\" для возврата в предыдущее меню");
        while (true) {
            Scanner console = new Scanner(System.in);
            if (console.hasNextInt()) {
                key = console.nextInt();
                break;
            } else if (console.toString().equals("EXIT")) {
                System.out.println("Возврат");
                break;
            } else {
                System.err.println("Некорректно введен ключ, попробуйте снова");
            }
        }

        int shift = keyCalculation(key);

        for (char c : cipherText) {
            if (RU_ALPHABET.contains(c)) {
                c = (RU_ALPHABET.get(offsetCalculationCipher(RU_ALPHABET.indexOf(c) - shift)));
                deCipherChar.add(c);
            } else {
                deCipherChar.add(c);
            }
        }
        return deCipherChar;
    }

    public void bruteForce(List<Character> cipherText) {
        for (int key = 1; key <= RU_ALPHABET.size(); key++) {
            StringBuilder builder = new StringBuilder();
            int shift = keyCalculation(key);
            for (char c : cipherText) {
                if (RU_ALPHABET.contains(c)) {
                    c = (RU_ALPHABET.get(offsetCalculationCipher(RU_ALPHABET.indexOf(c) - shift)));
                    builder.append(c);
                } else {
                    builder.append(c);
                }
            }

            if (builder.toString().contains(" в ") && builder.toString().contains(" и ") && builder.lastIndexOf(".") != -1) {
                System.out.println("Возможное совпадение: " + "ключ: " + "" + shift + " " + builder.substring(0, 50) + ".....");
                System.out.println(LINE);
            }
        }

        writeFile(decrypt(cipherText));
    }


    public int keyCalculation(int key) {
        if (key > RU_ALPHABET.size()) {
            return key %= RU_ALPHABET.size();
        } else {
            return key;
        }
    }

    public int offsetCalculationCipher(int index) {
        int shift = index;
        if (index >= RU_ALPHABET.size()) {
            shift = index - RU_ALPHABET.size();
        } else if (index < 0) {
            shift = index + RU_ALPHABET.size();
        }
        return shift;
    }

    public List<Character> readFile() {
        List<Character> originalText = new ArrayList<>();
        File filePath;

        System.out.println("Введите путь к текстовому файлу или введите \"EXIT\" для возврата в предыдущее меню:");

        while (true) {
            Scanner console = new Scanner(System.in);
            src = Path.of(console.nextLine().trim());
            filePath = src.toFile();
            if (filePath.exists()) {
                break;
            } else if (src.toString().equals("EXIT")) {
                break;
            } else {
                System.err.println("Не корректно введен путь к файлу или такого файла не существует, попробуйте снова:");
            }
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {

            while (bufferedReader.ready()) {
                originalText.add((char) bufferedReader.read());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return originalText;
    }

    public void writeFile(List<Character> fileToWrite) {
        StringBuilder text = new StringBuilder();

        for (char c : fileToWrite) {
            text.append(c);
        }

        int count = searchDigit(src.toString());
        try {
            while (Files.exists(src)) {
                src = Path.of(getNewFileName(src.toString(), count));
                count++;
            }
            Files.createFile(src);
            Files.write(src, text.toString().getBytes());

            System.out.println("Файл создан: " + src.toString());
            System.out.println(LINE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    String getNewFileName(String oldFileName, int number) {
        int x = oldFileName.lastIndexOf("\\");
        int dotIndex = oldFileName.lastIndexOf(".");
        if (number == 0) {
            number += 1;
            return oldFileName.substring(0, dotIndex) + number + oldFileName.substring(dotIndex);
        } else {
            String str1 = oldFileName.substring(x, dotIndex).replaceAll("[0-9]+", String.valueOf(number));
            return oldFileName.substring(0, x) + str1 + oldFileName.substring(dotIndex);

        }
    }

    int searchDigit(String src) {
        StringBuilder digit = new StringBuilder();
        int result = 0;
        char[] ch = src.toCharArray();
        for (char c : ch) {
            if (Character.isDigit(c)) {
                digit.append(c);
                result = Integer.parseInt(String.valueOf(digit));
            }
        }
        return result;
    }
}
