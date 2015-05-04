package sepm.ss15.grp16.service.impl;

import sepm.ss15.grp16.entity.Exercise;
import sepm.ss15.grp16.persistence.dao.ExerciseDAO;
import sepm.ss15.grp16.persistence.dao.impl.H2ExerciseDAOImpl;
import sepm.ss15.grp16.persistence.exception.PersistenceException;
import sepm.ss15.grp16.service.ExerciseService;
import sepm.ss15.grp16.service.exception.ServiceException;

import java.util.List;

/**
 * Created by lukas on 01.05.2015.
 */
public class ExerciseServiceImpl implements ExerciseService {

    ExerciseDAO exerciseDAO;

    public  ExerciseServiceImpl()throws ServiceException{
        try {
            this.exerciseDAO = H2ExerciseDAOImpl.getInstance();
        }catch (PersistenceException e){
            throw new ServiceException(e);
        }

    }

    public void setExerciseDAO(ExerciseDAO exerciseDAO)throws ServiceException{
        if(exerciseDAO==null)
            throw new ServiceException("exerciseDAO is null. can not be set in Service Layer");
        this.exerciseDAO = exerciseDAO;
    }

    @Override
    public Exercise create(Exercise exercise) throws ServiceException {
        validate(exercise);
        try {
            exerciseDAO.create(exercise);
            return exercise;
        }catch (PersistenceException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Exercise> findAll() throws ServiceException {
       try{
           List<Exercise> exerciseList = exerciseDAO.findAll();
           return exerciseList;
       }catch (PersistenceException e){
           throw new ServiceException(e);
       }
    }

    @Override
    public Exercise update(Exercise exercise) throws ServiceException {
        validate(exercise);
        try{
            Exercise updated = exerciseDAO.update(exercise);
            return updated;
        }catch (PersistenceException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Exercise exercise) throws ServiceException {
       try{
           exerciseDAO.delete(exercise);
       }catch (PersistenceException e){
           throw new ServiceException(e);
       }
    }

    private void validate(Exercise exercise)throws ServiceException{
        if(exercise ==null)
            throw new ServiceException("validation not passed. exercise is null");

        if(exercise.getCalories()<= 0)
            throw new ServiceException("validation not passed. calories can not be lower or equal to 0");


    }
}
