package com.main.service.admin;

import com.main.model.admin.AdminRevenueProjection;
import com.main.repository.admin.AdminRevenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminRevenueService {


    private final AdminRevenueRepository adminRevenueRepository;

    public List<AdminRevenueProjection> handleGetRevenueRequest() {

        return adminRevenueRepository.getRevenue();
    }
}
