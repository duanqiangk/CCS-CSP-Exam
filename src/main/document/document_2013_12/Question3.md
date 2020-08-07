## CCS-CSP 2013-12 批次 题目3

### **问题描述**

　　在横轴上放了n个相邻的矩形，每个矩形的宽度是1，而第i（1 ≤ i ≤ n）个矩形的高度是hi。这n个矩形构成了一个直方图。例如，下图中六个矩形的高度就分别是3, 1, 6, 5, 2, 3。

![](..\..\resources\image\p31.png)
　　请找出能放在给定直方图里面积最大的矩形，它的边要与坐标轴平行。对于上面给出的例子，最大矩形如下图所示的阴影部分，面积是10。
![](..\..\resources\image\p32.png)

#### **输入格式**

　　第一行包含一个整数n，即矩形的数量(1 ≤ n ≤ 1000)。
　　第二行包含n 个整数h1, h2, … , hn，相邻的数之间由空格分隔。(1 ≤ hi ≤ 10000)。hi是第i个矩形的高度。

#### **输出格式**

　　输出一行，包含一个整数，即给定直方图内的最大矩形的面积。

#### **样例输入**

6
3 1 6 5 2 3

#### **样例输出**

10

### 解题思路

#### 1. 方向

对题目结果进行分析，设a和b是结果的两端下标，那么结果矩形的宽则为```b - a + 1 ```，结果矩形的高则为a到b之间最矮矩形的高```h(k)```，面积为```S = (b - a + 1) * h(k)```，其中最矮矩形的高为```h(k)```。

对于输入数据```h(i)```，其下标为```i```，假设`i`为右边界，要做的有两个步骤:

1. 找到左边界`j`
2. 找到`j`-`i`之间最矮的矩形高度
3. 计算`i-j`的面积，并比较过程中得到的最大面积，如果大于最大面积则更新。

对所有数据遍历后得到的最大面积即为所求。

#### 2. 步骤

#### 2. 步骤

假设输入``` h(i) ```表示第i个长方形的高，并且设```S(i)```表示前i个输入数据所产生的的最大矩形面积。

1. 输入数据`h(i)`，对`i`前面的所有数据`h(j)`进行遍历，且设`j`为所求面积的左下标，`i`为右下标。
2. 遍历`i-j`之间的所有矩形，找到最短高度`min(h)`，设`S=min(h) * (j - i + 1)`
3. 如果S大于结果MaxS，更新结果，否则继续遍历

#### 3. 示例代码

``` java
public class Main {
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
```



#### 4. Github地址

https://github.com/duanqiangk/CCS-CSP-Exam.git