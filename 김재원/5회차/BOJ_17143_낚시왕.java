package day0303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  구현 실패
1. 낚시왕이 오른쪽으로 한 칸 이동한다. (낚시왕은 총 C번 이동한다.)
2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 
3. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
4. 상어가 이동한다.
5. 상어는 이동 할 때 부딫히면 반대방향으로 이동한다.
6. 이동후 겹치는 상어가 있다면 더 큰 상어가 잡아먹는다.
7. 이때 낚시왕이 잡은 상어의 크기의 합을 구하여라
 */
public class BOJ_17143_낚시왕 {
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	static class Shark {
		int x, y, vel, dir, size;
		boolean isAlive = true;

		public Shark(int x, int y, int vel, int dir, int size) {
			super();
			this.x = x;
			this.y = y;
			this.vel = vel;
			this.dir = dir - 1;
			this.size = size;
		}
	}

	static int sumOfSharkSize = 0;
	static int r, c, m;
	static Shark[] sharks;

	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		sharks = new Shark[m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int vel = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			sharks[i] = new Shark(x, y, vel, dir, size);
		}
		// check
		for (int i = 1; i <= 1; i++) {
			// 낚시
			fishing(i);
			// 상어 이동
			for (int j = 0; j < m; j++) {
				if (sharks[j].isAlive)
					moveShark(sharks[j]);
				System.out.println(sharks[j].x + " " + sharks[j].y + " " + sharks[j].dir);
			}
			// 잡아먹기 체크
			eating();
		}

		// output
		System.out.println(sumOfSharkSize);
	}

	static void fishing(int myCol) {
		int tmpMinX = Integer.MAX_VALUE;
		int tmpMinIdx = -1;
		for (int i = 0; i < m; i++) {
			if (sharks[i].isAlive && sharks[i].y == myCol && tmpMinX > sharks[i].x) {
				tmpMinX = sharks[i].x;
				tmpMinIdx = i;
			}
		}

		if (tmpMinIdx == -1)
			return;

		sumOfSharkSize += sharks[tmpMinIdx].size;
		sharks[tmpMinIdx].isAlive = false;
	}

	static void moveShark(Shark sh) {
		if (sh.dir == 0) {
			if (sh.x - sh.vel >= 1) {
				sh.x -= sh.vel;
			} else {
				int tmp = Math.abs(sh.x - sh.vel);
				if ((tmp / (r - 1)) % 2 == 0) {
					sh.dir = 1;
					sh.x = tmp % (r - 1) + 2;
				} else {
					sh.dir = 0;
					sh.x = r - (tmp % (r - 1) + 1);
				}
			}
		} else if (sh.dir == 1) {
			if (sh.x + sh.vel <= r) {
				sh.x += sh.vel;
			} else {
				int tmp = Math.abs(sh.x + sh.vel);
				if ((tmp / (r - 1)) % 2 == 0) {
					sh.dir = 0;
					sh.x = r - (tmp % (r - 1) + 2);
				} else {
					sh.dir = 1;
					sh.x = tmp % (r - 1) + 3;
				}
			}
		} else if (sh.dir == 2) {
			if (sh.y + sh.vel <= c) {
				sh.y += sh.vel;
			} else {
				int tmp = Math.abs(sh.y + sh.vel);
				if ((tmp / (c - 1)) % 2 == 0) {
					sh.dir = 0;
					sh.y = c - (tmp % (c - 1) + 2);
				} else {
					sh.dir = 1;
					sh.y = tmp % (c - 1) + 3;
				}
			}

		} else if (sh.dir == 3) {
			if (sh.y - sh.vel >= 1) {
				sh.y -= sh.vel;
			}
			int tmp = Math.abs(sh.y - sh.vel);
			if ((tmp / (c - 1)) % 2 == 0) {
				sh.dir = 2;
				sh.y = tmp % (c - 1) + 2;
			} else {
				sh.dir = 3;
				sh.y = c - (tmp % (c - 1) + 1);
			}
		}

//		for (int i = 0; i < sh.vel; i++) {
//			int nx = sh.x + dx[sh.dir];
//			int ny = sh.y + dy[sh.dir];
//			if (nx <= 0 || ny <= 0 || nx > r || ny > c) {
//				if (sh.dir == 0)
//					sh.dir = 1;
//				else if (sh.dir == 1)
//					sh.dir = 0;
//				else if (sh.dir == 2)
//					sh.dir = 3;
//				else if (sh.dir == 3)
//					sh.dir = 2;
//
//				nx = sh.x + dx[sh.dir];
//				ny = sh.y + dy[sh.dir];
//			}
//			sh.x = nx;
//			sh.y = ny;
//		}
	}

	static void eating() {
		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++) {
				if (!sharks[i].isAlive || !sharks[j].isAlive)
					continue;
				if (sharks[i].x == sharks[j].x && sharks[i].y == sharks[j].y) {
					if (sharks[i].size < sharks[j].size)
						sharks[i].isAlive = false;
					else
						sharks[j].isAlive = false;
				}
			}
		}
	}
}
