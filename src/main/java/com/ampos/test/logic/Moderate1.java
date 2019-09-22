package com.ampos.test.logic;

import java.util.LinkedList;
import java.util.List;

public class Moderate1 {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4};
        List<Integer> result = multiplyExceptI(input);
        System.out.println(result);
    }

    private static List<Integer> multiplyExceptI(int[] input) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < input.length; i++) {
            List<Integer> nums = getNotEqualIndex(input, i);
            result.add(nums.stream().reduce(1, (a, b) -> a * b));
        }
        return result;
    }

    private static List<Integer> getNotEqualIndex(int[] input, int i) {
        List<Integer> nums = new LinkedList<>();
        for (int j = 0; j < input.length; j++) {
            if (j != i) {
                nums.add(input[j]);
            }
        }
        return nums;
    }
}
