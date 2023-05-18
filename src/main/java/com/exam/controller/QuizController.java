package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.CategoryService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    // update quiz
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    // get all quizzes
    @GetMapping("/")
    public ResponseEntity<?> getQuizzes()
    {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    // get single quiz
    @GetMapping("/{qid}")
    public Quiz getQuiz(@PathVariable("qid") Long qid)
    {
        return this.quizService.getQuiz(qid);
    }

    // delete the quiz

    @DeleteMapping("/{qid}")
    public void deleteQuiz(@PathVariable("qid") Long qid)
    {
        this.quizService.deleteQuiz(qid);
    }

    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid){
        Category category = categoryService.getCategory(cid);
        return this.quizService.getQuizzesOfCategory(category);
    }

    // get Active quizzes
    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes(){
        return this.quizService.getActiveQuizzes();
    }

    // get active quizzes of category
    @GetMapping("category/active/{cid}")
    public List<Quiz> getActiveQuizzesOfCategory(@PathVariable("cid") Long cid){
        Category category = this.categoryService.getCategory(cid);
        return this.quizService.getActiveQuizzesOfCategory(category);
    }


}
