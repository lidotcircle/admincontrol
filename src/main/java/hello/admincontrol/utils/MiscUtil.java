package hello.admincontrol.utils;


/**
 * 杂七杂八的方法
 */
public class MiscUtil {
    private static String one2nine = "123456789";

    /**
     * 随机生成 n 位的数字字符串
     * @param n 字符串位数
     * @return  n位随机字符串
     */
    public static String generateDigitString(int n) //{
    {
        String ans = "";
        for(;n>0;n--) {
            final int idx = (int)Math.floor(Math.random() * 100) % 9;
            ans += one2nine.charAt(idx);
        }

        return ans;
    } //}
}

