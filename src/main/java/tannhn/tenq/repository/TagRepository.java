package tannhn.tenq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tannhn.tenq.entity.TagEntity;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {
	TagEntity findByName(String name);
	void deleteByIdIn(List<Integer> ids);
}
