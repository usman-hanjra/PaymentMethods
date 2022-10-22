package paymentmethods.com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT_PLANS")
public class PaymentPlans {

    @Id
    @Column(name = "Id", unique = true)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PAYMENT_CATEGORY_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PaymentCategory paymentCategory;
    @Column(name = "PAYMENT_CATEGORY_ID", insertable = false, updatable = false, nullable = false)
    @JsonIgnore
    private Integer paymentCategoryId;
    @Column(name = "NET_AMOUNT")
    private double netAmount;
    @Column(name = "TAX_AMOUNT")
    private double taxAmount;
    @Column(name = "GROSS_AMOUNT")
    private double grossAmount;
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "DURATION")
    private String duration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaymentCategoryId() {
        return paymentCategoryId;
    }

    public void setPaymentCategoryId(Integer paymentCategoryId) {
        this.paymentCategoryId = paymentCategoryId;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public PaymentCategory getPaymentCategory() {
        return paymentCategory;
    }

    public void setPaymentCategory(PaymentCategory paymentCategory) {
        this.paymentCategory = paymentCategory;
    }
}
