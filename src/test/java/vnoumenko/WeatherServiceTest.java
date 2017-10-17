package vnoumenko;

import com.github.fedy2.weather.data.Channel;
import org.junit.Assert;
import org.junit.Test;
import vnoumenko.exception.WeatherServiceException;
import vnoumenko.service.WeatherService;

/**
 * 17.10.2017
 * WeatherServiceTest
 *
 * @author Victoria Noumenko
 * @version v1.0
 */

public class WeatherServiceTest {


    WeatherService weatherService = new WeatherService();


    @Test
    public void testGetWeather() throws Exception {
        try {
            Channel ch = weatherService.getWeather("Moscow");
            Assert.assertNotNull(ch);
        } catch (WeatherServiceException e) {
            Assert.fail("fail");
        }
    }


    /*
    @Test
    public void testGetWeatherNullCity() {
        try {
            weatherService.getWeather(null);
            Assert.fail("fail");
        } catch (WeatherServiceException e) {
            Assert.assertTrue(ErrorClass.INVALID_CITY == e.getError());
        }
    }

*/
}