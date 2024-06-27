package bg.softuni.MVCDemo.services;

import bg.softuni.MVCDemo.dtos.ProjectExportDto;
import bg.softuni.MVCDemo.dtos.ProjectImportDto;
import bg.softuni.MVCDemo.dtos.ProjectImportDtoWrapper;
import bg.softuni.MVCDemo.entities.Company;
import bg.softuni.MVCDemo.entities.Project;
import bg.softuni.MVCDemo.repositories.ProjectRepository;
import bg.softuni.MVCDemo.utils.Paths;
import bg.softuni.MVCDemo.utils.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final CompanyService companyService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, CompanyService companyService) {
        this.projectRepository = projectRepository;
        this.companyService = companyService;
    }

    public boolean areImported() {
        return this.projectRepository.count() > 0;
    }

    public String getXmlInfo() throws IOException {
        return  Files.readString(Path.of(Paths.PROJECTS_IMPORT_XML));
    }

    public void importProjects() {
        ProjectImportDtoWrapper projectImportDtoWrapper =
                XmlParser.fromXmlFile(Paths.PROJECTS_IMPORT_XML, ProjectImportDtoWrapper.class);

        List<ProjectImportDto> projectImportDtoList = projectImportDtoWrapper.getProjectImportDtoList();

        for (ProjectImportDto projectImportDto : projectImportDtoList) {
            Optional<Company> firstCompanyByName = this.companyService
                    .findCompanyByFirstByName(projectImportDto.getCompanyDto().getName());

            if (firstCompanyByName.isEmpty()) continue;

            Project project = projectImportDto.toProject();
            project.setCompany(firstCompanyByName.get());
            projectRepository.saveAndFlush(project);
        }
    }

    public Optional<Project> findFirstProjectByName(String name) {
        return projectRepository.findFirstByName(name);
    }

    public String getExportStringForAllFinishedProjects() {
        StringBuilder builder = new StringBuilder();

         this.projectRepository
                .findAllByIsFinishedTrue()
                .stream()
                .map(Project::toProjectExportDto)
                .forEach(builder::append);

        return builder.toString().trim();
    }
}
