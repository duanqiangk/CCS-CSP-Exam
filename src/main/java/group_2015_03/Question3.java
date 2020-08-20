package main.java.group_2015_03;

import java.util.*;

public class Question3 {

    private static Scanner scanner = new Scanner(System.in);

    // 0 表示闰年，1表示平年
    private static final int[][] monthDays = {
            {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
            {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
    };

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
    }

    /**
     * 获取year的month一号是周几
     * 其中 0 是周日
     *
     * @param year  年份
     * @param month 月份
     * @return 星期
     */
    private static int getStartWeekDay(int year, int month) {
        int days = 2;
        for (int i = 1850; i < year; ++i) {
            if (isLeapYear(i)) {
                days = (days + 366) % 7;
                continue;
            }
            days = (days + 365) % 7;
        }
        int[] monthDayList = monthDays[isLeapYear(year) ? 0 : 1];
        for (int i = 0; i < month - 1; ++i) {
            days = (days + monthDayList[i]) % 7;
        }

        return days % 7;
    }


    public static void main(String[] args) {
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        c = c % 7;
        int startYear = scanner.nextInt();
        int endYear = scanner.nextInt();

        for (int year = startYear; year <= endYear; ++year) {
            int startWeekDay = getStartWeekDay(year, a);

            // 找到year年a月的第一个星期c是几号
            int day = 1;
            while (startWeekDay != c) {
                startWeekDay++;
                startWeekDay %= 7;
                ++day;
            }

            // 判断是否超过日期数限制，超过输出none
            int monthDaySize = monthDays[isLeapYear(year) ? 0 : 1][a - 1];
            if (day + (b - 1) * 7 > monthDaySize) {
                System.out.println("none");
                continue;
            }

            day += (b - 1) * 7;
            System.out.println(String.format("%d/%02d/%02d", year, a, day));
        }
    }
}