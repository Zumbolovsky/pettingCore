package br.com.guilinssolution.pettingCore.services.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import br.com.guilinssolution.pettingCore.exception.ApplicationException;
import br.com.guilinssolution.pettingCore.model.entities.PostAnimalEntity;
import br.com.guilinssolution.pettingCore.model.entities.PostItemEntity;
import br.com.guilinssolution.pettingCore.model.entities.UsurEntity;
import br.com.guilinssolution.pettingCore.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.guilinssolution.pettingCore.services.MessageService;

@Service
public class StorageServiceImpl implements StorageService {

	private final MessageService message;

	@Value("${folder.path.usur}")
	private Path usurFileFolderPath;

	@Value("${folder.path.post-animal}")
	private Path postAnimalFileFolderPath;

	@Value("${folder.path.post-item}")
	private Path postItemFileFolderPath;

	private static String image = "image/";

	@Autowired
	public StorageServiceImpl(MessageService message) {
		this.message = message;
	}

	@Override
	public void storeFile(MultipartFile file, UsurEntity usurEntity) {
		try {
			Files.copy(file.getInputStream(), this.usurFileFolderPath.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);

			File oldfile = new File(this.usurFileFolderPath.resolve(file.getOriginalFilename()).toString());
			File newfile = new File(this.usurFileFolderPath.resolve(usurEntity.getNameUsur()
					+ usurEntity.getCpfUsur()).toString()
					+ Objects.requireNonNull(file.getContentType()).replace(image, "."));

			boolean validFileRename = oldfile.renameTo(newfile);
			toUseBoolean(validFileRename);
		} catch (Exception e) {
			throw new ApplicationException(this.message.getMessage("storage.store-error"), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void updateFile(MultipartFile file, UsurEntity usurEntity) {
		try {
			if (usurEntity.getImageUsur() != null) {
				Files.delete(Paths.get(usurEntity.getImageUsur()));
			}
			usurEntity.setImageUsur(this.usurFileFolderPath.toString() + "\\"
					+ usurEntity.getNameUsur() + usurEntity.getCpfUsur()
					+ Objects.requireNonNull(file.getContentType()).replace(image, "."));
			Files.copy(file.getInputStream(), this.usurFileFolderPath.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			File oldfile = new File(this.usurFileFolderPath.resolve(file.getOriginalFilename()).toString());
			File newfile = new File(this.usurFileFolderPath.resolve(usurEntity.getNameUsur()
					+ usurEntity.getCpfUsur()).toString()
					+ file.getContentType().replace(image, "."));

			boolean validFileRename = oldfile.renameTo(newfile);
			toUseBoolean(validFileRename);
		} catch (Exception e) {
			throw new ApplicationException(this.message.getMessage("storage.store-error"), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void storeFile(MultipartFile file, PostAnimalEntity postAnimalEntity) {
		try {
			Files.copy(file.getInputStream(), this.postAnimalFileFolderPath.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);

			File oldfile = new File(this.postAnimalFileFolderPath.resolve(file.getOriginalFilename()).toString());
			File newfile = new File(this.postAnimalFileFolderPath.resolve(postAnimalEntity.getTitlePostAnimal()
					+ postAnimalEntity.getIdPostAnimal().toString()).toString()
					+ Objects.requireNonNull(file.getContentType()).replace(image, "."));

			boolean validFileRename = oldfile.renameTo(newfile);
			toUseBoolean(validFileRename);
		} catch (Exception e) {
			throw new ApplicationException(this.message.getMessage("storage.store-error"), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void updateFile(MultipartFile file, PostAnimalEntity postAnimalEntity) {
		try {
			if (postAnimalEntity.getImagePostAnimal() != null) {
				Files.delete(Paths.get(postAnimalEntity.getImagePostAnimal()));
			}
			postAnimalEntity.setImagePostAnimal(this.postAnimalFileFolderPath.toString() + "\\"
					+ postAnimalEntity.getTitlePostAnimal() + postAnimalEntity.getIdPostAnimal().toString()
					+ Objects.requireNonNull(file.getContentType()).replace(image, "."));
			Files.copy(file.getInputStream(), this.postAnimalFileFolderPath.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			File oldfile = new File(this.postAnimalFileFolderPath.resolve(file.getOriginalFilename()).toString());
			File newfile = new File(this.postAnimalFileFolderPath.resolve(postAnimalEntity.getTitlePostAnimal()
					+ postAnimalEntity.getIdPostAnimal()).toString()
					+ file.getContentType().replace(image, "."));

			boolean validFileRename = oldfile.renameTo(newfile);
			toUseBoolean(validFileRename);
		} catch (Exception e) {
			throw new ApplicationException(this.message.getMessage("storage.store-error"), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void storeFile(MultipartFile file, PostItemEntity postItemEntity) {
		try {
			Files.copy(file.getInputStream(), this.postItemFileFolderPath.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);

			File oldfile = new File(this.postItemFileFolderPath.resolve(file.getOriginalFilename()).toString());
			File newfile = new File(this.postItemFileFolderPath.resolve(postItemEntity.getTitlePostItem()
					+ postItemEntity.getTypePostItem()).toString() + postItemEntity.getIdPostItem().toString()
					+ Objects.requireNonNull(file.getContentType()).replace(image, "."));

			boolean validFileRename = oldfile.renameTo(newfile);
			toUseBoolean(validFileRename);
		} catch (Exception e) {
			throw new ApplicationException(this.message.getMessage("storage.store-error"), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void updateFile(MultipartFile file, PostItemEntity postItemEntity) {
		try {
			if (postItemEntity.getImagePostItem() != null) {
				Files.delete(Paths.get(postItemEntity.getImagePostItem()));
			}
			postItemEntity.setImagePostItem(this.postItemFileFolderPath.toString() + "\\" + postItemEntity.getTitlePostItem()
					+ postItemEntity.getTypePostItem().toString() + postItemEntity.getIdPostItem().toString()
					+ Objects.requireNonNull(file.getContentType()).replace(image, "."));
			Files.copy(file.getInputStream(), this.postItemFileFolderPath.resolve(file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			File oldfile = new File(this.postItemFileFolderPath.resolve(file.getOriginalFilename()).toString());
			File newfile = new File(this.postItemFileFolderPath.resolve(postItemEntity.getTitlePostItem()
					+ postItemEntity.getTypePostItem()).toString() + postItemEntity.getIdPostItem()
					+ file.getContentType().replace(image, "."));

			boolean validFileRename = oldfile.renameTo(newfile);
			toUseBoolean(validFileRename);
		} catch (Exception e) {
			throw new ApplicationException(this.message.getMessage("storage.store-error"), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public Resource loadFile(UsurEntity usurEntity) {
		try {
			Path file = Paths.get(usurEntity.getImageUsur());
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new ApplicationException(this.message.getMessage("storage.invalid-image"), HttpStatus.NOT_FOUND);
			}
		} catch (MalformedURLException e) {
			throw new ApplicationException(this.message.getMessage("storage.load-error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Resource loadFile(PostAnimalEntity postAnimalEntity) {
		try {
			Path file = Paths.get(postAnimalEntity.getImagePostAnimal());
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new ApplicationException(this.message.getMessage("storage.invalid-image"), HttpStatus.NOT_FOUND);
			}
		} catch (MalformedURLException e) {
			throw new ApplicationException(this.message.getMessage("storage.load-error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Resource loadFile(PostItemEntity postItemEntity) {
		try {
			Path file = Paths.get(postItemEntity.getImagePostItem());
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new ApplicationException(this.message.getMessage("storage.invalid-image"), HttpStatus.NOT_FOUND);
			}
		} catch (MalformedURLException e) {
			throw new ApplicationException(this.message.getMessage("storage.load-error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(this.postAnimalFileFolderPath);
			Files.createDirectories(this.postItemFileFolderPath);
			Files.createDirectories(this.usurFileFolderPath);
		} catch (IOException e) {
			throw new ApplicationException(this.message.getMessage("storage.initializiation-error"), HttpStatus.BAD_REQUEST);
		}
	}
	
	private void toUseBoolean(Boolean validFileRename) {
		if(!validFileRename) {
			throw new ApplicationException(this.message.getMessage("storage.store-error"), HttpStatus.CONFLICT);
		}
	}

}