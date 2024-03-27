import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.net.URI;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.JTextArea;


public class Notepad extends JFrame implements ActionListener{
    JTextArea area;
    String Text;
    UndoManager undoManager;
    Notepad(){
        //Frame
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen frame
        setTitle("Untitled - Notepad");
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("notepad.jpeg"));
        setIconImage(img.getImage());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Menu's
        JMenuBar Menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        Menubar.setBackground(Color.WHITE);
        file.setFont(new Font("Arial", Font.PLAIN, 12));
        Menubar.add(file);
        setJMenuBar(Menubar);

        JMenu edit = new JMenu("Edit");
        Menubar.setBackground(Color.WHITE);
        edit.setFont(new Font("Arial", Font.PLAIN, 12));
        Menubar.add(edit);

        JMenu format = new JMenu("Format");
        Menubar.setBackground(Color.WHITE);
        format.setFont(new Font("Arial", Font.PLAIN, 12));
        Menubar.add(format);

        JMenu view = new JMenu("View");
        Menubar.setBackground(Color.WHITE);
        view.setFont(new Font("Arial", Font.PLAIN, 12));
        Menubar.add(view);

        JMenu Help = new JMenu("Help");
        Menubar.setBackground(Color.WHITE);
        Help.setFont(new Font("Arial", Font.PLAIN, 12));
        Menubar.add(Help);

        undoManager = new UndoManager();
        area = new JTextArea();
        area.getDocument().addUndoableEditListener(undoManager);

        JMenuItem neww = new JMenuItem("New");
        neww.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        neww.setActionCommand("neww");
        neww.addActionListener(this);

//        JMenuItem newwin = new JMenuItem("New Window");
//        newwin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));

        JMenuItem open = new JMenuItem("Open..");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.setActionCommand("open");
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.setActionCommand("save");
        save.addActionListener(this);
        file.add(save);

        JMenuItem saveas = new JMenuItem("Save as..");
        saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        saveas.setActionCommand("saveas");
        saveas.addActionListener(this);
        file.add(saveas);

//        JMenuItem pagesetup = new JMenuItem("Page setup..");

        JMenuItem print = new JMenuItem("Print..");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.setActionCommand("print");
        print.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));
        exit.setActionCommand("exit");
        exit.addActionListener(this);

        file.add(neww);
//        file.add(newwin);
        file.add(open);
        file.add(save);
        file.add(saveas);
//        file.add(pagesetup);
        file.add(exit);

        JMenuItem undoMenuItem = new JMenuItem("Undo");
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undoMenuItem.setActionCommand("undo");
        undoMenuItem.addActionListener(this);


        JMenuItem redoMenuItem = new JMenuItem("Redo");
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        redoMenuItem.setActionCommand("redo");
        redoMenuItem.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.setActionCommand("cut");
        cut.addActionListener(this);

        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.setActionCommand("copy");
        copy.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.setActionCommand("paste");
        paste.addActionListener(this);

        JMenuItem delete = new JMenuItem("Delete");
        delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
        delete.setActionCommand("delete");
        delete.addActionListener(this);

//        JMenuItem searchwithbing = new JMenuItem("Search wih Bing...");
//        searchwithbing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        JMenuItem find = new JMenuItem("Find");
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        find.setActionCommand("find");
        find.addActionListener(this);

//        JMenuItem findnxt = new JMenuItem("Find Next");
//        findnxt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
//
//        JMenuItem findpre = new JMenuItem("Find previous");
//        findpre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.SHIFT_MASK));

        JMenuItem Replace = new JMenuItem("Replace...");
        Replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        Replace.setActionCommand("replace");
        Replace.addActionListener(this);

//        JMenuItem gooto = new JMenuItem("Go to");
//        gooto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));

        JMenuItem selectall = new JMenuItem("Select All");
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectall.setActionCommand("selectall");
        selectall.addActionListener(this);

        JMenuItem timedate = new JMenuItem("Time/Date");
        timedate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        timedate.setActionCommand("timedate");
        timedate.addActionListener(this);


        edit.add(undoMenuItem);
        edit.add(redoMenuItem);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(delete);
//        edit.add(searchwithbing);
        edit.add(find);
//        edit.add(findnxt);
//        edit.add(findpre);
        edit.add(Replace);
