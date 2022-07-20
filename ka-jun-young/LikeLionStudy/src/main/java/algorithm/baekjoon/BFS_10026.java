package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BFS_10026 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[][] nwPicture, wPicture;
    static boolean[][] nwVisited, wVisited;
    static int N, colorWeaknessCount, notColorWeaknessCount;
    final static int[] dx = {1, -1, 0, 0};
    final static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        nwPicture = new String[N][N];
        wPicture = new String[N][N];
        nwVisited = new boolean[N][N];
        wVisited = new boolean[N][N];

        dataInit(N);
        colorWeaknessCount = 0;
        notColorWeaknessCount = 0;
        getCount();

        System.out.println(notColorWeaknessCount + " " + colorWeaknessCount);
    }

    public static void dataInit(int N) throws IOException {
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                nwPicture[i][j] = line[j];

                if (line[j].equals("R")) wPicture[i][j] = "G";
                else wPicture[i][j] = line[j];
            }
        }
    }

    public static void getCount() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!nwVisited[i][j]) {
                    bfs(i, j, nwPicture[i][j], nwVisited, nwPicture);
                    notColorWeaknessCount++;
                }
                if (!wVisited[i][j]) {
                    bfs(i, j, wPicture[i][j], wVisited, wPicture);
                    colorWeaknessCount++;
                }
            }
        }
    }

    public static void bfs(int x, int y, String color, boolean[][] visited, String[][] picture) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (picture[nx][ny].equals(color) && !visited[nx][ny]) {
                    bfs(nx, ny, color, visited, picture);
                }
            }
        }
    }
}
