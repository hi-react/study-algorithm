package boj_g4_14226_이모티콘;

import java.io.*;
import java.util.*;

public class Main {
    static class State {
        // 현재 화면 수, 클립 보드 수, 걸린 시간
        int screen, clip, time;

        State(int screen, int clip, int time) {
            this.screen = screen;
            this.clip = clip;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt();
        sc.close();

        Queue<State> q = new LinkedList<>();
        boolean[][] visited = new boolean[1001][1001];

        q.offer(new State(1, 0, 0));
        visited[1][0] = true;

        while(!q.isEmpty()) {
            State cur = q.poll();

            if (cur.screen == S) {
                System.out.println(cur.time);
                break;
            }

            // 1. 복사
            if (!visited[cur.screen][cur.screen]) {
                visited[cur.screen][cur.screen] = true;
                q.offer(new State(cur.screen, cur.screen, cur.time + 1));
            }

            // 2. 붙여 넣기
            int nextScreen = cur.screen + cur.clip;
            if (cur.clip > 0 && nextScreen <= 1000 && !visited[nextScreen][cur.clip]) {
                visited[nextScreen][cur.clip] = true;
                q.offer(new State(nextScreen, cur.clip, cur.time + 1));
            }

            // 3. 삭제
            if (cur.screen > 1 && !visited[cur.screen - 1][cur.clip]) {
                visited[cur.screen - 1][cur.clip] = true;
                q.offer(new State(cur.screen - 1, cur.clip, cur.time + 1));
            }

        }


    }
}
