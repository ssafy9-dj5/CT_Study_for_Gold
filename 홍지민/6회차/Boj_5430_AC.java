package day0319;

import java.util.Scanner;

public class Boj_5430_AC {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 0; tc < T; tc++) {
			StringBuilder sb = new StringBuilder();
			String str1 = sc.next();
			char[] order = str1.toCharArray(); // 주문 알파벳

			int n = sc.nextInt();
			String str2 = sc.next();

			if (n == 0 && order[0]=='D') {
				System.out.println("error");
				continue;
			}
			
			else if(n==0 && order[0]=='R'){
				System.out.println("[]");
				continue;
			}
			
			String str3 = str2.substring(1, str2.length() - 1);
			String[] str = str3.split(","); // 숫자
			
			int dir = 0; // 앞방향 디폴트
			int front = 0;
			int back = str.length-1;
			int flag = 0;
			for(int i=0; i<order.length; i++){
				if(order[i] == 'R') { //명령 R의 경우
					if(dir==1) {
						dir = 0;
					}
					else dir = 1;
				}
				
				else { //명령 D의 경우
					if(str[front].equals("0") || str[back].equals("0")) { //배열 비어있는데 삭제시도할 경우
						flag = 1;
						break;
					}
					else {
						if(dir==0) { //앞
							str[front] = "0";
							front += 1;
							if(front==str.length) {
								front-=1;
								continue;
							}
						}
						else { // 뒤
							str[back] = "0";
							back -= 1;
							if(back==-1) {
								back += 1;
								continue;
							}
						}
					}
				}
			}
			
			if(flag==1) {
				System.out.println("error");
				continue;
			}
			else {
				sb.append("[");
				if(dir == 0) {
					while(!str[front].equals("0")) {
						sb.append(str[front]).append(",");
						front++;
						if(front==str.length) {
							sb.deleteCharAt(sb.length()-1);
							break;
						}
					}
					sb.append("]");
					System.out.println(sb);	
					continue;
				}
				else {
					while(!str[back].equals("0")) {
						sb.append(str[back]).append(",");
						back--;
						if(back==-1) {
							sb.deleteCharAt(sb.length()-1);
							break;
						}
					}
					sb.append("]");
					System.out.println(sb);	
					continue;
				}
			}
		}

	}
}