package bfs와dfs;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@BOJ
public class 백준10816 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer.parseInt(br.readLine());


        List<Integer> arr =  Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        arr.sort(Comparator.naturalOrder());

        Integer.parseInt(br.readLine());



        List<Integer> collect = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (Integer i : collect) {
            sb.append(upperBound(arr, i) - lowerBound(arr, i)+"\n");
        }

/*        Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList()).forEach( i -> sb.append(upperBound(arr, i) - lowerBound(arr, i)+"\n"));*/

        System.out.println(sb.toString());

    }

    private static int upperBound(List<Integer> arr, Integer i) {
        int low = 0;
        int high = arr.size();


        while (low < high){
            int mid = (high + low) >>> 1;// %2


            /**
             * Key 가 arr[mid]보다 클 때 ->  더 오른쪽 경계를 탐색하도록  low를 늘린다 -> low = mid +1
             * Key 가 arr[mid]와 같을 때 -> 더 오른쪽 경계를 탐색하도록 low를 늘린다 -> low = mid +1
             * Key 가 arr[mid]보다 작을 때 -> 왼쪽 경계를 탐색하도록 high를 줄인다 -> high = mid
             *
             * 만약 값이 존재하지 않는다면?? -> upperBound와 lowerBound가 같은 값을 반환한다 -> 빼면 0이다
             */
            if(i < arr.get(mid) ){ //mid 가 더 클 때
                high = mid;
            }else {
                low = mid+1;
            }
        }

        return low;
    }


    private static int lowerBound(List<Integer> arr, Integer i) {
        int low = 0;
        int high = arr.size();

        while (low < high){
            int mid = (high + low) >>> 1;// %2


            /**
             * Key 가 arr[mid]보다 클 때 ->  더 오른쪽 부분을 탐색하도록 low를 높인다 -> low = mid +1
             * Key 가 arr[mid]와 같을 때 -> 더 왼쪽 경계를 탐색하도록 high를 낮춘다 -> high = mid
             * Key 가 arr[mid]보다 작을 때 -> 왼쪽 경계를 탐색하도록 high를 낮춘다 ->high = mid
             */
            if(i <= arr.get(mid)){
                high = mid;
            }else {
                low = mid + 1;
            }
        }

        return low;
    }

}
