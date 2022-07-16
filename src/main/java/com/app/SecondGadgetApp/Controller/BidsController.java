//package com.app.SecondGadgetApp.Controller;
//
//import com.app.SecondGadgetApp.Dto.BidsDTO;
//import com.app.SecondGadgetApp.Dto.ResponseDTO;
//import com.app.SecondGadgetApp.Entity.Bids;
//import com.app.SecondGadgetApp.Service.BidsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/bid")
//public class BidsController
//{
//    @Autowired
//    BidsService bidsService;
//
//    @PostMapping("/buyer/add")
//    public ResponseEntity<?> add(@RequestBody BidsDTO bidsDTO)
//    {
//        bidsService.add_bid(bidsDTO);
//        return new ResponseEntity<>(new ResponseDTO("201", "Penawaran Berhasil Ditambahkan"), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/display-all")
//    public ResponseEntity<?> display_all()
//    {
//        return new ResponseEntity<>(new ResponseDTO("200", "success", bidsService.display_all()), HttpStatus.OK);
//    }
//
//    @GetMapping("/display/{id}")
//    public ResponseEntity<?> display_category(@PathVariable("id") Long id)
//    {
//        if(bidsService.display_bid(id) != null)
//        {
//            return new ResponseEntity<>(new ResponseDTO("200", "success", bidsService.display_bid(id)), HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity<>(new ResponseDTO("404", "bid not found"), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PutMapping("/status/processed/{id}")
//    public ResponseEntity<?> processed(@PathVariable("id") Long id)
//    {
//        bidsService.accept_bid(id);
//        return new ResponseEntity<>(new ResponseDTO("202", "status processed"), HttpStatus.ACCEPTED);
//    }
//
//    @PutMapping("/status/accepted/{id}")
//    public ResponseEntity<?> accepted(@PathVariable("id") Long id)
//    {
//        bidsService.transaction_accepted(id);
//        return new ResponseEntity<>(new ResponseDTO("202", "status accepted"), HttpStatus.ACCEPTED);
//    }
//
//    @PutMapping("/status/declined/{id}")
//    public ResponseEntity<?> declined(@PathVariable("id") Long id)
//    {
//        bidsService.transaction_declined(id);
//        return new ResponseEntity<>(new ResponseDTO("202", "status declined"), HttpStatus.ACCEPTED);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody BidsDTO bidsDTO)
//    {
//        return new ResponseEntity<>(new ResponseDTO("200", "success", bidsService.update_bid(id,bidsDTO)), HttpStatus.OK);
//    }
//
//    @GetMapping("/buyer/detail/{bidId}")
//    public ResponseEntity<?> display_bid_buyer(@PathVariable("bidId") Long bidId)
//    {
//        if(bidsService.display_bid_buyer(bidId) != null)
//        {
//            return new ResponseEntity<>(new ResponseDTO("200", "Penawaran Berhasil Ditampilkan", bidsService.display_bid_buyer(bidId)), HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity<>(new ResponseDTO("404", "Penawaran Tidak Ditemukan"), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("/buyer/all/{sellerId}")
//    public ResponseEntity<?> display_all_buyer(@PathVariable("sellerId") Long sellerId)
//    {
//        if(bidsService.display_all_buyer(sellerId) != null)
//        {
//            return new ResponseEntity<>(new ResponseDTO("200", "Penawaran Berhasil Ditampilkan", bidsService.display_all_buyer(sellerId)), HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity<>(new ResponseDTO("404", "Penawaran Tidak Ditemukan"), HttpStatus.BAD_REQUEST);
//        }
//    }
//}
