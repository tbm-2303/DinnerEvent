package dtos;

import entities.Member;

public class MemberDTO {

    private Long id;
    private String address;
    private String phone;
    private String email;
    private int birthYear;
    private int account;
    private String username;


    public MemberDTO(String address, String phone, String email, int birthYear, int account) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthYear = birthYear;
        this.account = account;

    }

    public MemberDTO(Member member) {
        if (member.getId() != null) {
            this.id = member.getId();
        }
        this.address = member.getAddress();
        this.phone = member.getPhone();
        this.email = member.getEmail();
        this.birthYear = member.getBirthYear();
        this.account = member.getAccount();
        this.username = member.getUser().getUserName();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
    public int getAccount() {
        return account;
    }
    public void setAccount(int account) {
        this.account = account;
    }
}
