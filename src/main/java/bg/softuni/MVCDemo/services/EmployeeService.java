package bg.softuni.MVCDemo.services;

import bg.softuni.MVCDemo.dtos.EmployeeExportDto;
import bg.softuni.MVCDemo.dtos.EmployeeImportDto;
import bg.softuni.MVCDemo.dtos.EmployeeDtoWrapper;
import bg.softuni.MVCDemo.entities.Employee;
import bg.softuni.MVCDemo.entities.Project;
import bg.softuni.MVCDemo.repositories.EmployeeRepository;
import bg.softuni.MVCDemo.utils.Paths;
import bg.softuni.MVCDemo.utils.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProjectService projectService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ProjectService projectService) {
        this.employeeRepository = employeeRepository;
        this.projectService = projectService;
    }

    public boolean areImported() {
        return this.employeeRepository.count() > 0;
    }

    public String getXmlInfo() throws IOException {
        return Files.readString(Path.of(Paths.EMPLOYEES_IMPORT_XML));
    }

    public void importEmployees() {
        EmployeeDtoWrapper employeeDtoWrapper = XmlParser.fromXmlFile(Paths.EMPLOYEES_IMPORT_XML,
                EmployeeDtoWrapper.class);
        List<EmployeeImportDto> employeeDtoList = employeeDtoWrapper.getEmployeeDtoList();

        for (EmployeeImportDto employeeDto : employeeDtoList) {
            Optional<Project> firstProjectByName = this.projectService
                    .findFirstProjectByName(employeeDto.getProjectDto().getName());

            if (firstProjectByName.isEmpty()) continue;

            Employee employee = employeeDto.toEmployee();
            employee.setProject(firstProjectByName.get());
            this.employeeRepository.saveAndFlush(employee);
        }
    }

    public String getExportStringForAllEmployeesAbove25Years() {
        StringBuilder builder = new StringBuilder();

         this.employeeRepository.findByAgeGreaterThan(25)
                .stream()
                .map(Employee::toEmployeeExportDto)
                .forEach(builder::append);

         return builder.toString().trim();
    }
}
