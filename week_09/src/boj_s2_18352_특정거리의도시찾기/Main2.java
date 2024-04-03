package boj_s2_18352_특정거리의도시찾기;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// 순수 BFS -> dist 배열 없이 int 사용해서 구하는 방법 (대신 오름차순 정렬 위해 List 사용)
public class Main2 {

	static int N; // 도시 수
	static List<Integer>[] adjcity; // 각 도시에 인접한 도시 목록(인접 리스트의 배열)

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 도시 수
		int M = sc.nextInt(); // 도로 수

		int goal = sc.nextInt(); // 목표 최단 거리
		int start = sc.nextInt(); // 출발 도시

		adjcity = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjcity[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			adjcity[u].add(v);
		}

		bfs(start, goal);

		sc.close();
	}

	private static void bfs(int start, int goal) {

		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];

		List<Integer> result = new ArrayList<>(); // 결과를 저장할 리스트

		queue.offer(start);
		visited[start] = true;

		int level = 0;

		while (!queue.isEmpty() && level <= goal) {

//			if (level == goal)
//				break; // 목표 레벨에 도달하면 추가 탐색 중지 (한 번이라도 덜 돌게 최적화..)

			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int current = queue.poll();

				for (int next : adjcity[current]) {
					if (!visited[next]) {
						if (level + 1 == goal) { // 다음 레벨이 목표 레벨이면 결과에 추가
							result.add(next);
						}
						queue.offer(next);
						visited[next] = true;
					}
				}
			}

			level++;
		}

		// 다 돌고 나왔을 때,
		if (result.isEmpty()) {
			System.out.println(-1);
		} else {
			Collections.sort(result); // 결과 오름차순 정렬
			for (int city : result) {
				System.out.println(city);
			}
		}
	}

}
