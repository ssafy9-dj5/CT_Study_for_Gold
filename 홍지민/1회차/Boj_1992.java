package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_1992 {
    static int[][] map;
    static StringBuilder sb;
    public static void zone(int r, int c, int div){
        if(check(r,c,div)){ // 특정 구역의 모든 숫자가 같은지 검사 후 숫자 추가
            if(map[r][c] == 1) sb.append(1);
            else sb.append(0);
            return;
        }

        int n_div = div/2;
        sb.append("("); // 구역 나누기 시작할 때 ( 추가
        
        zone(r,c,n_div); // 2사분면
        zone(r,c+n_div, n_div); // 1사분면
        zone(r+n_div, c, n_div); // 3사분면 
        zone(r+n_div, c+n_div, n_div); // 4사분면
        sb.append(")"); // 모든 구역 탐색 후 ) 추가
    }

    public static boolean check(int r, int c, int div){
        int first = map[r][c]; // 왼쪽 위를 기준으로 지정

        for(int i=r; i<r+div; i++){
            for(int j=c; j<c+div; j++){
                if(map[i][j] != first) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int n= Integer.parseInt(br.readLine());

        map = new int[n][n];

        for(int i=0; i<n; i++){
            String s = br.readLine();
            for(int j=0; j<n; j++){
                map[i][j] = s.charAt(j)-'0';
            }
        }
        
        zone(0,0,n);

        System.out.println(sb);
    }
}
