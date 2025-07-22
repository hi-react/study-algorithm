package boj_g3_16934_게임닉네임;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 사람 수

        Set<String> usedPrefixes = new HashSet<>(); // 사용된 접두사 저장
        Map<String, Integer> nameCount = new HashMap<>(); // 동일한 닉네임 개수 저장

        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            boolean aliasMade = false;

            // 1. 닉네임 탐색 : 가장 짧은 새로운 접두사 출력
            for (int j = 1; j <= name.length(); j++) {
                String prefix = name.substring(0, j);
                if (!usedPrefixes.contains(prefix)) {
                    bw.write(prefix + "\n");
                    aliasMade = true;
                    break;
                }
            }

            // 2. 동일 닉네임 : 닉네임 or 닉네임+카운트
            int count = nameCount.getOrDefault(name, 0) + 1;
            nameCount.put(name, count); // 동일 닉네임 수 갱신
            if (!aliasMade) {
                bw.write(count == 1 ? name + "\n" : name + count + "\n");
            }

            // 3. 사용된 모든 접두사 추가
            for (int j = 1; j <= name.length(); j++) {
                usedPrefixes.add(name.substring(0, j));
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
