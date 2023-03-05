package S1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Boj_1914_하노이_탑 {
    private static int N;
    private static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // Recursion
        BigInteger bigInteger1 = new BigInteger("1");
        BigInteger bigInteger2 = new BigInteger("2");
        for (int i = 0; i < N; i++) {
            bigInteger1 = bigInteger1.multiply(bigInteger2);
        }
        System.out.println(bigInteger1.subtract(new BigInteger("1")));
        if (N <= 20) {
            sb = new StringBuilder();
            hanoi(N, 1, 2, 3);
            System.out.print(sb);
        }
    }

    private static void hanoi(int n, int s, int t, int e){
        if (n == 1) {
            sb.append(s).append(' ').append(e).append('\n');
            return;
        }

        hanoi(n-1, s, e, t);
        hanoi(1, s, t, e);
        hanoi(n-1, t, s, e);
    }
}
