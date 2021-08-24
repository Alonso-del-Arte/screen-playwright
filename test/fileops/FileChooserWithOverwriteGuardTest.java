/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileops;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the FileChooserWithOverwriteGuard class.
 * @author Alonso del Arte
 */
public class FileChooserWithOverwriteGuardTest {
    
    private static final Random RANDOM = new Random();
    
    private static final String TEMP_DIR_PATH 
            = System.getProperty("java.io.tmpdir");
    
    private static File sampleFile;
    
    private static final JFrame FRAME = new JFrame("For testing purposes only");
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        String fileName = "ExampleFile" + RANDOM.nextInt() + ".txt";
        String pathName = TEMP_DIR_PATH + File.separatorChar + fileName;
        sampleFile = new File(pathName);
        boolean opResult = sampleFile.createNewFile();
        String msg = "Should have been able to create temporary test file " 
                + pathName;
        assert opResult : msg;
        String text = "This text was written by setUpClass()";
        try (FileWriter writer = new FileWriter(sampleFile)) {
            writer.write(text);
        }
        System.out.println("Successfully created " + pathName 
                + " with the text \"" + text + "\"");
    }
    
    private static boolean attemptOverwrite(JFileChooser fileChooser, 
            String testLabel) {
        int choice = fileChooser.showSaveDialog(FRAME);
        switch (choice) {
            case JFileChooser.APPROVE_OPTION:
                String replacementText = "This text was placed by " + testLabel;
                try (FileWriter writer = new FileWriter(sampleFile)) {
                    writer.write(replacementText);
                } catch (IOException ioe) {
                    System.err.println("\"" + ioe.getMessage() + "\"");
                    return false;
                }
                return true;
            case JFileChooser.CANCEL_OPTION:
                return false;
            default:
                String excMsg = "Choice " + choice + " not recognized";
                throw new RuntimeException(excMsg);
        }
    }
    
    /**
     * Test of the approveSelection procedure, of the 
     * FileChooserWithOverwriteGuard class. If the user is informed that a file 
     * already exists but chooses to overwrite it anyway, the program should be 
     * able to go ahead and overwrite the file.
     */
    @Test
    public void testApproveSelection() {
        System.out.println("approveSelection");
        ChooserListener listener = new ChooserListener();
        FileChooserWithOverwriteGuardImpl chooser 
                = new FileChooserWithOverwriteGuardImpl(JOptionPane.YES_OPTION);
        chooser.setSelectedFile(sampleFile);
        chooser.addActionListener(listener);
        boolean opResult = attemptOverwrite(chooser, 
                "approve selection overwrite test");
        assert opResult : "Should have been able to overwrite sample file";
        assertEquals(1, listener.approveCallCount);
        assertEquals(0, listener.cancelCallCount);
    }
    
    /**
     * Another test of the approveSelection procedure, of the 
     * FileChooserWithOverwriteGuard class. If the user is informed that a file 
     * already exists and decides to click "No" so as to choose a different 
     * filename, neither the Approve Selection event nor the Cancel Selection 
     * event should be sent to any action listeners.
     */
    @Test
    public void testAllowUserToChooseDifferentFilename() {
        ChooserListener listener = new ChooserListener();
        FileChooserWithOverwriteGuardImpl chooser 
                = new FileChooserWithOverwriteGuardImpl(JOptionPane.NO_OPTION);
        chooser.setSelectedFile(sampleFile);
        chooser.addActionListener(listener);
        chooser.approveSelection();
        assertEquals(0, listener.approveCallCount);
        assertEquals(0, listener.cancelCallCount);
    }
    
    /**
     * Another test of the approveSelection procedure, of the 
     * FileChooserWithOverwriteGuard class. If the user is informed that a file 
     * already exists and decides not to overwrite it, the program should then 
     * leave the file alone.
     */
    @Test
    public void testRejectOverwrite() {
        ChooserListener listener = new ChooserListener();
        FileChooserWithOverwriteGuardImpl chooser 
                = new FileChooserWithOverwriteGuardImpl(JOptionPane
                        .CANCEL_OPTION);
        chooser.setSelectedFile(sampleFile);
        chooser.addActionListener(listener);
        boolean opResult = attemptOverwrite(chooser, 
                "reject selection overwrite test");
        assert !opResult : "Should not have been able to overwrite sample file";
        assertEquals(0, listener.approveCallCount);
        assertEquals(1, listener.cancelCallCount);
    }
    
    /**
     * Another test of the approveSelection procedure, of the 
     * FileChooserWithOverwriteGuard class. If the user is informed that a file 
     * already exists and decides to close the dialog rather than click 
     * "Cancel," the overwrite should still be canceled.
     */
    @Test
    public void testClosedSameAsRejectOverwrite() {
        ChooserListener listener = new ChooserListener();
        FileChooserWithOverwriteGuardImpl chooser 
                = new FileChooserWithOverwriteGuardImpl(JOptionPane
                        .CLOSED_OPTION);
        chooser.setSelectedFile(sampleFile);
        chooser.addActionListener(listener);
        boolean opResult = attemptOverwrite(chooser, 
                "close dialog box test");
        assert !opResult : "Should not have been able to overwrite sample file";
        assertEquals(0, listener.approveCallCount);
        assertEquals(1, listener.cancelCallCount);
    }
    
    /**
     * Another test of the approveSelection procedure, of the 
     * FileChooserWithOverwriteGuard class. If the file does not already exist, 
     * the FileChooserWithOverwriteGuard should not block the creation of the 
     * file.
     */
    @Test
    public void testAllowNewFileCreation() {
        ChooserListener listener = new ChooserListener();
        FileChooserWithOverwriteGuardImpl chooser 
                = new FileChooserWithOverwriteGuardImpl(JOptionPane.YES_OPTION);
        String fileName = "ExtraExampleFile" + RANDOM.nextInt() + ".txt";
        String pathName = TEMP_DIR_PATH + File.separatorChar + fileName;
        File file = new File(pathName);
        String preMsg = pathName + " should not already exist";
        assert !file.exists() : preMsg;
        chooser.setSelectedFile(file);
        chooser.addActionListener(listener);
        chooser.approveSelection();
        assertEquals(1, listener.approveCallCount);
        assertEquals(0, listener.cancelCallCount);
    }
    
    @After
    public void tearDown() throws FileNotFoundException {
        System.out.println("Sample file has the following text:");
        try (Scanner scanner = new Scanner(sampleFile)) {
            while (scanner.hasNext()) {
                System.out.println("\"" + scanner.nextLine() + "\"");
            }
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("About to delete file " + sampleFile.getPath());
        boolean opResult = sampleFile.delete();
        String msg = "Should have been able to delete " + sampleFile.getName();
        assert opResult : msg;
        System.out.println("File successfully deleted.");
    }

    private static class FileChooserWithOverwriteGuardImpl 
            extends FileChooserWithOverwriteGuard {
        
        private int mockResponse;
        
        void setMockResponse(int responseCode) {
            this.mockResponse = responseCode;
        }
        
        @Override
        int getConfirmationResponse(String filename) {
            return this.mockResponse;
        }
        
        @Override
        public void cancelSelection() {
            super.cancelSelection();
            this.mockResponse = JOptionPane.CANCEL_OPTION;
        }
        
        @Override
        public int showSaveDialog(Component parent) {
            this.approveSelection();
            switch (this.mockResponse) {
                case JOptionPane.YES_OPTION:
                    return JFileChooser.APPROVE_OPTION;
                case JOptionPane.CLOSED_OPTION:
                case JOptionPane.CANCEL_OPTION:
                    return JFileChooser.CANCEL_OPTION;
                default:
                    String excMsg = "Response code " + this.mockResponse 
                            + " not recognized";
                    throw new RuntimeException(excMsg);
            }
        }

        public FileChooserWithOverwriteGuardImpl(int response) {
            this.mockResponse = response;
        }
        
    }
    
    private static class ChooserListener implements ActionListener {
        
        int approveCallCount = 0;
        
        int cancelCallCount = 0;

        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            switch (command) {
                case JFileChooser.APPROVE_SELECTION:
                    this.approveCallCount++;
                    break;
                case JFileChooser.CANCEL_SELECTION:
                    this.cancelCallCount++;
                    break;
                default:
                    System.out.println("Command \"" + command 
                            + "\" is not of interest to this listener");
            }
        }
        
    }
    
}
