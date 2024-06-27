package bg.softuni.MVCDemo.controllers;

import bg.softuni.MVCDemo.services.CompanyService;
import bg.softuni.MVCDemo.services.EmployeeService;
import bg.softuni.MVCDemo.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ImportController {
    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @Autowired
    public ImportController(CompanyService companyService, EmployeeService employeeService,
                            ProjectService projectService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("import/xml")
    public String mainView(Model model) {
        boolean[] importedStatuses = {
                this.companyService.areImported(),
                this.projectService.areImported(),
                this.employeeService.areImported()
        };

        model.addAttribute("areImported", importedStatuses);
        return "xml/import-xml";
    }

    @GetMapping("import/companies")
    public String getImportCompanies(Model model) throws IOException {
        model.addAttribute("companies", companyService.getXmlInfo());
        return "xml/import-companies";
    }

    @PostMapping("import/companies")
    public String postCompanies() {
        this.companyService.importCompanies();
        return "redirect:/import/xml";
    }

    @GetMapping("import/projects")
    public String getImportProjects(Model model) throws IOException {
        model.addAttribute("projects", projectService.getXmlInfo());
        return "xml/import-projects";
    }

    @PostMapping("import/projects")
    public String postProjects() {
        this.projectService.importProjects();
        return "redirect:/import/xml";
    }

    @GetMapping("import/employees")
    public String getImportEmployees(Model model) throws IOException {
        model.addAttribute("employees", employeeService.getXmlInfo());
        return "xml/import-employees";
    }

    @PostMapping("import/employees")
    public String postEmployees() {
        this.employeeService.importEmployees();
        return "redirect:/import/xml";
    }
}
