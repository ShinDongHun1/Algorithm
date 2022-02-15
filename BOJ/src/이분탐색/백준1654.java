package 이분탐색;


import annotation.SolveDate;
import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@BOJ
public class 백준1654 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        /**
         * 랜선의 길이와 필요한 랜선의 개수를 입력받는다
         */
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        LanCuttingMachine lanCuttingMachine = LanCuttingMachine.of(inputs[0]);
        int SIZE = inputs[1];


        /**
         * 각 랜선의 길이를 입력받는다
         */
        List<Integer> lanArr = new ArrayList<>();
        for (int i = 0; i < lanCuttingMachine.getLAN(); i++) {
            lanArr.add(Integer.parseInt(br.readLine()));
        }
        lanArr.sort(Comparator.naturalOrder());
        lanCuttingMachine.setLanArr(lanArr);




        System.out.println(lanCuttingMachine.cutting(SIZE));

    }



    private static class LanCuttingMachine{
        private int LAN;
        private List<Integer> lanArr = new ArrayList<>();

        private LanCuttingMachine(int LAN) {
            this.LAN = LAN;
        }

        public static LanCuttingMachine of(final int LAN){
            return new LanCuttingMachine(LAN);
        }

        public void setLanArr(List<Integer> lanArr) {
            this.lanArr = lanArr;
        }

        public int getLAN() {
            return LAN;
        }

        private long cutting(int SIZE) {

            long low = 1;

            long high =lanArr.get(LAN-1);
            //long high =lanArr.get(LAN-1);
            //high ++ ;




            long mid = 0;

            while (low <= high){

                mid = (low + high) / 2;
                /**
                 *  만약 중간 길이로 각 랜선을 나눈 길이가 GOAL보다 작다 -> high를 낮춘다.
                 *  만약 중간 길이로 각 랜선을 나눈 길이가 GOAL보다 크다 -> low를 높인다.
                 *  만약 중간 길이로 각 랜선을 나눈 길이가 GOAL과 같다 -> low를 늘려서 우측 값을 탐험하게 만든다. -> 처음으로 GOAL보다 작은 값이 나오면 그 값 -1
                 */
                long sum = 0;

                for (Integer lan : lanArr) {
                    sum += (lan/mid);
                }

                if(sum < SIZE){
                    high = mid-1;
                }
                else{
                    low = mid + 1;
                }
            }
            return high;
        }
    }
}

