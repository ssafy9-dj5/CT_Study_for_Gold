package G2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj_1525_퍼즐 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                s += st.nextToken();
            }
        }

        System.out.print(bfs(s));
    }

    private static int bfs(String s) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<String> que = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        que.add(s);
        visited.add(s);
        int cnt = 0;

        while (!que.isEmpty()) {
            int size = que.size();

            for (int i = 0; i < size; i++) {
                String str = que.poll();
                if (str.equals("123456780")) {
                    return cnt;
                }

                int idx = str.indexOf("0");
                int r = idx / 3;
                int c = idx % 3;

                for (int j = 0; j < 4; j++) {
                    int x = r + dx[j];
                    int y = c + dy[j];
                    if (0 <= x && x < 3 && 0 <= y && y < 3) {
                        char target = str.charAt(x * 3 + y);
                        String temp = str.substring(0, x * 3 + y) + '0' + str.substring(x * 3 + y + 1);
                        temp = temp.substring(0, r * 3 + c) + target + temp.substring(r * 3 + c + 1);
                        if (!visited.contains(temp)) {
                            visited.add(temp);
                            que.add(temp);
                        }
                    }
                }
            }
            cnt++;
        }
        return -1;
    }
}
