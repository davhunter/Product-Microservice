package com.deloitte.productapi.cdm;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * POJO containing Product info. An empty constructor is provided, as well as
 * one to initialize the bean with its properties, and there is a special
 * constructor populating only the name and description, used by the MuleSoft
 * application.
 * 
 * Class utilizes the <code>@JsonAutoDetect</code> annotation for serializing to
 * JSON.
 */
@JsonAutoDetect
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private int ID;
	private String name;
	private String desc;
	private String region;
	private float price;

	public Product(int iD, String name, String desc, String region, float price) {
		super();
		ID = iD;
		this.name = name;
		this.desc = desc;
		this.region = region;
		this.price = price;
	}

	public Product(String name, String desc) {
		super();
		this.name = name;
		this.desc = desc;
		this.ID = -1;
		this.region = null;
		this.price = 0;
	}

	public Product() {
		super();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
