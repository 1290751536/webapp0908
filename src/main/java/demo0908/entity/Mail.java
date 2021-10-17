package demo0908.entity;

public class Mail {
    private String mail;
    private String password;

    @Override
    public String toString() {
        return "Mail{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
