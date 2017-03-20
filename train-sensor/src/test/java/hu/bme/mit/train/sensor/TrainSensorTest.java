package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
	
	TrainController mockTC;
	TrainUser mockTU;
	TrainSensorImpl uut;

    @Before
    public void before() {
        mockTC = mock(TrainController.class);
        mockTU = mock(TrainUser.class);
        uut = new TrainSensorImpl(mockTC, mockTU);
    }

    @Test
    public void CheckTooHighSpeedLimit() {
        uut.overrideSpeedLimit(505);
        verify(mockTU, times(1)).setAlarmState(true);
    }
    
    @Test
    public void CheckLimitMultiplierTooHigh(){
    	when(mockTC.getReferenceSpeed()).thenReturn(10);
    	uut.overrideSpeedLimit(30);
    	verify(mockTU, times(1)).setAlarmState(true);
    }
    
    @Test
    public void CheckLimitMultiplierNotTooHigh(){
    	when(mockTC.getReferenceSpeed()).thenReturn(10);
    	uut.overrideSpeedLimit(15);
    	verify(mockTU, times(0)).setAlarmState(true);
    }
    
    @Test
    public void CheckLimitBelowRefSpeed(){
    	when(mockTC.getReferenceSpeed()).thenReturn(10);
    	uut.overrideSpeedLimit(0);
    	verify(mockTU, times(0)).setAlarmState(true);
    }
    
    @Test
    public void CheckLimitIntMin(){
    	uut.overrideSpeedLimit(Integer.MIN_VALUE);
    	verify(mockTU, times(1)).setAlarmState(true);
    }
    
    @Test
    public void CheckSuccessiveAlarms(){
    	uut.overrideSpeedLimit(5005);
    	uut.overrideSpeedLimit(-5005);
    	verify(mockTU, times(2)).setAlarmState(true);
    }
}
