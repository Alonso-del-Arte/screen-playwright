/*
 * Copyright (C) 2021 Alonso del Arte
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

/**
 *
 * @author Alonso del Arte
 */
public class ElementTest {
    
    /**
     * Test of getText method, of class Element.
     */
    @Test
    public void testGetText() {
        System.out.println("getText");
        String expected = "Expecting to get this text back";
        Element instance = new ElementImpl(expected);
        String actual = instance.getText();
        assertEquals(expected, actual);
    }

    /**
     * Test of setText method, of class Element.
     */
    @Test
    public void testSetText() {
        System.out.println("setText");
        String initial = "This was the initial text";
        Element instance = new ElementImpl(initial);
        String expected = "This is the replacement text";
        instance.setText(expected);
        String actual = instance.getText();
        assertEquals(expected, actual);
    }
    
    /**
     * Test of the getSpacingForPreformattedText function of the Element class.
     */
    @Test
    public void testGetSpacingForPreformattedText() {
        System.out.println("getSpacingForPreformattedText");
        Element instance = new ElementImpl("For testing purposes");
        String expected = "";
        String actual = instance.getSpacingForPreformattedText();
        assertEquals(expected, actual);
    }

    public class ElementImpl extends Element {

        private static final String DEFAULT_TEST_TEXT 
                = "For testing purposes only";
        
        @Override
        public String toPreformattedText() {
            return DEFAULT_TEST_TEXT;
        }

        @Override
        public String toPlainText() {
            return DEFAULT_TEST_TEXT;
        }

        @Override
        public String toXML() {
            return DEFAULT_TEST_TEXT;
        }

        public ElementImpl(String initialText) {
            super(initialText);
        }

    }
    
}
