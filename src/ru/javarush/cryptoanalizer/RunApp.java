package ru.javarush.cryptoanalizer;


import ru.javarush.cryptoanalizer.caesarscipher.CaesarsCipher;
import ru.javarush.cryptoanalizer.dialoginterface.Message;
import ru.javarush.cryptoanalizer.dialoginterface.EngMessage;
import ru.javarush.cryptoanalizer.dialoginterface.RuMessage;
import ru.javarush.cryptoanalizer.utils.Start;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RunApp {
    public static void main(String[] args) {
        Message ru = new RuMessage();
        Message eng = new EngMessage();
        Start start = new Start();
        int choice = 0;
        while (choice !=3) {
        Message.startMessage();
            try  {
                Scanner console = new Scanner(System.in);
                choice = console.nextInt();
                if (choice == 1) {
                    start.startCipher(eng, CaesarsCipher.RU_ALPHABET);
                    break;
                } else if (choice == 2) {
                    start.startCipher(ru, CaesarsCipher.RU_ALPHABET);
                    break;
                } else if (choice != 3) {
                    eng.noSuchValuesMess();
                    System.out.println(Message.LINE);
                }
            } catch (InputMismatchException e) {
                eng.enterNumber();
                System.out.println(Message.LINE);
            }
        }
    }
}
