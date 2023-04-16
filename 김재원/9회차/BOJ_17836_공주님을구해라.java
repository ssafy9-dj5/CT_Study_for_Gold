import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * BOJ_17836_공주님을구해라
 */
public class BOJ_17836_공주님을구해라 {
  public static class Node {
    int x;
    int y;
    int step;
    boolean breakable;
    // 지역변수로 검을 얻게하면 BFS큐에서 뒤죽박죽 되기때문에 노드에 저장해야 BFS 스텝 마다 검의 유무를 알 수 있다

    public Node(int x, int y, int count, boolean breakable) {
      this.x = x;
      this.y = y;
      this.step = count;
      this.breakable = breakable;
    }
  }

  static int n, m, t;
  static int[][] board;
  // 배열을 하나 더 추가하여 검을 주워도 되돌아 갈수 있게 한다.
  static boolean[][] isVisited, isVisited_verSword;
  static int[] dx = { 0, 1, 0, -1 };
  static int[] dy = { 1, 0, -1, 0 };

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();
    t = sc.nextInt();

    board = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        board[i][j] = sc.nextInt();
      }
    }

    isVisited = new boolean[n][m];
    isVisited_verSword = new boolean[n][m];
    int res = bfs(0, 0);
    if (res == -1)
      System.out.println("Fail");
    else
      System.out.println(res);
  }

  public static int bfs(int x, int y) {
    ArrayDeque<Node> q = new ArrayDeque<>();
    q.offer(new Node(x, y, 0, false));
    isVisited[x][y] = true;
    // isVisited_verSword[x][y] = true; // 시작하자마자 검을 얻을 수 있는 테스트 케이스가 있나?

    while (!q.isEmpty()) {
      Node cur = q.poll();

      if (cur.step > t) // 실패
        break;
      if (cur.x == n - 1 && cur.y == m - 1) // 성공
        return cur.step;

      for (int i = 0; i < 4; i++) {
        int nx = cur.x + dx[i];
        int ny = cur.y + dy[i];
        if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
          if (!cur.breakable) { // 부술 수 없음
            if (!isVisited[nx][ny] && board[nx][ny] == 0) {
              q.offer(new Node(nx, ny, cur.step + 1, cur.breakable));
              isVisited[nx][ny] = true;
            } else if (!isVisited[nx][ny] && board[nx][ny] == 2) {
              q.offer(new Node(nx, ny, cur.step + 1, true));
              isVisited[nx][ny] = true;
            }
          } else {// 부술 수 있음
            if (!isVisited_verSword[nx][ny]) {
              q.offer(new Node(nx, ny, cur.step + 1, cur.breakable));
              isVisited_verSword[nx][ny] = true;
            }
          }
        }
      }
    }
    return -1;
  }

}