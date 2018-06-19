package maple;

import encode.LoginCrypto;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean(name = "Login")
@RequestScoped
public class Login implements Serializable {

    private String username;
    private String password;
    private UIComponent component;
    public boolean condition;

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    public boolean getCondition() {
        return condition;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public UIComponent getComponent() {
        return component;
    }

    //validate login
    public String validateUsernamePassword() {

        boolean valid = validate(username, LoginCrypto.hexSha1(password));

        if (valid) {
            //領取動作

            Connection con = null;
            PreparedStatement ps = null;

            try {
                con = DBOperation.get_connection();
                ps = con.prepareStatement("SELECT Acash FROM accounts WHERE name =?");
                ps.setString(1, username);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    
                    int GASH_NEW = 0;
                    GASH_NEW = rs.getInt("Acash");
                    GASH_NEW += 500;
                    
                    Connection con2=DBOperation.get_connection();
                    PreparedStatement ps2=con2.prepareStatement(" UPDATE `accounts` SET `ACash` = ? WHERE `name` = ?");
                    ps2.setInt(1, GASH_NEW);
                    ps2.setString(2, username);
                    ps2.executeUpdate();

                    FacesContext.getCurrentInstance().addMessage(
                            component.getClientId(),
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "驗證成功", "已增加500點數"));
                    condition = true;
                    return "maple.xhtml";
                } else {
                    FacesContext.getCurrentInstance().addMessage(
                            component.getClientId(),
                            new FacesMessage(FacesMessage.SEVERITY_WARN, "驗證錯誤", "資料庫操作錯誤！"));
                    condition = false;
                    return "maple.xhtml";
                }
            } catch (SQLException ex) {
                System.out.println("Login error -->" + ex.getMessage());
                FacesContext.getCurrentInstance().addMessage(
                        component.getClientId(),
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "驗證錯誤", "資料庫操作錯誤！"));
                condition = false;
                return "maple.xhtml";
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(
                    component.getClientId(),
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "驗證錯誤", "帳號或密碼錯誤！"));
            condition = false;
            return "maple.xhtml";
        }
    }

    //logout event, invalidate session
//    public String logout() {
//		HttpSession session = SessionUtils.getSession();
//		session.invalidate();
//        return "login";
//    }
    public static boolean validate(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBOperation.get_connection();
            ps = con.prepareStatement("SELECT name, password from accounts where name = ? and password = ?");

            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ///回傳登入成功
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
