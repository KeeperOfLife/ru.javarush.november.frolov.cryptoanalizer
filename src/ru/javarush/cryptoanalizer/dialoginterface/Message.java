package ru.javarush.cryptoanalizer.dialoginterface;

import java.nio.file.Path;

public interface Message {
    String LINE = "======================";

    static void startMessage() {
        System.out.println("Select a language");
        System.out.println();
        System.out.println("Press 1: English");
        System.out.println("Press 2: Русский");
        System.out.println("Press 3: Exit");
    }

    void choiceActionMess();
    void enterKeyMess();
    void keyWarningMess();
    void enterPathMess();
    void pathWarningMess();
    void createFileMess(Path src);
    void matchMess(int shift, StringBuilder builder);
    void noSuchValuesMess();
    void exitMess();
    void pathNotFound();
    void enterNumber();
}
