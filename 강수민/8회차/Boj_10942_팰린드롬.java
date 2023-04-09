package G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_10942_팰린드롬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        boolean[][] check = new boolean[N + 1][N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            if (arr[i] == arr[i + 1])
                check[i][i + 1] = true;
        }

        for (int i = 1; i <= N; i++) {
            check[i][i] = true;
        }

        for (int i = N - 1; i >= 1; i--) {
            for (int j = i + 2; j <= N; j++) {
                if (arr[i] == arr[j] && check[i + 1][j - 1])
                    check[i][j] = true;
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(check[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] ? 1 : 0).append('\n');
        }
        System.out.println(sb);
    }
}
