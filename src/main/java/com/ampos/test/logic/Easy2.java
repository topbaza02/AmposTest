package com.ampos.test.logic;

import com.google.common.collect.Collections2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Easy2 {
    public static void main(String[] args) {
        int input = 6;
        /***/

        int step = input;
        int count = 1;
        while (step / 2 > 0) {
            List<Integer> nums = toExtractList(step, input);
            Collection<List<Integer>> permute = new HashSet<>(Collections2.permutations(nums));
            count += permute.size();
            step -= 2;
        }


        System.out.println(count);
    }

    private static List<Integer> toExtractList(int step, int input) {
        List<Integer> list = new ArrayList<>();
        List<Integer> oneList = extractToOne(step - 2);
        List<Integer> twoList = extractToTwo(oneList, input);
        list.addAll(oneList);
        list.addAll(twoList);
        return list;
    }

    private static List<Integer> extractToTwo(List<Integer> oneList, int input) {
        List<Integer> list = new ArrayList<>();
        int sum = input - oneList.stream().reduce(0, Integer::sum);
        for (int i = 0; i < sum; i += 2) {
            list.add(2);
        }
        return list;
    }

    private static List<Integer> extractToOne(int step) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < step; i++) {
            list.add(1);
        }
        return list;
    }

}
