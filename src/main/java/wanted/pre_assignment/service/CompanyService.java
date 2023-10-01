package wanted.pre_assignment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.pre_assignment.domain.Company;
import wanted.pre_assignment.dto.request.CompanyCreateRequest;
import wanted.pre_assignment.repository.CompanyRepository;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    @Transactional
    public void createCompany(CompanyCreateRequest request) {
        Company company = Company.builder()
                .name(request.getCompanyName())
                .build();

        companyRepository.save(company);
    }
}
