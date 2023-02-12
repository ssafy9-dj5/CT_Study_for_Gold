package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj_2312 {
    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for(int tc = 0; tc<T; tc++){
                int num = Integer.parseInt(br.readLine());
                // 소인수분해
                for (int i = 2; i <= num; i++) {
                    int cnt = 0;
                    while (num % i == 0) {
                        num /= i;
                        cnt++;
                    }
                    
                    if (cnt != 0) sb.append(i).append(" ").append(cnt).append("\n");
                    if (num == 0) break;
                }
            }
            System.out.println(sb);
    }
}
