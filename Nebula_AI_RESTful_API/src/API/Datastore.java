package API;

import java.util.HashMap;
import java.util.Map;

//singleton accessor point for data.
public class Datastore {

    private Map<String, Worker> workerMap = new HashMap<>();

    private static Datastore _instance = new Datastore();
    public static final Datastore getInstance() { return _instance; }

    private Datastore(){
        initWorkerTestWorkers(); //Initializes mock data
    }

    /**
     * hardcoded mock data to be used on the worker map on initialization, for testing and debugging.
     */
    public static void initWorkerTestWorkers() {
        //init workers
        Worker w0 = Worker.initDetailedWorker(
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
                0
        );

        Worker w1 = Worker.initDetailedWorker(
                "1",
                "p1_01",
                48,
                996000000,
                1473000000,
                "",
                0,
                3,
                1743000000,
                198300000,
                0
        );

        Worker w2 = Worker.initDetailedWorker(
                "2",
                "p2_00",
                32,
                1008000000,
                1485000000,
                "GeForce RTX 2080 Ti",
                8000000,
                32,
                37320000,
                157300000,
                0
        );

        Worker w3 = Worker.initDetailedWorker(
                "3",
                "filecoin",
                48,
                244000000,
                352000000,
                "GeForce GTX 1080 Ti",
                6000000,
                48,
                32550000,
                152600000,
                0
        );

        //populate map
        Datastore.getInstance().workerMap.put( w0.getId(), w0 );
        Datastore.getInstance().workerMap.put( w1.getId(), w1 );
        Datastore.getInstance().workerMap.put( w2.getId(), w2 );
        Datastore.getInstance().workerMap.put( w3.getId(), w3 );
    }

    /**Returns worker instance according tot he given key.
     *
     * @param key String referencing the worker id in the worker map
     * @return
     */
    public Worker getWorker(String key){ return workerMap.get(key); }

}
