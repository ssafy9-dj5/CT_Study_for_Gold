package day0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {
	static int [][] arr;
	static int minDif = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// input
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// check
		boolean isStartTeam[] = new boolean[n];
		subset(n, isStartTeam, 0, 0);
		// output
		System.out.println(minDif);
	}

	static void subset(int n, boolean[] isStartTeam, int idx, int cnt) {
		if (idx == n && cnt == n / 2) {
			int startTeam = 0, linkTeam = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (isStartTeam[i] == isStartTeam[j]) {
						if(isStartTeam[i])
							startTeam += arr[i][j];
						if(!isStartTeam[i])
							linkTeam += arr[i][j];
					}
				}
			}
			minDif = Math.min(minDif, Math.abs(startTeam - linkTeam));
//			System.out.print("start: ");
//			for (int i = 0; i < n; i++) {
//				if(isStartTeam[i]) System.out.print(i + " ");
//			}
//			System.out.print("\nlink: ");
//			for (int i = 0; i < n; i++) {
//				if(!isStartTeam[i]) System.out.print(i + " ");
//			}
//			System.out.print("\n"+ Math.abs(startTeam - linkTeam) + "\n");
			return;
		}
		if (idx >= n && cnt != n / 2)
			return;

		if (cnt <= n / 2) {
			isStartTeam[idx] = true; // include start team
			subset(n, isStartTeam, idx + 1, cnt + 1); // cnt -> number of start team member
		}
		isStartTeam[idx] = false; // include link team
		subset(n, isStartTeam, idx + 1, cnt);
	}
}
