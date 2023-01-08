package com.example.team8.websiteMonitor.repository;

import com.example.team8.websiteMonitor.model.Checks;
import com.example.team8.websiteMonitor.model.ChecksResponse;
import com.example.team8.websiteMonitor.model.WebMonitorUserInput;
import com.example.team8.websiteMonitor.model.WebSiteMonitorInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WebsiteMonitorRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class CheckRowMapper implements RowMapper<Checks> {

        @Override
        public Checks mapRow(ResultSet rs, int rowNum) throws SQLException {
            Checks check = new Checks();
            check.setUserId(rs.getInt("userId"));
            check.setCheckName(rs.getString("checkName"));
            check.setFrequency(rs.getString("frequency"));
            check.setUrl(rs.getString("url"));
            return check;
        }
    }

    class ChecksResponseRowMapper implements RowMapper<ChecksResponse> {

        @Override
        public ChecksResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            ChecksResponse checkRes = new ChecksResponse();
            checkRes.setCheckId(rs.getInt("checkId"));
            checkRes.setStatus(rs.getString("status"));
            checkRes.setHitTime(rs.getString("hitTime"));
            return checkRes;
        }
    }

    public int createNewUser(WebMonitorUserInput webMonitorUserInput) {
        return jdbcTemplate.update("insert into users(userName, emailId, phoneNum)" + "values(?,?,?)",
                new Object[]{
                        webMonitorUserInput.getName(), webMonitorUserInput.getEmailId(),
                        webMonitorUserInput.getPhoneNum()
                });
    }

    public int createNewCheck(WebSiteMonitorInput webSiteMonitorInput) {
        return jdbcTemplate.update("insert into checks(userId, checkName, frequency, url)" + "values(?,?,?,?)",
                new Object[]{
                        webSiteMonitorInput.getUserId(), webSiteMonitorInput.getCheckName(),
                        webSiteMonitorInput.getFrequency(), webSiteMonitorInput.getUrl()
                });
    }

    public int deleteCheck(int id) {
        return jdbcTemplate.update("delete from checks where checkId =?",
                new Object[]{
                        id
                });
    }

    public int updateCheck(WebSiteMonitorInput webSiteMonitorInput) {
        return jdbcTemplate.update("update checks set checkName=?, frequency=?, url=?",
                new Object[]{
                        webSiteMonitorInput.getCheckName(),
                        webSiteMonitorInput.getFrequency(), webSiteMonitorInput.getUrl()
                });
    }

    public List<Checks> getAllChecks(int userId) {
        return jdbcTemplate.query("select userId, checkName, frequency, url from Checks where userid=?",
                new Object[]{
                        userId
                }, new CheckRowMapper());
    }

    public Checks findCheckById(int checkId) {
        return jdbcTemplate.queryForObject(
                "select userId, frequency, url from Checks where checkId=?",
                new Object[]{
                        checkId
                }, new BeanPropertyRowMapper<Checks>(Checks.class)
        );
    }

    public List<ChecksResponse> getAllChecksResponses(int checkId) {
        return jdbcTemplate.query("select checkId, status, hitTime from CheckResponse where checkId=?",
                new Object[]{
                        checkId
                }, new ChecksResponseRowMapper());
    }
}
