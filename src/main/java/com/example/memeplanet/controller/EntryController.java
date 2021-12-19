package com.example.memeplanet.controller;

import com.example.memeplanet.model.Comment;
import com.example.memeplanet.model.Entry;
import com.example.memeplanet.model.User;
import com.example.memeplanet.service.CommentService;
import com.example.memeplanet.service.EntryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

@Controller
@Slf4j
@RequiredArgsConstructor
public class EntryController {
    private final EntryService entryService;
    private final CommentService commentService;

    @GetMapping("/")
    public String entries(@RequestParam(name = "searchWord", required = false) String title,
                          Principal principal, Model model) {
        model.addAttribute("entries", entryService.listEntries(title));
        model.addAttribute("user", entryService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", title);
        return "entries";
    }

    @PostMapping("/entry/create")
    public String createEntry(@RequestParam("file") MultipartFile file, Entry entry, Principal principal)
            throws IOException {
        entryService.saveEntry(principal, entry, file);
        return "redirect:/my/entry";
    }

    @GetMapping("entry/{id}")
    public String entryInfo(@PathVariable Long id, Model model, Principal principal) {
        Entry entry = entryService.getEntryById(id);
        model.addAttribute("user", entryService.getUserByPrincipal(principal));
        model.addAttribute("entries", entry);
        model.addAttribute("images", entry.getImages());
        model.addAttribute("authorEntry", entry.getUser());
        model.addAttribute("comments", entry.getComment());
        return "entryInfo";
    }

    @PostMapping("/entry/delete/{id}")
    public String deleteEntry(@PathVariable Long id, Principal principal) {
        entryService.deleteEntry(entryService.getUserByPrincipal(principal), id);
        return "redirect:/my/entry";
    }

    @GetMapping("/my/entry")
    public String userEntries(Principal principal, Model model) {
        User user = entryService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("entries", user.getEntries());
        return "myEntries";
    }
}

