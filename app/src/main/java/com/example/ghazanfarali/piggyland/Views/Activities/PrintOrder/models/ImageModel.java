package com.example.ghazanfarali.piggyland.Views.Activities.PrintOrder.models;

import java.io.Serializable;

public class ImageModel implements Serializable {

	/**
	 * 
	 */
	public ImageModel() {
		super();
	}
	private static final long serialVersionUID = 2767853691836204594L;
	private String imageName, picturePath, imageType, imageString;
	private String selectedImage;
	private byte[] imageByte;
	
	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}
	
	/**
	 * @param imageName
	 * @param picturePath
	 * @param imageType
	 */
	public ImageModel(String imageName, String picturePath, String imageType, String selectedImage) {
		super();
		this.imageName = imageName;
		this.picturePath = picturePath;
		this.imageType = imageType;
		this.selectedImage = selectedImage;
	}

	/**
	 * @param imageName
	 * @param picturePath
	 * @param imageType
	 * @param imageByte
	 */
	public ImageModel(String imageName, String picturePath, String imageType,
			byte[] imageByte) {
		super();
		this.imageName = imageName;
		this.picturePath = picturePath;
		this.imageType = imageType;
		this.imageByte = imageByte;
	}
//	public ImageModel(String imageName, String picturePath, String imageType,
//			String imageString) {
//		super();
//		this.imageName = imageName;
//		this.picturePath = picturePath;
//		this.imageType = imageType;
//		this.imageString = imageString;
//	}
	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	/**
	 * @return the picturePath
	 */
	public String getPicturePath() {
		return picturePath;
	}
	/**
	 * @param picturePath the picturePath to set
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	/**
	 * @return the imageType
	 */
	public String getImageType() {
		return imageType;
	}
	/**
	 * @param imageType the imageType to set
	 */
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	/**
	 * @return the imageString
	 */
	public String getImageString() {
		return imageString;
	}
	/**
	 * @param imageString the imageString to set
	 */
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	/**
	 * @return the imageByte
	 */
	public byte[] getImageByte() {
		return imageByte;
	}
	/**
	 * @param imageByte the imageByte to set
	 */
	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}

	/**
	 * @return the selectedImage
	 */
	public String getSelectedImage() {
		return selectedImage;
	}

	/**
	 * @param selectedImage the selectedImage to set
	 */
	public void setSelectedImage(String selectedImage) {
		this.selectedImage = selectedImage;
	}

	
}
