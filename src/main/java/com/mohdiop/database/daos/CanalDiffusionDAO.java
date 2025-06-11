package com.mohdiop.database.daos;

import com.mohdiop.database.daosimpls.CanalDiffusionDAOImpl;
import com.mohdiop.database.models.CanalDiffusion;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CanalDiffusionDAO {
    CanalDiffusion getCanalById(int id) throws SQLException;

    ArrayList<CanalDiffusion> getAllCanal() throws SQLException;

    Boolean createNewCanal(CanalDiffusion newCanalDiffusion) throws SQLException;

    Boolean deleteCanalById(int id) throws SQLException;
}
