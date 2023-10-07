package dev.ohate.commonlib;

import java.security.SecureRandom;

public class StringUtil {

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final String NUMBER_SET = "0123456789";
    private static final String LOW_ALPHA_SET = "abcdefghijklmnopqrstuvwxyz";
    private static final String CAP_ALPHA_SET = LOW_ALPHA_SET.toUpperCase();
    private static final String ALPHANUMERIC_SET = NUMBER_SET + LOW_ALPHA_SET + CAP_ALPHA_SET;

    public static String repeat(String string, int times) {
        return (new String(new char[times])).replace("\u0000", string);
    }

    public static String randomStringFromString(String characterSet, int desiredLength) {
        StringBuilder builder = new StringBuilder(desiredLength);

        for (int i = 0; i < desiredLength; i++) {
            builder.append(characterSet.charAt(RANDOM.nextInt(characterSet.length())));
        }

        return builder.toString();
    }

    public static String randomAlphanumericString(int length) {
        return randomStringFromString(ALPHANUMERIC_SET, length);
    }

    public static String randomNumberString(int length) {
        return randomStringFromString(NUMBER_SET, length);
    }

    public static String toHumanReadable(String input) {
        String[] name = input.toLowerCase().split("_");
        StringBuilder builder = new StringBuilder(input.length());

        for (String splitName : name) {
            String firstChar = splitName.substring(0, 1).toUpperCase();
            String rest = splitName.substring(1);

            builder.append(firstChar).append(rest).append(" ");
        }

        return builder.toString().trim();
    }

}
