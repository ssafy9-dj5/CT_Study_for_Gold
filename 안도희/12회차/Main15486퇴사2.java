package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15486퇴사2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] max = new int[n + 1];
		int[][] arr = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = n - 1; i >= 0; i--) {
			int day = arr[i][0];
			if (day <= n - i) {//일해도 돼
				if (max[i + 1] < arr[i][1] + max[i + day]) {
					max[i] = arr[i][1] + max[i + day];
					continue;
				}
			}
			max[i] = max[i + 1];
		}
		System.out.println(max[0]);

	}
}
