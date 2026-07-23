package com.hcl.paypilot.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import com.hcl.paypilot.entity.BillEntity;

import com.hcl.paypilot.service.BillService;


@RestController

@RequestMapping("/api/bills")

@CrossOrigin(origins = "*")

public class BillController {


    @Autowired

    private BillService billService;


    @PostMapping("/add")

    public String addBill(@RequestBody BillEntity bill) {

        return billService.addBill(bill);

    }


    @PutMapping("/update/{billId}")

    public String updateBill(

            @PathVariable Long billId,

            @RequestBody BillEntity bill) {


        return billService.updateBill(billId, bill);

    }


    @DeleteMapping("/delete/{billId}")

    public String deleteBill(@PathVariable Long billId) {


        return billService.deleteBill(billId);

    }


    @PutMapping("/set-reminder/{billId}")

    public String setReminder(@PathVariable Long billId) {


        return billService.setReminder(billId);

    }


    @PutMapping("/snooze/{billId}")

    public String snoozeBill(@PathVariable Long billId) {


        return billService.snoozeBill(billId);

    }


    @GetMapping("/user/{userId}")

    public List<BillEntity> getUserBills(@PathVariable String userId) {


        return billService.getUserBills(userId);

    }
    
    @PutMapping("/unsnooze/{billId}")

    public String unSnoozeBill(

            @PathVariable Long billId) {


        return billService.unSnoozeBill(billId);

    }
     


    @GetMapping("/{billId}")

    public BillEntity getBillById(@PathVariable Long billId) {


        return billService.getBillById(billId);

    }
    
    @GetMapping("/pending/{userId}")

    public List<BillEntity> getPendingBills(

            @PathVariable String userId) {


        return billService

                .getPendingBills(userId);

    }
    
    @PutMapping("/schedule/{billId}")

    public String enableSchedulePayment(

            @PathVariable Long billId) {


        return billService

                .enableSchedulePayment(billId);

    }
    
    @PostMapping("/autopay")

    public String autoPayBills() {


        return billService.autoPayBills();

    }
     
    
    @PutMapping("/unschedule/{billId}")

    public String disableSchedulePayment(

            @PathVariable Long billId) {


        return billService.disableSchedulePayment(billId);

    }

    
     
     
     

}
 