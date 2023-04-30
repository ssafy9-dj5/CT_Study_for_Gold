class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int target = 0;
        while((target = find_target(n, deliveries, pickups)) > -1) {
            // 이동 거리
            answer += (target+1)*2;
            
            // 배달하기
            int cnt = cap;
            for(int i = target; i >= 0; i--) { // 뒤에서 부터 찾기
                if(deliveries[i] > 0) { // 배달 가야 되는 집임
                    if (deliveries[i] <= cnt) { // 남은걸로 가능
                        cnt -= deliveries[i];
                        deliveries[i] = 0;
                    } else { // 이거라도 배달
                        deliveries[i] -= cnt;
                        cnt = 0;
                    }
                }
                if(cnt == 0) break;
            }
            
            // 수거하기
            for(int i = target; i >= 0; i--) { // 뒤에서 부터 찾기
                if(pickups[i] > 0) { // 수거 해야 되는 집임
                    if (cnt + pickups[i] <= cap) { // 남은 공간으로 가능
                        cnt += pickups[i];
                        pickups[i] = 0;
                    } else { // 조금이라도 수거
                        pickups[i] -= cap - cnt;
                        cnt = cap;
                    }
                }
                if(cnt == cap) break;
            }
        }
        
        return answer;
    }
    
    // 가야되는 곳 중 가장 먼 곳
    private int find_target(int n, int[] deliveries, int[] pickups) {
        for(int i = n-1; i >= 0; i--) {
            if (deliveries[i] > 0 || pickups[i] > 0) {
                return i;
            }
        }
        return -1;
    }
}