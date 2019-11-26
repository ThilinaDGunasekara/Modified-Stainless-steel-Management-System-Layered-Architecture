package lk.ijse.dep.akashStainlessSteel.business.custom;

import lk.ijse.dep.akashStainlessSteel.business.SuperBO;
import lk.ijse.dep.akashStainlessSteel.dto.RegisterDTO;

import java.util.List;

public interface RegisterBO extends SuperBO {
    boolean saveRegister(RegisterDTO registerDTO) throws Exception;
    boolean deleteRegister(String userName) throws Exception;
    boolean updateRegister(RegisterDTO registerDTO) throws Exception;
    List<RegisterDTO> findAllRegisters() throws Exception;
    RegisterDTO findRegister(String userName) throws Exception;
}
