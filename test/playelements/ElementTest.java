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

import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the Element class.
 * @author Alonso del Arte
 */
public class ElementTest {
    
    static final Random RANDOM = new Random();
    
    @Test
    public void testGetElementType() {
        System.out.println("getElementType");
        fail("REWRITE");
//        String expected = "example" + RANDOM.nextInt();
//        Element instance = new Element(expected, "FOR TESTING PURPOSES ONLY") {
//        };
//        String actual = instance.getElementType();
//        assertEquals(expected, actual);
    }
    
    /**
     * Test of the getText function, of the Element class.
     */
    @Test
    public void testGetText() {
        System.out.println("getText");
        String expected = "Expecting to get this text back";
        Element instance = new ElementImpl(0, expected);
        String actual = instance.getText();
        assertEquals(expected, actual);
    }

    /**
     * Test of the setText procedure, of the Element class.
     */
    @Test
    public void testSetText() {
        System.out.println("setText");
        String initial = "This was the initial text";
        Element instance = new ElementImpl(0, initial);
        String expected = "This is the replacement text";
        instance.setText(expected);
        String actual = instance.getText();
        assertEquals(expected, actual);
    }
    
    /**
     * Test of the getSpacingForPreformattedText function, of the Element class.
     */
    @Test
    public void testGetSpacingForPreformattedText() {
        System.out.println("getSpacingForPreformattedText");
        Element instance = new ElementImpl(0, "For testing purposes");
        String expected = "";
        String actual = instance.getSpacingForPreformattedText();
        assertEquals(expected, actual);
    }

    public class ElementImpl extends Element {

        public ElementImpl(int spacingCount, String initialText) {
            super(ElementType.ACTION, spacingCount, initialText);
        }

    }
    
}
