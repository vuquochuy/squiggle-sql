package com.truemesh.squiggle.literal;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateTimeLiteral extends StringLiteral {
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss.S";

	public DateTimeLiteral(Date literalValue) {
        super(new SimpleDateFormat(FORMAT).format(literalValue));
	}
}