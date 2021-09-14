package com.example.dep;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple Dep.
 */
public class DepTest 

{

    /**
     * Tests if Dep.Hello(strin name) adds "Hello " and "!" to a name
     */
    @Test
    public void TestDepHelloWithCorrectInputVariables()
    {
        // Arrange - Setup the code to be used
        Dep department = new Dep();

        // Act - Execute the method to be tested
        String toCompare = department.hello("Joe");
        
        // Assert - Check if the method postconditions is as expected
        assertEquals("Hello Joe!", toCompare);
    }
}
