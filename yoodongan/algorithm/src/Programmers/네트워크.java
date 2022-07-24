package Programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class 네트워크 {
    public static int[][] computers;
    public static boolean[] visited;
    public static int n;
    public static int solution(int n, int[][] computers) {
        int ans = 0;
        for(int i = 0 ; i < n ; i++) {
            if(!visited[i]) {    // 해당 노드를 방문한 적 없다면, 그 노드부터 dfs를 시작한다.
                dfs(i);   // dfs 재귀를 빠져나오면, 한 네트워크가 끝난 것이므로 ans 에 1 더해줌.
                ans++;
            }
        }
        return ans;
    }

    static void dfs(int nodeNum) {
        visited[nodeNum] = true;  // 방문한 노드 처리.
        for(int i = 0; i < n; i++) {
            //시작 컴퓨터와 연결되어 있는 컴퓨터들을 새 시작점으로 방문 명령
            if(computers[nodeNum][i] == 1 && visited[i] == false ) dfs(i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
//        computers = new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};  // return : 2
        computers = new int[][] {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};  // return : 2
        visited = new boolean[n];
        System.out.println(solution(n, computers));

    }
}
