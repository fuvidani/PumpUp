package PersitanceTest.TrainingTest;

import PersitanceTest.AbstractDAOTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import sepm.ss15.grp16.entity.Exercise;
import sepm.ss15.grp16.entity.Training.Helper.ExerciseSet;
import sepm.ss15.grp16.entity.Training.TrainingsSession;
import sepm.ss15.grp16.entity.Training.Trainingsplan;
import sepm.ss15.grp16.entity.User;
import sepm.ss15.grp16.persistence.dao.ExerciseDAO;
import sepm.ss15.grp16.persistence.dao.Training.TrainingsplanDAO;
import sepm.ss15.grp16.persistence.dao.UserDAO;
import sepm.ss15.grp16.persistence.exception.PersistenceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Lukas
 * Date: 09.05.2015
 */
public abstract class AbstractTrainingsPlanDAOTest extends AbstractDAOTest<Trainingsplan> {

	private static final Logger LOGGER = LogManager.getLogger(AbstractTrainingsPlanDAOTest.class);

	protected abstract UserDAO getUserDAO();

	@Test
	public void createValidSimplePlan() throws PersistenceException {
		LOGGER.info("createValidSimplePlan");
		createValid(dummySimpleTrainingsPlan());
	}

	@Test
	public void createValidPlanWithSession() throws PersistenceException {
		LOGGER.info("createValidPlanWithSession");
		createValid(dummyTrainingsPlanWithSession());
	}

	@Test
	public void createValidPlanWithSet() throws PersistenceException {
		LOGGER.info("createValidPlanWithSet");
		createValid(dummyTrainingsPlanWithSet());
	}

	@Test
	public void updateValidSimplePlan() throws PersistenceException {
		LOGGER.info("updateValidSimplePlan");
		Trainingsplan trainingsplan_old = dummySimpleTrainingsPlan();
		Trainingsplan trainingsplan_new = new Trainingsplan(trainingsplan_old);
		trainingsplan_new.setName("changed name");

		updateValid(trainingsplan_old, trainingsplan_new);
	}

	@Test
	public void updateValidPlanWithSession() throws PersistenceException {
		LOGGER.info("updateValidPlanWithSession");
		Trainingsplan trainingsplan_old = dummyTrainingsPlanWithSession();
		Trainingsplan trainingsplan_new = new Trainingsplan(trainingsplan_old);
		trainingsplan_new.setName("changed name");

		updateValid(trainingsplan_old, trainingsplan_new);
	}

	@Test
	public void updateValidPlanWithSet() throws PersistenceException {
		LOGGER.info("updateValidPlanWithSet");
		Trainingsplan trainingsplan_old = dummyTrainingsPlanWithSet();
		Trainingsplan trainingsplan_new = new Trainingsplan(trainingsplan_old);
		trainingsplan_new.setName("changed name");

		updateValid(trainingsplan_old, trainingsplan_new);
	}

	@Test
	public void deleteValidSimplePlan() throws PersistenceException {
		LOGGER.info("deleteValidSimplePlan");
		deleteValid(dummySimpleTrainingsPlan());
	}

	@Test
	public void deleteValidPlanWithSession() throws PersistenceException {
		LOGGER.info("deleteValidPlanWithSession");
		deleteValid(dummyTrainingsPlanWithSession());
	}

	@Test
	public void deleteValidPlanWithSet() throws PersistenceException {
		LOGGER.info("deleteValidPlanWithSet");
		deleteValid(dummyTrainingsPlanWithSet());
	}

	@Test
	public void searchByIDValidSimplePlan() throws PersistenceException {
		LOGGER.info("searchByIDValidSimplePlan");
		searchByIDValid(dummySimpleTrainingsPlan());
	}

	@Test
	public void searchByIDValidPlanWithSession() throws PersistenceException {
		LOGGER.info("searchByIDValidPlanWithSession");
		searchByIDValid(dummyTrainingsPlanWithSession());
	}

	@Test
	public void searchByIDValidPlanWithSet() throws PersistenceException {
		LOGGER.info("searchByIDValidPlanWithSet");
		searchByIDValid(dummyTrainingsPlanWithSet());
	}

