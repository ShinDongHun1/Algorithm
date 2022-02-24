package dp;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * LCS 문제
 * https://www.crocus.co.kr/787
 */
@BOJ
public class 백준9251 {

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(StrUtils.sizeForLCSubsequence(br.readLine(), br.readLine()));


    }



    private static class StrUtils{

        public static int sizeForLCSubsequence(String standardStr, String comparedStr) {//최장 공통 부분 수열
            int[][] dp = executeLCSAndGetArr(standardStr, comparedStr);

            return dp[comparedStr.length()][standardStr.length()];
        }



        public static String getLCSubsequence(String standardStr, String comparedStr) {//최장 공통 부분 수열

            int[][] dp = executeLCSAndGetArr(standardStr, comparedStr);

            StringBuilder sb = new StringBuilder();
            int i = comparedStr.length();
            int j = standardStr.length();
            while (i > 0 && j > 0) {

                int now = dp[i][j];

                if(dp[i-1][j] == now) i--;

                else if(now == dp[i][j-1]) j--;

                else if(now-1 == dp[i-1][j-1]) {
                    sb.append(comparedStr.charAt(i-1));
                    i--;
                    j--;
                }
                else throw new IllegalStateException("이거뜨면 ㅈㅈ");
            }


            return sb.reverse().toString();
        }



        private static int[][] executeLCSAndGetArr(String stdStr, String cpStr) {
            int [][] dp = new int[cpStr.length()+1][stdStr.length()+1];

            for (int i = 1; i <= cpStr.length(); i++) {
                for (int j = 1; j <= stdStr.length(); j++) {
                    dp[i][j] = (Character.compare(cpStr.charAt(i - 1), stdStr.charAt(j - 1)) == 0)
                                    ? dp[i - 1][j - 1] + 1
                                    : Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            return dp;
        }

    }
}


