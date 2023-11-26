import javax.swing.*;

public class Main {
    public static void main(String[] args){
        try {
            TextDB.init();
            App.loadData();
            SwingUtilities.invokeLater(() -> {
                (new LoginForm()).open();
            });
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
