package sepm.ss15.grp16.service.impl;

import sepm.ss15.grp16.entity.BodyfatHistory;
import sepm.ss15.grp16.persistence.dao.BodyfatHistoryDAO;
import sepm.ss15.grp16.persistence.dao.WeightHistoryDAO;
import sepm.ss15.grp16.persistence.exception.PersistenceException;
import sepm.ss15.grp16.service.BodyfatHistoryService;
import sepm.ss15.grp16.service.exception.ServiceException;
import sepm.ss15.grp16.service.exception.ValidationException;

import java.util.List;

/**
 * This class represents an implementation of a bodyfathistory service
 *
 * @author Michael Sober
 * @version 1.0
 */
public class BodyfatHistoryServiceImpl implements BodyfatHistoryService {

    private BodyfatHistoryDAO bodyfatHistoryDAO;

    public BodyfatHistoryServiceImpl(BodyfatHistoryDAO bodyfatHistoryDAO) throws ServiceException {
        if(bodyfatHistoryDAO == null){
            throw new ServiceException("BodyfatHistoryDAO is null. Cannot be set in service layer");
        }
        this.bodyfatHistoryDAO = bodyfatHistoryDAO;
    }

    @Override
    public BodyfatHistory create(BodyfatHistory bodyfatHistory) throws ServiceException {
        this.validate(bodyfatHistory);
        try{
            return bodyfatHistoryDAO.create(bodyfatHistory);
        }catch(PersistenceException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BodyfatHistory> findAll() throws ServiceException {
        try{
            return bodyfatHistoryDAO.findAll();
        }catch(PersistenceException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public BodyfatHistory update(BodyfatHistory bodyfatHistory) throws ServiceException {
        //TODO: Implement me
        return null;
    }

    @Override
    public void delete(BodyfatHistory bodyfatHistory) throws ServiceException {
        //TODO: Implement me
    }

    @Override
    public void validate(BodyfatHistory bodyfatHistory) throws ValidationException {

        if(bodyfatHistory == null){
            throw new ValidationException("WeightHistory is null.");
        }

        String errorMsg = "";
        Integer user_id = bodyfatHistory.getUser_id();
        Integer bodyfat = bodyfatHistory.getBodyfat();

        if(user_id == null){
            errorMsg += "UserID is required and has to be number.";
        }

        if(bodyfat == null || bodyfat < 0){
            errorMsg += "Bodyfat is required and has to be a number greater than 0.";
        }

        if(!errorMsg.isEmpty()){
            throw new ValidationException(errorMsg);
        }

    }
}