//        edit.add(gooto);
        edit.add(selectall);
        edit.add(timedate);

        JMenuItem wordwrap = new JMenuItem("Word Wrap");
        JMenuItem font = new JMenuItem("Font...");

        format.add(wordwrap);
        format.add(font);

        JMenuItem zoom = new JMenuItem("Zoom");
        JMenuItem statusbar = new JMenuItem("Status Bar");

        view.add(zoom);
        view.add(statusbar);

        JMenuItem viewhelp = new JMenuItem("View Help");
        viewhelp.setActionCommand("viewhelp");
        viewhelp.addActionListener(this);

        JMenuItem sendfeedback = new JMenuItem("Send Feedback");
        sendfeedback.setActionCommand("sendfeedback");
        sendfeedback.addActionListener(this);

        JMenuItem aboutnotepad = new JMenuItem("About Notepad");
        aboutnotepad.setActionCommand("aboutnotepad");
        aboutnotepad.addActionListener(this);

        Help.add(viewhelp);
        Help.add(sendfeedback);
        Help.add(aboutnotepad);

        //Text Area

        area = new JTextArea();
        area.setFont(new Font("Consolas", Font.PLAIN, 18));
        area.setLineWrap(true);// Auto New line
        area.setWrapStyleWord(true);//Auto adjust a long word in next line

        //Scroll Panel
        JScrollPane panel = new JScrollPane(area);
        panel.setBorder(BorderFactory.createEmptyBorder());
        add(panel);

        setVisible(true); //Frame Visiblity
    }

    public class About extends JFrame{
        About(){

            setBounds(400, 100, 500,400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);

            ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("download.png"));
            Image img2 = img1.getImage().getScaledInstance(290, 50, Image.SCALE_DEFAULT);
            ImageIcon img3 = new ImageIcon(img2);
            JLabel label = new JLabel(img3);
            label.setBounds(40, 20, 400,80);
            add(label);

            ImageIcon img4 = new ImageIcon(ClassLoader.getSystemResource("notepad.png"));
            Image img5 = img4.getImage().getScaledInstance(70, 60, Image.SCALE_SMOOTH);
            ImageIcon img6 = new ImageIcon(img5);
            JLabel label2 = new JLabel(img6);
            label2.setBounds(30, 125, 70,70);
            add(label2);

            JLabel text = new JLabel("<html>Code With Johaib<br>Version 0.0.1 (Java built 743.20.101)<br>" +
                    "@CWJ Coorporation, All Right Reserved<br><br>The Window 10 pro operating system and its user"+
                    "interface<br>are protected by trademark and existed interlectual property <br> right" +
                    "in the united state and other countries/region.</html>");
            text.setBounds(110, 90, 500, 200);
            text.setFont(new Font("Monotype Corsivia", Font.PLAIN, 12));
            add(text);

            JButton button = new JButton("Okay");
            button.setBounds(370, 326, 70,20);
            add(button);

            setVisible(true);

        }
    }




    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("neww")) {
            area.setText("");
        } else if (ae.getActionCommand().equals("open")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(true); // Allow all file types
            int action = chooser.showOpenDialog(this);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Text and CSV Files", "txt", "csv");
            chooser.addChoosableFileFilter(restrict);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File file = chooser.getSelectedFile();
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            }catch (Exception e){
                e.getStackTrace();
            }

        }else if (ae.getActionCommand().equals("save")) {
            JFileChooser saveChooser = new JFileChooser();
            saveChooser.setApproveButtonText("Save");
            int action = saveChooser.showSaveDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File selectedFile = saveChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                area.write(writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getActionCommand().equals("saveas")) {
            JFileChooser saveChooser = new JFileChooser();
            saveChooser.setApproveButtonText("Save");
            int action = saveChooser.showSaveDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File selectedFile = saveChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                area.write(writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(ae.getActionCommand().equals("print")){
            try{
                area.print();
            }catch (Exception e){
                e.getStackTrace();
            }
        }else if(ae.getActionCommand().equals("exit")){
            System.exit(0);
        }else if(ae.getActionCommand().equals("copy")){
            Text = area.getSelectedText();
        }
        else if(ae.getActionCommand().equals("cut")){
            Text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }
        else if(ae.getActionCommand().equals("paste")){
            area.insert(Text, area.getCaretPosition());
        }
        else if(ae.getActionCommand().equals("selectall")){
            area.selectAll();
        }else if(ae.getActionCommand().equals("delete")){
            Text = area.getSelectedText();
            area.replaceRange("",area.getSelectionStart(),area.getSelectionEnd());
        }else if(ae.getActionCommand().equals("aboutnotepad")){
            new About();
        }else if (ae.getActionCommand().equals("find")) {
            // Create a dialog box for finding text
            String searchText = JOptionPane.showInputDialog(this, "Find what:");
            if (searchText != null && !searchText.isEmpty()) {
                findText(searchText);
            }
        }else if (ae.getActionCommand().equals("replace")) {
            // Create a dialog box for Replacing text
            String searchText = JOptionPane.showInputDialog(this, "Enter Text :");
            if (searchText != null && !searchText.isEmpty()) {
                if (findText(searchText)) {
                    String replaceText = JOptionPane.showInputDialog(this, "Replace With :");
                    if (replaceText != null) {
                        replaceText(searchText, replaceText);
                    }
                }
            }
        }else if(ae.getActionCommand().equals("timedate")){
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = dateFormat.format(currentDate);
            area.getCaretPosition();
            area.replaceRange(date,area.getCaretPosition(),area.getCaretPosition());
        }else if(ae.getActionCommand().equals("viewhelp")){
            String link = "https://go.microsoft.com/fwlink/?LinkId=834783";
            try{
                Desktop.getDesktop().browse(new URI(link));
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getActionCommand().equals("sendfeedback")){
            JOptionPane.showInputDialog(this, "Enter your feedback message.");
        }else if (ae.getActionCommand().equals("undo")) {
            try {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            } catch (CannotUndoException ex) {
                ex.printStackTrace();
            }
        } else if (ae.getActionCommand().equals("redo")) {
            try {
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
            } catch (CannotRedoException ex) {
                ex.printStackTrace();
            }
        }
    }

    public boolean findText(String searchText) {
        String text = area.getText().toLowerCase();
        searchText = searchText.toLowerCase();
        int index = text.indexOf(searchText);
        if (index != -1) {
            // Highlight the found text
            area.setSelectionStart(index);
            area.setSelectionEnd(index + searchText.length());
            return true; // Text was found
        } else {
            JOptionPane.showMessageDialog(this, "Text not found.", "Find", JOptionPane.INFORMATION_MESSAGE);
            return false; // Text was not found
        }
    }
    public void replaceText(String searchText, String replacetext){
        String text = area.getText();
        text = text.replaceAll("(?i)" + searchText, replacetext);
        area.setText(text);
    }

    public static void main(String[] args) {
        new Notepad();
    }
}

//Ending