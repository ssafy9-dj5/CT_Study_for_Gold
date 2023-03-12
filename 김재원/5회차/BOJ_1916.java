package day0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 다익스트라 학습 미흡으로 인터넷 솔루션과 학습자료 참조하여 재구성 했습니다.

public class BOJ_1916 {
    static int N;
    static boolean[] visited;
    static long[] dijkstra;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        long[][] map = new long[N][N];

        for(int i = 0; i<N; i++)
            for(int j = 0; j<N; j++){
                if(i == j) {
                    map[i][j] = 0;
                    continue;
                }

                map[i][j] = Integer.MAX_VALUE;
            }

        for(int i = 0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int val = Integer.parseInt(st.nextToken());

            map[row][col] = Math.min(map[row][col], val);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken()) - 1;
        int to = Integer.parseInt(st.nextToken()) - 1;

        dijkstra = map[start].clone();

        visited = new boolean[N];
        visited[start] = true;

        for(int i = 1; i<N; i++) {
            int idx = choose();
            visited[idx] = true;

            for(int j = 0; j < N; j++)
                if(!visited[j])
                    dijkstra[j] = Math.min(dijkstra[j], dijkstra[idx] + map[idx][j]);
        }

        System.out.println(dijkstra[to]);
    }

    static int choose(){
        int minIdx = 0;
        long min = Integer.MAX_VALUE;

        for(int i = 0; i<N; i++){
            if(visited[i])
                continue;

            if(min>dijkstra[i]){
                min = dijkstra[i];
                minIdx = i;
            }
        }

        return minIdx;
    }
}