package week11;

import java.util.*;
// 풀이 봤음 
public class P택배배달 {
	    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
	       long answer = 0;
	        int deliver =0;
	        int pickup =0;
	        for(int i=n-1;i>=0;i--){//끝에서 부터 탐색하기
	           
	            if(deliveries[i]!=0 || pickups[i]!=0){
	                int c =0;
	                while(deliver<deliveries[i]||pickup<pickups[i]){
	                    c++;
	                    deliver+=cap;
	                    pickup+=cap;
	                }
	                deliver-=deliveries[i];
	                pickup -=pickups[i];
	                answer +=(i+1)*c*2;
	                
	            }
	        }
	        
	        
	        
	        return answer;
	    }

	  
}
