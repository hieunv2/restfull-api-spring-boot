//package vn.javisco.agency.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.UrlResource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//import vn.javisco.agency.entity.File;
//import vn.javisco.agency.exceptions.FileStorageException;
//import vn.javisco.agency.repository.FileRepository;
//
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//
//@Service
//public class FileService {
//
//    private final Path fileStorageLocation;
//
//    private final FileRepository fileRepository;
//
//    private final File file;
//
//    @Autowired
//    public FileService(File file, FileRepository fileRepository) {
//        this.fileStorageLocation = Paths.get(file.getUploadDir())
//                .toAbsolutePath().normalize();
//        this.fileRepository = fileRepository;
//        this.file = file;
//        try {
//            Files.createDirectories(this.fileStorageLocation);
//        } catch (Exception ex) {
//            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
//        }
//    }
//    public String storeFile(MultipartFile file, String type) {
//        // Normalize file name
//        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
//        String fileName = "";
//        try {
//            // Check if the file's name contains invalid characters
//            if(originalFileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + originalFileName);
//            }
//            String fileExtension = "";
//            try {
//                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
//            } catch(Exception e) {
//                fileExtension = "";
//            }
//            fileName = type + fileExtension;
//            // Copy file to the target location (Replacing existing file with the same name)
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//            File newFile = new File();
//            newFile.setPath(targetLocation.toString());
//            newFile.setName(fileName);
//            newFile.setType(type);
//            fileRepository.save(newFile);
//            return fileName;
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }
//    public Resource loadFileAsResource(String fileName) throws Exception {
//        try {
//            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//            if(resource.exists()) {
//                return resource;
//            } else {
//                throw new FileNotFoundException("File not found " + fileName);
//            }
//        } catch (MalformedURLException ex) {
//            throw new FileNotFoundException("File not found " + fileName);
//        }
//    }
//    public String getDocumentName(String path) {
//        return fileRepository.findByPath(path).getName();
//    }
//}