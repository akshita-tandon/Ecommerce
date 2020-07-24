package com.springbootcamp.springsecurity.service;

import com.springbootcamp.springsecurity.entity.Product;
import com.springbootcamp.springsecurity.entity.ProductVariation;
import com.springbootcamp.springsecurity.entity.User;
import com.springbootcamp.springsecurity.repository.ProductRepository;
import com.springbootcamp.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@Service
public class ImageService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public String uploadImage(MultipartFile image, Principal principal) throws IOException {
        if (image.isEmpty()) {
            return "Please Upload an Image";
        }
        try {
            byte[] bytes = image.getBytes();
            String username = principal.getName();
            User user=userRepository.findByUsername(username);
            String fileName = renameFile(image.getOriginalFilename(), user);

            Path path = Paths.get("/home/akshita/Desktop/images/" + fileName);
            Files.write(path, bytes);

            user.setImage(fileName);
            userRepository.save(user);
            return image.getOriginalFilename();

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }


    private String renameFile(String fileName, User user) {
        Integer index = fileName.lastIndexOf(".");
        fileName = fileName.substring(index);
        Long id = user.getId();
        return id + fileName;
    }

    public String uploadImageProduct(MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            return "Please Upload an Image";
        }
        try {
            byte[] bytes = image.getBytes();

            String fileName = image.getOriginalFilename();

            Path path = Paths.get("/home/akshita/Desktop/images/" + fileName);
            Files.write(path, bytes);

            return image.getOriginalFilename();

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }


}

