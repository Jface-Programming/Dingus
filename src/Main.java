import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Dingus");
        final JTextArea textArea = new JTextArea();
        JMenuBar menuBar = new JMenuBar();
        JMenuItem SaveItem = new JMenuItem("save");
        JMenuItem OpenItem = new JMenuItem("open");
        JMenuItem NewItem = new JMenuItem("new");
        JMenuItem AboutItem = new JMenuItem("about");
        JMenu menuFile = new JMenu("file");
        AboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JWindow aboutFrame = new JWindow();
                aboutFrame.setSize(400, 200);
                aboutFrame.setLayout(new FlowLayout());
                JLabel name = new JLabel("Dingus");
                JLabel version = new JLabel("version 1.1.1");
                JLabel developer = new JLabel("created by Jface_Programming");
                JButton exitButton = new JButton("X");
                exitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        aboutFrame.setVisible(false);
                    }
                });
                name.setFont(new Font("", Font.PLAIN, 80));
                aboutFrame.add(name);
                aboutFrame.add(version);
                aboutFrame.add(exitButton);
                aboutFrame.add(developer);
                aboutFrame.setLocationRelativeTo(null);
                aboutFrame.setVisible(true);
            }
        });
        NewItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });
        OpenItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("text", "txt");
                fileChooser.setFileFilter(filter);
                int response = fileChooser.showOpenDialog(null);
                if (response == 0) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    Scanner fileIn = null;

                    try {
                        fileIn = new Scanner(file);
                        if (file.isFile()) {
                            while (fileIn.hasNextLine()) {
                                String line = fileIn.nextLine() + "\n";
                                textArea.append(line);
                            }
                        }
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } finally {
                        fileIn.close();
                    }
                }

            }
        });
        SaveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("text", "txt");
                fileChooser.setFileFilter(filter);
                int response = fileChooser.showSaveDialog(null);
                if (response == 0) {
                    PrintWriter fileOut = null;
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                    try {
                        fileOut = new PrintWriter(file);
                        fileOut.println(textArea.getText());
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } finally {
                        fileOut.close();
                    }
                }

            }
        });
        textArea.setLineWrap(true);
        window.setSize(650, 500);
        window.setDefaultCloseOperation(3);
        window.setLocationRelativeTo(null);
        JScrollPane scroll = new JScrollPane(textArea);
        menuFile.add(AboutItem);
        menuFile.add(SaveItem);
        menuFile.add(OpenItem);
        menuFile.add(NewItem);
        menuBar.add(menuFile);
        window.setJMenuBar(menuBar);
        window.add(scroll);
        window.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Dingus.png")));
        window.setVisible(true);
    }
}
