package paymentmethods.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import paymentmethods.com.dto.PaymentMethodsDTO;
import paymentmethods.com.service.PaymentCategoryService;

@RestController
@RequestMapping("/api/v1.0/configuration")
public class PaymentMethodsController {

    @Autowired
    private PaymentCategoryService paymentCategoryService;

    @RequestMapping(value = "/payment-methods", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> getCategoryByIdOrName(
            @RequestParam(required = false, value="id") Integer id,
            @RequestParam(required = false, value="name") String name
    ) {
        if (id != null) {
            return paymentCategoryService.getCategoryById(id);
        } else if (name != null && name != "") {
            String categoryName = name.replaceAll("\\s+", " ");
            return paymentCategoryService.getCategoryByName(categoryName);
        } else {
            return paymentCategoryService.listAll();
        }
    }

    @RequestMapping(
            value = "/payment-methods",
            method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> savePaymentCategory(@RequestBody(required = true) PaymentMethodsDTO paymentMethodsDTO) {
        return paymentCategoryService.save(paymentMethodsDTO);
    }

    @RequestMapping(
            value = "/payment-methods",
            method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Object> updatePaymentCategory(@RequestParam(required = true, value="payment-methods") Integer planId,
                                                        @RequestBody(required = true) PaymentMethodsDTO paymentMethodsDTO) {
        return paymentCategoryService.update(planId, paymentMethodsDTO);
    }
}
