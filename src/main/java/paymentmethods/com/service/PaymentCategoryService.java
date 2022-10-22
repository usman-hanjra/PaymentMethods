package paymentmethods.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import paymentmethods.com.dto.PaymentMethodsDTO;
import paymentmethods.com.entity.PaymentCategory;
import paymentmethods.com.entity.PaymentPlans;
import paymentmethods.com.repository.PaymentCategoryRepository;
import paymentmethods.com.repository.PaymentPlansRepository;
import paymentmethods.com.utils.CommonUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentCategoryService {

    @Autowired
    private PaymentCategoryRepository paymentCategoryRepository;

    @Autowired
    private PaymentPlansRepository paymentPlansRepository;

    public ResponseEntity<Object> listAll() {
        List<PaymentCategory> paymentMethods = new ArrayList<>();
        paymentCategoryRepository.findAll().forEach(paymentMethods::add);
        return new ResponseEntity<>(new PaymentMethodsDTO(paymentMethods), HttpStatus.OK);
    }

    public ResponseEntity<Object> getCategoryById(Integer planId) {
        List<PaymentCategory> paymentMethods = new ArrayList<>();
        PaymentCategory paymentCategory = getPaymentCategoryThroughPlan(planId);
        paymentMethods.add(CommonUtils.isNotBlank(paymentCategory) ?
            paymentCategory : null);
        return new ResponseEntity<>(new PaymentMethodsDTO(paymentMethods), HttpStatus.OK);
    }

    public ResponseEntity<Object> getCategoryByName(String name) {
        List<PaymentCategory> paymentMethods = new ArrayList<>();
        PaymentCategory paymentCategory =  paymentCategoryRepository.findByName(name);
        paymentMethods.add(paymentCategory);
        return new ResponseEntity<>(new PaymentMethodsDTO(paymentMethods), HttpStatus.OK);
    }

    public ResponseEntity<Object> save(PaymentMethodsDTO paymentMethodsDTO) {
        if (CommonUtils.isNotBlank(paymentMethodsDTO) && CommonUtils.isNotEmpty(paymentMethodsDTO.getPaymentMethods())) {
            List<PaymentCategory> paymentCategoriesResponse = new ArrayList<>();
            List<PaymentCategory> paymentCategories = paymentMethodsDTO.getPaymentMethods();
            PaymentCategory savedPaymentCategory;
            for (PaymentCategory paymentCategory: paymentCategories) {
                savedPaymentCategory = paymentCategoryRepository.save(paymentCategory);
                if(CommonUtils.isNotEmpty(paymentCategory.getPaymentPlans()) && CommonUtils.isNotBlank(savedPaymentCategory)) {
                    for (PaymentPlans paymentPlans : paymentCategory.getPaymentPlans()) {
                        paymentPlans.setPaymentCategory(savedPaymentCategory);
                        paymentPlansRepository.save(paymentPlans);
                    }
                }
                paymentCategoriesResponse.add(savedPaymentCategory);
            }
            paymentMethodsDTO.setPaymentMethods(paymentCategoriesResponse);
            return new ResponseEntity<>(paymentMethodsDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Please add data", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> update(Integer planId, PaymentMethodsDTO paymentMethodsDTO) {
        PaymentCategory paymentCategory = getPaymentCategoryThroughPlan(planId);
        if (isPaymentCategoryNotNull(paymentMethodsDTO, paymentCategory) &&
                planId.equals(paymentMethodsDTO.getPaymentMethods().get(0).getPaymentPlans().get(0).getId())) {
            PaymentCategory requestCategory = paymentMethodsDTO.getPaymentMethods().get(0);
            requestCategory.setPaymentCategoryId(paymentCategory.getPaymentCategoryId());
            paymentCategoryRepository.save(requestCategory);
            PaymentPlans paymentPlans = requestCategory.getPaymentPlans().get(0);
            paymentPlans.setPaymentCategory(requestCategory);
            paymentPlans.setPaymentCategoryId(requestCategory.getPaymentCategoryId());
            paymentPlansRepository.save(paymentPlans);
            return new ResponseEntity<>(paymentMethodsDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Sorry, We are unable to update data. Please have a look at data and id.", HttpStatus.OK);
    }

    private Boolean isPaymentCategoryNotNull(PaymentMethodsDTO paymentMethodsDTO, PaymentCategory paymentCategory) {
        return CommonUtils.isNotBlank(paymentCategory) &&
                CommonUtils.isNotBlank(paymentMethodsDTO) &&
                CommonUtils.isNotEmpty(paymentMethodsDTO.getPaymentMethods()) &&
                CommonUtils.isNotEmpty(paymentMethodsDTO.getPaymentMethods().get(0).getPaymentPlans());
    }

    private PaymentCategory getPaymentCategoryThroughPlan(Integer id) {
        Optional<PaymentPlans> paymentPlansOptional = paymentPlansRepository.findById(id);
        PaymentPlans paymentPlans = paymentPlansOptional.isPresent() ? paymentPlansOptional.get() : null;
        if(CommonUtils.isNotBlank(paymentPlans)) {
            PaymentCategory paymentCategory = paymentPlans.getPaymentCategory();
            paymentCategory.setPaymentPlans(new ArrayList<>( Arrays.asList(paymentPlans)));
            return paymentCategory;
        }
        return null;
    }
}
