import java.util.Arrays;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        int[] alph = new int[26];
        Arrays.fill(alph, -1);
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (alph[c-'a'] > -1) {
                answer[i] = i - alph[c-'a'];
            }
            else {
                answer[i] = -1;
            }
            alph[c-'a'] = i;
        }
        
        return answer;
    }
}