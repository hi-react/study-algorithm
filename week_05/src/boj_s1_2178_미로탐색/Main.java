package boj_s1_2178_미로탐색;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
	int x;
	int y;
	int distance; // 해당 노드 까지의 이동 거리

	// 생성자 (좌표 x, y)
	public Node(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
}

public class Main {

	static int N, M;
	static int[][] miro; // 미로 배열
	static boolean[][] visited; // 방문 배열

	public static void main(String[] args) {

		// 입력 받기
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		// 미로 배열 초기화
		miro = new int[N][M];

		// 미로 배열 값 넣어주기
		for (int i = 0; i < N; i++) {
			String line = sc.next();
			for (int j = 0; j < M; j++) {
				miro[i][j] = Character.getNumericValue(line.charAt(j));
			}
		}

		// 방문 배열 초기화 (false)
		visited = new boolean[N][M];

		// BFS 실행 결과 = result(지나야 하는 최소 칸 수) 라고 반환 받는다.
		int result = BFS(0, 0); // miro 배열의 (0,0) 좌표에서 부터 시작

		System.out.println(result);

		sc.close();
	}

	private static int BFS(int x, int y) {

		// 0. Queue 생성 (Node 타입: x, y, distance 갖는다.) - 구현체는 LinkedList
		Queue<Node> queue = new LinkedList<>();

		// 0. 델타 배열 생성 (상, 하, 좌, 우)
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		// 1. 시작 노드의 좌표(x, y)와 현재 이동 거리(1)로 이루어진 Node를 queue에 넣어줍니다.
		queue.offer(new Node(x, y, 1));

		// 2. visited 배열에 현재 노드 방문 기록!
		visited[x][y] = true;

		// 3. Queue가 비기 전까지 반복을 돌립니다.
		while (!queue.isEmpty()) {

			// 1) queue에서 Node 꺼냅니다. (FIFO)
			Node node = queue.poll();

			int polledX = node.x;
			int polledY = node.y;

			// 2) 목적지에 도착하면, 해당 노드까지의 거리 반환
			if (polledX == N - 1 && polledY == M - 1)
				return node.distance;

			// 3) 방금 queue에서 꺼낸 노드 기준으로 델타 탐색 (상하좌우)
			for (int d = 0; d < 4; d++) {
				// 새로운 좌표 생성
				int newX = polledX + dx[d];
				int newY = polledY + dy[d];

				// 경계 조건 내에 있다면
				if (newX >= 0 && newY >= 0 && newX < N && newY < M) {

					// 새로운 좌표의 miro 배열 값 = 1 && visited 배열에 아직 방문 한 적 없다면,
					if (miro[newX][newY] == 1 && !visited[newX][newY]) {
						visited[newX][newY] = true; // visited 배열에 방문 기록
						queue.offer(new Node(newX, newY, node.distance + 1)); // Queue에 삽입 (노드 이동거리 + 1)
					}
				}
			}
		}

		return -1; // 목적지에 도착하지 못하는 경우는 사실 문제에서 없다고 주어짐

	}

}
