package main.java.group_2014_12;

import java.util.*;

public class Question2 {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * 获取初始遍历点
     *
     * @param loopTh 第几次循环
     * @param n 方针维度
     * @return 初始点坐标
     */
    private static Coordinate fixStartCoordinate(int loopTh, int n) {
        int a = (loopTh <= n ? loopTh : loopTh % n + 1);
        int b = (loopTh <= n ? 1 : n);

        if ((loopTh <= n && loopTh % 2 == 1 )|| (loopTh > n && loopTh % 2 == 0)) {
            return new Coordinate(a, b);
        }
        return new Coordinate(b, a);
    }

    /**
     * 判断是否是合法的坐标点
     *
     * @param map 坐标地图
     * @param coordinate 坐标点
     * @return 坐标点是否合法
     */
    private static boolean isLegalCoordinate(int[][] map, Coordinate coordinate) {
        int x = coordinate.x;
        int y = coordinate.y;

        return x > 0 && y > 0 && x < map.length && y < map.length;
    }

    /**
     * 从起始坐标点开始遍历
     *
     * @param map 地图
     * @param coordinate 起始坐标
     * @param loopTh 第几次循环
     * @return 遍历过程
     */
    private static List<Integer> traverseMap(int[][] map, Coordinate coordinate, int loopTh) {
        int xStep = (loopTh % 2 == 1 ? -1 : 1);
        int yStep = (loopTh % 2 == 1 ? 1 : -1);
        List<Integer> resultList = new ArrayList<>();

        while (isLegalCoordinate(map, coordinate)) {
            int x = coordinate.x;
            int y = coordinate.y;

            coordinate.x += xStep;
            coordinate.y += yStep;

            resultList.add(map[x][y]);
        }
        return resultList;
    }

    /**
     * 格式化输出
     *
     * @param dataList 数据列表
     * @return 格式化输出字符串
     */
    private static String formatOutput(List<Integer> dataList) {
        StringBuilder result = new StringBuilder();
        dataList.forEach(o -> result.append(" ").append(o));
        return result.toString().trim();
    }

    public static void main(String[] args) {
        int n = scanner.nextInt();

        int[][] map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                map[i][j] = scanner.nextInt();
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= 2 * n - 1; ++i) {
            Coordinate startCoordinate = fixStartCoordinate(i, n);
            List<Integer> list = traverseMap(map, startCoordinate, i);
            result.addAll(list);
        }

        System.out.println(formatOutput(result));
    }

    /**
     * 坐标类
     */
    private static class Coordinate {
        private int x;
        private int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
