package G4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2110_공유기_설치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] home = new int[N];
        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home);

        int start = 1;
        int end = home[N - 1] - home[0];
        int answer = 0;

        // 집들 사이의 거리 기준으로 이분탐색
        while (start <= end) {
            int mid = (start + end) / 2;
            int cnt = 1;
            int temp = home[0];
            for (int i = 1; i < N; i++) {
                if (home[i] - temp >= mid) {
                    cnt++;
                    temp = home[i];
                }
            }

            // 공유기 거리 늘려서 재설치해보기
            if (cnt >= C) {
                answer = Integer.max(answer, mid);
                start = mid + 1;
            }
            // 공유기 거리 줄여서 재설치 해보기
            else end = mid - 1;
        }
        System.out.print(answer);
    }
}
