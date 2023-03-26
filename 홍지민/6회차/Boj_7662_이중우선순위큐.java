package day0319;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Boj_7662_이중우선순위큐 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int cnt = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> tm = new TreeMap<>();
			
			String oper = "";
			int num = 0;
			int max, min =0;
			for(int i=0; i<cnt; i++) {
				st = new StringTokenizer(br.readLine());
				oper = st.nextToken();
				num = Integer.parseInt(st.nextToken());
				
				if(oper.equals("I")) {
					tm.put(num, tm.getOrDefault(num, 0)+1);
				}
				else {
					if(tm.size()!=0) {
						if(num == 1) {
							max = tm.get(tm.lastKey());
							if(max-1 > 0) {
								tm.put(tm.lastKey(), max-1);
							}
							else {
								tm.remove(tm.lastKey());
							}
						}
						else {
							min = tm.get(tm.firstKey());
							if(min-1 > 0) {
								tm.put(tm.firstKey(), min-1);
								
							}
							else {
								tm.remove(tm.firstKey());
							}
						}
					}
				}
			}
			
			if(tm.size()==0) System.out.println("EMPTY");
			else System.out.println(tm.lastKey() +" "+tm.firstKey());
		}
	}
}
