package bg.softuni.MVCDemo.entities;
import bg.softuni.MVCDemo.dtos.ProjectExportDto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(nullable = false)
    private BigDecimal payment;

    private LocalDate date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public Project() {}

    public Project(String name, String description, LocalDate startDate,
                   Boolean isFinished, BigDecimal payment) {
        this.name = name;
        this.description = description;
        this.date = startDate;
        this.isFinished = isFinished;
        this.payment = payment;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ProjectExportDto toProjectExportDto() {
        return new ProjectExportDto(this.name, this.description, this.payment);
    }

    public String getName() {
        return name;
    }
}
