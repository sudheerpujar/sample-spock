package com.hsbc.testing.automation.spock.unit.simple.specs

import groovy.sql.Sql
import groovy.util.logging.Slf4j
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by sudhe on 23-07-2016.
 */

@Slf4j
class DatabaseSpec extends Specification {

    @Shared sql = Sql.newInstance("jdbc:h2:mem:sample-spock", "org.h2.Driver")

    def setupSpec(){

        sql.execute("CREATE TABLE maxdata(a INT , b INT, max INT)")
        sql.execute("INSERT INTO maxdata VALUES(1,2,2)")
        sql.execute("INSERT INTO maxdata VALUES(10,5,10)")
        sql.execute("INSERT INTO maxdata VALUES(11,32,32)")

        sql.execute("CREATE TABLE mindata(a INT , b INT,min INT)")
        sql.execute("INSERT INTO mindata VALUES(1,2,1)")
        sql.execute("INSERT INTO mindata VALUES(10,5,5)")
        sql.execute("INSERT INTO mindata VALUES(11,32,11)")
    }

    def cleanupSpec(){
        sql.execute("DROP TABLE maxdata")
        sql.execute("DROP TABLE mindata")
    }

    def "Maximum of two numbers"() {
        expect : "Max of two number"
        Math.max(a,b)==c
        where: "data is being fetch from in memory database"
        [a, b, c] << sql.rows("select a, b, max from maxdata")
    }
    @Unroll
    def "Maximum of two numbers #a and #b "() {
        expect : "Max of two number #a and #b is #c"
        Math.max(a,b)==c
        where: "data is being fetch from in memory database"
        [a, b, c] << sql.rows("select a, b, max from maxdata")
    }

    def "Minimum of two numbers"() {
        expect : "Min of two number"
        Math.min(a,b)==c
        where: "data is being fetch from in memory database"
        [a, b, c] << sql.rows("select a, b, min from mindata")
    }

    @Unroll
    def "Minimum of two numbers #a and #b"() {
        expect : "Min of two number #a and #b is #c"
        Math.min(a,b)==c
        where: "data is being fetch from in memory database"
        [a, b, c] << sql.rows("select a, b, min from mindata")
    }
}