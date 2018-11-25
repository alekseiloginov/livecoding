package com.loginov;

public class Fibonacci {

    public static void main(String[] args) {
        int level = 100;
        fib(level);
    }

    static void fib(int maxLevel) {
        if (maxLevel < 1) {
            return;
        }
        System.out.printf("Fibonacci sequence to level %s: %s", maxLevel, 1);
        if (maxLevel > 1) {
            sum(maxLevel, 1, 0, 1);
        }
    }

    static void sum(int maxLevel, int level, int left, int right) {
        level++;
        int newLeft = right;
        long newRight = (long) right + left;
        if (newRight > Integer.MAX_VALUE) {
            throw new RuntimeException("Integer overflow for the level: " + level);
        }
        System.out.printf(" %s", newRight);
        if (maxLevel > level) {
            sum(maxLevel, level, newLeft, (int) newRight);
        }
    }
}
