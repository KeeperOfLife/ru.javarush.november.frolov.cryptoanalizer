package ru.javarush.cryptoanalizer.utils;

import ru.javarush.cryptoanalizer.caesarscipher.Cipher;
import ru.javarush.cryptoanalizer.dialoginterface.Message;
import ru.javarush.cryptoanalizer.caesarscipher.CaesarsCipher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Start {
    private CaesarsCipher cipher = new Cipher();
    private List<Character> text = new ArrayList<>();
    private ReadWriteFile rw = new ReadWriteFile();
    private boolean isExit = true;

    public void startCipher(Message lang, List<Character> alphabet) {
        while (isExit) {
            try {
                lang.choiceActionMess();
                int console= new Scanner(System.in).nextInt();
                switch (console ) {
                    case 1:
                        text = rw.read(lang);
                        if (!text.isEmpty()) {
                            text = cipher.encrypt(text, alphabet, lang);
                            rw.write(text, lang);
                        }
                        break;
                    case 2:
                        text = rw.read(lang);
                        if (!text.isEmpty()) {
                            text = cipher.decrypt(text, alphabet, lang);
                            rw.write(text, lang);
                        }
                        break;
                    case 3:
                        text = rw.read(lang);
                        if (!text.isEmpty()) {
                            cipher.bruteForce(text, alphabet, lang);
                            cipher.decrypt(text, alphabet, lang);
                            rw.write(text, lang);
                        }
                        break;
                    case 4:
                        lang.exitMess();
                        isExit = false;
                        break;
                    default:
                        lang.noSuchValuesMess();
                        System.out.println(Message.LINE);
                }
            } catch (RuntimeException e) {
                lang.enterNumber();
                System.out.println(Message.LINE);
            }
        }
    }
}
