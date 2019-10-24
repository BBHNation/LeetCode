import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import javafx.util.Pair;

/**
 * Solution class is saving all the solution function for leetcode questions.
 */
public class Solution {

    /**
     * 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     *
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶:
     *
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     *
     * 解题思路，找到状态转移公式：  f(n) = max[f(n-1), f(n-1)+n]
     *
     * @param nums 给定的数组
     * @return 返回最大子序和
     */
    public int maxSubArray(int[] nums) {
        int sum = 0, maxSum = Integer.MIN_VALUE;
        for (int i : nums) {
            sum = Math.max(i, sum + i);
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    /**
     * 买卖股票的最佳时机
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     *
     * 注意你不能在买入股票前卖出股票。
     *
     * 主要思路，循环一次，保存目前为止的最低价格和最大利润。
     * 动态规划推导公式：（前n天的最大利润）= MAX（前n-1天的最大利润， 第n天的价格 - 前n-1天中的最低价格）
     *
     * f(n) = max(f(n-1), price_n - current_min_price)
     *
     * 可以优化的地方是，保存每一步的状态计算结果。因为状态会重复计算。
     *
     * @param prices 价格变化列表
     * @return 以此买入卖出的最大利润
     */
    public int maxProfit(int[] prices) {
        var minPrice = Integer.MAX_VALUE;
        var maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }
        return maxProfit;
    }

    /**
     * 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     *
     * 动态规划状态推导公式：
     * n阶台阶的方法数量 = 最后只迈出1步的方法数 + 最后只迈出2步的方法数
     * f(n) = g(1) + g(2)  // 其中g代表最后一步跨出多少台阶的方案数量
     * f(n) = f(n-1) + f(n-2)
     *
     * @param n 总共需要爬n个台阶
     * @return 返回有多少种不同的方式爬到楼顶
     */
    public int climbStairs(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        var numOfTwoStepBefore = 1;
        int numOfOneStepBefore = 2;
        int numOfCurrentStep = 0;
        for (int index = 0; index < n-2; index++) {
            numOfCurrentStep = numOfTwoStepBefore + numOfOneStepBefore;
            numOfTwoStepBefore = numOfOneStepBefore;
            numOfOneStepBefore = numOfCurrentStep;
        }
        return numOfCurrentStep;
    }

    /**
     * 第一个错误的版本
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     *
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     *
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     *
     * @param n 总共有多少个版本
     * @return 第一个错误版本
     */
    public int firstBadVersion(int n) {
        return firstBadVersionInRange(1, n);
    }

    private int firstBadVersionInRange(int start, int end) {
        if (start == end) {
            return start;
        }
        int middle = start/2 + end/2;
        if (isBadVersion(middle)) {
            return firstBadVersionInRange(start, middle);
        } else {
            return firstBadVersionInRange(middle+1, end);
        }
    }

    private boolean isBadVersion(int version) {
        int wrongVersion = 1702766719;
        return version >= wrongVersion;
    }

    /**
     * 合并两个有序数组
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     *
     * 说明:
     *
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     *
     * @param nums1 第一个有序数组
     * @param m 第一个有序数组的长度
     * @param nums2 第二个有序数组
     * @param n 第二个有序数组的长度
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        Queue<Integer> tempQueue = new ArrayDeque<>();
        int indexOne = 0;
        int indexTwo = 0;

        while (indexOne < m + n) {
            int currentOfOne = indexOne < m ? nums1[indexOne] : Integer.MAX_VALUE;
            int currentOfTwo = indexTwo < n ? nums2[indexTwo] : Integer.MAX_VALUE;
            int currentOfTemp = tempQueue.isEmpty() ? Integer.MAX_VALUE : tempQueue.peek();

            if (currentOfOne < currentOfTwo && currentOfOne < currentOfTemp) {
                indexOne ++;
            } else {
                if (currentOfTwo < currentOfTemp) {
                    tempQueue.add(currentOfOne);
                    nums1[indexOne] = currentOfTwo;
                    indexTwo ++;
                } else {
                    tempQueue.add(currentOfOne);
                    nums1[indexOne] = tempQueue.poll();
                }
                indexOne ++;
            }
        }
    }

    public void newMerge(int[] nums1, int m, int[] nums2, int n) {
        int len1 = m-1;
        int len2 = n-1;
        int len = m+n-1;

        while (len1>=0&&len2>=0) {
            nums1[len--] = nums1[len1] > nums2[len2] ? nums1[len1--] : nums2[len2--];
        }
        // if nums2 remain some num. we need to copy nums2 remaining nums to num1.
        System.arraycopy(nums2, 0, nums1, 0, len2 + 1);
    }

    /**
     * 将有序数组转换为二叉搜索树
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     *
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     * 示例:
     *
     * 给定有序数组: [-10,-3,0,5,9],
     *
     * 一个可能的答案是：[0,-3,9,-10,null,5]
     *
     * @param nums 输入的有序数组
     * @return 返回高度平衡的二叉搜索树
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        int middleIndex = nums.length/2;
        TreeNode root = new TreeNode(nums[middleIndex]);
        if (middleIndex == 0) {
            return root;
        }
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, middleIndex));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, middleIndex+1, nums.length));
        return root;
    }

    /**
     * 二叉树的层次遍历
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *
     * @param root the root of the tree
     * @return result
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tempArr = new ArrayList<>();
            int currentSize = queue.size();
            for (int index = 0; index < currentSize; index++) {
                TreeNode currentNode = queue.poll();
                tempArr.add(currentNode.val);
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right!= null) {
                    queue.add(currentNode.right);
                }
            }
            result.add(tempArr);
        }
        return result;
    }

    /**
     * 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     *
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *
     * @param root the tree root
     * @return if the tree is symmetric
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        }
        return (left.val == right.val)  && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    /**
     * 验证二叉搜索树 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     *
     * 假设一个二叉搜索树具有如下特征：
     *
     * 节点的左子树只包含小于当前节点的数。 节点的右子树只包含大于当前节点的数。 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * @param root the tree
     * @return is the tree valid BST
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTInRange(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBSTInRange(TreeNode root, int minValue, int maxValue) {
        if (root == null) {
            return true;
        }
        if (root.val < minValue || root.val > maxValue) {
            return false;
        }
        if ((root.left != null && root.val == root.left.val)
            || (root.right != null && root.val == root.right.val)) {
            return false;
        }
        return isValidBSTInRange(root.left, minValue, root.val == Integer.MIN_VALUE ? root.val : root.val - 1)
            && isValidBSTInRange(root.right, root.val == Integer.MAX_VALUE ? root.val : root.val + 1, maxValue);
    }

    /**
     * 二叉树的最大深度 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 示例： 给定二叉树 [3,9,20,null,null,15,7]，
     *
     * 返回它的最大深度 3 。
     *
     * @param root tree
     * @return max depth of tree
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * 给定一个链表，判断链表中是否有环。
     *
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     *
     * @param head list
     * @return if the list has cycle;
     */
    public boolean hasCycle(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if (slowPointer.equals(fastPointer)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 回文链表 请判断一个链表是否为回文链表。
     *
     * 示例 1:
     *
     * 输入: 1->2 输出: false 示例 2:
     *
     * 输入: 1->2->2->1 输出: true 进阶： 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     *
     * 主要的思路是：快慢指针寻找中间位置，reverse快指针后续的node 然后顺序对比两个链表的内容
     *
     * @param head linked list
     * @return if the list is palindrome
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        ListNode middlePointer = slowPointer;
        ListNode reversedPartListHead = privateReverseList(middlePointer);
        ListNode originPartListHead = head;
        while (reversedPartListHead != null && originPartListHead != null) {
            if (reversedPartListHead.val != originPartListHead.val) {
                return false;
            }
            reversedPartListHead = reversedPartListHead.next;
            originPartListHead = originPartListHead.next;
        }
        return true;
    }

    private ListNode privateReverseList(ListNode head) {
        ListNode preNode = null;
        ListNode current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = preNode;
            preNode = current;
            current = next;
        }
        return preNode;
    }

    /**
     * 合并两个有序链表 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * 示例：
     *
     * 输入：1->2->4, 1->3->4 输出：1->1->2->3->4->4
     *
     * @param l1 linked sorted list 1
     * @param l2 linked sorted list 2
     * @return new merged sorted list
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        ListNode point = null;
        ListNode head = null;

        while (l1 != null && l2 != null) {
            if (point == null) {
                head = l1.val < l2.val ? l1 : l2;
                point = head;
            } else {
                point.next = l1.val < l2.val ? l1 : l2;
                point = point.next;
            }

            if (l1.val < l2.val) {
                l1 = l1.next;
            } else {
                l2 = l2.next;
            }
        }

        point.next = l1 == null ? l2 : l1;

        return head;
    }

    /**
     * 反转一个单链表。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL 进阶: 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     *
     * @param head head of list
     * @return new head
     */
    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode pointer = head;
        while (pointer != null) {
            stack.push(pointer);
            pointer = pointer.next;
        }
        if (stack.isEmpty()) {
            return head;
        }
        head = stack.pop();
        pointer = head;
        while (!stack.isEmpty()) {
            pointer.next = stack.pop();
            pointer = pointer.next;
            pointer.next = null;
        }
        return head;
    }

