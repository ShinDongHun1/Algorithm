package bfs와dfs;

import gitrepourlparser.GitRepositoryUrlParser;
import gitrepourlparser.PersonalGitRepositoryUrlParser;
import readmegenerator.BOJReadmeGenerator;
import readmegenerator.ReadmeGenerator;

public class RMGenerator {
    public static void main(String[] args) {
        GitRepositoryUrlParser parser = new PersonalGitRepositoryUrlParser("ShinDongHun1", "BOJ", "main");

        BOJReadmeGenerator rg = new BOJReadmeGenerator(parser);
        rg.setTitle("동훈이의 백준 풀이");

        rg.generate();
    }
}
