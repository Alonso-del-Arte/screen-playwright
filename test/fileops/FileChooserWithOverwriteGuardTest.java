/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileops;

import java.awt.Component;
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
     * Test of approveSelection method, of class FileChooserWithOverwriteGuard.
     */
    @Test
    public void testApproveSelection() {
        System.out.println("approveSelection");
        FileChooserWithOverwriteGuardImpl chooser 
                = new FileChooserWithOverwriteGuardImpl();
        chooser.setSelectedFile(sampleFile);
        chooser.setMockResponse(JOptionPane.YES_OPTION);
        boolean opResult = attemptOverwrite(chooser, 
                "approve selection overwrite test");
        assert opResult : "Should have been able to overwrite sample file";
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

    static class FileChooserWithOverwriteGuardImpl 
            extends FileChooserWithOverwriteGuard {
        
        private int mockResponse = JOptionPane.YES_OPTION;
        
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
        
    }
    
}
