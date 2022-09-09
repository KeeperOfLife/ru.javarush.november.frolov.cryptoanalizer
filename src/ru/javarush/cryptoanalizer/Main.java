package ru.javarush.cryptoanalizer;

import ru.javarush.cryptoanalizer.utils.Cipher;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Cipher cipher = new Cipher();
        List<Character> origin = cipher.readFile();
        List<Character> cipherTxt = cipher.cipher(origin);


        //   MainDialog.greeting();
        //   Scanner console = new Scanner(System.in);
        //   int x = console.nextInt();
        //   boolean isExit = false;
        //
        //   while (!isExit) {
        //       if (x == 5) {
        //           isExit = true;
        //       } else if (x == 1) {
        //
        //       } else if (x == 2) {
        //           RuDialog ruDialog = new RuDialog();
        //           ruDialog.greetingMessage();
        //           ruDialog.choiceActionMessage();
        //           x = console.nextInt();
        //           if(x == 1){
        //               ruDialog.pathMessage();
        //
        //           }
        //       } else if (x == 3) {
        //
        //       } else if (x == 4) {
        //
        //       }
        //   }
    }
}

