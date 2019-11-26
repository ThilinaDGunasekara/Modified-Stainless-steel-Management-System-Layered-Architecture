package lk.ijse.dep.akashStainlessSteel.dao.custom;

import lk.ijse.dep.akashStainlessSteel.dao.SuperDAO;
import lk.ijse.dep.akashStainlessSteel.entity.RegisterEntity;

import java.util.List;

public interface RegisterDAO  extends SuperDAO {
    boolean saveRegister(RegisterEntity registerEntity) throws Exception;
    boolean deleteRegister(String userName) throws Exception;
    boolean updateRegister(RegisterEntity registerEntity) throws Exception;
    List<RegisterEntity> findAllRegisters() throws Exception;
    RegisterEntity findRegister(String userName) throws Exception;
}
