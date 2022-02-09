package bfs와dfs;

import annotation.BOJ;
import annotation.BaekjoonTier;
import annotation.SolveDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

@BOJ(   number = 1697,
        tier = BaekjoonTier.SILVER_I,
        solveDate = @SolveDate(year = 2022, month = 2, day = 9))
public class 백준1697 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int tagger = inputs[0];
        int hider = inputs[1];
        HideMap hideMap = new HideMap();
        System.out.println(hideMap.play(tagger, hider));


    }




    public static class HideMap {

        private ArrayList<Integer> map = new ArrayList<>();
        private ArrayList<Boolean> isVisited = new ArrayList<>();

        public HideMap(){
            IntStream.rangeClosed(0,100000).forEach(i -> {
                map.add(0);
                isVisited.add(Boolean.FALSE);
            });
        }


        public int play(int tagger, int hider){

            if(tagger == hider) return 0;

            isVisited.set(tagger, Boolean.TRUE);


            Deque<Integer> deque = new ArrayDeque<>();

            deque.add(tagger);
            isVisited.set(tagger, Boolean.TRUE);

            while (!deque.isEmpty()){
                Integer current = deque.poll();
                if(current == hider) return map.get(current);

                checkAndEnque(deque, current,current+1);
                checkAndEnque(deque, current,current-1);
                checkAndEnque(deque, current,current * 2);


            }
            return -1;
        }

        private void checkAndEnque(Deque<Integer> deque, Integer isAs,Integer toBe) {
            if(toBe > -1 && toBe <= 100000 && !isVisited.get(toBe)) {

                map.set(toBe, map.get(isAs) + 1);
                isVisited.set(toBe, Boolean.TRUE);
                deque.offer(toBe);
            }
        }
    }







}
