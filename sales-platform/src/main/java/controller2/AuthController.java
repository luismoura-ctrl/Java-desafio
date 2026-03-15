package controller2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import entity.User;
import repository.UserRepository;
import com.corporatesales.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private JwtUtil jwtUtil;

    // REGISTRAR USUÁRIO
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User foundUser = userRepository
                .findByEmail(user.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!foundUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Senha inválida.");
        } else {
            return jwtUtil.generateToken(foundUser.getEmail());
        }
    }

}
