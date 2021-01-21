package API;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

//Servlet class
public class ApiServlet extends HttpServlet{

    private static String call_data_csv_path = "CallData.csv";
    private static Gson json = new Gson(); //Required for parsing class instances to json format

    /**Records and appends information of a given call to a csv.
     * Should be called at the end of any action.
     * @param request
     */
    public static void save2Csv(HttpServletRequest request, String json_response){
        File file = new File(call_data_csv_path);
        FileWriter file_writer = null;

        //construct csv line
        String csv_line = "\n";                                                     //start on new line
        csv_line += request.getRequestURI() + ",";                                  //API_CALL_DATA
        csv_line += request.getRequestURI().substring("/worker/".length()) + ",";   //API_KEY
        csv_line += json_response;

        try {
            //append new line to file
             file_writer = new FileWriter(file, true);
             file_writer.write(csv_line);
        }
        catch (IOException e) { e.printStackTrace(); }
        finally {
            // close file writer
            try { file_writer.close(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }

    //TODO
    public static void saveCallToDB() throws UnsupportedOperationException{}

    /** Fetches json of worker from Datastore worker map with the appropriate worker_id.
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String data = "{}";                                         //Initialize to empty json
        String requestUrl = request.getRequestURI();                //Get body
        String id = requestUrl.substring("/worker/".length());      //Get key value

        //Find Worker in Datastore worker map
        Worker worker = Datastore.getInstance().getWorker(id);

        //If ID found, convert worker to json
        if(worker != null){ data = json.toJson(worker); }

        //return resulting JSON object.
        response.getOutputStream().println(data);
        save2Csv(request,data);                                     //record call to csv
        //saveCallToDB();
    }


}
