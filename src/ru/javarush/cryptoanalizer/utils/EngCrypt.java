package ru.javarush.cryptoanalizer.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EngCrypt extends RuCrypt {
    @Override
    public void choiceActionMessage() {
        System.out.println("Select an action:");
        System.out.println();
        System.out.println("Press 1 to encrypt the text file");
        System.out.println("Press 2 to decrypt the text file");
        System.out.println("Press 3 to crack an encrypted file without a key");
        System.out.println("Press 4 to exit the app");
    }

    @Override
    public List<Character> encrypt(List<Character> origin) {
        List<Character> cipherChar = new ArrayList<>();
        int key = 0;

        System.out.println("Enter the key (the number by which to shift the characters):");
        while (true) {
            Scanner console = new Scanner(System.in);
            if (console.hasNextInt()) {
                key = console.nextInt();
                break;
            } else {
                System.err.println("The key is entered incorrectly, try again");
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

    @Override
    public List<Character> decrypt(List<Character> cipherText) {
        List<Character> deCipherChar = new ArrayList<>();
        int key = 0;

        System.out.println("Enter the key to decrypt the file or enter \"EXIT\" to return to the previous menu:");
        while (true) {
            Scanner console = new Scanner(System.in);
            if (console.hasNextInt()) {
                key = console.nextInt();
                break;
            } else if (console.toString().equals("EXIT")) {
                break;
            } else {
                System.err.println("The key is entered incorrectly, try again");
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

    @Override
    public void bruteForce(List<Character> cipherText) {
        for (int key = 0; key <= RU_ALPHABET.size(); key++) {
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
                System.out.println("Possible match: " + "key: " + "" + shift + " " + builder.substring(0, 50));
                System.out.println(LINE);
            }
        }

        writeFile(decrypt(cipherText));
    }

    @Override
    public List<Character> readFile() {
        List<Character> originalText = new ArrayList<>();
        File filePath;

        System.out.println("Enter the key to decrypt the file or enter \"EXIT\" to return to the previous menu:");

        while (true) {
            Scanner console = new Scanner(System.in);
            src = Path.of(console.nextLine().trim());
            filePath = src.toFile();
            if (filePath.exists()) {
                break;
            } else if (src.toString().equals("EXIT")) {
                break;
            } else {
                System.err.println("The path to the file is not entered correctly or there is no such file, try again:");
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

    @Override
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

            System.out.println("File created: " + src.toString());
            System.out.println(LINE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
