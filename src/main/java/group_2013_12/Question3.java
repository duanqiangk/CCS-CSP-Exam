package main.java.group_2013_12;

import java.util.*;

public class Question3 {
    /**
     * 结果面积
     */
    private static Integer area = 0 ;
    /**
     * 输入数据
     */
    private static List<Integer> inputData = new ArrayList<>();

    /**
     * 初始化数据
     */
    public static void init(){
        area = 0 ;
        inputData.clear();
    }

    /**
     * 计算以lastIndex为最右端点时得到的面积S，并更新最大面积
     *
     * @param lastIndex 最右端点
     */
    public static void calculate(int lastIndex){
        //最左端点
        int leftIndex = lastIndex;

        while (leftIndex >= 0){
            // 找到最左端点和最右端点之间最矮的矩形高度
            int minHeight = inputData.get(lastIndex);
            for(int j = lastIndex ; j >= leftIndex ; -- j){
                minHeight = minHeight > inputData.get(j) ? inputData.get(j) : minHeight;
            }

            // 计算左右端点之间的最大面积
            int currentArea = (lastIndex - leftIndex + 1) * minHeight;
            // 更新最大面积
            area = currentArea > area ? currentArea : area;
            --leftIndex;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()){
            init();
            int n = scanner.nextInt();
            for(int i = 0 ; i < n; ++i ){
                inputData.add(scanner.nextInt());
                calculate(i);
            }
            System.out.println(area);
        }
    }
}
