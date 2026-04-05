package assignment;

public class Receptionist extends Users{
    public Receptionist(String id, String password, String name, String email, String phone) {
        super(id, password, name, email, phone);
    }
    
    @Override
    public String toString() {
        return getId() + "::" + getPassword() + "::" + getName() + "::" + 
               getEmail() + "::" + getPhone();
    }
}
