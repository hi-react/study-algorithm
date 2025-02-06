package boj_g5_2212_센서;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력
        int N = Integer.parseInt(br.readLine()); // 센서 수
        int K = Integer.parseInt(br.readLine()); // 집중국 수

        int[] sensors = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }

        // 센서 오름차순 정렬
        Arrays.sort(sensors); // 1 3 6 6 7 9

        // 센서 간 거리 계산 및 오름 차순 정렬
        int[] dist = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            dist[i] = sensors[i + 1] - sensors[i];
        }
        Arrays.sort(dist); // 0 1 2 2 3

        // K개 집중국 세우기 = K개 그룹으로 나누기
        // => dist 배열에서 제일 먼 거리 순으로 K - 1개 제거
        int res = 0;
        for (int i = 0; i < N - 1 - (K - 1); i++) {
            res += dist[i];
        }

        br.close();
        bw.write(res + "\n");
        bw.flush();
        bw.close();
    }
}
