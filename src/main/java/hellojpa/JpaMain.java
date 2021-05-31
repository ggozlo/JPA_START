package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 영속성  컨텍스트 팩토리는 프로그램 전반에서 사용

        EntityManager entityManager = emf.createEntityManager();
        // 영속성 컨텍스트는 해당 작업에서만 사용

        EntityTransaction transaction = entityManager.getTransaction();
        // 영속성, 지속성 , 독립성, 원자성

        transaction.begin();
        try {
//            Member findMember = entityManager.find(Member.class, 1L); //검색

//            List<Member> memberList = entityManager.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(0).setMaxResults(1).getResultList(); //jpql

//            entityManager.remove(findMember);  //삭제
//            findMember.setName("JPA"); //수정
//            entityManager.persist(member); //저장

            //비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");

            //영속
//            entityManager.persist(member);

            //준영속
//           entityManager.detach(member); //영속성 컨텍스트에서 제거
//            entityManager.remove(member); //db에서 제거


//            Member member1 = entityManager.find(Member.class, 101L);
//            Member member2 = entityManager.find(Member.class, 101L);
//            System.out.println(member1 == member2); // 영속성 컨텍스트는 동일성 보장

//            Member member1 =new Member(150L, "A");
//            Member findMember = entityManager.find(Member.class, 150L);
//            findMember.setName("ZZZZZ"); // 영속상태의 엔티티는 커밋단계에서 변화를 감지하여 update 쿼리 생성

//            Member member = new Member(150L, "member200");
//            entityManager.persist(member);
//            entityManager.flush(); // sql 즉시 반영

//            Team team = new Team();
//            team.setName("TeamA");
//            entityManager.persist(team);
//            Member member = new Member();
//            member.setUsername("memberA");
//            member.setTeam(team);
//            entityManager.persist(member);
//            // 양방향 연관관계의 거울 이더라도 영속성 컨텍스트의 1차 캐시에서는 값이 자동으로 들어가는 것이
//            // 아니기 때문에 객체지향적인 설계를 위해 양쪽에 서로 넣어주는것이 옳다.

//            entityManager.clear(); // 영속성 컨텍스트 초기화

//            Member findMember = entityManager.find(Member.class, member.getId());
//            List<Member> members = findMember.getTeam().getMembers();
//            Team team1 = entityManager.find(Team.class, team.getId());
//            List<Member> members = team1.getMembers();
//            양방향 연관관계 에서의 호출

            // 프록시 - reference 를 엔티티매니저 로 부터 받으면 탐색대상을 상속받은 프록시 객체를 받는다
            // 프록시 객체는 원본을 필드값으로 가져서 원본 객체처럼 사용하면 프록시 객체가 그에 맞게 연결해 준다.
            // 원본 객체가 1차 캐시에 있다면 동일성을 유지하기 위해 레퍼런스를 호출해도 원본 객체를 반환한다
            // 반대로 프록시 객체가 먼저 1차 캐시에 잇다면 원본객체를 받아도 프록시 객체로 받는다
            // 프록시 객체는 영속상태가 풀리거나 초기화 되거나 영속성 컨텍스트가 종료되면 초기화 불가능으로 기능하지 않는다.

//            Member proxyMember = entityManager.getReference(Member.class, member1.getId());
//            //프록시 객체를 생성하는 getReference 메서드, 내용물이 필요할때 초기화 하기 위해 쿼리문을 보낸다
//            System.out.println("m1.getClass() = " + proxyMember.getClass().getName() );
//            // 프록시 클래스 확인 ( 어떤 객체의 프록시 인지 )
//            Hibernate.initialize(proxyMember);
//            // 강제 초기화 (JPA 표준에는 강제 초기화 없음)
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(proxyMember));
//            //프록시 객체의 초기화 상태를 확인해주는 PersistenceUnitUtil 의 isLoaded 메서드

//            List<Member> mebers = entityManager.createQuery("select m from Member m join fetch m.team").getResultList();
            // 즉시로딩, JPQL 사용시 N+1 문제로 연관 테이블 조회 쿼리를 다수 발생시킨다

            Parent parent = new Parent();

            Child child1 =new Child();
            Child child2 = new Child();

            parent.addChild(child1);
            parent.addChild(child2);

            entityManager.persist(parent);
            // casecade 옵션이 활성화 되어 parent를 영속화 하면 타겟인 child 객체들도 영속화됨

            entityManager.flush();
            entityManager.clear();

            Parent findParent = entityManager.find(Parent.class, parent.getId());
            //findParent.getChildList().remove(0);
            //entityManager.remove(findParent);
            // orphanRemoval 가 활성화 된 타겟은 영속성 컨텍스트에서 부모객체를 잃으면 DB로 delete 쿼리를 보냄


            System.out.println("======================");
            transaction.commit(); // 트랜잭션 커밋
        }
        catch (Exception e) {
            transaction.rollback(); // 오류 발생시 트랜잭션 롤백
            e.printStackTrace();
        } finally {
            entityManager.close(); // 영속성 컨텍스트 종료
        }
        emf.close(); // 영속성 컨텍스트 팩토리 종료



    }



}
