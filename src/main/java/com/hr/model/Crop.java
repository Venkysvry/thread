package com.hr.model;

public class Crop {
	private int CropId;
	private String CropType;
	private String CropName;
	private String quantity;
	private String locationName;
	private String cropPrice;
	private String description;
	private String contact;
	private String fId;
	public Crop() {
		
	}
	public Crop(int cropId, String cropType, String cropName, String quantity, String locationName, String cropPrice,
			String description, String contact,String id) {
		super();
		CropId = cropId;
		CropType = cropType;
		CropName = cropName;
		this.quantity = quantity;
		this.locationName = locationName;
		this.cropPrice = cropPrice;
		this.description = description;
		this.contact = contact;
	this.fId=id;
	}
	
	public String getfId() {
		return fId;
	}
	public void setfId(String fId) {
		this.fId = fId;
	}
	public int getCropId() {
		return CropId;
	}
	public void setCropId(int cropId) {
		CropId = cropId;
	}
	public String getCropType() {
		return CropType;
	}
	public void setCropType(String cropType) {
		CropType = cropType;
	}
	public String getCropName() {
		return CropName;
	}
	public void setCropName(String cropName) {
		CropName = cropName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getCropPrice() {
		return cropPrice;
	}
	public void setCropPrice(String cropPrice) {
		this.cropPrice = cropPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "Crop [CropId=" + CropId + ", CropType=" + CropType + ", CropName=" + CropName + ", quantity=" + quantity
				+ ", locationName=" + locationName + ", cropPrice=" + cropPrice + ", description=" + description
				+ ", contact=" + contact + ", fId=" + fId + "]";
	}
	
	
}
