import java.util.Scanner;

public class BOJ_2133_타일채우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n % 2 == 0) {
            int[] arr = new int[n + 1];
            arr[0] = 1;
            arr[2] = 3;
            int idx = 4;
            while (idx <= n) {
                arr[idx] = arr[idx - 2] * 4 - arr[idx - 4];
                idx += 2;
            }
            System.out.println(arr[n]);
        } else {
            System.out.println(0);
        }
    }
}
