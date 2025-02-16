package Client.UI;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Chat extends JPanel{
    JTextArea textArea;
    JScrollPane scrollPane;
    private JTextField message;
    HashMap<String,JTextArea> HashtextArea;


    public Chat(){
        super();
        setLayout(new BorderLayout());
        HashtextArea = new HashMap<>();

        // Create a JTextArea for displaying text
        textArea = createTextArea("General");

        // Add the text area to a JScrollPane with a vertical scrollbar
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Create a vertical scrollbar
        //JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();

        // Add a button to simulate content updates
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());

        message = new JTextField();
        message.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
                    sendMessage();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
            @Override
            public void keyTyped(KeyEvent e) {}
            
        });
        JButton addButton = new JButton("Send");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Add components to the content panel
        messagePanel.add(message,BorderLayout.CENTER);
        messagePanel.add(addButton,BorderLayout.EAST);
        
        add(scrollPane, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);
    
    }

    void sendMessage(){
        textArea.append(message.getText() + "\n");
        message.setText("");
    }

    void addMessage(String message){
        textArea.append(message + "\n");
    }
    
    JTextArea createTextArea(String name){
        JTextArea text = new JTextArea(10, 40);
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setName(name);
        HashtextArea.put(name,text);
        return text;
    }

    void setFocus(String name){
        remove(scrollPane);

        textArea = HashtextArea.get(name);
        if(textArea == null) {
            textArea = createTextArea(name);
        }
        textArea.setVisible(true);
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
