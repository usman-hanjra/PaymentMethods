package paymentmethods.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paymentmethods.com.entity.PaymentCategory;

@Repository
public interface PaymentCategoryRepository extends JpaRepository<PaymentCategory, Integer> {
    PaymentCategory findByName(String name);
}
