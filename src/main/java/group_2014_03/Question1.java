package main.java.group_2014_03;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Question1{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()){
            int n = scanner.nextInt();
            Set<Integer> dataSet = new HashSet<>();
            int result = 0 ;

            for(int i = 0 ; i < n ; ++ i){
                int data = scanner.nextInt();
                if(dataSet.contains(Math.abs(data))){
                    result ++;
                    continue;
                }
                dataSet.add(Math.abs(data));
            }
            System.out.println(result);
        }
    }
}
