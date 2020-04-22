package com.worthto.ioc.overview.definition;

import com.worthto.ioc.overview.bean.Person;
import org.junit.Test;

import java.beans.*;

/**
 * @author gezz
 * @description
 * @date 2020/4/21.
 */
public class BeanInfoDemo {


    @Test
    public void testBeanInfo() throws IntrospectionException {
        Person person = new Person();
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            Class<?> propertyType = descriptor.getPropertyType();
            String propertyName = descriptor.getName();
            if ("age".equals(propertyName)) {
                descriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);

                PropertyEditor propertyEditor = descriptor.createPropertyEditor(person);
                propertyEditor.setAsText("123");
            }
        }
        System.out.println(person.getAge());

    }


    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }

    }
}
