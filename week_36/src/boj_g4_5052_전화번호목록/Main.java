package boj_g4_5052_전화번호목록;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            String[] phoneNumbers = new String[N];

            for (int j = 0 ; j < N; j++) {
                phoneNumbers[j] = br.readLine();
            }

            Arrays.sort(phoneNumbers);

            // 정렬 결과 확인 로그
//            System.out.println("정렬된 전화번호 목록:");
//            for (String number : phoneNumbers) {
//                System.out.println(number);
//            }

            boolean isConsistent = true;

            for (int j = 0; j < N-1; j++) {
                if (phoneNumbers[j+1].startsWith(phoneNumbers[j]) ) {
                    isConsistent = false;
                    break;
                }
            }

            if (isConsistent) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }


        }


        br.close();

    }

}
