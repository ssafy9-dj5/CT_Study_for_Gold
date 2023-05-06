package week12;

import java.util.Scanner;


public class Main128521로만들기2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int[] dp = new int[n+1];
		String[] dps = new String[n+1];

		dp[1]=0;
		dps[1] ="1";
		
		
		
		for(int i=2;i<=n;i++) {
			dp[i] = dp[i-1]+1;
			dps[i] =Integer.toString(i)+" "+dps[i-1];
			
			if(i%3==0) {
				if(dp[i]>dp[i/3]+1) {
					dp[i] = dp[i/3]+1;
					dps[i] = Integer.toString(i)+" "+dps[i/3];
				}
			}
			
			if(i%2==0) {
				if(dp[i]>dp[i/2]+1) {
					dp[i] = dp[i/2]+1;
					dps[i] = Integer.toString(i)+" "+dps[i/2];
				}
			}
		}
		
		
		System.out.println(dp[n]);
		System.out.println(dps[n]);


	}
}
