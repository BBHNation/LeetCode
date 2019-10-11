import Solution.Solution;

public class Main {
    public static void main(String[] args) {
        Solution.ListNode l1 = new Solution.ListNode(1);
        Solution.ListNode l2 = new Solution.ListNode(9);
        l2.next = new Solution.ListNode(8);
        Solution.ListNode node = new Solution().tempFunc(l1, l2);
        while (null != node) {
            System.out.print(node.val);
            node = node.next;
        }
    }
}
