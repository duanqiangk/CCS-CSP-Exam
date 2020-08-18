## CCS-CSP 2013-12 批次 题目1

### Description

>  给定n个正整数，找出它们中出现次数最多的数。如果这样的数有多个，请输出其中最小的一个。 

### Input Format

>  给定n个正整数，找出它们中出现次数最多的数。如果这样的数有多个，请输出其中最小的一个。 

### Output Format

> 输入的第一行只有一个正整数n(1 ≤ n ≤ 1000)，表示数字的个数。
> 输入的第二行有n个整数s1, s2, …, sn (1 ≤ si ≤ 10000, 1 ≤ i ≤ n)。相邻的数用空格分隔。 

### Sample Input

>  6
> 10 1 10 20 30 20 

### Sample Output

> 10

### 解题思路

#### 1. 方向

使用Map来记录数据出现次数；使用单独变量记录当前满足题意的值；随着输入每次更新即可。

#### 2. 步骤

1. 定义Map，（键，值）= （输入数据，出现次数）
2. 每一次输入一个数据都更新结果数据，设结果数据为result，结果数据有：
   - result 的值（resultData）
   - result 出现的次数（resultCount）

3. result 更新方法：
   - 定义变量count 表示当前输入数据（input）的出现次数，如果没有出现过则置位1；
   - 如果cout 大于 resultCount，直接更新result和resultCount；
   - 如果count 等于 resultCount，判断result 和 data的大小，data 小于result 则更新；

4. 输入完成后获取resultData即可
5. 下一批测试数据需要重置内置属性。

#### 3. Github 地址

https://github.com/duanqiangk/CCS-CSP-Exam.git