package com.app.SecondGadgetApp.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinaryAccount(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name","ainokami",
                "api_key","315165863868633",
                "api_secret","yxOKmk7XtRgNgJLFtVid1D1e1bE"
        ));
    }
}