package w1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj_1954 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int exp[][] = new int[n][2];
		int arr[] = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			exp[i][0] = Integer.parseInt(st.nextToken());
			exp[i][1] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());

		HashMap<Integer, Integer> res = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= M; j++) {
				int key = exp[i][0] * j + exp[i][1];
				if (res.containsKey(key))
					res.put(key, res.get(key) + 1);
				else
					res.put(key, 1);
			}
		}

		boolean isRight = false;
		for (Integer key : res.keySet()) {
			if (res.get(key) == n) {
				int resM = 0;
				for (int i = 0; i < n; i++) { 
					resM += (key - exp[i][1]) / exp[i][0];
				}
				if (resM == M) {
					System.out.println(key);
					isRight = true;
				}
			}
		}
		if (!isRight)
			System.out.println(0);
	}
}
