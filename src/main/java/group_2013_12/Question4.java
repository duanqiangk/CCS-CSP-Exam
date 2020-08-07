package main.java.group_2013_12;

import java.util.Scanner;


public class Question4 {

    /**
     * 结果取余
     */
    private static final long MOD = 1000000007;

    /**
     * 状态数量
     */
    private static final int STATE_AMOUNT = 6;

    /**
     * 输入数据位数最大值
     */
    private static final int INPUT_DIGIT = 1000;

    /**
     * 结果
     */
    private static long result = 0;

    /**
     * 使用公式计算
     * @param digit 有趣的数 的位数
     */
    private static void calculate(int digit) {
        long[][] dp = new long[INPUT_DIGIT + 1][STATE_AMOUNT + 1];
        dp[1][1] = 1;

        for (int i = 2; i <= digit; ++i) {
            dp[i][1] = 1;
            dp[i][2] = (2 * dp[i - 1][2] + dp[i - 1][4]) % MOD;
            dp[i][3] = (2 * dp[i - 1][3] + dp[i - 1][5] + dp[i - 1][4]) % MOD;
            dp[i][4] = (2 * dp[i - 1][4] + dp[i - 1][1]) % MOD;
            dp[i][5] = (dp[i - 1][5] + dp[i - 1][1]) % MOD;
            dp[i][6] = (2 * dp[i - 1][6] + dp[i - 1][2] + dp[i - 1][3]) % MOD;
        }

        result = dp[digit][6];
    }

    /**
     * 获取结果
     * @return 结果数据
     */
    private static long getResult() {
        return result;
    }

    /**
     * 初始化结果数据
     */
    private static void init() {
        result = 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            init();
            int digit = scanner.nextInt();
            calculate(digit);
            System.out.println(getResult());
        }
    }
}
