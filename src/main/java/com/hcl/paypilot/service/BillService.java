package com.hcl.paypilot.service;

import java.util.List;


import com.hcl.paypilot.entity.BillEntity;

public interface BillService {

    String addBill(BillEntity bill);
    String updateBill(Long billId, BillEntity bill);
    String deleteBill(Long billId);
    String setReminder(Long billId);
    String snoozeBill(Long billId);
    List<BillEntity> getUserBills(String userId);
    BillEntity getBillById(Long billId);
    String unSnoozeBill(Long billId);
    
    List<BillEntity> getPendingBills(String userId);


    String enableSchedulePayment(Long billId);


    String autoPayBills();

    String disableSchedulePayment(Long billId);
    
}
 