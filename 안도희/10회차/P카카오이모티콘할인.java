package week10;

import java.util.*;
class Solution2 {
    static int n,m;   
    static int[] dis;
    static int rcnt,rprice;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        n = users.length;//사용자수
        m=emoticons.length;//이모티콘 개수
        dis = new int[m];//이모티콘 할인율
        discount(0,users,emoticons);//이모티콘 할인율 조합
        answer[0]=rcnt;
        answer[1]=rprice;
        
        return answer;
    }
    
    private void discount(int cnt ,int[][] users, int[] emoticons){
        if(cnt==m){//이모티콘 할인율 정해졌다
            result(users,emoticons);
            return;
        }
        
        for(int i=10;i<=40;i+=10){
            dis[cnt]=i;
            discount(cnt+1,users,emoticons);
        }
    }
    
    private void result(int[][] users, int[] emoticons){
        //할인율 별
        int cnt=0;//가입자수
        int price=0;//판매금액
        
        for(int i=0;i<n;i++){//이용자
            int p=0;
            for(int j=0;j<m;j++){//이모티콘
                if(users[i][0]>dis[j])//안사!
                    continue;
             
		p = (int)(p+ (emoticons[j] * (1 - (0.01) * dis[j])));// 정답
                //p=p+(int)(emoticons[j] * (1 - (0.01) * dis[j]));//13,15,18오답 
                if(p>=users[i][1]){
                    cnt++;//가입
                    p=0;
                    break;
                }   
            } 
             price+=p;
        }
        
        if(rcnt<cnt){
            rcnt=cnt;
            rprice=price;
        }
        else if(rcnt==cnt){
            if(rprice<price)
                rprice=price;
        }
       
    }
}
