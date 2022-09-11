package ru.javarush.cryptoanalizer.utils;

import ru.javarush.cryptoanalizer.dialog.RuMessage;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CaesarsCipher {
    private static final List<Character> RU_ALPHABET = Arrays.asList('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н',
            'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
            'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н',
            'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', '(', '.', ',', '"', ':', '-', '!', '&', ')');

    private Path src;

    public List<Character> readFile() {
        List<Character> originalText = new ArrayList<>();
        File filePath;

        RuMessage.WhereFromFileMessage();

        while (true) {
            Scanner console = new Scanner(System.in);
            src = Path.of(console.nextLine());
            filePath = src.toFile();
            if (filePath.exists()) {
                break;
            } else {
                RuMessage.pathInvalidMessage();
            }
        }


        try (Reader readFile = new FileReader(filePath, StandardCharsets.UTF_8);
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

        RuMessage.keyMessage();
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
        return cipherChar;
    }

    public List<Character> deCipher(List<Character> cipherText) {
        List<Character> deCipherChar = new ArrayList<>();

        RuMessage.keyMessage();
        int key = new Scanner(System.in).nextInt();
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
        } else if (index < 0) {
            shift = index + RU_ALPHABET.size();
        }
        return shift;
    }

    public void writeFile(List<Character> fileToWrite) {
        Scanner console = new Scanner(System.in);
        StringBuilder text = new StringBuilder();

        for (char c : fileToWrite) {
            text.append(c);
        }
        String s = text.toString();

        int count = searchDigit(src.toString());


        try {
            while (Files.exists(src)) {
                src = Path.of(getNewFileName(src.toString(), count));
                count++;
            }
            Files.createFile(src);

        } catch (
                IOException e) {
            throw new RuntimeException();
        }

        try (
                RandomAccessFile aFile = new RandomAccessFile(src.toFile(), "rw");
                FileChannel out = aFile.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(s.getBytes().length);
            buffer.put(s.getBytes());
            buffer.flip();
            out.write(buffer);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    private static String getNewFileName(String oldFileName, int number) {
        String str = String.valueOf(number);
        int x = oldFileName.lastIndexOf("\\");
        int dotIndex = oldFileName.lastIndexOf(".");
        String str1 = oldFileName.substring(x, dotIndex).replaceAll("\\d", str);
        return oldFileName.substring(0, x) + str1 + oldFileName.substring(dotIndex);

    }

    private int searchDigit(String src) {
        int result = 0;
        char[] ch = src.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (Character.isDigit(ch[i])) {
                result = Character.getNumericValue(ch[i]) + 1;
            }
        }
        return result;
    }
}

