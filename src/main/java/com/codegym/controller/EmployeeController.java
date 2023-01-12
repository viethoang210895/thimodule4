package com.codegym.controller;

import com.codegym.model.Department;
import com.codegym.model.Employee;
import com.codegym.service.ICRUDService;
import com.codegym.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private ICRUDService<Employee, Long> icrudService;
    @Autowired
    private IDepartmentService iDepartmentService;
    @Value("${upload}")
    private String upload;

    @ModelAttribute("department")
    public List<Department> getClasses() {
        return iDepartmentService.findClass();
    }
    @GetMapping
    public ModelAndView listStudents(@PageableDefault(size = 3) Pageable pageable,
                                     @RequestParam("search") Optional<String> name) {
        ModelAndView modelAndView = new ModelAndView("employee/list");
        if (!name.isPresent()) {
            modelAndView.addObject("employees", icrudService.findAll( pageable));
        } else {
            modelAndView.addObject("employees", icrudService.findByName(name.get(), pageable));
            modelAndView.addObject("search", name.get());
        }
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("employee/form");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("employee/form");
        Optional<Employee> student = Optional.ofNullable(icrudService.findById(id));
        if (student.isPresent()) {
            modelAndView.addObject("employee", student.get());
        } else {
            return new ModelAndView("404");
        }
        return modelAndView;
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Employee employee) {

        icrudService.save(employee);
        return "redirect:/employees";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        icrudService.delete(id);
        return "redirect:/employees";
    }
    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("employee/detail");
        Optional<Employee> student = Optional.ofNullable(icrudService.findById(id));
        if (student.isPresent()) {
            modelAndView.addObject("employee", student.get());
        } else {
            return new ModelAndView("404");
        }
        return modelAndView;
    }

}
