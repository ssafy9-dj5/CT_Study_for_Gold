package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16928�����ٸ����� {
	static int[] ladder, snake;
	static boolean[] game;
	static int cnt,idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ladder = new int[101];
		snake = new int[101];
		game = new boolean[101];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			ladder[n1] = n2;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int m1 = Integer.parseInt(st.nextToken());
			int m2 = Integer.parseInt(st.nextToken());
			snake[m1] = m2;
		}

		dice();
		System.out.println(cnt);

	}


	private static void dice() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		while (true) {
			int num = queue.size();//�ֻ��� ���� Ƚ�� ��ŭ �� �׷�
			cnt++;
			for (int j = 0; j < num; j++) {
				int now = queue.poll();
				for (int i = 1; i <= 6; i++) {//�ֻ��� ��ŭ �̵�
					idx = now + i;
					if (idx == 100)//100��ĭ ��!
						return;
					if (game[idx]) //���� �鸰 ĭ�̶�� ��ŵ
						continue;
					if(check()) //��, ��ٸ��� ���� 100���� ������
						return;
					queue.add(idx);
					
				}
			}
		}

	}

	private static boolean check() {
		if(idx==100) {
			return true;
		}
		if(game[idx])
			return false;
		game[idx]=true;
		if (ladder[idx] != 0) {//��ٸ�
			idx = ladder[idx];
			check();//��ٸ� Ÿ�� �ش� ĭ���� �̵�
		}

		else if (snake[idx] != 0) {//��
			idx = snake[idx];
			check();//�� �̿��� �ش� ĭ���� �̵�
		}
		return false;

	}
}
