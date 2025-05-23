package com.kh.opendataPractice.controller;

import com.kh.opendataPractice.model.vo.EmergencyShelter;
import com.kh.opendataPractice.service.EmergencyShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmergencyShelterController {

    private final EmergencyShelterService emergencyShelterService;

    @GetMapping("/getEmergencyShelterList")
    public List<EmergencyShelter> getEmergencyShelterlist() throws Exception {

        return emergencyShelterService.getEmergencyShelterList();

    }

}
