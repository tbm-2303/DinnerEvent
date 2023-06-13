package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "assignment")
@Entity
public class Assignment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "familyName")
    private String familyName;

    @Column(name = "created")
    private String created;

    @Column(name = "contactInfo")
    private String contactInfo;

    @ManyToMany
    @JoinTable(name = "Member_Assignment")
    private List<Member> members = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "event_id")
    private Event event;


    public Assignment() {
    }

    public Assignment(String familyName, String created, String contactInfo) {
        this.familyName = familyName;
        this.created = created;
        this.contactInfo = contactInfo;
    }

    public void addMember(Member member){
        this.members.add(member);
        member.getAssignments().add(this);
    }

    public void removeMember(Member member){
        this.members.remove(member);
        member.getAssignments().remove(this);
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFamilyName() {
        return familyName;
    }
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }
    public String getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
    public List<Member> getMembers() {
        return members;
    }
    public void setMembers(List<Member> members) {
        this.members = members;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
}