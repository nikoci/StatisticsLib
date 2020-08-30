package com.dehys.norbecore.main;

public class Util {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String generatePlayerID() {
        int length = 6;
        StringBuilder builder = new StringBuilder();
        //while (builder.toString().equals("") || !Main.getInstance().getUserData().isPlayerIDAvailable(builder.toString())) {
        while (length > 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            length--;
        }
        // }
        System.out.println(builder.toString());
        return builder.toString();
    }

}
