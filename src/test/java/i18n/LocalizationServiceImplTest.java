package i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    @Test
    public void localizationServiceTest() {
        //given
        final LocalizationService locService = new LocalizationServiceImpl();
        final Country argument = Country.BRAZIL;
        final String expected = "Welcome";

        //when
        final String result = locService.locale(argument);

        //then
        Assertions.assertEquals(expected, result);
    }


}
