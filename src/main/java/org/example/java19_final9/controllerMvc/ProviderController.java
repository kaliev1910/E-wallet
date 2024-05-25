package org.example.java19_final9.controllerMvc;

import org.example.java19_final9.service.ProviderService;
import org.example.java19_final9.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public String perform(@PathVariable int serviceId, Model model) {
        if (providerService.getServiceById(serviceId) != null) {
            model.addAttribute("service", providerService.getServiceById(serviceId));
            return "main/perform";
        } else {
            return "main/error";
        }
    }

    @GetMapping("/service/{serviceId}/subscribe")
    public String subscribe(@PathVariable int serviceId, Model model) {
        if (providerService.getServiceById(serviceId) != null) {
            model.addAttribute("service", providerService.getServiceById(serviceId));
            return "main/subscribe";
        } else {
            return "main/error";
        }
    }

//    @PostMapping("/service/{serviceId}/subscribe")
//    @ResponseStatus(HttpStatus.SEE_OTHER)
//    public String postSubscribe(
//            @RequestParam(name = "phone") String phone,
//            @PathVariable int serviceId
//    ) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        int id = userService.getUserByAccount(auth.getName()).get().getId();
//        PhoneDto phoneDto = PhoneDto.builder()
//                .phone(phone)
//                .userId(id)
//                .build();
//        phoneService.save(phoneDto);
//        return "redirect:/service/" + serviceId;
//
//    }
//
//    @PostMapping("/{serviceId}")
//    @ResponseStatus(HttpStatus.SEE_OTHER)
//    public String withdraw(
//            @RequestParam(name = "phone") String phone,
//            @RequestParam(name = "balance") int balance,
//            @PathVariable int serviceId
//    ) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        int id = userService.getUserByAccount(auth.getName()).get().getId();
//        SubscriberDto subscriberDto = SubscriberDto.builder()
//                .balance(balance)
//                .serviceId(serviceId)
//                .phone(phone)
//                .build();
//        System.out.println("PHONE"+phone);
//        subscriberService.save(subscriberDto, auth.getName());
//        return "redirect:/profile";
//
//    }


}
