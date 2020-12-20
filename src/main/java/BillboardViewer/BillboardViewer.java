/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillboardViewer;

import BillboardViewer.model.Billboard;
import Commmunication.Message;
import Commmunication.Requests.CurrentBillboardRequest;
import Commmunication.Response.CurrentBillboardResponse;
import Commmunication.Response.EndResponse;
import ServerConnection.Exceptions.ServerClosedException;
import ServerConnection.ServerConnection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;


/**
 * Billboard Viewer BillboardControlPanel
 * @author Asus
 */
public class BillboardViewer extends javax.swing.JFrame {

    private Billboard currentBillboard;
    /**
     * Creates new form BillboardViewer
     */
    public BillboardViewer() {
        currentBillboard = new Billboard();
        initComponents();
        containerPanelMPI.setVisible(false);
        containerPanelM.setVisible(false);
        containerPanelP.setVisible(false);
        containerPanelI.setVisible(false);
        containerPanelMP.setVisible(false);
        containerPanelMI.setVisible(false);
        containerPanelPI.setVisible(false);
        
        


        // Set a timer to send request and determine which screen will be displayed every 15 second
        final Timer timer = new Timer(15000, null);
        timer.setInitialDelay(10);
        ActionListener listener = new ActionListener() {
            int i = 0;
            String error = "";
            @Override
            public void actionPerformed(ActionEvent e) {
                error = getCurrentBillboardString();

                String  message= "null", information="null", bgColor="#FFFFFF", msColor="#000000", infoColor = "#000000" ,picture="null";
                // Get billboard's contents

                if (error == null) {
                    message = currentBillboard.getMessage();
                    information = currentBillboard.getInformation();
                    bgColor = currentBillboard.getBackgroundColor();
                    msColor = currentBillboard.getMessageColor();
                    picture = currentBillboard.getPicture();
                    infoColor = currentBillboard.getInfoColor();
                } else {
                    message = error;

                }

                // Check if billboard contains message, picture or information to display suitable panel
                if(!message.equals("null") && !picture.equals("null") && !information.equals("null")) {
                    containerPanelMPI.setVisible(true);
                    containerPanelM.setVisible(false);
                    containerPanelP.setVisible(false);
                    containerPanelI.setVisible(false);
                    containerPanelMP.setVisible(false);
                    containerPanelMI.setVisible(false);
                    containerPanelPI.setVisible(false);

                    containerPanelMPI.setBackground(hex2Rgb(bgColor));
                    lbMessageMPI.setForeground(hex2Rgb(msColor));
                    lbInfoMPI.setForeground(hex2Rgb(infoColor));

                    lbMessageMPI.setText("<html><body style='text-align: center'>"+ message + "</html>");
                    lbInfoMPI.setText("<html><body style='text-align: center'>"+ information + "</html>");

                    Image image = null;
                    try {
                        if (picture.contains("https:") || picture.contains("http:")) {
                            URL url = new URL(picture);
                            image = ImageIO.read(url);

                        }else {
                            byte[] imageBytes = Base64.getDecoder().decode(picture);
                            image = ImageIO.read(new ByteArrayInputStream(imageBytes));
                        }
                    } catch (Exception ex){
                        image = createNotFoundImage();
                    }
                    image = image.getScaledInstance(-1, lbPictureMPI.getPreferredSize().height,Image.SCALE_SMOOTH);
                    lbPictureMPI.setIcon(new ImageIcon(image));



                } else if (!message.equals("null") && picture.equals("null") && information.equals("null") ) {
                    containerPanelMPI.setVisible(false);
                    containerPanelM.setVisible(true);
                    containerPanelP.setVisible(false);
                    containerPanelI.setVisible(false);
                    containerPanelMP.setVisible(false);
                    containerPanelMI.setVisible(false);
                    containerPanelPI.setVisible(false);

                    containerPanelM.setBackground(hex2Rgb(bgColor));
                    lbMessageM.setText("<html><body style='text-align: center'>"+ message + "</html>");
                    lbMessageM.setForeground(hex2Rgb(msColor));



                } else if (message.equals("null") && !picture.equals("null") && information.equals("null") ) {
                    containerPanelMPI.setVisible(false);
                    containerPanelM.setVisible(false);
                    containerPanelP.setVisible(true);
                    containerPanelI.setVisible(false);
                    containerPanelMP.setVisible(false);
                    containerPanelMI.setVisible(false);
                    containerPanelPI.setVisible(false);

                    containerPanelP.setBackground(hex2Rgb(bgColor));
                    Image image = null;
                    try {
                        if (picture.contains("https:") || picture.contains("http:")) {
                            URL url = new URL(picture);
                            image = ImageIO.read(url);

                        }else {
                            byte[] imageBytes = Base64.getDecoder().decode(picture);
                            image = ImageIO.read(new ByteArrayInputStream(imageBytes));
                        }
                    } catch (Exception ex){
                        image = createNotFoundImage();
                    }
                    image = image.getScaledInstance(-1, lbPictureP.getPreferredSize().height,Image.SCALE_SMOOTH);
                    lbPictureP.setIcon(new ImageIcon(image));


                } else if (message.equals("null") && picture.equals("null") && !information.equals("null") ) {
                    containerPanelMPI.setVisible(false);
                    containerPanelM.setVisible(false);
                    containerPanelP.setVisible(false);
                    containerPanelI.setVisible(true);
                    containerPanelMP.setVisible(false);
                    containerPanelMI.setVisible(false);
                    containerPanelPI.setVisible(false);

                    containerPanelI.setBackground(hex2Rgb(bgColor));

                    lbInfoI.setText("<html><body style='text-align: center'>"+ information + "</html>");
                    lbInfoI.setForeground(hex2Rgb(infoColor));


                } else if (!message.equals("null") && !picture.equals("null") && information.equals("null") ) {
                    containerPanelMPI.setVisible(false);
                    containerPanelM.setVisible(false);
                    containerPanelP.setVisible(false);
                    containerPanelI.setVisible(false);
                    containerPanelMP.setVisible(true);
                    containerPanelMI.setVisible(false);
                    containerPanelPI.setVisible(false);

                    containerPanelMP.setBackground(hex2Rgb(bgColor));

                    lbMessageMP.setText("<html><body style='text-align: center'>"+ message + "</html>");
                    lbMessageMP.setForeground(hex2Rgb(msColor));

                    Image image = null;
                    try {
                        if (picture.contains("https:") || picture.contains("http:")) {
                            URL url = new URL(picture);
                            image = ImageIO.read(url);

                        }else {
                            byte[] imageBytes = Base64.getDecoder().decode(picture);
                            image = ImageIO.read(new ByteArrayInputStream(imageBytes));
                        }
                    } catch (Exception ex){
                        image = createNotFoundImage();
                    }
                    image = image.getScaledInstance(-1, lbPictureMP.getPreferredSize().height,Image.SCALE_SMOOTH);
                    lbPictureMP.setIcon(new ImageIcon(image));

                } else if (!message.equals("null") && picture.equals("null") && !information.equals("null") ) {
                    containerPanelMPI.setVisible(false);
                    containerPanelM.setVisible(false);
                    containerPanelP.setVisible(false);
                    containerPanelI.setVisible(false);
                    containerPanelMP.setVisible(false);
                    containerPanelMI.setVisible(true);
                    containerPanelPI.setVisible(false);

                    containerPanelMI.setBackground(hex2Rgb(bgColor));
                    lbMessageMI.setText("<html><body style='text-align: center'>"+ message + "</html>");
                    lbMessageMI.setForeground(hex2Rgb(msColor));

                    lbInfoMI.setText("<html><body style='text-align: center'>"+ information + "</html>");
                    lbInfoMI.setForeground(hex2Rgb(infoColor));

                } else if (message.equals("null") && !picture.equals("null") && !information.equals("null") ) {
                    containerPanelMPI.setVisible(false);
                    containerPanelM.setVisible(false);
                    containerPanelP.setVisible(false);
                    containerPanelI.setVisible(false);
                    containerPanelMP.setVisible(false);
                    containerPanelMI.setVisible(false);
                    containerPanelPI.setVisible(true);

                    containerPanelPI.setBackground(hex2Rgb(bgColor));

                    //picture
                    lbInfoPI.setText("<html><body style='text-align: center'>"+ information + "</html>");
                    lbInfoPI.setForeground(hex2Rgb(infoColor));

                    Image image = null;
                    try {
                        if (picture.contains("https:") || picture.contains("http:")) {
                            URL url = new URL(picture);
                            image = ImageIO.read(url);

                        }else {
                            byte[] imageBytes = Base64.getDecoder().decode(picture);
                            image = ImageIO.read(new ByteArrayInputStream(imageBytes));
                        }
                    } catch (Exception ex){
                        image = createNotFoundImage();
                    }
                    image = image.getScaledInstance(-1, lbPicturePI.getPreferredSize().height,Image.SCALE_SMOOTH);
                    lbPicturePI.setIcon(new ImageIcon(image));

                } else {
                    containerPanelMPI.setVisible(false);
                    containerPanelM.setVisible(true);
                    containerPanelP.setVisible(false);
                    containerPanelI.setVisible(false);
                    containerPanelMP.setVisible(false);
                    containerPanelMI.setVisible(false);
                    containerPanelPI.setVisible(false);

                    containerPanelM.setBackground(hex2Rgb("#0000FF"));
                    lbMessageM.setText("<html><body style='text-align: center'>"+"Something went wrong" + "</html>");
                    lbMessageM.setForeground(hex2Rgb("#00FFFF"));
                }

            }

        };
        timer.addActionListener(listener);
        timer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewerPanel = new javax.swing.JPanel();
        containerPanelM = new javax.swing.JPanel();
        lbMessageM = new javax.swing.JLabel();
        containerPanelMPI = new javax.swing.JPanel();
        lbPictureMPI = new javax.swing.JLabel();
        lbMessageMPI = new javax.swing.JLabel();
        lbInfoMPI = new javax.swing.JLabel();
        containerPanelI = new javax.swing.JPanel();
        lbInfoI = new javax.swing.JLabel();
        containerPanelMP = new javax.swing.JPanel();
        lbPictureMP = new javax.swing.JLabel();
        lbMessageMP = new javax.swing.JLabel();
        containerPanelMI = new javax.swing.JPanel();
        lbMessageMI = new javax.swing.JLabel();
        lbInfoMI = new javax.swing.JLabel();
        containerPanelPI = new javax.swing.JPanel();
        lbPicturePI = new javax.swing.JLabel();
        lbInfoPI = new javax.swing.JLabel();
        containerPanelP = new javax.swing.JPanel();
        lbPictureP = new javax.swing.JLabel();
        lbBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        viewerPanel.setBackground(new java.awt.Color(255, 255, 255));
        viewerPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        viewerPanel.setMinimumSize(new java.awt.Dimension(1920, 1080));
        viewerPanel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        viewerPanel.setLayout(null);

        containerPanelM.setBackground(new java.awt.Color(153, 255, 51));
        containerPanelM.setPreferredSize(new java.awt.Dimension(1920, 1080));

        lbMessageM.setFont(new java.awt.Font("MV Boli", 0, 60)); // NOI18N
        lbMessageM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessageM.setToolTipText("");
        lbMessageM.setPreferredSize(new java.awt.Dimension(1200, 900));
        lbMessageM.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout containerPanelMLayout = new javax.swing.GroupLayout(containerPanelM);
        containerPanelM.setLayout(containerPanelMLayout);
        containerPanelMLayout.setHorizontalGroup(
            containerPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerPanelMLayout.createSequentialGroup()
                .addGap(0, 361, Short.MAX_VALUE)
                .addComponent(lbMessageM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(359, Short.MAX_VALUE))
        );
        containerPanelMLayout.setVerticalGroup(
            containerPanelMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelMLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(lbMessageM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        viewerPanel.add(containerPanelM);
        containerPanelM.setBounds(0, 0, 1920, 1080);

        containerPanelMPI.setBackground(new java.awt.Color(153, 255, 51));
        containerPanelMPI.setPreferredSize(new java.awt.Dimension(1920, 1080));

        lbPictureMPI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPictureMPI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbPictureMPI.setMaximumSize(new java.awt.Dimension(960, 540));
        lbPictureMPI.setMinimumSize(new java.awt.Dimension(960, 540));
        lbPictureMPI.setPreferredSize(new java.awt.Dimension(1200, 300));

        lbMessageMPI.setFont(new java.awt.Font("MV Boli", 0, 60)); // NOI18N
        lbMessageMPI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessageMPI.setToolTipText("");
        lbMessageMPI.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lbMessageMPI.setPreferredSize(new java.awt.Dimension(1200, 300));
        lbMessageMPI.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        lbInfoMPI.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbInfoMPI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInfoMPI.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbInfoMPI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbInfoMPI.setPreferredSize(new java.awt.Dimension(1200, 300));

        javax.swing.GroupLayout containerPanelMPILayout = new javax.swing.GroupLayout(containerPanelMPI);
        containerPanelMPI.setLayout(containerPanelMPILayout);
        containerPanelMPILayout.setHorizontalGroup(
            containerPanelMPILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerPanelMPILayout.createSequentialGroup()
                .addGap(0, 405, Short.MAX_VALUE)
                .addGroup(containerPanelMPILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMessageMPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPictureMPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbInfoMPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(315, Short.MAX_VALUE))
        );
        containerPanelMPILayout.setVerticalGroup(
            containerPanelMPILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelMPILayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(lbMessageMPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPictureMPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbInfoMPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        viewerPanel.add(containerPanelMPI);
        containerPanelMPI.setBounds(0, 0, 1920, 1080);

        containerPanelI.setBackground(new java.awt.Color(153, 255, 51));
        containerPanelI.setPreferredSize(new java.awt.Dimension(1920, 1080));

        lbInfoI.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbInfoI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInfoI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbInfoI.setPreferredSize(new java.awt.Dimension(1200, 900));

        javax.swing.GroupLayout containerPanelILayout = new javax.swing.GroupLayout(containerPanelI);
        containerPanelI.setLayout(containerPanelILayout);
        containerPanelILayout.setHorizontalGroup(
            containerPanelILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerPanelILayout.createSequentialGroup()
                .addContainerGap(361, Short.MAX_VALUE)
                .addComponent(lbInfoI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(359, Short.MAX_VALUE))
        );
        containerPanelILayout.setVerticalGroup(
            containerPanelILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelILayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lbInfoI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        viewerPanel.add(containerPanelI);
        containerPanelI.setBounds(0, 0, 1920, 1080);

        containerPanelMP.setBackground(new java.awt.Color(153, 255, 51));
        containerPanelMP.setPreferredSize(new java.awt.Dimension(1920, 1080));

        lbPictureMP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPictureMP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbPictureMP.setMaximumSize(new java.awt.Dimension(960, 540));
        lbPictureMP.setMinimumSize(new java.awt.Dimension(960, 540));
        lbPictureMP.setPreferredSize(new java.awt.Dimension(1200, 600));

        lbMessageMP.setFont(new java.awt.Font("MV Boli", 0, 60)); // NOI18N
        lbMessageMP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessageMP.setToolTipText("");
        lbMessageMP.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lbMessageMP.setPreferredSize(new java.awt.Dimension(1200, 300));
        lbMessageMP.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout containerPanelMPLayout = new javax.swing.GroupLayout(containerPanelMP);
        containerPanelMP.setLayout(containerPanelMPLayout);
        containerPanelMPLayout.setHorizontalGroup(
            containerPanelMPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerPanelMPLayout.createSequentialGroup()
                .addGap(0, 360, Short.MAX_VALUE)
                .addGroup(containerPanelMPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMessageMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPictureMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(360, 360, 360))
        );
        containerPanelMPLayout.setVerticalGroup(
            containerPanelMPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelMPLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lbMessageMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbPictureMP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        viewerPanel.add(containerPanelMP);
        containerPanelMP.setBounds(0, 0, 1920, 1080);

        containerPanelMI.setBackground(new java.awt.Color(153, 255, 51));
        containerPanelMI.setPreferredSize(new java.awt.Dimension(1920, 1080));

        lbMessageMI.setFont(new java.awt.Font("MV Boli", 0, 60)); // NOI18N
        lbMessageMI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessageMI.setToolTipText("");
        lbMessageMI.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lbMessageMI.setPreferredSize(new java.awt.Dimension(1200, 450));
        lbMessageMI.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        lbInfoMI.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbInfoMI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInfoMI.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbInfoMI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbInfoMI.setPreferredSize(new java.awt.Dimension(1200, 450));

        javax.swing.GroupLayout containerPanelMILayout = new javax.swing.GroupLayout(containerPanelMI);
        containerPanelMI.setLayout(containerPanelMILayout);
        containerPanelMILayout.setHorizontalGroup(
            containerPanelMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerPanelMILayout.createSequentialGroup()
                .addGap(0, 360, Short.MAX_VALUE)
                .addGroup(containerPanelMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMessageMI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbInfoMI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(360, 360, 360))
        );
        containerPanelMILayout.setVerticalGroup(
            containerPanelMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelMILayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lbMessageMI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbInfoMI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        viewerPanel.add(containerPanelMI);
        containerPanelMI.setBounds(0, 0, 1920, 1080);

        containerPanelPI.setBackground(new java.awt.Color(153, 255, 51));
        containerPanelPI.setPreferredSize(new java.awt.Dimension(1920, 1080));

        lbPicturePI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPicturePI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbPicturePI.setMaximumSize(new java.awt.Dimension(960, 540));
        lbPicturePI.setMinimumSize(new java.awt.Dimension(960, 540));
        lbPicturePI.setPreferredSize(new java.awt.Dimension(1200, 600));

        lbInfoPI.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lbInfoPI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbInfoPI.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbInfoPI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbInfoPI.setPreferredSize(new java.awt.Dimension(1200, 300));

        javax.swing.GroupLayout containerPanelPILayout = new javax.swing.GroupLayout(containerPanelPI);
        containerPanelPI.setLayout(containerPanelPILayout);
        containerPanelPILayout.setHorizontalGroup(
            containerPanelPILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerPanelPILayout.createSequentialGroup()
                .addGap(0, 361, Short.MAX_VALUE)
                .addGroup(containerPanelPILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPicturePI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbInfoPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(359, Short.MAX_VALUE))
        );
        containerPanelPILayout.setVerticalGroup(
            containerPanelPILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelPILayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lbPicturePI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbInfoPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        viewerPanel.add(containerPanelPI);
        containerPanelPI.setBounds(0, 0, 1920, 1080);

        containerPanelP.setBackground(new java.awt.Color(153, 255, 51));
        containerPanelP.setPreferredSize(new java.awt.Dimension(1920, 1080));

        lbPictureP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPictureP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbPictureP.setMaximumSize(new java.awt.Dimension(960, 540));
        lbPictureP.setMinimumSize(new java.awt.Dimension(960, 540));
        lbPictureP.setPreferredSize(new java.awt.Dimension(960, 590));

        javax.swing.GroupLayout containerPanelPLayout = new javax.swing.GroupLayout(containerPanelP);
        containerPanelP.setLayout(containerPanelPLayout);
        containerPanelPLayout.setHorizontalGroup(
            containerPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelPLayout.createSequentialGroup()
                .addGap(480, 480, 480)
                .addComponent(lbPictureP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(480, Short.MAX_VALUE))
        );
        containerPanelPLayout.setVerticalGroup(
            containerPanelPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerPanelPLayout.createSequentialGroup()
                .addContainerGap(245, Short.MAX_VALUE)
                .addComponent(lbPictureP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(245, 245, 245))
        );

        viewerPanel.add(containerPanelP);
        containerPanelP.setBounds(0, 0, 1920, 1080);

        lbBackground.setBackground(new java.awt.Color(245, 245, 245));
        lbBackground.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbBackground.setForeground(new java.awt.Color(255, 255, 255));
        lbBackground.setIcon(new ImageIcon("src/main/java/BillboardViewer/images/bg_log_in.png"));
        lbBackground.setOpaque(true);
        lbBackground.setPreferredSize(new java.awt.Dimension(1920, 1080));
        viewerPanel.add(lbBackground);
        lbBackground.setBounds(0, 0, 1920, 1080);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(viewerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1920, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(viewerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        //Press ESC key to close app
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

    }//GEN-LAST:event_formKeyPressed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        //Click button anywhere to escape
        System.exit(0);
    }//GEN-LAST:event_formMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BillboardViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillboardViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillboardViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillboardViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillboardViewer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel containerPanelI;
    private javax.swing.JPanel containerPanelM;
    private javax.swing.JPanel containerPanelMI;
    private javax.swing.JPanel containerPanelMP;
    private javax.swing.JPanel containerPanelMPI;
    private javax.swing.JPanel containerPanelP;
    private javax.swing.JPanel containerPanelPI;
    private javax.swing.JLabel lbBackground;
    private javax.swing.JLabel lbInfoI;
    private javax.swing.JLabel lbInfoMI;
    private javax.swing.JLabel lbInfoMPI;
    private javax.swing.JLabel lbInfoPI;
    private javax.swing.JLabel lbMessageM;
    private javax.swing.JLabel lbMessageMI;
    private javax.swing.JLabel lbMessageMP;
    private javax.swing.JLabel lbMessageMPI;
    private javax.swing.JLabel lbPictureMP;
    private javax.swing.JLabel lbPictureMPI;
    private javax.swing.JLabel lbPictureP;
    private javax.swing.JLabel lbPicturePI;
    private javax.swing.JPanel viewerPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * Get billboard which is currently displayed
     * @return currentBillboard String if there is a billboard is presently scheduled, an error string if there is no billboard
     */
    private String getCurrentBillboardString() {
        try {
            ServerConnection svCon = new ServerConnection();
            CurrentBillboardRequest req = new CurrentBillboardRequest();
            svCon.sendRequest(req);
            Message msg = Message.nextMessageFromSocket(svCon.getChannel(), svCon.getByteBuffer());

            while (!(msg.messageType().equals(EndResponse.class.getSimpleName()))) {
                CurrentBillboardResponse res = (CurrentBillboardResponse) msg;
                currentBillboard.setUserId(res.getUserId());
                currentBillboard.setBackgroundColor(res.getBackgroundColor());
                currentBillboard.setMessageColor(res.getMessageColor());
                currentBillboard.setInformation(res.getInformation());
                currentBillboard.setMessage(res.getMessage());
                currentBillboard.setPicture(res.getPicture());
                currentBillboard.setInfoColor(res.getInfoColor());
                msg = Message.nextMessageFromSocket(svCon.getChannel(), svCon.getByteBuffer());
            }
            EndResponse end = (EndResponse) msg;
            if (Integer.parseInt(end.getMessage()) == 1) {
                return null;
            } else if (Integer.parseInt(end.getMessage()) == 0) {
                return "Nothing to display";
            } else {
                return "Error from server";
            }
        } catch (IOException | ServerClosedException e) {
            return "Server is not ready";
        }

    }

    private static Color hex2Rgb(String colorStr) {
        try {
            return new Color(
                    Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                    Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                    Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
        } catch (Exception e) {
            return new Color(0,0,255);
        }



    }

    private Image createNotFoundImage(){
        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(600, 300, BufferedImage.TYPE_INT_RGB);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();

        // fill all the image with white
        g2d.setColor(Color.red);
        g2d.fillRect(0, 0, 600, 300);

        // create a string with yellow
        g2d.setColor(Color.yellow);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g2d.drawString("Image Not Found", 180, 150);


        // Disposes of this graphics context and releases any system resources that it is using.
        g2d.dispose();
        return bufferedImage;
    }
}
