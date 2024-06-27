package bg.softuni.MVCDemo.dtos;

import bg.softuni.MVCDemo.entities.Company;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.springframework.data.annotation.AccessType;

import java.io.Serializable;
@XmlRootElement(name = "company")
@AccessType(AccessType.Type.FIELD)
public class CompanyDto implements Serializable {
    @XmlAttribute()
    @NotBlank
    private String name;

    public Company toCompany() {
        return new Company(this.name);
    }

    public @NotBlank String getName() {
        return name;
    }
}
