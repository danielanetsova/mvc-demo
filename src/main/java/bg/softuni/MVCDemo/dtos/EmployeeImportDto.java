package bg.softuni.MVCDemo.dtos;

import bg.softuni.MVCDemo.entities.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeImportDto implements Serializable {
    @NotBlank
    @XmlElement(name = "first-name")
    private String firstName;

    @NotBlank
    @XmlElement(name = "last-name")
    private String lastName;

    @NotNull
    @XmlElement()
    private Integer age;

    @NotNull
    @XmlElement(name = "project")
    private ProjectImportDto importDto;

    public Employee toEmployee() {
        return new Employee(this.firstName, this.lastName, this.age);
    }

    public @NotNull ProjectImportDto getProjectDto() {
        return importDto;
    }
}
