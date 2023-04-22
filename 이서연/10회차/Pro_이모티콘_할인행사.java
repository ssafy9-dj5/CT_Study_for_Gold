class Solution {
    static int[] sale = {10, 20, 30, 40};
    static int[] result; // 이모티콘별로 할인 인덱스 저장
    static int max_count, max_money;
    public int[] solution(int[][] users, int[] emoticons) {
        result = new int[emoticons.length];
        max_count = -1;
        max_money = -1;
        perm(0, users, emoticons);
        
        int[] answer = {max_count, max_money};
        return answer;
    }
    
    public void perm(int idx, int[][] users, int[] emoticons) {
        if (idx == result.length) {
            int count = 0;
            int money = 0;
            for (int i = 0; i < users.length; i++) {
                int sum = 0;
                int s1 = users[i][0];
                int s2 = users[i][1];
                boolean join = false;
                for (int j = 0; j < result.length; j++) {
                    if (sale[result[j]] >= s1) {
                        sum += emoticons[j] * (100 - sale[result[j]]) / 100;
                        if (sum >= s2) {
                            join = true;
                            break;
                        }
                    }
                }
                
                if (join) {
                    count++;
                } else {
                    money += sum;
                }
            }
            if (count > max_count) {
                max_count = count;
                max_money = money;
            } else if (count == max_count) {
                max_money = Math.max(money, max_money);
            }
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            result[idx] = i;
            perm(idx + 1, users, emoticons);
        }
    }
}