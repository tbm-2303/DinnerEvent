package dtos;

import entities.Assignment;
import entities.Member;

import java.util.ArrayList;
import java.util.List;

public class AssignmentDTO {
    private Long id;
    private String familyName;
    private String created;
    private String contactInfo;


    private List<MemberDTO> members = new ArrayList<>();
    private List<Long> memberIds = new ArrayList<>();

    private EventDTO event;
    private Long EventId;


    public AssignmentDTO(Assignment assignment) {
        if (assignment.getId() != null) {
            this.id = assignment.getId();
        }
        this.familyName = assignment.getFamilyName();
        this.created = assignment.getCreated();
        this.contactInfo = assignment.getContactInfo();
        if (assignment.getMembers() != null) {
            for (Member m : assignment.getMembers()) {
                this.members.add(new MemberDTO(m));
            }
        }
        if (assignment.getEvent() != null) {
            this.event = new EventDTO(assignment.getEvent());
        }
    }


    public AssignmentDTO(String familyName, String created, String contactInfo) {
        this.familyName = familyName;
        this.created = created;
        this.contactInfo = contactInfo;
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
    public List<MemberDTO> getMembers() {
        return members;
    }
    public void setMembers(List<MemberDTO> members) {
        this.members = members;
    }
    public List<Long> getMemberIds() {
        return memberIds;
    }
    public void setMemberIds(List<Long> memberIds) {
        this.memberIds = memberIds;
    }
    public EventDTO getEvent() {
        return event;
    }
    public void setEvent(EventDTO event) {
        this.event = event;
    }
    public Long getEventId() {
        return EventId;
    }
    public void setEventId(Long eventId) {
        EventId = eventId;
    }
}
