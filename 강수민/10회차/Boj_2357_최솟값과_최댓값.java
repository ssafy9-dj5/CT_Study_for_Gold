package G1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2357_최솟값과_최댓값 {
    private static long[] arr;
    private static long[][] tree;

    private static long[] init(int start, int end, int node) { // start : 시작 인덱스, end : 끝 인덱스
        if (start == end) {
            tree[node][0] = arr[start];
            tree[node][1] = arr[start];
            return tree[node];
        }
        int mid = (start + end) / 2;
        long[] left = init(start, mid, node * 2);
        long[] right = init(mid + 1, end, node * 2 + 1);
        return new long[]{tree[node][0] = Math.min(left[0], right[0]), tree[node][1] = Math.max(left[1], right[1])};
    }

    private static long[] minmax(int start, int end, int node, int left, int right) {
        // 범위 밖에 있는 경우
        if (left > end || right < start) {
            return new long[]{Long.MAX_VALUE, Long.MIN_VALUE};
        }
        // 범위 안에 있는 경우
        if (left <= start && end <= right) {
            return tree[node];
        }
        // 모두 아니면 두 부분으로 나누어 합을 구하기
        int mid = (start + end) / 2;
        long[] left_temp = minmax(start, mid, node * 2, left, right);
        long[] right_temp = minmax(mid + 1, end, node * 2 + 1, left, right);
        return new long[]{Math.min(left_temp[0], right_temp[0]), Math.max(left_temp[1], right_temp[1])};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new long[N];
        tree = new long[N * 4][2];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // 구간의 최소 최대값
            long[] result = minmax(0, N - 1, 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            sb.append(result[0]).append(' ').append(result[1]).append('\n');
        }
        System.out.print(sb);
    }
}
