package sepm.ss15.grp16.persistence.dao.training.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.grp16.entity.training.TrainingsSession;
import sepm.ss15.grp16.entity.training.Trainingsplan;
import sepm.ss15.grp16.entity.training.helper.ExerciseSet;
import sepm.ss15.grp16.persistence.dao.UserDAO;
import sepm.ss15.grp16.persistence.dao.impl.H2UserDAOImpl;
import sepm.ss15.grp16.persistence.dao.training.TrainingsplanDAO;
import sepm.ss15.grp16.persistence.dao.training.helper.ExerciseSetHelperDAO;
import sepm.ss15.grp16.persistence.dao.training.helper.TrainingsSessionHelperDAO;
import sepm.ss15.grp16.persistence.dao.training.helper.impl.H2ExerciseSetHelperDAOImpl;
import sepm.ss15.grp16.persistence.dao.training.helper.impl.H2TrainingssessionHelperDAOImpl;
import sepm.ss15.grp16.persistence.database.DBHandler;
import sepm.ss15.grp16.persistence.exception.DBException;
import sepm.ss15.grp16.persistence.exception.PersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Lukas
 * Date: 08.05.2015
 */
public class H2TrainingsplanDAOImpl implements TrainingsplanDAO {

	private static final Logger LOGGER = LogManager.getLogger(H2TrainingsplanDAOImpl.class);

	private final Connection con;

	private final PreparedStatement ps_create;
	private final PreparedStatement ps_findAll;
	private final PreparedStatement ps_findID;
	private final PreparedStatement ps_find_user;
	private final PreparedStatement ps_find_nouser;
	private final PreparedStatement ps_update;
	private final PreparedStatement ps_delete;

	private final PreparedStatement ps_seq_TP;

	private TrainingsSessionHelperDAO trainingsSessionHelperDAO;
	private ExerciseSetHelperDAO exerciseSetHelperDAO;
	private UserDAO userDAO;

	private H2TrainingsplanDAOImpl(DBHandler handler) throws PersistenceException {
		try {
			con = handler.getConnection();

			/** Trainingplan **/
			ps_create = con.prepareStatement("INSERT INTO TrainingsPlan (UID, name, description, duration, isDeleted)" +
					"VALUES (?, ?, ?, ?, ?)");
			ps_findAll = con.prepareStatement("SELECT * FROM TrainingsPlan WHERE isDeleted = FALSE ");
			ps_find_user = con.prepareStatement("SELECT * FROM TrainingsPlan " +
					"WHERE UID = ? AND name LIKE ?");
			ps_find_nouser = con.prepareStatement("SELECT * FROM TrainingsPlan " +
					"WHERE name LIKE ?");
			ps_findID = con.prepareStatement("SELECT * FROM TrainingsPlan WHERE ID_Plan=? ");
			ps_update = con.prepareStatement("UPDATE TrainingsPlan " +
					"SET UID = ?, name = ?, description = ?, duration=?, isDeleted = ? WHERE ID_Plan = ?");
			ps_delete = con.prepareStatement("UPDATE TrainingsPlan SET isDeleted = TRUE WHERE ID_Plan = ?");

			/** Sequences **/
			ps_seq_TP = con.prepareStatement("SELECT currval('seq_TP')");

		} catch (DBException | SQLException e) {
			LOGGER.error("" + e.getMessage());
			throw new PersistenceException("failed to prepare statements", e);
		}
	}

	@Override
	public Trainingsplan create(Trainingsplan dto) throws PersistenceException {

		try {
			ps_create.setObject(1, dto.getUser() != null ? dto.getUser().getId() : null);
			ps_create.setString(2, dto.getName());
			ps_create.setString(3, dto.getDescr());
			ps_create.setInt(4, dto.getDuration());
			ps_create.setBoolean(5, false);

			executeUpdate(ps_create);
			ResultSet rs = executeQuery(ps_seq_TP);
			rs.next();
			dto.setId(rs.getInt(1));

			if (dto.getTrainingsSessions() != null) {

				for (TrainingsSession session : dto.getTrainingsSessions()) {
					trainingsSessionHelperDAO.create(session, dto.getId());
				}
			}

		} catch (SQLException e) {
			LOGGER.error("" + e.getMessage());
			throw new PersistenceException("failed create " + dto, e);
		}

		return dto;
	}

