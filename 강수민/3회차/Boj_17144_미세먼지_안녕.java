package G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17144_미세먼지_안녕 {
    private static int R, C, top, bottom;
    private static int[][] room;

    public static void test(String s) throws IOException {
        // Input
        StringTokenizer st = new StringTokenizer(s);
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        room = new int[R][C];
        top = -1; bottom = -1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    if (top == -1) top = i;
                    else bottom = i;
                }
            }
        }

        // 시간 진행
        for (int t = 0; t < T; t++) {
//            print();
            dust(); // 먼지 확산
            top_wind();     // 반시계 바람 순환
            bottom_wind();  // 시계 바람 순환
//            print();
        }

        // Output
        System.out.println(sum_Dust());
    }

    private static int[] dx = {-1, 0, 1, 0}; // 상 좌 하 우
    private static int[] dy = {0, 1, 0, -1};
    private static void dust() {        // 먼지 확산
        int[][] temp = new int[R][C];   // 먼지 누적

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (room[i][j] > 0) { // 확산할 먼지가 있다면
                    int cnt = 0; // 확산 수
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        // 방 범위 안에 있으며, 공기청정기와 안겹치면 먼지 확산
                        if (0 <= x && x < R && 0 <= y && y < C && !(y==0&&(x==top||x==bottom))) {
                            cnt++;
                            temp[x][y] += room[i][j]/5;
                        }
                    }
                    temp[i][j] += room[i][j] - (room[i][j]/5)*cnt;
                }
            }
        }
        room = temp;
    }

    private static void top_wind() {    // 반시계 바람 순환
        if (top > 0) {
            for (int i = top-2; i >= 0; i--) {
                room[i+1][0] = room[i][0];
            }

            for (int i = 1; i < C; i++) {
                room[0][i-1] = room[0][i];
            }

            for (int i = 1; i <= top; i++) {
                room[i-1][C-1] = room[i][C-1];
            }

            for (int i = C-2; i > 0; i--) {
                room[top][i+1] = room[top][i];
            }
            room[top][1] = 0;
        }
    }

    private static void bottom_wind() { // 시계 바람 순환
        if (bottom < R) {
            for (int i = bottom+2; i < R; i++) {
                room[i-1][0] = room[i][0];
            }

            for (int i = 1; i < C; i++) {
                room[R-1][i-1] = room[R-1][i];
            }

            for (int i = R-2; i >= bottom; i--) {
                room[i+1][C-1] = room[i][C-1];
            }

            for (int i = C-2; i > 0; i--) {
                room[bottom][i+1] = room[bottom][i];
            }
            room[bottom][1] = 0;
        }
    }

    private static int sum_Dust() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) {
                    sum += room[i][j];
                }
            }
        }
        return sum;
    }

    private static void print() {
        System.out.println("============================");
        room[top][0] = -1;
        room[bottom][0] = -1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(room[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        room = new int[R][C];
        top = -1; bottom = -1;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    if (top == -1) top = i;
                    else bottom = i;
                }
            }
        }

        // 시간 진행
        for (int t = 0; t < T; t++) {
//            print();
            dust(); // 먼지 확산
            top_wind();     // 반시계 바람 순환
            bottom_wind();  // 시계 바람 순환
//            print();
        }

        // Output
        System.out.println(sum_Dust());
    }
}
