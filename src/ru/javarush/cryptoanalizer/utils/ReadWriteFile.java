package ru.javarush.cryptoanalizer.utils;

import ru.javarush.cryptoanalizer.dialoginterface.Message;

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


public class ReadWriteFile {
    Path src;

    List<Character> read(Message lang) {
        List<Character> originalText = new ArrayList<>();
        File filePath;
        lang.enterPathMess();

        while (true) {
            Scanner console = new Scanner(System.in);
            src = Path.of(console.nextLine().trim());
            filePath = src.toFile();
            if (filePath.exists()) {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
                    while (bufferedReader.ready()) {
                        originalText.add((char) bufferedReader.read());
                    }
                    break;
                } catch (IOException e) {
                    lang.pathWarningMess();
                    System.out.println(Message.LINE);
                }
            } else if (filePath.toString().equalsIgnoreCase("EXIT")) {
                lang.exitMess();
                System.out.println(Message.LINE);
                break;
            } else {
                lang.pathWarningMess();
                System.out.println(Message.LINE);
            }
        }
        return originalText;
    }

    void write(List<Character> fileToWrite, Message lang) {
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
            lang.createFileMess(src);
            System.out.println(Message.LINE);
        } catch (IOException e) {
            lang.pathNotFound();
        }
    }

    /*
    Метод меняет исходное имя файла на такойже с порядковым номер в конце (file1.txt -> file2.txt)
    Если цифры в имени файла нет, то в конце имени ставится 1 (file.txt -> file1.txt)
    =====================================================================================
    The method changes the original file name to the same with the sequence number at the end (file1.txt -> file2.txt )
    If there is no digit in the file name, then put 1 at the end of the name (file.txt -> file1.txt )
     */
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

    /*
     Метод ищет цифру в имени файла (file1.txt, file2.txt) и возвращает ее
     =====================================================================================
     The method looks for a digit in the file name (file1.txt , file2.txt ) and returns it
     */
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
