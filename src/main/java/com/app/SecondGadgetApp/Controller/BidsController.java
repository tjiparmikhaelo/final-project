package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.BidsDTO;
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

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody BidsDTO bidsDTO)
    {
        return new ResponseEntity<>(bidsService.add_bid(bidsDTO), HttpStatus.CREATED);
    }

    @GetMapping("/display-all")
    public ResponseEntity<?> display_all()
    {
        return new ResponseEntity<>(bidsService.display_all(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/display/{id}")
    public ResponseEntity<?> display_category(@PathVariable("id") Long id)
    {
        if(bidsService.display_bid(id) != null)
        {
            return new ResponseEntity<>(bidsService.display_bid(id), HttpStatus.ACCEPTED);
        }
        else
        {
            return new ResponseEntity<>("bid not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody BidsDTO bidsDTO)
    {
        return new ResponseEntity<>(bidsService.update_bid(id,bidsDTO), HttpStatus.ACCEPTED);
    }
}
