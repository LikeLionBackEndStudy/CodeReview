package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BFS_4963 {
    // 지도의 높이, 너비, 섬의 수
    static int h, w, count;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // Land, Sea 지도
    static int[][] squareMap;
    // 해당 node 방문 여부
    static boolean[][] visited;
    // 동, 서, 남, 북, 각 대각선 4방향에 대한 x축, y축
    final static int[] dx = {1, -1, 0, 0, -1, 1, -1, 1};
    final static int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};

    public static void main(String[] args) throws IOException {
        while (true) {
            // 지도의 너비와 높이를 받는 testCase
            String testCase = br.readLine();
            if(testCase.equals("0 0")) break;

            StringTokenizer st = new StringTokenizer(testCase);
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            // 지도와 방문 여부에 대한 2차원 배열 크기 지정
            squareMap = new int[h][w];
            visited = new boolean[h][w];

            // 지도에 땅과 바다에 대한 정보 입력
            dataInit(squareMap);

            // 한 testCase 별로 count를 지정해줘야하기 때문에 값 초기화
            count = 0;

            // BFS를 통해서 해당 지도에 섬의 개수가 총 몇개인지 확인
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    // 만약 지도 좌표에 1이 있으며 방문하지 않은 노드면 bfs 진행
                    if (squareMap[i][j] == 1 && !visited[i][j]) {
                        bfs(i, j);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }

    }

    public static void dataInit(int[][] map) throws IOException {
        for (int h = 0; h < map.length; h++) {
            // 1 0 1 0 0 과 같은 형태로 총 map 배열의 높이만큼 입력 받음
            String[] info = br.readLine().split(" ");
            for (int w = 0; w < map[h].length; w++) {
                // 지도에 각 정보를 저장
                squareMap[h][w] = Integer.parseInt(info[w]);
            }
        }
    }

    public static void bfs(int x, int y) {
        // 넘겨받은 좌표에 방문 도장 찍기
        visited[x][y] = true;

        // 가로, 세로, 대각선 총 8개 방향으로 탐색
        for (int i = 0; i < 8; i++) {

            // 다음 좌표를 탐색하기 위해 dx, dy의 정보를 더함
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 지도의 범위 안에 있고, 다음 방문할 노드가 땅이며, 방문하지 않았다면 bfs를 다시 진행
            if (nx >= 0 && nx < h && ny >= 0 && ny < w) {
                if(squareMap[nx][ny] == 1 && !visited[nx][ny]) bfs(nx, ny);
            }
        }
    }
}