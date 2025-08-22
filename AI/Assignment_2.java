import java.util.*;

public class mapColor {
    
    static final List<String> REGIONS = Arrays.asList("WA","NT","SA","Q","NSW","V","T");
    static final List<String> COLORS = Arrays.asList("RED","GREEN","BLUE");

    static final Map<String, List<String>> NEIGHBORS = new HashMap<>();
    static {
        NEIGHBORS.put("WA",  Arrays.asList("NT","SA"));
        NEIGHBORS.put("NT",  Arrays.asList("WA","SA","Q"));
        NEIGHBORS.put("SA",  Arrays.asList("WA","NT","Q","NSW","V"));
        NEIGHBORS.put("Q",   Arrays.asList("NT","SA","NSW"));
        NEIGHBORS.put("NSW", Arrays.asList("Q","SA","V"));
        NEIGHBORS.put("V",   Arrays.asList("SA","NSW"));
        NEIGHBORS.put("T",   Arrays.asList()); 
    }

    Map<String, String> assignment = new HashMap<>();

    public static void main(String[] args) {
        mapColor csp = new mapColor();
        if (csp.backtrack()) {
            System.out.println("Solution:");
            for (String r : REGIONS) {
                String color = csp.assignment.get(r);
                System.out.print(r + " = " + color);
                
                
                List<String> neighbors = NEIGHBORS.get(r);
                if (!neighbors.isEmpty()) {
                    System.out.print(" | Neighbors: ");
                    for (String nb : neighbors) {
                        String nbColor = csp.assignment.get(nb);
                        System.out.print(nb + "(" + nbColor + ") ");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution found.");
        }
    }

    /** Backtracking search */
    boolean backtrack() {
        if (assignment.size() == REGIONS.size()) return true;

        String var = null;
        for (String r : REGIONS) {
            if (!assignment.containsKey(r)) { var = r; break; }
        }

        // Try each color
        for (String color : COLORS) {
            if (isConsistent(var, color)) {
                assignment.put(var, color);
                if (backtrack()) return true;
                assignment.remove(var);
            }
        }
        return false; 
    }

 
    boolean isConsistent(String var, String color) {
        for (String nb : NEIGHBORS.get(var)) {
            String nbColor = assignment.get(nb);
            if (nbColor != null && nbColor.equals(color)) return false; 
        }
        return true;
    }
}
