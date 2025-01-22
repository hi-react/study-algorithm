package boj_g4_18427_함께블록쌓기;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, M, H;
        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 최대 블록 수
        H = Integer.parseInt(st.nextToken()); // 목표 높이

        // 학생들의 블록 정보 저장
        int[][] blocks = new int[N + 1][]; // i번째 학생으로 맞추기 위해 N + 1
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int size = st.countTokens();
            blocks[i] = new int[size]; // 학생 i의 size 크기의 블록 배열
            for (int j = 0; j < size; j++) {
                blocks[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DP 테이블 초기화
        int[][] dp = new int[N + 1][H + 1];
        dp[0][0] = 1; // 0번째 학생으로 높이 0 만드는 경우의 수

        // i번째 학생까지 사용해서 높이 j 만드는 경우의 수 계산
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= H; j++) {
                dp[i][j] = dp[i-1][j]; // 일단 윗 줄 복사 (i번째 학생의 블록 사용 X)

                // i번째 학생이 갖고 있는 블록 하나씩 체크
                for(int block : blocks[i]) {
                    if (j >= block) {
                        dp[i][j] += dp[i-1][j-block]; // block 사용하는 경우
                        dp[i][j] %= 10007;
                    }
                }
            }
        }

        br.close();
        bw.write(dp[N][H] + "\n");
        bw.flush();
        bw.close();
    }
}
