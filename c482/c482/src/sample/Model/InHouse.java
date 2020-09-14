package sample.Model;


public class InHouse extends Part {
    private int machineId;

    public InHouse( String name, double price, int stock, int min, int max, int machineId){
        super( name, price, stock, min, max);
        this.machineId = machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMachineId(){
        return machineId;
    }
}
