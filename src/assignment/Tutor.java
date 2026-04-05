package assignment;

/**
 *
 * @author ASUS
 */
public class Tutor extends Users{
    public Tutor(String id, String password, String name, String email, String phone) {
        super(id, password, name, email, phone);
    }
    
    @Override
    public String toString() {
        return getId() + "::" + getPassword() + "::" + getName() + "::" + 
               getEmail() + "::" + getPhone();
    }
}
