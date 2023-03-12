package G5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj_1916_최소비용_구하기 {
    private static class City implements Comparable<City> {
        int num;
        long weight;

        public City(int num, long weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(City o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[N+1];
        ArrayList<City>[] adjList = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st = null;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            adjList[Integer.parseInt(st.nextToken())].add(new City(Integer.parseInt(st.nextToken()), (long)Integer.parseInt(st.nextToken())));
        }

        // Dijkstra

        PriorityQueue<City> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        pq.add(new City(Integer.parseInt(st.nextToken()),0));

        int end = Integer.parseInt(st.nextToken());
        int cur;
        long min = 0;
        while (!pq.isEmpty()){

            City city = pq.poll();
            cur = city.num;
            min = city.weight;

            if (cur == end) break;

            visited[cur] = true;

            for (City adjCity : adjList[cur]) {
                if (!visited[adjCity.num]) {
                    pq.add(new City(adjCity.num, min+ adjCity.weight));
                }
            }
        }
        System.out.println(min);
    }
}