    public ListNode newReverseList(ListNode head) {
        ListNode preNode = null;
        while (head != null) {
            ListNode next = head.next;

            head.next = preNode;
            preNode = head;

            head = next;
        }
        return preNode;
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     *
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5. 说明：
     *
     * 给定的 n 保证是有效的。 你能尝试使用一趟扫描实现吗？
     *
     * @param head head of linked list.
     * @param n 倒数第n个
     * @return head of linked list.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fasterNode = head;
        ListNode pointer = head;
        ListNode preNode = head;

        for (int index = 0; index < n; index++) {
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
     * Definition for singly-linked list. public class ListNode { int val; ListNode next;
     * ListNode(int x) { val = x; } }
     *
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
     * 最长公共前缀 编写一个函数来查找字符串数组中的最长公共前缀。
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
            if (index >= strs[0].length()) {
                return commonPrefixBuilder.toString();
            }
            String currentChar = strs[0].substring(index, index + 1);
            for (String str : strs) {
                if (str.equals("") || index >= str.length() || !str.substring(index, index + 1)
                    .equals(currentChar)) {
                    return commonPrefixBuilder.toString();
                }
            }
            commonPrefixBuilder.append(currentChar);
            index++;
        }
    }

    public String newLongestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (String str : strs) {
            while (str.indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
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
     * 1.     1 2.     11 3.     21 4.     1211 5.     111221
     *
     * 1 被读作  "one 1"  ("一个一") , 即 11。 11 被读作 "two 1s" ("两个一"）, 即 21。 21 被读作 "one 2",  "one 1"
     * （"一个二" ,  "一个一") , 即 1211。
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
        for (int index = 0; index < n - 1; index++) {
            result = say(result);
        }
        return result;
    }

    Pair<String, String> cutFirstSameDigit(String number) {
        String currentDigit = number.substring(0, 1);
        int index = 0;
        do {
            index++;
        } while (index + 1 <= number.length() && currentDigit
            .equals(number.substring(index, index + 1)));
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
