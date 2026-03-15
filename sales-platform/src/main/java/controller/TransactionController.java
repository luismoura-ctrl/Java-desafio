package controller;

import entity.Product;
import entity.Transaction;
import entity.User;
import repository.ProductRepository;
import repository.TransactionRepository;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/buy")
    public Transaction buyProduct(
            @RequestParam Long userId,
            @RequestParam Long productId) {

        User user = userRepository.findById(userId)
                .orElseThrow();

        Product product = productRepository.findById(productId)
                .orElseThrow();

        Transaction transaction = new Transaction();

        transaction.setUser(user);
        transaction.setProduct(product);
        transaction.setDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
}
