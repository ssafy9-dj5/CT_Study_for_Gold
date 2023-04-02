package day0402;
import java.util.*;
 
public class Boj_1806_부분합{
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int s = sc.nextInt();
        
        int[] num = new int[n];
        for(int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }
        
        
        int start = 0;
        int end = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        
        while (true) {
            if (sum >= s) {
                sum -= num[start];
                ans = Math.min(end-start, ans);
                start++;
            } else if (end == n) {
                break;
            } else {
                sum += num[end++];
            }
        }
        
        System.out.println(ans == Integer.MAX_VALUE? 0 : ans);
    }
}