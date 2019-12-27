import java.util.*;

public class Expression {
    ///Attributes
    private String sentences;
    private HashMap<Integer, Integer> set;

    ///functions

    //████████████████
    //█ Constructors █
    //████████████████

    public Expression(String sentences) {
        this.sentences = sentences;
        this.set = new HashMap<>();
        evaluateSet();
    }

    public Expression() {
        this.sentences = "";
        this.set = new HashMap<>();
    }

    //█████████████████████
    //█ Setter and Getter █
    //█████████████████████

    public String getSentences() {
        return sentences;
    }

    private void setSentences(String sentences) {
        this.sentences = sentences;
    }

    private HashMap<Integer, Integer> getSet() {
        return set;
    }


    private void setSet(HashMap<Integer, Integer> set) {
        this.set = set;
    }

    //█████████████████████
    //█      Methods      █
    //█████████████████████

    private void evaluateSet() {
        String array[] = sentences.split("\\D+");
        for (int i = 1; i <= array.length - 1; i++) {
            set.put(Integer.parseInt(array[i]), Integer.parseInt(array[++i]));
        }
        setSentences(getSet().toString());
    }

    public Expression union(Expression expression) {
        /**
         * 1 - create an instance of Expression Class
         * 2 - save this.set in currentMap (before change)
         * 3 - putAll expression.set in this.set
         * 4 - set this.set to last version
         * 5 - set result.input and result.output to this.set.toString()
         * 6 - set result.set to this.set(after change)
         */
        Expression result = new Expression();
        HashMap<Integer, Integer> currentMap = (HashMap<Integer, Integer>) getSet().clone();
        getSet().putAll(expression.getSet());
        result.setSet(getSet());
        result.setSentences(getSet().toString());
        setSet(currentMap);
        return result;
    }
    public Expression intersection (Expression expression){
        /**
         * 1 - create an instance of Expression class as 'result'
         * 2 - step through in this.set and expression.set to find intersection
         */
        Expression result = new Expression();
        for (HashMap.Entry<Integer, Integer> thisEntry: getSet().entrySet()) {
            for (HashMap.Entry<Integer, Integer> expEntry: expression.getSet().entrySet()){
                if(thisEntry.equals(expEntry))
                    result.getSet().put(thisEntry.getKey(), thisEntry.getValue());
            }
        }
        result.setSentences(result.getSet().toString());
        return result;
    }

    public Expression difference(Expression expression){
        Expression result = new Expression();
        HashMap<Integer, Integer> intersectionMap = intersection(expression).getSet();
        if(intersectionMap.equals(getSet()))
            return result;
        result.setSet((HashMap<Integer, Integer>) getSet().clone());
        for (HashMap.Entry<Integer, Integer> thisEntry: getSet().entrySet()){
            for (HashMap.Entry<Integer, Integer> interSectionEntry: intersectionMap.entrySet()) {
                if(thisEntry.equals(interSectionEntry))
                    result.getSet().remove(thisEntry.getKey());
            }
        }
        result.setSentences(result.getSet().toString());
        return result;
    }

    public boolean reflexive(){
        boolean flag = false;
        for (HashMap.Entry<Integer, Integer> thisEntry: getSet().entrySet()) {
            if(thisEntry.getKey().equals(thisEntry.getValue()))
                flag = true;
            else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean symmetric() throws Exception{
        boolean flag = false;
        for (HashMap.Entry<Integer, Integer> thisEntry: getSet().entrySet()) {
            try {
                if (getSet().get(thisEntry.getValue()).equals(thisEntry.getKey()))
                    flag = true;
                else{
                    flag = false;
                    break;
                }
            }catch (Exception ex){
                return false;
            }
        }
        return flag;
    }

    @Override
    public String toString() {
        return getSet().toString();
    }
}
