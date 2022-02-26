package dp;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ShinD on 2022-02-26.
 */
@BOJ
public class 백준11054 {


    static int[] r_dp;
    static int[] l_dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        r_dp= new int[arr.length];
        l_dp= new int[arr.length];

        int [] l_arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            l_arr[i] = arr[arr.length-1-i];
        }
        LIS(arr, r_dp);
        LIS(l_arr, l_dp);

        int max= 0;
        for(int i=0; i< arr.length; i++){
            max= Math.max(max, r_dp[i] + l_dp[arr.length-1-i]);
        }

        System.out.println(max-1);


    }


    public static int LIS(int[] arr, int[] dp){
        dp[0] = 1;

        for(int i =1; i< arr.length; i++){
            dp[i]= 1;//부분 수열의 길이


            for(int k = 0; k < i; k++){
                if(arr[i] > arr[k]){//   1   2 인 경우
                    dp[i] = Math.max(dp[k]+1, dp[i]);
                }
            }
        }



        return 0;
    }

}
