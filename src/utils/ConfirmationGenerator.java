package utils;

import java.util.Random;

public final class ConfirmationGenerator {
    private static final Random rand = new Random();

    private ConfirmationGenerator() {} // jangan diinstansiasi

    public static int generate() {
        return 100000 + rand.nextInt(900000); // 6-digit random
    }
}
