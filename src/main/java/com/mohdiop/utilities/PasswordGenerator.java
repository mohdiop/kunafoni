package com.mohdiop.utilities;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {

    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_-+=[{}]|;:\",<.>/?";

    public static String generateRandomPassword() {
        int length = 8;
        StringBuilder password = new StringBuilder();
        Random random = new SecureRandom();

        password.append(getRandomCharacter(UPPER_CASE, random));
        password.append(getRandomCharacter(LOWER_CASE, random));
        password.append(getRandomCharacter(NUMBERS, random));
        password.append(getRandomCharacter(SPECIAL_CHARACTERS, random));
        String allChars = "";
        allChars += UPPER_CASE;
        allChars += LOWER_CASE;
        allChars += NUMBERS;
        allChars += SPECIAL_CHARACTERS;

        while (password.length() < length) {
            password.append(getRandomCharacter(allChars, random));
        }
        return shuffleString(password.toString(), random);
    }

    private static char getRandomCharacter(String characters, Random random) {
        return characters.charAt(random.nextInt(characters.length()));
    }

    private static String shuffleString(String input, Random random) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }
        return new String(characters);
    }
}