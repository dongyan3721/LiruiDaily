import com.liruisecond.liruisecond.utils.HttpsManager;
import org.junit.jupiter.api.Test;

public class OkHttp3DownloadTest {
    @Test
    public void test01(){
        HttpsManager.download(0, "https://minivideo.xiu123.cn/original/f7c3f850043471eebfdb7035d0b20102/d2dcd91-1888f6f1c55.mp4", "0.mp4", (errorMessage, index) -> System.out.println(111));
    }
}
