package bg.softuni.MVCDemo.dtos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.springframework.data.annotation.AccessType;

import java.util.List;

@XmlRootElement(name = "projects")
@AccessType(AccessType.Type.FIELD)
public class ProjectImportDtoWrapper {
    @XmlElement(name = "project")
    private List<ProjectImportDto> projectImportDtoList;

    public List<ProjectImportDto> getProjectImportDtoList() {
        return projectImportDtoList;
    }
}
