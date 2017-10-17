package vnoumenko;

import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.unit.DegreeUnit;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vnoumenko.exception.Error;
import vnoumenko.exception.WeatherServiceException;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 17.10.2017
 * WeatherService
 *
 * @author Victoria Noumenko
 * @version v1.0
 */

@Service
public class WeatherService implements MessageSourceAware {

    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private Logger logger = LoggerFactory.getLogger(WeatherService.class);

    /**
     * Gets temperature by id
     * @param city
     * @return an object representing the weather details
     * @throws WeatherServiceException
     */
    public Channel getWeather(String city) throws WeatherServiceException {

        logger.info("INFO");
        logger.error("ERROR");

        YahooWeatherService service;
        Channel channel = null;
        if (StringUtils.isEmpty(city)) {
            throw new WeatherServiceException(messageSource.getMessage(Error.INVALID_CITY, null, null));
        }
        try {
            // find Yahoo API "where on earth identifier' for the  city
            Integer woeid = getWoeid(city);
            if (woeid != null) {
                service = new YahooWeatherService();
                channel = service.getForecast(woeid.toString(),
                        DegreeUnit.CELSIUS);
            }
        } catch (JAXBException | IOException e) {
            throw new WeatherServiceException(messageSource.getMessage(Error.ERROR, null, null), e);
        }
        return channel;
    }

    /**
     *
     * @param city
     * @return city's where on earth identifier
     * @throws IOException
     */
    private Integer getWoeid(String city) throws IOException {

        logger.info("INFO");
        logger.error("ERROR");

        Integer woeid = null;
        //search in database by yahoo query language
        String baseUrl = "http://query.yahooapis.com/v1/public/yql?q=";
        String query = "select woeid from geo.places where text=\"" + city
                + "\"";

        String fullUrlStr = baseUrl + URLEncoder.encode(query, "UTF-8")
                + "&format=json";
        URL fullUrl = new URL(fullUrlStr);
        try (InputStream is = fullUrl.openStream();
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)) {
            String result = "";
            String read;
            while ((read = br.readLine()) != null) {
                result += read;
            }
            Gson gson = new Gson();
            ResultArray resultObj = gson.fromJson(result, ResultArray.class);
            if (resultObj != null && resultObj.query.results != null) {
                woeid = resultObj.query.results.place[0].woeid;
            }
        }
        return woeid;
    }
    /**
     * Static classes to store json result
     */
    private static class ResultArray {
        public QueryArray query;
    }

    private static class QueryArray {
        public ResultsArray results;
    }

    private static class ResultsArray {
        public Place[] place;
    }

    private static class Place {
        public int woeid;
    }
}