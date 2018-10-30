


import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.Key;
import java.security.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
public class Launcher extends javax.swing.JFrame {

    private final Socket readSocket;
    private static int flag=0;
    private final Socket writeSocket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private PublicKey publicKeyServer;
    private PrivateKey keyRSAPrivate;
    private static Socket socket;
    public final static String UPDATE_USERS = "updateuserslist:";
    public static String sessionUsername = null;
    private Key AESKey,DESKey;
    static int val=1;
     File file;
     String fileName1="E:/java/java project/plain.txt";
    public Launcher(Socket readSocket,Socket writeSocket,Key AESKey,Key DESKey,ObjectInputStream  ois,ObjectOutputStream oos) throws Exception {
        
        initComponents();
        
        this.readSocket = readSocket;
        this.writeSocket = writeSocket;
        this.AESKey = AESKey;
        this.DESKey = DESKey;
        
        this.oos=oos;
        this.ois=ois;
        new Thread(){
            public void run(){
                try {
                    while(true){
                        Message encryptedMessage= (Message) ois.readObject();
                        //int flag=0;
                       // System.out.println("msg recievd");
                       // int flag=(int)ois.readInt();
                       // System.out.println("int rec");
                        String type = encryptedMessage.getType();
                        MessageDecryption mess=null;
                        if(type.equals("AES")){
                            {
                                mess = new MessageDecryption(encryptedMessage.getMessage(),AESKey,type);
                            }
                        }else{
                            mess = new MessageDecryption(encryptedMessage.getMessage(),DESKey,type);
                        }
                        String plainMessageString = mess.getMessage();
                        if(flag==1)
                        {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName1, true));
                            //writer.append(' ');
                            writer.append(plainMessageString+"\n");
                            writer.close();
                            flag=0;
                        }
                        System.out.println(plainMessageString + " FROM using "+encryptedMessage.getType());
                        chatBoxTextArea.append(plainMessageString+"\n");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
        
        

    }
    public void AesMessage() throws FileNotFoundException, IOException
    {
         JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        file = chooser.getSelectedFile();
        String fileName = file.getAbsolutePath();
        System.out.println(fileName);
        String msg;
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        
        while((msg=br.readLine())!=null)
        {
            
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        sendDESBtn = new javax.swing.JButton();
        sendAESBtn = new javax.swing.JButton();
        msgTextField = new javax.swing.JTextField();
        sendFileDESBtn = new javax.swing.JButton();
        sendFileAESBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatBoxTextArea = new javax.swing.JTextArea();
        graphButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headerLabel.setBackground(new java.awt.Color(153, 255, 102));
        headerLabel.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText("Cryptography");
        headerLabel.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(102, 255, 153));

        sendDESBtn.setText("Send (DES)");
        sendDESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendDESBtnActionPerformed(evt);
            }
        });

