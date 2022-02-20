package dp;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@BOJ
public class 백준11053 {

    private static int[] dp;
    private static int[] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new int[Integer.parseInt(br.readLine())];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        /**
         * 만약 이전 수가 현재 수보다 작다면? -> +1
         * 만약 이전 수가 현재 수와 같다면? -> 이전 수 그대로
         * 만약 이전 수가 현재 수보다 크다면? -> 이전의 이전 수..... 0까지
         */



        for(int i=0; i< arr.length; i++){
            dp[i]=1;
            for(int j=0; j<i; j++){
                //만약 arr[i]보다 arr[j]가 작으면서, dp[i]보다 dp[j]가 같거나 크다면
                if((arr[i] > arr[j])  && (dp[i] <= dp[j] )){
                    dp[i] = dp[j] + 1;
                }
            }
        }


        System.out.println(Arrays.stream(dp).max().getAsInt());



    }



}
