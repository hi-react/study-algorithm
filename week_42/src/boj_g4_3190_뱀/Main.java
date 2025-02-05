package boj_g4_3190_뱀;

import java.awt.event.KeyListener;
import java.io.*;
import java.util.*;

public class Main {

    static int N, K, L; // 보드판 크기, 사과 개수, 방향 변환 횟수
    static int[][] board;
    static Map<Integer, Character> dirChangeInfo;

    // 우 - 하 - 좌 - 상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 보드판 크기
        K = Integer.parseInt(br.readLine()); // 사과 개수
        board = new int[N][N];

        // 보드 판에 사과 1로 세팅
        int apples = K;
        while (apples-- > 0) {
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]) - 1;
            int y = Integer.parseInt(input[1]) - 1;
            board[x][y] = 1;
        }

        L = Integer.parseInt(br.readLine());
        dirChangeInfo = new HashMap<>();

        // 방향 변환 정보 저장
        int count = L;
        while (count-- > 0) {
            String[] input = br.readLine().split(" ");
            int time = Integer.parseInt(input[0]);
            char dir = input[1].charAt(0);
            dirChangeInfo.put(time, dir);
        }

        br.close();
        bw.write(playGame() + "\n");
        bw.flush();
        bw.close();
    }

    // [method] 게임 끝난 시간(초) 반환하는 메서드
    static int playGame() {
        int time = 0, direction = 0; // 최초 오른쪽 방향

        Deque<int[]> snake = new ArrayDeque<>(); // 뱀 머리/꼬리 조정용
        Set<String> snakeBody = new HashSet<>(); // 뱀 몸통 부딪히는 거 확인용

        // 뱀 초기 세팅
        snake.add(new int[]{0,0});
        snakeBody.add("0,0");

        int x = 0, y = 0; // 초기 위치

        while (true) {
            time++;

            int nx = x + dx[direction];
            int ny = y + dy[direction];

            // [게임 종료] 벽이나 자기 몸과 부딪히면 게임 끝
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || snakeBody.contains(nx + "," + ny)) return time;

            // 일단 머리 이동시켜서 몸 늘리고
            snake.addFirst(new int[]{nx, ny});
            snakeBody.add(nx + "," + ny);

            // 사과가 있으면, 사과 사라짐
            if (board[nx][ny] == 1) {
                board[nx][ny] = 0;
            } else { // 사과가 없으면, 꼬리 잘라서 몸 줄이기
                int[] tail = snake.removeLast();
//                System.out.print(Arrays.toString(tail));
                snakeBody.remove(tail[0] + "," + tail[1]);
            }

            // 방향 전환 정보 (몇 초에 좌 or 우 회전)
            if (dirChangeInfo.containsKey(time)) {
                char changingDir = dirChangeInfo.get(time); // 방향 뽑아내서

                if (changingDir == 'L') { // 왼쪽 회전
                     direction = (direction + 3) % 4;
                } else { // 오른쪽 회전
                    direction = (direction + 1) % 4;
                }
            }

            x = nx;
            y= ny;
        }


    }
}
