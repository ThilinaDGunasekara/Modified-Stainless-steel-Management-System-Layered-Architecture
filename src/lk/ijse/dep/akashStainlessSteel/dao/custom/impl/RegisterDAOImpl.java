package lk.ijse.dep.akashStainlessSteel.dao.custom.impl;

import lk.ijse.dep.akashStainlessSteel.dao.CrudUtil;
import lk.ijse.dep.akashStainlessSteel.entity.RegisterEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegisterDAOImpl implements lk.ijse.dep.akashStainlessSteel.dao.custom.RegisterDAO {


    @Override
    public boolean saveRegister(RegisterEntity registerEntity) throws Exception {
        return CrudUtil.execute("INSERT INTO register VALUES (?,?,?)",
                registerEntity.getUserType(),
                registerEntity.getUserName(),
                registerEntity.getPassword()
        );
    }

    @Override
    public boolean deleteRegister(String userName) throws Exception {
        CrudUtil.execute("DELETE FROM register WHERE userName = ?",userName);
        return false;
    }

    @Override
    public boolean updateRegister(RegisterEntity registerEntity) throws Exception {
        return CrudUtil.execute("UPDATE register SET password = ?,userType = ?WHERE userName =?" ,
                registerEntity.getPassword(),
                registerEntity.getUserType(),
                registerEntity.getUserName()

        );
    }

    @Override
    public List<RegisterEntity> findAllRegisters() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM register");
        List<RegisterEntity> alRegisters = new ArrayList<>();
        while (resultSet.next()){
            alRegisters.add(new RegisterEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return alRegisters;
    }

    @Override
    public RegisterEntity findRegister(String userName) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM register WHERE userName =?",userName);
        if(resultSet.next()){
            return new RegisterEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }
        return null;
    }
}
