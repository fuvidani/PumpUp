package sepm.ss15.grp16.persistence.dao;

import sepm.ss15.grp16.entity.Exercise;
import sepm.ss15.grp16.persistence.exception.PersistenceException;

import java.util.List;

/**
 * Created by lukas on 30.04.2015.
 */
public interface ExerciseDAO extends DAO<Exercise>{

    /**
     * creating a new exercise
     * @param exercise which shall be inserted into the underlying persistance layer
     *                 must not be null, id must not be null
     * @return the given exercise for further usage
     * @throws PersistenceException if there are complications with the persitance layer
     */
    Exercise create(Exercise exercise) throws PersistenceException;

    /**
     * finding all exercises stored in the underlying layer
     * @return List of all exercises stored so far
     * @throws PersistenceException  if there are complications with the persitance layer
     */
    List<Exercise> findAll() throws PersistenceException;


    /**
     * @param id exercixe to search for
     * @return if the exercixe exists it is returned, otherwise null is the result.
     * @throws PersistenceException  if there are complications with the persitance layer
     */
    Exercise searchByID(int id) throws PersistenceException;

    /**
     * updating one given exercise with new values and returning it for further usage
     * @param exercise which shall be updated
     *                 must not be null, id must not be null
     * @return given exercise with updated values
     * @throws PersistenceException if there are complications with the persitance layer
     */
    Exercise update(Exercise exercise) throws PersistenceException;


    /**
     * deleting a given exercise
     * @param exercise which shall be deleted from the underlying persitance implementation layer
     * @throws PersistenceException if there are complications with the persitance layer
     */
    void delete(Exercise exercise) throws  PersistenceException;


    List<Exercise> getAllEnduranceExercises() throws PersistenceException;

    List<Exercise> getAllStrengthExercises()throws PersistenceException;

    List<Exercise> getAllBalanceExercises()throws PersistenceException;

    List<Exercise> getAllFlexibilityExercises()throws PersistenceException;

    List<Exercise> getAllExercisesWithCategoryID(Integer id)throws PersistenceException;


}
