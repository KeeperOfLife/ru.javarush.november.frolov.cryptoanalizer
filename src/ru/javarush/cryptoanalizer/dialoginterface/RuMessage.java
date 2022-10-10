package ru.javarush.cryptoanalizer.dialoginterface;

import java.nio.file.Path;

public class RuMessage implements Message {

    @Override
    public void choiceActionMess() {
        System.out.println("Выберите действие:");
        System.out.println();
        System.out.println("Нажмите 1 для шифрования текстового файла: ");
        System.out.println("Нажмите 2 для расшифровки текстового файла: ");
        System.out.println("Нажмите 3 для взлома зашифрованного файла без ключа: ");
        System.out.println("Нажмите 4 чтобы выйти из приложения: ");
    }

    @Override
    public void enterKeyMess() {
        System.out.println("Введите ключ для зашифровки\\расшифровки файла или введите \"EXIT\" для возврата в предыдущее меню: ");
    }

    @Override
    public void keyWarningMess() {
        System.err.println("Некорректно введен ключ, попробуйте снова: ");
    }

    @Override
    public void enterPathMess() {
        System.out.println("Введите путь к текстовому файлу или введите \"EXIT\" для возврата в предыдущее меню: ");
    }

    @Override
    public void pathWarningMess() {
        System.err.println("Не корректно введен путь к файлу или такого файла не существует, попробуйте снова: ");
    }

    @Override
    public void createFileMess(Path src) {
        System.out.println("Файл создан: " + src.toString());
    }

    @Override
    public void matchMess(int shift, StringBuilder builder) {
        System.out.println("Возможное совпадение: " + "ключ: " + "" + shift + " " + builder.substring(0, 50) + ".....");
    }

    @Override
    public void noSuchValuesMess() {
        System.err.println("Нет таких значений: ");
    }

    @Override
    public void exitMess() {
        System.out.println("Выход: ");
    }

    @Override
    public void pathNotFound() {
        System.err.println("Путь не найден: ");
    }

    @Override
    public void enterNumber() {
        System.err.println("Введите число");
    }
}
