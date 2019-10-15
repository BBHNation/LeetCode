import javafx.util.Pair;

/**
 * Solution class is saving all the solution function for leetcode questions.
 */
public class Solution {

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     *
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     *
     * 给定的 n 保证是有效的。
     * 你能尝试使用一趟扫描实现吗？
     *
     * @param head head of linked list.
     * @param n 倒数第n个
     * @return head of linked list.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fasterNode = head;
        ListNode pointer = head;
        ListNode preNode = head;

        for(int index = 0; index < n; index++) {
            fasterNode = fasterNode.next;
            if (fasterNode == null) {
                head = head.next;
                return head;
            }
        }

        while (fasterNode != null) {
            fasterNode = fasterNode.next;
            preNode = pointer;
            pointer = pointer.next;
        }

        preNode.next = preNode.next.next;

        return head;
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     * @param node node to delete.
     */
    public void deleteNode(ListNode node) {
        if (node.next == null) {
            node = null;
        } else {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }


    /**
     * 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * @param strs 字符串数组
     * @return 最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0 || strs[0].equals("")) {
            return "";
        }
        int index = 0;
        StringBuilder commonPrefixBuilder = new StringBuilder();
        while (true) {
            if (index>=strs[0].length()) {
                return commonPrefixBuilder.toString();
            }
            String currentChar = strs[0].substring(index, index+1);
            for(String str : strs) {
                if (str.equals("") || index >= str.length() || !str.substring(index, index+1).equals(currentChar)) {
                    return commonPrefixBuilder.toString();
                }
            }
            commonPrefixBuilder.append(currentChar);
            index ++;
        }
    }

    public String newLongestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (String str : strs) {
           while (str.indexOf(prefix) != 0) {
               prefix = prefix.substring(0, prefix.length()-1);
               if (prefix.equals("")) {
                   return "";
               }
           }
        }
        return prefix;
    }

    /**
     * 实现 strStr() 函数。
     *
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     *
     * 这里使用的是new subString的hashCode相等性质判断，没有用到KMP算法，在一定程度上，此算法和KMP时间复杂度一致
     * JDK6中subString时间复杂度是O(1)，底层维护同一个char[]对象，hash值的计算也是O(1)复杂度，所以目前整体算法在
     * JDK6中时间复杂度是稳定O(n)，空间复杂度是O(1). 但是JDK7之后，为了防止内存泄露，subString方法不再是维护同一个char[]，导致此算法时间复杂度增加为O(n*n)
     *
     * @param haystack 字符串
     * @param needle 字符串
     * @return index
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) {
            return 0;
        }
        if (haystack == null || haystack.isEmpty()) {
            return -1;
        }

        int lengthOfNeedle = needle.length();
        for (int indexOfPiece = 0;
            indexOfPiece < haystack.length() - lengthOfNeedle + 1;
            indexOfPiece++) {
            if (haystack.substring(indexOfPiece, indexOfPiece + lengthOfNeedle).hashCode()
                == needle.hashCode()) {
                return indexOfPiece;
            }
        }
        return -1;
    }


    /**
     * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
     *
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     *
     * 1 被读作  "one 1"  ("一个一") , 即 11。
     * 11 被读作 "two 1s" ("两个一"）, 即 21。
     * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
     *
     * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
     *
     * 注意：整数顺序将表示为一个字符串。
     *
     * @param n 正整数n
     * @return 报数序列第n项
     */
    public String countAndSay(int n) {
        String result = "1";
        for (int index = 0; index < n-1; index++) {
            result = say(result);
        }
        return result;
    }

    Pair<String, String> cutFirstSameDigit(String number) {
        String currentDigit = number.substring(0, 1);
        int index = 0;
        do {
            index ++;
        } while (index+1<=number.length() && currentDigit.equals(number.substring(index, index+1)));
        String result = index + currentDigit;
        String leftString = number.substring(index);
        return new Pair<>(result, leftString);
    }


    String say(String number) {
        StringBuilder resultBuilder = new StringBuilder();
        do {
            Pair<String, String> resultAndLeftString = cutFirstSameDigit(number);
            String result = resultAndLeftString.getKey();
            resultBuilder.append(result);
            number = resultAndLeftString.getValue();
        } while (!number.equals(""));
        return resultBuilder.toString();
    }

}
