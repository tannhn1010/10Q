package tannhn.tenq.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tannhn.tenq.entity.CategoryEntity;
import tannhn.tenq.entity.TagEntity;
import tannhn.tenq.entity.WordEntity;
import tannhn.tenq.repository.CategoryRepository;
import tannhn.tenq.repository.TagRepository;
import tannhn.tenq.repository.WordRepository;

@Service
@Transactional
public class WordServiceImpl implements WordService {
	
	@Autowired
	WordRepository wordRepo;

	@Autowired
	CategoryRepository catRepo;
	
	@Autowired
	TagRepository tagRepo;

	@Override
	public List<WordEntity> loadAllWord() {
		return wordRepo.findAll();
	}

	@Override
	public List<WordEntity> loadWordByCategory(int id) {
		return wordRepo.findAllByCategoryId(id);
	}

	@Override
	public List<WordEntity> loadWordByTag(int id) {
		return wordRepo.findAllByTagsId(id);
	}
	
	@Override
	public List<WordEntity> loadWordByCreateDate(String date) {
		return wordRepo.findAllByDate(date);
	}

	@Override
	public Optional<WordEntity> findWordById(int id) {
		return wordRepo.findById(id);
	}
	
	@Override
	public WordEntity findWordByName(String name) {
		return wordRepo.findByName(name);
	}
	
	@Override
	public void saveWord(WordEntity word) {
		wordRepo.save(word);
	}

	@Override
	public void deleteWords(List<Integer> ids) {
		wordRepo.deleteByIdIn(ids);
	}

	@Override
	public List<CategoryEntity> loadAllCategory() {
		return catRepo.findAll();
	}

	@Override
	public void saveCategory(CategoryEntity cat) {
		catRepo.save(cat);
	}

	@Override
	public void deleteCategories(List<Integer> ids) {
		catRepo.deleteByIdIn(ids);
	}

	@Override
	public List<TagEntity> loadAllTag() {
		return tagRepo.findAll();
	}

	@Override
	public void saveTag(TagEntity tag) {
		tagRepo.save(tag);
	}

	@Override
	public void deleteTags(List<Integer> ids) {
		tagRepo.deleteByIdIn(ids);
	}

	@Override
	public CategoryEntity findCatByName(String name) {
		return catRepo.findByName(name);
	}

	@Override
	public TagEntity findTagByName(String name) {
		return tagRepo.findByName(name);
	}

}
