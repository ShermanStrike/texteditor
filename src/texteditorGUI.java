import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class texteditorGUI {
    private JButton öppnaFilButton;


    public texteditorGUI() {
        /* Knapp som öppnar ett fönster där en existerande fil kan öppnas */
        öppnaFilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedReader inFil;
                String namn;
                String filnamn;
                namn = JOptionPane.showInputDialog("Filnamn?");
                FileReader fr = null;
                try {
                    fr = new FileReader(namn);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                inFil = new BufferedReader(fr);

                String content = null;
                try {
                    content = inFil.readLine();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                while (content != null) {
                    Text.setText(content);
                    try {
                        content = inFil.readLine();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                try {
                    inFil.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        /* Ny fil knapp, raderar befintlig text */
        nyFilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Text.setText("");
            }
        });
        /* Knapp som skapar en kopia av filen efer givet namn */
        sparaFilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrintWriter utFil;
                BufferedReader inFil;
                String namn;
                String filnamn;
                namn = JOptionPane.showInputDialog("Filnamn?");

                FileWriter fw = null;
                try {
                    fw = new FileWriter("KopiaAv" + namn);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                BufferedWriter bw = new BufferedWriter(fw);
                utFil = new PrintWriter(bw);
                // Skriv innehållet i textfältet till filen
                String content = Text.getText();
                utFil.println(content);
                utFil.flush();
                utFil.close();

            }
        });
    }

    /* GUI kod */
    public static void main(String[] args) {
        JFrame frame = new JFrame("texteditorGUI");
        frame.setContentPane(new texteditorGUI().texteditorpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(600,480));
        frame.setTitle("Unsaved document");
        frame.setVisible(true);
    }
    /* GUI kod för knapparna och textfält */
    private JButton sparaFilButton;
    private JButton nyFilButton;
    private JTextArea Text;
    private JPanel texteditorpanel;
}
