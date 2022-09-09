package ru.javarush.cryptoanalizer.dialog;


public class RuDialog {

    public static void greetingMessage(){
        System.out.println("Добро пожаловать");
        System.out.println();
    }

    public static void choiceActionMessage(){
        System.out.println("Выберите действие:");
        System.out.println();
        System.out.println("Нажмите 1 для шифрования текстового файла");
        System.out.println("Нажмите 2 для расшифрки текстового файла");
        System.out.println("Нажмите 3 для взлома зашифрованного файла без ключ");
        System.out.println("Нажмите 4 чтобы вернуться в предыдущее меню");
        System.out.println("Нажмите 5 чтобы выйти из приложения");
    }

    public static void pathMessage(){
        System.out.println("Введите путь к текстовому файлу:");
    }

    public static void keyMessage(){
        System.out.println("Введите ключ (число на которое сдвинуть символы):");
    }
    public static void pathInvalid(){
        System.out.println("Не корректно введен путь к файлу, попробуйтек снвоа:");
    }


}

