package com.ensa.gi4.utils;

import java.util.Scanner;

public class CustomScannerUtil {

    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static int scanForInt() {
        int returnValue = scanner.nextInt();
        scanner.nextLine();
        return returnValue;
    }

    public static int scanForInt(String message) {
        System.out.println(message);
        return scanForInt();
    }

    public static Long scanForLong() {
        Long returnValue = scanner.nextLong();
        scanner.nextLine();
        return returnValue;
    }

    public static Long scanForLong(String message) {
        System.out.println(message);
        return scanForLong();
    }

    public static String scanForString() {
        return scanner.nextLine();
    }

    public static String scanForString(String message) {
        System.out.println(message);
        return scanForString();
    }

}
