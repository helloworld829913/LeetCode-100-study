package org.study.stack;

import java.util.*;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 1. 左括号必须用相同类型的右括号闭合。
 * 2. 左括号必须以正确的顺序闭合。
 * 3. 每个右括号都有一个对应的相同类型的左括号。
 */
public class ValidParentheses {

    /**
     * 根据题目的提示想出来的方法
     * 思路：
     * 1. When you encounter an opening bracket, push it to the top of the stack.
     * 2. When you encounter a closing bracket,
     * check if the top of the stack was the opening for it.
     * If yes, pop it from the stack. Otherwise, return false.
     * 3. 简单：把符号按照顺序放进栈里，遇到右括号就取出栈顶，判断是不是相符。
     * 如果不符合就return false
     * <p>
     * 问题是：一堆的if判断，对后续维护不太好，如果有更多的符合进来就需要修改代码。
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // 如果是左括号，入栈
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 如果是右括号，检查栈是否为空或不匹配
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == ']' && top != '[') ||
                        (c == '}' && top != '{')) {
                    return false;
                }
            }
        }

        // 最后检查栈是否为空
        return stack.isEmpty();
    }

    /**
     * 更好维护的版本
     */
    public boolean isValid1(String s) {
        int n = s.length();
        // 快速失败：如果字符串长度是奇数，则不可能所有括号都成对出现，直接返回 false。
        if (n % 2 == 1) {
            return false;
        }
        // 3. 定义一个Map来存储所有左括号与右括号的配对关系。
        //    键是右括号，值是对应的左括号。
        //    使用匿名内部类的方式初始化Map。
        Map<Character, Character> pairs = new HashMap<>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        // 4. 定义一个栈（Deque作为栈使用），用于存储预期未闭合的左括号。
        //    当遇到左括号时，将其压入栈中；当遇到右括号时，检查栈顶是否是对应的左括号。
        Deque<Character> stack = new LinkedList<>();

        // 5. 遍历输入字符串中的每一个字符
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i); // 获取当前字符

            // 6. 判断当前字符是左括号还是右括号
            //    如果 Map 中包含当前字符作为键，说明当前字符是右括号（例如 ')', ']', '}'）。
            if (pairs.containsKey(ch)) {
                // 7. 处理右括号：
                //    a. 如果栈为空，或者栈顶元素与当前右括号期望的左括号不匹配：
                //       - stack.isEmpty()：表示遇到了右括号，但前面没有匹配的左括号，例如 "())"。
                //       - stack.peek() != pairs.get(ch)：栈顶的左括号类型与当前右括号不匹配，例如 "([)]"。
                //       以上两种情况都表示括号无效，直接返回 false。
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                //    b. 如果栈不为空且栈顶元素匹配，则将栈顶元素弹出。
                //       这表示当前的右括号成功匹配了最近的左括号。
                stack.pop();
            } else {
                // 8. 处理左括号：
                //    如果当前字符不是右括号（即它是左括号 '(', '[', '{'），则将其压入栈中。
                //    等待将来遇到匹配的右括号时弹出。
                stack.push(ch);
            }
        }

        // 9. 遍历结束后，检查栈是否为空。
        //    如果栈为空，说明所有左括号都找到了对应的右括号并成功匹配弹出，字符串是有效的。
        //    如果栈不为空，说明还有未闭合的左括号（例如 "([{"），字符串是无效的。
        return stack.isEmpty();
    }
}
