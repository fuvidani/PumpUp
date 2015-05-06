package PersitanceTest;

import org.junit.*;
import sepm.ss15.grp16.persistence.dao.impl.H2UserDAOImpl;
import sepm.ss15.grp16.persistence.database.impl.H2DBConnectorImpl;

import java.sql.Connection;

/**
 * Created by michaelsober on 05.05.15.
 */
public class H2UserDAOImplTest extends AbstractUserDaoTest{

    private static Connection con;

    @BeforeClass
    public static void setUpClass() throws Exception{
        con = new H2DBConnectorImpl("jdbc:h2:tcp://localhost/~/pumpup", "sa", "").getConnection();
    }

    @AfterClass
    public static void tearDownClass() throws Exception{
        new H2DBConnectorImpl("jdbc:h2:tcp://localhost/~/pumpup", "sa", "").closeConnection();
    }

    @Before
    public void setUp() throws Exception {
        this.setUserDAO(new H2UserDAOImpl());
        con.setAutoCommit(false);
    }

    @After
    public void tearDown() throws Exception {
        con.rollback();
        con.setAutoCommit(true);
    }
}
