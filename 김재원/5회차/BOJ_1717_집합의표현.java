package day0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1717_집합의표현 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int func = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (func == 0) {
				union(a, b, arr);
			} else {
				if (find(a, arr) == find(b, arr))
					System.out.println("YES");
				else
					System.out.println("NO");

			}
		}
	}

	static void union(int a, int b, int[] arr) {
		int aboss = find(a, arr);
		int bboss = find(b, arr);
		if (aboss != bboss) {
			arr[aboss] = bboss;
		}

	}

	static int find(int a, int[] arr) {
		if(arr[a] == a) return a;
		else
			return arr[a] = find(arr[a], arr);
	}
}
