
import java.math.BigInteger;
import java.util.Scanner;

public class Boj_1914_하노이탑 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		BigInteger b = new  BigInteger("2");
		BigInteger o = new  BigInteger("1");
		// 하노이 탑 이동 횟수 : 2^n-1
		System.out.println(b.pow(n).subtract(o));
		
		if(n <= 20) {
			hanoi(n, 1, 2, 3);
		}
	}

	private static void hanoi(int n, int start, int mid, int end) {
		if(n == 1) {
			System.out.println(start+" "+end);
			return;
		}
		
		//n-1개 원판 옮기기
		hanoi(n-1, start, end, mid);
		// 가장 큰 원반 마지막 막대로 옮기기
		System.out.println(start + " " + end);
		//중간 막대 원판을 마지막 막대로 옮기기
		hanoi(n-1, mid, start, end);

		return;
	}
}
