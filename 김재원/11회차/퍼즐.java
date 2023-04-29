import java.util.*;
import java.io.*;

class Main {
    static ArrayDeque<String> q = new ArrayDeque<>();
    static HashMap<String, Integer> map = new HashMap<>(); // 횟수, 중복제거
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        // 0(빈칸)을 찾는다
        // 빈칸 주변 BFS
        for (int i = 0; i < 3; i++) {
            sb.append(sc.nextInt());
        }

        if (sb.toString().equals("123456780"))
            System.out.println("0");
        else {
            System.out.println(bfs());
        }

    }

    public static int bfs() {
        map.put(sb.toString(), 0);
        q.offer(sb.toString());

        while (!q.isEmpty()) {
            String str = q.poll();
            int blankIdx = str.indexOf("0");
            int row = blankIdx / 3;
            int col = blankIdx % 3;

            for (int i = 0; i < 4; i++) {
                int nx = row + dx[i];
                int ny = col + dy[i];
                if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3)
                    continue;

                // 현재 내가 보고있는 위치가 일차원 배열일때 위치
                int arrIdx = 3 * nx + ny;

                // 자리 바꾸기
                StringBuilder sb = new StringBuilder(str);
                char ch = sb.charAt(arrIdx);
                sb.setCharAt(arrIdx, '0');
                sb.setCharAt(blankIdx, ch);

                // 정답 찾기
                if (sb.toString().equals("123456780"))
                    return map.get(str) + 1;

                // 정답 못찾아버리기
                if (!map.containsKey(sb.toString())) {
                    q.offer(sb.toString());
                    map.put(sb.toString(), map.get(str) + 1);
                }
            }
        }

        return -1;
    }
}