import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10942_팬린드롬 {
    static boolean[][] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int []arr = new int[n + 1];
        check = new boolean[n + 1][n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i <= n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        dp(arr, n);
        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(check[start][end]) 
                sb.append("1\n");
            else 
                sb.append("0\n");
        }

        System.out.println(sb);
    }
    public static void dp(int[] arr, int n){
        for(int i = 1; i <= n; i++)
            check[i][i] = true;

        for(int i = 1; i <= n - 1; i++)
            if(arr[i] == arr[i + 1]) check[i][i + 1]= true;

        for(int i = 2; i < n; i++){
            for(int j = 1; j <= n - i; j++){
                if(arr[j] == arr[j + i] && check[j + 1][j + i - 1])
                    check[j][j + i] = true;
            }
        }
    }
}