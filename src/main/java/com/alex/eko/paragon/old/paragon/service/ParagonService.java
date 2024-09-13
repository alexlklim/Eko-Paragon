package com.alex.eko.paragon.old.paragon.service;


import com.alex.eko.paragon.old.errors.exceptions.ResourceNotFoundException;
import com.alex.eko.paragon.old.paragon.Paragon;
import com.alex.eko.paragon.old.paragon.dto.ParagonDTO;
import com.alex.eko.paragon.old.paragon.repo.ParagonRepo;
import com.alex.eko.paragon.old.security.utils.SecHolder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParagonService {

    private final String TAG = "PARAGON_SERVICE - ";

    private final ParagonRepo paragonRepo;

    @SneakyThrows
    public ParagonDTO getParagon(String paragonUUID) {
        log.info(TAG + "getParagon");
        return ParagonMapper.toDTO(paragonRepo.findParagonByUuid(paragonUUID).orElseThrow(
                () ->  new ResourceNotFoundException("Paragon not found with id " + paragonUUID)
        ));
    }

    @SneakyThrows
    public ParagonDTO getParagon(String paragonUUID, Long userId) {
        log.info(TAG + "getParagon");
        Paragon paragon = paragonRepo.findParagonByUuid(paragonUUID).orElseThrow(
                () ->  new ResourceNotFoundException("Paragon not found with id " + paragonUUID));
        paragon.setUserId(userId);
        paragonRepo.save(paragon);
        return ParagonMapper.toDTO(paragon);
    }

    @SneakyThrows
    public List<ParagonDTO> getParagonsByUser() {
        log.info(TAG + "getParagonsByUser");
        return ParagonMapper.toDTOs(
                paragonRepo.getParagonsByUserId(
                        SecHolder.getPrincipal().getId()
                ));

    }

    @SneakyThrows
    public void createParagon(ParagonDTO paragonDTO) {
        log.info(TAG + "createParagon");
        paragonRepo.save(ParagonMapper.toEntity(paragonDTO));
    }
}
