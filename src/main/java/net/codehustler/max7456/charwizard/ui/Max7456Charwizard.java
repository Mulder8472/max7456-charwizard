package net.codehustler.max7456.charwizard.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;

import net.codehustler.max7456.charwizard.Max7456Charset;
import net.codehustler.max7456.charwizard.Pixel;
import net.codehustler.max7456.charwizard.ui.util.UserSettings;
public class Max7456Charwizard extends JFrame
{
  public static final String VERSION = "0.1";
  public static final String TITLE = "MAX7456 MCM Wizard";
  private JFileChooser jfOpen = new JFileChooser(new File("."));
  private JFileChooser jfSave = new JFileChooser(new File("."));

  private UserSettings userSettings = new UserSettings();
private Max7456Charset charset;
private ButtonGroup buttonGroup1;
private JDialog jDialog1;
private JScrollPane jScrollPane1;
private JTextArea jTextArea1;
private JButton jButton5;
private MCMPanel mCMPanel1;
private MCMCharacterEditor mCMCharacterEditor1;
private JButton jButton1;
private JButton jButton2;
private JButton jButton3;
private JButton jButton4;
private JMenu fileMenu;
private JMenuItem openMenuItem;
private JMenuItem saveMenuItem;
private JMenuItem saveAsMenuItem;
private JMenuItem exitMenuItem;
private JMenu editMenu;
private JMenuItem copyMenuItem;
private JMenuItem pasteMenuItem;
private JSeparator jSeparator1;
private JMenuItem jMenuItem1;
private JMenuItem jMenuItem2;
private JMenu helpMenu;
private JMenuItem contentsMenuItem;
private JMenuItem aboutMenuItem;
private JCheckBox jCheckBox1;
private JMenuBar menuBar1;
private JMenu fileMenu1;
private JMenuItem jMenuItem9;
private JMenuItem openMenuItem1;
private JMenuItem saveMenuItem1;
private JMenuItem saveAsMenuItem1;
private JSeparator jSeparator8;
private JMenu jMenu2;
private JSeparator jSeparator7;
private JMenuItem exitMenuItem1;
private JMenu editMenu1;
private JMenuItem jMenuItem10;
private JMenuItem copyMenuItem1;
private JMenuItem pasteMenuItem1;
private JSeparator jSeparator2;
private JMenuItem jMenuItem3;
private JMenuItem jMenuItem4;
private JMenu jMenu1;
private JMenuItem jMenuItem5;
private JMenuItem jMenuItem6;
private JMenuItem jMenuItem7;
private JSeparator jSeparator6;
private JMenuItem jMenuItem16;
private JSeparator jSeparator5;
private JMenuItem jMenuItem11;
private JMenuItem jMenuItem12;
private JMenuItem jMenuItem13;
private JSeparator jSeparator4;
private JMenuItem jMenuItem15;
private JMenuItem jMenuItem14;
private JSeparator jSeparator3;
private JMenuItem jMenuItem8;
private JMenu helpMenu1;
private JMenuItem aboutMenuItem1;





