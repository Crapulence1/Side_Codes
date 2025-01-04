package test;

import java.util.Stack;

public class stacksTest {
    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>();

        int i = 0;
        stack.push(i);
        while(!stack.isEmpty()){
             i = stack.pop();
            System.out.print(i + " is here");
            i++;
            stack.push(i);
        }
        System.out.println(stack);
    }
}
