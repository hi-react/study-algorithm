package boj_s3_2606_바이러스;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static ArrayList<Integer>[] list; // 인접 리스트의 배열
	static boolean[] visited; // 방문 배열
	static int count; // 몇 대의 컴퓨터가 바이러스에 걸리는 지 세어 주기 위한 변수

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 컴퓨터 수(노드 수)
		int M = sc.nextInt(); // 연결된 네트워크 정보(에지 수)

		// 인접 리스트 배열을 N+1 크기로 초기화
		list = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		// 연결된 네트워크 정보 확인하면서 인접 리스트 배열에 데이터 채워 넣기
		for (int i = 0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();

			list[start].add(end);
			list[end].add(start);
		}

		// 방문 배열 N+1 크기로 초기화
		visited = new boolean[N + 1];

		// 1번 컴퓨터 부터 웜 바이러스에 걸리기 시작 (시작 노드)
		DFS(1);

		System.out.println(count - 1); // 1번 컴퓨터는 제외해야 하니까 -1

		sc.close();
	}

	static void DFS(int Node) {
		// 일단 DFS가 실행되면, count 변수 올려준다.
		count++;

		// visited 배열에 해당 컴퓨터를 확인(방문)했음을 기록
		visited[Node] = true;

		// 현재 컴퓨터(Node)와 연결된 컴퓨터(Node) 모두 순회하면서,
		// visited가 false인 경우(아직 확인 안 한 경우), DFS 재귀 호출
		for (int i : list[Node]) {
			if (!visited[i])
				DFS(i);
		}

	}

}
