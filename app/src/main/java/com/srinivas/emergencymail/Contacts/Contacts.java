package com.srinivas.emergencymail.Contacts;

public class Contacts {

    String name,phone;
    Boolean checked;

    public Contacts(String name, String phone,Boolean checked) {
        this.name = name;
        this.phone = phone;
        this.checked = checked;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
