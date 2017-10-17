package vnoumenko;

import com.github.fedy2.weather.data.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vnoumenko.exception.WeatherServiceException;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 17.10.2017
 * WeatherController
 *
 * @author Victoria Noumenko
 * @version v1.0
 */

@Controller
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @ModelAttribute("cityList")
    public List<String> getCity()
    {
        List<String> cityList = new ArrayList<>();
        cityList.add("Moscow");
        cityList.add("Samara");
        cityList.add("Saint-Petersburg");
        return cityList;
    }

    @RequestMapping(value="/current-weather", method=RequestMethod.GET)
    public String dispForm(Map<String, WeatherBean> model)
    {
        WeatherBean wb = new WeatherBean();
        model.put("wb",wb);
        return "CurrentWeatherAjax";
    }

    @RequestMapping(value="/current-weather", method=RequestMethod.POST)
    @ResponseBody
    public WeatherBean processForm(@Valid @ModelAttribute("wb") WeatherBean wb,BindingResult result)
            throws WeatherServiceException, ParseException
    {
        Channel ch = null;
        ch = weatherService.getWeather(wb.getCity());
        if (ch != null){

            wb.setTemprature(ch.getItem().getCondition().getTemp());

        }
        return wb;
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        if (e instanceof WeatherServiceException) {
            return ((WeatherServiceException) e).getError();
        } else {
            return e.getMessage();
        }
    }
}