package com.app.SecondGadgetApp.Dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.util.Date;

@Setter
@Getter
public class NotificationsDTO
{
    private Long notificationId;
    private Long bidId;
    private Date createdAt;
    private Date updatedAt;
}
