/*
 * Copyright (C) 2024 Alonso del Arte
 *
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any later 
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU General Public License along with 
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package playelements;

import org.junit.Test;
import static org.junit.Assert.*;

import static playelements.ElementTest.RANDOM;

/**
 * Tests of the DialogueParenthetical class.
 * @author Alonso del Arte
 */
public class DialogueParentheticalTest {
    
    @Test
    public void testGetElementType() {
        System.out.println("getElementType");
        Element instance = new DialogueParenthetical("expected");
        String expected = "parenthetical";
        String actual = instance.getElementType();
        assertEquals(expected, actual);
    }
    
    /**
     * Test of toPreformattedText method, of class DialogueParenthetical.
     */
//    @Test
    public void testToPreformattedText() {
        System.out.println("toPreformattedText");
        DialogueParenthetical instance = null;
        String expResult = "";
//        String result = instance.toPreformattedText();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toPlainText method, of class DialogueParenthetical.
     */
//    @Test
    public void testToPlainText() {
        System.out.println("toPlainText");
        DialogueParenthetical instance = null;
        String expResult = "";
//        String result = instance.toPlainText();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toXML method, of class DialogueParenthetical.
     */
//    @Test
    public void testToXML() {
        System.out.println("toXML");
        DialogueParenthetical instance = null;
        String expResult = "";
//        String result = instance.toXML();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