	@Override
	public List<Trainingsplan> findAll() throws PersistenceException {
		try {
			ResultSet rs = executeQuery(ps_findAll);
			List<Trainingsplan> plans = new ArrayList<>();

			while (rs.next()) {

				Trainingsplan plan = new Trainingsplan();
				int id = rs.getInt("ID_Plan");
				plan.setId(id);

				Integer uid = (Integer) rs.getObject("UID");
				plan.setUser(uid != null ? userDAO.searchByID(uid) : null);

				plan.setIsDeleted(rs.getBoolean("isDeleted"));
				plan.setDuration(rs.getInt("duration"));
				plan.setName(rs.getString("name"));
				plan.setDescr(rs.getString("description"));

				List<TrainingsSession> sessions = trainingsSessionHelperDAO.searchByPlanID(id);
				plan.setTrainingsSessions(sessions);

				plans.add(plan);
			}
			return plans;
		} catch (SQLException e) {
			LOGGER.error("" + e.getMessage());
			throw new PersistenceException("failed to findAll ", e);
		}
	}

	@Override
	public Trainingsplan searchByID(int id) throws PersistenceException {
		try {
			ps_findID.setInt(1, id);
			ResultSet rs = executeQuery(ps_findID);
			Trainingsplan plan = null;

			if (rs.next()) {

				plan = new Trainingsplan();
				plan.setId(id);

				Integer uid = (Integer) rs.getObject("UID");
				plan.setUser(uid != null ? userDAO.searchByID(uid) : null);

				plan.setIsDeleted(rs.getBoolean("isDeleted"));
				plan.setDuration(rs.getInt("duration"));
				plan.setName(rs.getString("name"));
				plan.setDescr(rs.getString("description"));

				List<TrainingsSession> sessions = trainingsSessionHelperDAO.searchByPlanID(id);
				plan.setTrainingsSessions(sessions);
			}
			return plan;
		} catch (SQLException e) {
			LOGGER.error("" + e.getMessage());
			throw new PersistenceException("failed to searchByID " + id, e);
		}
	}

	@Override
	public Trainingsplan update(Trainingsplan dto) throws PersistenceException {

		try {
			ps_update.setObject(1, dto.getUser());
			ps_update.setString(2, dto.getName());
			ps_update.setString(3, dto.getDescr());
			ps_update.setInt(4, dto.getDuration());
			ps_update.setBoolean(5, dto.getIsDeleted());
			ps_update.setInt(6, dto.getId());

			executeUpdate(ps_update);

			List<TrainingsSession> sessions = trainingsSessionHelperDAO.searchByPlanID(dto.getId());

			if (sessions != null &&
					dto.getTrainingsSessions() != null &&
					sessions.size() != dto.getTrainingsSessions().size()) {

				List<TrainingsSession> sessions_toDelete = new ArrayList<>();
				List<TrainingsSession> sessions_toCreate = new ArrayList<>();

				sessions_toDelete.addAll(sessions.stream().filter(session ->
						!dto.getTrainingsSessions().contains(session)).collect(Collectors.toList()));

				sessions_toCreate.addAll(dto.getTrainingsSessions().stream().filter(session ->
						!sessions.contains(session)).collect(Collectors.toList()));

				for (TrainingsSession session : sessions_toCreate) {
					trainingsSessionHelperDAO.create(session, dto.getId());
				}

				for (TrainingsSession session : sessions_toDelete) {
					trainingsSessionHelperDAO.delete(session);
				}
			} else if (sessions == null && dto.getTrainingsSessions() != null) {
				for (TrainingsSession session : dto.getTrainingsSessions()) {
					trainingsSessionHelperDAO.create(session, dto.getId());
				}
			} else if (dto.getTrainingsSessions() == null && sessions != null) {
				for (TrainingsSession session : sessions) {
					trainingsSessionHelperDAO.delete(session);
				}
			}

			return dto;
		} catch (SQLException e) {
			LOGGER.error("" + e.getMessage());
			throw new PersistenceException("failed to update " + dto, e);
		}
	}

