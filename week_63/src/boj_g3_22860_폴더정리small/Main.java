package boj_g3_22860_폴더정리small;

import java.io.*;
import java.util.*;

public class Main {
    static class Folder {
        Map<String, Folder> subFolders = new HashMap<>();
        List<String> files = new ArrayList<>();
    }
    static Folder root = new Folder();
    static Map<String, Folder> folderMap = new HashMap<>(); // 폴더 계층 관리
    static List<String[]> info = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 폴더 개수
        int M = Integer.parseInt(st.nextToken()); // 파일 개수

        folderMap.put("main", root); // 최상위 폴더 세팅

        // 입력 값 => 객체만 미리 생성
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            String P = st.nextToken(); // 상위 폴더 명
            String C = st.nextToken(); // 하위 폴더 or 파일 명
            String isFolder = st.nextToken();

            info.add(new String[]{P, C, isFolder});

            folderMap.putIfAbsent(P, new Folder());
            if (isFolder.equals("1")) {
                folderMap.putIfAbsent(C, new Folder());
            }
        }

        // 계층 구조 연결
        for (String[] connection : info) {
            String P = connection[0];
            String C = connection[1];
            String isFolder = connection[2];

            Folder parent = folderMap.get(P);

            if (isFolder.equals("1")) { // 폴더
                Folder child = folderMap.get(C);
                parent.subFolders.put(C, child);
            } else { // 파일
                parent.files.add(C);
            }
        }

        int Q = Integer.parseInt(br.readLine());

        for(int i = 0; i < Q; i++) {
            // 계층 구조에서 쿼리 대상 폴더 찾기
            String path = br.readLine();
            Folder target = getFolderByPath(path);

            // 파일 종류 개수, 파일 총 개수 출력
            Set<String> fileTypes = new HashSet<>();
            int fileCount = findFiles(target, fileTypes);

            bw.write(fileTypes.size() + " " + fileCount + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static Folder getFolderByPath(String path) {
        String[] folders = path.split("/");
        Folder cur = root;
        for (int i = 1; i < folders.length; i++) {
            cur = cur.subFolders.get(folders[i]);
        }
        return cur;
    }

    static int findFiles(Folder folder, Set<String> fileTypes) {
        int count =  0;

        // 파일 추가
        for (String file : folder.files) {
            fileTypes.add(file);
            count++;
        }

        // 내부 폴더 돌면서 파일 추가
        for (Folder subFolder : folder.subFolders.values()) {
            count += findFiles(subFolder, fileTypes);
        }

        return count;
    }
}
