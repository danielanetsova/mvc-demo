package bg.softuni.MVCDemo.services;

import bg.softuni.MVCDemo.dtos.CompanyDto;
import bg.softuni.MVCDemo.dtos.CompanyImportDtoWrapper;
import bg.softuni.MVCDemo.entities.Company;
import bg.softuni.MVCDemo.repositories.CompanyRepository;
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
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public boolean areImported() {
        return this.companyRepository.count() > 0;
    }

    public String getXmlInfo() throws IOException {
        return  Files.readString(Path.of(Paths.COMPANIES_IMPORT_XML));
    }

    public void importCompanies() {
        CompanyImportDtoWrapper companyImportDtoWrapper = XmlParser
                .fromXmlFile(Paths.COMPANIES_IMPORT_XML, CompanyImportDtoWrapper.class);

        List<CompanyDto> companyDtoList = companyImportDtoWrapper.getCompanyDtoList();
        List<Company> companies = companyDtoList
                .stream()
                .map(CompanyDto::toCompany)
                .toList();

        this.companyRepository.saveAllAndFlush(companies);
    }

    Optional<Company> findCompanyByFirstByName(String name) {
        return this.companyRepository.findFirstByName(name);
    }
}
