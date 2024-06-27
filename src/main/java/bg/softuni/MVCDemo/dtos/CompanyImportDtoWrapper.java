package bg.softuni.MVCDemo.dtos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.springframework.data.annotation.AccessType;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "companies")
@AccessType(AccessType.Type.FIELD)
public class CompanyImportDtoWrapper implements Serializable {
    @XmlElement(name = "company")
    private List<CompanyDto> companyDtoList;

    public List<CompanyDto> getCompanyDtoList() {
        return companyDtoList;
    }
}
