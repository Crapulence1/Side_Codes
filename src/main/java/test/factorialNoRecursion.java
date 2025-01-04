package test;
import java.util.Stack;
public class factorialNoRecursion {

        public static int factorialIterative(int n) {
            Stack<Integer> stack = new Stack<>();
            stack.push(n);
            int result = 1;

            while (!stack.isEmpty()) {
                int current = stack.pop();
                if (current > 1) {
                    result *= current;
                    stack.push(current - 1);
                }
            }

            return result;
        }

        public static void main(String[] args) {
            int n = 5;
            System.out.println("Factorial of " + n + " is: " + factorialIterative(n));
        }


}
