package bg.softuni.MVCDemo.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeDtoWrapper implements Serializable {
    @XmlElement(name = "employee")
    private List<EmployeeImportDto> employeeDtoList;

    public List<EmployeeImportDto> getEmployeeDtoList() {
        return employeeDtoList;
    }
}
