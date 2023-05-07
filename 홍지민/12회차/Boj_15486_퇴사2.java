package day0507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_15486_퇴사2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] sch = new int[n+1][2];
		int[] dp = new int[n+2];
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			sch[i][0] = Integer.parseInt(st.nextToken());
			sch[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=n; i++) {
			int value = i + sch[i][0];
			if(value <= n+1) {
				dp[value] = Math.max(dp[i]+sch[i][1], dp[value]);
			}
			dp[i+1] = Math.max(dp[i], dp[i+1]);
		}
		
		System.out.println(dp[n+1]);
	}
}
