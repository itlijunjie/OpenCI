package com.itlijunjie.openci.util;

import java.io.Serializable;

/**
 * Created by seba on 16/7/7.
 */
public class ValueTextDto implements Serializable {
    private Integer value;
    private String text;

    public ValueTextDto() {
    }

    public ValueTextDto(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ValueTextDto{" +
                "value=" + value +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValueTextDto that = (ValueTextDto) o;

        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return text != null ? text.equals(that.text) : that.text == null;

    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
