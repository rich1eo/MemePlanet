package com.example.memeplanet.service;

import com.example.memeplanet.model.Entry;
import com.example.memeplanet.model.Image;
import com.example.memeplanet.model.User;
import com.example.memeplanet.repositoty.EntryRepository;
import com.example.memeplanet.repositoty.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EntryService {
    public final EntryRepository entryRepository;
    public final UserRepository userRepository;

    public List<Entry> listEntries(String title) {
        if (title != null) return entryRepository.findByTitle(title);
        return entryRepository.findAll();
    }

    public Entry getEntryById(Long id) {
        return entryRepository.findById(id).orElse(null);
    }

    public void saveEntry(Principal principal, Entry entry, MultipartFile file) throws IOException {
        entry.setUser(getUserByPrincipal(principal));
        Image image;
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            entry.addImageToEntry(image);
        }
        log.info("Saving new Product. Title: {}; Author email: {}", entry.getTitle(), entry.getUser().getEmail());
        Entry entryFromDb = entryRepository.save(entry);
        entryFromDb.setPreviewImageId(entryFromDb.getImages().get(0).getId());
        entryRepository.save(entry);
    }

    public void deleteEntry(User user, Long id) {
        Entry entry = entryRepository.findById(id)
                .orElse(null);
        if (entry != null) {
            if (entry.getUser().getId().equals(user.getId())) {
                entryRepository.delete(entry);
                log.info("Entry with id = {} was deleted", id);
            } else {
                log.error("User: {} haven't this entry with id = {}", user.getEmail(), id);
            }
        } else {
            log.error("Entry with id = {} is not found", id);
        }
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
}
