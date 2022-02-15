package bfs와dfs;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@BOJ
public class 백준2805 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {


        int DesiredTreeLength = enterDesiredTreeLength();

        ArrayList<Integer> trees = new ArrayList<>(enterTrees());
        sorting(trees);

        System.out.println(cutting(DesiredTreeLength, trees));

    }



    private static long cutting(int DesiredTreeLength, ArrayList<Integer> trees) {
        long low = 0;
        long high = trees.get(trees.size()-1);

        while(low <= high){

            long mid = (low + high) /2;


            /**
             * UPPER BOUND를 구하는 문제
             * 만약 자른 크기가 원하는 길이보다 크다면? LOW를 높여
             * 만약 자른 크기가 원하는 길이와 같다면?  LOW를 높여
             * 만약 자른 크기가 원하는 길이보다 작다면?  HIGH를 줄여
             */
            Long cuttingSize = trees.stream().filter(integer -> integer > mid).map(integer -> integer - mid).reduce((a, b) -> a + b).orElse(0L);


            if(cuttingSize < DesiredTreeLength) {
                high = mid-1;
            }
            else {
                low = mid+1;
            }

        }
        return high;
    }

    private static void sorting(ArrayList<Integer> trees) {
        trees.sort(Comparator.naturalOrder());
    }

    private static List<Integer> enterTrees() throws IOException {
        return Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static int enterDesiredTreeLength() throws IOException {
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        return inputs[1];
    }
}
