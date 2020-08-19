package main.java.group_2014_09;


import java.util.*;

public class Question3 {

    private static boolean caseSensitive = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String pattern = scanner.next();
        caseSensitive = scanner.nextInt() == 1 ;
        pattern = caseSensitive ? pattern : pattern.toLowerCase();

        int caseCount = scanner.nextInt();
        List<String> resultList = new LinkedList<>();

        while ((caseCount --) > 0){
            String inputCase = scanner.next();
            String caseString = caseSensitive ? inputCase : inputCase.toLowerCase();

            if(caseString.contains(pattern)){
                resultList.add(inputCase);
            }
        }

        resultList.forEach(System.out::println);
    }
}
