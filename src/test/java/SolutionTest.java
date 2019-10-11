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
