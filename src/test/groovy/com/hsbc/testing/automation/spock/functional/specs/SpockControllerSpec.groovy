package com.hsbc.testing.automation.spock.functional.specs

import com.hsbc.testing.automation.spock.bean.RestSpock
import com.hsbc.testing.automation.spock.bean.Spock
import com.hsbc.testing.automation.spock.builder.impl.SpockBuilder
import groovy.sql.Sql
import org.h2.jdbcx.JdbcDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.client.RestTemplate
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise
import spock.lang.Unroll


/**
 * Created by sudhe on 05-08-2016.
 */
@ContextConfiguration(locations ="classpath:conf/sample-spock-config.xml")
@Stepwise
class SpockControllerSpec extends Specification {

    @Shared sql = Sql.newInstance("jdbc:h2:tcp://localhost/E:/runtime/database/h2/1.4.192/data/sample-spock","sa","pwd","org.h2.Driver")
    @Shared url="http://localhost:8998/spock/spocks"


    @Autowired  SpockBuilder spockBuilder

    def setupSpec(){
        sql.execute("delete from spock;")
    }

    def cleanupSpec(){
        sql.execute("delete from spock;")

    }

    def "Checking status of spocks"(){
        when : "/status is called "
            ResponseEntity entity = new RestTemplate().getForEntity(url+"/status", String.class)

        then : "/status returns Running"
            entity.statusCode==HttpStatus.OK
            "Running".equals((String)entity.body);
    }

    def "List All Spocks"(){
        when: "/all is called"
            ResponseEntity entity = new RestTemplate().getForEntity(url+"/all", List.class)
            List<Spock> spocks =(List<Spock>)entity.body
        then : "/all returns empty or a list"
            entity.statusCode==HttpStatus.OK
            spocks.empty || spocks.size()>0
    }

    @Unroll
    def "create spock #name"(){

        given: "spock #id, #name, #date, #emailid, #amount"
            RestSpock spockIn= new RestSpock(id,name,emailid,date,amount);
        when: "/create is called"
            RestTemplate restTemplate=new RestTemplate()
            Boolean result =restTemplate.postForObject(url+"/create",spockIn,Boolean.class)
        then: "returns true"
            result && sql.rows("select id from spock where id="+id).size()==1

        where: "the spock being created"
            id|name|date|emailid|amount
            1|"Spokito"|GregorianCalendar.getInstance().time|"spockito@spock.com"|1000
            2|"Spokian"|GregorianCalendar.getInstance().time|"spockian@spock.com"|12000
    }

    @Unroll
    def "modify spock #name"(){

        given: "spock #id, #name, #date, #emailid, #amount"
            RestSpock spockIn= new RestSpock(id,name,emailid,date,amount);
        when: "/update is called"
            RestTemplate restTemplate=new RestTemplate()
            Boolean result =restTemplate.postForObject(url+"/modify",spockIn,Boolean.class)
        then: "returns true"
            result && sql.rows("select id from spock where id="+id).size()==1

        where: "the spock being modified"
            id|name|date|emailid|amount
            1|"Spokito1"|GregorianCalendar.getInstance().time|"spockito1@spock.com"|14000
            2|"Spokian2"|GregorianCalendar.getInstance().time|"spockian2@spock.com"|12000
    }

    @Unroll
    def " get spock by #id"(){
        given: "spock #id, #name, #date, #emailid, #amount"
        RestSpock restSpock= new RestSpock(id,name,emailid,date,amount);
        when: "/get is called"
        RestTemplate restTemplate=new RestTemplate()
        ResponseEntity responseEntity=restTemplate.getForEntity(url+"/get/"+id,RestSpock.class);
        RestSpock restSpockOut = (RestSpock)responseEntity.getBody();
        then: "returns true"
        restSpock.equals(restSpockOut)

        where: "the spock being modified"
        id|name|date|emailid|amount
        1|"Spokito1"|GregorianCalendar.getInstance().time|"spockito1@spock.com"|14000
        2|"Spokian2"|GregorianCalendar.getInstance().time|"spockian2@spock.com"|12000
    }


    @Unroll
    def "remove spock #name"(){

        when: "/remove is called"
        RestTemplate restTemplate=new RestTemplate()
        restTemplate.delete(url+"/remove/"+id)

        then: "returns true"
        sql.rows("select id from spock where id="+id).size()==0

        where: "the spock being removed"
        id|name
        1|"Spokito1"
        2|"Spokian2"
    }

}