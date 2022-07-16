package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Status.ResultStatus;
import org.springframework.stereotype.Component;

@Component
public interface CityServices
{
    ResultStatus getAllCity();
    ResultStatus getCityById(Long idCity);
}