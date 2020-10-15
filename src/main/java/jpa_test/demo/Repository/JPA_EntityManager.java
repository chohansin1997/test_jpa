package jpa_test.demo.Repository;

import jpa_test.demo.DTO.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class JPA_EntityManager {

    @PersistenceContext
    EntityManager entityManager;



    public void Save(Member member){
//        entityManager.getTransaction().begin();

        entityManager.persist(member);

//        entityManager.getTransaction().commit();
        //entityManager.flush();

    }




    public List<Member> find_all(){

       return entityManager.createQuery("select m from Member m").getResultList();
    }


    public List<Member> find_name_and_older_age(  String name, int age) {
        return entityManager.createQuery("select m from Member m where m.name=:name and m.age>:age")
                .setParameter("name",name).setParameter("age",age).getResultList();
    }
}
