import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Prim {
    static int V, E;                        // V : 점, E : 변
    static ArrayList<Edge> mst;             // result값 Minimum spanning tree
    static LinkedList<Edge>[] graph;        // Kruskal에서는 각 노드를 PriorityQueue<Edge>에 넣었다면, 여기서 graph는 해당 노드가 start인 모든 엣지를 넣는다. 즉, 3번 노드와 연결된 엣지가 5개면 3번 graph[3] start가 3인 엣지를 5개 가지고 있는 배열이 된다.
    static boolean[] visit;                 // 노드의 방문 여부 (Kruskal에서는 int[] arr에 각 노드의 부모가 누구인지를 저장했다.)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();

        // 각 노드를 graph로 정의한다. 배열을 V+1개로 만들면 인덱스가 0 ~ V까지 배정되고, 0을 버리고 1 ~ V번 인덱스를 사용한다.
        graph = new LinkedList[V+1];
        // 각 노드의 방문 여부를 boolean 타입의 일차운 단순 배열로 만든다.
        visit =  new boolean[V+1];

        // 1번 노드부터 V번 노드까지 LinkedList<Edge>[] 타입의 graph 변수에 넣는다. (1 ~ 6번 노드가 샘플로 주어진다.)
        for (int i = 1; i <= V; i++) {
            graph[i]= new LinkedList<>();
        }

        mst = new ArrayList<>();

        // 각 노드의 연결 관계와 비용을 입력 받는다. (샘플에서 1 2 5...  1 3 4 에 해당. 엣지가 총 9개 주어졌으니 9번 돈다.)
        for (int i = 0; i < E; i++) {
            int v1 = sc.nextInt();      // 1번 노드와...  1번 노드와
            int v2 = sc.nextInt();      // 2번 노드의...  3번 노드의
            int value = sc.nextInt();   // 비용은 5다...  비용은 4다

            // 노드1에서 노드2로 가는 방향으로 저장.
            graph[v1].add(new Edge(v1,v2,value));
            // 노드2에서 노드1로 가는 방향으로 저장.
            graph[v2].add(new Edge(v2,v1,value));

            // 왜 양방향으로 저장할까?
            // 노드를 기준으로 뻗어 나가는 엣지를 정의하고 그 중 비용이 작은 것을 택하는 것이기 때문에 각 노드에서 뻗어나가는 모든 엣지를 합하면 결국 양방향이 되고 전체 엣지의 2배가 된다.
        }

        // Kruskal에서 while(mst.size() < (V-1)) { }에 해당. 메인 메소드에 다 넣지 않고 빼놓은 것 뿐이다.
        makeMst();
        System.out.println(mst);

    }
    static void makeMst() {
        // 1. 시작 노드는 아무거나 지정.
        // 2. 선택한 노드에 연결된 엣지들을 PrioityQueue에 넣는다. (비용을 기준으로 오름차순 정렬됨.)
        // 3. 비용이 최소인 Edge 객체를 poll()한다. 이 객체는 [start, end, value]를 갖고 있고, end를 visit 검사를 통해 방문 여부를 확인한다.
        // 4. 2~3 반복. n-1개의 간선이 선택할때까지 or 모든 정점이 연결될때까지

        // 노드를 기준으로 연결된 엣지를 담을 PriorityQueue를 하나 인스턴스화 한다. 그러면 해당 노드에 연결된 엣지들을 비용을 기준으로 오름차순 정렬한다.
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // nowV에 현재 방문중인 노드를 넣어주고, 더이상 방문중인 노드가 없다면 while문을 돌려주기 위해 temp로 사용할 변수로써 큐를 인스턴스화 한다.
        // visit은 각 노드의 방문 여부를 true, false로 저장하는 boolean 타입의 배열로, temp처럼 사용되는게 아닌 전역변수이기 때문에 queue와는 쓰임새가 다르다.
        Queue<Integer> queue = new LinkedList<>();
        // 1번 노드에서 시작하겠다.
        queue.add(1);

        while(!queue.isEmpty()) {
            // 현재 노드
            int nowV = queue.poll();
            // 현재 노드는 방문 했으니 true로 바꾼다.
            visit[nowV] = true;

            // LinkedList<Edge>[] graph에 모든 노드를 담고 시작했다. nowV에 해당하는 노드의 엣지를 하나씩 꺼낸다.
            // 즉, 1번 노드에 엣지가 3개면 각 엣지로 for문을 3번 돌거고, 5번 노드에 엣지가 1개면 for문을 1번 돌고 그런 식이다.
            // 그리고 이 엣지는 nowV라는 노드가 볼 때, start(자기 자신), end(연결된 노드), value(비용)을 가진 객체다.
            // 따라서 이 for문은 주어진 노드에 연결된 엣지를 개수만큼 꺼내 위에서 선언한 pq에 넣어 비용을 기준으로 엣지를 오름차순 정렬시킨다.
            // 이때 해당 노드의 모든 엣지를 무조건 가져올 수도 있찌만 이미 방문한 노드는 다시 계산할 필요가 없으니 if(!visit[edge.end]) { } 를 통해 방문된 노드와 연결된 엣지는 무시한다.
            for (Edge edge : graph[nowV]) {
                // Edge가 start -> end니까. start가 지금 들어온 nowV번 노드고, end가 start에서 뻗어 나간 엣지의 도착 방향의 노드다.
                // 이미 방문된 노드의 엣지는 무시하기 위한 로직.
                // *** 여기서 중요한 것은 방문한 노드가 end인 엣지를 추가하지 않았지만 그게 더 효율적일 수도 있다. 즉, 눈으로 볼 때는 그게 쉽게 보이지만
                // 컴퓨터로 볼 때는 그렇지 않다. 그러면 어떻게 처리를 할까? 방문한 노드 방향의 엣지를 담지 않는 대신 해당 엣지는 이미 이전에 반대편 노드가 start로 들어왔을 때
                // pq에 담겨있고, 그 pq를 매번 돌 때마다 초기화 하지 않기 때문에 유지가 된다. 즉,
                if(!visit[edge.end]) {
                    pq.add(edge);
                }
            }

            // nowV 노드의 엣지를 비용으로 정렬시킨 PriorityQueue가 모두 사라질 때까지 반복한다.
            while(!pq.isEmpty()) {
                // nowV 노드의 엣지중 비용이 작은 것부터 꺼낸다.
                Edge edge = pq.poll();
                // 해당 엣지의 end(nowV와 연결된 노드)가 방문하지 않았다면 if문으로 들어간다.
                if(!visit[edge.end]) {
                    // 연결된 노드를 방문한 노드를 담는 queue에 담는다.
                    // 그냥 nowV = edge.end에 바로 넣어도 될 것 같지만, 더이상 추가할 mst가 없다면 큐가 비어있을거고, while(!queue.isEmpty())를 종료하기 위해서 큐를 사용한다.
                    queue.add(edge.end);
                    // 그리고 해당 노드는 방문 여부를 true로 바꾼다.
                    visit[edge.end] = true;
                    // 그리고 그 엣지를 mst에 넣는다.
                    mst.add(edge);
                    // mst를 찾았으면 해당 nowV의 다른 엣지(지금 찾은 엣지보다 비용이 더 큰 엣지)는 반복을 중단한다.
                    break;
                }
            }
        }
    }
    static class Edge implements Comparable<Edge>{
        int start, end, value;

        Edge(int s, int e, int v) {
            this.start = s;
            this.end = e;
            this.value = v;
        }
        @Override
        public int compareTo(Edge o) {
            // TODO Auto-generated method stub
            return this.value - o.value;
        }
        @Override
        public String toString() {
            return "Edge [start=" + start + ", end=" + end + ", value=" + value + "]\n";
        }
    }
}
// source : https://velog.io/@dudrkdl777/Graph-최소신장트리-MSTPrim알고리즘
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