package com.techmaster.carrepairsystem.controller;

import com.techmaster.carrepairsystem.dto.DemandDTO;
import com.techmaster.carrepairsystem.service.DemandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demands")
public class DemandController {
    @Autowired
    private DemandServiceImpl demandService;
    @PostMapping()
    public DemandDTO saveDemand(@RequestBody DemandDTO demandDTO){
        return demandService.createDemand(demandDTO);
    }
}
