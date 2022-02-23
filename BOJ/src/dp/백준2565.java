package dp;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@BOJ
public class 백준2565 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int powerPoleCount = Integer.parseInt(br.readLine());

        List<PowerPole> powerPoleList = new ArrayList<>();
        for (int i = 0; i < powerPoleCount; i++) {
            powerPoleList.add(new PowerPole(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }


        powerPoleList.sort(Comparator.naturalOrder());



/*
안되는 코드
for(int i = powerPoleCount-1; i >=0; i--){//뒤에서부터 반복
            dp[i]=1; //초기는 무조건 1개
            for(int j = i; j<powerPoleCount; j++){//i부터 시작해서 맨 끝까지 가면서 탐색
                if( powerPoleList.get(j).getEndPoint() < powerPoleList.get(i).getEndPoint()
                    &&
                    dp[i] <= dp[j]
                ){
                    dp[i] = dp[j] + 1;
                }
            }
        }*/
        int dp[] = new int[powerPoleCount];
        for(int i = 0; i < powerPoleCount; i++){
            dp[i]=1; //초기는 무조건 1개

            for(int j = 0; j<i; j++){//0부터 i까지 반복하며 이전에 설치할 수 있는 값으 최대 개수 구하기

                if( powerPoleList.get(j).getEndPoint() < powerPoleList.get(i).getEndPoint() && dp[i] <= dp[j]){
                    dp[i] = dp[j] + 1;
                }
            }
        }

        System.out.println(powerPoleCount- Arrays.stream(dp).max().getAsInt());
    }


    private static class PowerPole implements Comparable<PowerPole> {
        private int startPoint;
        private int endPoint;

        public PowerPole(int[] input) {
            this.startPoint = input[0];
            this.endPoint = input[1];
        }

        public int getStartPoint() {
            return startPoint;
        }

        public int getEndPoint() {
            return endPoint;
        }

        public void setStartPoint(int startPoint) {
            this.startPoint = startPoint;
        }

        public void setEndPoint(int endPoint) {
            this.endPoint = endPoint;
        }

        @Override
        public int compareTo(PowerPole o) {
            return this.startPoint - o.startPoint;
        }
    }
}


