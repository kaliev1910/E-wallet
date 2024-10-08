package org.example.java19_final9.service;


import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java19_final9.dto.UserDto;
import org.example.java19_final9.enums.AccountType;
import org.example.java19_final9.model.User;
import org.example.java19_final9.repository.ProviderUserRepository;
import org.example.java19_final9.repository.RoleRepository;
import org.example.java19_final9.repository.UserRepository;
import org.example.java19_final9.util.Utility;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleService roleService;
    private final EmailService emailService;
    private final RoleRepository roleRepository;
    private final ProviderUserRepository providerUserRepository;


    public List<UserDto> getAllUsers() {
        log.info("Got all users");
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream()
                .map(e -> UserDto.builder()
                        .id(e.getId())
                        .accountType(AccountType.USER)
                        .password(e.getPassword())
                        .email(e.getEmail())
                        .build()
                ).collect(Collectors.toList());

        return userDtos;
    }

    public Boolean isAccountExists(String account) {
        return userRepository.existsByAccount(account);
    }

    public void subMoney(int amount, String account, String receiverAccount) {
        int balance = userRepository.findUserByAccount(account).get().getBalance();
        int total = balance - amount;
        int receiverBalance = userRepository.findUserByAccount(receiverAccount).get().getBalance();
        int receiverTotal = receiverBalance + amount;
        userRepository.updateBalanceByAccount(account, total);
        System.out.println();
        userRepository.updateBalanceByAccount(receiverAccount, receiverTotal);

    }
    public void subMoneyForProvider(int amount, String account, String receiverAccount) {
        int balance = userRepository.findUserByAccount(account).get().getBalance();
        int total = balance - amount;
        int receiverBalance = providerUserRepository.findByUserPhone(Integer.parseInt(receiverAccount)).get().getBalance();
        int receiverTotal = receiverBalance + amount;
        userRepository.updateBalanceByAccount(account, total);
        System.out.println();
        userRepository.updateBalanceByAccount(receiverAccount, receiverTotal);

    }


    public Optional<User> getUserByEmail(String email) {
        log.info("Gol user by email:" + email);
        Optional<User> mayBeUser = userRepository.findUserByEmail(email);
        return mayBeUser;
    }

    public Optional<User> getUserByAccount(String account) {
        log.info("Gol user by account:" + account);
        Optional<User> mayBeUser = userRepository.findUserByAccount(account);
        return mayBeUser;
    }

    public String isUserExist(String email) {
        try {
            Optional<User> user = userRepository.findUserByEmail(email);
            if (user.isPresent()) {
                log.error("User:" + email + "  exists!");
                return "1";
            } else {
                log.info("User:" + email + " does not exist!");
                return "0";
            }
        } catch (EmptyResultDataAccessException e) {
            log.error("Empty Result!");
            return "error";
        }
    }


    public Optional<User> getUserById(int id) {
        log.info("Got user by id:{}", id);
        return userRepository.findById(id);
    }


//    public int save(UserDto userDto) {
//        log.info("The user:{} is saved!", userDto.getEmail());
//        return userRepository.save(User.builder()
//                .email(userDto.getEmail())
//                .password(encoder.encode(userDto.getPassword()))
//                .enabled(true)
//                .account(userDto.getAccount())
//                .balance(1000)
//                .build()
//
//
//        ).getId();
//
//    }
    public int save(UserDto userDto) {
        User user;
        try {
            user = fromDto(userDto);
            user.setPassword(encoder.encode(userDto.getPassword()));
            user.setEnabled(true);
            user.setAccount(userDto.getAccount());
           User savedUser =  userRepository.save(user);

            Optional<User> newUser = userRepository.findUserByAccount(user.getAccount());


            if (newUser.isPresent()) {
                if (user.getAccountType().equalsIgnoreCase(AccountType.USER.getValue())) {
                    roleRepository.addUserRole(1,savedUser.getId());
                }
                if (user.getAccountType().equalsIgnoreCase(AccountType.ADMIN.getValue())) {
                    roleRepository.addUserRole(2,savedUser.getId());
                }

                log.info("User with email {} has been created", userDto.getAccount());
                return user.getId();
            } else {
                log.info("User could not be created");
                return 0;
            }
        } catch (Exception e) {
            log.error("Error while trying to create user", e);
            throw e;
        }
    }
    protected UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .accountType(AccountType.USER)
                .account(user.getAccount())

                .build();
    }

    protected static User fromDto(UserDto user) {
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .accountType(user.getAccountType().getValue())
                .account(user.getAccount())
                .enabled(true)
                .build();
    }


    public UserDto mapToUserDto(User user) {
        if (user != null) {
            return UserDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .accountType(AccountType.USER)
                    .password(user.getPassword())
                    .resetPasswordToken(user.getResetPasswordToken())
                    .account(user.getAccount())
                    .balance(user.getBalance())
                    .build();
        } else {
            return null;
        }
    }

    public AccountType getAccountType(int num) {
        if (num == 1) {
            return AccountType.USER;
        }
        return AccountType.ADMIN;
    }

    private void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        user.setResetPasswordToken(token);
        userRepository.saveAndFlush(user);
    }

    public UserDto getByResetPasswdToken(String token) {
        User user = userRepository.findByResetPasswordToken(token).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return mapToUserDto(user);
    }

    public void updatePassword(UserDto userDto, String newPasswd) {
        User u = userRepository.findUserByEmail(userDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        u.setResetPasswordToken(null);
        u.setPassword(encoder.encode(newPasswd));
        userRepository.saveAndFlush(u);
    }

    public void makeResetPasswdLink(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);
        String resetPasswordLink = Utility.getSiteUrl(request) + "/reset?token=" + token;
        emailService.sendEmail(email, resetPasswordLink);
    }


}
