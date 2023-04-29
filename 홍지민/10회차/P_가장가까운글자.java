package day0423;

class P_가장가까운글자 {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        boolean[] exist = new boolean[26];
        
        for(int i=0; i<s.length(); i++){
            int num = s.charAt(i)-97;
            if(!exist[num]){
                answer[i] = -1;
                exist[num] = true;
            }
            else {
                int idx = 1;
                for(int j=i-1; j>=0; j--){
                    if(s.charAt(j) == s.charAt(i)){
                        answer[i] = idx;
                        break;
                    }
                    idx++;
                }
            }
        }
        return answer;
    }
}