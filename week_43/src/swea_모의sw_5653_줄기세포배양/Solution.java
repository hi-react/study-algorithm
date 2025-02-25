package swea_모의sw_5653_줄기세포배양;

import java.io.*;
import java.util.*;

public class Solution {
    static class Cell implements Comparable<Cell> {
        int x, y; // 좌표
        int life, passedTime, state;

        public Cell(int x, int y, int life) {
            this.x = x;
            this.y = y;
            this.life = life; // 생명력
            this.passedTime = 0; // 현재 상태에서 경과한 시간 (초기 상태 0)
            this.state = 0;  // 0: 비활성, 1: 활성, 2: 죽음 (초기 상태 0)
        }

        @Override
        public int compareTo(Cell o) {
            return o.life - this.life; // 생명력 높은 순 (내림차순)
        }
    }
    static int K;
    static Map<String, Cell> allCellInfo;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // HashMap으로 줄기세포 위치 관리
            allCellInfo = new HashMap<>();

            // 초기 상태 입력
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life > 0) {
                        Cell cell = new Cell(i, j, life);
                        String key = i + "," + j;
                        allCellInfo.put(key, cell);
                    }
                }
            }

            BFS();

            long alive = allCellInfo.values().stream().filter(cell -> cell.state != 2).count(); // 살아 있는 놈 카운트
            bw.write("#" + tc + " " + alive + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    // [method] K 시간 동안 시뮬레이션
    static void BFS() {
        for (int t = 0; t < K; t++) {
            // 1. 번식할 세포 저장을 위한 우선순위 큐 선언
            PriorityQueue<Cell> spreadQueue = new PriorityQueue<>();

            // 2. 모든 세포 상태 업데이트
            for (Cell cell : new ArrayList<>(allCellInfo.values())) { // 원본 Map 복사해서 사용
                // 1) 죽은 놈 패스
                if (cell.state == 2) continue;

                // 2) 상태 경과한 시간 처리
                cell.passedTime++;

                // 3) 비활성(0) -> 활성(1) : 생명력 시간이 지나면 활성화
                if (cell.state == 0 && cell.passedTime == cell.life) {
                    cell.state = 1;
                    cell.passedTime = 0;  // 활성화 됐으니까, (상태 변경) 경과 시간 초기화
                }

                // 4) 활성(1) 상태의 번식 로직
                if (cell.state == 1) {
                    // 활성화되고 1시간 지나야 번식
                    if (cell.passedTime == 1) {
                        for (int d = 0; d < 4; d++) {
                            int nx = cell.x + dx[d];
                            int ny = cell.y + dy[d];
                            String newKey = nx + "," + ny;
                            // 새로운 세포만 큐에 추가
                            if (!allCellInfo.containsKey(newKey)) {
                                spreadQueue.offer(new Cell(nx, ny, cell.life));
                            }
                        }
                    }
                    // 활성 시간이 다 지나면 죽음 상태로 변경
                    if (cell.passedTime == cell.life) {
                        cell.state = 2;
                    }
                }
            }

            // 3. 번식된 세포 처리
            while (!spreadQueue.isEmpty()) {
                Cell newCell = spreadQueue.poll();
                String newKey = newCell.x + "," + newCell.y;
                if (!allCellInfo.containsKey(newKey)) {
                    allCellInfo.put(newKey, newCell);
                }
            }
        }
    }
}
