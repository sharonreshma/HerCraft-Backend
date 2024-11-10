

 // PaymentController.java
    package com.example.demo;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/payments")
    public class PaymentController {

        @Autowired
        private PaymentRepository paymentRepository;

        @PostMapping
        public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
            try {
                Payment savedPayment = paymentRepository.save(payment);
                return ResponseEntity.ok(savedPayment);
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Failed to save payment data");
            }
        }
    }


