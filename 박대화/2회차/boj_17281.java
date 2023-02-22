import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int score;
	static int max = Integer.MIN_VALUE;

	public static boolean np(int[] arr) {
		int i = arr.length - 1;
		while (i > 0 && arr[i] <= arr[i - 1])
			i--;
		if (i == 0)
			return false;

		int j = arr.length - 1;
		while (arr[j] <= arr[i - 1])
			j--;

		int tmp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = tmp;

		j = arr.length - 1;
		while (i < j) {
			tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
			i++;
			j--;
		}

		return true;
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[8];
		int[][] map = new int[n][10];
		for (int i = 0; i < 8; i++) {
			arr[i] = i + 2;
		}

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		do {
			score = 0;
			int first = 1;
			for (int i = 0; i < n; i++) {
				int[] inning = new int[10];
				int idx = 0;
				for (int j = 1; j <= 9; j++) {
					if (j == 4) {
						inning[j] = map[i][1];
					} else {
						inning[j] = map[i][arr[idx++]];
					}
				}

				first = baseball(inning, first);
				// System.out.println(Arrays.toString(inning));
			}
			max = Math.max(score, max);
		} while (np(arr));

		System.out.println(max);

		br.close();
	}

	private static int baseball(int[] inning, int first) {
		int out = 0;
		int i = first;
		boolean[] base = new boolean[3];
		while (true) {
			if (out == 3) {
				return i;
			}

			int r = inning[i];
			switch (r) {
			case 0:
				out++;
				break;
			case 1:
				if (base[2]) {
					base[2] = false;
					score++;
				}
				if (base[1]) {
					base[2] = true;
					base[1] = false;
				}
				if (base[0]) {
					base[1] = true;
				}
				if (!base[0]) {
					base[0] = true;
				}
				break;
			case 2:
				if (base[2]) {
					base[2] = false;
					score++;
				}
				if (base[1]) {
					base[1] = false;
					score++;
				}
				if (base[0]) {
					base[2] = true;
					base[0] = false;
				}
				if (!base[1]) {
					base[1] = true;
				}
				break;
			case 3:
				if (base[2]) {
					base[2] = false;
					score++;
				}
				if (base[1]) {
					base[1] = false;
					score++;
				}
				if (base[0]) {
					base[0] = false;
					score++;
				}
				if (!base[2]) {
					base[2] = true;
				}
				break;
			case 4:
				if (base[2]) {
					base[2] = false;
					score++;
				}
				if (base[1]) {
					base[1] = false;
					score++;
				}
				if (base[0]) {
					base[0] = false;
					score++;
				}
				score++;
				break;
			}

			i++;
			if (i == 10) {
				i = 1;
			}
		}
	}
}