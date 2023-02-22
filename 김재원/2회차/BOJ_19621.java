import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int arr[][];
	static int maxPtcp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new int[n][3];
		StringTokenizer st;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // start
			arr[i][1] = Integer.parseInt(st.nextToken()); // end
			arr[i][2] = Integer.parseInt(st.nextToken()); // participate
		}

		maxPtcp = 0;
		subset(0, 0);

		System.out.println(maxPtcp);
	}

	static void subset(int idx, int res) {
		if (idx >= n) {
			maxPtcp = Math.max(maxPtcp, res);
			return;
		}

		subset(idx + 2, res + arr[idx][2]);
		subset(idx + 1, res);
	}
}