// 문제를 못풀어서 다른 분들 풀이 참고해서 다시 풀어보도록 하겠습니다!!
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
            Arrays.sort(game, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0]!=o2[0] ? o1[0]-o2[0] : (o1[1]!=o2[1] ? o1[1]-o2[1] : o1[2]-o2[2]);
                }
            });

            int cnt = 1;
            int idx =0;
            for(int i=0; i<n; i++){
               if(game[idx][0] == game[i][0] && game[idx][2] <= game[i][1]){ // 날짜가 같은 경우만 진행, start 시간이 end보다 작거나 같으면 다음 진행 
                   cnt++;
                   idx = i;
               }
               else if(game[idx][0] != game[i][0]) {
                   idx = i;
               }
            }

            System.out.println(cnt);


        }
    }
}

