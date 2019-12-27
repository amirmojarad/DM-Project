import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        Expression expression = new Expression("(1,2),(2,1),(3,8),(4,3),(8,2)");
        System.out.println(expression.symmetric());
    }
}
