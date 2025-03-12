package boj_g3_2835_인기도조사;

import java.io.*;
import java.util.*;

public class Main2 {
    static final int MAX_TIME = 24 * 60 * 60; // 24시간 = 86,400초
    static int[] viewers = new int[MAX_TIME]; // 각 초마다 시청자 수
    static long[] prefixSum = new long[MAX_TIME]; // 누적 합 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 1. 시청 시간 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " :-");
            int start = timeToSecond(st);
            int end = timeToSecond(st);

            // 각 초에 해당하는 시청자 수 변화량 기록 (차분 배열 적용)
            if (start <= end) {
                viewers[start]++;
                if (end + 1 < MAX_TIME) viewers[end + 1]--;
            } else { // 자정 넘기는 경우 (ex. 23:45:30 - 01:15:00)
                // 시작 ~ 자정
                viewers[start]++;
                // 자정 ~ 종료
                viewers[0]++;
                if (end + 1 < MAX_TIME) viewers[end + 1]--;
            }
        }

        // 2. 각 초에 해당하는 시청자 수 계산 (변화량 누적 합)
        for (int i = 1; i < MAX_TIME; i++) {
            viewers[i] += viewers[i - 1];
        }

        // 3. 특정 구간의 시청자 수 총합을 O(1)로 구하기 위해 (누적 합 배열)
        prefixSum[0] = viewers[0];
        for (int i = 1; i < MAX_TIME; i++) {
            prefixSum[i] = prefixSum[i - 1] + viewers[i];
        }

        // 4. 질문 처리
        int Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " :-");
            int start = timeToSecond(st);
            int end = timeToSecond(st);

            // 구간 합 구하고 평균 계산
            long sum;
            double avg;
            if (start <= end) {
                sum = prefixSum[end] - (start > 0 ? prefixSum[start - 1] : 0);
                avg = (double) sum / (end - start + 1);
            } else { // 자정 넘기는 경우 (ex. 23:45:30 - 01:15:00)
                sum = (prefixSum[MAX_TIME - 1] - prefixSum[start - 1]) + prefixSum[end];
                avg = (double) sum / (MAX_TIME - start + end + 1);
            }

            bw.write(String.format("%.10f\n", avg));
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static int timeToSecond(StringTokenizer st) {
        return 3600 * Integer.parseInt(st.nextToken()) + 60 * Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
    }
}
