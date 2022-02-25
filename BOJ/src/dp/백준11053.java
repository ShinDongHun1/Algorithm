package dp;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//https://gom20.tistory.com/91?category=1032507
@BOJ
public class 백준11053 {

    private static int[] dp;
    private static int[] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new int[Integer.parseInt(br.readLine())];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();




        for(int i=0; i< arr.length; i++){

            dp[i]=1;

            for(int j=0; j<i; j++){
                //만약 arr[i]보다 arr[j]가 작으면서, dp[i]보다 dp[j]가 크다면
                if((arr[i] > arr[j])  && (dp[i] <= dp[j])){
                    dp[i] = dp[j] + 1;
                }
            }
        }




        System.out.println(Arrays.stream(dp).max().getAsInt());



    }



}
