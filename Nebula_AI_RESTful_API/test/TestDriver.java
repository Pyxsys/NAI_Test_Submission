import junit.textui.TestRunner;
import org.junit.runner.JUnitCore;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestDriver {

    public static void main(String[] args) {
        System.out.println("Testing Started...");

        //Record System.out instance
        PrintStream oldPrintStream = System.out;
        FileOutputStream outputFile = null;

        //swap to file stream
        try {
            outputFile = new FileOutputStream("TestResult.txt");

            PrintStream newPrintStream = new PrintStream(outputFile);
            System.setOut(newPrintStream);

            Result result = (Result) JUnitCore.runClasses(TestSuite.class);

            // Print the results
            System.out.println("Runtime: " + result.getRunTime() + "ms");

            if(result.wasSuccessful()) { System.out.println("All tests passed."); }
            else{
                System.out.println(result.getFailureCount() + "/" + result.getRunCount() + " tests passed.\n");
                for(Failure f : result.getFailures()){
                    System.out.println(f.toString());
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestDriver.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Reset to old System.out instance
            System.setOut(oldPrintStream);
            System.out.println("Testing Completed!\nRefer to output folder \"TestResult.txt\" for result.");

            try {
                outputFile.close();
            } catch (IOException ex) {
                Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
