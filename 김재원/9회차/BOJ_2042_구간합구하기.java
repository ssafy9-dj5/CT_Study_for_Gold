import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042_구간합구하기 {
  static long[] arr, tree;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    arr = new long[N + 1];
    for (int i = 1; i <= N; i++) {
      arr[i] = Long.parseLong(br.readLine());
    }

    // 2^k >= N인 최소의 k를 찾아야 함.
    // 양변에 log을 취하면,
    // k >= logN / log2
    // (logN / log2)의 값을 올림한 후 1을 더하면 k가 됨.
    // 위에서 구한 k를 제곱하면 세그먼트 트리의 size를 구할 수 있음.

    // int k = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
    // int size = (int) Math.pow(2, k);
    //
    // tree = new long[size];

    // 사이즈를 구하는 위의 과정이 귀찮으면, 단순히 N에 4를 곱한 사이즈를 사용해도 무방함.
    tree = new long[N * 4];

    init(1, N, 1);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < M + K; i++) {
      st = new StringTokenizer(br.readLine());

      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      long c = Long.parseLong(st.nextToken());

      if (a == 1) {
        long dif = c - arr[b];
        arr[b] = c;
        update(1, N, 1, b, dif);
      } else if (a == 2) {
        sb.append(sum(1, N, 1, b, (int) c) + "\n");
      }
    }
    System.out.println(sb.toString());
  }

  // start: 시작 인덱스, end: 끝 인덱스
  public static long init(int start, int end, int node) {
    if (start == end) {
      return tree[node] = arr[start];
    }

    int mid = (start + end) / 2;

    // 재귀적으로 두 부분으로 나눈 뒤에 그 합을 자기 자신으로 함.
    return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
  }

  // start: 시작 인덱스, end: 끝 인덱스
  // left, right: 구간 합을 구하고자 하는 범위
  public static long sum(int start, int end, int node, int left, int right) {
    // 범위 밖에 있는 경우
    if (left > end || right < start) {
      return 0;
    }

    // 범위 안에 있는 경우
    if (left <= start && end <= right) {
      return tree[node];
    }

    // 그렇지 않다면, 두 부분으로 나누어 합을 구하기
    int mid = (start + end) / 2;
    return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
  }

  // start: 시작 인덱스, end: 끝 인덱스
  // idx: 구간 합을 수정하고자 하는 노드
  // dif: 수정할 값
  public static void update(int start, int end, int node, int idx, long dif) {
    // 범위 밖에 있는 경우
    if (idx < start || idx > end) {
      return;
    }

    // 범위 안에 있으면 내려가며 다른 원소도 갱신
    tree[node] += dif;
    if (start == end) {
      return;
    }

    int mid = (start + end) / 2;
    update(start, mid, node * 2, idx, dif);
    update(mid + 1, end, node * 2 + 1, idx, dif);
  }
}
