package day0319;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Boj_9019_DSLR {
	static int a,b;
	static String com;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=0; tc<T; tc++) {
			a = sc.nextInt();
			b = sc.nextInt();
			
			
			System.out.println(bfs());
		}
	}
	private static String bfs() {
		Queue<cal> q = new ArrayDeque<>();
		q.add(new cal(a,""));
		boolean[] used = new boolean[10002];
		
		while(!q.isEmpty()) {
			cal now = q.poll();
			if(now.num == b) {
				return now.oper;
			}
			
			int numD = (now.num *2) % 10000;
			if(!used[numD]) {
				used[numD] = true;
				q.add(new cal(numD, now.oper+"D" ));
			}
			
			int numS = 0;
			if(now.num == 0) numS = 9999;
			else numS = now.num-1;
			if(!used[numS]) {
				used[numS] = true;
				q.add(new cal(numS, now.oper+"S" ));
			}
			
			int numL = ((now.num % 1000) * 10) + (now.num /1000);
			if(!used[numL]) {
				used[numL] = true;
				q.add(new cal(numL, now.oper+"L"));
			}
			
			int numR = ((now.num % 10) * 1000) + (now.num/10);
			if(!used[numR]) {
				used[numR] = true;
				q.add(new cal(numR, now.oper+"R"));
				
			}
		}
		return "";
		
	}

	static class cal{
		int num;
		String oper;
		public cal(int num, String oper) {
			this.num = num;
			this.oper = oper;
		}
	}
}
