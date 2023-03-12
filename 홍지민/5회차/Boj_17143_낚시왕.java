package day0312;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17143_낚시왕 {

    static class Shark {
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static int R, C, M, answer = 0;
    static Shark[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        R = Integer.parseInt(stringTokenizer.nextToken());
        C = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        map = new Shark[R][C];
        int r, c, s, d, z;
        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());
            r = Integer.parseInt(stringTokenizer.nextToken());
            c = Integer.parseInt(stringTokenizer.nextToken());
            s = Integer.parseInt(stringTokenizer.nextToken());
            d = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            z = Integer.parseInt(stringTokenizer.nextToken());

            map[r - 1][c - 1] = new Shark(r - 1, c - 1, s, d, z);
        }

        movePerson();
        System.out.println(answer);
    }

    private static void movePerson() {
        for (int i = 0; i < C; i++) {
            fishing(i);
            map = moveSharks();
        }
    }

    private static Shark[][] moveSharks() {
        Shark[][] tmp = new Shark[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == null) continue;

                Shark now = map[i][j];
                int s = now.s;
                if (now.d == 0 || now.d == 1) { // 이돟방향 : 상하
                    s %= (R - 1) * 2;
                } else { //이동방향 : 좌우
                    s %= (C - 1) * 2;
                }

                for (int k = 0; k < s; k++) {
                    int nr = now.r + dr[now.d];
                    int nc = now.c + dc[now.d];

                    //범위 map 넘을 경우 방향 조정
                    if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                        now.r -= dr[now.d];
                        now.c -= dc[now.d];

                        if (now.d == 0) now.d = 1;
                        else if (now.d == 1) now.d = 0;
                        else if (now.d == 2) now.d = 3;
                        else now.d = 2;
                        continue;
                    }

                    now.r = nr;
                    now.c = nc;
                }

                if (tmp[now.r][now.c] != null && tmp[now.r][now.c].z > now.z) continue;
                tmp[now.r][now.c] = now;
            }
        }

        return tmp;
    }


    private static void fishing(int c) {
        for (int i = 0; i < R; i++) {
            if (map[i][c] != null) {
                answer += map[i][c].z;
                map[i][c] = null;
                return;
            }
        }
    }
}