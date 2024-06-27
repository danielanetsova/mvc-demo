package bg.softuni.MVCDemo.entities;

import bg.softuni.MVCDemo.dtos.EmployeeExportDto;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public Employee() {}

    public Employee(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public EmployeeExportDto toEmployeeExportDto() {
        return new EmployeeExportDto(this.firstName, this.lastName, this.age, this.project.getName());
    }
}
