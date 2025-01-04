package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.DepartmentDAO;
import by.itacademy.jd2.entity.DepartmentEntity;

public class DepartmentDaoImpl extends DAO<DepartmentEntity> implements DepartmentDAO {
    public DepartmentDaoImpl() {
        super(DepartmentEntity.class);
    }
}
