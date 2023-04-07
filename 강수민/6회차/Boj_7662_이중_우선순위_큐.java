package G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_7662_이중_우선순위_큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        // TestCase
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            // Input
            int k = Integer.parseInt(br.readLine());
            Queue<Integer> max_pq = new PriorityQueue<>(k, Collections.reverseOrder());
            Queue<Integer> min_pq = new PriorityQueue<>(k);
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                char order = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());
                // Orders
                if (order == 'I') { // Inque
                    max_pq.add(num);
                    min_pq.add(num);
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else {            // Deque
                    if (num == 1) {     // max_pq deque
                        deque(max_pq, map);
                    } else {            // min_pq deque
                        deque(min_pq, map);
                    }
                }
            }
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            boolean flag = true;

            for (int key : map.keySet()) {
                int num = map.get(key);
                if (num > 0) {
                    flag = false;
                    max = Math.max(max, key);
                    min = Math.min(min, key);
                }
            }

            if (flag) {
                sb.append("EMPTY").append('\n');
            } else {
                sb.append(max).append(' ').append(min).append('\n');
            }
        }
        System.out.print(sb);
    }

    private static void deque(Queue<Integer> queue, Map<Integer, Integer> map) {
        while (!queue.isEmpty()) {
            int num = queue.poll();

            if (map.get(num) > 0) {
                map.put(num, map.get(num) - 1);
                break;
            }
        }
    }
}
