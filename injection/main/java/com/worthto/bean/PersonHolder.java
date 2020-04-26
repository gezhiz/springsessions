package com.worthto.bean;

/**
 * @author gezz
 * @description
 * @date 2020/4/26.
 */
public class PersonHolder {

    private Person person;

    public PersonHolder() {
    }

    public PersonHolder(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "PersonHolder{" +
                "person=" + person +
                '}';
    }
}
