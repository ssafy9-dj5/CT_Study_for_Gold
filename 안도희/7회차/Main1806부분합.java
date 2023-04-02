package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1806부분합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int sum = 0;
		int len = 100001;
		int[] arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			arr[i] = arr[i-1]+Integer.parseInt(st.nextToken());
		}
		int start=0;
		int e=0;

		while (true) {
			if (sum < s) {// sum이 s보다 작을때
				if (e==n)
					break;
				e++;

			} else { // sum이 s보다 크거나 같을때
				start++;
				if (e==start)
					break;
			}
			sum=arr[e]-arr[start];
			if (sum >= s) // 연속된 합이 s보다 크다면 최소 길이 갱신해주기!
				len = Math.min(len, e-start);

			
		}

		if (len > 100000)// s이상 불가능
			len = 0;
		System.out.println(len);

	}
}
