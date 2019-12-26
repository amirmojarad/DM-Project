import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Expression expression1 = new Expression("(1,1),(2,2),(3,3),(7,4),(33,4),(22,4),(11,4),");
        Expression expression2 = new Expression("(1,1),(2,8),(3,3),(4,4),(7,4),(8,4),(5,4)");
        System.out.println(expression1.difference(expression2));
    }
}
