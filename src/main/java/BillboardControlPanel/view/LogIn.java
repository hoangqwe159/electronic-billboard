package BillboardControlPanel.view;


import Commmunication.Requests.LoginRequest;
import ServerConnection.Exceptions.ServerClosedException;
import ServerConnection.ServerConnection;
import Commmunication.Message;
import Commmunication.Requests.CheckTokenRequest;
import Commmunication.Response.CheckTokenResponse;
import Commmunication.Response.LoginResponse;
import Utils.Hash.Hash;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class LogIn extends javax.swing.JFrame {

    /**
     * Creates new form LogIn
     */
    private String token;

    public String getToken() {
        return token;
    }

    public LogIn() {
        try {
            //Connect to the server and send request
            CheckTokenRequest ckToken = new CheckTokenRequest();
            ServerConnection svCon = new ServerConnection();
            svCon.sendRequest(ckToken);
            Message msg = Message.nextMessageFromSocket(svCon.getChannel(), svCon.getByteBuffer());
            // Get response from server
            CheckTokenResponse response = (CheckTokenResponse) msg;
            token = response.getMessage();
            if (!response.isSuccessful()) {
                initComponents();
            } else {
                Home home = new Home(response.getMessage());
                home.setVisible(true);
            }

        }
        catch (IOException | ServerClosedException e) {
            // Display error page if server is not ready
            ErrorPage errorPage = new ErrorPage();
            errorPage.setVisible(true);
        }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginPanel = new javax.swing.JPanel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lbPassword = new javax.swing.JLabel();
        lbIconLogin = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        lbClose = new javax.swing.JLabel();
        lbUsername = new javax.swing.JLabel();
        lbAppName = new javax.swing.JLabel();
        lbBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        loginPanel.setBackground(new java.awt.Color(255, 255, 255));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        loginPanel.setMinimumSize(new java.awt.Dimension(1920, 1080));
        loginPanel.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        loginPanel.setLayout(null);

        txtUsername.setBackground(new Color(0,0,0,0));
        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));
        txtUsername.setCaretColor(new java.awt.Color(255, 255, 255));
        txtUsername.setOpaque(false);
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsernameKeyPressed(evt);
            }
        });
        loginPanel.add(txtUsername);
        txtUsername.setBounds(710, 550, 500, 40);

        txtPassword.setBackground(new Color(0,0,0,0));
        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));
        txtPassword.setCaretColor(new java.awt.Color(255, 255, 255));
        txtPassword.setOpaque(false);
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });
        loginPanel.add(txtPassword);
        txtPassword.setBounds(710, 680, 500, 40);

        lbPassword.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbPassword.setForeground(new java.awt.Color(204, 204, 204));
        lbPassword.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbPassword.setText("password");
        loginPanel.add(lbPassword);
        lbPassword.setBounds(710, 650, 110, 30);

        lbIconLogin.setFont(new java.awt.Font("MV Boli", 0, 60)); // NOI18N
        lbIconLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIconLogin.setIcon(new javax.swing.ImageIcon("src/main/java/BillboardControlPanel/view/images/icons8_user_90px_4.png")); // NOI18N
        loginPanel.add(lbIconLogin);
        lbIconLogin.setBounds(900, 400, 80, 70);

        btnLogin.setBackground(new java.awt.Color(68, 118, 185));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Log in");
        btnLogin.setBorder(new javax.swing.border.MatteBorder(null));
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLogin.setOpaque(false);
        btnLogin.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnLogin.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        loginPanel.add(btnLogin);
        btnLogin.setBounds(840, 770, 220, 50);

        lbClose.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbClose.setForeground(new java.awt.Color(54, 33, 89));
        lbClose.setText("X");
        lbClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbCloseMousePressed(evt);
            }
        });
        loginPanel.add(lbClose);
        lbClose.setBounds(1880, 10, 16, 32);

        lbUsername.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbUsername.setForeground(new java.awt.Color(204, 204, 204));
        lbUsername.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbUsername.setText("username");
        loginPanel.add(lbUsername);
        lbUsername.setBounds(710, 520, 110, 30);

        lbAppName.setFont(new java.awt.Font("MV Boli", 0, 60)); // NOI18N
        lbAppName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAppName.setText("BBCreator");
        loginPanel.add(lbAppName);
        lbAppName.setBounds(800, 130, 320, 110);

        lbBackground.setBackground(new java.awt.Color(245, 245, 245));
        lbBackground.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbBackground.setForeground(new java.awt.Color(255, 255, 255));
        lbBackground.setIcon(new javax.swing.ImageIcon("src/main/java/BillboardControlPanel/view/images/bg_log_in.png")); // NOI18N
        lbBackground.setPreferredSize(new java.awt.Dimension(1920, 1080));
        loginPanel.add(lbBackground);
        lbBackground.setBounds(0, 0, 1920, 1080);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1920, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        onClickLogin();

    }//GEN-LAST:event_btnLoginActionPerformed

    private void lbCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMousePressed
        System.exit(0);
    }//GEN-LAST:event_lbCloseMousePressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // Press ENTER to log in
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            onClickLogin();
        }
    }//GEN-LAST:event_formKeyPressed

    private void txtUsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            onClickLogin();
        }
    }//GEN-LAST:event_txtUsernameKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            onClickLogin();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lbAppName;
    private javax.swing.JLabel lbBackground;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbIconLogin;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    /**
     * Send log in request to server
     */
    private void onClickLogin() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        // Send hashed string to the server
        LoginRequest lgRequest = new LoginRequest();
        lgRequest.setUserName(username);
        try{
            lgRequest.setPassWord(Hash.hashString(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        try{
            // Connect to the server
            ServerConnection svCon = new ServerConnection();
            svCon.sendRequest(lgRequest);
            Message msg = Message.nextMessageFromSocket(svCon.getChannel(), svCon.getByteBuffer());
            // Get response message
            LoginResponse lgResponse = (LoginResponse) msg;
            if(lgResponse.getMessage() != null && lgResponse.getMessage().equals("Invalid Credentials")){
                JOptionPane.showMessageDialog(loginPanel, "Invalid Credentials");

            }else{
                String token = lgResponse.getMessage();
                Home homePage = new Home(token);
                homePage.setVisible(true);
                this.setVisible(false);
            }

        }catch (IOException | ServerClosedException e) {
            JOptionPane.showMessageDialog(loginPanel, e.getMessage());
            System.exit(0);
        }
    }
}
