package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main7579앱 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
			
		int[] memory = new int[n + 1];
		int[] cost = new int[n + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++)
			memory[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++)
			cost[i] = Integer.parseInt(st.nextToken());
		

		int dp[] = new int[m + 1];
			Arrays.fill(dp, -1);
		
		int max=0;
		for (int i = 1; i <= n; i++) {
			max+=memory[i];
			if(max>m)
				max=m;

			for(int j=max;j>memory[i];j--) {
				if(dp[j]==-1)
					dp[j] = dp[j-memory[i]]+cost[i];
				else
					dp[j]= Math.min(dp[j-memory[i]]+cost[i], dp[j]);
			}
			

			for(int j=1;j<=m;j++) {
				if(memory[i]<j)
					break;
				if(dp[j]==-1)
					dp[j] = cost[i];
				else
					dp[j] = Math.min(cost[i], dp[j]);
			}

		}
		System.out.println(dp[m]);
	}
}
