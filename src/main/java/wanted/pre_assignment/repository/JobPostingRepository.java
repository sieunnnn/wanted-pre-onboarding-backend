package wanted.pre_assignment.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wanted.pre_assignment.domain.JobPosting;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobPostingRepository extends CrudRepository<JobPosting, Long> {
    Optional<JobPosting> findJobPostingById(Long jobPostingId);
    @Query("SELECT jp.id " +
            "FROM JobPosting jp " +
                "WHERE jp.company.id = :companyId " +
                "AND jp.id != :jobPostingId"
    )
    List<Long> findOtherJobPostingsByCompanyId(@Param("companyId") Long companyId, @Param("jobPostingId") Long jobPostingId);

}