        sendAESBtn.setText("Send (AES)");
        sendAESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendAESBtnActionPerformed(evt);
            }
        });

        msgTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgTextFieldActionPerformed(evt);
            }
        });

        sendFileDESBtn.setText("Send File (DES)");
        sendFileDESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFileDESBtnActionPerformed(evt);
            }
        });

        sendFileAESBtn.setText("Send File (AES)");
        sendFileAESBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFileAESBtnActionPerformed(evt);
            }
        });

        chatBoxTextArea.setColumns(20);
        chatBoxTextArea.setRows(5);
        jScrollPane1.setViewportView(chatBoxTextArea);

        graphButton.setText("Show on Graph");
        graphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(msgTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendDESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendAESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(sendFileDESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendFileAESBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                        .addComponent(graphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sendAESBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendDESBtn))
                    .addComponent(msgTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendFileDESBtn)
                    .addComponent(sendFileAESBtn)
                    .addComponent(graphButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendFileDESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFileDESBtnActionPerformed
       
        
        JFileChooser chooser = new JFileChooser();
        int values1 = chooser.showOpenDialog(null);
        file = chooser.getSelectedFile();
        val=1;
        if (values1 == JFileChooser.APPROVE_OPTION)
        {
            try {
                 
                System.out.println(file.getPath());
                String fileName = file.getAbsolutePath();
                System.out.println(fileName);
                String msgStr;
                BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                chatBoxTextArea.append("file: "+fileName+" has been sent to another client at time "+new Date());
                while((msgStr=br.readLine())!=null)
                {

                    MessageEncryption messEncryption = new MessageEncryption(msgStr,DESKey,"DES");
                    String encryptedMsgStr = messEncryption.getMessageString();
                    Message encryptedMsg = new Message(encryptedMsgStr,"DES"); 
                    chatBoxTextArea.append(msgStr+" \n");
                    oos.writeObject(encryptedMsg);
                    flag=1;
                  //  oos.writeInt(1);
                }
               /* if(val!=0)
                {
                    File f = new File(fileName1);
                    if(f.exists()){
                            f.delete();
                            try {
                                    f.createNewFile();
                                    
                            } catch (IOException e) {
                                    e.printStackTrace();
                            }
                    }
                    val=0;
                }*/
            } catch (Exception ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }    
        } else if (values1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("No file is selected");
        } else if (values1 == JFileChooser.ERROR_OPTION) {
            System.out.println("Error!");
        } else if (file == null) {
            System.out.println("No File is chosen");
        }
            
    }//GEN-LAST:event_sendFileDESBtnActionPerformed

    private void msgTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_msgTextFieldActionPerformed

    private void sendAESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendAESBtnActionPerformed

        try {
            
            String msgStr = msgTextField.getText();
            chatBoxTextArea.append(msgStr+"\n");
            MessageEncryption messEncryption = new MessageEncryption(msgStr,AESKey,"AES");
            String encryptedMsgStr = messEncryption.getMessageString();
            Message encryptedMsg = new Message(encryptedMsgStr,"AES");
            oos.writeObject(encryptedMsg);
            System.out.println("mes sent");
            //oos.writeInt(0);
            System.out.println("int sent");

        } catch (Exception ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_sendAESBtnActionPerformed

    private void sendDESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendDESBtnActionPerformed
        try {
            String msgStr = msgTextField.getText();
            chatBoxTextArea.append(msgStr+"\n");
            MessageEncryption messEncryption = new MessageEncryption(msgStr,DESKey,"DES");
            String encryptedMsgStr = messEncryption.getMessageString();
            Message encryptedMsg = new Message(encryptedMsgStr,"DES");
            oos.writeObject(encryptedMsg);
           // oos.writeInt(0);
        } catch (Exception ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendDESBtnActionPerformed

    private void graphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphButtonActionPerformed
       
            Frame f = new Frame();
        f.setBounds(200,200,800,300);
        f.add(new Graph());
        f.setResizable(false);
        f.setVisible(true);
        
        
    }//GEN-LAST:event_graphButtonActionPerformed

    private void sendFileAESBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFileAESBtnActionPerformed
       
        //String fileName=fileName.replace("\" , '/');
        JFileChooser chooser = new JFileChooser();
        int values1 = chooser.showOpenDialog(null);
        file = chooser.getSelectedFile();
        val=1;
        if (values1 == JFileChooser.APPROVE_OPTION)
        {
            try {
                 
                System.out.println(file.getPath());
                String fileName = file.getAbsolutePath();
                System.out.println(fileName);
                String msgStr;
                BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                chatBoxTextArea.append("file: "+fileName+" has been sent to another client at time "+new Date());
                while((msgStr=br.readLine())!=null)
                {

                    MessageEncryption messEncryption = new MessageEncryption(msgStr,AESKey,"AES");
                    String encryptedMsgStr = messEncryption.getMessageString();
                    Message encryptedMsg = new Message(encryptedMsgStr,"AES"); 
                    chatBoxTextArea.append(msgStr+" \n");
                    flag=1;
                    oos.writeObject(encryptedMsg);
                    
                  // oos.writeInt(1);
                   // System.out.println("int sent");
                }
               /* if(val!=0)
                {
                    File f = new File(fileName1);
                    if(f.exists()){
                            f.delete();
                            try {
                                    f.createNewFile();
                                    
                            } catch (IOException e) {
                                    e.printStackTrace();
                            }
                    }
                    val=0;
                }*/
            } catch (Exception ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }    
        } else if (values1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("No file is selected");
        } else if (values1 == JFileChooser.ERROR_OPTION) {
            System.out.println("Error!");
        } else if (file == null) {
            System.out.println("No File is chosen");
        }
            
    }//GEN-LAST:event_sendFileAESBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatBoxTextArea;
    private javax.swing.JButton graphButton;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField msgTextField;
    private javax.swing.JButton sendAESBtn;
    private javax.swing.JButton sendDESBtn;
    private javax.swing.JButton sendFileAESBtn;
    private javax.swing.JButton sendFileDESBtn;
    // End of variables declaration//GEN-END:variables
}
