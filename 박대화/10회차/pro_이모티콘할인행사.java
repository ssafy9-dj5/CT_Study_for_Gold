import java.util.*;

class Solution {
    static int n, m;
    static int[][] users_arr;
    static int[] emoticons_arr;
    static int[] emo_sale;
    static int plusmax, salemax;
    
    public static void perm(int idx) {
        if(idx == m) {
            emoti();
            return;
        }
        
        for(int i = 1; i <= 4; i++) {
            emo_sale[idx] = i * 10;
            perm(idx + 1);
        }
    }
    
    public static void emoti() {
        int plus = 0;
        int tot = 0;
        for(int i = 0; i < n; i++){
            int sum = 0;
            for(int j = 0; j < m; j++) {
                if(emo_sale[j] >= users_arr[i][0]){
                    sum += emoticons_arr[j] - (emoticons_arr[j] * emo_sale[j] / 100);
                }
            }
            if(sum >= users_arr[i][1]){
                plus++;
            }else {
                tot += sum;
            }
        }
        
        if(plus > plusmax) {
            plusmax = plus;
            salemax = tot;
        }else if(plus == plusmax && tot > salemax) {
            salemax = tot;
        }
        
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        users_arr = users;
        emoticons_arr = emoticons;
        
        n = users.length;
        m = emoticons.length;
        
        emo_sale = new int[m];
        plusmax = 0;
        salemax = 0;
        
        perm(0);
        
        answer[0] = plusmax;
        answer[1] = salemax;
        
        return answer;
    }
}