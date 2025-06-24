package boj_g4_17144_미세먼지안녕;

import java.io.*;
import java.util.*;

public class Main {
    static int R, C, T;
    static int[][] room;
    static int[] airCleaner = new int[2];

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new int[R][C];

        int airCleanerIdx = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    airCleaner[airCleanerIdx++] = i;
                }
            }
        }

        br.close();

        while (T-- > 0) {
            spread();
            clear();
        }

        int result = getDust();
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    static void spread() {
        int[][] tmp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) {
                    int spreadAmount = room[i][j] / 5;
                    int count = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx >= 0 && nx < R && ny >=0 && ny < C && room[nx][ny] != -1) {
                            tmp[nx][ny] += spreadAmount; // 확산된 미세먼지 누적
                            count++;
                        }
                    }
                    tmp[i][j] += room[i][j] - (spreadAmount * count); // 확산 후 남은 미세먼지 누적
                }
            }
        }

        tmp[airCleaner[0]][0] = -1; // 공기청정기 위치 유지
        tmp[airCleaner[1]][0] = -1;
        room = tmp;
    }

    static void clear() {
        int upper = airCleaner[0]; // 위쪽 공기 청정기 (반시계)
        for (int i = upper-1; i > 0; i--) room[i][0] = room[i-1][0];
        for (int j = 0; j < C-1; j++) room[0][j] = room[0][j+1];
        for (int i = 0; i < upper; i++) room[i][C-1] = room[i+1][C-1];
        for (int j = C-1; j > 1; j--) room[upper][j] = room[upper][j-1];
        room[upper][1] = 0;

        int lower = airCleaner[1]; // 아래쪽 공기 청정기 (시계)
        for (int i = lower+1; i < R-1; i++) room[i][0] = room[i+1][0];
        for (int j = 0; j < C-1; j++) room[R-1][j] = room[R-1][j+1];
        for (int i = R-1; i > lower; i--) room[i][C-1] = room[i-1][C-1];
        for (int j = C-1; j > 1; j--) room[lower][j] = room[lower][j-1];
        room[lower][1] = 0;
    }

    static int getDust() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) {
                    sum += room[i][j];
                }
            }
        }
        return sum;
    }
}
