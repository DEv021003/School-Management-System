package com.example.sis.controller;

import com.example.sis.model.Score;
import com.example.sis.model.Student;
import com.example.sis.model.Subject;
import com.example.sis.repository.ScoreRepository;
import com.example.sis.repository.StudentRepository;
import com.example.sis.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        return "addScore";
    }

    @PostMapping("/add")
    public String addScore(@RequestParam Long studentId,
                           @RequestParam Long subjectId,
                           @RequestParam double score1,
                           @RequestParam double score2) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();

        Score score = new Score();
        score.setStudent(student);
        score.setSubject(subject);
        score.setScore1(score1);
        score.setScore2(score2);
        scoreRepository.save(score);
        return "redirect:/scores/list";
    }

    @GetMapping("/list")
    public String listScores(Model model) {
        model.addAttribute("scores", scoreRepository.findAll());
        return "scoreList";
    }
}
