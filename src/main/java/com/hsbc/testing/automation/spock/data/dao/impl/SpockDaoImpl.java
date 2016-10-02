package com.hsbc.testing.automation.spock.data.dao.impl;

import com.hsbc.testing.automation.spock.data.dao.SpockDao;
import com.hsbc.testing.automation.spock.data.mapper.impl.SpockMapper;
import com.hsbc.testing.automation.spock.data.vo.SpockVo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sudhe on 24-07-2016.
 */
public class SpockDaoImpl implements SpockDao{
    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SpockMapper spockMapper;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource=dataSource;
        this.namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void setMapper(SpockMapper spockMapper) {
        this.spockMapper=spockMapper;
    }

    @Override
    public SpockMapper getMapper() {
        return spockMapper;
    }

    @Override
    public List<SpockVo> getAll() {
        final String SQL="SELECT id,name,emailid,amount,date FROM spock";
        List<SpockVo> spocks= namedParameterJdbcTemplate.query(SQL,spockMapper);
        return spocks;
    }

    @Override
    public SpockVo getById(int id) {
        final String SQL="SELECT id,name,emailid,amount,date FROM spock where id=:id";
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id",id);
        SpockVo spock=null;
        try {
            spock = (SpockVo) namedParameterJdbcTemplate.queryForObject(SQL, paramMap,spockMapper);
        }catch (EmptyResultDataAccessException e){
            //do nothing;
        }
        return spock;
    }

    @Override
    public SpockVo getByEmailId(String emailId) {
        final String SQL="SELECT id,name,emailid,amount,date FROM spock where emailid=:emailid";
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("emailid",emailId);
        SpockVo spock= (SpockVo) namedParameterJdbcTemplate.queryForObject(SQL,paramMap,spockMapper);
        return spock;
    }

    @Override
    public void createSpock(SpockVo vo) {
        final String SQL="INSERT INTO spock(id,name,emailid,amount,date) VALUES(:id,:name,:emailid,:amount,:date)";
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id",vo.getId());
        paramMap.put("name",vo.getName());
        paramMap.put("emailid",vo.getEmailId());
        paramMap.put("amount",vo.getAmount());
        paramMap.put("date",vo.getDate());
        namedParameterJdbcTemplate.update(SQL,paramMap);
    }

    @Override
    public void modifySpock(SpockVo vo) {
        final String SQL="UPDATE spock set name=:name,emailid=:emailid,amount=:amount,date=:date where id=:id";
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id",vo.getId());
        paramMap.put("name",vo.getName());
        paramMap.put("emailid",vo.getEmailId());
        paramMap.put("amount",vo.getAmount());
        paramMap.put("date",vo.getDate());
        namedParameterJdbcTemplate.update(SQL,paramMap);
    }

    @Override
    public void removeSpock(int id) {
        final String SQL="DELETE FROM spock where id=:id";
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id",id);
        namedParameterJdbcTemplate.update(SQL,paramMap);
    }
}

