package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main14889스타트와링크 {
	static int n;
	static boolean[] team;
	static int[][] arr;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		team = new boolean[n+1];
		arr = new int[n + 1][n + 1];
		for(int i=1;i<=n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}	
		}
		comb(0,1);
		System.out.println(min);
	}

	private static void comb(int cnt,int s) {
		if(cnt==n/2) {
			score();
			return;
		}
		 
		for(int i=s;i<=n;i++) {
			team[i]=true;
			comb(cnt+1,i+1);
			team[i]=false;
		}
		
	}

	private static void score() {
		int s=0;
		int l=0;
		for(int i=1;i<=n;i++) {
			for(int j=i+1;j<=n;j++) {
				if(team[i]) { //true
					if(team[j]) {
						s+=(arr[i][j]+arr[j][i]);
					}
				}
				else {//false
					if(!team[j]) {
						l+=(arr[i][j]+arr[j][i]);
					}
				}
			}
		}
		
		int sum = Math.abs(s-l);
		min = Math.min(min, sum);
		
	}

}
