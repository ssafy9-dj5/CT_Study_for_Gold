import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main { // dfs 풀이
    static int[] numbers, cards;
    static int N, ansMax, ansMin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        cards = new int[4];      // 연산자는 숫자보다 한 개 적음

        ansMax = Integer.MIN_VALUE;
        ansMin = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

//        dfs(0, numbers[0]);
        dfs2(0, numbers[0], cards[0], cards[1], cards[2], cards[3]);

        System.out.println(ansMax);
        System.out.println(ansMin);
    }

    private static void dfs(int idx, int tmp) {
        if (idx == N - 1) {
            ansMax = Math.max(tmp, ansMax);
            ansMin = Math.min(tmp, ansMin);

            return;
        }

        if (cards[0] > 0) {
            cards[0]--;
            dfs(idx + 1, tmp + numbers[idx + 1]);
            cards[0]++;
        }
        if (cards[1] > 0) {
            cards[1]--;
            dfs(idx + 1, tmp - numbers[idx + 1]);
            cards[1]++;
        }
        if (cards[2] > 0) {
            cards[2]--;
            dfs(idx + 1, tmp * numbers[idx + 1]);
            cards[2]++;
        }
        if (cards[3] > 0) {
            cards[3]--;
            dfs(idx + 1, tmp / numbers[idx + 1]);
            cards[3]++;
        }
    }

    private static void dfs2(int idx, int tmp, int a, int b, int c, int d) {
        if (idx == N - 1) {
            ansMax = Math.max(tmp, ansMax);
            ansMin = Math.min(tmp, ansMin);

            return;
        }

        if (a > 0) {
            dfs2(idx + 1, tmp + numbers[idx + 1], a - 1, b, c, d);
        }
        if (b > 0) {
            dfs2(idx + 1, tmp - numbers[idx + 1], a, b - 1, c, d);
        }
        if (c > 0) {
            dfs2(idx + 1, tmp * numbers[idx + 1], a, b, c - 1, d);
        }
        if (d > 0) {
            dfs2(idx + 1, tmp / numbers[idx + 1], a, b, c, d - 1);
        }
    }
}