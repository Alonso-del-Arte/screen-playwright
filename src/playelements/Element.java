/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playelements;

/**
 *
 * @author Alonso del Arte
 */
abstract class Element {
    
    private String elementText;
    
    // TODO: Write tests for this
    public String getText() {
        return "NOT IMPLEMENTED YET";
    }
    
    public void setText(String replacement) {
        // TODO: Write tests for this
        this.elementText = "Not implemented yet";
    }
    
    public String getSpacingForPreformattedText() {
        return "";
    }
    
    /**
     * Gives the text in a manner that it can be pasted into a plaintext 
     * document
     * @return The text with enough
     */
    public abstract String toPreformattedText();
    
    public abstract String toPlainText();
    
    public abstract String toXML();
    
    Element(String initialText) {
        this.elementText = initialText;
    }
    
}
