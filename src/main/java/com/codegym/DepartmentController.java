package com.codegym;

import com.codegym.model.Department;
import com.codegym.service.ICRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private ICRUDService<Department,Long>icrudService;
    @GetMapping
    public ModelAndView listClasses(@PageableDefault(size = 3)Pageable pageable,
                                    @RequestParam("search") Optional<String> name){
        ModelAndView modelAndView=new ModelAndView("department/list");
        if (!name.isPresent()){
            modelAndView.addObject("departments",icrudService.findAll(pageable));
        }else {
            modelAndView.addObject("departments",icrudService.findByName(name.get(),pageable));
            modelAndView.addObject("search", name.get());
        }return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("department/form");
        modelAndView.addObject("department", new Department());
        return modelAndView;
    }
    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("department/form");
        Optional<Department> classes = Optional.ofNullable(icrudService.findById(id));
        if (classes.isPresent()) {
            modelAndView.addObject("department", classes.get());
        } else {
            return new ModelAndView("404");
        }
        return modelAndView;
    }
    @PostMapping("/save")
    public String save(@ModelAttribute Department department) {
        icrudService.save(department);
        return "redirect:/departments";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        icrudService.delete(id);
        return "redirect:/departments";
    }


}
