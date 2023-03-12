package G1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17143_낚시왕 {
    private static class Shark {
        int s, d, z;

        public Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    private static int R, C, M, ans;
    private static Shark[][] map, temp;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Shark[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = new Shark(
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()));
        }

        // 낚시왕 이동
        for (int i = 1; i <= C; i++) {
            // 상어 있으면 잡기
            for (int j = 1; j <= R; j++) {
                if (map[j][i] != null) {
                    ans += map[j][i].z;
                    M--;
                    map[j][i] = null;
                    break;
                }
            }

            // 상어 이동
            temp = new Shark[R + 1][C + 1];
            for (int j = 1; j <= R; j++) {
                for (int k = 1; k <= C; k++) {
                    if (map[j][k] != null) { // 상어 발견
                        move_shark(j, k, map[j][k]); // 이동
                    }
                }
            }
            deepCopy();
        }
        System.out.println(ans);
    }

    private static void move_shark(int r, int c, Shark shark) {
        // 상어 이동
        int dd = shark.d % 2 == 0 ? 1 : -1;
        int dr = r, dc = c;
        for (int i = 0; i < shark.s; i++) {
            dr = r + dx[shark.d];
            dc = c + dy[shark.d];
            if (1 > dr || dr > R || 1 > dc || dc > C) {
                shark.d += dd;
                dd *= -1;
                i--;
                continue;
            }
            r = dr;
            c = dc;
        }

        // 상어 위치 저장
        if (temp[dr][dc] == null) {
            temp[dr][dc] = shark;
        } else {
            temp[dr][dc] = temp[dr][dc].z > shark.z ? temp[dr][dc] : shark;
        }
    }

    private static void deepCopy() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (temp[i][j] != null)
                    map[i][j] = temp[i][j];
                else {
                    map[i][j] = null;
                }
            }
        }
    }
    private static void print() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] != null) {
                    System.out.print('S' + " ");
                } else {
                    System.out.print('O' + " ");
                }
            }
            System.out.println();
        }
    }
}
