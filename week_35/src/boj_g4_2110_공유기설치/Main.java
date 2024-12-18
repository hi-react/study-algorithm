package boj_g4_2110_공유기설치;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이분 탐색
 * <p>
 * 최적화 문제 => 결정 문제로 변환
 * 공유기 간의 최소 거리 mid 인 공유기 를 설치할 수 있는 지
 */

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int home; // 집 개수
    static int router; // 공유기 개수
    static int[] positions; // 집 좌표

    public static void main(String[] args) throws IOException {

//        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        // 1) 입력 받기
        st = new StringTokenizer(br.readLine());

        home = Integer.parseInt(st.nextToken());
        router = Integer.parseInt(st.nextToken());
        positions = new int[home];

        for (int i = 0; i < home; i++) {
            positions[i] = Integer.parseInt(br.readLine());
        }

        br.close();

        // 2) 집 오름 차순 정렬
        Arrays.sort(positions);

        // 3) 이분 탐색 - 가장 인접한 두 공유기 사이의 최대 거리 정하기
        int left = 1; // 최소 거리는 1
        int right = positions[home - 1] - positions[0]; // 최대 거리는 끝 집 - 첫 집
        int result = 1; // 가장 인접한 두 공유기 사이의 최대 거리

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canInstallRouter(mid)) {
                result = mid; // 결과 값 갱신
                left = mid + 1; // 더 큰 거리 시도
            } else {
                right = mid - 1; // 더 작은 거리 시도
            }
        }

        System.out.println(result);
    }

    // [method] 주어진 distance 이상의 거리로 주어진 공유기 설치 할 수 있는 지
    private static boolean canInstallRouter(int distance) {
        int count = 1; // 일단 첫 집에 하나 설치
        int lastInstalledHome = positions[0]; // 현재 공유기 설치된 마지막 집 = 첫 집

        // 두 번째 집부터 돌면서
        for (int i = 1; i < home; i++) {
            // distance 이상 거리가 되면 공유기 설치
            if (positions[i] - lastInstalledHome >= distance) {
                count++;
                lastInstalledHome = positions[i]; // 공유기 설치된 마지막 집 업데이트
            }
        }

        return count >= router; // 설치 해야 하는 Router(공유기) 수 만큼 충분히 설치 했으면 TRUE 반환
    }
}
