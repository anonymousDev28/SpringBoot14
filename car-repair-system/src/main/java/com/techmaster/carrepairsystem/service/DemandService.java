package com.techmaster.carrepairsystem.service;

import com.techmaster.carrepairsystem.dto.DemandDTO;

public interface DemandService {
    DemandDTO createDemand(DemandDTO demandDTO);
    DemandDTO addStaff(DemandDTO demandDTO);
    String updateDemand(Integer id);
}
