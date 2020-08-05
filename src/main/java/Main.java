package main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Description 使用Map 记录即可
 */
public class Main {

    /**
     * Map：输入数据-出现次数
     */
    private static Map<Integer, Integer> dataCountMap = new HashMap<>();
    /**
     * 记录当前选中的值
     */
    private static int currentData = 0;
    /**
     * 记录最大出现次数
     */
    private static int maxCount = 0;

    /**
     * 初始化static数据
     */
    private static void reset() {
        dataCountMap.clear();
        currentData = 0;
        maxCount = 0;
    }

    /**
     * 记录一次数据，并更新currentData和maxCount
     * @param data 输入数据
     */
    private static void record(Integer data) {
        // data是否已经出现过
        boolean exist = dataCountMap.containsKey(data);
        // data的出现次数
        int count = exist ? dataCountMap.get(data) + 1 : 1;

        if (count > maxCount) {
            currentData = data;
            maxCount = count;
        }
        if (count == maxCount && data < currentData) {
            currentData = data;
            maxCount = count;
        }
        dataCountMap.put(data, count);
    }

    /**
     * 获取本次测试结果
     *
     * @return 满足题意的值
     */
    private static int getResult() {
        return currentData;
    }

    /**
     * 主程序
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            reset();
            int n = scanner.nextInt();
            for (int i = 0; i < n; ++i) {
                record(scanner.nextInt());
            }
            System.out.println(getResult());
        }
    }
}
