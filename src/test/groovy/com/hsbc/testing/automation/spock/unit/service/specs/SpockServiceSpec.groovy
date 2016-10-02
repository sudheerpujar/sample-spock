package com.hsbc.testing.automation.spock.unit.service.specs

import com.hsbc.testing.automation.spock.bean.Spock
import com.hsbc.testing.automation.spock.builder.impl.SpockBuilder
import com.hsbc.testing.automation.spock.service.SpockService
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification


/**
 * Created by sudhe on 30-07-2016.
 */
@ContextConfiguration(locations ="classpath:conf/sample-spock-config.xml")
@Slf4j
class SpockServiceSpec extends Specification {
    @Shared sql = Sql.newInstance("jdbc:h2:mem:sample-spock", "org.h2.Driver")

    @Autowired
    SpockService spockService;
    @Autowired
    SpockBuilder spockBuilder;

    def setupSpec(){
        sql.execute("CREATE TABLE spock(id INT , name VARCHAR, emailid VARCHAR ,amount NUMERIC,date TIMESTAMP)")
    }

    def cleanupSpec(){
        sql.execute("DROP TABLE spock")

    }

    def "No spocks in List, hence it is empty"(){
        expect : "spockBuilder.getAll().empty is true"
        spockService.getAll().empty;
    }

    def "Only one spock was created"(){
        given: "the details of spock "
        Spock spock=spockBuilder.id(1)
                .name("Spock The Great")
                .emailId("spock@spockians.com")
                .date(GregorianCalendar.getInstance().time)
                .amount(100.00)
                .build();
        when : "the a spock was created"
        spockService.createSpock(spock);
        then  : "only one spock is created"
        spockService.getAll().size()==1;
    }

    def "A spock was modified"(){
        given: "the details of spock "
        Spock spock=spockBuilder.id(1)
                .name("Spock The Great is King")
                .emailId("spock1@spockians.com")
                .date(GregorianCalendar.getInstance().time)
                .amount(130.00)
                .build();
        when : "the a spock was modified"
        spockService.modifySpock(spock);
        then  : "the modified spock was fetched by id"
        def modifiedSpock =spockService.getById(spock.getId())
        modifiedSpock.equals(spock)
        and  : "the modified spock was fetched by emailId"
        def modifiedSpock2 =spockService.getByEmailId(spock.getEmailId())
        modifiedSpock2.equals(spock);
    }

    def "A spock was removed" (){
        given: " the spock id"
        def spockId=1;
        when: "the spock was removed"
        spockService.removeSpock(spockId);
        then : "no spock was found"
        def deletedSpock=spockService.getById(spockId)
        deletedSpock==null
    }

}