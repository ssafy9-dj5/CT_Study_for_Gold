package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj17143_낚시왕 {
	static int R, C, M, answer;
	static ArrayList<Shark> shark;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static Shark[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		shark = new ArrayList<>();

		char a = 'A';
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());

			if (d == 0 || d == 1) { // 상하 방향 이동
				s = s % (2 * (R - 1)) + 2 * (R - 1);
			} else { // 좌우 방향 이동
				s = s % (2 * (C - 1)) + 2 * (C - 1);
			}
			shark.add(new Shark(r, c, s, d, z, a++));
		}

		for (int t = 1; t <= C; t++) {
			if (shark.size() == 0)
				break;
			fishing(t); // 상어 낚시
			move(); // 상어 이동
		}

		System.out.println(answer);
	}

	private static void fishing(int col) { // col번 열에 있는 상어 중 땅과 제일 가까운 상어를 잡는다
		int minIdx = R + 1;
		int removeIdx = -1;
		for (int i = 0; i < shark.size(); i++) {
			Shark sh = shark.get(i);
			if (sh.j == col && minIdx > sh.i) {
				minIdx = Math.min(minIdx, sh.i);
				removeIdx = i;
			}
		}
		if (removeIdx != -1) {
//		System.out.println("낚시: " + shark.get(removeIdx));
			answer += shark.get(removeIdx).z;
			shark.remove(removeIdx);
		}
	}

	private static void move() {
		visited = new Shark[R + 1][C + 1];
		ArrayList<Character> remove = new ArrayList<>();
		for (int x = 0; x < shark.size(); x++) {
			Shark now = shark.get(x);

			int nexti = now.i + di[now.d] * now.s;
			int nextj = now.j + dj[now.d] * now.s;

			while (nexti <= 0 || nexti > R || nextj <= 0 || nextj > C) {
				switch (now.d) {
				case 0:
					now.d = 1;
					nexti = -(nexti - 2);
					break;
				case 1:
					now.d = 0;
					nexti = 2 * R - nexti;
					break;
				case 2:
					now.d = 3;
					nextj = 2 * C - nextj;
					break;
				case 3:
					now.d = 2;
					nextj = -(nextj - 2);
					break;
				}
			}
			now.i = nexti;
			now.j = nextj;

			if (visited[nexti][nextj] != null) { // 같은 칸에 상어가 있으면 크기가 작은 상어 먹힘
				Shark sh = visited[nexti][nextj];
				if (sh.z > now.z) {
					remove.add(now.alpha);
				} else {
					remove.add(sh.alpha);
					visited[nexti][nextj] = now;
				}
			} else {
				visited[nexti][nextj] = now;
			}
		}
//		System.out.println("이동 후");
//		System.out.println(shark);

		for (int i = shark.size() - 1; i >= 0; i--) {
			if (remove.contains(shark.get(i).alpha))
				shark.remove(i);
		}
//		System.out.println("먹힘");
//		System.out.println(shark);
	}

	static class Shark implements Comparable<Shark> {

		@Override
		public String toString() {
			return "Shark [i=" + i + ", j=" + j + ", speed=" + s + ", dir=" + d + ", size=" + z + ", alpha=" + alpha
					+ "]\n";
		}

		int i, j, s, d, z;
		char alpha;

		public Shark(int i, int j, int s, int d, int z, char alpha) {
			super();
			this.i = i;
			this.j = j;
			this.s = s;
			this.d = d;
			this.z = z;
			this.alpha = alpha;
		}

		@Override
		public int compareTo(Shark o) {
			return Integer.compare(this.i, o.i);
		}
	}
}
