package lk.ijse.dep.akashStainlessSteel.business.custom.impl;
import lk.ijse.dep.akashStainlessSteel.business.custom.FnrCustomerBO;
import lk.ijse.dep.akashStainlessSteel.business.exception.AlreadyExistsInJobException;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.FnrCustomerDAO;
import lk.ijse.dep.akashStainlessSteel.dao.custom.JobDAO;
import lk.ijse.dep.akashStainlessSteel.dto.FnrCustomerDTO;
import lk.ijse.dep.akashStainlessSteel.entity.FnrCustomer;

import java.util.ArrayList;
import java.util.List;

public class FnrCustomerBOImpl implements FnrCustomerBO {

    FnrCustomerDAO fnrCustomerDAO = DAOFactory.getInstance().getDAO(DAOTypes.FNR_CUSTOMER);
    JobDAO jobDAO = DAOFactory.getInstance().getDAO(DAOTypes.JOB);


    @Override
    public boolean saveFnrCustomer(FnrCustomerDTO fnrCustomer) throws Exception {
        return fnrCustomerDAO.save(new FnrCustomer(
                fnrCustomer.getCustomerId(),
                fnrCustomer.getName(),
                fnrCustomer.getAddress(),
                fnrCustomer.getContactNo()
        ));
    }

    @Override
    public boolean updateFnrCustomer(FnrCustomerDTO fnrCustomer) throws Exception {
        return fnrCustomerDAO.update(new FnrCustomer(fnrCustomer.getCustomerId(),fnrCustomer.getName(),fnrCustomer.getAddress(),fnrCustomer.getContactNo()));
    }

    @Override
    public boolean deleteFnrCustomer(String fnrCustomerId) throws Exception {
        if(jobDAO.existsByCustomerId(fnrCustomerId)){
            throw new AlreadyExistsInJobException("Customer already have an job, hence unable to delete");
        }
        return fnrCustomerDAO.delete(fnrCustomerId);
    }

    @Override
    public List<FnrCustomerDTO> findAllFnrCustomers() throws Exception {
        List<FnrCustomer> fnrCustomerDAOAll  = fnrCustomerDAO.findAll();
        List<FnrCustomerDTO> alFnrCustomers= new ArrayList<>();
        for (FnrCustomer fnrCustomer : fnrCustomerDAOAll) {
            alFnrCustomers.add(new FnrCustomerDTO(fnrCustomer.getCustomerId(),
                    fnrCustomer.getName(),
                    fnrCustomer.getAddress(),
                    fnrCustomer.getContactNo()));
        }
        return alFnrCustomers;

    }

    @Override
    public String getLastFnrCustomerId() throws Exception {
        return fnrCustomerDAO.getLastCustomerId();
    }

    @Override
    public FnrCustomerDTO findFnrCustomer(String fnrCustomerId) throws Exception {
        FnrCustomer fnrCustomer = fnrCustomerDAO.find(fnrCustomerId);
        return new FnrCustomerDTO(fnrCustomer.getCustomerId(),
                fnrCustomer.getName(),
                fnrCustomer.getAddress(),
                fnrCustomer.getContactNo());
    }

    @Override
    public List<String> getAllFnrCustomerIDs() throws Exception {
        List<FnrCustomer> fnrCustomerDAOAllId = fnrCustomerDAO.findAll();
        List<String> fnrCustomerIds = new ArrayList<>();
        for (FnrCustomer fnrCustomer : fnrCustomerDAOAllId) {
            fnrCustomerIds.add(fnrCustomer.getCustomerId());
        }

        return fnrCustomerIds;

    }
}
