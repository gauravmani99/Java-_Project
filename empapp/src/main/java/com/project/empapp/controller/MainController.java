package com.project.empapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.empapp.dto.EmployeeDto;
import com.project.empapp.models.Employee;
import com.project.empapp.service.EmployeeRepository;

@Controller
public class MainController {

    @Autowired
    EmployeeRepository erepo;

    @GetMapping("/")
    public String showIndex(Model model) {
        List<Employee> employees = erepo.findAll();
        model.addAttribute("employees", employees);
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        EmployeeDto eDto = new EmployeeDto();
        model.addAttribute("eDto", eDto);
        return "create";
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute EmployeeDto eDto) {
        Employee e = new Employee();
        e.setName(eDto.getName());
        e.setDepartment(eDto.getDepartment());
        e.setDesignation(eDto.getDesignation());
        e.setSalary(eDto.getSalary());
        erepo.save(e);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam int id) {
        Optional<Employee> e = erepo.findById(id);
        e.ifPresent(erepo::delete);
        return "redirect:/";
    }

    // ✅ Show the update form
    @GetMapping("/Update")
    public String showUpdateForm(@RequestParam("id") int id, Model model) {
        Optional<Employee> optionalEmployee = erepo.findById(id);
        if (optionalEmployee.isPresent()) {
            model.addAttribute("employee", optionalEmployee.get());
            return "update"; // Your Thymeleaf HTML form for update
        } else {
            return "redirect:/"; // Fallback if ID not found
        }
    }

    // ✅ Handle update form submission
    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") Employee employee) {
        erepo.save(employee); // This will update if ID is present
        return "redirect:/";
    }
}
