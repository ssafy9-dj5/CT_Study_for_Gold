package G5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1717_집합의_표현 {
    private static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parents = new int[n+1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 0) union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            else if (find(Integer.parseInt(st.nextToken())) == find(Integer.parseInt(st.nextToken())))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    private static int find(int n) {
        if (parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }
    private static boolean union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if(p1 == p2) return false;

        parents[p2] = p1;
        return true;
    }
}
