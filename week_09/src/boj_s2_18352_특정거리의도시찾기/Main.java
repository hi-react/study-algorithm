package boj_s2_18352_특정거리의도시찾기;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N; // 도시 수
	static List<Integer>[] adjcity; // 인접 리스트의 배열(각 도시에 인접한 도시 정점 인덱스 담을거야)

	static int[] dist; // 최단 경로 배열
	static int INF = Integer.MAX_VALUE; // 무한 대 값 저장해두고 사용

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 도시 수 (정점 수) -> 1부터 시작
		int M = sc.nextInt(); // 도로 수 (간선 수)

		int goal = sc.nextInt(); // 목표 최단 거리
		int start = sc.nextInt(); // 출발 도시

		// 인접 리스트의 배열 -> 초기화
		adjcity = new ArrayList[N + 1]; // 도시 N개인데 인덱스 1부터 시작
		for (int i = 1; i <= N; i++) {
			adjcity[i] = new ArrayList<>();
		}

		// 인접 리스트에 인접 도시(정점) 인덱스 추가
		for (int i = 0; i < M; i++) {
			adjcity[sc.nextInt()].add(sc.nextInt());
		}

		// 최단 경로 배열 초기화 (최대 값으로)
		dist = new int[N + 1];
		Arrays.fill(dist, INF);

		dijkstra(start);

		boolean isPossible = false; // 목표 거리에 맞는 도시가 하나도 없음을 default로 놓고,

		// 최소 거리를 담아 놓은 dist 배열을 돌면서
		for (int i = 1; i <= N; i++) {
			if (dist[i] == goal) { // 목표로 하는 거리 길이 있으면,
				isPossible = true; // 가능하다고 바꿔주고
				System.out.println(i); // 해당 도시 번호 출력
			}
		}

		// 불가능 하다면 -1 반환
		if (!isPossible)
			System.out.println(-1);

		sc.close();
	}

	private static void dijkstra(int start) {

		// 1. 큐 생성
		Queue<Integer> pq = new LinkedList<>();

		// 2. 방문 처리용 배열
		boolean[] visited = new boolean[N + 1]; // 도시 1 ~ N

		// 3. 시작 지점의 최소 거리 0부터 시작
		dist[start] = 0;

		// 4. 큐에 시작 지점 넣어
		pq.offer(start);

		// 큐가 비지 않는 동안 반복
		while (!pq.isEmpty()) {

			int curr = pq.poll(); // 도시 하나 꺼내

			// 방문 했으면 pass
			if (visited[curr])
				continue;

			// 방문 안했으면 여기로 넘어오겠지 -> 방문 처리
			visited[curr] = true;

			// 해당 도시의 인접 도시 다 가져와
			for (int near : adjcity[curr]) {

				// 방문하지 않았고 && 인접 도시에 direct로 가는 것보다 > 해당 도시를 거쳐서 가는 게 짧으면
				if (!visited[near] && dist[near] > dist[curr] + 1) { // 모든 도로의 길이 = 1
					dist[near] = dist[curr] + 1;
					pq.offer(near);
				}
			}

		}

	}

}