  public Max7456Charwizard()
  {
    try
    {
      this.userSettings.load();
      this.charset = new Max7456Charset();
      this.charset.createEmpty();

      this.jfSave.setDialogTitle("Save MCM File");
      this.jfSave.setMultiSelectionEnabled(false);
      this.jfSave.setFileSelectionMode(0);
      this.jfSave.setDialogType(1);

      this.jfOpen.setDialogTitle("Open MCM File");
      this.jfOpen.setMultiSelectionEnabled(false);
      this.jfOpen.setFileSelectionMode(0);
      this.jfOpen.setDialogType(0);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    initComponents();

    initRecentFilesMenu();
  }

  private void initRecentFilesMenu()
  {
    this.jMenu2.removeAll();


    for (String f : this.userSettings.getRecentFiles()) {
      JMenuItem mi = new JMenuItem(f);
      mi.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          Max7456Charwizard.this.openRecentFileHandler(evt);

        }
      });
      this.jMenu2.add(mi);
    }
  }

  public void openRecentFileHandler(ActionEvent evt) {
    openFile(new File(evt.getActionCommand()));
  }

  public String getTitle() {
    String title = "MAX7456 MCM Wizard v0.1 - " + this.charset.getFilename();

    return title;
  }



  private void initComponents()
  {
    this.buttonGroup1 = new ButtonGroup();
    this.jDialog1 = new JDialog();
    this.jScrollPane1 = new JScrollPane();
    this.jTextArea1 = new JTextArea();
    this.jButton5 = new JButton();
    this.mCMPanel1 = new MCMPanel();
    this.mCMPanel1.setCharset(this.charset);
    this.mCMCharacterEditor1 = new MCMCharacterEditor();
    this.mCMPanel1.addCharacterSelectionListener(this.mCMCharacterEditor1);
    this.jButton1 = new JButton();
    this.jButton2 = new JButton();
    this.jButton3 = new JButton();
    this.jButton4 = new JButton();
    //this.menuBar = new JMenuBar();
    //setMenuBar(new JMenuBar());
    this.fileMenu = new JMenu();
    this.openMenuItem = new JMenuItem();
    this.saveMenuItem = new JMenuItem();
    this.saveAsMenuItem = new JMenuItem();
    this.exitMenuItem = new JMenuItem();
    this.editMenu = new JMenu();
    this.copyMenuItem = new JMenuItem();
    this.pasteMenuItem = new JMenuItem();
    this.jSeparator1 = new JSeparator();
    this.jMenuItem1 = new JMenuItem();
    this.jMenuItem2 = new JMenuItem();
    this.helpMenu = new JMenu();
    this.contentsMenuItem = new JMenuItem();
    this.aboutMenuItem = new JMenuItem();
    this.jCheckBox1 = new JCheckBox();
    this.menuBar1 = new JMenuBar();
    this.fileMenu1 = new JMenu();
    this.jMenuItem9 = new JMenuItem();
    this.openMenuItem1 = new JMenuItem();
    this.saveMenuItem1 = new JMenuItem();
    this.saveAsMenuItem1 = new JMenuItem();
    this.jSeparator8 = new JSeparator();
    this.jMenu2 = new JMenu();
    this.jSeparator7 = new JSeparator();
    this.exitMenuItem1 = new JMenuItem();
    this.editMenu1 = new JMenu();
    this.jMenuItem10 = new JMenuItem();
    this.copyMenuItem1 = new JMenuItem();
    this.pasteMenuItem1 = new JMenuItem();
    this.jSeparator2 = new JSeparator();
    this.jMenuItem3 = new JMenuItem();
    this.jMenuItem4 = new JMenuItem();
    this.jMenu1 = new JMenu();
    this.jMenuItem5 = new JMenuItem();
    this.jMenuItem6 = new JMenuItem();
    this.jMenuItem7 = new JMenuItem();
    this.jSeparator6 = new JSeparator();
    this.jMenuItem16 = new JMenuItem();
    this.jSeparator5 = new JSeparator();
    this.jMenuItem11 = new JMenuItem();
    this.jMenuItem12 = new JMenuItem();
    this.jMenuItem13 = new JMenuItem();
    this.jSeparator4 = new JSeparator();
    this.jMenuItem15 = new JMenuItem();
    this.jMenuItem14 = new JMenuItem();
    this.jSeparator3 = new JSeparator();
    this.jMenuItem8 = new JMenuItem();
    this.helpMenu1 = new JMenu();
    this.aboutMenuItem1 = new JMenuItem();

    this.jDialog1.setBounds(new Rectangle(0, 0, 400, 300));
    this.jDialog1.setResizable(false);

    this.jTextArea1.setColumns(20);
    this.jTextArea1.setEditable(false);
    this.jTextArea1.setLineWrap(true);
    this.jTextArea1.setRows(5);
    this.jTextArea1
      .setText("MCM Character File Editor Version 0.1\n======================================\n\nEdit Character table files (*.MCM) for the MAX7456 on-screen-display IC.\n\nThis software is released \"as is\", use at your own risk.  \n\n(C) Daniel Mueller (daniel.mueller@gmx.com), 2010\n");
    this.jTextArea1.setDisabledTextColor(new Color(0, 0, 0));
    this.jTextArea1.setEnabled(false);
    this.jScrollPane1.setViewportView(this.jTextArea1);

    this.jButton5.setText("Close");
    this.jButton5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jButton5ActionPerformed(evt);

      }
    });
    GroupLayout jDialog1Layout = new GroupLayout(
      this.jDialog1.getContentPane());
    this.jDialog1.getContentPane().setLayout(jDialog1Layout);
    jDialog1Layout
      .setHorizontalGroup(jDialog1Layout
      .createParallelGroup(
      GroupLayout.Alignment.LEADING)
      .addGroup(
      GroupLayout.Alignment.TRAILING, 
      jDialog1Layout
      .createSequentialGroup()
      .addContainerGap()
      .addGroup(
      jDialog1Layout
      .createParallelGroup(
      GroupLayout.Alignment.TRAILING)
      .addComponent(
      this.jScrollPane1, 
      GroupLayout.Alignment.LEADING, 
      -1, 
      380, 
      32767)
      .addComponent(this.jButton5))
      .addContainerGap()));
    jDialog1Layout
      .setVerticalGroup(jDialog1Layout
      .createParallelGroup(
      GroupLayout.Alignment.LEADING)
      .addGroup(
      GroupLayout.Alignment.TRAILING, 
      jDialog1Layout
      .createSequentialGroup()
      .addContainerGap()
      .addComponent(
      this.jScrollPane1, 
      -1, 
      249, 32767)
      .addPreferredGap(
      LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(this.jButton5)
      .addContainerGap()));

    setDefaultCloseOperation(3);
    setTitle("MAX7456 Charwizard");
    addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent evt) {
        Max7456Charwizard.this.formKeyReleased(evt);

      }
    });
    GroupLayout mCMPanel1Layout = new GroupLayout(
      this.mCMPanel1);
    this.mCMPanel1.setLayout(mCMPanel1Layout);
    mCMPanel1Layout.setHorizontalGroup(mCMPanel1Layout.createParallelGroup(
      GroupLayout.Alignment.LEADING).addGap(0, 243, 
      32767));
    mCMPanel1Layout.setVerticalGroup(mCMPanel1Layout.createParallelGroup(
      GroupLayout.Alignment.LEADING).addGap(0, 339, 
      32767));

    GroupLayout mCMCharacterEditor1Layout = new GroupLayout(
      this.mCMCharacterEditor1);
    this.mCMCharacterEditor1.setLayout(mCMCharacterEditor1Layout);
    mCMCharacterEditor1Layout.setHorizontalGroup(mCMCharacterEditor1Layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGap(0, 213, 32767));
    mCMCharacterEditor1Layout.setVerticalGroup(mCMCharacterEditor1Layout
      .createParallelGroup(GroupLayout.Alignment.LEADING)
      .addGap(0, 289, 32767));

    this.jButton1.setText("Fill white");
    this.jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jButton1ActionPerformed(evt);

      }
    });
    this.jButton2.setText("Fill black");
    this.jButton2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jButton2ActionPerformed(evt);

      }
    });
    this.jButton3.setText("Fill transparent");
    this.jButton3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jButton3ActionPerformed(evt);

      }
    });
    this.jButton4.setText("Save Character");
    this.jButton4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jButton4ActionPerformed(evt);

      }
    });
    this.fileMenu.setText("File");

    this.openMenuItem.setText("Open");
    this.fileMenu.add(this.openMenuItem);

    this.saveMenuItem.setText("Save");
    this.fileMenu.add(this.saveMenuItem);

    this.saveAsMenuItem.setText("Save As ...");
    this.fileMenu.add(this.saveAsMenuItem);

    this.exitMenuItem.setText("Exit");
    this.exitMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.exitMenuItemActionPerformed(evt);
      }
    });
    this.fileMenu.add(this.exitMenuItem);

    //this.getMenuBar().add(this.fileMenu);

    this.editMenu.setText("Edit");

    this.copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(
      67, 
      2));
    this.copyMenuItem.setText("Copy");
    this.copyMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.copyMenuItemActionPerformed(evt);
      }
    });
    this.editMenu.add(this.copyMenuItem);

    this.pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(
      86, 
      2));
    this.pasteMenuItem.setText("Paste");
    this.pasteMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.pasteMenuItemActionPerformed(evt);
      }
    });
    this.editMenu.add(this.pasteMenuItem);
    this.editMenu.add(this.jSeparator1);

    this.jMenuItem1.setAccelerator(KeyStroke.getKeyStroke(
      65, 
      2));
    this.jMenuItem1.setText("Select All");
    this.jMenuItem1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem1ActionPerformed(evt);
      }
    });
    this.editMenu.add(this.jMenuItem1);

    this.jMenuItem2.setAccelerator(KeyStroke.getKeyStroke(
      73, 
      2));
    this.jMenuItem2.setText("Inverse Selection");
    this.jMenuItem2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem2ActionPerformed(evt);
      }
    });
    this.editMenu.add(this.jMenuItem2);

    //this.menuBar.add(this.editMenu);

    this.helpMenu.setText("Help");

    this.contentsMenuItem.setText("Contents");
    this.helpMenu.add(this.contentsMenuItem);

    this.aboutMenuItem.setText("About");
    this.helpMenu.add(this.aboutMenuItem);

    //this.menuBar.add(this.helpMenu);

    this.jCheckBox1.setText("Select Transparent Pixels");
    this.jCheckBox1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jCheckBox1ActionPerformed(evt);

      }
    });
    this.fileMenu1.setText("File");

    this.jMenuItem9.setAccelerator(KeyStroke.getKeyStroke(
      78, 
      2));
    this.jMenuItem9.setText("New");
    this.jMenuItem9.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem9ActionPerformed(evt);
      }
    });
    this.fileMenu1.add(this.jMenuItem9);

    this.openMenuItem1.setAccelerator(KeyStroke.getKeyStroke(
      79, 
      2));
    this.openMenuItem1.setText("Open");
    this.openMenuItem1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.openMenuItem1ActionPerformed(evt);
      }
    });
    this.fileMenu1.add(this.openMenuItem1);

    this.saveMenuItem1.setAccelerator(KeyStroke.getKeyStroke(
      83, 
      2));
    this.saveMenuItem1.setText("Save");
    this.saveMenuItem1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.saveMenuItem1ActionPerformed(evt);
      }
    });
    this.fileMenu1.add(this.saveMenuItem1);

    this.saveAsMenuItem1.setText("Save As ...");
    this.saveAsMenuItem1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.saveAsMenuItem1ActionPerformed(evt);
      }
    });
    this.fileMenu1.add(this.saveAsMenuItem1);
    this.fileMenu1.add(this.jSeparator8);

    this.jMenu2.setText("Recent Files");
    this.fileMenu1.add(this.jMenu2);
    this.fileMenu1.add(this.jSeparator7);

    this.exitMenuItem1.setText("Exit");
    this.exitMenuItem1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.exitMenuItemActionPerformed(evt);
      }
    });
    this.fileMenu1.add(this.exitMenuItem1);

    this.menuBar1.add(this.fileMenu1);

    this.editMenu1.setText("Edit");

    this.jMenuItem10.setAccelerator(KeyStroke.getKeyStroke(
      88, 
      2));
    this.jMenuItem10.setText("Cut");
    this.jMenuItem10.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem10ActionPerformed(evt);
      }
    });
    this.editMenu1.add(this.jMenuItem10);

    this.copyMenuItem1.setAccelerator(KeyStroke.getKeyStroke(
      67, 
      2));
    this.copyMenuItem1.setText("Copy");
    this.copyMenuItem1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.copyMenuItemActionPerformed(evt);
      }
    });
    this.editMenu1.add(this.copyMenuItem1);

    this.pasteMenuItem1.setAccelerator(KeyStroke.getKeyStroke(
      86, 
      2));
    this.pasteMenuItem1.setText("Paste");
    this.pasteMenuItem1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.pasteMenuItemActionPerformed(evt);
      }
    });
    this.editMenu1.add(this.pasteMenuItem1);
    this.editMenu1.add(this.jSeparator2);

    this.jMenuItem3.setAccelerator(KeyStroke.getKeyStroke(
      65, 
      2));
    this.jMenuItem3.setText("Select All");
    this.jMenuItem3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem1ActionPerformed(evt);
      }
    });
    this.editMenu1.add(this.jMenuItem3);

    this.jMenuItem4.setAccelerator(KeyStroke.getKeyStroke(
      73, 
      2));
    this.jMenuItem4.setText("Inverse Selection");
    this.jMenuItem4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem2ActionPerformed(evt);
      }
    });
    this.editMenu1.add(this.jMenuItem4);

    this.menuBar1.add(this.editMenu1);

    this.jMenu1.setText("Tools");

    this.jMenuItem5.setAccelerator(KeyStroke.getKeyStroke(
      87, 
      2));
    this.jMenuItem5.setText("Fill White");
    this.jMenuItem5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem5ActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItem5);

    this.jMenuItem6.setAccelerator(KeyStroke.getKeyStroke(
      66, 
      2));
    this.jMenuItem6.setText("Fill Black");
    this.jMenuItem6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem6ActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItem6);

    this.jMenuItem7.setAccelerator(KeyStroke.getKeyStroke(
      84, 
      2));
    this.jMenuItem7.setText("Fill Transparent");
    this.jMenuItem7.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem7ActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItem7);
    this.jMenu1.add(this.jSeparator6);

    this.jMenuItem16.setAccelerator(KeyStroke.getKeyStroke(
      73, 0));
    this.jMenuItem16.setText("Invert Colors");
    this.jMenuItem16.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem16ActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItem16);
    this.jMenu1.add(this.jSeparator5);

    this.jMenuItem11.setAccelerator(KeyStroke.getKeyStroke(
      87, 0));
    this.jMenuItem11.setText("Set Color: White");
    this.jMenuItem11.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem11ActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItem11);

    this.jMenuItem12.setAccelerator(KeyStroke.getKeyStroke(
      66, 0));
    this.jMenuItem12.setText("Set Color: Black");
    this.jMenuItem12.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem12ActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItem12);

    this.jMenuItem13.setAccelerator(KeyStroke.getKeyStroke(
      84, 0));
    this.jMenuItem13.setText("Set Color: Transparent");
    this.jMenuItem13.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem13ActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItem13);
    this.jMenu1.add(this.jSeparator4);

    this.jMenuItem15.setAccelerator(KeyStroke.getKeyStroke(
      86, 0));
    this.jMenuItem15.setText("Flip Vertically");
    this.jMenuItem15.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem15ActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItem15);

    this.jMenuItem14.setAccelerator(KeyStroke.getKeyStroke(
      72, 0));
    this.jMenuItem14.setText("Flip Horizontally");
    this.jMenuItem14.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem14ActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItem14);
    this.jMenu1.add(this.jSeparator3);

    this.jMenuItem8.setAccelerator(KeyStroke.getKeyStroke(
      83, 0));
    this.jMenuItem8.setText("Save Character");
    this.jMenuItem8.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.jMenuItem8ActionPerformed(evt);
      }
    });
    this.jMenu1.add(this.jMenuItem8);

    this.menuBar1.add(this.jMenu1);

    this.helpMenu1.setText("Help");

    this.aboutMenuItem1.setText("About");
    this.aboutMenuItem1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        Max7456Charwizard.this.aboutMenuItem1ActionPerformed(evt);
      }
    });
    this.helpMenu1.add(this.aboutMenuItem1);

    this.menuBar1.add(this.helpMenu1);

    setJMenuBar(this.menuBar1);

    GroupLayout layout = new GroupLayout(
      getContentPane());
    getContentPane().setLayout(layout);
    layout
      .setHorizontalGroup(layout
      .createParallelGroup(
      GroupLayout.Alignment.LEADING)
      .addGroup(
      layout
      .createSequentialGroup()
      .addContainerGap()
      .addComponent(
      this.mCMPanel1, 
      -2, 
      -1, 
      -2)
      .addGap(6, 6, 6)
      .addComponent(
      this.mCMCharacterEditor1, 
      -1, 
      213, 32767)
      .addPreferredGap(
      LayoutStyle.ComponentPlacement.RELATED)
      .addGroup(
      layout
      .createParallelGroup(
      GroupLayout.Alignment.LEADING)
      .addGroup(
      layout
      .createParallelGroup(
      GroupLayout.Alignment.LEADING, 
      false)
      .addComponent(
      this.jButton4, 
      0, 
      0, 
      32767)
      .addComponent(
      this.jButton1, 
      -1, 
      -1, 
      32767)
      .addComponent(
      this.jButton2, 
      -1, 
      -1, 
      32767)
      .addComponent(
      this.jButton3, 
      -1, 
      -1, 
      32767))
      .addComponent(
      this.jCheckBox1))
      .addContainerGap(75, 32767)));
    layout
      .setVerticalGroup(layout
      .createParallelGroup(
      GroupLayout.Alignment.LEADING)
      .addGroup(
      layout
      .createSequentialGroup()
      .addContainerGap()
      .addGroup(
      layout
      .createParallelGroup(
      GroupLayout.Alignment.TRAILING)
      .addGroup(
      GroupLayout.Alignment.LEADING, 
      layout
      .createSequentialGroup()
      .addComponent(
      this.jButton1)
      .addPreferredGap(
      LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(
      this.jButton2)
      .addPreferredGap(
      LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(
      this.jButton3)
      .addPreferredGap(
      LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(
      this.jButton4)
      .addPreferredGap(
      LayoutStyle.ComponentPlacement.RELATED)
      .addComponent(
      this.jCheckBox1))
      .addComponent(
      this.mCMCharacterEditor1, 
      GroupLayout.Alignment.LEADING, 
      -2, 
      -1, 
      -2)
      .addComponent(
      this.mCMPanel1, 
      GroupLayout.Alignment.LEADING, 
      -2, 
      -1, 
      -2))
      .addContainerGap(52, 32767)));

    pack();
  }

  protected void formKeyReleased(KeyEvent evt) {
	// TODO Auto-generated method stub
	
}

private void jButton5ActionPerformed(ActionEvent evt)
  {
    this.jDialog1.setVisible(false);
  }

  private void aboutMenuItem1ActionPerformed(ActionEvent evt) {
    this.jDialog1.setVisible(true);
  }

  private void jMenuItem8ActionPerformed(ActionEvent evt) {
    if (this.mCMCharacterEditor1.getMcmChar() != null) {
      this.mCMCharacterEditor1.getMcmChar().saveChanges();
      this.mCMPanel1.repaint();
    }
  }

  private void jMenuItem14ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.flipHorizontal();
  }

  private void jMenuItem15ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.flipVertical();
  }

  private void jMenuItem9ActionPerformed(ActionEvent evt) {
    this.charset.createEmpty();
    this.mCMPanel1.resetSelection();

    setTitle(getTitle());
    this.mCMPanel1.repaint();
  }

  private void openMenuItem1ActionPerformed(ActionEvent evt)
  {
    String dir = this.userSettings.getLastUsedDirectory();
    if (dir == null) {
      dir = ".";
    }
    this.jfOpen.setCurrentDirectory(new File(dir));

    this.jfOpen.showOpenDialog(this);

    if (this.jfOpen.getSelectedFile() != null)
      openFile(this.jfOpen.getSelectedFile());
  }

  private void openFile(File file) {
    try {
      this.charset.setFilename(file.getAbsolutePath());
      this.charset.load();

      this.userSettings.setLastUsedDirectory(file.getParent());
      this.userSettings.addRecentFile(file.getAbsolutePath());
      this.userSettings.save();

      this.mCMPanel1.resetSelection();

      initRecentFilesMenu();


      setTitle(getTitle());
      repaint();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }


  private void saveAsMenuItem1ActionPerformed(ActionEvent evt)
  {
    String dir = this.userSettings.getLastUsedDirectory();
    if (dir == null) {
      dir = ".";
    }
    this.jfSave.setCurrentDirectory(new File(dir));

    this.jfSave.showOpenDialog(this);
    try
    {
      if (this.jfSave.getSelectedFile() != null) {
        this.charset.setFilename(this.jfSave.getSelectedFile()
          .getAbsolutePath());
        this.charset.save();

        this.userSettings.setLastUsedDirectory(this.jfSave.getSelectedFile()
          .getParent());
        this.userSettings.addRecentFile(this.jfSave.getSelectedFile()
          .getAbsolutePath());

        this.userSettings.save();

        initRecentFilesMenu();

        setTitle(getTitle());
        repaint();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }





  private void jCheckBox1ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.setSelectTransparentPixels(this.jCheckBox1
      .isSelected());
  }

  private void jMenuItem16ActionPerformed(ActionEvent evt)
  {
    this.mCMCharacterEditor1.invertPixels();
  }

  private void jMenuItem13ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.setBrushColor(Pixel.TRANSPARENT);
  }

  private void jMenuItem12ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.setBrushColor(Pixel.BLACK);
  }

  private void jMenuItem11ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.setBrushColor(Pixel.WHITE);
  }

  private void jMenuItem7ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.setAllTransparent();
  }

  private void jMenuItem6ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.setAllBlack();
  }

  private void jMenuItem5ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.setAllWhite();
  }

  private void jMenuItem10ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.cutSelectedPixels();
  }

  private void saveMenuItem1ActionPerformed(ActionEvent evt) {
    try {
      if (this.charset.getFilename().equals("UNNAMED")) {
        saveAsMenuItem1ActionPerformed(evt); return;
      }
      this.charset.save();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void pasteMenuItemActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.pasteSelectedPixels();
  }

  private void copyMenuItemActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.copySelectedPixels();
  }

  private void jButton4ActionPerformed(ActionEvent evt) {
    if (this.mCMCharacterEditor1.getMcmChar() != null) {
      this.mCMCharacterEditor1.getMcmChar().saveChanges();
      this.mCMPanel1.repaint();
    }
  }

  private void jMenuItem2ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.inverseSelection();
  }

  private void jMenuItem1ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.selectAll();
  }

  private void jButton3ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.setAllTransparent();
  }

  private void jButton1ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.setAllWhite();
  }

  protected void exitMenuItemActionPerformed(ActionEvent evt) {
    System.exit(0);
  }

  private void jButton2ActionPerformed(ActionEvent evt) {
    this.mCMCharacterEditor1.setAllBlack();
  }



  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Max7456Charwizard().setVisible(true);
      }
    });
  }
}
