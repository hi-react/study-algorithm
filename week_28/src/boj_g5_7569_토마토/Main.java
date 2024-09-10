package boj_g5_7569_토마토;

/**
 * 1. 문제 분석
 * M(가로) * N(세로) * H(높이) 크기의 사각형 창고
 * 각각의 칸에는 토마토가 있을 수도 있고, 없을 수도 있음(-1)
 * 토마토가 있다면, 익거나(1) 익지 않았음(0)
 * 하루가 지나면, 익은 토마토에 인접한 6칸(위/아래/상/하/좌/우)의 토마토가 익음
 * 창고에 보관된 토마토가 며칠이 지나야 다 익는 지 최소 일수 구하기
 * 단, 시작 부터 모든 토마토 익어 있으면 0, 토마토가 모두 익지 못하는 상황 이면 -1 출력
 *
 * 2. 문제 풀이
 * 1) 6 방향 탐색(위/아래/상/하/좌/우)을 위한 델타 배열을 생성 한다.
 *
 * 2) 시작 점이 되는 익은 토마토(1)들을 모두 큐에 넣는다.
 * 익지 않은 토마토(0) 개수는 unripeTomatoes 변수로 관리 한다.
 *
 * 3) 큐에 있는 모든 익은 토마토(1) 시작 점에서 '동시에 익히기' 작업 수행 한다.
 * 큐에서 익은 토마토(1) 한 개씩 꺼낸다.
 * 6 방향 으로 탐색 하여, 안 익은 토마토(0) 있으면 익힌다.
 * 이 떄, 새롭게 익은 토마토(1)는 큐에 추가 하고, 안 익은 토마토 수(unripeTomatoes) 감소 시킨다.
 * '동시에 익히기' 작업 1 Turn 끝나면, 날짜 +1 증가 시킨다.
 * 모든 탐색이 끝난 후, unripeTomatoes가 0 ? (모든 토마토가 익었으므로) 해당 일수 - 1 반환 : IF NOT -1 반환
 *
 * 3. 알고리즘: (3차원 배열의) BFS(너비 우선 탐색)
 *
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N, H; // 가로, 세로, 높이

    // 6방향 탐색 위한 델타 배열 (위/아래/상/하/좌/우)
    static int[] dh = {1, -1, 0, 0, 0, 0}; // 높이 (위/아래)
    static int[] dc = {0, 0, -1, 1, 0, 0}; // 세로 (상/하)
    static int[] dr = {0, 0, 0, 0, -1, 1}; // 가로 (좌/우)

    static int[][][] box; // 3차원 토마토 창고

    static Queue<int[]> queue = new LinkedList<>(); // 익은 토마토(1)의 3차원 위치 저장 하는 큐
    static int unripeTomatoes = 0; // 안 익은 토마토 개수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력 받기
        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H = Integer.parseInt(st.nextToken()); // 높이

        box = new int[H][N][M]; // 데이터가 실제로 처리되는 순서(높이 → 세로 → 가로)에 맞춤

        for (int i = 0; i < H; i++) { // 높이
            for (int j = 0; j < N; j++) { // 세로
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) { // 가로
                    // 토마토 상태 넣기 (0 또는 1 또는 -1)
                    box[i][j][k] = Integer.parseInt(st.nextToken());

                    // 토마토 있는 경우(0 또는 1)
                    if (box[i][j][k] == 1) {
                        queue.offer(new int[]{i, j, k}); // 익은 토마토(1) 위치를 큐에 추가
                    } else if (box[i][j][k] == 0) {
                        unripeTomatoes++; // 안 익은 토마토(0) 개수 세기
                    }

                }
            }
        }

        // 2. 예외 처리 : 시작 부터 모든 토마토 익은 경우
        if (unripeTomatoes == 0) {
            System.out.println(0);
            return;
        }

        // 3. BFS 탐색 => 토마토 다 익히는 최소 일 수 반환
        int result = bfs();
        System.out.println(result);

        br.close();
    }

    // method 1 : BFS 함수
    private static int bfs() {
        int days = 0; // 반환 할 최소 일수

        while (!queue.isEmpty()) {
            int size = queue.size(); // 현재 큐에 있는 모든 익은 토마토(1) 개수 => 이걸 하루 동안 동시에 익힐 것

            for (int i = 0; i < size; i++) {
                int[] ripeTomato = queue.poll(); // 익은 토마토 하나 꺼내
                int h = ripeTomato[0]; // 높이
                int c = ripeTomato[1]; // 세로
                int r = ripeTomato[2]; // 가로

                // 6방향 탐색
                for (int d = 0; d < 6; d++) {
                    int nh = h + dh[d];
                    int nc = c + dc[d];
                    int nr = r + dr[d];
                    ripenTomato(nh, nc, nr); // 새 위치에 안 익은 토마토(0) 있으면 익히기
                }
            }

            days++; // 하루 끝남

        }

        // 모든 탐색을 마친 후, 안 익은 토마토(0) 남아 있는 지 확인
        if (unripeTomatoes == 0) { // 모든 토마토 다 익은(1) 경우
            return days - 1; // 마지막 날, 모든 토마토 다 익고 나서도 days++ 되니까, 빼주기
        } else return -1; // 안 익은 토마토(0) 남아 있는 경우


    }

    // method 2 : (가능한 범위 내) 인접한 안 익은 토마토(0) 익히기
    private static void ripenTomato(int nh, int nc, int nr) {
        if (nh >= 0 && nh < H && nc >=0 && nc < N && nr >= 0 && nr < M) {  // 범위 벗어 나지 않고,
            if (box[nh][nc][nr] == 0) { // 안 익은 토마토가 있으면,
                box[nh][nc][nr] = 1; // 1) 익히고
                queue.offer(new int[]{nh, nc, nr}); // 2) 새롭게 익은 토마토(1) 큐에 추가
                unripeTomatoes--; // 3) 안 익은 토마토 개수 감소
            }
        }
    }


}

