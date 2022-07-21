package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.NotificationsDTO;
import com.app.SecondGadgetApp.Dto.ResponseDTO;
import com.app.SecondGadgetApp.Service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Transactional
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

    @GetMapping("/buyer/mini/{id}")
    public ResponseEntity<?> notification_buyer_mini(@PathVariable("id") Long userId)
    {
        return new ResponseEntity<>(new ResponseDTO("200", "Notifikasi Pengguna Berhasil Ditampilkan", notificationsService.notification_buyer_mini(userId)), HttpStatus.OK);
    }

    @DeleteMapping("/buyer/delete/{id}")
    public ResponseEntity<?> delete_buyer(@PathVariable("id") Long userId)
    {
        notificationsService.delete_buyer(userId);
        return new ResponseEntity<>(new ResponseDTO("200", "Notifikasi Pengguna Berhasil Dihapus"), HttpStatus.OK);
    }

    @DeleteMapping("/buyer/delete-all/{id}")
    public ResponseEntity<?> delete_buyer_all(@PathVariable("id") Long userId)
    {
        notificationsService.delete_all_buyer(userId);
        return new ResponseEntity<>(new ResponseDTO("200", "Notifikasi Pengguna Berhasil Dihapus"), HttpStatus.OK);
    }

    @GetMapping("/seller/all/{id}")
    public ResponseEntity<?> notification_seller_all(@PathVariable("id") Long userId)
    {
        return new ResponseEntity<>(new ResponseDTO("200", "Notifikasi Toko Berhasil Ditampilkan", notificationsService.notification_seller_all(userId)), HttpStatus.OK);
    }

    @GetMapping("/seller/mini/{id}")
    public ResponseEntity<?> notification_seller_mini(@PathVariable("id") Long userId)
    {
        return new ResponseEntity<>(new ResponseDTO("200", "Notifikasi Toko Berhasil Ditampilkan", notificationsService.notification_seller_mini(userId)), HttpStatus.OK);
    }

    @DeleteMapping("/seller/delete/{id}")
    public ResponseEntity<?> delete_seller(@PathVariable("id") Long userId)
    {
        notificationsService.delete_seller(userId);
        return new ResponseEntity<>(new ResponseDTO("200", "Notifikasi Toko Berhasil Dihapus"), HttpStatus.OK);
    }

    @DeleteMapping("/seller/delete-all/{id}")
    public ResponseEntity<?> delete_seller_all(@PathVariable("id") Long userId)
    {
        notificationsService.delete_all_seller(userId);
        return new ResponseEntity<>(new ResponseDTO("200", "Notifikasi Toko Berhasil Dihapus"), HttpStatus.OK);
    }
}