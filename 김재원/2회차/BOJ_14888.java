package day0214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888 {
	static int arr[];
	static int opt[];
	static int minValue;
	static int maxValue;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		opt = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			opt[i] = Integer.parseInt(st.nextToken());
		}
		
		minValue = Integer.MAX_VALUE;
		maxValue = Integer.MIN_VALUE;
		perm(1, arr[0], opt[0], opt[1], opt[2], opt[3]);

		System.out.println(maxValue);
		System.out.println(minValue);
	}

	static void perm(int idx, int num, int o1, int o2, int o3, int o4) {
		if (idx == N) {
			minValue = Math.min(minValue, num);
			maxValue = Math.max(maxValue, num);
			return;
		}

		for (int i = 0; i < opt.length; i++) {
			if (opt[i] == 0)
				continue;
			switch (i) {
			case 0:
				if (o1 == 0) continue;
				perm(idx + 1, num + arr[idx], o1 - 1, o2, o3, o4);
				break;
			case 1:
				if (o2 == 0) continue;
				perm(idx + 1, num - arr[idx], o1, o2 - 1, o3, o4);
				break;
			case 2:
				if (o3 == 0) continue;
				perm(idx + 1, num * arr[idx], o1, o2, o3 - 1, o4);
				break;
			case 3:
				if (o4 == 0) continue;
				if (num >= 0)
					perm(idx + 1, num / arr[idx], o1, o2, o3, o4 - 1);
				else
					perm(idx + 1, Math.abs(num) / arr[idx] * -1, o1, o2, o3, o4 - 1);
				break;
			}

		}
	}
}
// 빨간공 3개 파란공 2개
// 순서가 있는 접시 5개
// DFS인 이유
//
