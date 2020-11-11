package tannhn.tenq.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tannhn.tenq.entity.WordEntity;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Integer> {
	List<WordEntity> findAllByCategoryId(int id);
	List<WordEntity> findAllByTagsId(int id);
	WordEntity findByName(String name);
	void deleteByIdIn(List<Integer> ids);
	
	@Query(value = "SELECT * FROM word WHERE create_date LIKE CONCAT(:date,'%')", nativeQuery = true)
	List<WordEntity> findAllByDate(@Param("date") String date);
}
