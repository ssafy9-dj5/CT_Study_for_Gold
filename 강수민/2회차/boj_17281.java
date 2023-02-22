import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] inning_point;
    static int[] player;
    static boolean[] used;
    static int n, max;

    private static void perm(int idx) { // idx : 골라야 하는 타순
        if (idx == 10) {
            baseBall();
            return;
        }

        if (idx == 4) {
            perm(idx + 1); // 4번자리 스킵
        } else {
            for (int i = 2; i <= 9; i++) {
                if (!used[i]) {
                    used[i] = true;
                    player[idx] = i;
                    perm(idx + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void baseBall() {
        int point = 0;
        int player_num = 1;
        for (int i = 1; i <= n; i++) {
            int out = 0;
            Queue<Integer> lu = new ArrayDeque<>();
            while (out < 3) {
                if (inning_point[i][player[player_num]] == 0) {
                    out++;
                } else {
                    int size = lu.size();
                    for (int k = 0; k < size; k++) {
                        int man = lu.poll() + inning_point[i][player[player_num]];
                        if (man > 3) {
                            point++;
                        } else {
                            lu.add(man);
                        }
                    }

                    if (inning_point[i][player[player_num]] == 4) {
                        point++;
                    } else {
                        lu.add(inning_point[i][player[player_num]]);
                    }
                }

                if (++player_num == 10) {
                    player_num = 1;
                }
            }
        }

        max = Math.max(max, point);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        player = new int[10];
        used = new boolean[10];
        player[4] = 1;
        used[1] = true;

        inning_point = new int[n + 1][10];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                inning_point[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        perm(1);

        System.out.println(max);
    }
}