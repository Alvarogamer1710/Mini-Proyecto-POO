package Modelo;

public class Usuario{

    private String user,passw;

    Usuario(String user,String passw){
        this.user=user;
        this.passw=passw;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }


    @Override
    public String toString() {
        return "Usuario [user=" + user + ", passw=" + passw + "]";
    }

     public boolean checkPassw(String input){

        return this.passw.equals(input);

    }
    
}
