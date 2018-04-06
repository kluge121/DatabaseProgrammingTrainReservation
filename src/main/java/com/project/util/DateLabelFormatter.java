package com.project.util;

import javax.swing.JFormattedTextField.AbstractFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern, Locale.ENGLISH);

    public Object stringToValue(String text) throws ParseException {

        return dateFormatter.parseObject(text);

    }

    public String valueToString(Object value) throws ParseException {

        if (value != null) {

            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());

        }

        return "";

    }




}
