package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.EmployeeDAO;
import by.itacademy.jd2.entity.EmployeeEntity;

public class EmployeeDaoImpl extends DAO<EmployeeEntity> implements EmployeeDAO {
    public EmployeeDaoImpl() {
        super(EmployeeEntity.class);
    }
}
