package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	
	boolean threadStarted = false;
	
	TrainControllerImpl(){
		followSpeed();//followSpeed will be invoked here, no more required
	}

	@Override
	public void followSpeed() {
		if(threadStarted)
			return;
		
		threadStarted = true;
		
		while(true){	
			if (referenceSpeed < 0) {
				referenceSpeed = 0;
			} else {
			    if(referenceSpeed+step > 0) {
	                referenceSpeed += step;
	            } else {
			        referenceSpeed = 0;
	            }
			}
	
			enforceSpeedLimit();
			
			try {
				wait(500l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;
	}

}
