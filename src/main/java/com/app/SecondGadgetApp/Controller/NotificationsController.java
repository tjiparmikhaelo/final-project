package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.NotificationsDTO;
import com.app.SecondGadgetApp.Dto.ResponseDTO;
import com.app.SecondGadgetApp.Service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationsController
{
    @Autowired
    NotificationsService notificationsService;

    @PostMapping("/add")
    public ResponseEntity<?> add_notification(@RequestBody NotificationsDTO notificationsDTO)
    {
        notificationsService.add_notification(notificationsDTO);
        return new ResponseEntity<>(new ResponseDTO("201", "Notifikasi Berhasil Ditampilkan"), HttpStatus.CREATED);
    }

    @GetMapping("/buyer/all/{id}")
    public ResponseEntity<?> notification_buyer_all(@PathVariable("id") Long userId)
    {
        return new ResponseEntity<>(new ResponseDTO("200", "Notifikasi Pengguna Berhasil Ditampilkan", notificationsService.notification_buyer_all(userId)), HttpStatus.OK);
    }

    @GetMapping("/seller/all/{id}")
    public ResponseEntity<?> notification_seller_all(@PathVariable("id") Long userId)
    {
        return new ResponseEntity<>(new ResponseDTO("200", "Notifikasi Toko Berhasil Ditampilkan", notificationsService.notification_seller_all(userId)), HttpStatus.OK);
    }
}