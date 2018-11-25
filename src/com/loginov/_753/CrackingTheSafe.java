package com.loginov._753;

public class CrackingTheSafe {

    public static void main(String[] args) {
        System.out.println(new CrackingTheSafe().getAllCodesCombinations());
    }

    public String getAllCodesCombinations() {
        // init helper array to track that we use all combos (once)
        int[] combos = new int[1000];
        for (int i = 0; i < 1000; i++) combos[i] = 9;
        StringBuilder codes = new StringBuilder("999");
        int nextIdx = 999;
        int nextDigit = combos[nextIdx]--;
        while (nextDigit >= 0) {
            codes.append(nextDigit);
            nextIdx = getNextThreeDigits(nextIdx, nextDigit);
            nextDigit = combos[nextIdx]--;
        }
        return codes.toString();
    }

    protected int getNextThreeDigits(int lastThreeDigits, int newDigit) {
        return (lastThreeDigits % 100) * 10 + newDigit;
    }


}
