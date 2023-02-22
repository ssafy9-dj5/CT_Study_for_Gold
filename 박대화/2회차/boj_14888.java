import java.util.Scanner;

public class Main {
	public static boolean nextperm(int[] arr) {
		int i = arr.length - 1;
		while (i > 0 && arr[i] <= arr[i - 1])
			i--;

		if (i == 0) {
			return false;
		}

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
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] arr = new int[n];
		int[] arithlist = new int[n - 1];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		int idx = 0;
		for (int i = 0; i < 4; i++) {
			int a = sc.nextInt();
			for (int j = 0; j < a; j++) {
				if (i == 0) {
					arithlist[idx++] = 0;
				} else if (i == 1) {
					arithlist[idx++] = 1;
				} else if (i == 2) {
					arithlist[idx++] = 2;
				} else {
					arithlist[idx++] = 3;
				}
			}
		}

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		do {
			int tot = arr[0];
			int index = 1;
			for (int i = 0; i < n - 1; i++) {
				if (arithlist[i] == 0) {
					tot += arr[index++];
				} else if (arithlist[i] == 1) {
					tot -= arr[index++];
				} else if (arithlist[i] == 2) {
					tot *= arr[index++];
				} else {
					tot /= arr[index++];
				}
			}
			if (tot > max) {
				max = tot;
			}
			if (tot < min) {
				min = tot;
			}
		} while (nextperm(arithlist));

		System.out.println(max);
		System.out.println(min);

	}
}