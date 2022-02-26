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
        int start = 0;
        int end = arr.size()-1;

        while (start < end) {
            int mid = (start + end) >>> 1;


            if (arr.get(mid) <= i)//같으면 start를 오른쪽
                start = mid + 1;
            else
                end = mid;

        }
        return start;//or end
    }

    private static int lowerBound(List<Integer> arr, int key) {
        int start = 0;
        int end = arr.size()-1;

//초과하는 값
        while (start <= end) {
            int mid = (start + end) >>> 1;


            if (arr.get(mid) < key) //중간값이 원하는 값보다 작을 경우,중간값이 커저야 하므로 start를 늘린다
                start = mid + 1;
            else //만약 중간값과 같다면? -> 더 좌측 범위를 탐색하기 위해 end를 줄인다.
                end = mid - 1;

        }
        return end+1;//or start
    }

}
