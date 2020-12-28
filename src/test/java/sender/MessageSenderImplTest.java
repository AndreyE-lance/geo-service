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

    @Test
    void messageSenderTestRU(){
        //given
        LocalizationServiceImpl localisationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localisationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        final String ip = "172.19.61.13";
        final Map<String, String> headers = new HashMap<String, String>();
        headers.put(IP_ADDRESS_HEADER, ip);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        final MessageSenderImpl original = new MessageSenderImpl(geoService, localisationService);

        //when
        final String result = original.send(headers);

        //then
        Assertions.assertEquals("Добро пожаловать", result);
    }

    @Test
    public void messageSenderTestUS() {
        //given
        LocalizationServiceImpl localisationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localisationService.locale(Country.USA))
                .thenReturn("Welcome");
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        final String ip = "96.19.101.042";
        final Map<String, String> headers = new HashMap<String, String>();
        headers.put(IP_ADDRESS_HEADER, ip);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        final MessageSenderImpl original = new MessageSenderImpl(geoService, localisationService);

        //when
        final String result = original.send(headers);

        //then
        Assertions.assertEquals("Welcome", result);
    }
}
