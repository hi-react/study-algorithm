package boj_g4_1261_알고스팟;

import java.io.*;
import java.util.*;

public class Main2 {
    static int N, M;
    static int[][] miro, dist;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static class Room implements Comparable<Room> {
        int x, y, cost;
        Room(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Room o) {
            return this.cost - o.cost; // (비용 기준) 오름차순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]); // 가로
        N = Integer.parseInt(input[1]); // 세로

        miro = new int[N][M];
        dist = new int[N][M]; // 벽 부순 횟수

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                miro[i][j] = line.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        br.close();

        dijkstra();

        System.out.println(dist[N-1][M-1]);
    }

    static void dijkstra() {
        PriorityQueue<Room> pq = new PriorityQueue<>();
        pq.offer(new Room(0, 0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Room now = pq.poll();
            int x = now.x, y = now.y, cost = now.cost;

            if (cost > dist[x][y]) continue; // 이미 더 적은 비용으로 도달한 적 있으면, 현재 경로 무시

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                int nextCost = cost + miro[nx][ny];

                if (nextCost < dist[nx][ny]) {
                    dist[nx][ny] = nextCost;
                    pq.offer(new Room(nx, ny, nextCost));
                }
            }
        }


    }
}
