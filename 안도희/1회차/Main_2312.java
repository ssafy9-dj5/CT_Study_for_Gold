package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2312 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		
		for (int i = 0; i < tc; i++) {
			int d = 2;
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n + 1];
			
			while(n!=1) {
				if(n%d==0) {
					arr[d]++;
					n/=d;
				}
				else d++;
			}
			for(int j=2;j<=d;j++) {
				if(arr[j]!=0) System.out.println(j +" "+arr[j]);
			}
		}
	}
}
