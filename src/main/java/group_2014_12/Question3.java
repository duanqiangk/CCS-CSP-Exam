package main.java.group_2014_12;

import java.util.*;

public class Question3 {

    private static Scanner scanner = new Scanner(System.in);

    private static List<Command> commandList = new LinkedList<>();

    private static Set<Double> bidSet = new HashSet<>();

    private static Double finalBid = 0.0;
    private static long finalCount = 0L;

    /**
     * 录入命令数据
     * @param command 命令
     */
    private static void loadCommand(String command) {
        String[] splitCommand = command.split("\\s+");
        if(splitCommand.length == 2 && "cancel".equals(splitCommand[0])){
            commandList.add(new CancelCommand(Integer.parseInt(splitCommand[1])));
            return;
        }

        double bid = Double.parseDouble(splitCommand[1]);
        long count = Long.parseLong(splitCommand[2]);

        bidSet.add(bid);
        commandList.add("buy".equals(splitCommand[0]) ? new BuyCommand(bid,count) : new SellCommand(bid,count));
    }

    /**
     * 整理命令数据
     */
    private static void clearCommandList() {
        boolean[] commandActive = new boolean[commandList.size()];
        for(int i = 0 ; i < commandList.size() ; ++i){
            if(commandList.get(i) instanceof CancelCommand){
                CancelCommand command = (CancelCommand)commandList.get(i);
                commandActive[command.commandNum - 1] = true;
                commandActive[i] = true;
            }
        }

        List<Command> tempList = new LinkedList<>();
        for(int i = 0 ; i < commandList.size() ; ++i ){
            if(commandActive[i]){
                continue;
            }
            tempList.add(commandList.get(i));
        }
        commandList = tempList;
    }

    /**
     * 对于每个定价，获得它的开盘价和成交量，如果大于其他定价的开盘价和成交量，更新最终量
     *
     * @param price 定价
     */
    private static void updateFinalBidCount(Double price){
        long buyCountSum = 0L;
        long sellCountSum = 0L;

        for(Command command : commandList){
            if(command instanceof BuyCommand){
                BuyCommand buyCommand = (BuyCommand) command;
                double bid = buyCommand.bid;
                long count = buyCommand.count;

                buyCountSum += (bid >= price ? count : 0L);
                continue;
            }
            if(command instanceof SellCommand){
                SellCommand sellCommand = (SellCommand) command;
                double bid = sellCommand.bid;
                long count = sellCommand.count;

                sellCountSum += (bid <= price ? count : 0L);
            }
        }
        long minCount = Math.min(buyCountSum, sellCountSum);
        if( minCount >= finalCount){
            finalCount = minCount;
            finalBid = price;
        }
    }

    public static void main(String[] args) {
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            loadCommand(command);
        }

        clearCommandList();
        List<Double> bidSetList = new ArrayList<>(bidSet);
        Collections.sort(bidSetList);

        for (Double price : bidSetList) {
            updateFinalBidCount(price);
        }

        System.out.println(String.format("%.2f %d" , finalBid , finalCount));
    }

    private interface Command{

    }

    private static class BuyCommand implements Command{
        private Double bid;
        private long count;

        BuyCommand(Double bid, long count) {
            this.bid = bid;
            this.count = count;
        }
    }

    private static class SellCommand implements Command{
        private Double bid;
        private long count;

        SellCommand(Double bid, long count) {
            this.bid = bid;
            this.count = count;
        }
    }

    private static class CancelCommand implements Command{
        private int commandNum;

        CancelCommand(int commandNum) {
            this.commandNum = commandNum;
        }
    }
}