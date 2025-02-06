package boj_g4_2295_세수의합;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        // 오름차순 정렬
        Arrays.sort(nums);

        // 두 수의 합 저장해 놓을 집합 (중복 X)
        Set<Integer> sumOfTwo = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                sumOfTwo.add(nums[i] + nums[j]);
            }
        }

        // 가장 큰 K 찾기 (뒤에서 부터 시작)
        int maxK = 0;
        for (int k = N - 1; k >= 0; k--) {
            for (int i = 0; i < N - 1; i++) {
                int target = nums[k] - nums[i];
                if (sumOfTwo.contains(target)) {
                    maxK = nums[k];
                    break; // 내부 루프 종료
                }
            }
            if (maxK > 0) break; // K 찾았으면 전체 루프 종료
        }

        br.close();
        bw.write(maxK + "\n");
        bw.flush();
        bw.close();

    }
}
