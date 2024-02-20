// 이진 탐색으로 풀어 보자 

package boj_s2_2805_나무자르기;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("src/boj_s2_2805_나무자르기/input.txt"));

		int N = sc.nextInt(); // 나무의 수
		int M = sc.nextInt(); // 필요한 나무의 길이

		// N개의 나무 높이 배열 만들 거임
		int[] tree = new int[N];
		for (int i = 0; i < N; i++) {
			tree[i] = sc.nextInt();
		}

		sc.close();

		// 변수 선언
		int minHeight = 0; // [이진 탐색] 절단기 높이의 최소 값
		int maxHeight = 1000000000; // [이진 탐색] 문제에서 주어진 절단기 높이의 최대 값
		int ans = 0; // 반환해 줄 최적의 절단기 높이!

		// 이진 탐색 ㄱ
		while (minHeight <= maxHeight) {

			// 일단 기준 값 정해서 이것부터 절단기 높이라고 쳐보자.
			int mid = (minHeight + maxHeight) / 2;
			long treeGet = 0; // 얻어낸 나무 길이 세어줄 변수 초기화

			// tree 배열 돌면서
			for (int i = 0; i < N; i++) {
				// 나무의 높이가 > 현재 지정해 둔 절단기 높이(mid)보다 높으면,
				if (tree[i] > mid) {
					treeGet += (tree[i] - mid); // 해당 만큼의 나무를 얻을 수 있지.
				}
			}

			// [비교] 얻어 낸 나무의 수 vs 필요한 나무의 길이

			// 더 많이 필요하다면, 절단기 높이를 낮춰야 함.
			if (treeGet < M)
				maxHeight = mid - 1;
			// 충분히 얻어 냈다면,
			else {
				minHeight = mid + 1; // 절단기 높이를 높이고
				ans = mid; // 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값
			}

		}

		// 출력 
		System.out.println(ans);

	}

}
