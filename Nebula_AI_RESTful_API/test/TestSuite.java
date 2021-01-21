import API.Worker;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class TestSuite {

    @Test
    public void testWorkerConstruction(){
        Worker w = Worker.initDetailedWorker(
                "0",
                "p2_pc",
                48,
                125900000,
                261900000,
                "GeForce RTX 2070",
                8000000,
                0,
                1726000,
                1726000,
                0);

        assertEquals("p2_pc", w.getWorkerName());
    }

    @Test
    public void testSave2Csv() throws FileNotFoundException {
        //send http request
        HttpServletRequest request; //TODO Initialize a mock
        String result = "";         //result to be obtained from last line of file

        //obtain last line of file

        try {
            BufferedReader input = new BufferedReader(new FileReader("CallData.csv"));
            String line;
            while ((line = input.readLine()) != null) {
                result = line;
            }
        }
        catch(FileNotFoundException e){
            assertEquals("FileNotFoundException Occurred", "File \'CallData.csv\' not found");
        }
        catch(IOException e) {
            assertEquals("IOException Occurred", e.getMessage());
        }

        String expected_string = "api_call,api_key,api_value"; //TODO mock HTTP Result broken

        assertEquals("",result);
    }

    @Test
    public void testGetServlet(){
        //send http request
        HttpServletRequest request; //TODO Initialize a mock
        String result = null;       //result to be obtained from a callback

        //ensure received json matches
        String expected_json =
                "{" +
                        "\"worker_name\":\"p2_pc\",\"id\":\"0\"," +
                        "\"cpu_available\":48,\"cpu_inUse\":0," +
                        "\"ram_available\":125900000,\"ram_inUse\":1726000," +
                        "\"vmem_available\":261900000,\"vmem_inUse\":1726000," +
                        "\"gpu_name\":\"GeForce RTX 2070\"," +
                        "\"gpu_available\":8000000," +
                        "\"gpu_inUse\":0" +
                "}";

        assertEquals(expected_json, result);
    }

}
