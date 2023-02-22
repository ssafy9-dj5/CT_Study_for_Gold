package st;
import java.util.ArrayList;
import java.util.Scanner;

public class Boj_14888 {
	static int[] mix, num;
	static boolean[] used;
	static ArrayList<Integer> list;
	static int min, max;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		num = new int[n];
		mix = new int[n-1];
		list = new ArrayList<>();
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		for(int i=0; i<n; i++) {
			num[i] = sc.nextInt();
		}
		
		for(int i=0; i<4; i++) {
			int o = sc.nextInt();
			if(o != 0) {
				for(int j=0; j<o; j++) {
					list.add(i);
				}
			}
		}

		used = new boolean[list.size()];
		
		perm(0);
		
		System.out.println(max);
		System.out.println(min);
		
	}

	private static void perm(int cnt) {
		if(cnt == mix.length) {
			cal(mix);
			return;
		}
		
		for(int i=0; i<list.size(); i++) {
			if(used[i]) continue;
			mix[cnt] = list.get(i);
			used[i] = true;
			perm(cnt+1);
			used[i] = false;
		}
	}

	private static void cal(int[] mix) {
		int result = num[0];
		
		for(int i=0; i<mix.length; i++) {
			switch(mix[i]){
				case 0:
					result += num[i+1];
					break;
				case 1:
					result -= num[i+1];
					break;
				case 2:
					result *= num[i+1];
					break;
				case 3:
					if(result > 0) {
						result /= num[i+1];
						break;
					}
					else if(result < 0){
						result *= (-1);
						result /= num[i+1];
						result *= (-1);
						break;
					}
			}	
		}
		
		if(result <= min) {
			min = result;
		}
		if(result >= max) {
			max = result;
		}
	}
}
