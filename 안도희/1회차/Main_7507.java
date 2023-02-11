package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_7507 {
	static int[][] time;
	static int n;
	static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int i = 0; i < tc; i++) {
			max = 0;
			n = Integer.parseInt(br.readLine());
			time = new int[n][3];
			for (int j = 0; j < n; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 3; k++) {
					time[j][k] = Integer.parseInt(st.nextToken());
				}
			}

			Arrays.sort(time, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {

					return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
				}
			});

			com(0, 1);
			System.out.println("Scenario #" + (i + 1));
			System.out.println(max);
		}

	}

	private static void com(int s, int r) {// 내가 고른 경기, 경기 몇개 골랐는지

		for (int i = s + 1; i < n; i++) {
			if (time[s][0] == time[i][0]) {
				if (time[s][2] <= time[i][1]) {
					if (i == n - 1 && max <=r) {
						max = r+1;
					} else
						com(i, r+1);
				}
			} else {//날짜가 다르다면
				if (i == n - 1 && max <=r)
					max = r+1;
				else
					com(i, r+1);

			}

		}
	}

}
