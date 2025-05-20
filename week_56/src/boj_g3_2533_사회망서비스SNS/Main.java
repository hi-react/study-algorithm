package boj_g3_2533_사회망서비스SNS;

import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] relations;
    static int[][] dp; // dp[node][0] = 얼리어답터 X, dp[node][1] = 얼리어답터 O
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        relations = new ArrayList[N + 1];
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        for(int i = 1; i <= N; i++) {
            relations[i] = new ArrayList<>();
        }

        for(int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            relations[u].add(v);
            relations[v].add(u);
        }

        br.close();

        dfs(1);

        bw.write(String.valueOf(Math.min(dp[1][0], dp[1][1])));
        bw.flush();
        bw.close();
    }

    static void dfs(int node) {
        visited[node] = true;

        dp[node][0] = 0; // 해당 node = 얼리어답터 X
        dp[node][1] = 1; // 해당 node = 얼리어답터 O

        for(int near : relations[node]) {
            if(!visited[near]) {
                dfs(near);

                dp[node][0] += dp[near][1]; // node가 얼리어답터 아니면 => near은 얼리어답터 여야함
                dp[node][1] += Math.min(dp[near][0], dp[near][1]); // node가 얼리어답터라면 => near은 얼리어답터 거나 아니거나 중 작은 값
            }
        }
    }
}
