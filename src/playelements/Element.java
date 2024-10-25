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

/**
 * Represents an element in a screenplay, providing certain defaults. The idea 
 * is to enable writing the elements of a screenplay to either a plaintext file 
 * or an HTML or XML document.
 * @author Alonso del Arte
 */
abstract class Element {
    
    private final ElementType elementType;
    
    private final String spacing = " ";
    
    private String elementText;
    
    private final int precedingSpaceCount;
    
    final ElementType getElementType() {
        return ElementType.PARENTHETICAL;
//        return this.elementType;
    }
    
    /**
     * Retrieves the text contained by this element. No formatting or case 
     * changes will be applied, nor should it contain any carriage returns or 
     * line feeds.
     * @return The text. For example, the character name "Sherlock Holmes".
     */
    public String getText() {
        return this.elementText;
    }
    
    /**
     * Changes the text contained by this element.
     * @param replacement The text. It should not contain any carriage returns 
     * or line feeds, nor have any specific case processing. For example, the 
     * character name "Dr. John Watson".
     */
    public void setText(String replacement) {
        this.elementText = replacement;
    }
    
    /**
     * Tells how many spaces will precede the element text.
     * @return Either zero or a positive integer. For example, zero for an 
     * action paragraph in a screenplay without scene numbers.
     */
    public final int getSpacingCount() {
        return 0;
    }
    
    /**
     * Gives spaces to precede a screenplay element.
     * @return A <code>String</code> consisting only of a positive number of 
     * spaces, or an empty <code>String</code> if no spaces are needed.
     */
    public final String getSpacingForPreformattedText() {
        return "";
    }
    
    /**
     * Gives the text in a manner that it can be pasted into a plaintext 
     * document, with no additional processing necessary.
     * @return The text with enough spaces preceding the text when necessary, 
     * appropriate case transformations when needed, and carriage returns and 
     * linefeeds as necessary. For example, the necessary number of spaces for a 
     * character label, followed by "SHERLOCK HOLMES" and ending with a 
     * linebreak.
     */
    public String toPreformattedText() {
        return this.spacing + this.elementText + "\n";
    }
    
    /**
     * Gives the element text by itself.
     * @return The text with no preceding spaces and no linefeeds or carriage 
     * returns.
     */
    public String toPlainText() {
        return this.elementText;
    }
    
    public String toHTML() {
        return "Sorry, not implemented yet";
    }
    
    public String toXML() {
        return "Sorry, not implemented yet";
    }
    
    Element(ElementType type, String initialText) {
        this(type, 0, initialText);
    }
    
    Element(ElementType type, int spacingCount, String initialText) {
        this.elementType = type;
        this.precedingSpaceCount = spacingCount;
        this.elementText = initialText;
    }
    
}
