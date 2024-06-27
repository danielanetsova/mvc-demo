package bg.softuni.MVCDemo.dtos;

public class EmployeeExportDto {
    private String firstName;
    private String lastName;
    private int age;
    private String projectName;

    public EmployeeExportDto() {}

    public EmployeeExportDto(String firstName, String lastName, int age, String projectName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        String fullName = this.firstName + " " + this.lastName;

        return String.format("Name: %s%n" +
                "    Age: %d%n" +
                "    Project name: %s%n", fullName, this.age, this.projectName);
    }
}
