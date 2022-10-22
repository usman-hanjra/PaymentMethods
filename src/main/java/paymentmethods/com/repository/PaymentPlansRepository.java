package paymentmethods.com.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paymentmethods.com.entity.PaymentPlans;

@Repository
public interface PaymentPlansRepository extends JpaRepository<PaymentPlans, Integer> {
}
