package br.com.guilinssolution.pettingCore.services;

import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostItemEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    
    void storeFile(MultipartFile file, UsurEntity usurEntity);
    
    void updateFile(MultipartFile file, UsurEntity usurEntity);

    void storeFile(MultipartFile file, PostAnimalEntity postAnimalEntity);

    void updateFile(MultipartFile file, PostAnimalEntity postAnimalEntity);

    void storeFile(MultipartFile file, PostItemEntity postItemEntity);

    void updateFile(MultipartFile file, PostItemEntity postItemEntity);

    Resource loadFile(UsurEntity usurEntity);

    Resource loadFile(PostAnimalEntity postAnimalEntity);

    Resource loadFile(PostItemEntity postItemEntity);

    void init();
    
}
