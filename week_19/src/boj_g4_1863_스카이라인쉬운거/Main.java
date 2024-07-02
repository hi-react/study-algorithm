package boj_g4_1863_스카이라인쉬운거;

import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * 주어진 높이 변화를 통해 최소한 몇 개의 직사각형 건물이 필요한 지 찾는 문제
 * 
 * 스택을 활용하여 높이가 변할 때 마다 현재 높이와 비교하여 건물의 시작과 끝을 추적
 * 
 */

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 입력 값 받기
		int n = sc.nextInt(); // 변화 지점 개수
		int[] heights = new int[n];

		for (int i = 0; i < n; i++) {
			sc.nextInt(); // x 좌표는 사용 안하니까 읽기만 하고 끝
			heights[i] = sc.nextInt();
		}

		sc.close();

		System.out.println(countBuildings(heights));

	}

	private static int countBuildings(int[] heights) {

		Stack<Integer> stack = new Stack<>();

		int buildingCount = 0;

		for (int height : heights) {

			// 건물의 끝을 추적 (높이의 감소를 먼저 처리) 
			// 현재 높이(height)보다 높은 높이가 스택에 있다면 제거하면서 건물 하나 끝 
			while (!stack.isEmpty() && stack.peek() > height) {
				stack.pop();
				buildingCount++;
			}

			// 새로운 건물이 시작되는 지점
			// 같은 높이가 연속해서 나타내는 경우, 중복해서 스택에 넣지 않도록 함 
			if (height > 0 && (stack.isEmpty() || stack.peek() != height)) {
				stack.push(height);
			}

		}

		// 스택에 남아 있는 높이들 처리
		while (!stack.isEmpty()) {
			stack.pop();
			buildingCount++;
		}

		return buildingCount;
	}

}
