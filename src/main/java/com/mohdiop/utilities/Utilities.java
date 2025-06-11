package com.mohdiop.utilities;

public class Utilities {
    public static String generateIdentifiantNumber() {
        int randomNumber = (int) (Math.random() * (999 - 1 + 1)) + 1;
        int randomNumberLength = String.valueOf(randomNumber).length();
        String idNumber = "";
        switch (randomNumberLength) {
            case 1:
                idNumber = "00" + randomNumber;
            case 2:
                idNumber = "0" + randomNumber;
            case 3:
                idNumber = String.valueOf(randomNumber);
        }
        return idNumber;
    }
}
