import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static room[] r;
	static int n;
	static boolean[] used;
	static int max = 0;

	public static class room {
		int start;
		int end;
		int num;

		public room(int start, int end, int num) {
			this.start = start;
			this.end = end;
			this.num = num;
		}
	}

	public static void loop(int cnt) {
		if (cnt == n) {
			int sum = 0;
			if (used[0]) {
				sum += r[0].num;
			}
			for (int i = 1; i < n; i++) {
				if (used[i] && used[i - 1]) {
					return;
				}

				if (used[i]) {
					sum += r[i].num;
				}
			}
			if (max < sum) {
				max = sum;
			}
			return;
		}

		used[cnt] = true;
		loop(cnt + 1);
		used[cnt] = false;
		loop(cnt + 1);
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		r = new room[n];
		used = new boolean[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			r[i] = new room(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(r, new Comparator<room>() {
			@Override
			public int compare(room o1, room o2) {
				return o1.end - o2.end;
			}
		});

		loop(0);

		System.out.println(max);
	}
}