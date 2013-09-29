/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.DAOException;
import br.uel.database.DAOFactory;
import br.uel.database.ServiceEvaluationDAO;
import br.uel.entity.Service;
import br.uel.entity.ServiceEvaluation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leticia
 */
public class PgServiceEvaluationDAO extends ServiceEvaluationDAO {

    public PgServiceEvaluationDAO(DAOFactory daoFactory_) {
        daoFactory = daoFactory_;
    }

    @Override
    public void create(ServiceEvaluation se) {
        try {
            String query = "INSERT INTO spn.service_evaluation(service_id, c_price, "
                    + "c_respect_for_deadlines, c_quality_of_service,"
                    + "c_quality_of_care, c_comment, c_dat_assessment)"
                    + "VALUES (?, ?, ?, ?,?, ?, 'NOW');";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, se.getServiceId());
            ps.setInt(2, se.getcPrice());
            ps.setInt(3, se.getcRespectForDeadlines());
            ps.setInt(4, se.getcQualityOfService());
            ps.setInt(5, se.getcQualityOfCare());
            ps.setString(6, se.getcComment());
            ResultSet rs = ps.executeQuery();

            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public void update(ServiceEvaluation se) {
        try {
            String query = "UPDATE spn.service_evaluation SET p_appropriate_payment=?, "
                    + " p_materials_supply=?, p_communication_with_client=?, p_comment=?,"
                    + " p_dat_assessment='NOW' WHERE service_id=?;";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, se.getpAppropriatePayment());
            ps.setInt(2, se.getpMaterialsSupply());
            ps.setInt(3, se.getpComunicationWithClient());
            ps.setString(4, se.getpComment());
            ps.setInt(5, se.getServiceId());
        } catch (SQLException ex) {
            Logger.getLogger(PgServiceEvaluationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
}
