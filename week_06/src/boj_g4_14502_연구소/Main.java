package boj_g4_14502_연구소;

import java.util.*;

public class Main {
    static int[][] map;
    static int N, M;
    static int maxSafeArea = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        List<int[]> blankSpaces = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 0) { // 빈 칸인 경우 좌표 저장
                    blankSpaces.add(new int[]{i, j});
                }
            }
        }

        buildWalls(0, 0, blankSpaces);

        System.out.println(maxSafeArea);
    }

    static void buildWalls(int start, int count, List<int[]> blankSpaces) {
        if (count == 3) { // 벽을 3개 세웠을 때
            int[][] tempMap = new int[N][M];
            for (int i = 0; i < N; i++) {
                tempMap[i] = Arrays.copyOf(map[i], M);
            }
            maxSafeArea = Math.max(maxSafeArea, getSafeArea(tempMap));
            return;
        }

        for (int i = start; i < blankSpaces.size(); i++) {
            int[] pos = blankSpaces.get(i);
            map[pos[0]][pos[1]] = 1; // 벽 세우기
            buildWalls(i + 1, count + 1, blankSpaces);
            map[pos[0]][pos[1]] = 0; // 벽 제거 (백트래킹)
        }
    }

    static int getSafeArea(int[][] tempMap) {
        int safeArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempMap[i][j] == 2) {
                    spreadVirus(i, j, tempMap);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempMap[i][j] == 0) {
                    safeArea++;
                }
            }
        }
        return safeArea;
    }

    static void spreadVirus(int x, int y, int[][] tempMap) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < N && ny >= 0 && ny < M && tempMap[nx][ny] == 0) {
                tempMap[nx][ny] = 2;
                spreadVirus(nx, ny, tempMap);
            }
        }
    }
}