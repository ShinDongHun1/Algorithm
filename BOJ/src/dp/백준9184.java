package dp;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

@BOJ
public class 백준9184 {


    private static long[][][] dp = new long[21][21][21];
    private static StringBuilder sb = new StringBuilder();
    static {



        IntStream.range(0,21).forEach(i -> {
            IntStream.range(0,21).forEach(j -> {
                IntStream.range(0,21).forEach(k -> {
                    dp[i][j][k]=1L;
                });
            });
        });

        IntStream.range(0,21).forEach(i -> {
            IntStream.range(0,21).forEach(j -> {
                IntStream.range(0,21).forEach(k -> {
                    setDp(i,j,k);
                });
            });
        });

    }

    private static void setDp(int a, int b, int c) {
        if(a == 0 || b == 0 || c == 0){
            dp[a][b][c]=1;
            return;
        }

        if (a > 20 || b > 20 || c > 20) {
            dp[a][b][c] = dp[20][20][20];
            return;
        }
        if (a < b && b < c) {
            dp[a][b][c] = dp[a][b][c - 1] + dp[a][b - 1][c - 1] - dp[a][b - 1][c];
            return;
        }
        dp[a][b][c] = dp[a - 1][b][c] + dp[a - 1][b - 1][c] + dp[a - 1][b][c - 1] - dp[a - 1][b - 1][c - 1];
        return;



    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = br.readLine()) != null){

            int[] ints = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            if(Arrays.stream(ints).allMatch(i -> i == -1)){
                System.out.println(sb.toString());
                return;
            }

            if(Arrays.stream(ints).filter(i -> i<=0).findAny().isPresent()){
                soutFormat(ints[0],ints[1],ints[2], 1l);
                continue;
            }

            if(Arrays.stream(ints).filter(i -> i>20).findAny().isPresent()){
                soutFormat(ints[0],ints[1],ints[2], dp[20][20][20]);
                continue;
            }

            soutFormat(ints[0],ints[1],ints[2], dp[ints[0]][ints[1]][ints[2]]);
        }

    }



    public static void soutFormat(int a,int b,int c, long result){
        sb.append(String.format("w(%d, %d, %d) = %d",a,b,c,result)+"\n");
    }

}
