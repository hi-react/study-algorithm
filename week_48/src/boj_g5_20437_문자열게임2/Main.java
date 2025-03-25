package boj_g5_20437_문자열게임2;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String W = br.readLine(); // 문자열
            int K = Integer.parseInt(br.readLine()); // K개

            Map<Character, List<Integer>> dict = new HashMap<>();

            // 각 문자 별로 인덱스 저장
            for (int i = 0; i < W.length(); i++) {
                char c = W.charAt(i);
                if (!dict.containsKey(c)) {
                    dict.put(c, new ArrayList<>());
                }
                dict.get(c).add(i);
            }

            int MIN_LEN = Integer.MAX_VALUE;
            int MAX_LEN = -1;

            // 문자 별 인덱스 배열 하나씩 확인
            for(List<Integer> indexes : dict.values()) {
                // 연속된 K개 없으면 패스
                if (indexes.size() < K) continue;

                for (int i = 0; i <= indexes.size() - K; i++) {
                    int length = indexes.get(i + K - 1) - indexes.get(i) + 1;
                    MIN_LEN = Math.min(MIN_LEN, length);
                    MAX_LEN = Math.max(MAX_LEN, length);
                }
            }

            if (MAX_LEN == -1) { // 연속된 K개 문자열 없는 경우
                bw.write(-1 + "\n");
            } else {
                bw.write(MIN_LEN + " " + MAX_LEN + "\n");
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
