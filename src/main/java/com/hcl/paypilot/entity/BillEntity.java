package com.hcl.paypilot.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

/**
 * Entity class representing a bill in the PayPilot application.
 *
 * This class is mapped to the BILL_TAB table in Oracle Database. It stores bill
 * details, reminder settings, bill status information, due dates, and snooze
 * details.
 *
 * @author PayPilotTeam
 * @version 1.0
 */
@Entity
@Table(name = "BILL_TAB")
public class BillEntity {
	/**
	 * Unique Bill ID.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "bill_id")
	private Long billId;
	/**
	 * User ID associated with the bill.
	 */
	@Column(name = "user_id")
	private String userId;
	/**
	 * Name of the bill. Example: Electricity Bill, Water Bill
	 */
	@Column(name = "bill_name")
	private String billName;
	/**
	 * Category of the bill. Example: Utilities, Insurance, Subscription
	 */
	@Column(name = "bill_category")
	private String billCategory;
	/**
	 * Amount to be paid for the bill.
	 */
	@Column(name = "bill_amount")
	private double billAmount;
	/**
	 * Due date of the bill.
	 */
	@Column(name = "bill_due_date")
	private LocalDate billDueDate;

	/**
	 * Indicates whether reminder is enabled. Example: YES, NO
	 */
	@Column(name = "reminder_enabled")
	private String reminderEnabled;
	/**
	 * Date on which reminder should be sent.
	 */
	@Column(name = "reminder_date")
	private LocalDate reminderDate;
	/**
	 * Current status of the bill. Example: PENDING, PAID, OVERDUE
	 */
	@Column(name = "bill_status")
	private String billStatus;

	/**
	 * Date until which the bill is snoozed.
	 */
	@Column(name = "snooze_date")
	private LocalDate snoozeDate;
	
	@Column(name="previousReminderStatus")
	private String previousReminderStatus;
	
	@Column (name="shedulePayment")
	private boolean shedulePayment;

	

	/**
	 * 
	 *
	 * @param billId          Bill ID
	 * @param userId          User ID
	 * @param billName        Bill Name
	 * @param billCategory    Bill Category
	 * @param billAmount      Bill Amount
	 * @param billDueDate     Bill Due Date
	 * @param reminderEnabled Reminder Enabled Status
	 * @param reminderDate    Reminder Date
	 * @param billStatus      Bill Status
	 * @param snoozeDate      Snooze Date
	 */
	
	/**
	 * Default Constructor.
	 */
	public BillEntity() {
	}
	

	/**
	 * Gets Bill ID.
	 *
	 * @return billId
	 */
	public Long getBillId() {
		return billId;
	}

	/**
	 * Sets Bill ID.
	 *
	 * @param billId Bill ID
	 */
	public void setBillId(Long billId) {
		this.billId = billId;
	}

	/**
	 * Gets User ID.
	 *
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets User ID.
	 *
	 * @param userId User ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets Bill Name.
	 *
	 * @return billName
	 */
	public String getBillName() {
		return billName;
	}

	/**
	 * Sets Bill Name.
	 *
	 * @param billName Bill Name
	 */
	public void setBillName(String billName) {
		this.billName = billName;
	}

	/**
	 * Gets Bill Category.
	 *
	 * @return billCategory
	 */
	public String getBillCategory() {
		return billCategory;
	}

	/**
	 * Sets Bill Category.
	 *
	 * @param billCategory Bill Category
	 */
	public void setBillCategory(String billCategory) {
		this.billCategory = billCategory;
	}

	/**
	 * Gets Bill Amount.
	 *
	 * @return billAmount
	 */
	public double getBillAmount() {
		return billAmount;
	}

	/**
	 * Sets Bill Amount.
	 *
	 * @param billAmount Bill Amount
	 */
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	/**
	 * Gets Bill Due Date.
	 *
	 * @return billDueDate
	 */
	public LocalDate getBillDueDate() {
		return billDueDate;
	}

	/**
	 * Sets Bill Due Date.
	 *
	 * @param billDueDate Bill Due Date
	 */
	public void setBillDueDate(LocalDate billDueDate) {
		this.billDueDate = billDueDate;
	}

	/**
	 * Gets Reminder Enabled Status.
	 *
	 * @return reminderEnabled
	 */
	public String getReminderEnabled() {
		return reminderEnabled;
	}

	/**
	 * Sets Reminder Enabled Status.
	 *
	 * @param reminderEnabled Reminder Enabled Status
	 */
	public void setReminderEnabled(String reminderEnabled) {
		this.reminderEnabled = reminderEnabled;
	}

	/**
	 * 
	 * Gets Reminder Date.
	 *
	 * 
	 * 
	 * @return reminderDate
	 * 
	 */

	public LocalDate getReminderDate() {

		return reminderDate;

	}

	/**
	 * 
	 * Sets Reminder Date.
	 *
	 * 
	 * 
	 * @param reminderDate Reminder Date
	 * 
	 */

	public void setReminderDate(LocalDate reminderDate) {

		this.reminderDate = reminderDate;

	}

	/**
	 * 
	 * Gets Bill Status.
	 *
	 * 
	 * 
	 * @return billStatus
	 * 
	 */

	public String getBillStatus() {

		return billStatus;

	}

	/**
	 * 
	 * Sets Bill Status.
	 *
	 * 
	 * 
	 * @param billStatus Bill Status
	 * 
	 */

	public void setBillStatus(String billStatus) {

		this.billStatus = billStatus;

	}

	/**
	 * 
	 * Gets Snooze Date.
	 *
	 * 
	 * 
	 * @return snoozeDate
	 * 
	 */

	public LocalDate getSnoozeDate() {
		return snoozeDate;
	}

	/**
	 * 
	 * Sets Snooze Date.
	 *
	 * 
	 * 
	 * @param snoozeDate Snooze Date
	 * 
	 */

	public void setSnoozeDate(LocalDate snoozeDate) {
		this.snoozeDate = snoozeDate;
	}

	public String getPreviousReminderStatus() {
		return previousReminderStatus;
	}

	public void setPreviousReminderStatus(String previousReminderStatus) {
		this.previousReminderStatus = previousReminderStatus;
	}


	public boolean isShedulePayment() {
		return shedulePayment;
	}


	public void setShedulePayment(boolean shedulePayment) {
		this.shedulePayment = shedulePayment;
	}
	
	
	
	
}
