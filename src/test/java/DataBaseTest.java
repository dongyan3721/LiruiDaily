import com.liruisecond.liruisecond.mapper.CaptchaMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class DataBaseTest {
    @Test
    public void testDataBaseConn() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        CaptchaMapper mapper = sqlSession.getMapper(CaptchaMapper.class);
        System.out.println(mapper.getCaptchaValueByUUID("122"));
    }

    @Test
    public void testDataBaseInsertPoss() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        CaptchaMapper mapper = sqlSession.getMapper(CaptchaMapper.class);
        System.out.println(mapper.insertIntoCaptchaUUIDAndValue("908-bihgyt0-90jbsese4", "u8sd"));
    }
}
