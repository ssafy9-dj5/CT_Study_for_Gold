import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int root = 0;

		List[] arr = new List[n];

		for (int i = 0; i < n; i++) {
			arr[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < n; i++) {
			int num = sc.nextInt();
			if (num == -1) {
				root = i;
			} else {
				arr[num].add(i);
			}
		}

		int del = sc.nextInt();

		if (del == root) {
			System.out.println(0);
		} else {
			delete(arr, del);

			for (int i = 0; i < n; i++) {
				if (arr[i] != null && arr[i].size() != 0) {
					for (int j = 0; j < arr[i].size(); j++) {
						if ((int) arr[i].get(j) == del) {
							arr[i].remove(j);
						}
					}
				}
			}

			int cnt = 0;
			for (int i = 0; i < n; i++) {
				if (arr[i] != null && arr[i].size() == 0) {
					cnt++;
				}
			}

			System.out.println(cnt);
		}
	}

	public static void delete(List[] arr, int del) {
		for (int i = 0; i < arr[del].size(); i++) {
			int num = (int) arr[del].get(i);
			if (arr[num].size() != 0) {
				delete(arr, num);
			}
			arr[num] = null;
		}
		arr[del] = null;
	}
}