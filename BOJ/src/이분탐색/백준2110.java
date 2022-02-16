package 이분탐색;

import annotation.boj.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@BOJ
public class 백준2110 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int HOME_SIZE;
    static int ROUTER_SIZE;
    static List<Integer> homeList= new ArrayList<>();

    public static void main(String[] args) throws IOException {

        enterHomeSizeAndRouterSize();//집
        enterHomePostion();
        sortHome();
        System.out.println(findingMaxRouterInstallDistance());

    }


    private static long findingMaxRouterInstallDistance() {
        long low = 1; //최소 간격은 1
        long high = homeList.get(HOME_SIZE -1) - homeList.get(0);//최대 간격은 처음 집과 끝 집 사이의 거리

        while (low <= high){
            long mid = (low + high) >>> 1;
            if(install(mid) < ROUTER_SIZE){//설치한 개수가 공유기 크기보다 작다면 -> 간격을 줄여야 함 -> high를 낮춘다

                high = mid-1;
            }else {//설치한 개수가 공유기 크기보다 같거나 크다면 -> 간격을 늘려야 함 -> low를 높인다
                low = mid+1;
            }
        }
        return high;
    }


    private static void sortHome() {
        homeList.sort(Comparator.naturalOrder());
    }


    private static void enterHomePostion() {
        IntStream.range(0, HOME_SIZE).forEach(i ->{
            try {
                homeList.add(Integer.parseInt(br.readLine()));
            } catch (IOException e) {}
        });
    }


    private static void enterHomeSizeAndRouterSize() throws IOException {
        int[] ints = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        HOME_SIZE = ints[0];
        ROUTER_SIZE = ints[1];
    }







    /**
     * 이 문제에서 중요한 점은 이분탐색의 대상이 '공유기를 설치하는 간격' 이 된다는 것이다.
     * 따라서 최소 간격은 1, 최대 간격은 처음 집과 마지막 집 사이의 거리가 되며,
     * 이 둘 사이에서 이분탐색을 수행하며, 해당 간격으로 설치했을 때 공유기를 몇 개 설치할 수 있는지에 대한 연산이 필요하다.
     * 그리고 설치한 공유기의 개수가 주어진 공유기의 개수보다 작다면 설치 간격을 줄여서 공유기 설치 개수를 늘려야 한다.
     * 만약 설치한 공유기 개수가 주어진 공유기의 개수와 같을 때에는, 간격을 더 늘려가며 최대 간격을 찾아내야 한다.
     */
    private static int install(long mid) {

        int result = 1;
        int lastPos = homeList.get(0);

        for(int i=1; i< HOME_SIZE; i++){
            if(homeList.get(i) - lastPos >= mid){
                result++;
                lastPos = homeList.get(i);
            }
        }
        return result;
    }
}
