package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team") // 양방향 연관관계의 거울로 지정
 //   @JoinColumn(name = "TEAM_ID") // 양방향 연관관계의 주인 설정
    private List<Member> members = new ArrayList<>();

    public void memberSet(Member member) {
//        member.setTeam(this);
        members.add(member);
    } // Team 측 양방향 연관관계를 지정한는 메서드


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

}
