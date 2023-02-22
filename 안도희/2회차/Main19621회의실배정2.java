package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main19621회의실배정2 {
	static int tc;
	static int[][] arr;
	static int max=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tc = Integer.parseInt(br.readLine());
		arr = new int[tc][3];
		for (int i = 0; i < tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		sub(0,0);
		System.out.println(max);
		
	}

	private static void sub(int s,int sum) {
		if(s>tc-1) {
			if(max<sum) max=sum;
			return;
		}
		for(int i=s;i<tc;i++) {
			sub(i+2,sum+arr[i][2]);
		}
		
	}
}
