package org.study.hash;

import java.util.*;

public class GroupAnagrams {
    /**
     * 使用排序来解决。
     * 1. 可以将所有的字符串排序，然后通过排序后的字符串作为key，将原字符串作为value，保存在map中。
     * 2. 这样就不用计算字符出现的次数了。
     *
     * @param strs 传入的字符串数组
     * @return 返回结果，一个包含多个列表的列表
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            // 因为 Java 的类型系统不允许直接将一个 char 数组赋值给一个 String 变量。
            // 它们是不同的数据类型，需要显式地进行转换。new String(array) 就是这种显式转换的方式。
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /***
     * 49. Group Anagrams。
     * 就是计算一个字符串中出现字母的次数，从而获得一个唯一的key，然后把相同的key的加入到一个list中。
     * 时间复杂度：O(n(k+∣Σ∣))，空间复杂度：O(n(k+∣Σ∣))
     * @param strs 给定的字符串数组
     * @return 列表。返回map中所有的值。
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                // 将当前字符转换为相对于 'a' 的索引，并增加该字符的统计次数
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    //将当前索引 i 转换回对应的字符。例如，i=0 转换为 'a'，i=1 转换为 'b'。
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                    //以eat为例，此时sb中的内容就是："a1e1t1"
                }
            }
            String key = sb.toString();
            // 在一开始的时候，key是不在map中的，所以map.getOrDefault()返回的是一个空的ArrayList。
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }


    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams s = new GroupAnagrams();
        List<List<String>> res = s.groupAnagrams1(strs);
        for (List<String> group : res) {
            System.out.println(String.join(" ", group));
        }
    }
}
