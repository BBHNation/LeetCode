import java.util.List;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for Solution class.
 */
public class SolutionTest {
    private Solution solution;

    @Before
    public void init() {
        solution = new Solution();
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [最大子序和]
     */
    @Test
    public void shouldReturn6_whenMaxSubArray_given213412154() {
        //given
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        //when
        int result = solution.maxSubArray(nums);

        //then
        Assert.assertEquals(6, result);
    }

    @Test
    public void shouldReturn6_whenFindMaxProfit_given715364() {
        Assert.assertEquals(5, solution.maxProfit(new int[]{7,1,5,3,6,4}));
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [爬楼梯动态规划]
     */
    @Test
    public void shouldReturn3_whenClimbStairs_given3() {
        int ways = solution.climbStairs(3);
        Assert.assertEquals(3, ways);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [合并两个有序数组]
     */
    @Test
    public void shouldFindFirstBadVersion() {
        int firstBadVersion = solution.firstBadVersion(2126753390);
        Assert.assertEquals(1702766719, firstBadVersion);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [合并两个有序数组]
     */
    @Test
    public void shouldMergeSuccess_whenMergeTwoSortedArray_givenTwoSortedArray() {
        //given
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};

        //when
        solution.newMerge(nums1, 3, nums2, 3);

        //then
        Assert.assertEquals(1, nums1[0]);
        Assert.assertEquals(2, nums1[1]);
        Assert.assertEquals(2, nums1[2]);
        Assert.assertEquals(3, nums1[3]);
        Assert.assertEquals(5, nums1[4]);
        Assert.assertEquals(6, nums1[5]);
    }

    @Test
    public void shouldMergeSuccess_whenMergeTwoSortedArray_givenOneArrayIsEmpty() {
        //given
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{};

        //when
        solution.merge(nums1, 1, nums2, 0);

        //then
        Assert.assertEquals(1, nums1[0]);
    }

    @Test
    public void shouldMergeSuccess_whenMergeTwoSortedArray_givenTwoArrayLengthIsOne() {
        //given
        int[] nums1 = new int[]{2, 0};
        int[] nums2 = new int[]{1};

        //when
        solution.merge(nums1, 1, nums2, 1);

        //then
        Assert.assertEquals(1, nums1[0]);
        Assert.assertEquals(2, nums1[1]);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [层次遍历二叉树]
     */
    @Test
    public void shouldReturnCurrentTreeRoot_givenSortedArray() {
        int[] arr = new int[]{-10, -3, 0, 5, 9};
        TreeNode root = solution.sortedArrayToBST(arr);
        Assert.assertEquals(0, root.val);
        Assert.assertEquals(-3, root.left.val);
        Assert.assertEquals(9, root.right.val);
        Assert.assertEquals(-10, root.left.left.val);
        Assert.assertEquals(5, root.right.left.val);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [层次遍历二叉树]
     */
    @Test
    public void shouldPrintLevelOrder() {
        TreeNode root = new TreeNode(
            new TreeNode(
                new TreeNode(4),
                new TreeNode(5),
                2
            ),
            new TreeNode(
                new TreeNode(6),
                new TreeNode(7),
                3
            ),
            1
        );
        List<List<Integer>> result = solution.levelOrder(root);
        Assert.assertEquals(3, result.size());
        Assert.assertEquals(1, result.get(0).get(0).intValue());
        Assert.assertEquals(2, result.get(1).get(0).intValue());
        Assert.assertEquals(3, result.get(1).get(1).intValue());
        Assert.assertEquals(4, result.get(2).get(0).intValue());
        Assert.assertEquals(5, result.get(2).get(1).intValue());
        Assert.assertEquals(6, result.get(2).get(2).intValue());
        Assert.assertEquals(7, result.get(2).get(3).intValue());
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [判断是否是二叉排序树]
     */
    @Test
    public void shouldReturnTrue_whenJudgeIsSymmetric_givenNotSymmetricTree() {
        //given
        TreeNode root = new TreeNode(
            new TreeNode(
                null,
                new TreeNode(3),
                2
            ),
            new TreeNode(
                null,
                new TreeNode(3),
                2
            ),
            1
        );

        //when
        boolean treeIsSymmetric = solution.isSymmetric(root);

        //then
        Assert.assertFalse(treeIsSymmetric);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [判断是否是二叉排序树]
     */
    @Test
    public void shouldReturnFalse_whenJudgeIsValidBST_givenMinNumber() {
        //given
        TreeNode root = new TreeNode(
            new TreeNode(Integer.MIN_VALUE),
            null,
            Integer.MIN_VALUE
        );

        //when
        boolean isValidBST = solution.isValidBST(root);

        //then
        Assert.assertFalse(isValidBST);
    }

    @Test
    public void shouldReturnFalse_whenJudgeIsValidBST_givenANotBST() {
        //given
        TreeNode root = new TreeNode(
            new TreeNode(5),
            new TreeNode(
                new TreeNode(6),
                new TreeNode(20),
                15
            ),
            10
        );

        //when
        boolean isValidBST = solution.isValidBST(root);

        //then
        Assert.assertFalse(isValidBST);
    }

    @Test
    public void shouldReturnTrue_whenJudgeIsValidBST_givenABST() {
        //given
        TreeNode root = new TreeNode(
            new TreeNode(1),
            new TreeNode(3),
            2
        );

        //when
        boolean isValidBST = solution.isValidBST(root);

        //then
        Assert.assertTrue(isValidBST);
    }

    @Test
    public void shouldReturnFalse_whenJudgeIsValidBST_givenNotBST() {
        //given
        TreeNode root = new TreeNode(
            new TreeNode(1),
            new TreeNode(
                new TreeNode(3),
                new TreeNode(6),
                4
            ),
            5
        );

        //when
        boolean isValidBST = solution.isValidBST(root);

        //then
        Assert.assertFalse(isValidBST);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [二叉树的最大深度]
     */
    @Test
    public void shouldReturn3_whenGetMaxDepthOfTree_given3920nullNull157() {
        //given
        TreeNode root = new TreeNode(
            new TreeNode(9),
            new TreeNode(
                new TreeNode(15),
                new TreeNode(7),
                20
            ),
            3
        );

        //when
        int depth = solution.maxDepth(root);

        //then
        Assert.assertEquals(3, depth);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [判断环形链表]
     */
    @Test
    public void shouldReturnTrue_whenJudgeHasCycle_givenPos1() {
        //given
        ListNode last = new ListNode(-4);
        ListNode head = new ListNode(3, new ListNode(2, new ListNode(0, last)));
        last.next = head.next; // cycle

        //when
        boolean result = solution.hasCycle(head);

        //then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalse_whenJudgeHasCycle_given123() {
        //given
        ListNode list = new ListNode(1, new ListNode(2, new ListNode(3)));

        //when
        boolean result = solution.hasCycle(list);

        //then
        Assert.assertFalse(result);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [合并两个有序链表]
     */
    @Test
    public void shouldReturnTrue_whenListIsPalindrome_given1221() {
        //given
        ListNode palindrome = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));

        //when
        boolean result = solution.isPalindrome(palindrome);

        //then
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalse_whenListIsNotPalindrome_given123() {
        //given
        ListNode notPalindrome = new ListNode(1, new ListNode(2, new ListNode(3)));

        //when
        boolean result = solution.isPalindrome(notPalindrome);

        //then
        Assert.assertFalse(result);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [合并两个有序链表]
     */
    @Test
    public void shouldReturnMergedList_whenMergeTwoList_given124And134() {
        //given
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        //when
        ListNode mergedList = solution.mergeTwoLists(l1, l2);

        //then
        Assert.assertEquals(1, mergedList.val);
        Assert.assertEquals(1, mergedList.next.val);
        Assert.assertEquals(2, mergedList.next.next.val);
        Assert.assertEquals(3, mergedList.next.next.next.val);
        Assert.assertEquals(4, mergedList.next.next.next.next.val);
        Assert.assertEquals(4, mergedList.next.next.next.next.next.val);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [反转链表]
     */
    @Test
    public void shouldListReversed_whenReverseList_given12345() {
        //given
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        //when
        ListNode newHead = solution.newReverseList(head);
        //then
        Assert.assertEquals(5, newHead.val);
        Assert.assertEquals(4, newHead.next.val);
        Assert.assertEquals(3, newHead.next.next.val);
        Assert.assertEquals(2, newHead.next.next.next.val);
        Assert.assertEquals(1, newHead.next.next.next.next.val);
    }

    @Test
    public void shouldReturnEmptyList_whenReverseList_givenEmptyList() {
        //given
        ListNode head = null;
        //when
        ListNode newHead = solution.reverseList(head);
        //then
        Assert.assertNull(newHead);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [删除链表的倒数第N个节点]
     */
    @Test
    public void shouldDeleteNode_whenDeleteNthFromEnd_givenLinkedList() {
        //given
        ListNode head = new ListNode(1);
        head.next =
            new ListNode(2,
                new ListNode(3,
                    new ListNode(4,
                        new ListNode(5))));
        //when
        solution.removeNthFromEnd(head, 2);

        //then
        Assert.assertEquals(5, head.next.next.next.val);
    }

    @Test
    public void shouldDeleteFirstFromEnd_whenRemoveNthFromEnd_given12() {
        //given
        ListNode head = new ListNode(1, new ListNode(2));
        //when
        ListNode newHead = solution.removeNthFromEnd(head, 1);
        //then
        Assert.assertEquals(1, newHead.val);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [deleteNode]
     */

    @Test
    public void shouldDeleteNodeThatIndex5_whenInputIndex5_givenNodeList() {
        //given
        ListNode head = new ListNode(4);
        ListNode fiveNode = new ListNode(5);
        ListNode oneNode = new ListNode(1);
        ListNode nineNode = new ListNode(9);

        head.next = fiveNode;
        fiveNode.next = oneNode;
        oneNode.next = nineNode;

        //when
        solution.deleteNode(fiveNode);

        //then
        Assert.assertEquals(1, head.next.val);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [最长公共前缀]
     */
    @Test
    public void shouldReturnFl_whenGetLongestCommonPrefix_givenFlowerFlowFlight() {
        String result = solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"});
        Assert.assertEquals("fl", result);

        String newResult = solution.newLongestCommonPrefix(new String[]{"flower", "flow", "flight"});
        Assert.assertEquals("fl", newResult);
    }

    @Test
    public void shouldReturnBlank_whenGetLongestCommonPrefix_givenDogRacecarCar() {
        String result = solution.longestCommonPrefix(new String[]{"dog", "racecar", "car"});
        Assert.assertEquals("", result);

        String newResult = solution.newLongestCommonPrefix(new String[]{"dog", "racecar", "car"});
        Assert.assertEquals("", newResult);
    }

    @Test
    public void shouldReturnBlank_whenGetLongestCommonPrefix_givenBlank() {
        String result = solution.longestCommonPrefix(new String[]{""});
        Assert.assertEquals("", result);

        String newResult = solution.newLongestCommonPrefix(new String[]{""});
        Assert.assertEquals("", newResult);
    }

    @Test
    public void shouldReturnA_whenGetLongestCommonPrefix_givenA() {
        String result = solution.longestCommonPrefix(new String[]{"a"});
        Assert.assertEquals("a", result);
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [报数]
     */
    @Test
    public void shouldReturn111221_given5() {
        String result = solution.countAndSay(5);
        Assert.assertEquals("111221", result);
    }

    @Test
    public void shouldReturn1211_given4() {
        String result = solution.countAndSay(4);
        Assert.assertEquals("1211", result);
    }


    @Test
    public void shouldReturn11_given1() {
        String result = solution.countAndSay(1);
        Assert.assertEquals("1", result);
    }

    @Test
    public void shouldCut111_whenCutFirstSameDigit_given11122() {
        Pair<String, String> resultAndLeftString = solution.cutFirstSameDigit("11122");
        Assert.assertEquals("31", resultAndLeftString.getKey());
        Assert.assertEquals("22", resultAndLeftString.getValue());

    }

    @Test
    public void shouldSay3122_whenSayDigit_given11122() {
        Assert.assertEquals("3122", solution.say("11122"));
    }

    /**
     * _____________________________________________________________________________________________
     * below is tests for [实现 strStr()]
     */
    @Test
    public void shouldReturn2_whenInput_ll_givenHello() {
        //given
        String haystack = "hello";

        //when
        String needle = "ll";

        //then
        int result = solution.strStr(haystack, needle);
        Assert.assertEquals(2, result);
    }


    @Test
    public void shouldReturnNegativeOne_whenInput_bba_givenAaaaa() {
        //given
        String haystack = "aaaaa";

        //when
        String needle = "bba";

        //then
        int result = solution.strStr(haystack, needle);
        Assert.assertEquals(-1, result);
    }

    @Test
    public void shouldReturn0_whenInput_a_given_a() {
        //given
        String haystack = "a";

        //when
        String needle = "a";

        //then
        int result = solution.strStr(haystack, needle);
        Assert.assertEquals(0, result);
    }


}
