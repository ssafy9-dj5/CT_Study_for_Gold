import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        dp[1] = 0;
        int[] list = new int[n + 1];

        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            list[i] = i - 1;
            if(i % 3 == 0) {
                if(dp[i / 3] + 1 < dp[i]) {
                    dp[i] = dp[i / 3] + 1;
                    list[i] = i / 3;
                }
            }
            if(i % 2 == 0) {
                if(dp[i /2] + 1 < dp[i]) {
                    dp[i] = dp[i / 2] + 1;
                    list[i] = i / 2;
                }
            }
        }

        System.out.println(dp[n]);
        StringBuilder sb = new StringBuilder();
        int i = n;
        while(i != 1) {
            sb.append(i+" ");
            i = list[i];
        }
        sb.append("1");
        System.out.println(sb);
    }
}
