package boj.g1;

import java.util.Scanner;

public class Boj2042_구간_합_구하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		long[] num = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			num[i] = sc.nextLong();
		}

		SegmentTree st = new SegmentTree(N);
		st.init(num, 1, 1, N);
//		st.printTree();
		for (int t = 0; t < M + K; t++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			long c = sc.nextLong();

			if (a == 1) { // b번째 수를 c로 바꿈
				// 기존의 값 구하기
				long before = st.sum(1, 1, N, b, b);
				long diff = c - before;
				st.update(1, 1, N, b, diff);
//				st.printTree();
			} else if (a == 2) { // b번째 수부터 c번째 수까지의 합을 구하여 출력
				long sum_result = st.sum(1, 1, N, b, (int) c);
				System.out.println(sum_result);
			}
		}
		sc.close();
	}

	static class SegmentTree {
		private long[] tree; // 세그먼트 트리를 구현할 배열
		int treeSize; // 트리의 크기

		public SegmentTree(int arrSize) { // 생성자에서 세그먼트 트리의 전체 노드 수 계산
			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2)); // 트리 높이 구하기
			this.treeSize = (int) Math.pow(2, h + 1); // 트리에 들어가는 노드의 개수는 2^(h+1) 미만 개
			tree = new long[treeSize];
		}

		public void printTree() {
			for (int i = 1; i < treeSize; i++) {
				System.out.print(tree[i] + " ");
			}
		}

		long init(long[] arr, int node_idx, int start, int end) { // 최초 Segment Tree의 노드 값 초기화
			if (start == end) { // leaf 노드
				return tree[node_idx] = arr[start]; // 리프 노드에 배열의 값 저장 후 리턴
			} else { // 리프 노드가 아닌 경우 자식 노드의 값을 더해서 노드의 값 저장 후 리턴
				return tree[node_idx] = init(arr, node_idx * 2, start, (start + end) / 2)
						+ init(arr, node_idx * 2 + 1, (start + end) / 2 + 1, end);
			}
		}

		long sum(int node_idx, int start, int end, int left, int right) { // 구간 합을 구할 구간의 시작과 끝 : left, right
			if (end < left || right < start) { // 노드가 가지는 값의 구간이 구하려고 하는 합의 구한에 속하지 않는 경우 (0 리턴)
				return 0;
			}

			if (left <= start && end <= right) { // 노드가 가지는 값의 구간이 구하려는 합의 구간에 완전히 포함되는 경우 (더 내려가지 않고 리턴)
				return tree[node_idx];
			}

			// 그 외의 경우 자식노드를 탐색해서 값을 리턴
			return sum(node_idx * 2, start, (start + end) / 2, left, right)
					+ sum(node_idx * 2 + 1, (start + end) / 2 + 1, end, left, right);
		}

		void update(int node_idx, int start, int end, int idx, long diff) { // 배열의 특정 인덱스의 값이 변경 될 경우 세그먼트 트리의 값 변경
			if (idx < start || end < idx) { // 노드가 가지는 값의 구간에 변경할 index가 포함되지 않을 경우
				return;
			} else { // 노드가 가지는 값의 구간에 변경할 index가 포함되는 경우
				tree[node_idx] += diff; // diff(변경할 값 - 기존값)를 영향 받는 각 노드에 더해 줌

				if (start != end) { // 노드가 리프노드가 아닌 경우 계속 자식노드를 탐색
					update(node_idx * 2, start, (start + end) / 2, idx, diff);
					update(node_idx * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
				}
			}
		}
	}
}
