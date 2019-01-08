package com.sherlockcodes.libraryDemo.common;

import java.util.Collections;
import java.util.List;

public class Utils {
    private static final Long[] EMPTY = {};

    public static <T> List<T> safe(List<T> iterable) {
        return iterable == null ? Collections.emptyList() : iterable;
    }

    public static Long[] safe(Long[] arr) {
        return arr == null ? EMPTY : arr;
    }
}
