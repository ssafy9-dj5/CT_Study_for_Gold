//성공완료 !_!
package day0226;

import java.util.Scanner;

public class Boj_17144_미세먼지안녕 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int t = sc.nextInt();
        int[][] map = new int[r][c];
        int[][] mapCopy = new int[r][c];


        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int time = 0; time < t; time++) {
            // 먼지확산
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (map[i][j] != 0 && map[i][j] != -1) {
                        int m = map[i][j] / 5;
                        for (int d = 0; d < 4; d++) {
                            int nr = i + dr[d];
                            int nc = j + dc[d];

                            if (nr >= 0 && nc >= 0 && nr < r && nc < c && map[nr][nc] != -1) {
                                mapCopy[nr][nc] += m;
                                mapCopy[i][j] -= m;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    map[i][j] += mapCopy[i][j];
                }
            }

            mapCopy = new int[r][c];

            //공기청정기 작동
            int top = 0;
            for (int i = 2; i < r - 2; i++) {
                if (map[i][0] == -1) {
                    top = i;
                    for (int p = 1; p <= i - 1; p++) {
                        map[i - p][0] = map[i - p - 1][0];
                    }
                    for (int p = 0; p < c - 1; p++) {
                        map[0][p] = map[0][p + 1];
                    }
                    for (int p = 0; p < i; p++) {
                        map[p][c - 1] = map[p + 1][c - 1];
                    }
                    for (int p = c - 1; p > 1; p--) {
                        map[i][p] = map[i][p - 1];
                    }
                    map[i][1] = 0;
                    break;
                }
                
            }

            int down = top + 1;
            for (int p = 1; p <= r - down - 1; p++) {
                map[down + p][0] = map[down + p + 1][0];
            }
            for (int p = 0; p < c - 1; p++) {
                map[r - 1][p] = map[r - 1][p + 1];
            }
            for (int p = r - 1; p > down; p--) {
                map[p][c - 1] = map[p - 1][c - 1];
            }
            for (int p = c - 1; p > 1; p--) {
                map[down][p] = map[down][p - 1];
            }
            map[down][1] = 0;
        }

        int sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(map[i][j] != -1) sum += map[i][j];
            }
        }

        System.out.println(sum);
    }
}

