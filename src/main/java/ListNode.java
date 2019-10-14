public class ListNode {
     private int val;
     private ListNode next;
     ListNode(int x) { val = x; }

     public void setVal(int val) {
          this.val = val;
     }

     public void setNext(ListNode next) {
          this.next = next;
     }

     public int getVal() {
          return val;
     }

     public ListNode getNext() {
          return next;
     }
}
