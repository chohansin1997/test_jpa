package jpa_test.demo.Controller;

import jpa_test.demo.DTO.Member;
import jpa_test.demo.Repository.JPA_EntityManager;
import jpa_test.demo.Repository.Member_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import java.util.List;

@Controller
public class Home_Controller {
    @Autowired
    public void setMember_repository(Member_Repository member_repository) {this.member_repository = member_repository;  }
    @Autowired
    public void setJpaEntityManager(JPA_EntityManager jpaEntityManager) { this.jpaEntityManager = jpaEntityManager;   }

    JPA_EntityManager jpaEntityManager;
    Member_Repository member_repository;


    @GetMapping(value = "")
    @Transactional
    public String go_home() {

        Member member = new Member();
        member.setName("han");
        member.setAge(11);
        member_repository.save(member);// 하이버네이트에서 만들어준거
        // 여기서 save를 했지만 jpa는 쓰기 지연을 사용하므로 아래 쪽에 있는 hansin 값이 저장된다
        System.out.println(member_repository.findAll());


        member.setName("hansin");
        member.setAge(111);

        for (int i=0 ;i<30; i++) {
            Member member1 = new Member(); // jpa 는 쓰기 지연때문에 member.setName("han"+i);로 하면 그냥 마지막걸로 집어넣어진다 새로운것을 하나하나 만들려면 각각의 엔티티를 생성해 줘야한다
                                           // , 쓰기 지연이 안되도 같은 객체의 save는 insert 취급이 아니라
            member1.setAge(10+i);         //update 취급을 받는다 중요 ⭐⭐⭐
            member1.setName("han"+i);
            System.out.println(i);

            member_repository.save(member1);// 하이버네이트에서 만들어준거

        }


        jpaEntityManager.Save(member); // 사용자가 만든것

        System.out.println(jpaEntityManager.find_all());


        System.out.println(jpaEntityManager.find_name_and_older_age("hansin",11)); //이름이 한신이고 나이가 11살보다 늙은 member
        //엔티티매니저로 직접 쿼리 생성

        System.out.println(member_repository.findByNameAndAgeGreaterThan("hansin",11));
        // 메소드 이름으로 자동으로 만들어준것

//마스터랑 겹치는 브랜치 ~

        //git test용 주석


// 여긴 어디고 ~
//45422
        return "index";
    }
}
