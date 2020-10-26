import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import p06_TirePressureMonitoringSystem.Alarm;
import p06_TirePressureMonitoringSystem.Sensor;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class TirePressureTests {

    @Test
    public void alarmIsOnWhenPressureIsLowerThanLimit() throws NoSuchFieldException, IllegalAccessException {
        Alarm alarm = new Alarm();

        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(16d);

        Field sensorField = alarm.getClass().getDeclaredField("sensor");
        sensorField.setAccessible(true);
        sensorField.set(alarm, sensor);

        alarm.check();

        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void alarmIsOnWhenPressureIsHigherThanLimit() throws NoSuchFieldException, IllegalAccessException {
        Alarm alarm = new Alarm();

        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(22d);

        Field sensorField = alarm.getClass().getDeclaredField("sensor");
        sensorField.setAccessible(true);
        sensorField.set(alarm, sensor);

        alarm.check();

        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void alarmIsOnWhenPressureIsInLimit() throws NoSuchFieldException, IllegalAccessException {
        Alarm alarm = new Alarm();

        Sensor sensor = Mockito.mock(Sensor.class);
        Mockito.when(sensor.popNextPressurePsiValue()).thenReturn(20d);

        Field sensorField = alarm.getClass().getDeclaredField("sensor");
        sensorField.setAccessible(true);
        sensorField.set(alarm, sensor);

        alarm.check();

        assertFalse(alarm.getAlarmOn());
    }

}
