package hellojpa;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // 엔티티 선언
//@Table(name = "hello") // 테이블명 지정
//@SequenceGenerator( // 시퀀스 생성
//        name = "member_seq_generator",
//        sequenceName = "member_seq",
//        initialValue = 1, allocationSize = 50 // 버퍼링, 데이터베이스에서 50까지의 값을 미리 가져와 메모리에서 할당
//)
//@TableGenerator( // 증가값 테이블 생성
//        name = "member_seq_generator",
//        table = "my_seq",
//        pkColumnValue = "member_seq",allocationSize = 1
//)
public class Member extends BaseEntity {

    @Id // PK 선언
    @GeneratedValue(strategy = GenerationType.AUTO) // PK값 자동 생선 (숫자)
    @Column(name = "MEMBER_ID") // 컬럼명 지정
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name ="TEAM_ID") // FK 로써 TEAM 의 PK와 매핑 (연관관계의 주인 일때)
//    @JoinColumn(name = "TEAM_ID",insertable = false, updatable = false) // 양방향 연관관계의 거울로 지정하고 싶을때
    private Team team; //연관관계의 주인은 FK를 가지는 엔티티로 잡는것이 설계상 깔끔하다.

    @OneToOne // 일대일 연관관계
    @JoinColumn(name = "LOCKER_ID") // 사용자애 FK 생성
    private Locker locker;


    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public List<MemberProduct> getMemberProducts() {
        return memberProducts;
    }

    public void setMemberProducts(List<MemberProduct> memberProducts) {
        this.memberProducts = memberProducts;
    }

    public void teamSet(Team team) {
//        this.team = team;
        team.getMembers().add(this);
        // Member측 연관관계를 간단히 맺기위해 둘이 동시에 설정해주는 메서드.
    }



//    @Enumerated(EnumType.STRING) // 열거형
//    private RoleType roleType;

//    @Temporal(TemporalType.TIMESTAMP) // 시간 데이터
//    private Date createDate;


//    @Lob // 큰 데이터
//    private String description;

//    @Transient // 테이블에 삽입 안함
//    private int temp;
//

}
