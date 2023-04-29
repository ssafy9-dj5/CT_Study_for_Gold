package week11;

import java.util.*;
//틀렸음
public class P택배배달 {


	 static long answer=-1;
	    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
	        int[] order = new int[n];// 배달할 순서
	        boolean[] visited = new boolean[n];
	        perm(order,visited,0,cap,deliveries,pickups);
	        return answer;
	    }
	    
	    private static void perm(int[] order,boolean[] visited,int cnt,int cap,int[] deliveries, int[] pickups){
	        if(cnt==order.length) {
	            go(order,cap,deliveries,pickups);
	            return;
	        }
	        
	        for(int i=0;i<order.length;i++){
	            if(visited[i])
	                continue;
	            visited[i]=true;
	            order[cnt]=i;
	            perm(order,visited,cnt+1,cap,deliveries,pickups);
	            visited[i]=false;
	        }
	    }
	    
	     private static void go(int[] order,int cap,int[] deliveries, int[] pickups){
	        int d =0; //이동거리
	        int c=cap;//최대로 담을 수 있는 상자
	         int s = order[0];//배달 시작
	         boolean[] pvisited = new boolean[pickups.length];
	        for(int i=0;i<order.length;i++){
	            
	             //order[i]에 배달
	             for(int k=0;k<deliveries[order[i]];k++){//i번째 집에 해야할 배달개수

	                if(c==0){//배달더 해야해 다시 물류창고가서 받아
	                    d+=(order[i]+1)*2;
	                    if(answer!=-1 && d>=answer)//이미 최소가 아님
	                                 return;
	                            
	                    c=cap;//물류 창고 다녀왔음 
	                } 
	                  c--;
	            }
	            
	            
	            //수거
	            if(i==order.length-1 || order[i]>order[i+1]){
	                c=0;
	                d+=(order[i]+1)*2;
	                if(answer!=-1 && d>=answer)
	                                 return;
	                            
	                
	                for(int j=order[i];j>=s;j--){ //j번째 수거 하기
	                    if(pvisited[j])
	                        continue;
	                    int num = pickups[j];
	                    pvisited[j]=true;
	                    for(int n = 0;n<num;n++){
	                        if(c==cap){
	                            d+=(j+1)*2;;
	                            if(answer!=-1 && d>=answer)
	                                 return;
	                            c=0;
	                        }
	                        c++;
	                    }
	                }
	                if(i!=(order.length)-1){
	                    s=order[i+1];
	                }     
	            }
	        }
	             answer = d;  
	        
	     } 
	    
}
