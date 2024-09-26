package com.alex.eko.paragon.image;



import com.alex.eko.paragon.utils.exceptions.errors.ResourceNotFoundException;
import com.alex.eko.paragon.security.repo.SH;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepo imageRepo;

    public void uploadImage(MultipartFile file, Boolean isPublic) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setCreatedBy(SH.getUserId());
        image.setIsPublic(isPublic != null && isPublic);
        image.setType(file.getContentType());
        image.setByteData(ImageCompressor.compress(file.getBytes()));
        imageRepo.save(image);
    }

    @SneakyThrows
    @Transactional
    public Image getFileById(Long id) {
        Image dbImage = imageRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Image not found")
        );

        dbImage.setByteData(ImageCompressor.decompress(dbImage.getByteData()));
        return dbImage;
    }


    @SneakyThrows
    public void deleteImage(Long id) {
        Image dbImage = imageRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Image not found")
        );
        if (!dbImage.getCreatedBy().equals(SH.getUserId())) {
            throw new ResourceNotFoundException("You can delete only your documents");
        }
        imageRepo.deleteById(id);
    }
}
