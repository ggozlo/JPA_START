package hellojpa;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
public class Member //extends BaseEntity
 {

    @Id // PK 선언
    @GeneratedValue(strategy = GenerationType.AUTO) // PK값 자동 생선 (숫자)
    @Column(name = "MEMBER_ID") // 컬럼명 지정
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne(fetch = FetchType.LAZY) // fetch 의 지연로딩 설정 - 대상 레퍼런스를 프록시로 가져옴으로써 값을 호출할때 실제 데이터를 가져옴
    // EAGER 는 관계된 모든 테이블을 즉시 가져옴
    @JoinColumn(name ="TEAM_ID") // FK 로써 TEAM 의 PK와 매핑 (연관관계의 주인 일때)
//    @JoinColumn(name = "TEAM_ID",insertable = false, updatable = false) // 양방향 연관관계의 거울로 지정하고 싶을때
    private Team team; //연관관계의 주인은 FK를 가지는 엔티티로 잡는것이 설계상 깔끔하다.

    @OneToOne // 일대일 연관관계
    @JoinColumn(name = "LOCKER_ID") // 사용자애 FK 생성
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    @Embedded // 내장객체 - 필드쪽에
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    @ElementCollection // 컬렉션 원자 어노테이션
    @CollectionTable(
            name ="FAVORATE_FOOD",
            joinColumns = @JoinColumn(name = "MEMBER_ID")
    ) // 컬렉션을 지정한 이름의 테이블로 분리하는 어노테이션, 조인될 컬럼명을 지정
    @Column(name = "FOOD_NAME") // 예외적으로 컬렉션 값타입은 column 어노테이션으로 내용 컬럼명을 수정
    private Set<String> favoriteFoods = new HashSet<>();

//    @ElementCollection
//    @CollectionTable(
//            name ="ADDRESS",
//            joinColumns = @JoinColumn(name = "MEMBER_ID")) // 탐색이 어려워서 좋은 방법이 아님
     @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
     @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();

//     @Embedded
//     @AttributeOverrides({
//             @AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
//             @AttributeOverride(name = "street", column = @Column(name = "STREET_CITY")),
//             @AttributeOverride(name = "zipcode", column = @Column(name = "ZIP_CITY"))
//     })
//     private Address workAddress;


     public Period getWorkPeriod() {
         return workPeriod;
     }

     public void setWorkPeriod(Period workPeriod) {
         this.workPeriod = workPeriod;
     }

     public Address getHomeAddress() {
         return homeAddress;
     }

     public void setHomeAddress(Address homeAddress) {
         this.homeAddress = homeAddress;
     }

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

     public Set<String> getFavoriteFoods() {
         return favoriteFoods;
     }

     public void setFavoriteFoods(Set<String> favoriteFoods) {
         this.favoriteFoods = favoriteFoods;
     }

     public List<AddressEntity> getAddressHistory() {
         return addressHistory;
     }

     public void setAddressHistory(List<AddressEntity> addressHistory) {
         this.addressHistory = addressHistory;
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
