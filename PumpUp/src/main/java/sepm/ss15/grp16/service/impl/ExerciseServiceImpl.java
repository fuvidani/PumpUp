package sepm.ss15.grp16.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.grp16.entity.Exercise;
import sepm.ss15.grp16.persistence.dao.ExerciseDAO;
import sepm.ss15.grp16.persistence.dao.impl.H2ExerciseDAOImpl;
import sepm.ss15.grp16.persistence.database.DBHandler;
import sepm.ss15.grp16.persistence.database.impl.H2DBConnectorImpl;
import sepm.ss15.grp16.persistence.exception.PersistenceException;
import sepm.ss15.grp16.service.ExerciseService;
import sepm.ss15.grp16.service.exception.ServiceException;
import sepm.ss15.grp16.service.exception.ValidationException;

import java.util.List;

/**
 * Created by lukas on 01.05.2015.
 *
 */
public class ExerciseServiceImpl implements ExerciseService {

    private ExerciseDAO exerciseDAO;
    private static final Logger LOGGER = LogManager.getLogger();


    public ExerciseServiceImpl(ExerciseDAO exerciseDAO)throws ServiceException{
        if(exerciseDAO==null) {
            throw new ServiceException("exerciseDAO is null. can not be set in Service Layer");
        }
        this.exerciseDAO = exerciseDAO;
    }

    @Override
    public Exercise create(Exercise exercise) throws ServiceException, ValidationException {
        LOGGER.debug("creating new Exercise in service");
        validate(exercise);
        try {

            return  exerciseDAO.create(exercise);
        }catch (PersistenceException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Exercise> findAll() throws ServiceException {
        try{
            LOGGER.debug("trying to find all exercises in servic");
            return exerciseDAO.findAll();
        }catch (PersistenceException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Exercise update(Exercise exercise) throws ServiceException {
        LOGGER.debug("updating exercise in service");
        validate(exercise);
        try{
            Exercise updated = exerciseDAO.update(exercise);
            validate(updated);
            return updated;
        }catch (PersistenceException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Exercise exercise) throws ServiceException {
        LOGGER.debug("deleting exercise in service");
        try{
            exerciseDAO.delete(exercise);
        }catch (PersistenceException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void validate(Exercise exercise)throws sepm.ss15.grp16.service.exception.ValidationException{
        LOGGER.debug("validating exericse in service layer");
        if(exercise ==null)
            throw new ValidationException("validation not passed. exercise is null");

        if(exercise.getName().equals("") || exercise.getName()==null || exercise.getName().isEmpty())
            throw new ValidationException("name can not be null");

        if(exercise.getCalories()<= 0)
            throw new ValidationException("validation not passed. calories can not be lower or equal to 0");
    }
}
