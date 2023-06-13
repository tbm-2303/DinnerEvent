package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "birthYear")
    private int birthYear;

    @Column(name = "account")
    private int account;

    @OneToOne()
    @JoinColumn(name = "username")
    private User user;

    @ManyToMany(mappedBy = "members")
    private List<Assignment> assignments  = new ArrayList<>();





    public Member() {
    }

    public Member(String address, String phone, String email, int birthYear, int account) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.birthYear = birthYear;
        this.account = account;
    }


    public void addUser(User u) {
        this.user = u;
        u.setMember(this);
    }

    public void removeUser(User u) {
        this.user = null;
        u.setMember(null);
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Assignment> getAssignments() {
        return assignments;
    }
    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }


}