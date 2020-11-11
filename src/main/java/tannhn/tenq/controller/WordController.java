package tannhn.tenq.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tannhn.tenq.entity.*;
import tannhn.tenq.service.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping( value ="/api/v1/word")
public class WordController {
	
	public static final Logger logger = LoggerFactory.getLogger(WordController.class);
	
	@Autowired
	WordService wordService;
	
	@GetMapping("/list")
	public ResponseEntity<List<WordEntity>> loadAllWord() {
		logger.info("Loading all word");
		List<WordEntity> lsWord = wordService.loadAllWord();
		return new ResponseEntity<List<WordEntity>>(lsWord, HttpStatus.OK);
	}
	
	@GetMapping("/listbycat/{catId}")
	public ResponseEntity<List<WordEntity>> loadAllWordByCategory(@PathVariable String catId) {
		logger.info("Loading all word by category");
		List<WordEntity> lsWord = wordService.loadWordByCategory(Integer.parseInt(catId));
		return new ResponseEntity<List<WordEntity>>(lsWord, HttpStatus.OK);
	}
	
	@GetMapping("/listbytag/{tagId}")
	public ResponseEntity<List<WordEntity>> loadAllWordByTag(@PathVariable String tagId) {
		logger.info("Loading all word by category");
		List<WordEntity> lsWord = wordService.loadWordByTag(Integer.parseInt(tagId));
		return new ResponseEntity<List<WordEntity>>(lsWord, HttpStatus.OK);
	}
	
	@GetMapping("/listbydate/{date}")
	public ResponseEntity<List<WordEntity>> loadAllWordByDate(@PathVariable String date) {
		logger.info("Loading all word by created date");
		List<WordEntity> lsWord = wordService.loadWordByCreateDate(date);
		return new ResponseEntity<List<WordEntity>>(lsWord, HttpStatus.OK);
	}
	
	@GetMapping("/get/{idWord}")
	public ResponseEntity<?> getWordById(@PathVariable String idWord) {
		logger.info("Loading word by id");
		WordEntity word = wordService.findWordById(Integer.parseInt(idWord)).get();
		return new ResponseEntity<WordEntity>(word, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createWord(@RequestBody WordEntity word) {
		logger.info("Create Word");
		if (wordService.findWordByName(word.getName()) != null) {
			return new ResponseEntity<String>("duplicated", HttpStatus.OK);
		}
		Set<TagEntity> tagLs = new HashSet<>();
		word.getTags().forEach(tag -> {
			if (wordService.findTagByName(tag.getName()) != null) {
				tagLs.add(wordService.findTagByName(tag.getName()));
			}
			else {
				tagLs.add(tag);
			}
		});
		word.setTags(tagLs);
		wordService.saveWord(word); 
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateWord(@RequestBody WordEntity word) {
		logger.info("Update Word");
		if (wordService.findWordByName(word.getName()) != null) {
			return new ResponseEntity<String>("duplicated", HttpStatus.OK);
		}
		Set<TagEntity> tagLs = new HashSet<>();
		word.getTags().forEach(tag -> {
			if (wordService.findTagByName(tag.getName()) != null) {
				tagLs.add(wordService.findTagByName(tag.getName()));
			}
			else {
				tagLs.add(tag);
			}
		});
		word.setTags(tagLs);
		wordService.saveWord(word); 
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteWords(@RequestBody List<Integer> ids) {
		logger.info("Delete Words");
		wordService.deleteWords(ids); 
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public ResponseEntity<List<WordEntity>> findWordByDate() {
		List<WordEntity> wordLs = wordService.loadWordByCreateDate("2020-11-15");
		return new ResponseEntity<List<WordEntity>>(wordLs, HttpStatus.OK);
	}
	
	@GetMapping("/category/list")
	public ResponseEntity<List<CategoryEntity>> loadAllCategory() {
		logger.info("Loading all category");
		List<CategoryEntity> lsCat = wordService.loadAllCategory();
		return new ResponseEntity<List<CategoryEntity>>(lsCat, HttpStatus.OK);
	}
	
	@PostMapping("/category/save")
	public ResponseEntity<String> saveCategory(@RequestBody CategoryEntity cat) {
		logger.info("Delete Categories");
		if( cat.getId() == null && wordService.findCatByName(cat.getName()) != null) {
			return new ResponseEntity<String>("duplicated", HttpStatus.OK); 
		}
		wordService.saveCategory(cat);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@PostMapping("/category/delete")
	public ResponseEntity<String> deleteCategories(@RequestBody List<Integer> ids) {
		logger.info("Delete Categories");
		wordService.deleteCategories(ids);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@GetMapping("/tag/list")
	public ResponseEntity<List<TagEntity>> loadAllTag() {
		logger.info("Loading all tag");
		List<TagEntity> lsTag = wordService.loadAllTag();
		return new ResponseEntity<List<TagEntity>>(lsTag, HttpStatus.OK);
	}
	
	@PostMapping("/tag/delete")
	public ResponseEntity<String> deleteTags(@RequestBody List<Integer> ids) {
		logger.info("Delete Tags");
		wordService.deleteTags(ids);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
}
