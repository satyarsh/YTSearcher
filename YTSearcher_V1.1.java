package YTSearcher;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.*;

class text extends JFrame implements ActionListener {

    static JTextField textfield;
    static JFrame frame;
    static JButton button;
    static JButton exitButton;
    static JLabel label;
    static JLabel YTLogo;

    private static boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
    private static String username = System.getProperty("user.name");
    public static boolean isBrave = false;
    public static boolean isFirefox = true;
 
    text(){
    }

    public static void main(String[] args)
    {
        
        frame = new JFrame("YTSearch");
 
        label = new JLabel("Enter the Search Result You Seek!");
        YTLogo = new JLabel();
 
        button = new JButton("Search");
        //button.setBounds(255,0,0,0);
        
        exitButton = new JButton("Exit");
        
        Border br = BorderFactory.createLineBorder(Color.red,1);
        
        /* YT Logo */
        ImageIcon img = new ImageIcon("YTLogo.png");
        YTLogo.setIcon(img);
 
        text te = new text();
 
        // addActionListener to button
        button.addActionListener(te);
        exitButton.addActionListener(te);
 
        // create a object of JTextField with a given initial text
        textfield = new JTextField();
        textfield.setPreferredSize(new Dimension(220,23));
        textfield.setFont(new Font(null,Font.PLAIN,15));
        textfield.setBackground(new Color(99,99,99));
        textfield.setForeground(Color.white);
        textfield.setSelectedTextColor(Color.red);
        textfield.setCaretColor(Color.white);
 
        // create a panel to add buttons and textfield
        JPanel panel = new JPanel();
 
        panel.setBackground(Color.darkGray);
        frame.setForeground(Color.black);
        label.setForeground(Color.white);
        panel.setBorder(br);

        // add buttons and textfield to panel
        panel.add(textfield);
        panel.add(button);
        panel.add(exitButton);
        panel.add(label);
        panel.add(YTLogo);
 
        // add panel to frame
        frame.add(panel);
 
        frame.setSize(270, 240);
        frame.setResizable(true);
        
        /* Spawns the app in the center of the screen! */
        frame.setLocationRelativeTo(null);
       
        /* Old and deprecated! */
        //frame.show();
        
        frame.setVisible(true);
        
    }
    
    // if the button is pressed
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if (s.equals("Search")) {

            if(isWindows) {

                String toYoutube = textfield.getText();
                String replacedString = toYoutube.replaceAll("\\s+", "+");

                if(isBrave){

                File location = new File("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application");

                String command = ".\\brave.exe https://www.youtube.com/results?search_query=%s".formatted(replacedString);
                
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.directory(location);
                processBuilder.command("cmd.exe", "/c", command);

                try {    
                Process process = processBuilder.start();

                int exitCode = process.waitFor();
                System.out.println("\nExited with error code : " + exitCode);
                
                } catch (IOException IOEx) {
                    System.out.println(IOEx);
                } catch (InterruptedException InterruptedEx) {
                    System.out.println(InterruptedEx);
                }

                }else if(isFirefox){

                //File location = new File("C:\\Users\\%s\\AppData\\Local\\Mozilla Firefox".formatted(username));

                File location = new File("C:\\Program Files (x86)\\Mozilla Firefox");

                String command = ".\\firefox.exe https://www.youtube.com/results?search_query=%s".formatted(replacedString);

                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.directory(location);
                processBuilder.command("cmd.exe", "/c", command);

                try {    
                Process process = processBuilder.start();

                int exitCode = process.waitFor();
                System.out.println("\nExited with error code : " + exitCode);
                
                } catch (IOException IOEx) {
                    System.out.println(IOEx);
                } catch (InterruptedException InterruptedEx) {
                    System.out.println(InterruptedEx);
                }

            }

        }else {
                   	
            String toYoutube = textfield.getText();

            //Replace every whitespace with a '+' so Youtube can recognize it
            String replacedString = toYoutube.replaceAll("\\s+", "+");

            ProcessBuilder processBuilder = new ProcessBuilder();

            String commandString = "firefox https://www.youtube.com/results?search_query=%s".formatted(replacedString);
            processBuilder.command("bash", "-c", commandString);
    
            try {
                
                Process process = processBuilder.start();

                int exitCode = process.waitFor();
                System.out.println("\nExited with error code : " + exitCode);
                
            } catch (IOException IOEx) {
                System.out.println(IOEx);
            } catch (InterruptedException InterruptedEx) {
                System.out.println(InterruptedEx);
            }	

            // set the text of field to blank
            textfield.setText("  ");
            label.setText("What else do you want to search?");
                
            } 

        }else if (s.equals("Exit")) {
                System.exit(0);
        }
    }
}
