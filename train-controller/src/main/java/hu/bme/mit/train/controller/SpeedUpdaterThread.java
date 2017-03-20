package hu.bme.mit.train.controller;

public class SpeedUpdaterThread extends Thread {
	public TrainControllerImpl update;
	
	@Override
	public void run(){
		while(true){
			update.followSpeed();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
