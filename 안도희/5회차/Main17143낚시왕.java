package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17143낚시왕 {
	static class shark {
		int s, d, z;

		public shark(int s, int d, int z) {
			super();
			this.s = s;// 속력
			this.d = d;// 방향
			this.z = z;// 크기
		}
	}

	static shark[][] arr;
	static int r, c;
	static int[] di = { 0, -1, 1, 0, 0 };
	static int[] dj = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());// row
		c = Integer.parseInt(st.nextToken());// col
		int m = Integer.parseInt(st.nextToken());// 상어의 수
		arr = new shark[r + 1][c + 1];// 상어 낚시
		int result = 0; // 상어 크기 합 저장

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int idxr = Integer.parseInt(st.nextToken());
			int idxc = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			arr[idxr][idxc] = new shark(s, d, z); // 상어 정보 저장
		}

		for (int j = 1; j <= c; j++) {// 낚시왕 이동!!
			int i = 0;
			while (i < r) {
				i++;
				if (arr[i][j] != null) {
					result += arr[i][j].z;
					arr[i][j] = null;
					break;
				}
			}
			arr = move();// 상어 이동!!
		}

		System.out.println(result);
	}

	private static shark[][] move() {
		shark[][] sharkm = new shark[r + 1][c + 1];
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if (arr[i][j] != null) {// 상어
					int nexti = i;
					int nextj = j;

					for (int t = 0; t < arr[i][j].s; t++) {
						if (nexti + di[arr[i][j].d] < 1 || nexti + di[arr[i][j].d] > r || nextj + dj[arr[i][j].d] < 1
								|| nextj + dj[arr[i][j].d] > c) {
							switch (arr[i][j].d) {
							case 1:
								arr[i][j].d = 2;
								break;
							case 2:
								arr[i][j].d = 1;
								break;
							case 3:
								arr[i][j].d = 4;
								break;
							case 4:
								arr[i][j].d = 3;
								break;
							}
						}
						nexti += di[arr[i][j].d];
						nextj += dj[arr[i][j].d];
					}

					if (sharkm[nexti][nextj] == null || sharkm[nexti][nextj].z < arr[i][j].z)
						sharkm[nexti][nextj] = arr[i][j];
				}
			}
		}

		return sharkm;
	}
}
