package ru.javarush.cryptoanalizer.dialog;

import ru.javarush.cryptoanalizer.utils.CaesarsCipher;

import java.util.List;
import java.util.Scanner;

public class MainDialog {
    public static void greeting() {
        System.out.println("Select a language");
        System.out.println("===============");
        System.out.println("Press 1: English");
        System.out.println("Press 2: Русский");
        System.out.println("Press 3: Український");
        System.out.println("Press 4: Беларускі");
        System.out.println("Press 5: Exit");

        boolean isExit = true;
        while (isExit) {
            try {
                Scanner console = new Scanner(System.in);
                switch (console.nextInt()) {
                    case 1:
                        System.out.println("English");
                        isExit = false;
                        break;
                    case 2:
                        System.out.println("Русский");
                        while (isExit) {
                            RuMessage.choiceActionMessage();
                            switch (console.nextInt()) {
                                case 1: {
                                    CaesarsCipher caesarsCipher = new CaesarsCipher();
                                    List<Character> cipherFile = caesarsCipher.cipher(caesarsCipher.readFile());
                                    caesarsCipher.writeFile(cipherFile);
                                }
                                break;
                                case 2: {
                                    CaesarsCipher caesarsCipher = new CaesarsCipher();
                                    List<Character> deCipherFie = caesarsCipher.deCipher(caesarsCipher.readFile());
                                    caesarsCipher.writeFile(deCipherFie);
                                }
                                break;
                                case 3: {
                                    CaesarsCipher caesarsCipher = new CaesarsCipher();
                                    caesarsCipher.bruteForce(caesarsCipher.readFile());
                                }
                                break;
                                case 4:
                                    System.out.println("Выход");
                                    isExit = false;
                                    break;
                            }
                        }
                        isExit = false;
                        break;
                    case 3:
                        System.out.println("Український");
                        isExit = false;
                        break;
                    case 4:
                        System.out.println("Беларускі");
                        isExit = false;
                        break;
                    case 5:
                        System.out.println("Exit");
                        isExit = false;
                        break;
                    default:
                        System.out.println("Нет таких значений");
                }
            } catch (RuntimeException e) {
                System.out.println("Некорректно введено значение");
            }
        }
    }
}
