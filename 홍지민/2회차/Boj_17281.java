package st;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17281 {

    static int N, result = -1;
    static boolean[] used = new boolean[10];
    static int[] players = new int[10];
    static int[][] in;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        in = new int[N][10];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=9;j++) {
                in[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        // 4번 고정
        players[4] = 1;
        used[4]=true;

        permutation(2);
        System.out.println(result);

    }

    private static void permutation(int cnt) {

        if(cnt==10) {
            result = Math.max(result, game());
            return;
        }

        for(int i=1;i<=9;i++) {
            if(!used[i]) {
                used[i] = true;
                players[i] = cnt;
                permutation(cnt+1);
                used[i] = false;
            }
        }
    }


    private static int game() {
        int start = 1;
        int score = 0;

        for(int i=0;i<N;i++) {

            // 주자위치 저장
            int[] point = {0,0,0,0,0};

            // 아웃 3번되면 종료
            while(point[0]<3) {
                run(point,in[i][players[start]]);
                if(start==9) {
                    start=1;
                }else {
                    start++;
                }
            }

            // 이닝 끝날 때마다 점수 저장
            score += point[4];
        }
        return score;
    }

    private static void run(int[] point, int n) {

        for(int i=0;i<n;i++) {
            point[4]+=point[3];
            point[3]=point[2];
            point[2]=point[1];
            point[1]=0;
        }

        // 새로 친 주자의 위치 저장
        point[n]++;
    }



}