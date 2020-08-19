package main.java.group_2014_09;


import java.util.*;

public class Question2 {

    private static int ROW_SIZE = 100;

    private static int COL_SIZE = 100;

    private static boolean[][] vis = new boolean[ROW_SIZE ][COL_SIZE];

    /**
     * 计算有效区域面积
     *
     * @param x1 左下标x
     * @param y1 左下标y
     * @param x2 右上标x
     * @param y2 右上标y
     * @return 有效面积
     */
    private static int countArea(int x1, int y1, int x2, int y2) {
        int area = 0 ;
        for (int i = x1 ; i < x2 ; ++i ){
            for(int j = y1 ; j < y2 ; ++ j){
                if( vis[i][j] ){
                    continue;
                }
                vis[i][j] = true;
                ++ area;
            }
        }
        return area;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int area = 0;

        while ((n--) > 0) {
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();

            area += countArea(x1, y1, x2, y2);
        }

        System.out.println(area);
    }
}
