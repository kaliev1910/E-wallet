package org.example.java19_final9.controllerMvc;

import lombok.RequiredArgsConstructor;
import org.example.java19_final9.dto.PaymentForService;
import org.example.java19_final9.dto.ProviderUserDto;
import org.example.java19_final9.service.ProviderService;
import org.example.java19_final9.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService providerService;
    private final UserService userService;

    @GetMapping("/service")
    public String getServices(Model model) {
        model.addAttribute("providers", providerService.getAllServices());
        return "main/services";
    }

    @GetMapping("/service/{serviceId}")
    public String perform(@PathVariable int serviceId, Authentication auth, Model model) {
        if (providerService.getProviderById(serviceId) != null) {
            model.addAttribute("service", providerService.getProviderById(serviceId));

            return "main/perform";
        } else {
            return "main/error";
        }
    }

    @GetMapping("/service/{serviceId}/subscribe")
    public String subscribe(@PathVariable int serviceId, Model model) {
        if (providerService.getProviderById(serviceId) != null) {
            model.addAttribute("service", providerService.getProviderById(serviceId));
            return "main/subscribe";
        } else {
            return "main/error";
        }
    }

    @PostMapping("/service/{serviceId}/subscribe")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addProviderUser(
            @RequestParam(name = "phone") int phone,
            @PathVariable int serviceId, Authentication auth
    ) {
        int id = userService.getUserByAccount(auth.getName()).get().getId();
        ProviderUserDto providerUserDto = ProviderUserDto.builder()
                .userPhone(phone)
                .providerId(serviceId)
                .build();
        providerService.addProviderUser(providerUserDto);
        return "redirect:/service/" + serviceId;

    }

    @PostMapping("/service/{serviceId}")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String withdraw(
            @RequestParam(name = "phone") int phone,
            @RequestParam(name = "balance") int balance,
            @PathVariable int serviceId, Model model, Authentication auth
    ) {

        int id = userService.getUserByAccount(auth.getName()).get().getId();
        PaymentForService providerUserDto = PaymentForService.builder()
                .amount(balance)
                .providerId(serviceId)
                .userPhone(phone)
                .build();
        String response = providerService.replenishProviderUser(providerUserDto);
        model.addAttribute("response", response);
        return "redirect:/profile";

    }


}
