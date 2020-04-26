package com.worthto.bean;


/**
 * @author gezz
 * @description
 * @date 2020/4/21.
 */
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
