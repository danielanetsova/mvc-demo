package bg.softuni.MVCDemo.dtos;

import java.math.BigDecimal;

public class ProjectExportDto {
    private String name;

    private String description;

    private BigDecimal payment;

    public ProjectExportDto() {}

    public ProjectExportDto(String name, String description, BigDecimal payment) {
        this.name = name;
        this.description = description;
        this.payment = payment;
    }

    @Override
    public String toString() {
        return String.format("Project Name: %s%n" +
                "     Description: %s%n" +
                "     %.2f%n", this.name, this.description, this.payment);
    }
}
