package com.worthto.definition.bean;

import com.worthto.definition.annotation.Super;

/**
 * @author gezz
 * @description
 * @date 2020/4/21.
 */
@Super
public class SuperMan extends Person {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "SuperMan{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
