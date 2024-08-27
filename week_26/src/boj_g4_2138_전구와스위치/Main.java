package boj_g4_2138_전구와스위치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 1. 문제 분석
 * N개의 스위치와 N개의 전구
 * 각각의 전구는 켜져 있거나 꺼져 있다.
 * i번째 스위치를 누르면, i-1, i, i+1 세 개의 전구 상태가 바뀜
 * 단, 제일 첫 번째의 경우 1, 2번 전구만 변경, 마지막의 경우 N, N-1번 전구만 변경
 * 주어진 전구의 상태를 원하는 전구의 상태로 변경하기 위한 최소 스위칭 횟수 찾기
 * 불가능하면 -1
 *
 * 2. 문제 풀이
 * 전구가 꺼지고 켜지는 행위는 boolean 값으로 관리
 * 결국 i번째 전구의 최종 상태는 i+1번째 스위치에 달려 있다.
 * 첫 시작이 되는 0번째 스위치 눌렀는 지 여부 분기 필요
 *
 * 3. 알고리즘: 그리디 + 브루트 포스
 * 그리디: 각 스위치를 누를 지 말 지 결정할 때, 현재 상황에서의 최선을 선택
 * 즉, i번째 전구 상태를 목표에 맞추기 위해 i+1번째 스위치 누를지 말 지 결정
 * 브루트 포스: 초기 조건, 즉 첫 번째 스위치 누르는 경우 / 누르지 않는 경우 각각 모두 시도
 *
 */

public class Main {

    static int N; // N개의 스위치, N개의 전구

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // N개의 전구

        boolean[] asIs = new boolean[N]; // 전구의 현재 상태 배열
        boolean[] toBe = new boolean[N]; // 만들고자 하는 상태 배열

        // 전구 상태 string 받아서 -> boolean 값으로 변경
        stringToBoolean(br.readLine(), asIs);
        stringToBoolean(br.readLine(), toBe);

        // 1. 첫 번째 스위치 눌렀는 지에 따른 분기
        boolean[] clickFirstSwitch = asIs.clone();
        boolean[] noClickFirstSwitch = asIs.clone();

        // 첫 번째 스위치 클릭 O
        switchClick(clickFirstSwitch, 0);
        int switchCountForFirstClick = 1; // 얘는 일단 한 번 누르고 시작하니까

        // 첫 번째 스위치 클릭 X
        int switchCountForFirstNoClick = 0;

        // 2. 두 번째 스위치부터 돌면서 i-1번째 값을 확인
        for (int i = 1; i < N; i++) {
            // 2-1) i-1번째 전구 상태 값이 다르면, i번째 스위치 누른다. (count up)
            if (clickFirstSwitch[i - 1] != toBe[i - 1]) {
                switchClick(clickFirstSwitch, i);
                switchCountForFirstClick++;
            }
            if (noClickFirstSwitch[i - 1] != toBe[i - 1]) {
                switchClick(noClickFirstSwitch, i);
                switchCountForFirstNoClick++;
            }

            // 2-2) 결과적으로 toBe 만족했는 지 확인 -> 두 경우 중 최소 값 계산
            if (Arrays.equals(clickFirstSwitch, toBe)) {
                // [95% 오류] 둘 다 동시에 같은 경우 더 작은 걸로
                if (Arrays.equals(noClickFirstSwitch, toBe)) {
                    System.out.println(Math.min(switchCountForFirstClick, switchCountForFirstNoClick));
                    System.exit(0);
                } else {
                System.out.println(switchCountForFirstClick);
                    System.exit(0);
                }
            } else if (Arrays.equals(noClickFirstSwitch, toBe)) {
                System.out.println(switchCountForFirstNoClick);
                System.exit(0);
            }
        }

        // 다 돌고 그냥 나왔다면, 불가능한 경우임
        System.out.println(-1);
    }

    // method 1 : 전구 입력 값 변경 (string -> boolean)
    static void stringToBoolean(String str, boolean[] array) {
        int N = str.length();
        for (int i = 0; i < N; i++) {
            char now = str.charAt(i);
            array[i] = (now == '1');
        }
    }

    // method 2 : 스위치 눌러서 전구 상태 값 바꾸기
    static void switchClick(boolean[] array, int i) {
        if (i > 0) {
            array[i - 1] = !array[i - 1];
        }
        array[i] = !array[i];
        if (i != (N - 1)) {
            array[i + 1] = !array[i + 1];
        }
    }
}
