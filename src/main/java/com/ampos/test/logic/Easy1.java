package com.ampos.test.logic;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Easy1 {

    public static void main(String[] args) {
        int[] input = {-1, 2, 4, 2, -1, 4, 8};
        Integer val = findNonDupEle(input);
        System.out.println(val);
    }

    private static Integer findNonDupEle(int[] input) {
        Map<Integer, Long> countedMap = Arrays.stream(input).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return countedMap.keySet().stream().filter(key -> countedMap.get(key) == 1).findFirst().orElse(null);
    }


}
