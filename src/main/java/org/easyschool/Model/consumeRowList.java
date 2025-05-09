package org.easyschool.Model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class consumeRowList implements RowMapper<Contect> {
    @Override
    public Contect mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contect msg = new Contect();
        msg.setContact_id(rs.getInt("contact_id"));
        msg.setName(rs.getString("name"));
        msg.setMobileNum(rs.getString("mobile_num"));
        msg.setMail_id(rs.getString("mail_id"));
        msg.setSubject(rs.getString("subject"));
        msg.setMessage(rs.getString("message"));
        msg.setStatus(rs.getString("status"));
        msg.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        msg.setCreatedBy(rs.getString("created_by"));
        if(null != rs.getTimestamp("update_at")) {
            msg.setUpdateAt(rs.getTimestamp("update_at").toLocalDateTime());
        }
        if(null != rs.getTimestamp("update_by")) {
            msg.setUpdateBy(rs.getString("update_by"));
        }
        return msg;
    }
}
