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
            indexOfPiece < haystack.length() - lengthOfNeedle+1;
            indexOfPiece++) {
            String pieceString = haystack.substring(indexOfPiece, indexOfPiece+lengthOfNeedle);
            if (pieceString.hashCode() == needle.hashCode()) {
                return indexOfPiece;
            }
        }
        return -1;
    }
}
