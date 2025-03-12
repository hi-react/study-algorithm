package boj_g3_2835_인기도조사;

/**
 * 시청 시간 기록 직접 순회하지 않았는데도 메모리 초과
 * split 메모리가 그렇게 많이 드냐고 ..
 *
 * 자정 넘기는 경우도 고려되어 있지 않은 코드
 * */

import java.io.*;

public class Main {
    static final int MAX_TIME = 24 * 60 * 60; // 24시간 = 86,400초
    static int[] viewers = new int[MAX_TIME]; // 각 초마다 시청자 수
    static long[] prefixSum = new long[MAX_TIME];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 1. 시청 시간 입력
        for (int i = 0; i < N; i++) {
            String[] timeRange = br.readLine().split(" - ");
            int start = timeToSecond(timeRange[0]);
            int end = timeToSecond(timeRange[1]);

            // 각 초에 해당하는 시청자 수 변화량 기록 (차분 배열 적용)
            viewers[start]++;
            if (end + 1 < MAX_TIME) viewers[end + 1]--;
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
            String[] timeRange = br.readLine().split(" - ");
            int start = timeToSecond(timeRange[0]);
            int end = timeToSecond(timeRange[1]);

            // 구간 합 구하고 평균 계산
            long sum = prefixSum[end] - (start > 0 ? prefixSum[start - 1] : 0);
            double avg = (double) sum / (end - start + 1);

            sb.append(String.format("%.10f", avg)).append("\n");
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int timeToSecond(String time) {
        String[] hhmmss = time.split(":");
        int hh = Integer.parseInt(hhmmss[0]);
        int mm = Integer.parseInt(hhmmss[1]);
        int ss = Integer.parseInt(hhmmss[2]);

        return hh * 3600 + mm * 60 + ss;
    }
}
