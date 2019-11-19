package object;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JFrame play;
    private Game game=null;

    public static void main(String [] args)
    {
        EventQueue.invokeLater(()->{
            try {
                Main frame=new Main();
                frame.setResizable(false);
                frame.setVisible(true);
            }catch (Exception o)
            {
                o.printStackTrace();
            }
        });
    }


    private Main()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setBounds((screenSize.width-600)/3,(screenSize.height-600)/3,690,680);
        getContentPane().setLayout(new BorderLayout(0,0));
        getContentPane().add(new Game());
    }
}
