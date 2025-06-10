package boj_g3_13625_Boss;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, I;
    static int[] ages;
    static List<Integer>[] hierarchy, revHierarchy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 직원 수
        M = Integer.parseInt(st.nextToken()); // 관계 간선
        I = Integer.parseInt(st.nextToken()); // 명령 수

        ages = new int[N + 1]; // 직원 나이
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            ages[i] = Integer.parseInt(st.nextToken());
        }

        hierarchy = new ArrayList[N + 1]; // 상사 - 부하 직속 리스트
        revHierarchy = new ArrayList[N + 1]; // 부하 - 상사 직속 리스트
        for (int i = 1; i <= N; i++) {
            hierarchy[i] = new ArrayList<>();
            revHierarchy[i] = new ArrayList<>();
        }
        // 관계 그래프 간선 연결
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken()); // 상사
            int Y = Integer.parseInt(st.nextToken()); // 부하
            hierarchy[X].add(Y);
            revHierarchy[Y].add(X);
        }

        for (int i = 0; i < I; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("T")) {
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                swap(A, B);
            } else if (cmd.equals("P")) {
                int E = Integer.parseInt(st.nextToken());
                int result = findYoungestBoss(E);
                System.out.println(result == Integer.MAX_VALUE ? "*" : result);
            }
        }
    }

    static void swap(int a, int b) {
        // 1. hierarchy (상사 - 부하 직속 리스트 수정)
        List<Integer> tmp1 = hierarchy[a];
        hierarchy[a] = hierarchy[b];
        hierarchy[b] = tmp1;

        // 부하들에게 새 상사 반영
        for (int sub : hierarchy[a]) {
            revHierarchy[sub].remove(Integer.valueOf(b)); // 옛날 상사 제거
            revHierarchy[sub].add(a); // 새 상사 등록
        }
        for (int sub : hierarchy[b]) {
            revHierarchy[sub].remove(Integer.valueOf(a));
            revHierarchy[sub].add(b);
        }

        // 2. revHierarchy (부하 - 상사 직속 리스트 수정)
        List<Integer> tmp2 = revHierarchy[a];
        revHierarchy[a] = revHierarchy[b];
        revHierarchy[b] = tmp2;

        // 상사들에게 새 부하 반영
        for (int boss : revHierarchy[a]) {
            hierarchy[boss].remove(Integer.valueOf(b)); // 옛날 부하 제거
            hierarchy[boss].add(a); // 새 부하 등록
        }
        for (int boss : revHierarchy[b]) {
            hierarchy[boss].remove(Integer.valueOf(a));
            hierarchy[boss].add(b);
        }
    }

    static int findYoungestBoss(int e) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        visited[e] = true;
        q.offer(e);
        int minAge = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int boss : revHierarchy[curr]) {
                if (!visited[boss]) {
                    visited[boss] = true;
                    minAge = Math.min(minAge, ages[boss]);
                    q.offer(boss);
                }
            }
        }

        return minAge;
    }
}
