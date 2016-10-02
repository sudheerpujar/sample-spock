package com.hsbc.testing.automation.spock.unit.dao.specs

import com.hsbc.testing.automation.spock.data.builder.impl.SpockVoBuilder
import com.hsbc.testing.automation.spock.data.dao.SpockDao
import com.hsbc.testing.automation.spock.data.vo.SpockVo
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification

import java.sql.Timestamp


/**
 * Created by sudhe on 24-07-2016.
 */
@ContextConfiguration(locations ="classpath:conf/sample-spock-config.xml")
@Slf4j
class SpockDaoSpec extends Specification {
    @Autowired
    SpockDao dao

    @Autowired
    SpockVoBuilder spockVoBuilder

    @Shared sql = Sql.newInstance("jdbc:h2:mem:sample-spock", "org.h2.Driver")

    def setupSpec(){
        sql.execute("CREATE TABLE spock(id INT , name VARCHAR, emailid VARCHAR ,amount NUMERIC,date TIMESTAMP)")
    }

    def cleanupSpec(){
        sql.execute("DROP TABLE spock")

    }

    def "No data in spock table, hence it is empty"(){
        expect : "dao.getAll().empty is true"
        dao.getAll().empty;
    }
    def "Only one spock was created"(){
        given: "the details of spock "
        def SpockVo spockVo = spockVoBuilder.id(1)
        .name("Spock The Great")
        .emailId("spock@spockians.com")
        .amount(110.00)
        .date(new Timestamp(GregorianCalendar.getInstance().getTimeInMillis()))
        .build()

        when : "the a spock was created"
        dao.createSpock(spockVo);
        then  : "only one spock is created"
        dao.getAll().size()==1;
    }
    def "A spock was modified"(){
        given: "the details of spock "

        def SpockVo spockVo = spockVoBuilder.id(1)
                .name("Spock The Great is King")
                .emailId("spock1@spockians.com")
                .amount(130.00)
                .date(new Timestamp(GregorianCalendar.getInstance().getTimeInMillis()))
                .build()
        when : "the a spock was modified"
        dao.modifySpock(spockVo);
        then  : "the modified spock was fetched by id"
        def modifiedSpock =dao.getById(spockVo.id);
        modifiedSpock.equals(spockVo)
        and  : "the modified spock was fetched by emailId"
        def modifiedSpock2 =dao.getByEmailId(spockVo.emailId);
        modifiedSpock2.equals(spockVo)
    }

    def "A spock was removed" (){
        given: " the spock id"
        def spockId=1;
        when: "the spock was removed"
        dao.removeSpock(spockId);
        then : "no spock was found"
        def deletedSpock=dao.getById(spockId)
        deletedSpock==null
    }

}