package boj_g4_17471_게리맨더링;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	// static 변수
	static int N; // 지역 개수
	static int[] people; // 지역별 인구 수 배열
	static List<Integer>[] adj; // 연결된 지역 정보를 담는 인접 리스트

	static boolean[] selected; // 해당 지역 선택 여부 (T/F로 두 그룹 나눔)
	static boolean[] visited; // 해당 지역 방문 배열

	static int minDiff = Integer.MAX_VALUE; // 최종 반환할 두 선거구의 인구 차이 최소값

	// main
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 1. 입력 값 받고 초기화 하는 단계 !

		// 1) 지역 개수
		N = sc.nextInt();

		// 2) 지역별 인구 수 배열 초기화 & 입력 값 받기
		people = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			people[i] = sc.nextInt();
		}

		// 3-1) 연결된 지역 정보를 담는 인접 리스트 - 초기화
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		// 3-2) 각 지역 돌면서 위의 연결 리스트 완성하기
		for (int i = 1; i <= N; i++) {

			int m = sc.nextInt(); // 해당 지역에 연결된 지역 수

			// m 번 반복 (연결된 지역 수 만큼)
			while (m > 0) {
				int location = sc.nextInt(); // 지역 번호 받아서
				adj[i].add(location); // 연결 리스트에 추가

				m--;
			}
		}

		// 2. 두 선거구 '부분 집합' 생성할거다.
		selected = new boolean[N + 1]; // 선거구 선택 여부 초기화
		subsets(1); // index (1번 지역부터 시작)

		// 출력 ('부분 집합'을 만들 수 없는 경우, 초기 설정한 minDiff가 갱신되지 않는다.)
		System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);

		sc.close();

	}

	// [method 1] 부분 집합 : 두 선거구 '부분 집합' 나누는 method
	private static void subsets(int idx) { // N개의 선거구 index

		// 1. 기저 조건
		if (idx > N) { // N개의 선거구 한 번 싹 돌아서 selected 배열 완성하면

			// 해당 선거구 Grouping 가능한 것인지 확인
			if (isGroupValid()) { // 가능하다면!!
				int diff = calculateDiff(); // 인구 차이 계산
				if (minDiff > diff)
					minDiff = diff; // 인구 차이 최소값 갱신 !
			}

			return;

		}

		// 2. 재귀 조건
		selected[idx] = true; // 해당 선거구 선택 O
		subsets(idx + 1); // 다음 판단

		selected[idx] = false; // 해당 선거구 선택 X
		subsets(idx + 1); // 다음 판단

	}

	// [method 2] 해당 선거구 Grouping 가능한 것인지 확인하는 method

	// selected 배열의 true, false 결과에 따라 선거구를 2개로 나눠서 DFS로 보내
	private static boolean isGroupValid() {

		visited = new boolean[N + 1]; // 방문 배열 초기화 (F)

		// 1. 첫 번째 그룹(true) - 연결성 검사 (최초 T 나온 거 돌리고 끝)

		for (int i = 1; i <= N; i++) {
			if (selected[i]) {
				DFS(i, true);
				break;
			}
		}

		// 첫 번째 그룹에서 모든 구역이 방문 되었는 지 확인! (하나라도 방문 X -> 모든 선거구가 연결되어 있지 않은 것)
		for (int i = 1; i <= N; i++) {
			if (selected[i] != visited[i])
				return false; // 해당 grouping 불가 반환
		}

		visited = new boolean[N + 1]; // 방문 배열 다시 초기화 (F)

		// 2. 두번 째 그룹(false) - 연결성 검사 (최초 F 나온 거 돌리고 끝)
		for (int i = 1; i <= N; i++) {
			if (!selected[i]) {
				DFS(i, false);
				break;
			}
		}

		// 두 번째 그룹에서 모든 구역이 방문 되었는 지 확인! (하나라도 방문 X -> 모든 선거구가 연결되어 있지 않은 것)
		for (int i = 1; i <= N; i++) {
			if (!selected[i] && !visited[i]) // false인 애만 확인, 방문 안 한거 있으면
				return false; // 해당 grouping 불가 반환
		}

		return true; // 모든 검증 통과 (all 연결)
	}

	// [method 3] DFS : 연결되었는 지 확인 (visited 배열 갱신 목적)
	private static void DFS(int curr, boolean flag) {

		// 1. 기저 조건
		// 이미 방문 했거나, 같은 그룹 내에 있지 않다면 짤!
		if (visited[curr] || selected[curr] != flag)
			return;

		// 2. 같은 그룹 내에 있다면, 방문 처리
		visited[curr] = true;

		// 3. 재귀 조건
		// 해당 지역과 연결된 지역 하나 씩 순회 하면서
		for (int connected : adj[curr]) {
			// 같은 그룹 내 && 아직 방문하지 않았다면
			if (selected[connected] == flag && !visited[connected]) {
				DFS(connected, flag); // 재귀 호출
			}
		}

	}

	// [method 4] 구역 별 인구 수 차이 구하는 method
	private static int calculateDiff() {
		int sum1 = 0; // 1 그룹 인구 합
		int sum2 = 0; // 2 그룹 인구 합

		// selected 배열을 돌면서 -> true면 1 그룹, false면 2 그룹
		for (int i = 1; i <= N; i++) {
			if (selected[i]) // true
				sum1 += people[i];
			else // false
				sum2 += people[i];
		}

		return Math.abs(sum1 - sum2);
	}

}
