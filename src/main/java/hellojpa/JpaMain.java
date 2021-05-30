package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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


            Member member =new Member();
            member.setUsername("user1");
            member.setCreateBy("Kim");
            member.setCreateDate(LocalDateTime.now());

            entityManager.persist(member);

            Movie movie = new Movie();
            movie.setDirector("A");
            movie.setActor("B");
            movie.setName("바람과함께사라지다");
            movie.setPrice(10000);
            entityManager.persist(movie);

            entityManager.flush();
            entityManager.clear();

            Item findMovie = entityManager.find(Item.class, movie.getId());

            System.out.println("findMovie = " + findMovie);


            System.out.println("======================");
            transaction.commit(); // 트랜잭션 커밋
        }
        catch (Exception e) {
            transaction.rollback(); // 오류 발생시 트랜잭션 롤백
        } finally {
            entityManager.close(); // 영속성 컨텍스트 종료
        }
        emf.close(); // 영속성 컨텍스트 팩토리 종료



    }
}
