package day0513;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Boj_4056_스스스도쿠 {
	static int[][] grid;
	static List<Pos> zero;
	static int flag;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			flag = 0;
			grid = new int[9][9];
			zero = new ArrayList<>();

			for (int i = 0; i < 9; i++) {
				String s = br.readLine();
				for (int j = 0; j < 9; j++) {
					grid[i][j] = s.charAt(j) - '0';
					if (grid[i][j] == 0) {
						zero.add(new Pos(i, j));
					}
				}
			}
			
			if (game(zero.get(0), 0)) {
				print();
			} else {
				System.out.println("Could not complete this grid.");
			}
		}
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				sb.append(grid[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static boolean game(Pos pos, int idx) {

		for (int i = 1; i <= 9; i++) {
			
			if (check(pos.r, pos.c, i)) {
				if (idx + 1 == 5) {
					flag = 1;
					break;
				}
				game(zero.get(idx+1), idx + 1);
			}
		}

		if(flag == 1) {
			return true;
		}
		return false;

	}

	private static boolean check(int r, int c, int pick) {
		boolean[] num = new boolean[10];
		grid[r][c] = pick;

		// 행 검사
		for (int i = 0; i < 9; i++) {
			if(grid[i][c] == 0) continue;
			if (num[grid[i][c]]) {
				return false;
			}
			num[grid[i][c]] = true;
		}

		// 열 검사
		num = new boolean[10];

		for (int i = 0; i < 9; i++) {
			if(grid[r][i] == 0) continue;
			if (grid[r][i] != 0 && num[grid[r][i]]) {
				return false;
			}
			num[grid[r][i]] = true;
		}

		// 박스 검사
		num = new boolean[10];

		int i = r / 3 * 3;
		int j = c / 3 * 3;

		for (int p = i; p < i + 3; p++) {
			for (int q = j; q < j + 3; q++) {
				if(grid[p][q] == 0) continue;
				if (num[grid[p][q]]) {
					return false;
				}
				num[grid[p][q]] = true;
			}
		}

		return true;
	}

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
