package boj_s2_1541_잃어버린괄호;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 연산 String 받기
		String str = sc.next();
		sc.close();
		
		// 0. 반환 할 최종 변수
		int ans = 0; 

		// 1. '-' 기호를 기준으로 나누어 배열에 담는다.
		String[] parts = str.split("-");
		int n = parts.length;

		// 2. 각 배열의 연산을 수행한 결과 값 담아 줄 배열 생성
		int[] results = new int[n];
		
		// 3. 각 배열의 연산을 수행하여 results 배열에 넣어준다. 
		for (int i = 0; i < n; i++) {
			
			// '+' 기호가 있는 경우
			if (parts[i].contains("+")) {
				String[] nums = parts[i].split("\\+");
				for (String num : nums) {
					results[i] += Integer.parseInt(num);
				}
			} else { // '+' 기호 없으면 해당 숫자 그냥 넣어주면 된다. 
				results[i] = Integer.parseInt(parts[i]);
			}
		}
		
		// 4. results 배열을 돌면서 계산
		ans += results[0];
		for(int i=1; i<n; i++) {
			ans -= results[i];
		}

		System.out.println(ans);

	}

}
