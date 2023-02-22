package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_1954 {
	static int n;
	static int[] arr;
	static int gas[][];
	static int m;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n =Integer.parseInt(br.readLine());// 시약 종류
		arr = new int[n]; // 시약에 넣는 용액 양 x
		gas = new int[2][n]; // a,b저장

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			gas[0][i] = Integer.parseInt(st.nextToken());
			gas[1][i] = Integer.parseInt(st.nextToken());
		}
		m = Integer.parseInt(br.readLine());// 총 용액의 양
		perm(0,0,0);
		System.out.println(result);
	}

	private static void perm(int cnt, int sum,int r) {
		if (cnt == n - 1) {
			arr[cnt] = m - sum;
				if (cnt==0 || r == gas[0][cnt] * arr[cnt] + gas[1][cnt]) {
					result=gas[0][cnt] * arr[cnt] + gas[1][cnt];
					}
			return;
		}

		for (int i = 1; i <= m; i++) {
			arr[cnt] = i;
			if(cnt ==0) r=gas[0][0] * arr[0] + gas[1][0];
			if(r!=gas[0][cnt] * arr[cnt] + gas[1][cnt]) continue;
			perm(cnt + 1, sum + i,r);
			if(result!=0) return;
		}
	}
}
