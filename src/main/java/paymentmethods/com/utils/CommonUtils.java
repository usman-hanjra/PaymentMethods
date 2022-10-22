package paymentmethods.com.utils;


import java.util.Collection;

public class CommonUtils {
    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection coll) {
        return !isEmpty(coll);
    }

    public static boolean isNotBlank(Object object) {
        if (object == null) {
            return false;
        }
        String objString;
        if (object instanceof String) {
            objString = object.toString();
            if (objString.trim().length() <= 0) {
                return false;
            }
        }
        return true;
    }
}
