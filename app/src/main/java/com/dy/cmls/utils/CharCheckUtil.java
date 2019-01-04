package com.dy.cmls.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Demo 字符验证工具
 *
 * @author tangji
 * @date 2017/10/27
 */
public class CharCheckUtil {

    /**
     * 判断是否是数字
     */
    public static boolean isAllDigit(String str) {
        if (!"".equals(str) && str.length() > 0) {
            int len = 0;
            for (int idx = 0; idx < str.length(); idx++) {
                if (Character.isDigit(str.charAt(idx))) {
                    len++;
                }
            }
            if (len == str.length()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 时间格式，去-
     */
    public static String dateChange(String date) {
        StringBuilder sb = new StringBuilder();
        String[] dateStr = date.split("-");
        for (int i = 0; i < dateStr.length; i++) {
            sb.append(dateStr[i]);
        }
        return sb.toString();
    }

    /**
     * 判断是否是数字和"*"号组成的
     */
    public static boolean isJustDigitStar(String str) {
        int len = 0;
        for (int idx = 0; idx < str.length(); idx++) {
            if (Character.isDigit(str.charAt(idx)) || str.charAt(idx) == '.') {
                len++;
            }
        }
        if (len == str.length()) {
            return true;
        }
        return false;
    }

    /**
     * 区号+座机号码+分机号码
     */
    public static boolean isFixedPhone(String fixedPhone) {
        String reg = "(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|"
            + "(?:(86-?)"
            + ""
            + ""
            + ""
            + ""
            + ""
            + "?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
        return Pattern.matches(reg, fixedPhone);
    }

    /**
     * 判断是否是数字和"*"和“-”号组成的
     */
    public static boolean isJustDigitStar1(String str) {
        int len = 0;
        for (int idx = 0; idx < str.length(); idx++) {
            if (Character.isDigit(str.charAt(idx)) || str.charAt(idx) == '*' || str.charAt(idx) == '-') {
                len++;
            }
        }
        if (len == str.length()) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符长度
     */
    public static boolean allowMaxLenthOfString(String s, int charNum) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            String tmp = s.substring(i, i + 1);
            if (tmp.getBytes().length == 3) {
                num += 2;
            } else if (tmp.getBytes().length == 1) {
                num += 1;
            }
        }
        if (num <= charNum) {
            return true;
        }
        return false;
    }

    /**
     * 判断中英文字符长度
     */
    public static boolean checkStrType(String str) {
        boolean check = true;
        for (int i = 0; i < str.length(); i++) {
            String tmp = str.substring(i, i + 1);
            if (isChinese(tmp)) {
                check = true;
            } else if (isEnglish(tmp)) {
                check = true;
            } else {
                return false;
            }
        }
        return check;
    }

    private static String EMAILSTYLE = "^\\w+@\\w+\\.(com|cn)";

    /**
     * 邮箱验证
     */
    public static Boolean emailCheck(String str) {

        if (str.toString().length() > 0 && !str.matches(EMAILSTYLE)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 日期验证
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((("
            + ""
            + ""
            + ""
            + "(0?[13578])|(1[02]))[\\-\\/\\s]?("
            + "(0?[1-9])|([1-2][0-9])|(3[01])))|(("
            + ""
            + ""
            + ""
            + "(0?[469])|"
            + "(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?("
            + ""
            + ""
            + ""
            + "(0?[1-9])|"
            + ""
            + "([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))"
            + "[\\-\\/\\s]?((("
            + ""
            + "(0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])"
            + "|([1-2][0-9])|"
            + ""
            + "(3[01])))|(((0?[469])|"
            + "(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|"
            + ""
            + ""
            + ""
            + ""
            + "(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])"
            + "|"
            + "(2[0-8]))))))(\\s(((0?[0-9])|"
            + ""
            + ""
            + ""
            + ""
            + "([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:"
            + "([0-5]?[0-9]))"
            + ")))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 手机号码验证
     */
    public static boolean isPhoneNum(String phonenum) {
        String regExp = "^(1[2,3,4,5,6,7,8,9])\\d{9}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phonenum);
        return m.matches();
    }

    /**
     * 数字加字母密码验证
     */
    public static boolean isOkPwd(String pwd) {
        String regExp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(pwd);
        return m.matches();
    }

    /**
     * 身份证号码验证
     */
    public static boolean isIDNumber(String strID) {
        Pattern pattern = Pattern.compile("("
            + ""
            + ""
            + ""
            + ""
            + ""
            + ""
            + ""
            + ""
            + ""
            + ""
            + ""
            + ""
            + ""
            + "(11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54"
            + "|61|62|63|64|65"
            + "|71|81|82|91)\\d{4})((((19|20)(([02468][048])|([13579][26]))"
            + "0229)"
            + ""
            + ""
            + ""
            + ""
            + ""
            + ""
            + ")|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))("
            + ""
            + ""
            + "(0[1-9])|"
            + "(1\\d)"
            + ""
            + "|"
            + "(2[0-8]))"
            + ")|((((0[1,3-9])|(1[0-2]))(29|30))|("
            + ""
            + "("
            + "(0[13578])|(1[02])"
            + ")31))))("
            + "(\\d{3}"
            + ""
            + "(x|X))|(\\d{4}))");
        Matcher m = pattern.matcher(strID);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    private static Pattern ADDRESS = Pattern.compile("[\\u4e00-\\u9fa5]");

    /**
     * 详细地址验证
     */
    public static boolean isAddress(String address) {
        int i = 0, j = 0, k = 0, u = 0;
        int count = address.length();
        Matcher m = ADDRESS.matcher(address);
        while (m.find()) {
            i++;
        }
        for (int idx = 0; idx < count; idx++) {
            char c = address.charAt(idx);
            int tmp = (int) c;
            boolean isOk = (tmp >= 'a' && tmp <= 'z') || (tmp >= 'A' && tmp <= 'Z');
            if (isOk) {
                j++;
            }
            if (Character.isDigit(address.charAt(idx))) {
                k++;
            }
            if (c == ' ') {
                u++;
            }
        }
        if ((i + j + k + u) == count) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 除了“|”不能输和全部都是空格以外，其他的内容都可以通过
     */
    public static boolean isdouhao(String name) {
        int aa = name.indexOf("|");
        if (aa >= 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 是否有逗号
     */
    public static boolean isdouhao1(String name) {
        int aa = name.indexOf("|");
        int bb = name.indexOf(" ");
        if (aa >= 0 && bb >= 0) {
            return false;
        } else {
            return true;
        }
    }

    private static int NUMBER2 = 2;

    /**
     * 只能有一个“*”
     */
    public static boolean xinhao(String str) {

        String[] arrg = str.split("\\*");
        if (arrg.length > NUMBER2) {
            return false;
        } else {
            return true;
        }
    }

    private static Pattern CHINESE = Pattern.compile("[\\u4e00-\\u9fa5]");

    /**
     * 汉字验证
     */
    public static boolean isChinese(String name) {
        int j = 0;
        int i = name.length();
        Matcher m = CHINESE.matcher(name);
        while (m.find()) {
            j++;
        }
        if (i == j) {
            return true;
        } else {
            return false;
        }
    }

    private static Pattern ENGLISH = Pattern.compile("^[A-Za-z]+$");

    /**
     * 英文姓名验证
     */
    public static boolean isEnglish(String english) {
        Matcher m = ENGLISH.matcher(english);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    private static Pattern DATA = Pattern.compile("^[0-9]*$");

    /**
     * 数字验证
     */
    public static boolean isData(String number) {
        Matcher m = DATA.matcher(number);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    private static Pattern QUANSHILING = Pattern.compile("^0++$");

    /**
     * 不能全是000
     */
    public static boolean quanshiling(String number) {
        Matcher m = QUANSHILING.matcher(number);
        if (m.matches()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)) {
            return true;
        }

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    private static char CHARSS = 'N';

    /**
     * 判断是否是银行卡号
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == CHARSS) {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    private static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null
            || nonCheckCodeCardId.trim().length() == 0
            || !nonCheckCodeCardId.matches("\\d+")) {
            // 如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }
}
