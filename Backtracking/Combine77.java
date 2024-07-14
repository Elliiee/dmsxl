package Backtracking;
import java.util.*;
class Combine77 {
    List<List<Integer>> result = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();

    List<List<Integer>> combine(int n, int k){
        backtrack(n, k, 1);
        return result; 
    }

    void backtrack(int n, int k, int startIndex){
        if (path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n; i++){
            path.add(i);
            backtrack(n, k, i+1);
            path.removeLast();
        }
    }

    public static void main(String[] args){
        Combine77 c = new Combine77();
        c.combine(4, 2);
        for (List<Integer> row : c.result){
            for (int r : row){
                System.out.print(r + " ");
            }
            System.out.println();
        }
    }
}
