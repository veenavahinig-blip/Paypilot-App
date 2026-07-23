package com.hcl.paypilot.service;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.hcl.paypilot.entity.BillEntity;

import com.hcl.paypilot.repository.BillRepository;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepository billRepository;

	@Override
	public String addBill(BillEntity bill) {
		billRepository.save(bill);
		return "Bill added successfully";

	}

	@Override
	public String updateBill(Long billId, BillEntity bill) {

		BillEntity existingBill = billRepository.findById(billId)

				.orElseThrow(() -> new RuntimeException("Bill not found with ID : " + billId));

		existingBill.setUserId(bill.getUserId());
		existingBill.setBillName(bill.getBillName());
		existingBill.setBillCategory(bill.getBillCategory());
		existingBill.setBillAmount(bill.getBillAmount());
		existingBill.setBillDueDate(bill.getBillDueDate());
		existingBill.setReminderEnabled(bill.getReminderEnabled());
		existingBill.setReminderDate(bill.getReminderDate());
		existingBill.setBillStatus(bill.getBillStatus());
		existingBill.setSnoozeDate(bill.getSnoozeDate());

		billRepository.save(existingBill);

		return "Bill updated successfully";

	}

	@Override
	public String deleteBill(Long billId) {
		BillEntity bill = billRepository.findById(billId)

				.orElseThrow(() -> new RuntimeException("Bill not found with ID : " + billId));

		billRepository.delete(bill);

		return "Bill deleted successfully";

	}

	@Override

	public String setReminder(Long billId) {

		BillEntity bill = billRepository.findById(billId)

				.orElseThrow(() -> new RuntimeException("Bill not found with ID : " + billId));

		bill.setReminderEnabled("YES");

		if (bill.getBillDueDate() != null) {

			bill.setReminderDate(bill.getBillDueDate().minusDays(3));

		}

		billRepository.save(bill);

		return "Reminder set successfully";

	}

	@Override

	public String snoozeBill(Long billId) {


	    BillEntity bill = billRepository.findById(billId)

	            .orElseThrow(() ->

	                    new RuntimeException(

	                            "Bill not found with ID : "

	                                    + billId));


	    // Store current reminder status

	    bill.setPreviousReminderStatus(

	            bill.getReminderEnabled());


	    // Update bill details

	    bill.setBillStatus("SNOOZED");


	    bill.setSnoozeDate(

	            LocalDate.now().plusDays(3));


	    // Disable reminder

	    bill.setReminderEnabled("NO");


	    billRepository.save(bill);


	    return "Bill Snoozed Successfully";

	}
	 
	 
	
	@Override

	public String unSnoozeBill(Long billId) {


	    BillEntity bill = billRepository.findById(billId)

	            .orElseThrow(() ->

	                    new RuntimeException(

	                            "Bill not found with ID : "

	                                    + billId));


	    // Back to pending

	    bill.setBillStatus("PENDING");


	    // Remove snooze date

	    bill.setSnoozeDate(null);


	    // Restore previous reminder value

	    if (bill.getPreviousReminderStatus() != null) {


	        bill.setReminderEnabled(

	                bill.getPreviousReminderStatus());


	    } else {


	        bill.setReminderEnabled("YES");


	    }


	    billRepository.save(bill);


	    return "Bill Unsnoozed Successfully";

	}
	 
	 

	@Override

	public List<BillEntity> getUserBills(String userId) {

		return billRepository.findByUserId(String.valueOf(userId));

	}

	@Override

	public BillEntity getBillById(Long billId) {

		return billRepository.findById(billId)

				.orElseThrow(() -> new RuntimeException("Bill not found with ID : " + billId));

	}
	
	@Override

	public List<BillEntity> getPendingBills(

	        String userId) {


	    return billRepository

	            .findByUserIdAndBillStatus(

	                    userId,

	                    "PENDING");

	}
	
	@Override

	public String enableSchedulePayment(

	        Long billId) {


	    BillEntity bill =

	            billRepository.findById(billId)

	                    .orElseThrow(() ->

	                            new RuntimeException(

	                                    "Bill not found"));


	    bill.setShedulePayment(true);


	    billRepository.save(bill);


	    return "Schedule Payment Enabled";

	}
	
	@Override

	public String autoPayBills() {


	    List<BillEntity> bills =

	            billRepository

	                    .findByBillStatusAndShedulePayment(

	                            "PENDING",

	                            true);


	    LocalDate today =

	            LocalDate.now();


	    for (BillEntity bill : bills) {


	        if (bill.getBillDueDate() != null

	                && bill.getBillDueDate()

	                        .equals(today)) {


	            bill.setBillStatus("PAID");


	            billRepository.save(bill);

	        }

	    }


	    return "Auto Payment Process Completed";

	}
	
	@Override

	public String disableSchedulePayment(Long billId) {


	    BillEntity bill = billRepository.findById(billId)

	            .orElseThrow(() ->

	                    new RuntimeException("Bill not found"));


	    bill.setShedulePayment(false);


	    billRepository.save(bill);


	    return "Auto Payment Disabled Successfully";

	}
	 
	 
	 
	 

}
