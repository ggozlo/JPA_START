package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();


        transaction.begin();
        try {
//            Member findMember = entityManager.find(Member.class, 1L); //검색
            
//            List<Member> memberList = entityManager.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(0).setMaxResults(1).getResultList(); //jpql
//            for (Member member : memberList) {
//                System.out.println("member.getName() = " + member.getName());
//            }
            
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
//            System.out.println(member1 == member2);

//            Member member1 =new Member(150L, "A");
//            Member member2 =new Member(160L, "B");
//            entityManager.persist(member1);
//            entityManager.persist(member2);
//            System.out.println("======================");

//            Member findMember = entityManager.find(Member.class, 150L);
//            findMember.setName("ZZZZZ");
//            System.out.println("======================");

//            Member member = new Member(150L, "member200");
//            entityManager.persist(member);
//
//            entityManager.flush();
//
//            entityManager.detach(member);
//            member.setRoleType(RoleType.GUEST);

            Member member1 = new Member();
            member1.setUsername("A");
            Member member2 = new Member();
            member2.setUsername("B");
            Member member3 = new Member();
            member3.setUsername("C");

            entityManager.persist(member1);
            entityManager.persist(member2);
            entityManager.persist(member3);

            System.out.println("member1.getId() = " + member1.getId());
            System.out.println("member2.getId() = " + member2.getId());
            System.out.println("member3.getId() = " + member3.getId());

            System.out.println("======================");
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();

        }
        emf.close();



    }
}
