package ru.javarush.cryptoanalizer.utils;

import ru.javarush.cryptoanalizer.exo.CaesarsCipher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {
    public Runner() {
        System.out.println("Select a language");
        System.out.println(CaesarsCipher.LINE);
        System.out.println("Press 1: English");
        System.out.println("Press 2: Русский");
        System.out.println("Press 3: Exit");

        boolean isExit = true;
        while (isExit) {
            try {
                Scanner console = new Scanner(System.in);
                switch (console.nextInt()) {
                    case 1:
                        System.out.println("English");
                        while (isExit) {
                            try {
                                CaesarsCipher engCipher = new EngCrypt();
                                engCipher.choiceActionMessage();
                                Scanner engConsole = new Scanner(System.in);
                                switch (engConsole.nextInt()) {
                                    case 1: {
                                        try {
                                            List<Character> cipherFile = engCipher.encrypt(engCipher.readFile());
                                            engCipher.writeFile(cipherFile);
                                        }catch (RuntimeException ignored){}
                                    }
                                    break;
                                    case 2: {
                                        try {
                                            List<Character> deCipherFie = engCipher.decrypt(engCipher.readFile());
                                            engCipher.writeFile(deCipherFie);
                                        }catch (RuntimeException ignored){}
                                    }
                                    break;
                                    case 3: {
                                        try {
                                            engCipher.bruteForce(engCipher.readFile());
                                        }catch (RuntimeException ignored){}
                                    }
                                    break;
                                    case 4:
                                        System.out.println("Exit");
                                        isExit = false;
                                        break;
                                    default:
                                        System.err.println("There are no such values");
                                        System.out.println(CaesarsCipher.LINE);
                                }
                            } catch (RuntimeException e) {
                                System.err.println("Incorrectly entered values");
                                System.out.println(CaesarsCipher.LINE);
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Русский");
                        while (isExit) {
                            try {
                                CaesarsCipher ruCipher = new RuCrypt();
                                ruCipher.choiceActionMessage();
                                Scanner ruConsole = new Scanner(System.in);
                                switch (ruConsole.nextInt()) {
                                    case 1: {
                                        try {
                                            List<Character> cipherFile = ruCipher.encrypt(ruCipher.readFile());
                                            ruCipher.writeFile(cipherFile);
                                        }catch (RuntimeException ignored){}
                                    }
                                    break;
                                    case 2: {
                                        try {
                                            List<Character> deCipherFie = ruCipher.decrypt(ruCipher.readFile());
                                            ruCipher.writeFile(deCipherFie);
                                        }catch (RuntimeException ignored){}
                                    }
                                    break;
                                    case 3: {
                                        try {
                                            ruCipher.bruteForce(ruCipher.readFile());
                                        } catch (RuntimeException ignored) {}
                                    }
                                    break;
                                    case 4:
                                        System.out.println("Выход");
                                        isExit = false;
                                        break;
                                    default:
                                        System.err.println("Нет таких значений");
                                        System.out.println(CaesarsCipher.LINE);
                                }
                            } catch (RuntimeException e) {
                                System.err.println("Некорректно введено значение");
                                System.out.println(CaesarsCipher.LINE);
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Exit");
                        isExit = false;
                        break;
                    default:
                        System.err.println("There are no such values");
                }
            } catch (RuntimeException e) {
                System.err.println("Incorrectly entered values");
            }
        }
    }
}