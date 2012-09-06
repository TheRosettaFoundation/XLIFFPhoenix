/*
 * Lmc_BuilderView.java
 */

package lmc_builder;

import com.centerkey.utils.BareBonesBrowserLaunch;
import lmc_builder.IO.Output;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.FrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jdom.Element;


/**
 * The application's main frame.
 */
public class Lmc_BuilderView extends FrameView {
    Core core;
    JFileChooser chooser;
    boolean tmxConvert = false;
    boolean omit = false;
    boolean convertall = false;
    DefaultListModel current = new DefaultListModel();
    public Lmc_BuilderView(Lmc_BuilderApp app) {
        super(app);
        initComponents();
        JFrame temp = getFrame();
        temp.setTitle("LMC Builder 1.0");
        core= app.getCore();
        chooser = core.getChooser();
        chooser.setMultiSelectionEnabled(true);
        temp.setIconImage(Core.getLogo());
        temp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);

        

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
      
    }

    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tmxPanel = new javax.swing.JPanel();
        omitSameLangBox = new javax.swing.JCheckBox();
        convertAllLangBox = new javax.swing.JCheckBox();
        tmxConvertBox = new javax.swing.JCheckBox();
        sourceLanguageLable = new javax.swing.JLabel();
        targetLanguageLable = new javax.swing.JLabel();
        targetLanguageField = new javax.swing.JTextField();
        sourceLanguageField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jFileList = new javax.swing.JList();
        authorField = new javax.swing.JTextField();
        authorLable = new javax.swing.JLabel();
        export = new javax.swing.JButton();
        append = new javax.swing.JButton();
        selectLmc = new javax.swing.JButton();
        processButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        lmcPanel = new javax.swing.JPanel();
        fileCountLable = new javax.swing.JLabel();
        countLable = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lmcFileList = new javax.swing.JList();
        lmcContenceLable = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        helpMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(lmc_builder.Lmc_BuilderApp.class).getContext().getResourceMap(Lmc_BuilderView.class);
        mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(resourceMap.getColor("mainPanel.border.highlightColor"), resourceMap.getColor("mainPanel.border.shadowColor"))); // NOI18N
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setOpaque(false);
        mainPanel.setPreferredSize(new java.awt.Dimension(584, 490));

        tmxPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(resourceMap.getColor("tmxPanel.border.highlightColor"), resourceMap.getColor("tmxPanel.border.shadowColor"))); // NOI18N
        tmxPanel.setForeground(resourceMap.getColor("tmxPanel.foreground")); // NOI18N
        tmxPanel.setName("tmxPanel"); // NOI18N

        omitSameLangBox.setText(resourceMap.getString("omitSameLangBox.text")); // NOI18N
        omitSameLangBox.setName("omitSameLangBox"); // NOI18N
        omitSameLangBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                omitSameLangBoxActionPerformed(evt);
            }
        });

        convertAllLangBox.setText(resourceMap.getString("convertAllLangBox.text")); // NOI18N
        convertAllLangBox.setName("convertAllLangBox"); // NOI18N
        convertAllLangBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertAllLangBoxActionPerformed(evt);
            }
        });

        tmxConvertBox.setText(resourceMap.getString("tmxConvertBox.text")); // NOI18N
        tmxConvertBox.setName("tmxConvertBox"); // NOI18N
        tmxConvertBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmxConvertBoxActionPerformed(evt);
            }
        });

        sourceLanguageLable.setText(resourceMap.getString("sourceLanguageLable.text")); // NOI18N
        sourceLanguageLable.setName("sourceLanguageLable"); // NOI18N

        targetLanguageLable.setText(resourceMap.getString("targetLanguageLable.text")); // NOI18N
        targetLanguageLable.setName("targetLanguageLable"); // NOI18N

        targetLanguageField.setText(resourceMap.getString("targetLanguageField.text")); // NOI18N
        targetLanguageField.setName("targetLanguageField"); // NOI18N

        sourceLanguageField.setText(resourceMap.getString("sourceLanguageField.text")); // NOI18N
        sourceLanguageField.setName("sourceLanguageField"); // NOI18N

        javax.swing.GroupLayout tmxPanelLayout = new javax.swing.GroupLayout(tmxPanel);
        tmxPanel.setLayout(tmxPanelLayout);
        tmxPanelLayout.setHorizontalGroup(
            tmxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tmxPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tmxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(omitSameLangBox)
                    .addComponent(convertAllLangBox)
                    .addComponent(tmxConvertBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tmxPanelLayout.createSequentialGroup()
                        .addComponent(sourceLanguageLable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sourceLanguageField, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tmxPanelLayout.createSequentialGroup()
                        .addComponent(targetLanguageLable, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(targetLanguageField, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tmxPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {sourceLanguageField, targetLanguageField});

        tmxPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {sourceLanguageLable, targetLanguageLable});

        tmxPanelLayout.setVerticalGroup(
            tmxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tmxPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(tmxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sourceLanguageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sourceLanguageLable))
                .addGap(18, 18, 18)
                .addGroup(tmxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(targetLanguageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(targetLanguageLable))
                .addGap(18, 18, 18)
                .addComponent(tmxConvertBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(convertAllLangBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(omitSameLangBox)
                .addGap(43, 43, 43))
        );

        tmxPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {sourceLanguageField, sourceLanguageLable, targetLanguageField, targetLanguageLable});

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jFileList.setAutoscrolls(false);
        jFileList.setName("jFileList"); // NOI18N
        jScrollPane2.setViewportView(jFileList);

        authorField.setName("authorField"); // NOI18N
        authorField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorFieldActionPerformed(evt);
            }
        });

        authorLable.setText(resourceMap.getString("authorLable.text")); // NOI18N
        authorLable.setName("authorLable"); // NOI18N

        export.setText(resourceMap.getString("export.text")); // NOI18N
        export.setFocusable(false);
        export.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        export.setName("export"); // NOI18N
        export.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });

        append.setText(resourceMap.getString("append.text")); // NOI18N
        append.setFocusable(false);
        append.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        append.setName("append"); // NOI18N
        append.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        append.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appendActionPerformed(evt);
            }
        });

        selectLmc.setText(resourceMap.getString("selectLmc.text")); // NOI18N
        selectLmc.setFocusable(false);
        selectLmc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        selectLmc.setName("selectLmc"); // NOI18N
        selectLmc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        selectLmc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectLmcActionPerformed(evt);
            }
        });

        processButton.setText(resourceMap.getString("processButton.text")); // NOI18N
        processButton.setFocusable(false);
        processButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        processButton.setName("processButton"); // NOI18N
        processButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        processButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processButtonActionPerformed(evt);
            }
        });

        SaveButton.setText(resourceMap.getString("SaveButton.text")); // NOI18N
        SaveButton.setFocusable(false);
        SaveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SaveButton.setName("SaveButton"); // NOI18N
        SaveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        openButton.setText(resourceMap.getString("openButton.text")); // NOI18N
        openButton.setFocusable(false);
        openButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openButton.setName("openButton"); // NOI18N
        openButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        lmcPanel.setBorder(tmxPanel.getBorder());
        lmcPanel.setName("lmcPanel"); // NOI18N

        fileCountLable.setText(resourceMap.getString("fileCountLable.text")); // NOI18N
        fileCountLable.setName("fileCountLable"); // NOI18N

        countLable.setText(resourceMap.getString("countLable.text")); // NOI18N
        countLable.setName("countLable"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        lmcFileList.setName("lmcFileList"); // NOI18N
        jScrollPane3.setViewportView(lmcFileList);

        lmcContenceLable.setText(resourceMap.getString("lmcContenceLable.text")); // NOI18N
        lmcContenceLable.setName("lmcContenceLable"); // NOI18N

        javax.swing.GroupLayout lmcPanelLayout = new javax.swing.GroupLayout(lmcPanel);
        lmcPanel.setLayout(lmcPanelLayout);
        lmcPanelLayout.setHorizontalGroup(
            lmcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lmcPanelLayout.createSequentialGroup()
                .addGroup(lmcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lmcPanelLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(lmcContenceLable))
                    .addGroup(lmcPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(lmcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, Short.MAX_VALUE)
                            .addGroup(lmcPanelLayout.createSequentialGroup()
                                .addComponent(fileCountLable, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                .addGap(126, 126, 126)
                                .addComponent(countLable, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        lmcPanelLayout.setVerticalGroup(
            lmcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lmcPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lmcPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileCountLable)
                    .addComponent(countLable))
                .addGap(18, 18, 18)
                .addComponent(lmcContenceLable)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addContainerGap())
        );

        lmcPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {countLable, fileCountLable});

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(openButton, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(processButton, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selectLmc, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                                .addComponent(authorLable, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(authorField, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                            .addComponent(tmxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(append, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(export, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addGap(30, 30, 30))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(lmcPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addGap(533, 533, 533))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(authorLable, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                            .addComponent(authorField))
                        .addGap(20, 20, 20)
                        .addComponent(tmxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                    .addComponent(lmcPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(export, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(append, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectLmc, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(processButton, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        mainPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {SaveButton, append, export, openButton, processButton, selectLmc});

        authorField.setText(System.getProperty("user.name"));

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(lmc_builder.Lmc_BuilderApp.class).getContext().getActionMap(Lmc_BuilderView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        helpMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        helpMenuItem.setText(resourceMap.getString("helpMenuItem.text")); // NOI18N
        helpMenuItem.setName("helpMenuItem"); // NOI18N
        helpMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(helpMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setPreferredSize(new java.awt.Dimension(579, 40));

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 40));
        jPanel1.setMinimumSize(new java.awt.Dimension(100, 40));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(387, 40));

        jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(60, 29));
        jLabel1.setMinimumSize(new java.awt.Dimension(40, 29));
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(45, 29));
        jPanel1.add(jLabel1);

        jLabel3.setIcon(resourceMap.getIcon("jLabel3.icon")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setMaximumSize(new java.awt.Dimension(100, 35));
        jLabel3.setMinimumSize(new java.awt.Dimension(75, 35));
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(85, 35));
        jPanel1.add(jLabel3);

        jLabel4.setIcon(resourceMap.getIcon("jLabel4.icon")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N
        jPanel1.add(jLabel4);

        jLabel5.setIcon(resourceMap.getIcon("jLabel5.icon")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N
        jPanel1.add(jLabel5);

        jLabel6.setIcon(resourceMap.getIcon("jLabel6.icon")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setMaximumSize(new java.awt.Dimension(50, 29));
        jLabel6.setMinimumSize(new java.awt.Dimension(25, 29));
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(30, 29));
        jPanel1.add(jLabel6);

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 595, Short.MAX_VALUE)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
       if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
           if(chooser.getSelectedFiles().length>1){
               core.loadDocs(chooser.getSelectedFiles());
               DefaultListModel list= new DefaultListModel();
               for(File d:chooser.getSelectedFiles()) list.addElement(d.getName());
               jFileList.setModel(list);
               jFileList.validate();
               current=list;
           }
           else {
               DefaultListModel list= new DefaultListModel();
               list.addElement(chooser.getSelectedFile().getName());
               jFileList.setModel(list);
               jFileList.validate();
               current=list;
               core.loadDocs(chooser.getSelectedFile());
           }
       }
    }//GEN-LAST:event_openButtonActionPerformed

    private void processButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processButtonActionPerformed
        if(!core.doclist.isEmpty()){
            if(!authorField.getText().isEmpty())core.builder.setAuthor(authorField.getText());
            if(convertall)core.build(tmxConvert,omit);
            else {
                if((sourceLanguageField.getText().isEmpty()||targetLanguageField.getText().isEmpty())&&tmxConvert){
                JOptionPane.showMessageDialog(null, "Please enter both source and target language") ;
                return;
                }
                core.build(tmxConvert, convertall, omit, sourceLanguageField.getText(), targetLanguageField.getText());
            }
            DefaultListModel newLmc = new DefaultListModel();
            for(int i=0;i!=current.getSize();i++){
                Object o = current.get(i);
                newLmc.addElement(current.get(i));
            }
            lmcFileList.setModel(newLmc);
            lmcFileList.validate();
            countLable.setText(""+current.size());
            current.clear();
            core.doclist.clear();
        }
        else JOptionPane.showMessageDialog(null, "no files have been loaded.\nplease load files before building");
    }//GEN-LAST:event_processButtonActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        chooser.showSaveDialog(null);
        Output.save(core.lmcDoc,chooser.getSelectedFile().getAbsolutePath());
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void tmxConvertBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tmxConvertBoxActionPerformed
        tmxConvert=!tmxConvert;

    }//GEN-LAST:event_tmxConvertBoxActionPerformed

    private void convertAllLangBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertAllLangBoxActionPerformed
        convertall=!convertall;
    }//GEN-LAST:event_convertAllLangBoxActionPerformed

    private void omitSameLangBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_omitSameLangBoxActionPerformed
        omit=!omit;
    }//GEN-LAST:event_omitSameLangBoxActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        aboutBox= new AboutBox();
        aboutBox.setVisible(true);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void appendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appendActionPerformed
        if(!core.doclist.isEmpty()){
            if(!tmxConvert)core.append();
            else core.append(convertall, omit, sourceLanguageField.getText(), targetLanguageField.getText());
            DefaultListModel lmc =(DefaultListModel)lmcFileList.getModel();
            DefaultListModel newLmc = new DefaultListModel();
            for(int i=0;i!=lmc.getSize();i++){
                       newLmc.addElement(lmc.get(i));
            }
            for(int i=0;i!=current.getSize();i++){
                Object o = current.get(i);
                newLmc.addElement(current.get(i));
            }
            lmcFileList.setModel(newLmc);
            lmcFileList.validate();
            countLable.setText(""+newLmc.size());
            current.clear();
            core.doclist.clear();
        }
        else JOptionPane.showMessageDialog(null, "no files have been loaded.\nplease load files before appending");
    }//GEN-LAST:event_appendActionPerformed

    private void selectLmcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectLmcActionPerformed
        chooser.showOpenDialog(null);
        core.loadLmc(chooser.getSelectedFile());
        DefaultListModel list= new DefaultListModel();
        List temp = core.filter("//xliff:file", core.lmcDoc);
        for(Object name:temp) list.addElement(((Element)name).getAttributeValue("original"));
        lmcFileList.setModel(list);
        lmcFileList.validate();
        countLable.setText(""+list.size());
    }//GEN-LAST:event_selectLmcActionPerformed

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        if(sourceLanguageField.getText().isEmpty()||targetLanguageField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please enter both source and target language") ;
            return;
        }
        core.ttxExporter(sourceLanguageField.getText(),targetLanguageField.getText());
    }//GEN-LAST:event_exportActionPerformed

    private void authorFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorFieldActionPerformed
        core.setAuthor(authorField.getText());
    }//GEN-LAST:event_authorFieldActionPerformed

    private void helpMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemActionPerformed
       BareBonesBrowserLaunch.openURL("http://www.localisation.ie/lmcbuilder/help/help.html");
    }//GEN-LAST:event_helpMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SaveButton;
    private javax.swing.JButton append;
    private javax.swing.JTextField authorField;
    private javax.swing.JLabel authorLable;
    private javax.swing.JCheckBox convertAllLangBox;
    private javax.swing.JLabel countLable;
    private javax.swing.JButton export;
    private javax.swing.JLabel fileCountLable;
    private javax.swing.JMenuItem helpMenuItem;
    private javax.swing.JList jFileList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lmcContenceLable;
    private javax.swing.JList lmcFileList;
    private javax.swing.JPanel lmcPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JCheckBox omitSameLangBox;
    private javax.swing.JButton openButton;
    private javax.swing.JButton processButton;
    private javax.swing.JButton selectLmc;
    private javax.swing.JTextField sourceLanguageField;
    private javax.swing.JLabel sourceLanguageLable;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextField targetLanguageField;
    private javax.swing.JLabel targetLanguageLable;
    private javax.swing.JCheckBox tmxConvertBox;
    private javax.swing.JPanel tmxPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JFrame aboutBox;
}
