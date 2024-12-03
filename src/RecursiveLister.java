import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class RecursiveLister {

    private JFrame frame;
    private JTextArea textArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RecursiveLister::new);
    }

    public RecursiveLister() {
        frame = new JFrame("Recursive Lister");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        JLabel titleLabel = new JLabel("Recursive Directory Lister", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Text Area with Scroll Pane
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Start Button
        JButton startButton = new JButton("Start");
        startButton.addActionListener(this::startButtonAction);
        frame.add(startButton, BorderLayout.WEST);

        // Quit Button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        frame.add(quitButton, BorderLayout.EAST);

        frame.setVisible(true);
    }

    private void startButtonAction(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(frame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File directory = fileChooser.getSelectedFile();
            textArea.setText("");  // Clear the text area
            listFiles(directory);
        }
    }

    private void listFiles(File directory) {
        textArea.append("Listing files in directory: " + directory.getAbsolutePath() + "\n\n");
        listFilesRecursive(directory);
    }

    private void listFilesRecursive(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    textArea.append("Directory: " + file.getAbsolutePath() + "\n");
                    listFilesRecursive(file);
                } else {
                    textArea.append("File: " + file.getAbsolutePath() + "\n");
                }
            }
        }
    }
}
