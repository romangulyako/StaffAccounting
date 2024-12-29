package by.itacademy.jd2.dao.impl;

import by.itacademy.jd2.dao.DAO;
import by.itacademy.jd2.dao.api.PassportDAO;
import by.itacademy.jd2.repository.PassportEntity;

public class PassportDAOImpl extends DAO<PassportEntity> implements PassportDAO {
    public PassportDAOImpl() {
        super(PassportEntity.class);
    }
}
