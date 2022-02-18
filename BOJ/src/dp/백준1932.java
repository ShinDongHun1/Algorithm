package dp;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@BOJ
public class 백준1932 {

    private static int[][] dp;
    private static int[][] triangle;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp = new int[N+1][N+1];
        triangle = new int[N+1][N+1];


        for (int i = 1; i <= N; i++) {
            final int[] ints = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1;  j<= ints.length; j++) {
                triangle[i][j]=ints[j-1];
                if(i==N) {
                    dp[i][j] = ints[j - 1];
                }

            }
        }

        for (int i = N-1; i > 0; i--) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = triangle[i][j]+Math.max(dp[i+1][j], dp[i+1][j+1]);
            }
        }

        System.out.println(dp[1][1]);

    }
}
