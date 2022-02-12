package bfs와dfs;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@BOJ
public class 백준1920 {

    static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {


        int N = Integer.parseInt(br.readLine());
        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(array);

        int M = Integer.parseInt(br.readLine());
        int[] searchArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        Arrays.stream(searchArr).forEach(i -> search(array, i));


    }

    /**
     * 이분 탐색 수행
     */
    private static void search(int[] array, int i) {



        System.out.println(biSearch(array,0, array.length-1, i));

    }

    private static int biSearch(int[] array, int start, int end, int search){

        if(start > end){
            return 0;
        }

        int mid = (end+start)/2;

        if(array[mid] > search){
            return biSearch(array, start, mid-1, search);
        }else if(array[mid] < search){
            return biSearch(array, mid+1, end, search);
        }
        else{
            return 1;
        }

    }


}
