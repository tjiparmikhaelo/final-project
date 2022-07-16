package com.app.SecondGadgetApp.ServicesImpl;

import com.app.SecondGadgetApp.Service.CloudinaryStorageServices;
import com.app.SecondGadgetApp.Status.DataResult;
import com.app.SecondGadgetApp.Status.ErrorDataResult;
import com.app.SecondGadgetApp.Status.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class CloudinaryStorageServicesImpl implements CloudinaryStorageServices {
    private final Cloudinary cloudinary;
    @Override
    public DataResult<?> upload(MultipartFile multipartFile) {
        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
            return new SuccessDataResult<>(uploadResult);
        } catch (IOException e) {
            e.printStackTrace();
            return new ErrorDataResult<>();
        }
    }
    @Override
    public DataResult<?> delete(String publicIdOfImage) {
        return null;
    }
}
