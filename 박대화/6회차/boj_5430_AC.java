import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());
		out: for (int t = 0; t < tc; t++) {
			char[] func = br.readLine().toCharArray();

			int n = Integer.parseInt(br.readLine());

			char[] arr = br.readLine().toCharArray();

			ArrayList<Integer> list = new ArrayList<>();

			int num = 0;
			for (int i = 1; i < arr.length; i++) {
				if (arr[i] >= '0' && arr[i] <= '9') {
					num = num * 10 + (arr[i] - '0');
				} else if (arr[i] == ',' || arr[i] == ']') {
					if (num == 0) {
						break;
					}
					list.add(num);
					num = 0;
				}
			}

			boolean isReverse = false;
			for (int i = 0; i < func.length; i++) {
				if (func[i] == 'R') {
					if (isReverse) {
						isReverse = false;
					} else {
						isReverse = true;
					}
				} else {
					if (list.isEmpty()) {
						System.out.println("error");
						continue out;
					}

					if (isReverse) {
						list.remove(list.size() - 1);
					} else {
						list.remove(0);
					}
				}
			}

			if (isReverse) {
				ArrayList<Integer> newlist = new ArrayList<>();
				for (int i = list.size() - 1; i >= 0; i--) {
					newlist.add(list.get(i));
				}

				list = newlist;
			}

			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i));
				if (i != list.size() - 1) {
					sb.append(",");
				}
			}
			sb.append("]");

			System.out.println(sb.toString());
		}
	}
}