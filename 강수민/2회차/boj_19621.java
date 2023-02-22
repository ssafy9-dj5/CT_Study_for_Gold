import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Meeting[] meetings = new Meeting[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(meetings);

        int[] dp = new int[n];
        int max = meetings[0].people;

        dp[0] = meetings[0].people;

        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (meetings[i].start > meetings[j].end && dp[i] < dp[j]) {
                    dp[i] = dp[j];
                }
            }
            dp[i] += meetings[i].people;
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

    private static class Meeting implements Comparable<Meeting>{
        int start, end, people;

        public Meeting(int start, int end, int people) {
            this.start = start;
            this.end = end;
            this.people = people;
        }

        @Override
        public int compareTo(Meeting o) {
            return this.end - o.end;
        }
    }
}