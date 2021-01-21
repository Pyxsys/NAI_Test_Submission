package API;

//data class
public class Worker {

    private String worker_name;
    private String id;

    private int cpu_available;
    private int cpu_inUse;
    private int ram_available;
    private int ram_inUse;
    private int vmem_available;
    private int vmem_inUse;

    private String gpu_name;
    private int gpu_available;
    private int gpu_inUse;

    //Constructors

    /** GPU-less constructor
     *
     * @param name
     * @param cpu_available
     * @param ram_available
     * @param vmem_available
     */
    public Worker(
            String id, String name,
            int cpu_available, int ram_available, int vmem_available
    ){
        this.id = id;
        this.worker_name = name;

        this.cpu_available = cpu_available;
        this.ram_available = ram_available;
        this.vmem_available = vmem_available;
        this.cpu_inUse = 0;
        this.ram_inUse = 0;
        this.vmem_inUse = 0;

        this.gpu_name = "n/a";
        this.gpu_available = 0;
        this.gpu_inUse = 0;
    }

    /** Complete constructor
     *
     * @param name
     * @param cpu_available
     * @param ram_available
     * @param vmem_available
     * @param gpu_name
     * @param gpu_available
     */
    public Worker(
            String id, String name,
            int cpu_available, int ram_available, int vmem_available,
            String gpu_name, int gpu_available
    ){
        this.id = id;
        this.worker_name = name;

        this.cpu_available = cpu_available;
        this.ram_available = ram_available;
        this.vmem_available = vmem_available;
        this.cpu_inUse = 0;
        this.ram_inUse = 0;
        this.vmem_inUse = 0;

        this.gpu_name = gpu_name;
        this.gpu_available = gpu_available;
        this.gpu_inUse = 0;
    }

    public static Worker initDetailedWorker(
            String id, String name,
            int cpu_available, int ram_available, int vmem_available,
            String gpu_name, int gpu_available,
            int cpu_used, int ram_used, int vmem_used, int gpu_used
    ){
        Worker new_worker_instance;

        if(gpu_name.isBlank() || gpu_name.isEmpty()){
            new_worker_instance = new Worker( id, name, cpu_available, ram_available, vmem_available);
        } else {
            new_worker_instance = new Worker(id, name, cpu_available, ram_available, vmem_available, gpu_name, gpu_available);
            new_worker_instance.setGpuInUse(gpu_used);
        }

        new_worker_instance.setCpuInUse(cpu_used);
        new_worker_instance.setRamInUse(ram_used);
        new_worker_instance.setVmemInUse(vmem_used);

        return new_worker_instance;
    }

    //Getters + Setters

    public String getId()           { return this.id; }
    public String getWorkerName()  { return this.worker_name; }
    public void setWorkerName(String new_name)  { this.worker_name = new_name; }

    public int getCpuAvailable()                    { return cpu_available; }
    private void setCpuAvailable(int cpu_available) { this.cpu_available = cpu_available; }
    public int getCpuInUse()                        { return cpu_inUse; }
    public void setCpuInUse(int cpu_inUse)          { this.cpu_inUse = cpu_inUse; }

    public int getRamAvailable()                    { return ram_available; }
    private void setRamAvailable(int ram_available) { this.ram_available = ram_available; }
    public int getRamInUse()                        { return ram_inUse; }
    public void setRamInUse(int ram_inUse)          { this.ram_inUse = ram_inUse; }

    public int getVmemAvailable()                       { return vmem_available; }
    private void setVmemAvailable(int vmem_available)    { this.vmem_available = vmem_available; }
    public int getVmemInUse()                           { return vmem_inUse; }
    public void setVmemInUse(int vmem_inUse)            { this.vmem_inUse = vmem_inUse; }

    public String getGpuName()                  { return gpu_name; }
    public int getGpuAvailable()                { return gpu_available; }
    public int getGpuInUse()                   { return gpu_inUse; }
    public void setGpuInUse(int gpu_inUse)     { this.gpu_inUse = gpu_inUse; }

    public void setGpu(String gpu_name,int gpu_available) {
        this.gpu_name = gpu_name;
        this.gpu_available = gpu_available;
    }

    //Functions

}
