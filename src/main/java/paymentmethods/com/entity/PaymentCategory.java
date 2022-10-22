package paymentmethods.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "PAYMENT_CATEGORY")
public class PaymentCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAYMENT_CATEGORY_SEQ")
    @Column(name = "PAYMENT_CATEGORY_ID", unique = true, nullable = false)
    @JsonIgnore
    private Integer paymentCategoryId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DISPLAY_NAME")
    private String displayName;
    @Column(name = "PAYMENT_TYPE")
    private String paymentType;
    @OneToMany(mappedBy = "paymentCategory", fetch = FetchType.LAZY)
    private List<PaymentPlans> paymentPlans;

    public Integer getPaymentCategoryId() {
        return paymentCategoryId;
    }

    public void setPaymentCategoryId(Integer paymentCategoryId) {
        this.paymentCategoryId = paymentCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public List<PaymentPlans> getPaymentPlans() {
        return paymentPlans;
    }

    public void setPaymentPlans(List<PaymentPlans> paymentPlans) {
        this.paymentPlans = paymentPlans;
    }

    public PaymentCategory() {
    }
}
