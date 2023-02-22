package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1992 {
	static char[][] arr;
	static StringBuilder sb= new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		for(int i=0;i<n;i++) {
			arr[i] = br.readLine().toCharArray();
		}
		if(n==1) sb.append("(");
		cut(n,0,0);
		if(n==1) sb.append(")");

		System.out.println(sb);
		
	}
	
	private static void cut(int n, int i, int j) {
		
		for(int t=i;t<i+n;t++) {
			for(int r=j;r<j+n;r++) {
				if(arr[i][j]!=arr[t][r]) {
					sb.append("(");
					cut(n/2,i,j);
					cut(n/2,i,j+n/2);
					cut(n/2,i+n/2,j);
					cut(n/2,i+n/2,j+n/2);
					sb.append(")");
					return;
				}
			}			
		}
		
		sb.append(arr[i][j]);
	}
}
