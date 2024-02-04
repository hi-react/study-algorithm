package practice_selection_sort;

import java.util.Arrays;

public class Solution {

	public static void main(String[] args) {

		int[] arr = { 32, 55, 27, 94, 75, 69 };
		System.out.println(Arrays.toString(arr));
		System.out.println();

		selectionSort(arr);
		System.out.println("선택 정렬 결과: " + Arrays.toString(arr));

	}

	private static void selectionSort(int[] arr) {

		// 제일 앞에서 부터 최소값 찾아서 하나씩 채워넣기
		for (int i = 0; i < arr.length - 1; i++) {

			// 0번째 인덱스 부터 최소 값을 찾을 것임!
			int minIndex = i;

			// 일단, 최소 값 찾기
			for (int j = minIndex + 1; j < arr.length - 1; j++) {
				if (arr[minIndex] > arr[j])
					minIndex = j;
			}

			// 0번째 인덱스부터 순서대로 최소 값이랑 위치 swap 해주기
			int tmp = arr[i]; // 임시 변수에 값 저장
			arr[i] = arr[minIndex];
			arr[minIndex] = tmp;

		}

	}

}
