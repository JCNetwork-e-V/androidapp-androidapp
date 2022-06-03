package com.jcnetwork.android.app1.utils;

public interface Formatter {
    /**
     * @param value value to format
     * @param index index of value in a list of values to be formatted
     * @return formatted value
     */
    CharSequence format(CharSequence value, int index);
}
