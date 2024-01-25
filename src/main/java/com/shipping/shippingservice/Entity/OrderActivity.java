package com.shipping.shippingservice.Entity;

import java.util.Date;

import com.shipping.shippingservice.Dto.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderActivity {
    OrderStatus status;
    Date eventTime;
}
