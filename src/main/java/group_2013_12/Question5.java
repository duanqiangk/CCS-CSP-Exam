package main.java.group_2013_12;

import java.util.Scanner;

/**
 * 测试数据
 * 5 5
 * --+-+
 * ..|#.
 * ..|##
 * S-+-T
 * ####.
 *
 * 5 6
 * #--||#
 * #|#-|#
 * #|#++#
 * S+---T
 * #.####
 */
public class Question5 {

    private static final int MAP_SIZE = 50;

    private static char[][] map = new char[MAP_SIZE + 1][];

    private static Position startPosition = null;

    private static Position endPosition = null;

    private static int n, m;

    /**
     * 获取点的移动量
     *
     * @param x 横坐标
     * @param y 纵坐标
     * @return 横纵坐标移动分量数组
     */
    private static int[][] direct(int x, int y) {
        int[][] result;
        switch (map[x][y]) {
            case '-':
                result = new int[][]{{0,-1}, {0,1}};
                break;
            case '|':
                result = new int[][]{{-1,0}, {1,0}};
                break;
            case '.':
                result = new int[][]{{1,0}};
                break;
            case 'T':
            case 'S':
            case '+':
                result = new int[][]{{0,-1}, {0,1},{-1,0}, {1,0}};
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    /**
     * 检查点（x，y）是否到达边界
     * @param x 横坐标
     * @param y 纵坐标
     * @return 是否合法点
     */
    private static boolean checkPosition(int x,int y){
        if (x >= n || y >= m || x < 0 || y < 0) {
            return false;
        }
        return true;
    }

    /**
     * 起点遍历DFS
     *
     * @param current 当前点
     * @param visit 访问标记
     */
    private static void startPositionDFS(Position current, boolean[][] visit) {
        int x = current.getX();
        int y = current.getY();
        if (!checkPosition(x,y)) {
            return;
        }

        int[][] direct = direct(x, y);
        if(direct == null){
            return;
        }
        visit[x][y] = true;

        for (int i = 0; i < direct.length; ++i) {
            int nextX = x + direct[i][0];
            int nextY = y + direct[i][1];

            if (checkPosition(nextX , nextY) && !visit[nextX][nextY]) {
                startPositionDFS(new Position(x + direct[i][0], y + direct[i][1]), visit);
            }
        }
    }

    /**
     * 直接对点DFS，看是否可以到达终点
     * 优化：如果某个点的下一个点可以到达终点，那么这个点也可以到达终点，做剪枝
     *
     * @param current 当前点
     * @param endVisitMap 访问过的点集
     * @return 是否可以到达终点
     */
    private static boolean endPositionDFS(Position current, boolean[][] endVisitMap) {
        int x = current.getX();
        int y = current.getY();
        if(! checkPosition(x,y) || map[x][y] == '#'){
            return false;
        }
        if(map[x][y] == 'T'){
            return true;
        }
        endVisitMap[x][y] = true;
        int[][] direct = direct(x, y);

        for(int i = 0 ; i < direct.length ; ++ i){
            int nextX = x + direct[i][0];
            int nextY = y + direct[i][1];

            if(checkPosition(nextX,nextY) &&
                    !endVisitMap[nextX][nextY] &&
                    endPositionDFS(new Position(nextX,nextY) , endVisitMap)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        for (int i = 0; i < n; ++i) {
            String line = sc.next();
            map[i] = line.toCharArray();

            //定位开始点和结束点
            for (int j = 0; j < map[i].length; ++j) {
                if (map[i][j] == 'S') {
                    startPosition = new Position(i, j);
                }
                if (map[i][j] == 'T') {
                    endPosition = new Position(i, j);
                }
            }
        }

        // 起点遍历
        boolean[][] startVisitMap = new boolean[n][m];
        startPositionDFS(startPosition, startVisitMap);
        if(!startVisitMap[endPosition.getX()][endPosition.getY()]){
            System.out.println("I'm stuck!");
            return;
        }
        // 对起点可以达到的每个点做遍历，如果这个不可以到达终点，记录+1
        int count = 0 ;
        for(int i = 0 ; i < startVisitMap.length ; ++ i ){
            for(int j = 0 ; j < startVisitMap[i].length; ++j ){
                boolean[][] endVisitMap = new boolean[n][m];
                if(startVisitMap[i][j] && ! endPositionDFS(new Position(i,j) ,endVisitMap)){
                    ++ count;
                }
            }
        }
        System.out.println(count);
    }

    private static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
