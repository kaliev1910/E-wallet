package org.example.java19_final9.controllerMvc;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java19_final9.dto.UserDto;
import org.example.java19_final9.enums.AccountType;
import org.example.java19_final9.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;


    @GetMapping("/register")
    public String register() {
        return "auth/registration";
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addResume(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password

    ) {
        if (userService.isUserExist(email).equalsIgnoreCase("1")) {
            return "redirect:/register/error";
        } else {
            String accountPwd=String.valueOf(generateUniqueNumber(email));
            UserDto userDto = UserDto.builder()
                    .password(password)
                    .email(email)
                    .accountType(AccountType.USER)
                    .account(accountPwd)
                    .build();

            int userId = userService.save(userDto);
            return "redirect:/uniqueCode?account="+accountPwd;
        }
    }

    public static int generateUniqueNumber(String userEmail) {
        int hashCode = userEmail.hashCode();
        hashCode = Math.abs(hashCode);
        int uniqueNumber = hashCode % 1_000_000;

        if (uniqueNumber < 100_000) {
            uniqueNumber += 100_000;
        }

        return uniqueNumber;
    }


    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
    @GetMapping("/forgot")
    public String forgotPassword() {
        return "auth/forget";
    }

    @PostMapping("/forgot")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        try {
            userService.makeResetPasswdLink(request);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
        } catch (UsernameNotFoundException | UnsupportedEncodingException e) {
            model.addAttribute("error", e.getMessage());
        } catch (MessagingException e) {
            model.addAttribute("error", "Error while sending email!");
        }
        return "auth/forget";
    }

    @GetMapping("/reset")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        try {
            userService.getByResetPasswdToken(token);
            model.addAttribute("token", token);
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("error", "Invalid token");
        }
        return "auth/reset";
    }

    @PostMapping("/reset")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        try {
            var user = userService.getByResetPasswdToken(token);
            userService.updatePassword(user, password);
            model.addAttribute("message", "You have successfully changed your password.");
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("message", "Invalid Token");
        }
        return "auth/message";
    }

}
