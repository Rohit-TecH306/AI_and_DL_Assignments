import java.util.*;

public class puzzleSolver {
    static String goal = "123456780";  

    
    static int[][] directions = {
        {-1, 0}, 
        {1, 0},  
        {0, -1}, 
        {0, 1}   
    };

    
    static class Node {
        String state;
        String path;  

        Node(String state, String path) {
            this.state = state;
            this.path = path;
        }
    }

   
    static void solve(String start) {
        Queue<Node> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.add(new Node(start, ""));
        visited.add(start);

        while (!q.isEmpty()) {
            Node current = q.poll();

            if (current.state.equals(goal)) {
                System.out.println("Solved! Path: " + current.path);
                return;
            }

            int zeroIndex = current.state.indexOf('0');
            int row = zeroIndex / 3;
            int col = zeroIndex % 3;

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                    int newIndex = newRow * 3 + newCol;
                    String newState = swap(current.state, zeroIndex, newIndex);

                    if (!visited.contains(newState)) {
                        visited.add(newState);
                        q.add(new Node(newState, current.path + " -> " + newState));
                    }
                }
            }
        }

        System.out.println("Unsolvable Puzzle.");
    }

    static String swap(String s, int i, int j) {
        char[] arr = s.toCharArray();
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return new String(arr);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter initial puzzle state (0 for blank): ");
        String start = sc.next();
        solve(start);
    }
}
