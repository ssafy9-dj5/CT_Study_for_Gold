package day0430;

class Solution {
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;
		int dstart = n - 1;
		int pstart = n - 1;

		// 총 박스 개수
		int sumD = 0;
		int sumP = 0;
		
		for (int i = 0; i < n; i++) {
			if (deliveries[i] > 0) {
				sumD += deliveries[i];
			}
			if (pickups[i] > 0) {
				sumP += pickups[i];
			}
		}

		while (!(sumD == 0 && sumP == 0)) {
			int capD = cap;
			int capP = cap;
			int index = -1;

			for (int i = dstart; i >= 0; i--) {
				if (deliveries[i] > 0) {
					index = Math.max(index, i);
					// 수용할 수 있는 박스 개수라면
					if (deliveries[i] <= capD) { 
						capD -= deliveries[i];
						sumD -= deliveries[i];
						deliveries[i] = 0;
					} 
					else { 
						deliveries[i] -= capD;
						dstart = i;
						sumD -= capD;
						break;
					}
				}
			}

			for (int i = pstart; i >= 0; i--) {
				if (pickups[i] > 0) {
					index = Math.max(index, i);

					if (pickups[i] <= capP) {
						capP -= pickups[i];
						sumP -= pickups[i];
						pickups[i] = 0;
					} 
					else {
						pickups[i] -= capP;
						pstart = i;
						sumP -= capP;
						break;
					}
				}
			}

			// 진행된 부분까지의 거리 결과에 더하기
			answer += (index + 1) * 2;
		}

		return answer;
	}
}
