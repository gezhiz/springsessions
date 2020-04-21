package com.worthto.definition;

import java.beans.PropertyEditorSupport;

/**
 * @author gezz
 * @description
 * @date 2020/4/21.
 */
public class BeanInfoDemo {




    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }

    }
}