	@Test
	public void findValidSimplePlan() throws PersistenceException {
		LOGGER.info("findValidSimplePlan");
		User user = dummyUser();

		Trainingsplan trainingsplan_name = dummySimpleTrainingsPlan();
		trainingsplan_name.setName("asdf");
		Trainingsplan trainingsplan_user = dummySimpleTrainingsPlan();
		trainingsplan_user.setUser(user);
		Trainingsplan trainingsplan_descr = dummySimpleTrainingsPlan();
		trainingsplan_descr.setDescr("xyz");
		Trainingsplan trainingsplan4_session = dummySimpleTrainingsPlan();
		trainingsplan4_session.setTrainingsSessions(dummySession());

		Assert.assertFalse(getDAO().findAll().contains(trainingsplan_name));
		Assert.assertFalse(getDAO().findAll().contains(trainingsplan_user));
		Assert.assertFalse(getDAO().findAll().contains(trainingsplan_descr));
		Assert.assertFalse(getDAO().findAll().contains(trainingsplan4_session));
		trainingsplan_name = getDAO().create(trainingsplan_name);
		trainingsplan_user = getDAO().create(trainingsplan_user);
		trainingsplan_descr = getDAO().create(trainingsplan_descr);
		trainingsplan4_session = getDAO().create(trainingsplan4_session);
		Assert.assertTrue(getDAO().findAll().contains(trainingsplan_name));
		Assert.assertTrue(getDAO().findAll().contains(trainingsplan_user));
		Assert.assertTrue(getDAO().findAll().contains(trainingsplan_descr));
		Assert.assertTrue(getDAO().findAll().contains(trainingsplan4_session));

		List<Trainingsplan> list = ((TrainingsplanDAO) getDAO()).find(new Trainingsplan(null, null, "asdf", null, null, null));
		Assert.assertTrue(list.contains(trainingsplan_name));

		list = ((TrainingsplanDAO) getDAO()).find(new Trainingsplan(null, user, null, null, null, null));
		Assert.assertTrue(list.contains(trainingsplan_user));

		list = ((TrainingsplanDAO) getDAO()).find(new Trainingsplan(null, null, null, "xyz", null, null));
		Assert.assertTrue(list.contains(trainingsplan_descr));

		list = ((TrainingsplanDAO) getDAO()).find(new Trainingsplan(null, null, null, null, null, dummySession()));
		Assert.assertTrue(list.contains(trainingsplan4_session));
	}

	@Test
	public void findValidPlanWithSession() throws PersistenceException {
		LOGGER.info("findValidPlanWithSession");
		User user = dummyUser();

		Trainingsplan trainingsplan_name = dummyTrainingsPlanWithSession();
		trainingsplan_name.setName("asdf");
		Trainingsplan trainingsplan_user = dummyTrainingsPlanWithSession();
		trainingsplan_user.setUser(user);
		Trainingsplan trainingsplan_descr = dummyTrainingsPlanWithSession();
		trainingsplan_descr.setDescr("xyz");
		Trainingsplan trainingsplan4_session = dummyTrainingsPlanWithSession();
		trainingsplan4_session.setTrainingsSessions(dummySession());

		Assert.assertFalse(getDAO().findAll().contains(trainingsplan_name));
		Assert.assertFalse(getDAO().findAll().contains(trainingsplan_user));
		Assert.assertFalse(getDAO().findAll().contains(trainingsplan_descr));
		Assert.assertFalse(getDAO().findAll().contains(trainingsplan4_session));
		trainingsplan_name = getDAO().create(trainingsplan_name);
		trainingsplan_user = getDAO().create(trainingsplan_user);
		trainingsplan_descr = getDAO().create(trainingsplan_descr);
		trainingsplan4_session = getDAO().create(trainingsplan4_session);
		Assert.assertTrue(getDAO().findAll().contains(trainingsplan_name));
		Assert.assertTrue(getDAO().findAll().contains(trainingsplan_user));
		Assert.assertTrue(getDAO().findAll().contains(trainingsplan_descr));
		Assert.assertTrue(getDAO().findAll().contains(trainingsplan4_session));

		List<Trainingsplan> list = ((TrainingsplanDAO) getDAO()).find(new Trainingsplan(null, null, "asdf", null, null, null));
		Assert.assertTrue(list.contains(trainingsplan_name));

		list = ((TrainingsplanDAO) getDAO()).find(new Trainingsplan(null, user, null, null, null, null));
		Assert.assertTrue(list.contains(trainingsplan_user));

		list = ((TrainingsplanDAO) getDAO()).find(new Trainingsplan(null, null, null, "xyz", null, null));
		Assert.assertTrue(list.contains(trainingsplan_descr));

		list = ((TrainingsplanDAO) getDAO()).find(new Trainingsplan(null, null, null, null, null, dummySession()));
		Assert.assertTrue(list.contains(trainingsplan4_session));
	}

