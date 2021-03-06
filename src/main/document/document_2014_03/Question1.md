# CCS-CSP 2014-03 批次 题目1 相反数

## **问题描述**

　　有 N 个非零且各不相同的整数。请你编一个程序求出它们中有多少对相反数(a 和 -a 为一对相反数)。

### **输入格式**

　　第一行包含一个正整数 N。(1 ≤ N ≤ 500)。
　　第二行为 N 个用单个空格隔开的非零整数,每个数的绝对值不超过1000,保证这些整数各不相同。

### **输出格式**

　　只输出一个整数,即这 N 个数中包含多少对相反数。

### **样例输入**

> 5
> 1 2 3 -1 -2

### **样例输出**

> 2

## 解题方案

### 1.解题思路

**方案一：**设置一个2001大小的数组A，设输入数据为data，将data+1000的值设index，将A[index] + 1。

求结果时直接遍历A的前1000个数据，假如index为遍历过程中的下标，那么2000-index则为相反数下标，根据两边的最小值即可获得相反数对数；

**方案二：**使用Map<Integer,Integer>存储，key为输入数据值，value为出现的次数；求结果时对输入数据遍历一次，并获得该值和该值的相反数出现次数的最小值，即为相反数出现对数。

**方案三：**因为题目中说输入的整数互不相同，那么可以直接用一个Set<Integer>记录输入数据，即相同的相反数对不会出现多次，那么就直接记录一次就好。

### 2.解题步骤

这里三个方案其实都差不多，方案1、2是对于有多组相同相反数的解决方案，为了方便起见可以直接用第三种：

1. 设置Set
2. 对每个输入数据，如果Set中没有则存储绝对值到Set中，如果Set中出现则结果加一
3. 输出结果

### 3. Github地址

https://github.com/duanqiangk/CCS-CSP-Exam.git

