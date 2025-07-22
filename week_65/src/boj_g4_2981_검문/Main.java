package boj_g4_2981_검문;

import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> divisors = new ArrayList<>();
    static int gcd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        Arrays.sort(nums); // 5 14 17 23 83

        // 차이 계산 => 그들의 최대공약수(GCD)
        gcd = nums[1] - nums[0];
        for (int i = 2; i < N; i++) {
            gcd = findGCD(gcd, nums[i] - nums[i-1]); // 9 3 6 60
        }

        findDivisors(); // 최대공약수(GCD)의 약수 찾기

        Collections.sort(divisors);

        for (int d : divisors) {
            bw.write(d + " ");
        }
        bw.flush();
        bw.close();
    }

    static void findDivisors() {
        divisors.add(gcd);
        for(int i = 2; i <= Math.sqrt(gcd); i++) {
            if (gcd % i == 0) {
                divisors.add(i); // 해당 약수
                if (gcd / i != i) {
                    divisors.add(gcd / i); // 짝 약수
                }
            }
        }
    }

    static int findGCD(int a, int b) {
        int min = Math.min(a, b);
        for (int i =  min; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                return i;
            }
        }
        return 1;
    }
}
