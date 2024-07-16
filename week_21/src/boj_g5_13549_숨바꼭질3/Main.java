package boj_g5_13549_숨바꼭질3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	static int N, K; // 시작, 도착 지점

	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("src/boj_g5_13549_숨바꼭질3/input.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		System.out.println(findMinTime(N, K));

		sc.close();
	}

	private static int findMinTime(int start, int end) {

		final int INF = 987654321;

		// 위치 별 최소 시간 저장할 배열 (0 ~ 100,000)
		int maxPosition = 100000;
		int[] time = new int[maxPosition + 1];
		Arrays.fill(time, INF); // 최대 값으로 초기 값 설정
		time[start] = 0; // 시작 지점은 0

		// 다음 이동할 위치 판단 하기 위한 deque
		Deque<Integer> deque = new LinkedList<>();
		deque.add(start); // 시작 지점부터 넣고 시작

		while (!deque.isEmpty()) {
			int current = deque.poll(); // 현재 위치 뽑기

			// 이동 가능한 경로 배열
			int[] nextPosition = { current - 1, current + 1, current * 2 };

			for (int next : nextPosition) {
				if (next >= 0 && next <= 100000) { // 이동 가능 범위 내
					int newTime = time[current] + (next == current * 2 ? 0 : 1);

					if (newTime < time[next]) { // 다음 이동할 경로가 최소 시간 배열 갱신할 수 있는 경우
						time[next] = newTime;
						if (next == current * 2) { // 0초 걸리니까 우선 순위를 주기 위해 앞에 삽입 
							deque.addFirst(next); 
						} else {
							deque.addLast(next); // 1초 걸리니까 뒤에 삽입 
						}
					}
					
					if (next == end) {
						return time[next]; // 도착 ! 
					}
					
				}
			}
		}

		return -1; // 찾지 못하는 경우 (문제 상 주어지지 않으나 int 반환형 처리 위해)

	}

}
