package G5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_5430_AC {
    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String origin = br.readLine();
            String[] ss = origin.substring(1, origin.length() - 1).split(",");

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(ss[i]);
            }

            // 중복 R 제거
            while (p.contains("RR")) {
                p = p.replace("RR", "");
            }

            // 연산 수행
            process(n, p, arr);
        }
    }

    private static void process(int n, String p, int[] arr) {
        int left = 0, right = n - 1;
        boolean flag = true;

        for (int i = 0; i < p.length(); i++) {
            // 뒤집기
            if (p.charAt(i) == 'R') {
                flag = !flag;
            }
            // 맨 앞 지우기
            else {
                if (n == 0) {
                    System.out.println("error");
                    return;
                }
                n--;
                if (flag) left++;
                else right--;
            }
        }

        if (n==0) {
            System.out.println("[]");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');

        if (flag) {
            for (int i = left; i <= right; i++) {
                sb.append(arr[i]).append(',');
            }
        } else {
            for (int i = right; i >= left; i--) {
                sb.append(arr[i]).append(',');
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');
        System.out.println(sb);
    }
}