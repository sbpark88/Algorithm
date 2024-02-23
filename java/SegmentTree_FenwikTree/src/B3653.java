import java.util.*;
// source : https://yeoulcoding.tistory.com/125
public class B3653 {
    public static void main(String[] args) {
    
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        
        while(num > 0) {
            num--;
            int n = input.nextInt();
            int m = input.nextInt();
            int[] pos = new int[n+1];
            FenwickTree fenwickTree = new FenwickTree(n+m+1);

            // initialize pos arr & fenwick tree
            for(int i=1; i<=n; i++){
                pos[i] = i+m;
                fenwickTree.add(i+m, 1);
            }

            for(int i=1; i<=m; i++){
                int card = input.nextInt(); // the number of card
                int posCard = pos[card];    // the actual location of card
                
                System.out.print(fenwickTree.sum(posCard)-1 + " ");
                pos[card] = m-i+1;
                int afterPosCard = pos[card];
                
                // feenwickTree update
                fenwickTree.add(afterPosCard, 1);
                fenwickTree.add(posCard, -1);
            }
            System.out.println();
        }
    }
}

class FenwickTree {
    private int n, tree[];

    FenwickTree(int n) {
        this.n = n;
        this.tree = new int[n+1];
    }

    public int sum(int pos) {
        int result = 0;
        while(pos > 0) {
            result += tree[pos];
            pos -= (pos & -pos);
        }
        return result;
    }

    public void add(int pos, int val) {
        while(pos <= n) {
            tree[pos] += val;
            pos += (pos & -pos);
        }
    }
}

