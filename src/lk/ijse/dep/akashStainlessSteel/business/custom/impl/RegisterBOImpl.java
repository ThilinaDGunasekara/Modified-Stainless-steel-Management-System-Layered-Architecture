package lk.ijse.dep.akashStainlessSteel.business.custom.impl;

import lk.ijse.dep.akashStainlessSteel.business.custom.RegisterBO;
import lk.ijse.dep.akashStainlessSteel.dao.DAOFactory;
import lk.ijse.dep.akashStainlessSteel.dao.DAOTypes;
import lk.ijse.dep.akashStainlessSteel.dao.custom.RegisterDAO;
import lk.ijse.dep.akashStainlessSteel.dto.RegisterDTO;
import lk.ijse.dep.akashStainlessSteel.entity.RegisterEntity;

import java.util.ArrayList;
import java.util.List;

public class RegisterBOImpl implements RegisterBO {

    RegisterDAO registerDAO = DAOFactory.getInstance().getDAO(DAOTypes.REGISTER);
    @Override
    public boolean saveRegister(RegisterDTO registerDTO) throws Exception {
        return registerDAO.saveRegister(new RegisterEntity(
                registerDTO.getUserType(),
                registerDTO.getUserName(),
                registerDTO.getPassword()
        ));
    }

    @Override
    public boolean deleteRegister(String userName) throws Exception {
        return registerDAO.deleteRegister(userName);
    }

    @Override
    public boolean updateRegister(RegisterDTO registerDTO) throws Exception {
        return registerDAO.updateRegister(new RegisterEntity(
                registerDTO.getUserType(),
                registerDTO.getUserName(),
                registerDTO.getPassword()
        ));
    }

    @Override
    public List<RegisterDTO> findAllRegisters() throws Exception {
        List<RegisterEntity> allRegisters = registerDAO.findAllRegisters();
        List<RegisterDTO> registerDTOS = new ArrayList<>();

        for (RegisterEntity allRegister : allRegisters) {
            registerDTOS.add(new RegisterDTO(
                 allRegister.getUserType(),
                 allRegister.getUserName(),
                 allRegister.getPassword()
            ));
        }
        return registerDTOS;
    }

    @Override
    public RegisterDTO findRegister(String userName) throws Exception {
        RegisterEntity register = registerDAO.findRegister(userName);
        return new RegisterDTO(
                register.getUserType(),
                register.getUserName(),
                register.getPassword()
        );
    }
}
