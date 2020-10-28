import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
//import java.util.Scanner;
// 시간 초과 런타임 오류 발생 (O(n^2))

public class B2957 {
    static int[] binaryTree = new int[300001];
    static int c = 0;
 
    public static void insert(int num, int idx) {
        c++;
        if (num < binaryTree[idx]) { // 새로 들어온 숫자가 노드보다 작다면
            if (binaryTree[idx * 2] == 0) { // 왼쪽 자식이 없다면
                binaryTree[idx * 2] = num;
            } else { //왼쪽 자식이 있으므로 왼쪽자식을 기준으로 함수 호출
                insert(num, idx * 2);
            }
        } else if (num > binaryTree[idx]) { // 새로 들어온 숫자가 노드보다 크다면
            if (binaryTree[idx * 2 + 1] == 0) { // 오른쪽 자식이 있다면
                binaryTree[idx * 2 + 1] = num;
            } else { //오른쪽 자식이 있으므로 오른쪽 자식을 기준으로 함수 호출
                insert(num, idx * 2 + 1);
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
       //Scanner sc = new Scanner(System.in);
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
        //int N = sc.nextInt(); // 수열의 크기
    	int N = Integer.parseInt(br.readLine());
    	int num[] = new int[N+1];
    	  for (int i = 1; i <= N; i++) {
               num[i] = Integer.parseInt(br.readLine());// sc.nextInt();
    	  }
    	  
        for (int i = 1; i <= N; i++) {

            if (i == 1) {
                binaryTree[1] = num[1];
            } else {
                insert(num[i], 1);
            }
            //System.out.println(c);
            bw.write(c + "\n");
        }
       // sc.close();
        bw.flush();
        bw.close();
        
    }
}


// 출처: https://jaesungbong.tistory.com/18 [improving my programming skills]
// pre reading : https://blog.naver.com/foat3376/220003640781