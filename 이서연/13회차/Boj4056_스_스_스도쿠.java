package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 틀렸습니다
public class Boj4056_스_스_스도쿠 {
	static int[][] board;
	static Point[] points;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < TC; tc++) {
			board = new int[9][9];
			points = new Point[5];
			int idx = 0;
			for (int i = 0; i < 9; i++) {
				String line = br.readLine();
				for (int j = 0; j < 9; j++) {
					board[i][j] = line.charAt(j) - '0';
					
					if (board[i][j] == 0) {
						points[idx] = new Point(i, j);
						idx++;
					}
				}
				
			}
			
			if (sudoku(0)) {
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						System.out.print(board[i][j]);
					}
					System.out.println();
				}
			} else {
				System.out.println("Could not complete this grid.");
			}
			System.out.println();
		} 
	}
	
	private static boolean sudoku(int idx) {
		if (idx == 5) {
			return true;
		}
		
		int nowi = points[idx].i;
		int nowj = points[idx].j;
		
		boolean result = false;
		for (int n = 1; n <= 9; n++) {
			if (isAvailable(nowi, nowj, n)) {
				board[nowi][nowj] = n;	
				result = sudoku(idx+1);
				
				if (result)
					break;
			}
		}
		return result;
	}

	private static boolean isAvailable(int nowi, int nowj, int nown) {
		// 행 확인
		for (int j = 0; j < 9; j++) {
			if (nowj == j)
				continue;
			if (board[nowi][j] == nown)
				return false;
		}
		
		// 열 확인
		for (int i = 0; i < 9; i++) {
			if(nowi == i)
				continue;
			if (board[i][nowj] == nown)
				return false;
		}
		
		// 3*3 확인
		int si = (nowi / 3) * 3;
		int sj = (nowj / 3) * 3;
		
		for (int i = si; i < si + 3; i++) {
			for (int j = sj; j <sj+3; j++) {
				if (nowi == i && nowj == j)
					continue;
				if (board[i][j] == nown)
					return false;
			}
		}
		return true;
	}

	static class Point {
		int i, j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
	}
}
