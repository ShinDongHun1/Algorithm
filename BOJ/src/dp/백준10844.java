package dp;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;


@BOJ
public class 백준10844 {

    private static BigInteger[][] dp = new BigInteger[101][10];//0~9

    static {
        dp[1][0]= BigInteger.ZERO;


        for(int i = 1; i< 10; i++){
            dp[1][i] = BigInteger.ONE;
        }

        for(int i = 2; i <= 100; i++){
            for(int k =0; k < 10; k++) {
                switch (k) {
                    case 0 -> dp[i][k] = dp[i - 1][1];//0일때와 9일때는 가능성이 1개밖에 없음
                    case 9 -> dp[i][k] = dp[i - 1][8];
                    default -> dp[i][k] = dp[i - 1][k - 1].add(dp[i - 1][k + 1]);
                }
            }

        }


    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int i = Integer.parseInt(br.readLine());
        BigInteger result = BigInteger.ZERO;
        for(int k =0; k<10; k++){
            result = result.add(dp[i][k]);
        }
        System.out.println(result.remainder(BigInteger.valueOf(1000000000)));
    }

}
