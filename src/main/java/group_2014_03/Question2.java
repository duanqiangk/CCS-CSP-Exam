package main.java.group_2014_03;

import java.util.LinkedList;
import java.util.Scanner;

public class Question2{

    /**
     * 选择窗口并重新排序
     *
     * @param windowList 窗口排序列表
     * @param x 点击点横坐标
     * @param y 点击点纵坐标
     * @return windowId or IGNORED
     */
    private static String selectWindow(LinkedList<Window> windowList, int x , int y ){
        for(int i = 0 ; i < windowList.size() ; ++i ){
            Window window = windowList.get(i);

            if(window.checkIn(x,y)){
                // 将选择的窗口从原有列表中移除并移到最顶端
                windowList.remove(window);
                windowList.addFirst(window);
                return Integer.toString(window.getId());
            }
        }

        return "IGNORED";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()){
            int n = sc.nextInt();
            int m = sc.nextInt();
            LinkedList<Window> windowList = new LinkedList<>();

            for(int i = 0 ; i < n; ++i){
                int x1 = sc.nextInt();
                int y1 = sc.nextInt();
                int x2 = sc.nextInt();
                int y2 = sc.nextInt();

                windowList.addFirst(new Window(i+1, x1,y1,x2,y2));
            }

            for(int i = 0 ; i < m ; ++ i){
                int x = sc.nextInt();
                int y = sc.nextInt();

                System.out.println(selectWindow(windowList , x,y));
            }

        }
    }

    /**
     * 窗口类
     */
    private static class Window{
        // 窗口序号
        private int id;
        private int x1;
        private int y1;
        private int x2;
        private int y2;

        private Window(int id, int x1, int y1, int x2, int y2) {
            this.id = id;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        private int getId(){
            return id;
        }

        private boolean checkIn(int x , int y){
            return x >= x1 && y >= y1 && x <= x2 && y <= y2;
        }
    }
}
