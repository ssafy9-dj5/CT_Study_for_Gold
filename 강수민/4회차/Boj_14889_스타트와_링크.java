package S2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_14889_스타트와_링크 {
    private static int N, ans = Integer.MAX_VALUE;
    private static int[][] arr;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N];

        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Combination
        comb(0, 0, 0);

        // Output
        System.out.println(ans);
    }

    private static boolean comb(int start, int cnt, int sum) {
        if (cnt == N / 2) {
            int temp = 0;
            for (int i = 0; i < N-1; i++) {
                for (int j = i+1; j < N; j++) {
                    if (!visited[i] && !visited[j])
                        temp += arr[i][j] + arr[j][i];
                }
            }

            ans = Math.min(ans, Math.abs(sum - temp));
            return ans == 0;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;

                int temp = 0;
                for (int j = 0; j < i; j++) {
                    if (visited[j])
                        temp += arr[i][j] + arr[j][i];
                }

                if(comb(i + 1, cnt + 1, sum + temp)) return true;

                visited[i] = false;
            }
        }
        return false;
    }
}
