package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BFS_2644 {
    /*
     *               1                       4
     *      2                3           5       6
     *  7   8   9
     *
     * */
    public static List<List<Integer>> graph = new ArrayList<>();
    public static int answer = -1;
    public static boolean[] visited;
    static int end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 총 9명
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());  // 7
        end = Integer.parseInt(st.nextToken());  // 3

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        dfs(start, 0);
        System.out.println(answer);
    }

    public static void dfs(int point, int cnt) {
        visited[point] = true;
        for (int x : graph.get(point)) {
            if (!visited[x]) {
                if (x == end) {
                    answer = cnt + 1;
                    return;
                }
                dfs(x, cnt + 1);
            }
        }
    }
}
