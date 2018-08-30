package com.uesc.lif.i2ot.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class keyTest {
	public static void main(String args[]) {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
