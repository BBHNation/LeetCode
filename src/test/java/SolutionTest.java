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
