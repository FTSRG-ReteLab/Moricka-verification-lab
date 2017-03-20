package hu.bme.mit.train.controller;

public class SpeedLimiterThread extends Thread {
	
	TrainControllerImpl train;
	
	public SpeedLimiterThread(TrainControllerImpl obj){
		train = obj;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				train.followSpeed();
						
				Thread.sleep(500);
				
			} catch (InterruptedException e) {
				this.run();//start again.
			}
		}
	}

}
