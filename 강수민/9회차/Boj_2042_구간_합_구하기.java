package G1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2042_구간_합_구하기 {
    private static long[] arr;
    private static long[] tree;

    private static long init(int start, int end, int node) { // start : 시작 인덱스, end : 끝 인덱스
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        // 재귀적으로 두 부분으로 나눈 뒤, 그 합을 자기 자신으로
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    private static long sum(int start, int end, int node, int left, int right) {
        // 범위 밖에 있는 경우
        if (left > end || right < start) {
            return 0;
        }
        // 범위 안에 있는 경우
        if (left <= start && end <= right) {
            return tree[node];
        }
        // 모두 아니면 두 부분으로 나누어 합을 구하기
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    private static void update(int start, int end, int node, int idx, long dif) {
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        tree = new long[N * 4];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(0, N - 1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
//            System.out.println(Arrays.toString(tree));
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 1) { // update
                int b = Integer.parseInt(st.nextToken()) - 1;
                update(0, N - 1, 1, b, Long.parseLong(st.nextToken()) - arr[b]);
            } else { // 구간 합 출력
                sb.append(sum(0, N - 1, 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1))
                        .append('\n');
            }
        }
        System.out.println(sb);
    }
}
/*
1 2 3 4 5
1 2 6 4 5
1 2 6 4 2
 */