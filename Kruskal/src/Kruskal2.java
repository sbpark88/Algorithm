// 기존 1번 코드 원본에서 불필요하게 반복되는 find 함수의 실행을 줄임.

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Kruskal2 {
    static int V, E;                    // V : 점, E : 변
    static ArrayList<Edge> mst;         // result값 Minimum spanning tree
    static int[] arr ;                  // 각 노드의 부모가 누구인지를 저장한다. ex) arr[0] = 1 이면 0의 부모(root node)는 1이다.
    static PriorityQueue<Edge> pq;      // 모든 노드(점)을 힙에 넣고 시작.

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        long startTime = System.nanoTime();

        // Vertex : 전체 점의 개수를 입력 받는다. (샘플에서 6에 해당)
        V = sc.nextInt();

        // Edge : 전체 변의 개수를 입력 받는다. (샘플에서 9에 해당)
        E = sc.nextInt();

        mst = new ArrayList<>();
        arr = new int[V+1]; // array는 인덱스가 0부터 시작하는데 문제의 점이 1부터 6까지라 인덱스 0번을 제외하고 1~6을 쓰기 위헤 V+1개의 배열 생성.
        // Queue는 기본적으로 FIFO 구조를 갖는다. Priority queue는  '최대 우선순위 큐'와 '최소 우선순위 큐'로 나뉜다.
        pq = new PriorityQueue<>();

        // vertex의 개수 + 1개 만큼 반복. (i = 1부터 시작하면 안 된다. 0번을 안 쓰더라도 배열은 반드시 0번부터 채워야한다. 비워둘 수 없다.)
        for (int i = 0; i <= V; i++) {
            // 각 vertex는 시작할 때는 자기 자신을 root로 초기화.
            arr[i] = i;
        }

        // 각 노드의 연결 관계와 비용을 입력 받는다. (샘플에서 1 2 5...  1 3 4 에 해당)
        for (int i = 0; i < E; i++) {
            int v1 = sc.nextInt();      // 1번 노드와...  1번 노드와
            int v2 = sc.nextInt();      // 2번 노드의...  3번 노드의
            int value = sc.nextInt();   // 비용은 5다...  비용은 4다

            // 우선순위 큐에 Edge라는 객체를 하나씩 추가한다. Edge는 변으로, 자기 자신의 좌우의 노드와 비용에 대한 정보를 갖는다.
            pq.add(new Edge(v1, v2, value));
        }

        // V개의 노드를 연결하는 최소 Edge의 수는 '노드-1'이므로 mst.size()가 'V-1'이 될 때까지 반복한다.
        // 즉, mst.size()가 'V-2'개일 때 반복하다, 마지막 하나를 더 찾아 'V-1'개가 되면 반복문 조건이 false가 된다.
        while(mst.size() < (V-1)) {
            // PQ에 하나씩 넣었고, 이제 하나씩 뺀다. PQ로 넣어서 FIFO이 아닌 오름차순으로 나온다.(value 기준)
            Edge edge = pq.poll();
            // Edge의 양 끝 각 노드(start, end라 명명함)의 최종 부모(root)를 구한다.
            int parentEdgeStart = find(edge.start);
            int parentEdgeEnd = find(edge.end);

//            int parentEdgeStart = -1;
//            int parentEdgeEnd = -1;
//            // 기본 변수 타입인 int는 null을 받아들일 수 없다. edge 객체의 int 변수를 Integer로 바꿨다.
//            // 의미가 있는지는 모르겠음. 그냥 위에 바로 int에 find 돌려서 넣고 edge 인스턴스 변수는 그대로 int로 해도 될 것 같은데...
//            if (edge.start != null && edge.end != null) {
//                parentEdgeStart = find(edge.start);
//                parentEdgeEnd = find(edge.end);
//            }

            // Edge(변)의 앞뒤 각각 노드의 최종 부모가 서로 같지 않다면(= 서로 다른 그룹이라면 혹은 아직 그룹이 아니라면) 실행. (Edge의 좌우 노드의 최종 부모가 같다면 이미 같은 그룹이니 해당 엣지는 로직을 돌지 않고 버린다.)
            if(parentEdgeStart != parentEdgeEnd)
            {
                // 같은 그룹이 아니라면 MST 로직을 만족(True)하니까 mst ArrayList에 edge 객체를 추가한다.
                mst.add(edge);
                // MST에 추가한 후 이 Edge가 속한 그룹(Union)이 새 그룹이든, 기존 그룹에 결합된 그룹이든간에 Edge 객체의 좌우 노드의 최종 부모(root)를 갱신해준다.
                union(parentEdgeStart, parentEdgeEnd);
            }
        }
        System.out.println(mst);
        long endTime = System.nanoTime(); // (종료점)
        long timeInterval = (endTime - startTime) / 1000000; // (시간차)
        System.out.printf("Code Time : %d (ms) \n", timeInterval);
    }

    static int find(int n) {            // 입력 받은 노드의 최종 부모를 구한다.
        if (n == arr[n]) {              // 자기 자신이 root면 자기 자신을 반환. root가 바뀌어 자기 자신이 root가 아니면 자신의 부모 노드가 root인지를 재귀를 통해 확인. 이 경우 아래 else문을 탄다.
            return n;
        } else {
            int p = find(arr[n]);       // 각 노드의 부모를 찾는 함수를 재귀로 호출한다. 자기 자신이 root가 되어 if문을 탈 때까지 재귀를 반복한다. 재귀를 돌 때 arr[n]을 넣어 자기의 부모를 넣는다.
                                        // 그러면 그 부모는 또 자기의 부모를 찾는다. 그리고 더이상 부모가 없고 자기 자신이 root면 return을 한다. // ex) arr[0] = 1, arr[1] = 5, arr[5] = 5 자기 자신인 최종 root가 나오면 3번 호출되어 if문을 타고 return된다.
            arr[n]=p;                   // path compression : 코드 최적화. 불필요한 재귀 호출을 줄이기 위해 재귀를 들어가 return 되어 나올 때 각 노드의 최종 부모(root)를 가리키도록 저장한다.
                                        // 즉, arr[0] = 3, arr[1] = 3, arr[5] = 3. 그러면 다음번에는 1+1번만 호출되어 return된다.
            return p;                   // 최종 부모(root)를 return.
        }
    }

    // Union 로직 함수. 노드1과 노드2의 최종 부모가 다르다면 같은 그룹이 아니므로 하나의 그룹으로 결합한다. 이 때 root는 뒤쪽 노드가 된다.
    static void union(int p1, int p2) {
        if (p1 != p2) {
            arr[p1]= p2;
        }
    }

    // Edge 객체를 클래스로 정의. 2개의 노드와 1개의 비용을 변수로 갖는다.
    static class Edge implements Comparable<Edge>{
        int start, end, value;
//        Integer start, end, value;

        Edge(int s, int e, int v) {
            this.start = s;
            this.end = e;
            this.value = v;
        }
        @Override
        public int compareTo(Edge o) {
            return this.value - o.value;
        }
        @Override
        public String toString() {
            return "Edge [start=" + start + ", end=" + end + ", value=" + value + "]\n";
        }
    }
}

/*
6
9
1 2 5
1 3 4
2 3 2
2 4 7
3 4 6
4 6 8
3 5 11
4 5 3
5 6 8
*/
// source : https://velog.io/@dudrkdl777/Graph-최소신장트리-MSTKruskal알고리즘