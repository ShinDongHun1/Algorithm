package bfs와dfs;

import annotation.BOJ;
import annotation.BaekjoonTier;
import annotation.SolveDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@BOJ(   number = 2178,
        tier = BaekjoonTier.SILVER_I,
        solveDate = @SolveDate(year = 2022, month = 2, day = 7))
public class 백준2178 {


    public static void main(String[] args) throws IOException {

        Maze maze = setMazeByInputs();//입력 받아서 Maze 생성

        Runner runner = Runner.getDefaultRunner();

        int resultCount = maze.escape(runner);

        System.out.println(resultCount);
    }


    private static Maze setMazeByInputs() throws IOException {
        try (   BufferedReader br = new BufferedReader(new InputStreamReader(System.in))    ){

            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            final int COLUMN_SIZE = inputs[0];
            final int ROW_SIZE = inputs[1];
            ArrayList<ArrayList<Integer>> temp = new ArrayList<>();

            for(int i =0; i< COLUMN_SIZE; i++){
                temp.add(new ArrayList<>(Arrays.stream(br.readLine().split("")).map(Integer::parseInt).collect(Collectors.toList())));
            }

            return new Maze(temp);
        }

    }
}



class Maze {

    private ArrayList<ArrayList<Integer>> map;
    private final int ROW_SIZE;
    private final int COLUMN_SIZE;

    public Maze(ArrayList<ArrayList<Integer>> map) {
        this.map = map;
        this.ROW_SIZE = map.get(0).size();
        this.COLUMN_SIZE = map.size();
    }


    public ArrayList<ArrayList<Integer>> getMap() {
        return map;
    }

    public int escape(Runner runner){
        int result = 1;

        Deque<Position> deque = new ArrayDeque<>();

        deque.add(runner.getPosition());

        while (!deque.isEmpty()){
            Position current = deque.poll();

            List<Position> nextPositions = current.getNextPositions();
            nextPositions.stream().filter(this::isMovable).forEach(toBePos -> addCountAndEnque(current, toBePos,  deque));

        }

        return map.get(COLUMN_SIZE-1).get(ROW_SIZE-1);
    }

    private boolean isMovable(Position position){
        int x = position.getX();
        int y = position.getY();
        return (x > -1 && y > -1) && ( y < map.size() && x < map.get(0).size()) && (map.get(y).get(x) == 1);
    }

    private void addCountAndEnque(Position isAsPos, Position toBePos, Deque<Position> deque){

        Integer isAsCount = map.get(isAsPos.getY()).get(isAsPos.getX());
        map.get(toBePos.getY()).set(toBePos.getX(), ++isAsCount);
        deque.offer(toBePos);
    }

}


class Runner {
    private Position position;

    public Runner(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
    public static Runner getDefaultRunner(){
        return new Runner(new Position(0, 0));
    }



}
class Position {
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
    public Position getRight(){
        return new Position(x+1, y);
    }
    public Position getLeft(){
        return new Position(x-1, y);
    }
    public Position getUp(){
        return new Position(x, y-1);
    }
    public Position getDown(){
        return new Position(x, y+1);
    }

    public List<Position> getNextPositions(){
        List<Position> result = new ArrayList<>();

        result.add(getLeft());
        result.add(getRight());
        result.add(getUp());
        result.add(getDown());
        return result;
    }
}
