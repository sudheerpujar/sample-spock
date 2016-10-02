package com.hsbc.testing.automation.spock.unit.simple.specs

import spock.lang.Specification


/**
 * Created by sudhe on 23-07-2016.
 */
class ExceptionSpec extends Specification {

    def "Throw exception while popping up an empty stack"(){
        setup: "A new stack"
        def stack = new Stack()
        when: " I poped up the stack"
        stack.pop()
        then : " an exception is thrown"
        thrown(EmptyStackException)
    }

    def "No exception thrown while popping up a stack after a push"(){
        setup: "A new stack"
        def stack = new Stack()
        when: "I pushed into stack "
        stack.push("Push me")
        and: " I poped up the stack"
        stack.pop()
        then : " an exception is thrown"
        noExceptionThrown()

    }

    def "No EmptyStackException thrown while popping up a stack after a push"(){
        setup: "A new stack"
        def stack = new Stack()
        when: "I pushed into stack "
        stack.push("Push me")
        and: " I poped up the stack"
        stack.pop()
        then : " an exception is thrown"
        notThrown(EmptyStackException)
    }

}