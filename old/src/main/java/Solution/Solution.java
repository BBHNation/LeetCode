package Solution;

public class Solution {
    public static class ListNode {
      public int val;
      public ListNode next;
      public ListNode(int x) { val = x; }
    }

    public ListNode tempFunc(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode current = null;
        boolean plusOne = false;
        while (l1 != null && l2 != null) {
            int currentResult = l1.val + l2.val + (plusOne ? 1 : 0);
            if (null == result) {
                result = new ListNode(currentResult%10);
                current = result;
            } else {
                current.next = new ListNode(currentResult%10);
                current = current.next;
            }
            l1 = l1.next;
            l2 = l2.next;
            plusOne = currentResult >= 10;
        }

        if (current != null) {
            if (null == l2) {
                current.next = l1;
            }
            if (null == l1) {
                current.next = l2;
            }
            if (plusOne) {
                current.next = new ListNode(1);
            }
        }

        return result;
    }

}
