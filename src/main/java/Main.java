import javax.swing.SwingUtilities;
import ui.CustomerUI;

public class Main{

    public static void main(String[] args){

        SwingUtilities.invokeLater(() ->{
            new CustomerUI();
        });
    }
}