package com.example.sis.controller;

import com.example.sis.model.Student;
import com.example.sis.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        return "addStudent";
    }

    @PostMapping("/add")
    public String addStudent(@RequestParam String studentCode,
                             @RequestParam String fullName,
                             @RequestParam String address) {
        Student student = new Student();
        student.setStudentCode(studentCode);
        student.setFullName(fullName);
        student.setAddress(address);
        studentRepository.save(student);
        return "redirect:/students/list";
    }

    @GetMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "studentList"; // Trả về tên của tệp view
    }
}