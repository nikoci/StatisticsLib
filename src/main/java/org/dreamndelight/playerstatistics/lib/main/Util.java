package org.dreamndelight.playerstatistics.lib.main;

public class Util {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String generatePlayerID() {
        int length = 6;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            length--;
        }

        return (PlayerStatistics.get().getUserData().isPlayerIDAvailable(builder.toString())) ? builder.toString() : generatePlayerID();
    }

}
