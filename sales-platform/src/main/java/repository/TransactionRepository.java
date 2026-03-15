package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
