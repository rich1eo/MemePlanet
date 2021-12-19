package com.example.memeplanet.service;

import com.example.memeplanet.model.Image;
import com.example.memeplanet.model.User;
import com.example.memeplanet.model.enums.Role;
import com.example.memeplanet.repositoty.ImageRepository;
import com.example.memeplanet.repositoty.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> list() {
        return userRepository.findAll();
    }

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        userRepository.save(user);
        return true;
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        userRepository.save(user);
    }

    public void addAvatar(Principal principal, MultipartFile file) throws IOException {
        User user = getUserByPrincipal(principal);
        Image avatar = new Image();
        if (avatar.getSize() != 0) {
            avatar.setName(file.getName());
            avatar.setOriginalFileName(file.getOriginalFilename());
            avatar.setContentType(file.getContentType());
            avatar.setSize(file.getSize());
            avatar.setBytes(file.getBytes());
            avatar.setUser(user);
            user.setAvatar(avatar);
        }
        log.info("Saving new Avatar. User: {}", user.getUsername());
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }


}
