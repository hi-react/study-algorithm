// 완전 탐색 마냥 푸니까, 시간 초과 뜸 

package boj_s2_2805_나무자르기;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("src/boj_s2_2805_나무자르기/input.txt"));

		int N = sc.nextInt(); // 나무의 수
		int M = sc.nextInt(); // 필요한 나무의 길이

		// N개의 나무 높이 배열 만들 거임
		int[] tree = new int[N];
		for (int i = 0; i < N; i++) {
			tree[i] = sc.nextInt();
		}

		// 변수 선언
		int maxHeight = 0; // 반환 해 줄 절단기 높이의 최댓값 변수
		int treeGet = 0; // 얻어낸 나무 길이 세어줄 변수 초기화

		// tree 배열 돌면서 가장 높은 나무 값을 maxHeight 초기 값으로 설정
		for (int i = 0; i < N; i++) {
			if (tree[i] > maxHeight)
				maxHeight = tree[i];
		}

		// maxHeight부터 시작해서 하나씩 내려가면서, 필요한 나무의 길이(M)를 얻을 때 까지 반복
		while (treeGet < M) {

			int height = maxHeight; // maxHeight부터 절단기 높이 시작해서 점점 내릴 거임.
			treeGet = 0; // 얻어 낸 나무 수 초기화

			// tree 배열 돌면서
			for (int i = 0; i < N; i++) {
				// 나무의 높이가 > 현재 지정해 둔 절단기 높이보다 높으면,
				if (tree[i] > height) {
					treeGet += (tree[i] - height); // 해당 만큼의 나무를 얻을 수 있지.
				}
			}

			// 한 바퀴 돌았으면 maxHeight 줄여준다.
			maxHeight--;

		}

		// 마지막에 maxHeight 줄이고 나왔으니까 다시 +1
		System.out.println(maxHeight + 1);

		sc.close();
	}

}
