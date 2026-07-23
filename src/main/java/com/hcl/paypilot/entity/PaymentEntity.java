package com.hcl.paypilot.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

/**
 * 
 * Entity class representing a payment in the PayPilot application.
 *
 * 
 * 
 * This class is mapped to the PAYMENT_TABLE table in Oracle Database.
 * 
 * It stores bill payment details, payment amount,
 * 
 * payment date, payment status,
 * 
 * and payment response message.
 *
 * 
 * 
 * @author PayPilotTeam
 * 
 * @version 1.0
 * 
 */

@Entity

@Table(name = "PAYMENT_TABLE")

public class PaymentEntity {

	/**
	 * 
	 * Unique Payment ID.
	 * 
	 */

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "payment_id")

	private Long paymentId;

	/**
	 * 
	 * User ID associated with the payment.
	 * 
	 */

	@Column(name = "user_id")

	private String userId;

	/**
	 * 
	 * Bill ID associated with the payment.
	 * 
	 */

	@Column(name = "bill_id")

	private Long billId;

	/**
	 * 
	 * Amount paid towards the bill.
	 * 
	 */

	@Column(name = "paid_amount")

	private Double paidAmount;

	/**
	 * 
	 * Date and time when the payment was made.
	 * 
	 */

	@Column(name = "payment_date")

	private LocalDateTime paymentDate;

	/**
	 * 
	 * Status of the payment.
	 * 
	 * Example: SUCCESS, FAILED, PENDING
	 * 
	 */

	@Column(name = "payment_status")

	private String paymentStatus;

	/**
	 * 
	 * Payment response message.
	 * 
	 * Example: Payment Successful
	 * 
	 */

	@Column(name = "message")

	private String message;

	/**
	 * 
	 * Default Constructor.
	 * 
	 */

	public PaymentEntity() {

	}

	/**
	 * 
	 * Parameterized Constructor.
	 *
	 * 
	 * 
	 * @param paymentId     Payment ID
	 * 
	 * @param userId        User ID
	 * 
	 * @param billId        Bill ID
	 * 
	 * @param paidAmount    Paid Amount
	 * 
	 * @param paymentDate   Payment Date and Time
	 * 
	 * @param paymentStatus Payment Status
	 * 
	 * @param message       Payment Message
	 * 
	 */

	public PaymentEntity(Long paymentId,
			String userId,
			Long billId,
			Double paidAmount,
			LocalDateTime paymentDate,
			String paymentStatus,
			String message) {

		super();
		this.paymentId = paymentId;
		this.userId = userId;
		this.billId = billId;
		this.paidAmount = paidAmount;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.message = message;

	}

	/**
	 * 
	 * Gets Payment ID.
	 *
	 * 
	 * 
	 * @return paymentId
	 * 
	 */

	public Long getPaymentId() {

		return paymentId;

	}

	/**
	 * 
	 * Sets Payment ID.
	 *
	 * 
	 * 
	 * @param paymentId Payment ID
	 * 
	 */

	public void setPaymentId(Long paymentId) {

		this.paymentId = paymentId;

	}

	/**
	 * 
	 * Gets User ID.
	 *
	 * 
	 * 
	 * @return userId
	 * 
	 */

	public String getUserId() {

		return userId;

	}

	/**
	 * 
	 * Sets User ID.
	 *
	 * 
	 * 
	 * @param userId User ID
	 * 
	 */

	public void setUserId(String userId) {

		this.userId = userId;

	}

	/**
	 * 
	 * Gets Bill ID.
	 *
	 * 
	 * 
	 * @return billId
	 * 
	 */

	public Long getBillId() {

		return billId;

	}

	/**
	 * 
	 * Sets Bill ID.
	 *
	 * 
	 * 
	 * @param billId Bill ID
	 * 
	 */

	public void setBillId(Long billId) {

		this.billId = billId;

	}

	/**
	 * 
	 * Gets Paid Amount.
	 *
	 * 
	 * 
	 * @return paidAmount
	 * 
	 */

	public Double getPaidAmount() {

		return paidAmount;

	}

	/**
	 * 
	 * Sets Paid Amount.
	 *
	 * 
	 * 
	 * @param paidAmount Paid Amount
	 * 
	 */

	public void setPaidAmount(Double paidAmount) {

		this.paidAmount = paidAmount;

	}

	/**
	 * 
	 * Gets Payment Date and Time.
	 *
	 * 
	 * 
	 * @return paymentDate
	 * 
	 */

	public LocalDateTime getPaymentDate() {

		return paymentDate;

	}

	/**
	 * 
	 * Sets Payment Date and Time.
	 *
	 * 
	 * 
	 * @param paymentDate Payment Date and Time
	 * 
	 */

	public void setPaymentDate(LocalDateTime paymentDate) {

		this.paymentDate = paymentDate;

	}

	/**
	 * 
	 * Gets Payment Status.
	 *
	 * 
	 * 
	 * @return paymentStatus
	 * 
	 */

	public String getPaymentStatus() {

		return paymentStatus;

	}

	/**
	 * 
	 * Sets Payment Status.
	 *
	 * 
	 * 
	 * @param paymentStatus Payment Status
	 * 
	 */

	public void setPaymentStatus(String paymentStatus) {

		this.paymentStatus = paymentStatus;

	}

	/**
	 * 
	 * Gets Payment Message.
	 *
	 * 
	 * 
	 * @return message
	 * 
	 */

	public String getMessage() {

		return message;

	}

	/**
	 * 
	 * Sets Payment Message.
	 *
	 * 
	 * 
	 * @param message Payment Message
	 * 
	 */

	public void setMessage(String message) {

		this.message = message;

	}

}
