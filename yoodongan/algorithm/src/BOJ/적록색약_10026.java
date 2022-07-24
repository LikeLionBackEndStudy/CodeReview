package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 적록색약_10026{

    static int dx[] = { -1, 0, 1, 0 };   // 좌표 이동, 좌, 상, 우, 하
    static int dy[] = { 0, -1, 0, 1 };
    static int n;    // n*n 그리드 입력을 위한 n
    static int[][] colorMap;   // 정상인 사람이 보는 색map.
    static int[][] colorBlindMap;   // 색약인 사람이 보는 색map
    static BufferedReader br;

    public static void main(String[] args) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 구역에 paint 하기.
        colorMap = new int[n][n];
        colorBlindMap = new int[n][n];
        paintMap();

        // 구역의 수 count. 0으로 초기화
        int ans1 = 0, ans2 = 0;   // 정상인 사람이 봤을 때 구역의 수, 색약인 사람이 봤을 때 구역의 수.
        ans1 = countArea(colorMap, ans1);
        ans2 = countArea(colorBlindMap, ans2);

        // 계산한 count 출력하기.
        StringBuilder sb = new StringBuilder();
        sb.append(ans1).append(" ").append(ans2);
        System.out.println(sb);
    }
    public static void paintMap() throws IOException {   // 그리드 칸에 RGB 색칠하기.
        for (int i = 0; i < n; i++) {
            String line = br.readLine();     // n줄의 색상정보 입력 받기.  RRRBB GGBBB ... RRRRR
            for (int j = 0; j < n; j++) {
                char color = line.charAt(j);   // 각 줄에 대하여 n가지 색상 정보 가져오기. R/R/R/B/B

                // RGB 각 색상을 숫자로 표현합니다. R은 1, G는 2, B는 3
                if (color == 'R') {
                    colorMap[i][j] = 1;
                    colorBlindMap[i][j] = 1;
                } else if (color == 'G') {
                    colorMap[i][j] = 2;
                    colorBlindMap[i][j] = 1;
                } else {
                    colorMap[i][j] = 3;
                    colorBlindMap[i][j] = 3;
                }
            }
        }
    }

    public static int countArea(int[][] map, int cnt) {  // 영역의 개수를 count 해주는 메서드.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) {   // 방문하지 않았다면,
                    dfs(i, j, map, map[i][j]);  // 만약, dfs 재귀호출이 끝나면, 더 이상 같은 색상이 아니므로
                    cnt++;   // cnt 에 1 더해준다.
                }
            }
        }
        return cnt;
    }

    public static void dfs(int x, int y, int map[][], int color) {    // x, y 는 그리드 방문 좌표 | map은 paint한 grid | color 은 해당 위치의 색깔.
        map[x][y] = 0;   // 방문함.  그리드 배열에서 1,2,3(색깔)이 아닌 0으로 바꿔준다.

        for(int d = 0; d < 4; d++) { // 상,하,좌,우 로 이어져 있으면 같은 영역이므로, 4가지 경우에 대한 좌표 이동
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 좌표 이동할 때, 그리드 영역을 넘지 않고, 이동한 좌표 값이 같은 색깔이라면 하나의 영역이므로 계속 이동가능
            if(0 <= nx && nx < map.length && 0 <= ny && ny < map.length && map[nx][ny] == color)
                dfs(nx, ny, map, color);     // 다음 좌표로 이동한다.
        }
    }
}