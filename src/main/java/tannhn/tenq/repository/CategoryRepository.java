package tannhn.tenq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tannhn.tenq.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
	CategoryEntity findByName(String name);
	void deleteByIdIn(List<Integer> ids);
}