	@Test
	public void findValidPlanWithSet() throws PersistenceException {
		LOGGER.info("findValidPlanWithSet");
		User user = dummyUser();

		Trainingsplan trainingsplan_name = dummyTrainingsPlanWithSet();
		trainingsplan_name.setName("asdf");
		Trainingsplan trainingsplan_user = dummyTrainingsPlanWithSet();
		trainingsplan_user.setUser(user);
		Trainingsplan trainingsplan_descr = dummyTrainingsPlanWithSet();
		trainingsplan_descr.setDescr("xyz");
		Trainingsplan trainingsplan4_session = dummyTrainingsPlanWithSet();
		trainingsplan4_session.setTrainingsSessions(dummySession());

		Assert.assertFalse(getDAO().findAll().contains(trainingsplan_name));
		Assert.assertFalse(getDAO().findAll().contains(trainingsplan_user));
		Assert.assertFalse(getDAO().findAll().contains(trainingsplan_descr));
		Assert.assertFalse(getDAO().findAll().contains(trainingsplan4_session));
		trainingsplan_name = getDAO().create(trainingsplan_name);
		trainingsplan_user = getDAO().create(trainingsplan_user);
		trainingsplan_descr = getDAO().create(trainingsplan_descr);
		trainingsplan4_session = getDAO().create(trainingsplan4_session);
		Assert.assertTrue(getDAO().findAll().contains(trainingsplan_name));
		Assert.assertTrue(getDAO().findAll().contains(trainingsplan_user));
		Assert.assertTrue(getDAO().findAll().contains(trainingsplan_descr));
		Assert.assertTrue(getDAO().findAll().contains(trainingsplan4_session));

		List<Trainingsplan> list = ((TrainingsplanDAO) getDAO()).find(new Trainingsplan(null, null, "asdf", null, null, null));
		Assert.assertTrue(list.contains(trainingsplan_name));

		list = ((TrainingsplanDAO) getDAO()).find(new Trainingsplan(null, user, null, null, null, null));
		Assert.assertTrue(list.contains(trainingsplan_user));

		list = ((TrainingsplanDAO) getDAO()).find(new Trainingsplan(null, null, null, "xyz", null, null));
		Assert.assertTrue(list.contains(trainingsplan_descr));

		list = ((TrainingsplanDAO) getDAO()).find(new Trainingsplan(null, null, null, null, null, dummySession()));
		Assert.assertTrue(list.contains(trainingsplan4_session));
	}

	@Test
	public void getPlanBySessionValid() throws PersistenceException {
		LOGGER.info("getPlanBySessionValid");

		Trainingsplan plan_original = dummyTrainingsPlanWithSession();

		Assert.assertFalse(getDAO().findAll().contains(plan_original));
		plan_original = getDAO().create(plan_original);
		Assert.assertTrue(getDAO().findAll().contains(plan_original));

		for (TrainingsSession session : plan_original.getTrainingsSessions()) {
			Trainingsplan plan_found = ((TrainingsplanDAO) getDAO()).getPlanBySession(session);

			Assert.assertEquals(plan_original, plan_found);
		}
	}

	@Test
	public void getPlanBySetValid() throws PersistenceException {
		LOGGER.info("getPlanBySetValid");

		Trainingsplan plan_original = dummyTrainingsPlanWithSet();

		Assert.assertFalse(getDAO().findAll().contains(plan_original));
		plan_original = getDAO().create(plan_original);
		Assert.assertTrue(getDAO().findAll().contains(plan_original));

		for (TrainingsSession session : plan_original.getTrainingsSessions()) {
			for (ExerciseSet set : session.getExerciseSets()) {
				Trainingsplan plan_found = ((TrainingsplanDAO) getDAO()).getPlanBySet(set);

				Assert.assertEquals(plan_original, plan_found);
			}
		}
	}

	private User dummyUser() throws PersistenceException {
		User testUser = new User(null, "msober", true, 20, 194, false);
		return getUserDAO().create(testUser);
	}

	private Exercise dummyExercise() throws PersistenceException {
		return getExerciseDAO().create(new Exercise("liegestuetz", "eine der besten uebungen ueberhaupt", 9.0, "", new ArrayList<>(), false));
	}

	protected abstract ExerciseDAO getExerciseDAO();

	private List<ExerciseSet> dummySet() throws PersistenceException {
		List<ExerciseSet> sets = new ArrayList<>();
		ExerciseSet set = new ExerciseSet();

		set.setRepeat(15);
		set.setExercise(dummyExercise());
		set.setIsDeleted(false);
		set.setUser(dummyUser());

		for (int i = 0; i < 2; i++) {
			set.setOrder_nr(i);
			sets.add(set);
		}
		return sets;
	}

	private List<TrainingsSession> dummySession() {
		List<TrainingsSession> sessions = new ArrayList<>();

		for (int i = 0; i < 1; i++) {
			TrainingsSession trainingsSession = new TrainingsSession();
			trainingsSession.setName("testsession");
			trainingsSession.setIsDeleted(false);
			sessions.add(trainingsSession);
		}

		return sessions;
	}

	private Trainingsplan dummySimpleTrainingsPlan() {
		Trainingsplan trainingsplan = new Trainingsplan();

		trainingsplan.setName("testtraining");
		trainingsplan.setDescr("testdescription");
		trainingsplan.setIsDeleted(false);

		return trainingsplan;
	}

	private Trainingsplan dummyTrainingsPlanWithSession() {
		Trainingsplan trainingsplan = dummySimpleTrainingsPlan();
		trainingsplan.setTrainingsSessions(dummySession());

		return trainingsplan;
	}

	private Trainingsplan dummyTrainingsPlanWithSet() throws PersistenceException {
		Trainingsplan trainingsplan = dummySimpleTrainingsPlan();

		List<TrainingsSession> sessions = dummySession();
		for (TrainingsSession session : sessions) {
			session.setExerciseSets(dummySet());
		}

		trainingsplan.setTrainingsSessions(sessions);
		return trainingsplan;
	}

}
