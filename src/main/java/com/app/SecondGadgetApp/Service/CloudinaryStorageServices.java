package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Status.DataResult;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryStorageServices
{
    DataResult<?>upload(MultipartFile multipartFile);
    DataResult<?>delete(String publicIdOfImage);
}
