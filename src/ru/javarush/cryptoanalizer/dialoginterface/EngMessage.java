package ru.javarush.cryptoanalizer.dialoginterface;

import java.nio.file.Path;

public class EngMessage implements Message {
    @Override
    public void choiceActionMess() {
        System.out.println("Select an action:");
        System.out.println();
        System.out.println("Press 1 to encrypt the text file: ");
        System.out.println("Press 2 to decrypt the text file: ");
        System.out.println("Press 3 to crack an encrypted file without a key: ");
        System.out.println("Press 4 to exit the app: ");
    }

    @Override
    public void enterKeyMess() {
        System.out.println("Enter the key to encrypt\\decrypt the file or enter \"EXIT\" to return to the previous menu: ");
    }

    @Override
    public void keyWarningMess() {
        System.err.println("The key is entered incorrectly, try again: ");
    }

    @Override
    public void enterPathMess() {
        System.out.println("Enter the path to the text file or type \\\"EXIT\\\" to return to the previous menu: ");
    }

    @Override
    public void pathWarningMess() {
        System.err.println("The path to the file is not entered correctly or there is no such file, try again: ");
    }

    @Override
    public void createFileMess(Path src) {
        System.out.println("File created: " + src.toString());
    }

    @Override
    public void matchMess(int shift, StringBuilder builder) {
        System.out.println("Possible match: " + "key: " + "" + shift + " " + builder.substring(0, 50));
    }

    @Override
    public void noSuchValuesMess() {
        System.err.println("No such values: ");
    }

    @Override
    public void exitMess() {
        System.out.println("Exit: ");
    }

    @Override
    public void pathNotFound() {
        System.err.println("Path not found: ");
    }

    @Override
    public void enterNumber() {
        System.err.println("enter a number");
    }
}
