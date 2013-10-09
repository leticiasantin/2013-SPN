/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uel.database.postgres;

import br.uel.database.DAOException;
import br.uel.database.DAOFactory;
import br.uel.database.ServiceDAO;
import br.uel.entity.CompletedService;
import br.uel.entity.Picture;
import br.uel.entity.Service;
import br.uel.entity.ServiceEvaluation;
import br.uel.log.Logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author leticia
 */
public class PgServiceDAO extends ServiceDAO {
    
    public PgServiceDAO(DAOFactory daoFactory_) {
        daoFactory = daoFactory_;
    }
    
    @Override
    public void create(Service service) {
        try {
            String query = "INSERT INTO spn.service "
                    + " (client_id,provider_id, client_request_dat,description) "
                    + " VALUES (?,?,'NOW',?)";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, service.getClientId());
            ps.setInt(2, service.getProviderId());
            ps.setString(3, service.getDescription());
            ResultSet rs = ps.executeQuery();
            conn.close();
        } catch (SQLException ex) {
        }
        
        
    }
    
    @Override
    public List<Service> pendencesListAsProvider(int pId) {
        Logger.getInstance().setLog("listas provicer" + pId);
        ArrayList<Service> pList = new ArrayList();
        try {
            String query = " SELECT s.*,u.name as client_name, u.street || ' nº '||u.number|| ', '|| u.complements||\n"
                    + " ' <br/> '||u.neighborhood || ', ' || u.city || '-'||u.state as client_address "
                    + " FROM spn.service s, spn.user u "
                    + " WHERE s.provider_id=? AND s.provider_response_dat IS NULL"
                    + " AND u.status=true and u.user_id=s.client_id";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pId);
            ResultSet rs = ps.executeQuery();
            Service service;
            while (rs.next()) {
                service = this.getService(rs);
                service.setClientName(rs.getString("client_name"));
                service.setClientAddress(rs.getString("client_address"));
                pList.add(service);
            }
            conn.close();
            
        } catch (SQLException ex) {
        }
        return pList;
    }
    
    @Override
    public List<Service> pendencesListAsClient(int cId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void rejectSolicitation(Service service) {
        Logger.getInstance().setLog("reject Solicitation : sId=" + service.getServiceId() + "  reason= " + service.getReasonForCancellation());
        try {
            String query = "  UPDATE spn.service SET provider_response_dat='NOW', status=false, "
                    + "  reason_for_cancellation=? WHERE service_id=?;";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, service.getReasonForCancellation());
            ps.setInt(2, service.getServiceId());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }
    
    @Override
    public void acceptSolicitation(Service service) {
        try {
            String query = "  UPDATE spn.service SET provider_response_dat='NOW', start_date=?, "
                    + " finish_date=?, price= ? WHERE service_id=?;";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDate(1, Date.valueOf(service.getStartDate()));
            ps.setDate(2, Date.valueOf(service.getFinishDate()));
            ps.setFloat(3, service.getFloatPrice());
            ps.setInt(4, service.getServiceId());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex.getCause());
        }
    }
    
    private Service getService(ResultSet rs) throws SQLException {
        Service s = new Service();
        s.setServiceId(rs.getInt("service_id"));
        s.setClientId(rs.getInt("client_id"));
        s.setProviderId(rs.getInt("provider_id"));
        s.setCategoryId(rs.getInt("category_id"));
        s.setClientRequestDat(rs.getString("client_request_dat"));
        s.setProviderResponseDat(rs.getString("provider_response_dat"));
        s.setStartDate(rs.getString("start_date"));
        s.setFinishDate(rs.getString("finish_date"));
        s.setDescription(rs.getString("description"));
        s.setStatus(rs.getBoolean("status"));
        s.setReasonForCancellation(rs.getString("reason_for_cancellation"));
        s.setPrice(String.valueOf(rs.getFloat("price")));
        return s;
    }
    
    @Override
    public List<Service> listNotAssessedClient(int clientId) {
        ArrayList<Service> services = new ArrayList();
        try {
            String query = "SELECT s.*, u.name as provider_name FROM spn.service s, spn.user u "
                    + " WHERE s.client_id=? and s.finish_date < 'TODAY' AND "
                    + " s.service_id NOT IN (SELECT service_id from spn.service_evaluation) "
                    + " AND u.user_id=s.provider_id";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service s = this.getService(rs);
                s.setProviderName(rs.getString("provider_name"));
                services.add(s);
            }
            conn.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return services;
    }
    
    @Override
    public List<Service> listNotAssessedProvider(int providerId) {
        ArrayList<Service> services = new ArrayList();
        try {
            String query = "SELECT s.*, u.name AS client_name, u.street || ' nº '||u.number|| ', '|| u.complements||' <br/> '||u.neighborhood || ', ' || u.city || '-'||u.state as client_address "
                    + " FROM spn.service s,spn.user u "
                    + " WHERE  provider_id=? and service_id IN "
                    + " (SELECT service_id FROM spn.service_evaluation "
                    + "WHERE p_dat_assessment IS NULL ) "
                    + " and u.user_id=s.client_id";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, providerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service s = this.getService(rs);
                s.setClientName(rs.getString("client_name"));
                s.setClientAddress(rs.getString("client_address"));
                services.add(s);
            }
            conn.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return services;
    }
    
    @Override
    public List<CompletedService> completedServiceProviderList(int providerId) {
        List<CompletedService> completedServices = new ArrayList();
        try {
            
            String query = "SELECT serv.*, u.name AS client_name,u.street || ' nº '||u.number|| "
                    + " ', '|| u.complements||' <br/> '|| u.neighborhood || ', ' || u.city || "
                    + " '-'||u.state as client_address FROM spn.user u,"
                    + " (SELECT * FROM spn.service s NATURAL JOIN spn.service_evaluation se "
                    + " WHERE s.provider_id=? AND se.c_dat_assessment IS NOT NULL AND "
                    + " se.p_dat_assessment IS NOT NULL) AS serv WHERE u.user_id = serv.client_id";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, providerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CompletedService compServ = new CompletedService();
                Service service = this.getService(rs);
                service.setClientName("client_name");
                service.setClientAddress("client_address");
                compServ.setService(service);
                compServ.setServiceEvaluation(this.getServiceEvaluation(rs));
                completedServices.add(compServ);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return completedServices;
    }
    
    @Override
    public List<CompletedService> completedServiceClientList(int providerId) {
        List<CompletedService> completedServices = new ArrayList();
        try {
            String query = "SELECT serv.*, u.name AS provider_name, u.city || "
                    + " '-'||u.state as provider_address FROM spn.user u,"
                    + " (SELECT * FROM spn.service s NATURAL JOIN spn.service_evaluation se "
                    + " WHERE s.client_id=? AND se.c_dat_assessment IS NOT NULL AND "
                    + " se.p_dat_assessment IS NOT NULL) AS serv WHERE u.user_id = serv.provider_id";
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, providerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CompletedService compServ = new CompletedService();
                Service service = this.getService(rs);
                service.setProviderName("provider_name");
                service.setProviderAddress("provider_address");
                compServ.setService(service);
                compServ.setServiceEvaluation(this.getServiceEvaluation(rs));
                completedServices.add(compServ);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return completedServices;
        
    }
    
    private ServiceEvaluation getServiceEvaluation(ResultSet rs) throws SQLException {
        ServiceEvaluation se = new ServiceEvaluation();
        se.setServiceId(rs.getInt("service_id"));
        se.setcComment(rs.getString("c_comment"));
        se.setcPrice((int) rs.getFloat("c_price"));
        se.setcQualityOfCare(rs.getInt("c_quality_of_care"));
        se.setcQualityOfService(rs.getInt("c_quality_of_service"));
        se.setcRealFinishDate(rs.getString("c_real_finish_date"));
        se.setcRealStartDate(rs.getString("c_real_start_date"));
        se.setcRespectForDeadlines(rs.getInt("c_respect_for_deadlines"));
        se.setpAppropriatePayment(rs.getInt("p_appropriate_payment"));
        se.setpComment(rs.getString("p_comment"));
        se.setpComunicationWithClient(rs.getInt("p_communication_with_client"));
        se.setpMaterialsSupply(rs.getInt("p_materials_supply"));
        se.setcPictures(this.getPictures(se.getServiceId(), "client"));
        Logger.getInstance().setLog("tamanho cPictures: "+se.getcPictures().size());
        se.setpPictures(this.getPictures(se.getServiceId(), "provider"));
           Logger.getInstance().setLog("tamanho pPictures: "+se.getpPictures().size());
        return se;
    }
    
    private List<Picture> getPictures(int serviceId, String type) {
        
        List<Picture> pictures = new ArrayList();
        String tableName = null;
        try {
            if (type.equalsIgnoreCase("client")) {
                tableName = "spn.service_client_picture";
            } else if (type.equalsIgnoreCase("provider")) {
                tableName = "spn.service_provider_picture";
            }
            
            String query = "SELECT * FROM " + tableName + " WHERE service_id=?;";
            Logger.getInstance().setLog("carrega de "+tableName);
            Connection conn = daoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, serviceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Picture picture = new Picture();
                picture.setServiceId(serviceId);
                picture.setPictureId(rs.getInt("picture_id"));
                picture.setImage(rs.getString("path"));
                pictures.add(picture);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(PgServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pictures;
    }
}
