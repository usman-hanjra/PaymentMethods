package paymentmethods.com.dto;

import paymentmethods.com.entity.PaymentCategory;

import java.util.List;

public class PaymentMethodsDTO {

    public PaymentMethodsDTO(List<PaymentCategory> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public PaymentMethodsDTO() {
    }

    private List<PaymentCategory> paymentMethods;

    public List<PaymentCategory> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentCategory> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
