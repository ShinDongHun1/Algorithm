package bfs와dfs;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

@BOJ
public class 백준7562 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int TEST_SIZE = Integer.parseInt(br.readLine());
        for (int i =0; i< TEST_SIZE; i++){

            final int CHESS_SIZE = Integer.parseInt(br.readLine());
            Chess chess = Chess.from(CHESS_SIZE);
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int knigtX = s[0];
            int knigtY = s[1];

            s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int goalX = s[0];
            int goalY = s[1];

            int moveCountTo = chess.getMoveCountTo(knigtX, knigtY, goalX, goalY);
            System.out.println(moveCountTo);
        }

    }

    private static class Chess{
        private ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        private ArrayList<ArrayList<Boolean>> visitedMap = new ArrayList<>();

        public Chess(final int SIZE) {
            IntStream.range(0,SIZE).forEach(i -> {
                ArrayList<Integer> temp = new ArrayList<>();
                ArrayList<Boolean> vTemp = new ArrayList<>();
                IntStream.range(0, SIZE).forEach(j -> {temp.add(0);vTemp.add(Boolean.FALSE);});
                map.add(temp);
                visitedMap.add(vTemp);
            });
        }

        public static Chess from(final int SIZE){
            return new Chess(SIZE);
        }

        public int getMoveCountTo(int x, int y, int goalX, int goalY){
            visitedMap.get(y).set(x, Boolean.TRUE);
            map.get(y).set(x,0);

            Deque<Position> deque = new ArrayDeque<>();
            deque.add(new Position(x,y));

            while (!deque.isEmpty()){
                Position current = deque.poll();
                int currentX = current.getX();
                int currentY = current.getY();

                if (currentX == goalX && currentY == goalY){
                    return map.get(currentY).get(currentX);
                }

                /**
                 * 움직일 수 있는 위치인지?
                 * 방문한 적이 없는지?
                 */
                List<Position> positions = current.movablePosition();
                positions.stream().filter(this::isMovable).filter(this::isNotVisited)
                        .forEach(position -> {
                            visitedMap.get(position.getY()).set(position.getX(), Boolean.TRUE);
                            map.get(position.getY()).set(position.getX(), map.get(currentY).get(currentX) + 1);
                            deque.offer(position);
                        });
            }
            return -1;
        }

        public boolean isMovable(Position position){
            int x = position.getX();
            int y = position.getY();
            return (x > -1 && y > -1) && (y < map.size() && x < map.size());
        }
        public boolean isNotVisited(Position position){
            return !visitedMap.get(position.getY()).get(position.getX());
        }
    }
    private static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public List<Position> movablePosition() {
            List<Position> result = new ArrayList<>();
            result.add(new Position(x+2,y+1));
            result.add(new Position(x+2,y-1));
            result.add(new Position(x+1,y+2));
            result.add(new Position(x+1,y-2));
            result.add(new Position(x-2,y+1));
            result.add(new Position(x-2,y-1));
            result.add(new Position(x-1,y+2));
            result.add(new Position(x-1,y-2));
            return result;
        }
    }

}



