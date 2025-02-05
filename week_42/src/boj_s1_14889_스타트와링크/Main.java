package boj_s1_14889_스타트와링크;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] ability;
    static boolean[] visited;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력
        N = Integer.parseInt(br.readLine());
        ability = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 팀 나누기
        visited[0] = true; // 첫 번째 선수는 무조건 스타트 팀으로
        divideTeam(1, 1);

        br.close();
        bw.write(minDiff + "\n"); // 최소 차이 출력
        bw.flush();
        bw.close();
    }

    // [method 1] 두 팀으로 나누기
    static void divideTeam(int index, int count) {
        if (count == N / 2) {
            calculateDiff();
            return;
        }

        for (int i = index; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true; // i번을 스타트 팀에 추가
                divideTeam(i + 1, count + 1);
                visited[i] = false; // 백트래킹 (원상복구)
            }
        }

    }

    // [method 2] 팀 별 능력 차이 계산
    static void calculateDiff() {
        int startTeam = 0, linkTeam = 0;

        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i] && visited[j]) {
                    startTeam += ability[i][j];
                } else if (!visited[i] && !visited[j]) {
                    linkTeam += ability[i][j];
                }
            }
        }

        int diff = Math.abs(startTeam - linkTeam);
        minDiff = Math.min(diff, minDiff);
    }
}
