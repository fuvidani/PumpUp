package PersitanceTest;

import a.a.e;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sepm.ss15.grp16.persistence.dao.ExerciseDAO;
import sepm.ss15.grp16.persistence.database.DBHandler;
import sepm.ss15.grp16.persistence.database.impl.H2DBConnectorImpl;
import sepm.ss15.grp16.persistence.exception.DBException;

import java.sql.SQLException;

/**
 * Created by lukas on 30.04.2015.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class H2ExerciseDAOImplTest extends  AbstractExerciseDaoTest {

    @Autowired
    private ExerciseDAO exerciseDAO;

    @Autowired
    private DBHandler dbConnector;

    @Override
    public ExerciseDAO getExerciseDAO() {
        return exerciseDAO;
    }

    @Before
    public void setUp() {
        try {
            dbConnector.getConnection().setAutoCommit(false);
        }catch ( DBException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            dbConnector.getConnection().rollback();
        } catch (DBException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
