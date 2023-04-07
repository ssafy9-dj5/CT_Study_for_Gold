import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * BOJ_2589_보물섬
 */
public class BOJ_2589_보물섬 {
  static int dx[] = { -1, 0, 1, 0 };
  static int dy[] = { 0, 1, 0, -1 };
  static char map[][];
  static int n;
  static int m;

  static class Point {
    public int x, y, dep;

    public Point(int x, int y, int dep) {
      this.x = x;
      this.y = y;
      this.dep = dep;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    map = new char[n][m];
    for (int i = 0; i < n; i++) {
      String str = br.readLine();
      for (int j = 0; j < m; j++) {
        map[i][j] = str.charAt(j);
      }
    }
    int res = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (map[i][j] == 'L') {
          int tmp = bfs(new Point(i, j, 0));
          if (tmp > res)
            res = tmp;
        }
      }
    }
    System.out.println(res);
  }

  public static int bfs(Point start) {
    ArrayDeque<Point> dq = new ArrayDeque<>();
    boolean isVisited[][] = new boolean[n][m];
    dq.add(start);
    isVisited[start.x][start.y] = true;
    while (!dq.isEmpty()) {
      Point cur = dq.poll();
      // isVisited[cur.x][cur.y] = true; 여기서만 true 해주면 될 줄 알았는데
      for (int i = 0; i < dx.length; i++) {
        int nx = cur.x + dx[i];
        int ny = cur.y + dy[i];
        if (nx >= 0 && ny >= 0 && nx < n && ny < m && map[nx][ny] == 'L' && isVisited[nx][ny] != true) {
          isVisited[nx][ny] = true; // 이거 안적었더니 메모리 초과됨;;
          dq.add(new Point(nx, ny, cur.dep + 1));
        }
      }
      if (dq.isEmpty()) {
        return cur.dep;
      }
    }
    return 0;
  }
}