package model;

import java.io.Serializable;

public class Contact implements Serializable {
        private String numberPhone;
        private String group;
        private String fullName;
        private String gender;
        private String address;
        private String dateOfBirth;
        private String Email;

    public Contact() {
    }

    public Contact(String group, String fullName, String gender, String address, String dateOfBirth, String email) {
        this.group = group;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        Email = email;
    }

    public Contact(String numberPhone, String group, String fullName, String gender, String address, String dateOfBirth, String email) {
        this.numberPhone = numberPhone;
        this.group = group;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        Email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return  "Số điện thoại : '" + numberPhone + '\'' +
                ", Nhóm : '" + group + '\'' +
                ", Tên : '" + fullName + '\'' +
                ", Giới tính : '" + gender + '\'' +
                ", Địa chỉ : '" + address + '\'' +
                ", Ngày sinh : '" + dateOfBirth + '\'' +
                ", Email : '" + Email + '\'';
    }
}
