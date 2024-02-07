// 신승호님 코드 
// - GradeForMain3 클래스 생성
// - 선택 정렬과 조건문으로 하나씩 구현한 것 

package boj_s4_10825_국영수;

import java.util.Scanner;

public class Main3 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		// 배열생성
		GradeForMain3[] arr = new GradeForMain3[T];

		for (int i = 0; i < T; i++) {
			String name = sc.next();
			int Kr = sc.nextInt();
			int Eng = sc.nextInt();
			int math = sc.nextInt();
			arr[i] = new GradeForMain3(name, Kr, Eng, math);

		}

		for (int i = 0; i < T - 1; i++) {
			for (int j = i + 1; j < T; j++) {
				if (arr[i].Kr < arr[j].Kr) {

					GradeForMain3 tem = arr[j];
					arr[j] = arr[i];
					arr[i] = tem;

				} else if (arr[i].Kr == arr[j].Kr) {
					if (arr[i].Eng > arr[j].Eng) {

						GradeForMain3 tem = arr[j];
						arr[j] = arr[i];
						arr[i] = tem;

					} else if (arr[i].Eng == arr[j].Eng) {
						if (arr[i].math < arr[j].math) {

							GradeForMain3 tem = arr[j];
							arr[j] = arr[i];
							arr[i] = tem;

						} else if (arr[i].math == arr[j].math) {
							if (arr[i].name.compareTo(arr[j].name) > 0) {
								GradeForMain3 tem = arr[j];
								arr[j] = arr[i];
								arr[i] = tem;

							}

						}

					}

				}

			}

		}

		for (int i = 0; i < T; i++) {
			System.out.println(arr[i].name);

		}

	}

}