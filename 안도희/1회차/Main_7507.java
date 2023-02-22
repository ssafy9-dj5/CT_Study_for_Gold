package week1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_7507 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for (int i = 0; i < tc; i++) {
			int max = 1;
			int n = Integer.parseInt(br.readLine());
			int[][] time = new int[n][3];
			for (int j = 0; j < n; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 3; k++) {
					time[j][k] = Integer.parseInt(st.nextToken());
				}
			}

			Arrays.sort(time, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {

					return o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1];
				}
			});

			
			int s=0;
			for(int j=s;j<n;j++) {
				if(time[s][0]!=time[j][0]||time[s][2]<=time[j][1]) {
					max++;
					s=j;
				}
				else {
					if(time[s][2]>time[j][2])
						s=j;
				}
			}
			System.out.println("Scenario #" + (i + 1)+":");
			System.out.println(max);
			System.out.println();
		}
		
		

	}
}
