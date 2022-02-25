package dp;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ShinD on 2022-02-25.
 */
@BOJ
public class 백준12015 {

    private static List<Integer> dp= new ArrayList<>();
    private static List<Integer> arr;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        arr = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());



        /**
         * 증가하는 부분 수열
         * https://4legs-study.tistory.com/106
         */
        int size = sizeLIS();

        System.out.println(size);



    }


    private static int sizeLIS() {
        dp.add(arr.get(0));



        for(int i =1; i< arr.size(); i++){

            if(dp.get(dp.size()-1) < arr.get(i)){
                dp.add(arr.get(i));
            }
            else{

                dp.set(biSearch( arr.get(i)), arr.get(i));
            }
        }

        return dp.size();
    }

    private static int biSearch(int target) {
        int start = 0;
        int end = dp.size()-1;

        while (start < end){

            int mid = (start + end) >>>1;

            if(dp.get(mid) >= target) {//타겟보다 같거나 크면 end를 줄이고, 작으면 start를 늘린다
                end = mid;
            }else {
                start = mid+1;
            }
        }
        return end;

    }


}
