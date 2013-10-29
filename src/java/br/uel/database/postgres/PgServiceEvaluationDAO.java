/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.DAOException;
import br.uel.database.DAOFactory;
import br.uel.database.ServiceEvaluationDAO;
import br.uel.entity.ServiceEvaluation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                    + "c_quality_of_care, c_comment, c_real_start_date, c_real_finish_date,c_dat_assessment)"
                    + "VALUES (?, ?, ?, ?,?, ?,?,?, 'NOW');";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, se.getServiceId());
            ps.setInt(2, se.getcPrice());
            ps.setInt(3, se.getcRespectForDeadlines());
            ps.setInt(4, se.getcQualityOfService());
            ps.setInt(5, se.getcQualityOfCare());
            ps.setString(6, se.getcComment());
            ps.setDate(7, Date.valueOf(se.getcRealStartDate()));
            ps.setDate(8, Date.valueOf(se.getcRealFinishDate()));
            ps.execute();
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public void update(ServiceEvaluation se) {
        try {
            String query = "BEGIN; "
                    + "UPDATE spn.service_evaluation SET p_appropriate_payment=?, "
                    + " p_materials_supply=?, p_communication_with_client=?, p_comment=?,"
                    + " p_dat_assessment='NOW' WHERE service_id=?;"
                    + " UPDATE spn.service SET category_id = ? WHERE service_id = ?; "
                    + "COMMIT";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, se.getpAppropriatePayment());
            ps.setInt(2, se.getpMaterialsSupply());
            ps.setInt(3, se.getpComunicationWithClient());
            ps.setString(4, se.getpComment());
            ps.setInt(5, se.getServiceId());
            ps.setInt(6,se.getCatId());
            ps.setInt(7,se.getServiceId());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PgServiceEvaluationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
