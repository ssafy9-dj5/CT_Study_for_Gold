import java.util.*;

class Solution {
    public int[] solution(String s) {
        int len = s.length();
        int[] answer = new int[len];
        Map<Character, Integer> idx_map = new HashMap<>();
        
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (idx_map.containsKey(c)) {
                answer[i] = i - idx_map.get(c);
                idx_map.put(c, i);
            } else {
                answer[i] = -1;
                idx_map.put(c, i);
            }
        }
        
        return answer;
    }
}