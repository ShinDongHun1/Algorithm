package 백트래킹;

import annotation.boj.BOJ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by ShinD on 2022-02-28.
 */
@BOJ
public class 백준15650 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        MAX_INT = inputs[0];
        SIZE = inputs[1];



        dfs( 1, 0);



    }
    public static int MAX_INT;
    public static int SIZE;
    public static ArrayList<Integer> arr = new ArrayList<>();


    public static void dfs(int now, int depth){

        if(depth == SIZE){
            arr.forEach(i -> System.out.print(i + " "));
            System.out.println();
            return;
        }

        for(int i = now; i <= MAX_INT; i ++){
            arr.add(depth, i);
            dfs(i+1, depth+1);
            arr.remove(depth);//depth번째 인덱스의 원소 제거
        }


    }
}
