package bfs와dfs;


import annotation.SolveDate;
import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@BOJ
public class 백준7569 {

    public static void main(String[] args) throws Exception {
        Store<Tomato> tomatoStore = setInputAndGetStore();

        System.out.println(tomatoStore.getAllRipeDay());

    }
    public static Store<Tomato> setInputAndGetStore() throws Exception{
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int[] ints = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x = ints[0];//가로 col
            int y = ints[1];//세로 row
            int z = ints[2];

            ArrayList<ArrayList<ArrayList<Tomato>>> store = new ArrayList<>();

            for (int i = 0; i < z; i++) {

                store.add(new ArrayList<>());

                for (int j = 0; j < y; j++) {

                    store.get(i).add(new ArrayList<>());
                    String[] str = br.readLine().split(" ");

                    for (int k = 0; k < x; k++) {

                        store.get(i).get(j).add(Tomato.parseTomato(str[k], k, j, i));

                    }
                }
            }


            return new Store<Tomato>(store, x, y, z);
        }
    }



    private static class Store<T extends Tomato> {
        private ArrayList<ArrayList<ArrayList<T>>> store = new ArrayList<>(new ArrayList<>());
        private final int X_SIZE;
        private final int Y_SIZE;
        private final int Z_SIZE;

        public Store (ArrayList<ArrayList<ArrayList<T>>> store, int x_SIZE, int y_SIZE, int z_SIZE) {
            this.store = store;
            X_SIZE = x_SIZE;
            Y_SIZE = y_SIZE;
            Z_SIZE = z_SIZE;
        }

        public int getAllRipeDay(){

            Deque<T> deque = new ArrayDeque<>();
            IntStream.range(0, Z_SIZE).forEach(z -> {
                IntStream.range(0, Y_SIZE).forEach(y ->{
                    IntStream.range(0, X_SIZE).forEach(x ->{
                        T t = store.get(z).get(y).get(x);
                        if( t != null && t.isRiped()) {
                            deque.offer(store.get(z).get(y).get(x));
                        }
                    });
                });
            });
            riping(deque);



            int max = 0;
            for (ArrayList<ArrayList<T>> box : store) {
                for (ArrayList<T> line : box) {
                    for (T t : line) {
                        if(t == null) continue;
                        if(!t.isRiped())return -1;
                        if(t.getRipeDay() > max){
                            max = t.getRipeDay();
                        }
                    }
                }
            }
            return max;

        }


        private void riping(Deque<T> deque) {


            while (!deque.isEmpty()){
                T current = deque.poll();


                current.getAroundPosition().stream().filter(this::isMovable)
                        .map(this::getOptByPosition)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .filter(t1 -> !t1.isRiped())
                        .forEach(toBe -> setRipeDayAndEnque(current, toBe, deque));
            }

        }

        private int plusOne(int i) {
            return ++i;
        }

        private boolean isMovable(Position position){
            int x = position.getX();
            int y = position.getY();
            int z = position.getZ();

            return (x > -1) && (y > -1) && (z > -1) &&
                    (z < Z_SIZE) &&
                    (y < Y_SIZE) &&
                    (x < X_SIZE);
        }
        private Optional<T> getOptByPosition(Position position){

            return Optional.ofNullable(store.get(position.getZ()).get(position.getY()).get(position.getX()));
        }
        private void setRipeDayAndEnque(T isAs, T toBe, Deque<T> deque){

            toBe.riping();
            toBe.setRipeDay(isAs.getRipeDay() + 1);
            deque.offer(toBe);
        }



    }



    private static class Tomato implements Comparable<Tomato>{
        private Position position;

        private boolean isRiped;

        private int ripeDay;

        private Tomato(Position position) {
            this.position = position;
        }
        public static Tomato fromRiped(Position position){
            Tomato tomato = new Tomato(position);
            tomato.riping();
            return tomato;
        }
        public static Tomato fromNoneRiped(Position position){
            return new Tomato(position);
        }


        public Position getPosition() {
            return position;
        }

        public boolean isRiped() {
            return isRiped;
        }

        public int getRipeDay() {
            return ripeDay;
        }

        public void riping() {
            isRiped = true;
        }

        public void setRipeDay(int ripeDay) {
            this.ripeDay = ripeDay;
        }

        public List<Position> getAroundPosition(){
            List<Position> result = new ArrayList<>();
            int currentX = position.getX();
            int currentY = position.getY();
            int currentZ = position.getZ();

            result.add(Position.of(currentX+1,currentY,currentZ));
            result.add(Position.of(currentX-1,currentY,currentZ));
            result.add(Position.of(currentX,currentY+1,currentZ));
            result.add(Position.of(currentX,currentY-1,currentZ));
            result.add(Position.of(currentX,currentY,currentZ+1));
            result.add(Position.of(currentX,currentY,currentZ-1));


            return result;
        }

        @Override
        public int compareTo(Tomato o) {
            return ripeDay - o.getRipeDay();
        }

        public static Tomato parseTomato(String str, int x,  int y, int z) {
            return switch (str){
                case "1" -> Tomato.fromRiped(Position.of(x,y,z));
                case "0" -> Tomato.fromNoneRiped(Position.of(x,y,z));
                default -> null;
            };
        }
    }

    private static class Position {
        private int x;
        private int y;
        private int z;

        private Position(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        private static Position of(int x, int y, int z){
            return new Position(x, y, z);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }
    }
}


