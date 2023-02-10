package S2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_7507 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 1) Test Case
        int n = Integer.parseInt(br.readLine());
        for (int t = 1; t <= n; t++) {
            // 2) Input
            int m = Integer.parseInt(br.readLine());

            Game[] games = new Game[m];

            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                games[i] = new Game(Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken());
            }

            // 3) Sort
            Arrays.sort(games);

            // 4) Greedy
            int answer = 1;
            Game game = games[0];
            for (int i = 1; i < m; i++) {
                if (game.day != games[i].day || game.end.compareTo(games[i].start) <= 0) {
                    game = games[i];
                    answer++;
                }
            }

            // 5) Output
            sb.append(String.format("Scenario #%d:\n%d\n\n", t, answer));
//            print(games, dp);
        }
        System.out.println(sb);
    }

    private static class Game implements Comparable<Game> {
        int day;
        String start;
        String end;

        public Game(int day, String start, String end) {
            this.day = day;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Game o) {
            if (this.day != o.day)
                return this.day - o.day;
            return this.end.compareTo(o.end);
        }
    }

    // 시간초과 DP 풀이 //
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//
//        // 1) Test Case
//        int n = Integer.parseInt(br.readLine());
//        for (int t = 1; t <= n; t++) {
//            // 2) Input
//            int m = Integer.parseInt(br.readLine());
//            int[] dp = new int[m];
//            dp[0] = 1;
//
//            Game[] games = new Game[m];
//
//            for (int i = 0; i < m; i++) {
//                StringTokenizer st = new StringTokenizer(br.readLine());
//                games[i] = new Game(Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken());
//            }
//
//            // 3) Sort
//            Arrays.sort(games);
//
//            // 4) dp
//            int max = 0;
//            for (int i = 1; i < m; i++) {
//                dp[i] = find_Before_Game(games, dp, i);
//                max = Math.max(max, dp[i]);
//            }
//
//            // 5) Output
//            sb.append(String.format("Scenario #%d:\n%d\n\n", t, max));
////            print(games, dp);
//        }
//        System.out.println(sb);
//    }
//
//    private static int find_Before_Game(Game[] games, int[] dp, int now) {
//        for (int i = now - 1; i >= 0; i--) {
//            if (games[now].day > games[i].day ||
//                    games[now].start.compareTo(games[i].end) >= 0) {
//                return dp[i] + 1;
//            }
//        }
//        return 1;
//    }
//
//
//    private static class Game implements Comparable<Game> {
//        int day;
//        String start;
//        String end;
//
//        public Game(int day, String start, String end) {
//            this.day = day;
//            this.start = start;
//            this.end = end;
//        }
//
//        @Override
//        public int compareTo(Game o) {
//            if (this.day != o.day)
//                return this.day - o.day;
//            return this.start.compareTo(o.start);
//        }
//    }
//
//    private static void print(Game[] games, int[] dp) {
//        System.out.println("\n=============================\n");
//        System.out.println("\n# n : day\tstart\tend \tdp");
//        for (int i = 0; i < games.length; i++) {
//            System.out.printf("# %d : %d \t%s\t%s\t%d\n",
//                    i, games[i].day, games[i].start, games[i].end, dp[i]);
//        }
//        System.out.println("\n=============================\n");
//    }
}