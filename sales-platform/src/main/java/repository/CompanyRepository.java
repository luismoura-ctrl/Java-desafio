package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	
}
