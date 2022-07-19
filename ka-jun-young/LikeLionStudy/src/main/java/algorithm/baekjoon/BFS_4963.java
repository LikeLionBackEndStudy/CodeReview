package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BFS_4963 {
    static int h, w, count;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] squareMap;
    static boolean[][] visited;
    final static int[] dx = {1, 0, -1, 0, -1, 1, -1, 1};
    final static int[] dy = {0, 1, 0, -1, -1, 1, 1, -1};

    public static void main(String[] args) throws IOException {
        while (true) {
            String testCase = br.readLine();
            if(testCase.equals("0 0")) break;

            StringTokenizer st = new StringTokenizer(testCase);
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            squareMap = new int[h][w];
            visited = new boolean[h][w];
            dataInit(squareMap);
            count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (squareMap[i][j] == 1 && !visited[i][j]) {
                        bfs(i, j);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }

    }

    public static void dataInit(int[][] arr) throws IOException {
        for (int h = 0; h < arr.length; h++) {
            String[] info = br.readLine().split(" ");
            for (int w = 0; w < arr[h].length; w++) {
                squareMap[h][w] = Integer.parseInt(info[w]);
            }
        }
    }

    public static void bfs(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < h && ny >= 0 && ny < w) {
                if(squareMap[nx][ny] == 1 && !visited[nx][ny]) bfs(nx, ny);
            }
        }
    }
}
/*for (int h = 0; h < squareMap.length; h++) {
            for (int w = 0; w < squareMap[h].length; w++) {
                System.out.print(squareMap[h][w]+ " ");
            }
            System.out.println();
        }*/
/*
for (boolean[] booleans : visited) {
        for (int j = 0; j < booleans.length; j++) {
        System.out.print(booleans[j] + " ");
        }
        System.out.println();
        }*/
