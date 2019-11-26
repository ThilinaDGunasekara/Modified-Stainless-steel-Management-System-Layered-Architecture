package lk.ijse.dep.akashStainlessSteel.business.custom.impl;
import lk.ijse.dep.akashStainlessSteel.business.custom.FrCustomerBO;
import lk.ijse.dep.akashStainlessSteel.business.exception.AlreadyExistsInOrderException;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.FrCustomerDAO;
import lk.ijse.dep.akashStainlessSteel.dao.custom.FrOrderDAO;
import lk.ijse.dep.akashStainlessSteel.dto.FrCustomerDTO;
import lk.ijse.dep.akashStainlessSteel.entity.FrCustomer;
import java.util.ArrayList;
import java.util.List;

public class FrCustomerBOImpl implements FrCustomerBO {
    
    FrCustomerDAO frCustomerDAO = DAOFactory.getInstance().getDAO(DAOTypes.FR_CUSTOMER);
    FrOrderDAO frOrderDAO = DAOFactory.getInstance().getDAO(DAOTypes.FR_ORDER);
    
    @Override
    public boolean saveFrCustomer(FrCustomerDTO frCustomer) throws Exception {
        return frCustomerDAO.save(new FrCustomer(
                frCustomer.getCustomerId(),
                frCustomer.getName(),
                frCustomer.getAddress(),
                frCustomer.getContactNo()
        ));
    }

    @Override
    public boolean updateFrCustomer(FrCustomerDTO frCustomer) throws Exception {
        return frCustomerDAO.update(new FrCustomer(
                frCustomer.getCustomerId(),
                frCustomer.getName(),
                frCustomer.getAddress(),
                frCustomer.getContactNo()
        ));
    }

    @Override
    public boolean deleteFrCustomer(String frCustomerId) throws Exception{
        if(frOrderDAO.existsByCustomerId(frCustomerId)){
            throw new AlreadyExistsInOrderException("Customer already exists in an FrOrder, hence unable to delete");
        }
        return frCustomerDAO.delete(frCustomerId);
    }

    @Override
    public List<FrCustomerDTO> findAllFrCustomers() throws Exception {
        List<FrCustomer> frCustomerDAOAll  = frCustomerDAO.findAll();
        List<FrCustomerDTO> alFrCustomers= new ArrayList<>();
        for (FrCustomer frCustomer : frCustomerDAOAll) {
            alFrCustomers.add(new FrCustomerDTO(frCustomer.getCustomerId(),
                    frCustomer.getName(),
                    frCustomer.getAddress(),
                    frCustomer.getContactNo()));
        }
        return alFrCustomers;
    }

    @Override
    public String getLastFrCustomerId() throws Exception {
        return frCustomerDAO.getLastCustomerId();
    }

    @Override
    public FrCustomerDTO findFrCustomer(String frCustomerId) throws Exception {
        FrCustomer frCustomer = frCustomerDAO.find(frCustomerId);
        return new FrCustomerDTO(frCustomer.getCustomerId(),
                frCustomer.getName(),
                frCustomer.getAddress(),
                frCustomer.getContactNo());
    }

    @Override
    public List<String> getAllFrCustomerIDs() throws Exception {
        List<FrCustomer> frCustomerDAOAllId = frCustomerDAO.findAll();
        List<String> frCustomerIds = new ArrayList<>();
        for (FrCustomer frCustomer : frCustomerDAOAllId) {
            frCustomerIds.add(frCustomer.getCustomerId());
        }

        return frCustomerIds;
    }
}
