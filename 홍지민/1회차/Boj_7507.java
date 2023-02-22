package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj_7507 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int tc = 0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] game = new int[n][3];

            //경기 스케줄 입력
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<3; j++){
                    game[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            //0번째 인덱스 기준 정렬, 1번째 인덱스 기준 정렬, 2번째 인덱스 기준 정렬
            // 끝나는 시간이 빠를수록 더 많은 경기를 볼 수 있기 때문
            Arrays.sort(game, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0]!=o2[0] ? o1[0]-o2[0] : (o1[2]!=o2[2] ? o1[2]-o2[2] : o1[1]-o2[1]);
                }
            });

            int cnt = 1; // 볼 수 있는 경기 횟수
            int p = 0; // 현재 경기
            for(int i=0; i<n; i++){
                if(game[p][0] == game[i][0]){ // 같은 날짜인 경우만
                    if(game[p][2] <= game[i][1]){ // 현재 경기 종료시간이 다음 경기 시작시간보다 적거나 같은 경우만 
                        cnt++;
                        p=i;
                    }
                }
                if(game[p][0] != game[i][0]){ // 날짜가 다른 경우 무조건 볼 수 있기 때문에 cnt++
                    cnt++;
                    p=i;
                }
            }

            System.out.println("Scenario"+" "+"#"+tc+":");
            System.out.println(cnt);
            System.out.println();


        }
    }
}

