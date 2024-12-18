package boj_g5_2096_내려가기;

import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

    static int N; // 몇 줄
    static int[][] inputs; // 입력 값

    // 최종 반환 형식 (최대 값, 최소 값)
    static class Score {
        private final int max, min;

        public Score(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 받기
        N = Integer.parseInt((br.readLine()));
        inputs = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            inputs[i][0] = Integer.parseInt(st.nextToken());
            inputs[i][1] = Integer.parseInt(st.nextToken());
            inputs[i][2] = Integer.parseInt(st.nextToken());
        }

        // 최대, 최소 계산
        Score result = calculateMaxAndMin();

        bw.write(result.max + " " + result.min + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    // [method] 최대 값, 최소 값 계산
    private static Score calculateMaxAndMin() {
        int[] maxDp = new int[3];
        int[] minDp = new int[3];
        int[] tmpMax = new int[3];
        int[] tmpMin = new int[3];

        // DP 배열 초기화
        maxDp[0] = minDp[0] = inputs[0][0];
        maxDp[1] = minDp[1] = inputs[0][1];
        maxDp[2] = minDp[2] = inputs[0][2];

        // 최대, 최소 값 계산
        for (int i = 1; i < N; i++) {
            // 최대
            tmpMax[0] = Math.max(maxDp[0], maxDp[1]) + inputs[i][0];
            tmpMax[1] = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]) + inputs[i][1];
            tmpMax[2] = Math.max(maxDp[1], maxDp[2]) + inputs[i][2];

            // 최소
            tmpMin[0] = Math.min(minDp[0], minDp[1]) + inputs[i][0];
            tmpMin[1] = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]) + inputs[i][1];
            tmpMin[2] = Math.min(minDp[1], minDp[2]) + inputs[i][2];

            // 임시 배열 => maxDp, minDp 배열에 복사
            System.arraycopy(tmpMax, 0, maxDp, 0, 3);
            System.arraycopy(tmpMin, 0, minDp, 0, 3);
        }

        int maxScore = Math.max(Math.max(maxDp[0], maxDp[1]), maxDp[2]);
        int minScore = Math.min(Math.min(minDp[0], minDp[1]), minDp[2]);

        return new Score(maxScore, minScore);
    }
}
