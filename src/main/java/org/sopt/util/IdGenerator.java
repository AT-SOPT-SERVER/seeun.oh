package org.sopt.util;

public class IdGenerator {
    private static int postId;

    private IdGenerator() {}

    public static int getId() {
        return postId++;
    }
}
