package main.java.group_2014_03;

import java.util.*;

public class Question3 {

    /**
     * 存储合法的选项
     * key： "-" + 选项
     * value: true-有参选项，false-无参选项
     */
    private static Map<String, Boolean> accessOptionMap = new HashMap<>();

    /**
     * 读取合法参数算法
     *
     * @param accessOption 输入字符串
     */
    private static void loadAccessOption(String accessOption) {
        char[] charArray = accessOption.toCharArray();

        for (int i = charArray.length - 1; i >= 0; --i) {
            char c = charArray[i];
            boolean isParamOption = false;
            if (c == ':') {
                --i;
                c = charArray[i];
                isParamOption = true;
            }
            accessOptionMap.put("-" + c, isParamOption);
        }
    }

    /**
     * 判断是否是合法的选项参数
     *
     * @param paramString 选项的参数
     * @return 是否合法
     */
    private static boolean isLegalParam(String paramString) {
        char[] charArray = paramString.toCharArray();

        for (char c : charArray) {
            if (!Character.isLowerCase(c) && c != '-' && !Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 添加option 到 结果列表optionList中
     *
     * @param optionList 结果列表
     * @param option     要添加的选项对象
     */
    private static void addToResultList(List<AbstractOption> optionList, AbstractOption option) {
        for (int i = 0; i < optionList.size(); ++i) {
            AbstractOption currentOption = optionList.get(i);

            if (currentOption.equals(option)) {
                optionList.set(i, option);
                return;
            }
        }
        optionList.add(option);
    }

    /**
     * 读取输入命令行，并解析命令行中的参数
     *
     * @param command 命令行
     * @return 解析后的参数
     */
    private static List<AbstractOption> loadOptionList(String command) {
        String[] wordList = command.split("\\s+");
        List<AbstractOption> resultList = new ArrayList<>();

        for (int i = 1; i < wordList.length; ++i) {
            String option = wordList[i];

            if (option.length() != 2 && option.charAt(0) != '-') {
                break;
            }

            if (!accessOptionMap.containsKey(option)) {
                break;
            }

            if (!accessOptionMap.get(option)) {
                addToResultList(resultList, new NoParamOption(option));
                continue;
            }

            if (accessOptionMap.get(option) && i + 1 < wordList.length && isLegalParam(wordList[i + 1])) {
                addToResultList(resultList, new OneParamOption(option, wordList[++i]));
                continue;
            }

            break;
        }
        return resultList;
    }

    /**
     * 打印结果
     *
     * @param caseNum    第case组
     * @param optionList 结果列表
     * @return 结果String
     */
    private static String printResult(int caseNum, List<AbstractOption> optionList) {
        StringBuilder stringBuilder = new StringBuilder();

        optionList.sort(Comparator.comparing(o -> o.option));

        for (int i = 0; i < optionList.size(); ++i) {
            AbstractOption option = optionList.get(i);
            stringBuilder.append(" ").append(option.getCommandOption());
        }

        return String.format("Case %d:%s", caseNum, stringBuilder.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String accessOption = scanner.nextLine();
        loadAccessOption(accessOption);

        int caseSize = scanner.nextInt();
        scanner.next();
        List<String> result = new LinkedList<>();

        for (int i = 1; i <= caseSize; ++i) {
            String command = scanner.nextLine();

            List<AbstractOption> optionList = loadOptionList(command);
            result.add(printResult(i, optionList));
        }

        result.forEach(System.out::println);
    }

    /**
     * 选项抽象类
     */
    private static abstract class AbstractOption {
        private String option;

        /**
         * 获取选项的字符串形式
         * 1. 如果是无参返回：option
         * 2. 如果是有参返回： option + " " + param
         *
         * @return 选项字符串
         */
        abstract String getCommandOption();

        @Override
        public boolean equals(Object object) {
            if (object instanceof AbstractOption) {
                return this.option.equals(((AbstractOption) object).option);
            }

            return false;
        }
    }

    /**
     * 选项具体类，无参选项
     */
    private static class NoParamOption extends AbstractOption {
        NoParamOption(String option) {
            super.option = option;
        }

        @Override
        public String getCommandOption() {
            return super.option;
        }
    }

    /**
     * 选项具体类， 有参选项
     */
    private static class OneParamOption extends AbstractOption {
        private String param;

        OneParamOption(String option, String param) {
            super.option = option;
            this.param = param;
        }

        @Override
        public String getCommandOption() {
            return super.option + " " + this.param;
        }
    }
}