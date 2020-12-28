package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceTest {
    @Test
    public void geoServiceImplByIpTest() {
        //given
        final GeoService original = new GeoServiceImpl();
        final String argument = "172.19.61.13";
        final Country expected = Country.RUSSIA;

        //when
        final Country result = original.byIp(argument).getCountry();

        //then
        Assertions.assertEquals(expected, result);
    }
}
