package dp;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BinaryOperator;

/**
 * Created by ShinD on 2022-02-25.
 */
@BOJ
public class 백준12865 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int ITEM_NUM = inputs[0];
        final int MAX_WEIGHT = inputs[1];



        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < ITEM_NUM; i++) {
            itemList.add(new Item(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }


        int [][] valueDp = new int[ITEM_NUM+1][MAX_WEIGHT+1];

        for(int i = 1; i<= ITEM_NUM; i++){

            for(int j = 1; j<= MAX_WEIGHT; j++){

                //valueDp[i][j] -> 현재 물건이 들어갔을 때, j무게에 대한 값의 최댓값
                //valueDp[i-1][j] -> 이전 물건이 들어갔을 때, j무게에 대한 값의 최댓값
                //valueDp[i- item(i).getWeight() ][j] + item(i).getValue()
                if(j >= itemList.get(i-1).getWeight()) {
                    valueDp[i][j] = Math.max(valueDp[i - 1][j], valueDp[i - 1][j - itemList.get(i-1).getWeight()] + itemList.get(i-1).getValue());
                }else {
                    valueDp[i][j] = valueDp[i - 1][j];
                }
            }

        }

        System.out.println(valueDp[ITEM_NUM][MAX_WEIGHT]);


    }

    private static class Item implements Comparable<Item>{
        private int value;
        private int weight;

        public Item(int[] info) {
            this.weight = info[0];
            this.value = info[1];
        }

        public int getValue() {
            return value;
        }

        public int getWeight() {
            return weight;
        }


        @Override
        public int compareTo(Item o) {
            return value - o.getValue();
        }
    }
}

