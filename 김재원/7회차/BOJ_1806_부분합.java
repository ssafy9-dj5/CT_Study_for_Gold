import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806_부분합 {
  static int n;
  static int s;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    s = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int arr[] = new int[n];
    arr[0] = 0;
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int min = Integer.MAX_VALUE;

    int start = 0;
    int end = 0;
    int sum = 0;

    while (true) {
      if (sum >= s) {
        sum -= arr[start];
        min = Math.min(end - start, min);
        start++;
      } else if (end == n)
        break;
      else
        sum += arr[end++];
    }
    if (min == Integer.MAX_VALUE)
      System.out.println(0);
    else
      System.out.println(min);
  }
}