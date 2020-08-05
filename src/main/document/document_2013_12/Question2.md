## CCS-CSP 2013-12 批次 题目2

### Description

> ​        每一本正式出版的图书都有一个ISBN号码与之对应，ISBN码包括9位数字、1位识别码和3位分隔符，其规定格式如“x-xxx-xxxxx-x”，其中符号“-”是分隔符（键盘上的减号），最后一位是识别码，例如0-670-82162-4就是一个标准的ISBN码。ISBN码的首位数字表示书籍的出版语言，例如0代表英语；第一个分隔符“-”之后的三位数字代表出版社，例如670代表维京出版社；第二个分隔之后的五位数字代表该书在出版社的编号；最后一位为识别码。
> 　　识别码的计算方法如下：
> 　　首位数字乘以1加上次位数字乘以2……以此类推，用所得的结果mod 11，所得的余数即为识别码，如果余数为10，则识别码为大写字母X。例如ISBN号码0-670-82162-4中的识别码4是这样得到的：对067082162这9个数字，从左至右，分别乘以1，2，…，9，再求和，即0×1+6×2+……+2×9=158，然后取158 mod 11的结果4作为识别码。
> 　　编写程序判断输入的ISBN号码中识别码是否正确，如果正确，则仅输出“Right”；如果错误，则输出是正确的ISBN号码。 

### Input Format

>  输入只有一行，是一个字符序列，表示一本书的ISBN号码（保证输入符合ISBN号码的格式要求）。 

### Output Format

>  输出一行，假如输入的ISBN号码的识别码正确，那么输出“Right”，否则，按照规定的格式，输出正确的ISBN号码（包括分隔符“-”）。 

### Sample Input

>  0-670-82162-4 
>
>  0-670-82162-0 

### Sample Output

>  Right 
>
>  0-670-82162-4 

### 解题思路

#### 1. 方向

- ISBN号格式：语言（1位）- 出版社（3位）- 出版社编号（5位）- 识别码（1位）
- 识别码计算方法：[首位数字\*1+次位数字\*2 + ...末位数字\*9] mod 11
- 输出：如果识别码正确输出```Right```，错误更改正确的识别码后再输出。

#### 2. 步骤

1. 以“-”为分隔符拆分，获取10位数字
2. 计算识别码
3. 根据正确计算得到的识别码进行输出

#### 3.示例代码

``` java
import java.util.*;

/**
 * @Description 直接计算，合格则输出Right，不合格输出正确字符串
 */
public class Main {

    /**
     * 计算正确的识别码
     *
     * @param inputISBN 输入ISBN编号
     * @return 正确识别码（字符格式）
     */
    private static char calculate(String inputISBN){
        char[] charArray = inputISBN.toCharArray();
        int index = 1;
        int result = 0 ;

        for(int i = 0 ; i < charArray.length - 2; ++ i){
            char item = charArray[i];

            if(Character.isDigit(item)){
                result += index * (item - '0');
                ++index;
            }
        }

        result %= 11;
        return result == 10 ? 'X' : (char)(result + '0');
    }
    /**
     * 主处理程序
     *
     * @param inputISBN 题目输入ISBN编号
     */
    private static void doOutput(String inputISBN) {
        // 正确的识别码
        char flag = calculate(inputISBN);
        // 题目所给识别码
        char inputFlag = inputISBN.charAt(inputISBN.length() - 1);

        if(flag == inputFlag){
            System.out.println("Right");
            return;
        }

        char[] charArray = inputISBN.toCharArray();
        charArray[charArray.length - 1] = flag;
        System.out.println(String.valueOf(charArray));
    }

    /**
     * Main
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            doOutput(scanner.next().trim());
        }
    }
}
```

#### 4. Github地址

https://github.com/duanqiangk/CCS-CSP-Exam.git

