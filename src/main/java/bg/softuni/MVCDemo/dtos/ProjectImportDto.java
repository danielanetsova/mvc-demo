package bg.softuni.MVCDemo.dtos;

import bg.softuni.MVCDemo.entities.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.springframework.data.annotation.AccessType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@XmlRootElement(name = "project")
@AccessType(AccessType.Type.FIELD)
public class ProjectImportDto implements Serializable {
    @NotBlank
    @XmlElement
    private String name;

    @NotBlank
    @XmlElement
    private String description;

    @XmlElement(name = "start-date")
    private String startDate;

    @XmlElement(name = "is-finished")
    private boolean isFinished;

    @NotNull
    @XmlElement
    private BigDecimal payment;

    @NotNull
    @XmlElement(name = "company")
    private CompanyDto companyDto;

    public Project toProject() {
        return new Project(this.name,
                this.description,
                LocalDate.parse(this.startDate),
                isFinished,
                this.payment);
    }

    public @NotNull CompanyDto getCompanyDto() {
        return companyDto;
    }

    public @NotBlank String getName() {
        return name;
    }
}
