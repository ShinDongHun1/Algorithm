package bfs와dfs;


import gitrepourlparser.AbsolutePathBeforePackageNameParser;
import readmegenerator.BOJReadmeGenerator;

public class RMGenerator {

    public static void main(String[] args) {
        AbsolutePathBeforePackageNameParser parser = new AbsolutePathBeforePackageNameParser();

        parser.setAbsolutePathBeforePackageName("https://github.com/ShinDongHun1/Algorithm/blob/main/BOJ/src");// 마지마 /를 넣어도 되고 빼도 되나 빼는게 이쁨 ^^

        BOJReadmeGenerator rg = new BOJReadmeGenerator(parser);
        rg.setTitle("동훈이의 백준 풀이");

        rg.generate();
    }
}
