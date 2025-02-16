package Client;

import Client.UI.*;


import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

public class Main extends JFrame{
    

    Main(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JSplitPane split = new JSplitPane();
		split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        
        Chat chat = new Chat();
	    split.setRightComponent(chat);
        split.setLeftComponent(new ChatList(chat));

        add(split);
        split.setVisible(true);
        setSize(600, 400);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new Main("name").setVisible(true);
            }
        });
    }
}
