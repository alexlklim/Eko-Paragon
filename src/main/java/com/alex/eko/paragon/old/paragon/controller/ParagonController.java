package com.alex.eko.paragon.old.paragon.controller;


import com.alex.eko.paragon.old.paragon.dto.ParagonDTO;
import com.alex.eko.paragon.old.paragon.service.ParagonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/e-paragon")
public class ParagonController {

    private final String TAG = "PARAGON_CONTROLLER - ";

    private final ParagonService paragonService;


    @Operation(summary = "Get paragon (not secured, you can get paragon, but paragon won't be added to user profile")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{paragon_uuid}")
    public ParagonDTO getParagonByLink(
            @PathVariable("paragon_uuid") String paragonUUID) {
        log.info(TAG + "getParagonByLink");
        return paragonService.getParagon(paragonUUID);
    }


    @Operation(summary = "Get paragon (secured, paragon will be added to user profile")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/s/{paragon_uuid}")
    public ParagonDTO getParagonByLinkSecured(
            @PathVariable("paragon_uuid") String paragonUUID) {
        log.info(TAG + "getParagonByLinkSecured");
        return paragonService.getParagon(paragonUUID);
    }


    @Operation(summary = "Create paragon (secured)." +
            "Shop can create paragon and get link to this paragon")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void createParagon(
            @RequestBody ParagonDTO paragonDTO) {
        log.info(TAG + "createParagon");
        System.out.println(paragonDTO);
        paragonService.createParagon(paragonDTO);
    }


}
