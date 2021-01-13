package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {
    public static final String IP_ADDRESS_HEADER = "x-real-ip";
    public static final LocalizationServiceImpl localisationService = Mockito.mock(LocalizationServiceImpl.class);
    public static final GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);

    @Test
    void messageSenderTestRU() {
        //given
        Mockito.when(localisationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        final Map<String, String> headers = getStringMap(geoService, "172.19.61.13", "Moscow", Country.RUSSIA, "Lenina", 15);
        final MessageSenderImpl original = new MessageSenderImpl(geoService, localisationService);

        //when
        final String result = original.send(headers);

        //then
        Assertions.assertEquals("Добро пожаловать", result);
    }

    @Test
    public void messageSenderTestUS() {
        //given
        Mockito.when(localisationService.locale(Country.USA))
                .thenReturn("Welcome");
        final Map<String, String> headers = getStringMap(geoService, "96.19.101.042", "New York", Country.USA, " 10th Avenue", 32);
        final MessageSenderImpl original = new MessageSenderImpl(geoService, localisationService);

        //when
        final String result = original.send(headers);

        //then
        Assertions.assertEquals("Welcome", result);
    }

    private Map<String, String> getStringMap(GeoServiceImpl geoService, String ip, String city, Country country, String street, int house) {
        final Map<String, String> headers = new HashMap<String, String>();
        headers.put(IP_ADDRESS_HEADER, ip);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location(city, country, street, house));
        return headers;
    }
}
