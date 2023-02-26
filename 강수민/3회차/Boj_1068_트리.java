package G5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1068_트리 {
    private static int N, cnt;
    private static ArrayList<Integer>[] adjList;
    public static void test(String s) throws IOException {
        // Input
        StringTokenizer st = new StringTokenizer(s);

        N = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N];

        Queue<Pair> queue = new ArrayDeque<>();
        int start = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num == -1) {
                start = i;
                adjList[i] = new ArrayList<>();
            } else if (adjList[num] != null) {
                adjList[num].add(i);
                adjList[i] = new ArrayList<>();
                adjList[i].add(num);
            } else {
                queue.add(new Pair(i, num));
            }
        }

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int num = pair.num;
            int i = pair.i;
            if (adjList[num] != null) {
                adjList[num].add(i);
                adjList[i] = new ArrayList<>();
                adjList[i].add(num);
            } else {
                queue.add(new Pair(i, num));
            }
        }

//        print();

        // Delete node
        cnt = 0;
        int target = Integer.parseInt(st.nextToken());
        if (target != start) { // 부모 노드가 있으면, 부모 노드에서 타겟 삭제
            adjList[adjList[target].get(0)].remove(Integer.valueOf(target));

            for (int i = 0; i < N; i++) { // 루트 노드가 아니면, 부모노드 정보 삭제
                if (i != start)
                    adjList[i].remove(0);
            }

            dfs_delete(target);

            for (ArrayList<Integer> arr: adjList) {
                if (arr != null && arr.size() == 0) cnt++;
            }
        }
//        print();

        // Output
        System.out.println(cnt);
    }

    private static void dfs_delete(int target) {
        while (adjList[target].size() > 0) {
            dfs_delete(adjList[target].get(0));
            adjList[target].remove(0);
        }
        adjList[target] = null;
    }

    private static class Pair {
        int i, num;

        public Pair(int i, int num) {
            this.i = i;
            this.num = num;
        }
    }

    private static void print() {
        System.out.println("=========================");
        for (ArrayList<Integer> arr : adjList) {
            System.out.println(arr);
        }
    }

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N];

        Queue<Pair> queue = new ArrayDeque<>();
        int start = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num == -1) {
                start = i;
                adjList[i] = new ArrayList<>();
            } else if (adjList[num] != null) {
                adjList[num].add(i);
                adjList[i] = new ArrayList<>();
                adjList[i].add(num);
            } else {
                queue.add(new Pair(i, num));
            }
        }

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int num = pair.num;
            int i = pair.i;
            if (adjList[num] != null) {
                adjList[num].add(i);
                adjList[i] = new ArrayList<>();
                adjList[i].add(num);
            } else {
                queue.add(new Pair(i, num));
            }
        }

//        print();

        // Delete node
        cnt = 0;
        int target = Integer.parseInt(br.readLine());
        if (target != start) { // 부모 노드가 있으면, 부모 노드에서 타겟 삭제
            adjList[adjList[target].get(0)].remove(Integer.valueOf(target));

            for (int i = 0; i < N; i++) { // 루트 노드가 아니면, 부모노드 정보 삭제
                if (i != start)
                    adjList[i].remove(0);
            }

            dfs_delete(target);

            for (ArrayList<Integer> arr: adjList) {
                if (arr != null && arr.size() == 0) cnt++;
            }
        }
//        print();

        // Output
        System.out.println(cnt);
    }
}