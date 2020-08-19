package main.java.group_2014_12;

import java.util.*;

public class Question1 {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        List<Integer> resultList = new ArrayList<>();
        Map<Integer , Integer> visMap = new HashMap<>();

        while ((n--) > 0){
            int code = scanner.nextInt();
            int count = visMap.containsKey(code) ? visMap.get(code) + 1 : 1;
            resultList.add(count);
            visMap.put(code,count);
        }

        StringBuilder result = new StringBuilder();
        for(Integer th : resultList){
            result.append(th).append(" ");
        }
        System.out.println(result.toString().trim());
    }
}
