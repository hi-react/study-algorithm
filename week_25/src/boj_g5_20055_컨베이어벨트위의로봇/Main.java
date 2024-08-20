package boj_g5_20055_컨베이어벨트위의로봇;

import java.util.Scanner;

public class Main {
    static int N, K; // 벨트 길이, 종료 조건인 칸의 개수
    static int[] belts; // 컨베이어 벨트 내구도 배열
    static boolean[] robots; // 로봇 존재 여부 배열
    static int zeroCount; // 내구도 0인 칸의 개수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        belts = new int[2 * N];
        robots = new boolean[N];

        for (int i = 0; i < 2 * N; i++) {
            belts[i] = sc.nextInt();
        }

        int step = 0; // 최종 반환할 값 (몇 번째 단계에서 종료 되었는 지)
        zeroCount = 0;

        while (zeroCount < K) {
            step++;

            rotate();
            move();
            put();
        }

        System.out.println(step);
    }

    // 1. 벨트와 함께 로봇 한 칸씩 회전 시키기
    static void rotate() {
        // 벨트 회전
        int lastPoint = belts[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            belts[i] = belts[i - 1];
        }
        belts[0] = lastPoint;

        // 로봇 회전
        for (int i = N - 1; i > 0; i--) {
            robots[i] = robots[i - 1];
        }
        robots[N - 1] = false; // 마지막 로봇 내리고
        robots[0] = false; // 첫 로봇도 이동했으니까 false
    }

    // 2. 로봇 스스로 이동
    static void move() {
        for (int i = N - 2; i > 0; i--) {
            if (robots[i] && !robots[i + 1] && belts[i + 1] > 0) {
                robots[i] = false;
                robots[i + 1] = true;
                belts[i + 1]--;
                if (belts[i + 1] == 0) zeroCount++;
            }
        }
        robots[N - 1] = false; // 마지막 로봇이면 내려
    }

    // 3. 로봇 올리기
    static void put() {
        if (belts[0] > 0) {
            robots[0] = true;
            belts[0]--;
            if (belts[0] == 0) zeroCount++;
        }
    }

}
