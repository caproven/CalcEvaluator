package calc.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Extension of an ArrayList supporting built-in sorting upon item insertion.
 * Represents a list of mathematical operators, represented as strings.
 * Operators are sorted in increasing order based of their 'weight', indicating
 * the order of operations to be followed during evaluation.
 * @author caproven
 */
public class OperatorList extends ArrayList<String> {
    /** Default serializable ID. */
    static final long serialVersionUID = 1L;
    
    /** Code for an open parenthesis when retrieving the weight of an operator. */
    public static final int OPEN_PAREN = -1;
    /** Code for a close parenthesis when retrieving the weight of an operator. */
    public static final int CLOSE_PAREN = -2;
    
    /** Map of operators to their relative order-of-operation weights. */
    private Map<String, Integer> opWeights;
    
    /**
     * Constructs the OperatorList, initializing the map of operator weights.
     */
    public OperatorList() {
        super();
        
        opWeights = new HashMap<>();
        opWeights.put("+", 1);
        opWeights.put("-", 1);
        opWeights.put("*", 2);
        opWeights.put("/", 2);
        opWeights.put("^", 3);
        opWeights.put("(", OPEN_PAREN);
        opWeights.put(")", CLOSE_PAREN);
    }
    
    /**
     * Appends the given operator to the back of the OperatorList, sorting by
     * increasing weight.
     * @param op New operator to be inserted into the OperatorList
     */
    public void addOp(String op) {
        // insert operator to end of list
        this.add(op);
        
        // sort by increasing (stable, similar to insertion sort - only start with last element)
        int idx = size() - 1;
        while (idx > 0 && opWeights.get(get(idx)) < opWeights.get(get(idx - 1))) {
            swap(idx, idx - 1);
            idx--;
        }
    }
    
    /**
     * Swaps the values at the two given indices within the OperatorList
     * @param idx1 First index to swap
     * @param idx2 Second index to swap
     */
    private void swap(int idx1, int idx2) {
        String temp = get(idx1);
        set(idx1, get(idx2));
        set(idx2, temp);
    }
    
    /**
     * Retrieves the weight of the highest operator in the OperatorList.
     * @return Weight of highest weight operator in the OperatorList
     */
    public Integer getHighestOpWeight() {
        // if addOp() is always used for insertion, list will be sorted
        // return last element's weight
        return size() != 0 ? getOpWeight(get(size() - 1)) : null;
    }
    
    /**
     * Retrieves the weight of the given operator.
     * @param op Operator whose weight will be retrieved
     * @return Weight of the given operator
     */
    public Integer getOpWeight(String op) {
        return opWeights.get(op);
    }
}
