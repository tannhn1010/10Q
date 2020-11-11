package tannhn.tenq.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import tannhn.tenq.entity.CategoryEntity;
import tannhn.tenq.entity.TagEntity;
import tannhn.tenq.entity.WordEntity;

public interface WordService {
	
	List<WordEntity> loadAllWord();
	
	List<WordEntity> loadWordByCategory(int id);
	
	List<WordEntity> loadWordByTag(int id);
	
	List<WordEntity> loadWordByCreateDate(String date);
	
	Optional<WordEntity> findWordById(int id);
	
	WordEntity findWordByName(String name);
	
	void saveWord(WordEntity word);
	
	void deleteWords(List<Integer> ids);
	
	List<CategoryEntity> loadAllCategory();
	
	CategoryEntity findCatByName(String name);
	
	void saveCategory(CategoryEntity cat);
	
	void deleteCategories(List<Integer> ids);
	
	List<TagEntity> loadAllTag();
	
	TagEntity findTagByName(String name);
	
	void saveTag(TagEntity tag);
	
	void deleteTags(List<Integer> ids);
}
