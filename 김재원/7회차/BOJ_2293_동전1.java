import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2293_동전1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int coin[] = new int[n];
		int coinSum[] = new int[k + 1];
		for (int i = 0; i < coin.length; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		coinSum[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = coin[i]; j <= k; j++) {
				coinSum[j] += coinSum[j - coin[i]];
			}
		}

		System.out.println(coinSum[k]);
	}
}