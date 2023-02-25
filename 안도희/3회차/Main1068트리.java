package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1068트리 {
	static int n;
	static int[] arr;
	static boolean[] isdel;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		isdel = new boolean[n];
		int cnt =0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		int d = Integer.parseInt(br.readLine());
		del(d);
		
		for(int i=0;i<n;i++) {
			if(!isdel[i]) {//삭제 안된거 중에
				for(int j=0;j<n;j++) {
					if(arr[j]==i&&(!isdel[j])) break;
					if(j==n-1) cnt++; //자신의 부모로 갖는 경우가 없음
					
					
					
				}
			}
		}
		System.out.println(cnt);
	}
	private static void del(int d) {//삭제하면  true
		isdel[d]=true;
		for(int i=0;i<n;i++) {
			if(arr[i]==d) {
				del(i);
			}
		}
		
	}
}
