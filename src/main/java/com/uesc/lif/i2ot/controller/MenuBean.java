package com.uesc.lif.i2ot.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class MenuBean implements Serializable{
	private int option;

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public String chooseByOption() {
		switch (option) {
		case 0: {
			SmartObjectBean smartObjectBean = new SmartObjectBean();
			return smartObjectBean.FlameSmartObjectRegister();
		}
		default: {
			return "";
		}
		}
	}
}
