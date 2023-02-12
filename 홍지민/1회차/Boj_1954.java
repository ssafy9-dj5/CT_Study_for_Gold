package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Boj_1954 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] s = new int[n][2];
        int result =0;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            s[i][0]= Integer.parseInt(st.nextToken());
            s[i][1]= Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        
        
        int gas =0; // 생성되는 가스
        for(int i=1; i<=m-(n-1); i++){
            int use = i; //n개의 시약이 사용한 용액의 양
            int num = 0; //특정 시약이 사용한 용액의 양
            gas = s[0][0]*i + s[0][1];
            for(int j=1; j<n; j++){
                num = (gas - s[j][1])/s[j][0];
                use += num;
            }
            if(use == m) { // 사용한 용액의 양이 주어진 용액의 양과 동일하면 종료
                result = 1;
                break;
            }
        }
        if(result == 1) System.out.println(gas);
        else System.out.println(0);
    }
}