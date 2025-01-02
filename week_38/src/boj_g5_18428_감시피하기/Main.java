package boj_g5_18428_감시피하기;

import java.io.*;
import java.lang.*;
import java.util.*;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int N;
    static char[][] map;
    static List<Point> teachers = new ArrayList<>();
    static List<Point> empties = new ArrayList<>();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean avoidPossible = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 받기
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0); // st.nextToken()은 String 값을 반환하므로 char 타입으로 변환
                if (map[i][j] == 'T') {
                    teachers.add(new Point(i, j));
                } else if (map[i][j] == 'X') {
                    empties.add(new Point(i, j));
                }
            }
        }

        // 장애물 설치
        placeObstacles(0, 0);

        br.close();
        bw.write(avoidPossible ? "YES" : "NO");
        bw.flush();
        bw.close();
    }

    // 장애물 설치 백트래킹
    static void placeObstacles(int count, int start) {
        if (count == 3) {
            if (avoidWatch()) {
                avoidPossible = true;
            }
            return;
        }

        for (int i = start; i < empties.size(); i++) {
            Point empty = empties.get(i);
            map[empty.x][empty.y] = 'O'; // 장애물 설치
            placeObstacles(count + 1, i + 1);
            map[empty.x][empty.y] = 'X'; // 장애물 제거

        }
    }

    // 모든 학생이 감시 피할 수 있는 지 확인
    static boolean avoidWatch() {
        for (Point teacher : teachers) {
            for (int d = 0; d < 4; d++) {
                int x = teacher.x;
                int y = teacher.y;

                while (true) {
                    x += dx[d];
                    y += dy[d];

                    if (x < 0 || x >= N || y < 0 || y >= N || map[x][y] == 'O') {
                        break;
                    }

                    if (map[x][y] == 'S') {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
