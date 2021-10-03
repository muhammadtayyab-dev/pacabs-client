package com.techlogix.pacaps.models.bookingsModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCurrentBooking {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("customerid")
    @Expose
    private Integer customerid;
    @SerializedName("customername")
    @Expose
    private String customername;
    @SerializedName("triptypeid")
    @Expose
    private Integer triptypeid;
    @SerializedName("triptypename")
    @Expose
    private String triptypename;
    @SerializedName("pickupcityid")
    @Expose
    private Integer pickupcityid;
    @SerializedName("pickuplocation")
    @Expose
    private String pickuplocation;
    @SerializedName("pickuplatitude")
    @Expose
    private Double pickuplatitude;
    @SerializedName("pickuplongitude")
    @Expose
    private Double pickuplongitude;
    @SerializedName("dropcityid")
    @Expose
    private Integer dropcityid;
    @SerializedName("droplocation")
    @Expose
    private String droplocation;
    @SerializedName("droplatitude")
    @Expose
    private Double droplatitude;
    @SerializedName("droplongitude")
    @Expose
    private Double droplongitude;
    @SerializedName("faremasterid")
    @Expose
    private Integer faremasterid;
    @SerializedName("calculatedBaseFare")
    @Expose
    private Integer calculatedBaseFare;
    @SerializedName("calculatedBaseKm")
    @Expose
    private Integer calculatedBaseKm;
    @SerializedName("extraKmRate")
    @Expose
    private Integer extraKmRate;
    @SerializedName("waitingCharges")
    @Expose
    private Integer waitingCharges;
    @SerializedName("totalfare")
    @Expose
    private Integer totalfare;
    @SerializedName("cgst")
    @Expose
    private Integer cgst;
    @SerializedName("sgst")
    @Expose
    private Integer sgst;
    @SerializedName("igst")
    @Expose
    private Integer igst;
    @SerializedName("totalTravelledKms")
    @Expose
    private Integer totalTravelledKms;
    @SerializedName("totalFareWithGst")
    @Expose
    private Integer totalFareWithGst;
    @SerializedName("paymentTypeId")
    @Expose
    private Integer paymentTypeId;
    @SerializedName("BookingAmount")
    @Expose
    private Integer bookingAmount;
    @SerializedName("amountToBeCollected")
    @Expose
    private Integer amountToBeCollected;
    @SerializedName("driverId")
    @Expose
    private Integer driverId;
    @SerializedName("vehicleNumber")
    @Expose
    private String vehicleNumber;
    @SerializedName("promoCodeId")
    @Expose
    private Integer promoCodeId;
    @SerializedName("discountAmount")
    @Expose
    private Integer discountAmount;
    @SerializedName("pickupDate")
    @Expose
    private String pickupDate;
    @SerializedName("pickupTime")
    @Expose
    private String pickupTime;
    @SerializedName("riderOtp")
    @Expose
    private Object riderOtp;
    @SerializedName("cancelledBy")
    @Expose
    private String cancelledBy;
    @SerializedName("cancelledReason")
    @Expose
    private String cancelledReason;
    @SerializedName("bookingStatus")
    @Expose
    private Integer bookingStatus;
    @SerializedName("bookingStatusText")
    @Expose
    private String bookingStatusText;
    @SerializedName("tempbookingid")
    @Expose
    private Integer tempbookingid;
    @SerializedName("googlemapimagepath")
    @Expose
    private String googlemapimagepath;
    @SerializedName("vehiclecategory")
    @Expose
    private String vehiclecategory;
    @SerializedName("paymentmode")
    @Expose
    private String paymentmode;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("startotp")
    @Expose
    private String startotp;
    @SerializedName("drivername")
    @Expose
    private String drivername;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public Integer getTriptypeid() {
        return triptypeid;
    }

    public void setTriptypeid(Integer triptypeid) {
        this.triptypeid = triptypeid;
    }

    public String getTriptypename() {
        return triptypename;
    }

    public void setTriptypename(String triptypename) {
        this.triptypename = triptypename;
    }

    public Integer getPickupcityid() {
        return pickupcityid;
    }

    public void setPickupcityid(Integer pickupcityid) {
        this.pickupcityid = pickupcityid;
    }

    public String getPickuplocation() {
        return pickuplocation;
    }

    public void setPickuplocation(String pickuplocation) {
        this.pickuplocation = pickuplocation;
    }

    public Double getPickuplatitude() {
        return pickuplatitude;
    }

    public void setPickuplatitude(Double pickuplatitude) {
        this.pickuplatitude = pickuplatitude;
    }

    public Double getPickuplongitude() {
        return pickuplongitude;
    }

    public void setPickuplongitude(Double pickuplongitude) {
        this.pickuplongitude = pickuplongitude;
    }

    public Integer getDropcityid() {
        return dropcityid;
    }

    public void setDropcityid(Integer dropcityid) {
        this.dropcityid = dropcityid;
    }

    public String getDroplocation() {
        return droplocation;
    }

    public void setDroplocation(String droplocation) {
        this.droplocation = droplocation;
    }

    public Double getDroplatitude() {
        return droplatitude;
    }

    public void setDroplatitude(Double droplatitude) {
        this.droplatitude = droplatitude;
    }

    public Double getDroplongitude() {
        return droplongitude;
    }

    public void setDroplongitude(Double droplongitude) {
        this.droplongitude = droplongitude;
    }

    public Integer getFaremasterid() {
        return faremasterid;
    }

    public void setFaremasterid(Integer faremasterid) {
        this.faremasterid = faremasterid;
    }

    public Integer getCalculatedBaseFare() {
        return calculatedBaseFare;
    }

    public void setCalculatedBaseFare(Integer calculatedBaseFare) {
        this.calculatedBaseFare = calculatedBaseFare;
    }

    public Integer getCalculatedBaseKm() {
        return calculatedBaseKm;
    }

    public void setCalculatedBaseKm(Integer calculatedBaseKm) {
        this.calculatedBaseKm = calculatedBaseKm;
    }

    public Integer getExtraKmRate() {
        return extraKmRate;
    }

    public void setExtraKmRate(Integer extraKmRate) {
        this.extraKmRate = extraKmRate;
    }

    public Integer getWaitingCharges() {
        return waitingCharges;
    }

    public void setWaitingCharges(Integer waitingCharges) {
        this.waitingCharges = waitingCharges;
    }

    public Integer getTotalfare() {
        return totalfare;
    }

    public void setTotalfare(Integer totalfare) {
        this.totalfare = totalfare;
    }

    public Integer getCgst() {
        return cgst;
    }

    public void setCgst(Integer cgst) {
        this.cgst = cgst;
    }

    public Integer getSgst() {
        return sgst;
    }

    public void setSgst(Integer sgst) {
        this.sgst = sgst;
    }

    public Integer getIgst() {
        return igst;
    }

    public void setIgst(Integer igst) {
        this.igst = igst;
    }

    public Integer getTotalTravelledKms() {
        return totalTravelledKms;
    }

    public void setTotalTravelledKms(Integer totalTravelledKms) {
        this.totalTravelledKms = totalTravelledKms;
    }

    public Integer getTotalFareWithGst() {
        return totalFareWithGst;
    }

    public void setTotalFareWithGst(Integer totalFareWithGst) {
        this.totalFareWithGst = totalFareWithGst;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public Integer getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(Integer bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    public Integer getAmountToBeCollected() {
        return amountToBeCollected;
    }

    public void setAmountToBeCollected(Integer amountToBeCollected) {
        this.amountToBeCollected = amountToBeCollected;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Integer getPromoCodeId() {
        return promoCodeId;
    }

    public void setPromoCodeId(Integer promoCodeId) {
        this.promoCodeId = promoCodeId;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Object getRiderOtp() {
        return riderOtp;
    }

    public void setRiderOtp(Object riderOtp) {
        this.riderOtp = riderOtp;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public String getCancelledReason() {
        return cancelledReason;
    }

    public void setCancelledReason(String cancelledReason) {
        this.cancelledReason = cancelledReason;
    }

    public Integer getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(Integer bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getBookingStatusText() {
        return bookingStatusText;
    }

    public void setBookingStatusText(String bookingStatusText) {
        this.bookingStatusText = bookingStatusText;
    }

    public Integer getTempbookingid() {
        return tempbookingid;
    }

    public void setTempbookingid(Integer tempbookingid) {
        this.tempbookingid = tempbookingid;
    }

    public String getGooglemapimagepath() {
        return googlemapimagepath;
    }

    public void setGooglemapimagepath(String googlemapimagepath) {
        this.googlemapimagepath = googlemapimagepath;
    }

    public String getVehiclecategory() {
        return vehiclecategory;
    }

    public void setVehiclecategory(String vehiclecategory) {
        this.vehiclecategory = vehiclecategory;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStartotp() {
        return startotp;
    }

    public void setStartotp(String startotp) {
        this.startotp = startotp;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }
}
