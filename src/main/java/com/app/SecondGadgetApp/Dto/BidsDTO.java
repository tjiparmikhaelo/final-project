package com.app.SecondGadgetApp.Dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class BidsDTO
{
    private Long bidId;
    private Long buyerId;
    private Long productId;
    private BigDecimal bidPrice;
    private String bidStatus;
    private Date createdAt;
    private Date updatedAt;
}
