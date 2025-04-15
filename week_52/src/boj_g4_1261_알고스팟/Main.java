package boj_g4_1261_알고스팟;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] miro, dist;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static class Room {
        int x, y;
        Room(int x, int y) {
            this.x = x;
            this.y = y;
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

        bfs();

        System.out.println(dist[N-1][M-1]);
    }

    static void bfs() {
        Deque<Room> deque = new ArrayDeque<>();
        deque.offer(new Room(0, 0));
        dist[0][0] = 0;

        while (!deque.isEmpty()) {
            Room now = deque.poll();
            int x = now.x, y = now.y;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                int cost = dist[x][y] + miro[nx][ny];

                if (cost < dist[nx][ny]) {
                    dist[nx][ny] = cost;
                    if (miro[nx][ny] == 1) {
                        deque.offerLast(new Room(nx, ny));
                    } else {
                        deque.offerFirst(new Room(nx, ny));
                    }
                }
            }
        }


    }
}
