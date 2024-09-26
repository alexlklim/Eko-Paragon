package com.alex.eko.paragon.image;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("${api.base-path}/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void upload(
            @RequestParam("image") MultipartFile file,
            @RequestParam("isPublic") Boolean isPublic
    ) throws IOException {
        imageService.uploadImage(file, isPublic);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        imageService.deleteImage(id);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Resource> getFileById(@PathVariable("id") Long id) {
        Image image = imageService.getFileById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getType()));  // Set the MIME type dynamically
        headers.setContentDispositionFormData("attachment", image.getName());  // Use original file name

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(image.getByteData().length)
                .body(new ByteArrayResource(image.getByteData()));
    }

}
