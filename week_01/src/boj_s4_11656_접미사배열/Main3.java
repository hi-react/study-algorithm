package boj_s4_11656_접미사배열;

import java.util.Scanner;

// custom method 생성 후 이용 
public class Main3 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 문자열 입력 받기
		String str = sc.next();

		// 문자열 길이 변수에 담아두기
		int n = str.length();

		// 문자열 배열 넣을 수 있게 생성
		String[] strArr = new String[n];
		// 문자열 길이만큼 회전하면서
		for (int i = 0; i < n; i++) {
			strArr[i] = str.substring(i, n);
		}

		sc.close();

		// 선택 정렬(Selection Sort)
		// - compareTo 메소드: Java String 클래스의 일부, 두 문자열을 사전순으로 비교
		// - 비교는 문자열에 있는 각 문자의 유니코드 값을 기반으로 합니다.
		for (int i = 0; i < n - 1; i++) {

			int minIndex = i; // 앞에서 부터 사전 순으로 가장 작은 값을 하나씩 찾아갈 것임

			for (int j = i + 1; j < n; j++) {

				// 확인 중인 j 번째의 문자열이 더 작다면(true), minIndex 교체 !
				if (isLessThan(strArr[j], strArr[minIndex])) { // 작다면 사전 순으로 앞 순위
					minIndex = j; // minIndex (사전 순으로 가장 앞에 와야할 인덱스) 대체
				}
			}
			String tmp = strArr[minIndex]; // 임시 변수에 저장하고, 둘의 위치 바꿔줍니다.
			strArr[minIndex] = strArr[i];
			strArr[i] = tmp;
		}

		// 출력 확인
		for (int i = 0; i < n; i++) {
			System.out.println(strArr[i]);
		}

	}

	// 문자열 비교 메서드 구현
	private static boolean isLessThan(String a, String b) {
		int minLength = Math.min(a.length(), b.length()); // 더 작은 길이 찾아서

		// 그 길이만큼 순회할 거다.
		for (int i = 0; i < minLength; i++) {
			// 두 문자열의 같은 인덱스에 있는 값끼리 비교해서 다르다면,
			if (a.charAt(i) != b.charAt(i)) {
				return a.charAt(i) < b.charAt(i); // a 문자열이 사전 순으로 더 작으면 true, 더 크면 false
			}

		}
		// 비교 가능한 모든 문자가 동일하다면, 더 짧은 쪽이 사전 순으로 앞 !!!
		return a.length() < b.length(); // a가 길이 더 짧으면(사전 순으로 앞) true, 더 길면 false
	}

}
