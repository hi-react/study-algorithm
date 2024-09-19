package boj_g4_13397_구간나누기2;

/**
 * 1. 문제 분석
 * 주어진 배열(총 N개의 수)을 M개 이하의 구간 으로 나눈다.
 * 각 구간의 점수 = (구간 내) 최대 값 - 최소 값
 * 구간의 점수들 중 최대 값(maxDiff)이 최소로 되도록 해야 한다.
 * 그 최소 값을 출력할 것
 *
 * 2. 문제 풀이
 * 1) 파라메트릭 서치(매개 변수 탐색)
 * 최적화 문제 : 배열을 M개 이하의 구간 으로 나누고, 각 구간의 점수를 구한 후, 그 중에서 가장 큰 점수(maxDiff)를 최소로 만드는 값을 반환
 * => 결정 문제 : 특정 값 mid(maxDiff) 가 주어 졌을 때, 배열을 M개 이하의 구간 으로 나눌 수 있는지 여부를 판단 하는 문제로 변환
 *
 * 2) 핵심
 * mid(maxDiff) 값을 조정 하면서 최소 값을 찾는다.
 * - mid : 구간 내에서 "최대 값 - 최소 값" 차이가 mid(maxDiff) 이하가 되는 지 확인 하는 값
 * - 목표 : 주어진 mid(maxDiff) 에 대해 배열을 M개 이하의 구간 으로 나눌 수 있는 지 확인 한다.
 *
 * 3) 과정
 * 배열을 특정 값 mid(maxDiff) 로 나눈다.
 * "최대 값 - 최소 값"이 mid(maxDiff) 를 넘지 않도록 배열을 나누는 것이 가능한 지 확인 한다.
 * 이 때, 구간의 수가 M개 이하 라면, 해당 mid 값은 가능 하다고 판단 하고, 그렇지 않다면 mid 값이 더 커야 한다.
 *
 * 3. 알고리즘: 이분 탐색, 파라메트릭 서치(매개 변수 탐색)
 * 이분 탐색 : 주어진 범위 내에서 값을 절반 으로 나누며 원하는 값을 찾아 가는 방식
 * 파라메트릭 서치(매개 변수 탐색) : 원하는 조건을 만족 하는 최소 값 또는 최대 값을 찾기 위해, 값을 조정 하며 답을 찾아 가는 과정
 * => 최적 화 문제를 결정 문제로 바꾸기
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M; // N개의 수, M개 이하의 구간
    static int[] numbers; // N개의 숫자 배열

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 구간 점수의 최소 값, 최대 값
        int minDiff = 0; // 구간 점수가 가장 작은 경우 = 최종 반환할 값
        int maxDiff = 0; // 구간 점수가 가장 큰 경우

        // 이분 탐색 범위 설정
        // maxDiff = 구간 점수의 최대 값이 가질 수 있는 최대 값 찾기 (최소 값 빼는 과정 생략)
        for (int i : numbers) {
            maxDiff = Math.max(maxDiff, i);
        }

        // 이분 탐색
        while (minDiff <= maxDiff) {
            int mid = (minDiff + maxDiff) / 2;

            // "최대 값 - 최소 값"이 mid 인 값 기준 으로 M개의 구간 으로 나눌 수 있으면,
            if (canDivideIntoMSection(mid)) {
                maxDiff = mid - 1; // 더 작은 차이(mid) 찾기 위해 maxDiff 줄이고,
            } else {
                minDiff = mid + 1; // 아니면 더 큰 차이(mid) 찾기 위해 minDiff 늘림
            }
        }

        System.out.println(minDiff);

        br.close();

    }

    // [method] 주어진 mid 값(maxDiff)으로 M개의 구간 으로 나눌 수 있는 지 확인
    // 주어진 numbers 배열을 "최대 값 - 최소 값"이 mid(maxDiff) 이하인 구간 으로 나누어, 구간의 개수를 센다.
    // 구간 개수가 M개 이하 라면 가능(true), 그렇지 않다면 불가능(false)
    private static boolean canDivideIntoMSection(int maxDiff) {
        int count = 1; // 구간의 개수를 세는 변수
        int minValue = numbers[0];
        int maxValue = numbers[0];

        // numbers 예시: 1 5 4 6 2 1 3 7
        for (int i = 1; i < N; i++) {
            minValue = Math.min(minValue, numbers[i]);
            maxValue = Math.max(maxValue, numbers[i]);

            // 현재 구간의 점수가 maxDiff 넘으면,
            if (maxValue - minValue > maxDiff) {
                count++; // 구간을 나눌 거니까, 구간 개수 count up

                // i번째 부터 새 구간 시작
                minValue = numbers[i];
                maxValue = numbers[i];
            }

            // 구간의 개수가 M을 넘으면 false 반환
            if (count > M) return false;
        }

        // for 문 다 돌고 나왔다 ? => M개 이하의 구간 으로 나눌 수 있다.
        return true;
    }

}