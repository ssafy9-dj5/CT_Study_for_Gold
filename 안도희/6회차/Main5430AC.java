package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main5430AC {
	static String ac;
	static int s,e,cntr;
	static StringBuilder sb;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			sb = new StringBuilder();
			ac = br.readLine();
			int n = Integer.parseInt(br.readLine());
			s = 0;
			e = n - 1;
			cntr = 0; //뒤집은 상태
			arr = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine(), "[|,|]");
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());

			}

			AC();
			
			System.out.println(sb);
		}
	}
	
	private static void AC() {
		
		for (int i = 0; i < ac.length(); i++) {
			if (ac.charAt(i) == 'R')
				cntr++;
			else {
				if (e < s) {
					sb.append("error");
					return;
					}
				if (cntr % 2 == 0) {// 짝수
					s++;
				} else //홀수 reverse
					e--;

			}
		}
		sb.append("[");
		
		if(cntr%2==0) {
			for(int i=s;i<=e;i++) {
				sb.append(arr[i]);
				if(i!=e)
					sb.append(",");
			}
			
		}
		else {
			for(int i=e;i>=s;i--) {
				sb.append(arr[i]);
				if(i!=s)
					sb.append(",");
			}
		}
		
		sb.append("]");
		
	}
}
