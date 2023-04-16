package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2042구간합구하기 {
	static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 데이터 개수
		int m = Integer.parseInt(st.nextToken());// 업데이트 횟수
		int k = Integer.parseInt(st.nextToken());// 구간합 구하는 횟수

		int r = 0;
		while (true) {
			if ((Math.pow(2, r)) >= n)
				break;
			r++; // 트리의 크기 정하기
		}		
		tree = new long[(int) (Math.pow(2, r)) * 2];
		
	

		for (int i = 0; i < n; i++) {
			tree[(int)Math.pow(2, r) + i] = Long.parseLong(br.readLine()); // 원본 데이터 저장
		}
		init((int)Math.pow(2, r),n);
		//System.out.println(Arrays.toString(tree));
		
		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			long t = Long.parseLong(st.nextToken());
			long s = Long.parseLong(st.nextToken());

			if (w == 1)
				update(tree[(int) (Math.pow(2, r) + t - 1)], (int) (Math.pow(2, r) + t - 1), s);
			else
				System.out.println(sum((int) (Math.pow(2, r) + t - 1), (int) (Math.pow(2, r) + s - 1), 0));
			//System.out.println(Arrays.toString(tree));
			
		}

	}

	private static void init(int s, int e) {
		for(int i=s;i<s+e;i++) {
			int idx = i/2;
			while(idx!=0) {
				tree[idx]+=tree[i];
				idx/=2;
			}
		}
	}


	private static long sum(int s, int e, long sum) {
		// s~e까지 합 구하기

		if (s > e) {
			return sum;
		}
		if (s % 2 == 1)
			sum += tree[s];
		if (e % 2 == 0)
			sum += tree[e];

		return sum((s + 1) / 2, (e - 1) / 2, sum);
	}

	private static void update(long n, int t, long s) {
		// t자리에 있는 n을 s로 바꾸기
		if (t == 0)
			return;

		tree[t] = tree[t] - n + s;
		update(n, t / 2, s);

	}
}
