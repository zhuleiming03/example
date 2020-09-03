import org.junit.Test;
import service.HtmlClientService;

public class BaseTest {

    @Test
    public void getCodeTest() {
        HtmlClientService htmlClientService = new HtmlClientService();
        htmlClientService.getInfo(11);
    }
}
