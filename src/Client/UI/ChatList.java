package Client.UI;

import java.util.Vector;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ChatList extends JPanel {

    Chat chat;
    //private JPanel mainList;
    private DefaultListModel<String> list;
    public JList<String> listName;

    public ChatList(Chat chat, Vector<String> vectorList) {
        this.chat = chat;
        list = new DefaultListModel<String>();
        listName = new JList<>(list);
        setLayout(new BorderLayout());

        Action joinChat = new AbstractAction("Join Chat") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chat = JOptionPane.showInputDialog("Rejoindre un conversation");
                if (chat!=null&& !chat.isEmpty()){
                    joinChat(chat);
                }
            }
        };
        JButton join = new JButton(joinChat);
        join.setText("Join Chat");

        JScrollPane scrollList = new JScrollPane(listName, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollList);
        add(join, BorderLayout.SOUTH);

        listName.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Avoid reacting to the programmatic selection (to prevent recursion)
                if (!e.getValueIsAdjusting()) {
                    String selectedFrame = listName.getSelectedValue();
                    chat.setFocus(selectedFrame);
                }
            }
        });
        for(String s : vectorList) joinChat(s);
        
    }

    

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }


    public void addMessage(String chanel, String message){
        chat.addMessage(chanel, message);
    }

    void joinChat(String name){
        if(chat.joinChat(name)) list.addElement(name);
        repaint();
    }
}


