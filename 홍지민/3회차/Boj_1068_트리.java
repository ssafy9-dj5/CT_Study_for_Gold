package day0226;

import java.util.Scanner;

public class Boj_1068_트리 {
    static int[] node;
    static int n, d, cnt;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cnt = 0;
        visited = new boolean[n];
        node = new int[n];

        int start = 0;
        for (int i = 0; i < n; i++) {
            node[i] = sc.nextInt();
            if(node[i] == -1) start = i;
        }
        d = sc.nextInt();

        del(d);
        count(start);
        System.out.println(cnt);
    }

    private static void count(int idx) {
        visited[idx] = true;
        int flag =0;
        if (node[idx] != -2) {
            for (int i = 0; i < n; i++) {
                if (node[i] == idx && !visited[i]) {
                    count(i);
                    flag =1;
                }
            }
            if(flag==0) cnt++;
        }
    }

    private static void del(int idx) {
        node[idx] = -2;
        for (int i = 0; i < n; i++) {
            if (node[i] == idx) {
                del(i);
            }
        }
    }
}

