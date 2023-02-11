package S3;

import java.util.Scanner;

public class boj_2312 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < tc; t++) {
            int n = sc.nextInt();
            int[] arr = new int[n+1];
            int temp = n, pt = 2;

            while (temp > 1) {
                if (temp % pt == 0) {
                    arr[pt]++;
                    temp /= pt;
                }
                else {
                    pt++;
                }
            }

            for (int i = 2; i < n+1; i++) {
                if (arr[i] > 0) {
                    sb.append(i + " " + arr[i] + "\n");
                }
            }
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.print(sb);
    }
}
