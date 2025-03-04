package boj_g5_2866_문자열잘라내기;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] table  = new char[R][C];

        for (int i = 0; i < R; i++) {
            table[i] = br.readLine().toCharArray();
        }

        int left = 0, right = R - 1;
        int count = 0; // 삭제할 수 있는 최대 행 개수

        while (left <= right) {
            int mid = (left + right) / 2;

            Set<String> allString = new HashSet<>(); // mid행부터 고려했을 때 전체 세로 문자열
            boolean isUnique = true; // 중복 체크용

            for (int col = 0; col < C; col++) {
                // 세로 문자열 만들고
                StringBuilder sb = new StringBuilder();
                for (int row = mid; row < R; row++) {
                    sb.append(table[row][col]);
                }
                String colString = sb.toString();

                // 동일한 세로 문자열 발견 시
                if (allString.contains(colString)) {
                    isUnique = false;
                    break;
                }

                allString.add(colString);
            }

            if (isUnique) { // 중복 없으면, 더 많은 행 삭제 가능한 지 확인
                count = mid;
                left = mid + 1;
            } else { // 중복 있으면, 삭제할 행 개수 줄이기 위해 right 줄이기
                right = mid - 1;
            }

        }

        System.out.println(count);
    }
}
