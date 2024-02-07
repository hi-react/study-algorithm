package boj_s4_10825_국영수;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("src/boj_s4_10825_국영수/input.txt");
		Scanner sc = new Scanner(file);

		int N = sc.nextInt();
		sc.nextLine(); // 개행 문자 지워준다.

		// 학생들의 성적을 담아 줄 배열을 선언 하고
		String[][] score = new String[N][4];

		// 값을 읽어와서 담아준다.
		for (int i = 0; i < N; i++) {
			String line = sc.nextLine();
			score[i] = line.split(" ");
		}

		// 이제 score 배열 정렬 해줄 건데, 한 줄씩을 하나의 String 배열로 비교
		// 국어(내림차순) -> 영어(오름차순) -> 수학(내림차순) -> 이름(오름차순)
		Arrays.sort(score, new Comparator<String[]>() {

			/**
			 * Comparator 이용해서 score 배열을 정렬할 것임. - util 패키지에 존재하므로, import해서 사용 - String의
			 * 배열을 비교.
			 * 
			 */
			@Override
			public int compare(String[] s1, String[] s2) { // 문자열의 배열을 비교할 건데,

				// 기본적으로는 국어 점수(1번 인덱스) 내림차순 정렬
				int cmp = Integer.compare(Integer.parseInt(s2[1]), Integer.parseInt(s1[1])); // s2가 더 크면 위치 교환 (내림차순)

				// 국어 점수가 같으면, 영어 점수(2번 인덱스) 오름차순 정렬
				if (cmp == 0) {
					cmp = Integer.compare(Integer.parseInt(s1[2]), Integer.parseInt(s2[2])); // s1이 더 크면 위치 교환 (오름차순)

					// 영어 점수도 같으면, 수학 점수(3번 인덱스) 내림차순 정렬
					if (cmp == 0) {
						cmp = Integer.compare(Integer.parseInt(s2[3]), Integer.parseInt(s1[3])); // s2가 더 크면 위치 교환
																									// (내림차순)

						// 모든 성적이 같으면, 이름 오름차순(0번 인덱스) 정렬
						if (cmp == 0) {
							cmp = s1[0].compareTo(s2[0]); // compareTo는 lan 패키지에 존재하는 Comparable -> s1이 더 크면 위치 바꿔줌
															// (오름차순)
						}
					}
				}

				return cmp;
			}
		});

		// 정렬된 배열 출력
		for (String[] s : score) {
			System.out.println(s[0]);
		}

		sc.close();

	}

}
