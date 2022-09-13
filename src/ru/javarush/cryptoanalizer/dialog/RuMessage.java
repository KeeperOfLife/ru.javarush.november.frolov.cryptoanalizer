package ru.javarush.cryptoanalizer.dialog;


public class RuMessage {

    static void greetingMessage() {
        System.out.println("Добро пожаловать");
        System.out.println();
    }

    public static void choiceActionMessage() {
        System.out.println("Выберите действие:");
        System.out.println();
        System.out.println("Нажмите 1 для шифрования текстового файла");
        System.out.println("Нажмите 2 для расшифрки текстового файла");
        System.out.println("Нажмите 3 для взлома зашифрованного файла без ключ");
      //  System.out.println("Нажмите 4 чтобы вернуться в предыдущее меню");
        System.out.println("Нажмите 4 чтобы выйти из приложения");
    }

    public static void WhereFromFileMessage() {
        System.out.println("Введите путь к текстовому файлу:");
    }


    public static void whereFileMessage() {
        System.out.println("Введите путь для сохранения файла");
    }

    public static void pathInvalidMessage() {
        System.out.println("Не корректно введен путь к файлу, попробуйте снова:");
    }

    public static void FileNotFoundMessage() {
        System.out.println("Файл не найден");
    }

    public static void keyMessage() {
        System.out.println("Введите ключ (число на которое сдвинуть символы):");
    }

    public static void startCipherMeassage() {
        System.out.println("Начало зашифровки");
    }

    public static void endCipherMessage() {
        System.out.println("Шифврока завеошилась");
    }

}

