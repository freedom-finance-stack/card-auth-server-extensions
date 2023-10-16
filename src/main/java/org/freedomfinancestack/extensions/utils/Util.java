package org.freedomfinancestack.extensions.utils;

import java.sql.Timestamp;

public class Util {
    public static Timestamp getCurrentTimestamp() {
        long currentTimeMillis = System.currentTimeMillis();

        // Create a Timestamp object using the current time
        return new Timestamp(currentTimeMillis);
    }

    /**
     * Checks if the given object is null or its string representation is blank.
     *
     * @param object the object to check
     * @return {@code true} if the object is null or its string representation is blank, {@code
     *     false} otherwise
     */
    public static boolean isNullorBlank(Object object) {
        if (null == object) {
            return true;
        } else return "".equals(object.toString());
    }
}
