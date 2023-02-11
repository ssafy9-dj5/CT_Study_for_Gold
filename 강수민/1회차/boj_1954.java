package G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1954 {
    static int n, m;
    static int[] a;
    static int[] b;
    private static boolean recursion(int idx, int ml, int befor_gas) {
        if (idx == n) {
            return ml == 0;
        }

        for (int i = 1; i <= ml-(n-idx-1); i++) {
            int now_gas = a[idx]*i+b[idx];
            if (now_gas == befor_gas && recursion(idx+1, ml - i, befor_gas)) {
                return true;
            } else if (now_gas > befor_gas) {
                break;
            }
        }
        return false;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        a = new int[n];
        b = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());

        int answer = 0;
        for (int i = 1; i <= m - n + 1; i++) {
            int gas = a[0]*i+b[0];
            if (recursion(1, m-i, gas)) {
                answer = gas;
                break;
            }
        }
        System.out.println(answer);
    }
}
