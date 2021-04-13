package sample.model;

public class Client {
    private int ID;
    private String name;
    private String address;
    private String email;
    public Client(){

    }
    public Client(int ID, String name,String address,String email){
        this.ID=ID;
        this.name=name;
        this.address=address;
        this.email=email;
    }
    public Client(String name,String address,String email){
        this.name=name;
        this.address=address;
        this.email=email;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
