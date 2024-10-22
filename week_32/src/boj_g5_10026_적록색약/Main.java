package boj_g5_10026_적록색약;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. 문제 분석
 * 그리드 크기: N * N
 * 색상: R(빨강), G(초록), B(파랑) 중 하나로 색칠됨
 * 구역 정의: 같은 색으로 상하좌우 인접해 있으면 하나의 구역
 * 적록색약은 R(빨강), G(초록) 구분 하지 못하므로 하나의 구역으로 인식
 *
 * 목표 : 적록색약 아닌 사람 기준 구역 수 & 적록색약 기준 구역 수 출력
 *
 * 2. 문제 풀이
 * 하나의 색상에 대해 상하좌우 인접한 노드(같은 색상 인지) 따라 가며 연결된 모든 노드 탐색해야 함
 * visited 배열 사용 하여 방문 여부 확인
 * 상하좌우 탐색을 위한 4방향 벡터 (dx, dy 배열) 활용
 *
 * 3. 알고리즘 : 그래프 탐색, DFS(깊이 우선 탐색)
 * 특정 색상 영역을 찾기 위해 인접한 모든 칸을 깊이 있게 탐색 해야 함
 *
 */

public class Main {

    static BufferedReader br;
    static int N;
    static char[][] grid;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

//        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        // 1. 입력 받기
        N = Integer.parseInt(br.readLine());
        grid = new char[N][N];

        for (int i = 0; i < N; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        // 2. 적록색약 아닌 사람
        visited = new boolean[N][N];
        int normalCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    normalDFS(i, j, grid[i][j]);
                    normalCount++;
                }
            }
        }

        // 3. 적록색약
        visited = new boolean[N][N];
        int redEqualGreenCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    redEqualGreenDFS(i, j, grid[i][j]);
                    redEqualGreenCount++;
                }
            }
        }


        System.out.println(normalCount + " " + redEqualGreenCount);

        br.close();

    }

    // 적록 색약 아닌 사람 DFS
    private static void normalDFS(int x, int y, char color) {
        visited[x][y] = true; // 일단 방문 하고
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (isValid(nx, ny) && grid[nx][ny] == color) {
                normalDFS(nx, ny, color);
            }
        }
    }

    // 적록 색약 DFS
    private static void redEqualGreenDFS(int x, int y, char color) {
        visited[x][y] = true;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (isValid(nx, ny)) {
                if (color == 'R' || color == 'G') {
                    if (grid[nx][ny] == 'R' || grid[nx][ny] == 'G') {
                        redEqualGreenDFS(nx, ny, color);
                    }
                } else if (grid[nx][ny] == color) {
                    redEqualGreenDFS(nx, ny, color);
                }
            }
        }
    }

    // 경계 조건
    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N && !visited[x][y];
    }

}
