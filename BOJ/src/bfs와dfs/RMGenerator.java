package bfs와dfs;


import gitrepourlparser.AbsolutePathBeforePackageNameParser;
import readmegenerator.BOJReadmeGenerator;

public class RMGenerator {

    public static void main(String[] args) {
        AbsolutePathBeforePackageNameParser parser = new AbsolutePathBeforePackageNameParser();

        parser.setAbsolutePathBeforePackageName("https://github.com/ShinDongHun1/Algorithm/blob/main/BOJ/src");

        BOJReadmeGenerator rg = new BOJReadmeGenerator(parser);
        rg.setTitle("동훈이의 백준 풀이");

        rg.generate();
    }
}
