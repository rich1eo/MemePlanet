package com.example.memeplanet.repositoty;

import com.example.memeplanet.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByTitle(String title);
}
