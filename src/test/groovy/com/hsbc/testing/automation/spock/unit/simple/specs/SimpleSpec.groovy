package com.hsbc.testing.automation.spock.unit.simple.specs

import groovy.util.logging.Slf4j
import spock.lang.Specification
import spock.lang.Unroll


/**
 * Created by sudhe on 23-07-2016.
 */
@Slf4j
class SimpleSpec extends Specification {

    def setupSpec(){
        log.info("setupSpec() - Runs once per Specification");
    }

    def setup(){
        log.info("setup() Runs before every feature method");
    }

    def cleanup(){
        log.info ("Cleanup method - Runs  after every feature method.");
    }

    def cleanupSpec(){
        log.info ("cleanupSpec() - Runs only once per specification");
    }

    def "Maximum of two number"(){
        given: "Two numbers 10 and 20"
        def a=10
        def b=20
        when: " I calculate the maximum of 10 and 20 "
        def c=Math.max(a,b)
        then : " the value I got is 20"
        c==20
    }


    def "Minimum of two number"(){
        given: "Two numbers a and b"

        when: " I calculate the minimum of a and b "
        def minimum=Math.min(a,b)
        then : " the value I got is c"
        minimum==c
        where: "possible values are "
        a| b | c
        10|20|10
        10|5|5
    }

    @Unroll
    def "Add the two numbers"(){
        expect: "The sum of #a and #b should be #c "
        Math.addExact(a,b)==c
        where: "a=#a and b=#b"
        a| b || c
        10|20||30
        30|20||50
        40|20||60
    }

    @Unroll
    def "Maximum and Minimum of two number"(){
        given: "Two numbers #a and #b"

        when: " I calculated the maximum of #a and #b "
        def maximum=Math.max(a,b)

        and : " I calculated the minimum of #a and #b "
        def minimum=Math.min(a,b)

        then : " the maximum value I got is #c"
        maximum==c
        and : " the minimum value I got is #d"
        minimum==d
        where: "a is #a, b is #b, c is #c and d is #d   "
        a | b || c | d
        10|20 || 20|10
        30|50 || 50|30
    }

    @Unroll
    def "Sum and product"() {
        given: "Two numbers #a and #b"

        and: " a number #c"

        when: " I calculated the sum of #a and #b "
        def addition = Math.addExact(a, b)

        and: " multiplied the sum c"
        def product = Math.multiplyExact(addition, c)

        then: "the resultant value is #d"
        product == d

        where: "a is #a, b is #b, c is #c and d is #d "
        a | b | c || d
        1 | 2 | 3 || 9
        8 | 1 | 1 || 9
    }


}
