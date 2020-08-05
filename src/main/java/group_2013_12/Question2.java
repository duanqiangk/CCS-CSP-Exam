package main.java.group_2013_12;

import java.util.*;

/**
 * @Description 直接计算，合格则输出Right，不合格输出正确字符串
 */
public class Question2 {

    /**
     * 计算正确的识别码
     *
     * @param inputISBN 输入ISBN编号
     * @return 正确识别码（字符格式）
     */
    private static char calculate(String inputISBN){
        char[] charArray = inputISBN.toCharArray();
        int index = 1;
        int result = 0 ;

        for(int i = 0 ; i < charArray.length - 2; ++ i){
            char item = charArray[i];

            if(Character.isDigit(item)){
                result += index * (item - '0');
                ++index;
            }
        }

        result %= 11;
        return result == 10 ? 'X' : (char)(result + '0');
    }
    /**
     * 主处理程序
     *
     * @param inputISBN 题目输入ISBN编号
     */
    private static void doOutput(String inputISBN) {
        // 正确的识别码
        char flag = calculate(inputISBN);
        // 题目所给识别码
        char inputFlag = inputISBN.charAt(inputISBN.length() - 1);

        if(flag == inputFlag){
            System.out.println("Right");
            return;
        }

        char[] charArray = inputISBN.toCharArray();
        charArray[charArray.length - 1] = flag;
        System.out.println(String.valueOf(charArray));
    }

    /**
     * Main
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            doOutput(scanner.next().trim());
        }
    }
}
