package week10;
import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        int[] alp = new int[26];
        Arrays.fill(alp,-1);
        for(int i=0;i<s.length();i++){
            char now = s.charAt(i);
            if(alp[now-'a']==-1)
                answer[i] = alp[now-'a'];
            else
                answer[i] = i-alp[now-'a'];
            alp[now-'a']=i;
        }
        return answer;
    }
}