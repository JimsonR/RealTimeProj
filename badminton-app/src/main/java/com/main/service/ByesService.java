package com.main.service;

import com.main.model.ByesForMatchesProjection;
import com.main.repository.ByesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ByesService {
    private final ByesRepository byesRepository;
    public List<ByesForMatchesProjection> handleGetByesForMatches(int eventId) {
        return byesRepository.getByesByEventId(eventId);
    }
}
