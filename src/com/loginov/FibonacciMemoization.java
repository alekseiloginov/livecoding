package com.loginov;

import java.util.Arrays;

public class FibonacciMemoization {

    public static void main(String[] args) {
        int n = 10;
        int[] memo = new int[n+1];
        for (int i = 0; i < n; i++) {
            System.out.println(i + ":" + fib(memo, i));
        }
        System.out.println(Arrays.toString(memo));
    }

    static int fib(int[] memo, int n) {
        if (n<0) return 0;
        if (n==1) {
            memo[1] = 1;
            return 1;
        }
        if (memo[n] > 0) return memo[n];
        memo[n] = fib(memo,n-1) + fib(memo,n-2);
        return memo[n];
    }
}
