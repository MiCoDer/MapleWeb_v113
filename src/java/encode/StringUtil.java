package encode;

public class StringUtil {

    public static String getLeftPaddedStr(String in, char padchar, int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int x = in.getBytes().length; x < length; x++) {
            builder.append(padchar);
        }
        builder.append(in);
        return builder.toString();
    }
}
