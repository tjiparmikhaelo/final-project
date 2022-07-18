package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.BidsDTO;
import com.app.SecondGadgetApp.Dto.ResponseDTO;
import com.app.SecondGadgetApp.Service.BidsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bid")
public class BidsController
{
    @Autowired
    BidsService bidsService;

    @PostMapping("/buyer/add")
    public ResponseEntity<?> add_bid(@RequestBody BidsDTO bidsDTO)
    {
        return new ResponseEntity<>(new ResponseDTO("201", "Penawaran Berhasil Ditambahkan"), HttpStatus.CREATED);
    }

    @GetMapping("buyer/all/{id}")
    public ResponseEntity<?> show_all_bid_buyer(@PathVariable("id") Long userId)
    {
        return new ResponseEntity<>(new ResponseDTO("200", "Penawaran Berhasil Ditampilkan", bidsService.show_all_bid_buyer(userId)), HttpStatus.OK);
    }

    @PutMapping("buyer/edit/{id}")
    public ResponseEntity<?> edit_buyer(@PathVariable("id") Long bidId, @RequestBody BidsDTO bidsDTO)
    {
        bidsService.edit_bid_buyer(bidId, bidsDTO);
        return new ResponseEntity<>(new ResponseDTO("200", "Penawaran Berhasil Diupdate"), HttpStatus.OK);
    }

    @GetMapping("seller/all/{id}")
    public ResponseEntity<?> show_all_bid_seller(@PathVariable("id") Long userId)
    {
        return new ResponseEntity<>(new ResponseDTO("200", "Penawaran Berhasil Ditampilkan", bidsService.show_all_bid_seller(userId)), HttpStatus.OK);
    }

    @PutMapping("seller/edit/{id}")
    public ResponseEntity<?> edit_seller(@PathVariable("id") Long bidId, @RequestBody BidsDTO bidsDTO)
    {
        bidsService.edit_bid_seller(bidId, bidsDTO);
        return new ResponseEntity<>(new ResponseDTO("200", "Penawaran Berhasil Diupdate"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> show_detail_bid_seller(@PathVariable("id") Long bidId)
    {
        return new ResponseEntity<>(new ResponseDTO("200", "Penawaran Berhasil Ditampilkan", bidsService.show_detail_bid_buyer_seller(bidId)), HttpStatus.OK);
    }
}
