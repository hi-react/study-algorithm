package boj_g5_1374_강의실;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1. 문제 분석
 * 1) 조건
 * N개의 강의 (강의 번호, 강의 시작 시간, 강의 종료 시간 주어짐)
 * 하나의 강의실 에서 2개 이상의 강의 동시 진행 불가
 * 한 강의의 종료 시간과 다음 강의의 시작 시간은 겹칠 수 있음
 *
 * 2) 제약
 * 강의 개수 : 1 ≤ N ≤ 100,000
 * 강의 시작 시간, 종료 시간 : 0 이상 10억 이하의 정수
 *
 * 3) 목표
 * 모든 강의가 이루어 지기 위해 필요한 최소 강의실 개수 출력
 *
 * 2. 문제 풀이
 * 1) 최초 정렬
 * 모든 강의를 '시작 시간' 기준 으로 오름차순 정렬
 * '시작 시간' 동일한 경우, '종료 시간' 기준 으로 오름차순 정렬
 *
 * 2) 우선 순위 큐 - 예시
 * 우선 순위 큐에 첫 번째 강의의 종료 시간을 추가
 * 1)에서 정렬한 두 번째 강의 부터 확인
 *
 * IF 두 번째 강의의 시작 시간 >= 우선 순위 큐에 있는 값(첫 번째 강의의 종료 시간)
 * => 기존 강의실 사용 가능 하므로, 우선 순위 큐에 있는 값을 제거
 * (부등호 반대 라면, 새로운 강의 실 필요)
 *
 * 우선 순위 큐에 두 번째 강의의 종료 시간을 추가
 *
 * 3) 우선 순위 큐 - 일반화
 * IF 현재 강의의 시작 시간 >= 우선 순위 큐의 최소 값(가장 빨리 끝나는 강의의 종료 시간)
 * => 기존 강의실 사용 가능 하므로, 우선 순위 큐에 있는 값을 제거
 * (부등호 반대 라면, 새로운 강의 실 필요)
 *
 * 우선 순위 큐에 현재 강의의 종료 시간을 추가
 *
 * 4) 결론: 필요한 최소 강의실 개수 = 우선 순위 큐에 남아 있는 요소의 개수
 *
 * 3. 알고리즘 : 그리디, 정렬(람다 식 사용)
 * 각 단계에서 가장 최적의 선택을 함으로써 최적 해를 구하는 알고리즘
 *
 * 이 문제에서는 강의를 순서 대로 확인 하면서,
 * 매번 현재 강의의 시작 시간 VS 우선 순위 큐의 최소 값(가장 빨리 끝나는 강의의 종료 시간)을 비교,
 * 기존 강의실을 사용할 건지, 새로운 강의실을 추가할 건지 결정함
 *
 * 즉, 매번 현재 시점에서 최적의 선택을 반복 수행하므로 그리디 !
 *
 * 4. 자료 구조 : 우선 순위 큐
 * 강의 종료 시간이 빠른 순서 대로 관리
 *
 */

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

//        br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        // 1) 입력 받기
        int N = Integer.parseInt(br.readLine());
        int[][] lectures = new int[N][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken(); // 강의실 번호 버리고
            lectures[i][0] = Integer.parseInt(st.nextToken());
            lectures[i][1] = Integer.parseInt(st.nextToken());
        }

        // 2) 정렬 - 강의 시작 시간 기준 (같으면 종료 시간 기준)
        Arrays.sort(lectures, (a, b) -> { // 람다 식
            if (a[0] == a[1]) {
                return a[1] - b[1]; // 종료 시간 기준
            }
            return a[0] - b[0]; // 시작 시간 기준
        });

        // 3) 우선 순위 큐 - 강의 종료 시간 관리
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0][1]); // 첫 번째 강의의 종료 시간을 추가

        // 두 번째 강의부터 확인하면서,
        for(int i = 1; i < N; i++) {
            // 현재 강의의 시작 시간 >= 우선 순위 큐의 최소 값(가장 빠른 강의 종료 시간)
            if (lectures[i][0] >= pq.peek()) {
                pq.poll(); // 기존 강의실 사용 가능 => 우선 순위 큐에서 제거
            }
            // 우선 순위 큐에 현재 강의의 종료 시간 추가
            pq.offer(lectures[i][1]);
        }

        System.out.println(pq.size());

        br.close();

    }
}
