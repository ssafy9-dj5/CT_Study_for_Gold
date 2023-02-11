package S1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1992 {
    static int[][] arr;
    static StringBuilder sb;
    private static void recursion(int n, int x, int y) {
        if (check(n, x, y)) {
            sb.append(arr[x][y]);
        } else {
            sb.append("(");
            recursion(n / 2, x, y);
            recursion(n / 2, x, y + n / 2);
            recursion(n / 2, x + n / 2, y);
            recursion(n / 2, x + n / 2, y + n / 2);
            sb.append(")");
        }
    }

    private static boolean check(int n, int x, int y) {
        int num = arr[x][y];
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y + n; j++) {
                if (arr[i][j] != num) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            String temp = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = temp.charAt(j) - '0';
            }
        }
        sb = new StringBuilder();
        recursion(n, 0, 0);
        System.out.println(sb.toString());
    }
}
