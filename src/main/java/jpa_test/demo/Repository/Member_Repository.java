package jpa_test.demo.Repository;

import jpa_test.demo.DTO.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

//엔티티         pk값 유형
@Transactional
public interface Member_Repository extends JpaRepository<Member, Long> {

    //JpaRepository 에서는 메소드의 이름으로 쿼리문을 구현할수 있다 편리 ⭐⭐ (쿼리메소드 기능)
    List<Member> findByNameAndAgeGreaterThan(String name, int age);
    //메소드 이름 순서대로 변수를 정해야 한다 List<Member> findByNameAndAgeGreaterThan(int age,String name);으로 순서를 변경하면 오류뜸


}
