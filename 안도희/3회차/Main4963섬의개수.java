package week3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main4963¼¶ÀÇ°³¼ö{
	static int[][] arr;
	static int cnt;
	static int w;
	static int h;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			cnt=0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w==0&&h==0) break;
			arr = new int[h][w];

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (arr[i][j] == 1) {
						arr[i][j] = 2;
						island(i, j);
						cnt++;

					}
				}
			}
			sb.append(cnt + "\n");
		}
		System.out.println(sb);
	}
	

	private static void island(int i, int j) {
		int[] di = { -1, 1, 0, 0, -1, -1, 1, 1 };
		int[] dj = { 0, 0, -1, 1, -1, 1, -1, 1 };

		for (int d = 0; d < 8; d++) {
			if (i + di[d]>=0 && i + di[d]<h&&j + dj[d]>=0&&j + dj[d]<w&&
					arr[i + di[d]][j + dj[d]] == 1) {
				arr[i + di[d]][j + dj[d]] = 2;
				island(i + di[d], j + dj[d]);
			}

		}

	}
}
