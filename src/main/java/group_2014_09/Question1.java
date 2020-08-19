package main.java.group_2014_09;

import java.util.*;

public class Question1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> dataList = new ArrayList<>();
        int n = scanner.nextInt();
        for(int i = 0 ; i < n ; ++ i ){
            dataList.add(scanner.nextInt());
        }

        Collections.sort(dataList);
        int count = 0 ;

        for(int i = 1; i < dataList.size() ; ++i ){
            if(dataList.get(i) - dataList.get(i-1) == 1){
                ++ count;
            }
        }
        System.out.println(count);
    }
}
