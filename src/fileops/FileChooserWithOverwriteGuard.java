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
package fileops;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Just a simple tweak on <code>JFileChooser</code> so that it asks before 
 * overwriting an existing file. This is done simply by overriding 
 * <code>JFileChooser.approveSelection()</code>.
 * @author Alonso del Arte, based on the tutorial at 
 * http://www.thepcwizard.in/2013/05/complete-guide-to-jfilechooser.html
 */
public class FileChooserWithOverwriteGuard extends JFileChooser {
    
    int getConfirmationResponse(String filename) {
        return JOptionPane.showConfirmDialog(this,  
                "Do you want to overwrite the existing file?", filename 
                        + " already exists", JOptionPane.YES_NO_CANCEL_OPTION);
    }
    
    /**
     * Approves the selection, though first checking whether or not the file 
     * already exists. If the file already exists, the user is presented with 
     * the option to go ahead or think of a different name for the file.
     */
    @Override
    public void approveSelection() {
        File file = this.getSelectedFile();
        if (file.exists()) {
            switch (this.getConfirmationResponse(file.getName())) {
                case JOptionPane.YES_OPTION:
                    super.approveSelection();
                case JOptionPane.NO_OPTION:
                    return;
                case JOptionPane.CLOSED_OPTION:
                case JOptionPane.CANCEL_OPTION:
                    this.cancelSelection();
            }
        } else {
            super.approveSelection();
        }
    }
    
}