	@Override
	public void delete(Trainingsplan dto) throws PersistenceException {

		try {
			ps_delete.setInt(1, dto.getId());
			executeUpdate(ps_delete);

			if (dto.getTrainingsSessions() != null) {
				for (TrainingsSession session : dto.getTrainingsSessions()) {
					trainingsSessionHelperDAO.delete(session);
				}
			}

			dto.setIsDeleted(true);

		} catch (SQLException e) {
			LOGGER.error("" + e.getMessage());
			throw new PersistenceException("failed to prepare statements", e);
		}
	}

	@Override
	public List<Trainingsplan> find(Trainingsplan filter) throws PersistenceException {
		try {
			List<Trainingsplan> plans = null;

			if (filter.getId() != null) {
				plans = new ArrayList<>();
				plans.add(searchByID(filter.getId()));
				return plans;
			}

			PreparedStatement ps_find;

			if (filter.getUser() != null) {
				ps_find_user.setInt(1, filter.getUser().getId());

				if (filter.getName() != null) ps_find_user.setString(2, filter.getName());
				else ps_find_user.setString(2, "%");

				ps_find = ps_find_user;
			} else {
				if (filter.getName() != null) ps_find_nouser.setString(1, filter.getName());
				else ps_find_nouser.setString(1, "%");

				ps_find = ps_find_nouser;
			}

			ResultSet rs = executeQuery(ps_find);

			if (rs.next()) {
				plans = new ArrayList<>();
				do {
					Trainingsplan plan = new Trainingsplan();
					int id = rs.getInt("ID_Plan");
					plan.setId(id);
					Integer uid = (Integer) rs.getObject("UID");
					plan.setUser(uid != null ? userDAO.searchByID(uid) : null);
					plan.setIsDeleted(rs.getBoolean("isDeleted"));
					plan.setDuration(rs.getInt("duration"));
					plan.setName(rs.getString("name"));
					plan.setDescr(rs.getString("description"));

					List<TrainingsSession> sessions = trainingsSessionHelperDAO.searchByPlanID(id);
					plan.setTrainingsSessions(sessions);

					plans.add(plan);
				} while (rs.next());
			}
			return plans;

		} catch (SQLException e) {
			LOGGER.error("" + e.getMessage());
			throw new PersistenceException("failed to find with filter" + filter, e);
		}
	}

	@Override
	public Trainingsplan getPlanBySession(TrainingsSession session) throws PersistenceException {
		return trainingsSessionHelperDAO.getPlanBySession(session);
	}

	@Override
	public Trainingsplan getPlanBySet(ExerciseSet set) throws PersistenceException {
		TrainingsSession session = exerciseSetHelperDAO.getSessionBySet(set);
		return getPlanBySession(session);
	}

	private void executeUpdate(PreparedStatement ps) throws SQLException {
		LOGGER.info("execute: " + ps);
		ps.executeUpdate();
	}

	private ResultSet executeQuery(PreparedStatement ps) throws SQLException {
		LOGGER.info("execute: " + ps);
		return ps.executeQuery();
	}

	public void setTrainingsSessionHelperDAO(H2TrainingssessionHelperDAOImpl trainingsSessionHelperDAO) {
		this.trainingsSessionHelperDAO = trainingsSessionHelperDAO;
	}

	public void setUserDAO(H2UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	public void setExerciseSetHelperDAO(H2ExerciseSetHelperDAOImpl exerciseSetHelperDAO) {
		this.exerciseSetHelperDAO = exerciseSetHelperDAO;
	}
}