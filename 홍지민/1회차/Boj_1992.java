package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_1992 {
    static int[][] map;
    static StringBuilder sb;
    public static void zone(int r, int c, int div){
        if(check(r,c,div)){
            if(map[r][c] == 1) sb.append(1);
            else sb.append(0);
            return;
        }

        int n_div = div/2;
        sb.append("(");
        
        zone(r,c,n_div);
        zone(r,c+n_div, n_div);
        zone(r+n_div, c, n_div);
        zone(r+n_div, c+n_div, n_div);
        sb.append(")");
    }

    public static boolean check(int r, int c, int div){
        int first = map[r][c];

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
