package com.philco;

import java.util.Calendar;
import java.util.Date;

public final class DateProvider {
    private static DateProvider dateProvider = null;

    public static final DateProvider getInstance() {
        if (dateProvider != null)
            dateProvider = new DateProvider();
        return dateProvider;
    }

    public final Date now() {
        return Calendar.getInstance().getTime();
    }
}
