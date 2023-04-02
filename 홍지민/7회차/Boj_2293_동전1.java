package day0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2293_동전1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] coin = new int[n];
		for(int i=0; i<n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		int[] value = new int[k+1];
		value[0] = 1;
		
		//동전 1이 안주어질 수 있으니까 value[1]은 초기화 진행 X
		for(int i=0; i<n; i++) {
			for(int j=1; j<=k; j++) {
				if(coin[i] <= j) {
					value[j] += value[j-coin[i]];
				}
			}
		}
		System.out.println(value[k]);
	}
}
