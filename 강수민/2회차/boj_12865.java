import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Obj[] objs = new Obj[n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            objs[i] = new Obj(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        int[][] dp = new int[n+1][k+1];

        for (int i = 1; i <= n; i++) {      // 물건 순회
            for (int j = 1; j <= k; j++) {  // 배낭 제한 무게 순회
                if (objs[i].w <= j) {       // 현재 물건 넣을 수 있음
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-objs[i].w]+objs[i].v); // 넣었을 때와 넣지 않았을때 비교
                }
                else {                      // 현재 물건 넣을 수 없어서 이전 최적값
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        System.out.println(dp[n][k]);
    }

    private static class Obj{
        int w, v;

        public Obj(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
}