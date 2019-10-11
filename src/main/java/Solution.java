/**
 * Solution class is saving all the solution function for leetcode questions.
 */
public class Solution {

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
}
