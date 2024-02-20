// Scanner 시간초과 -> BufferedReader로 변경한 코드 

package boj_s2_18111_마인크래프트;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws IOException {

		// 백준에서는 다시 FileReader -> InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(new FileReader("src/boj_s2_18111_마인크래프트/input.txt"));

		// 입력 값 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		// N * M 배열 만들고, 땅 높이 넣어주기
		int[][] minecraft = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				minecraft[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		br.close();

		// 반환해 줄 변수 = 최대 땅 높이(maxHeight), 최소 시간(minTime)
		int maxHeight = 0; // 최소 0
		int minTime = Integer.MAX_VALUE;

		// 땅 높이가 0 일 때부터 ~ 256 일 때 까지 완전 탐색
		for (int h = 0; h <= 256; h++) {

			int time = 0; // 시간
			int height = h; // 땅 높이
			int inventory = B; // 인벤토리

			// N * M 배열의 집터 순환
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					// 땅 고르기 완료 후의 땅 높이(height) vs 현재의 땅 높이 차이 구하기
					int diff = height - minecraft[i][j];

					// 차이(diff) = 0이면 아무런 작업할 필요 없으므로 continue;
					if (diff == 0)
						continue;

					// 차이(diff) > 0 -> 블럭 추가 작업(1초) 필요
					if (diff > 0) {
						time += diff;
						inventory -= diff;
					}
					// 차이(diff) < 0 -> 블럭 제거 작업(2초) 필요
					else if (diff < 0) {
						time += (2 * Math.abs(diff));
						inventory += Math.abs(diff);
					}

				}
			}

			// 집터 순환 완료, 땅 고르기를 마쳤는데, inventory < 0라면, 추가할 수 없는데 추가한 경우임.
			// 따라서 minTime, maxHeight 업데이트 하지 않고 탐색 continue
			if (inventory < 0) {
				continue;
			}

			// inventory >= 0 이면,
			else {
				// 최소 시간일 경우 -> minTime, maxHeight 업데이트
				if (time <= minTime) {
					minTime = time;
					if (height > maxHeight)
						maxHeight = height;
				}
			}

		}

		// 출력
		System.out.println(minTime + " " + maxHeight);

	}

}
