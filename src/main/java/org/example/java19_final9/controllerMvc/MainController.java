package org.example.java19_final9.controllerMvc;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java19_final9.dto.TransactionDto;
import org.example.java19_final9.dto.UserImageDto;
import org.example.java19_final9.service.TransactionService;
import org.example.java19_final9.service.UserImageService;
import org.example.java19_final9.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final UserService userService;
    private final UserImageService userImageService;

    private final TransactionService transactionService;


    @GetMapping("/")
    public String getHomePage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        if (auth.getName().equals("anonymousUser")) {
            model.addAttribute("username", null);
        } else {
            model.addAttribute("username", userService.mapToUserDto(userService.getUserByAccount(auth.getName()).get()));

        }
        return "main/main";
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String sendMoney(
            @RequestParam(name = "receiver") String receiver,
            @RequestParam(name = "amount") int amount,
            @RequestParam(name = "transactionType") int transactionType
    ) {

        if (userService.isAccountExists(receiver)) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (userService.getUserByAccount(auth.getName()).get().getBalance() < amount) {
                return "redirect:/?account=balance";
            } else {
                if (receiver.equals(auth.getName())) {
                    return "redirect:/?account=yourself";
                } else {
                    TransactionDto transactionDto = TransactionDto.builder()
                            .senderAccount(Integer.valueOf(auth.getName()))
                            .receiverAccount(Integer.valueOf(receiver))
                            .transactionType(transactionType)
                            .amount(amount)
                            .build();
                    transactionService.savePayment(transactionDto);
                    return "redirect:/?account=success";
                }
            }
        } else {
            return "redirect:/?account=user";
        }
    }


    @GetMapping("/uniqueCode")
    public String getAccountCode(@RequestParam(name = "account", defaultValue = "No code") int account,
                                 Model model) {
        model.addAttribute("account", account);
        return "main/uniqueCode";

    }

    @PostMapping("/upload")
    public String uploadImage(UserImageDto userImageDto) {
        userImageService.uploadImage(userImageDto);
        return "redirect:/profile";
    }

}
