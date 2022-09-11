package ru.javarush.cryptoanalizer;


import ru.javarush.cryptoanalizer.utils.CaesarsCipher;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        CaesarsCipher cipher = new CaesarsCipher();
        List<Character> origin = cipher.readFile();
        List<Character> cipherTxt = cipher.cipher(origin);
        cipher.writeFile(cipherTxt);

    }
}




