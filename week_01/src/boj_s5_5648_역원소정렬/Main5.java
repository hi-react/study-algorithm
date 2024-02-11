// 장우님 코드 

package boj_s5_5648_역원소정렬;

import java.util.Arrays;
import java.util.Scanner;

public class Main5 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		int leng = sc.nextInt();

		String[] nums = new String[leng];

		Long[] sols = new Long[leng];
		// 배열 입력

		for (int i = 0; i < nums.length; i++) {
			nums[i] = sc.next();
		}

		// 0번쨰 숫자부터
		for (int i = 0; i < nums.length; i++) {
			// 새로운 배열 생성
			char[] numsChar = nums[i].toCharArray();
			char[] numsCopy = numsChar.clone();

			// reverse
			for (int j = 0, k = numsChar.length - 1; j < numsChar.length; j++, k--) {
				numsCopy[j] = numsChar[k];
			}

			String reverseNum = String.valueOf(numsCopy);

			long solNum = Long.parseLong(reverseNum);
			sols[i] = solNum;

		}

		// reverse 정렬 필요
		Arrays.sort(sols);

		for (Long i : sols) {
			System.out.println(i);
		}
	}
}