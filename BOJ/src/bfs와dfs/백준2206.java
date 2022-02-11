package bfs와dfs;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

@BOJ
public class 백준2206 {
    public static void main(String[] args) throws IOException, IOException {

        /**
         * 입력을 받아서 Row와 Column 사이즈를 지정한다.
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int COL_SIZE = inputs[1];
        int ROW_SIZE = inputs[0];


        /**
         * Map 정보를 입력받는다
         */
        ArrayList<ArrayList<Integer>> map = new ArrayList<>(new ArrayList<>());
        for(int i =0; i< ROW_SIZE; i++){
            map.add(new ArrayList(Arrays.stream(br.readLine().split(""))
                    .map((String s) ->{
                        if(s.equals("1")) return null;
                        return 0;})
                    .collect(Collectors.toList())));
        }

        /**
         * map을 방문했는지 체크한 결과를 가진 배열을 생성한다.
         */
        boolean[][][] visited = new boolean[ROW_SIZE][COL_SIZE][2];
        int[] dy = {0,0,-1,1};
        int[] dx = {-1,1,0,0};

        Deque<Location> deque = new ArrayDeque<>();
        deque.offer(Location.of(1,0,0,false));
        visited[0][0][0]=false;


        while (!deque.isEmpty()){
            Location current = deque.poll();

            int X = current.getX();
            int Y = current.getY();

            if(X == COL_SIZE-1 && Y == ROW_SIZE-1){
                System.out.println(current.getCount());
                return;
            }

            boolean destroyWall = current.isDestroyWall();
            int destroyed = destroyWall ? 1 : 0;


            for(int i=0; i<4; i++){
                int nx = X + dx[i];
                int ny = Y + dy[i];


                if(nx < 0 || ny < 0 || nx >= COL_SIZE || ny >= ROW_SIZE) continue;

                int nextCount = current.getCount()+1;
                if(map.get(ny).get(nx) != null) {//벽이 아니면
                    if (  visited[ny][nx][destroyed]) continue;

                    deque.offer(Location.of(current.getCount() + 1, nx, ny, destroyWall));
                    visited[ny][nx][destroyed] = true;
                }else{
                    if (!destroyWall){
                        deque.offer(Location.of(current.getCount() + 1, nx, ny, true));
                        visited[ny][nx][1] = true;
                    }
                }
            }


        }
        System.out.println("-1");
    }


    private static class Location {
        private int count;
        private int x;
        private int y;
        private boolean isDestroyWall;

        private Location(int count, int x, int y, boolean isDestroyWall) {
            this.count = count;
            this.x = x;
            this.y = y;
            this.isDestroyWall = isDestroyWall;
        }
        public static Location of(int count, int x, int y, boolean isDestroyWall){
            return new Location(count,x,y,isDestroyWall);
        }

        public int getCount() {
            return count;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean isDestroyWall() {
            return isDestroyWall;
        }
    }
}
