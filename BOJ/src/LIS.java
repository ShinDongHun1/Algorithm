import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ShinD on 2022-02-26.
 */
public class LIS {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        List<Integer> arr = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());


        System.out.println(lisByBiSearch(arr));


    }




    public static int lisByDp(List<Integer> arr){
        List<Integer> lisSize = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {//0부터 수열의 끝까지 차례대로 진행한다.
            lisSize.add(1);//처음에는 자기 자신만 포함될 것이므로 크기는 1로 초기화한다.


            /**
             * 수열의 처음부터 현재 인덱스(i) 전까지 탐색을 진행하며
             */
            for(int j =0; j < i; j++){


                /**
                 * 만약 수열의 현재 인덱스(i)번째 수가 그 이전 인덱스(j)의 수보다 크다면
                 * (ex: arr.get(j)는 2이고, arr.get(i)는 4인 그런 상황)
                 */
                if(arr.get(j) < arr.get(i)){

                    /**
                     * 만약 j 인덱스의 최장 부분수열에 값에 1을 더한 길이와,
                     * 현재 저장된 최장 부분수열의 길이를 비교하며
                     * 더 큰 값을 현재 인덱스(i)의 최장 부분수열로 저장한다.
                     */
                    lisSize.set(i, Math.max(lisSize.get(i), lisSize.get(j)+1));
                }
            }
        }


        /**
         * lisSize(인덱스별 최장 부분수열의 길이를 저장한 List) 의 최댓값을 구해서 반환
         */
        return lisSize.stream().max(Integer::compareTo).get();
    }



    public static int lisByBiSearch(List<Integer> arr){//arr은 입력받은 문자열
        List<Integer> lisInt = new ArrayList<>();

        lisInt.add(arr.get(0));//첫번째 수를 넣어둔다

        for (int i = 1; i < arr.size(); i++) {

            if(arr.get(i) > lisInt.get(lisInt.size()-1)){//만약 맨 뒤의 수보다 더 큰 수라면 마지막 위치에 추가
                lisInt.add(arr.get(i));
            }
            else {
                binarySearch(lisInt, arr.get(i));
            }

        }

        return lisInt.size();
    }


    public static void binarySearch(List<Integer> lisInt, int target){

        //탐색 범위는 처음부터 lisInt(증가하는 부분수열을 담아둔 배열)의 끝까지
        int start = 0;
        int end = lisInt.size()-1;

        while (start <= end){
            int mid = start + ((end - start) >>> 1); //나누기 2


            if(lisInt.get(mid) == target){
                return;
            }

            if(lisInt.get(mid) > target){//중간값이 더 크다? -> 범위를 더 작게
                end = mid-1;
            }

            if(lisInt.get(mid) < target){//중간값이 더 작다? -> 범위를 더 크게
                start = mid+1;
            }
        }
        lisInt.set(start, target);
    }
}
