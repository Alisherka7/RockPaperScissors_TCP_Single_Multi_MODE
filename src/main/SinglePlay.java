/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 *
 * @author alisherka7
 */
public class SinglePlay extends javax.swing.JFrame {
    public double total1, total2;

    private Button kawi, bawi, bo, exit;

    private DataInputStream reader;
    private DataOutputStream writer;

    public static int KAWI = 0;
    public static int BAWI = 1;
    public static int BO = 2;

    public int key=256,  win = 0, loss = 0, draw = 0;

    public String ID;
    public static String name = "Player1";

    Socket socket;

    /**
     * Creates new form multi
     */
    public SinglePlay(String title) throws Exception {
//    	clientStart();
        name = title;
        initComponents();
        playerName.setText(name.toString());
    }
   
    
    private static String host = "localhost";
    private static Integer port = 1340;

    private static String msgRules = "this is msg rulles";

 
    
    void clientStart() throws Exception
    {

    	String input = "";
    	String response;

    	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    	Socket clientSocket = new Socket(SinglePlay.host, SinglePlay.port);
    	DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
    	BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    	do {

    	    if (input.equals("-rules")) {
    		System.out.println(SinglePlay.msgRules);
    	    }

    	    System.out
    		    .println("(S)가위 | (R)바위 | (P)보 ");
    	    input = inFromUser.readLine();

    	} while (!input.equals("S") && !input.equals("R") && !input.equals("P"));

    	// Transmit input to the server and provide some feedback for the user
    	outToServer.writeBytes(input + "\n");
    	System.out
    		.println("\n("
    			+ input
    			+ ") . ..");

    	response = inFromServer.readLine();

    	System.out.println("Response from server: " + response);

    	// Close socket
    	clientSocket.close();

    }
    
    private void checkOption(int player) throws IOException{
        double avg;
        int a = reader.readInt();
        int server = ((a/key)-1);
        if(player == 0)
		{
			msgView.append("You put out scissors.\n");
                        ImageIcon image1 = new ImageIcon("img/sR.png");
                        playerIcon.setIcon(image1);
			
			if(server == 0)
			{
                                ImageIcon image = new ImageIcon("img/sR.png");
                                pcIcon.setIcon(image);
				msgView.append("Computer put out Scissors.\n");
				draw++;
				msgView.append("Draw.\n\n");
                                winLose.setText("Draw");
                                winLose.setForeground(Color.BLACK);
				avg = total2 / total1;
				avg = avg*100;
				msgView.append("Record - " + "win : " + win +" loss : " + loss + " draw : " + draw + "\n");
				msgView.append("WinRate : " + String.format("%.2f",avg) + "%\n\n");
			}
			
			else if(server == 1)
			{
                                ImageIcon image = new ImageIcon("img/rR.png");
                                pcIcon.setIcon(image);
				msgView.append("Computer put out Rock.\n");
				loss++;
				msgView.append("Loss.\n");
                                winLose.setText("You Lose");
                                winLose.setForeground(Color.RED);
				avg = total2 / total1;
				avg = avg*100;
				msgView.append("Record - " + "win : " + win +" loss : " + loss + " draw : " + draw + "\n");
				msgView.append("WinRate : " + String.format("%.2f",avg) + "%\n\n");
			}
			
			else if(server == 2)
			{
                                ImageIcon image = new ImageIcon("img/pR.png");
                                pcIcon.setIcon(image);
				msgView.append("Computer put out Paper.\n");
				win++;
				msgView.append("Win.\n");
                                winLose.setText("You WIN");
                                winLose.setForeground(Color.GREEN);
				total2++;
				avg = total2 / total1;
				avg = avg*100;
				msgView.append("Record - " + "win : " + win +" loss : " + loss + " draw : " + draw + "\n");
				msgView.append("WinRate : " + String.format("%.2f",avg) + "%\n\n");
			}
			
		}
		
		else if(player == 1)
		{
			msgView.append("You put out Rock.\n");
			ImageIcon image1 = new ImageIcon("img/rR.png");
                        playerIcon.setIcon(image1);
			if(server == 0)
			{
                                ImageIcon image = new ImageIcon("img/sR.png");
                                pcIcon.setIcon(image);
				msgView.append("Computer put out Scissors.\n");
				win++;
				msgView.append("Win.\n");
                                winLose.setText("You WIN");
                                winLose.setForeground(Color.GREEN);
				total2++;
				avg = total2 / total1;
				avg = avg*100;
				msgView.append("Record - " + "win : " + win +" loss : " + loss + " draw : " + draw + "\n");
				msgView.append("WinRate : " + String.format("%.2f",avg) + "%\n\n");
				
			}
			
			else if(server == 1)
			{
                                ImageIcon image = new ImageIcon("img/rR.png");
                                pcIcon.setIcon(image);
				msgView.append("Computer put out Rock.\n");
				draw++;
				msgView.append("Draw.\n");
                                winLose.setText("Draw");
                                winLose.setForeground(Color.BLACK);
				avg = total2 / total1;
				avg = avg*100;
				msgView.append("Record - " + "win : " + win +" loss : " + loss + " draw : " + draw + "\n");
				msgView.append("WinRate : " + String.format("%.2f",avg) + "%\n\n");
				
			}
			
			else if(server == 2)
			{
                                ImageIcon image = new ImageIcon("img/pR.png");
                                pcIcon.setIcon(image);
				msgView.append("Computer put out Paper.\n");
				loss++;
				msgView.append("Loss.\n");
                                winLose.setText("You Lose");
                                winLose.setForeground(Color.RED);
				avg = total2 / total1;
				avg = avg*100;
				msgView.append("Record - " + "win : " + win +" loss : " + loss + " draw : " + draw + "\n");
				msgView.append("WinRate : " + String.format("%.2f",avg) + "%\n\n");
			}
		}
		
		else if(player == 2)
		{
			msgView.append("You put out Paper.\n");
                        ImageIcon image1 = new ImageIcon("img/pR.png");
                        playerIcon.setIcon(image1);
			if(server == 0)
			{
                                ImageIcon image = new ImageIcon("img/sR.png");
                                pcIcon.setIcon(image);
				msgView.append("Computer put out Scissors.\n");
				loss++;
				msgView.append("Loss.\n");
                                winLose.setText("You Lose");
                                winLose.setForeground(Color.RED);
				avg = total2 / total1;
				avg = avg*100;
				msgView.append("Record - " + "win : " + win +" loss : " + loss + " draw : " + draw + "\n");
				msgView.append("WinRate : " + String.format("%.2f",avg) + "%\n\n");
				
				
			}
			
			else if(server == 1)
			{
                                ImageIcon image = new ImageIcon("img/rR.png");
                                pcIcon.setIcon(image);
                               
				msgView.append("Computer put out Rock.\n");
				win++;
				msgView.append("Win.\n");
                                winLose.setText("You WIN");
                                winLose.setForeground(Color.GREEN);
				total2++;
				avg = total2 / total1;
				avg = avg*100;
				msgView.append("Record - " + "win : " + win +" loss : " + loss + " draw : " + draw + "\n");
				msgView.append("WinRate : " + String.format("%.2f",avg) + "%\n\n");
			}
			
			else if(server == 2)
			{
                                ImageIcon image = new ImageIcon("img/pR.png");
                                pcIcon.setIcon(image);
				msgView.append("Computer put out Paper.\n");
				draw++;
				msgView.append("Draw.\n");
                                winLose.setText("Draw");
                                winLose.setForeground(Color.BLACK);
				avg = total2 / total1;
				avg = avg*100;
				msgView.append("Record - " + "win : " + win +" loss : " + loss + " draw : " + draw + "\n");
				msgView.append("WinRate : " + String.format("%.2f",avg) + "%\n\n");
			}
		}
    }
    
    
    void connect()
	{
                
		String ch = name;
                System.out.println(ch);
		ID = ch;
                try
                {
                        msgView.append("ID : " + ch + "\n");
                        msgView.append("connecting....\n");
                        socket = new Socket("localhost",1234);
                        msgView.append("Success! Game Start!\n");

                        reader = new DataInputStream(socket.getInputStream());
                        writer = new DataOutputStream(socket.getOutputStream());
                }
                catch(IOException e)
                {
                        msgView.append("Disconneted");
                }
		
		
	}
    
    
    
    public void writerList(String id, double x, double y) 
   {
      double tota = x;
      double tota2 = y;
      
      double result = tota2/tota;
      PrintWriter out = null;
      
      try
      {
         out = new PrintWriter(new FileWriter("Result.txt", true));
         out.write("아이디 : " + id  +" 전적 - " + "승 : " + win +" 패 : " + loss + " 무 : " + draw + " 최종 결과 " + String.format("%.2f",result*100) + "%\n");
         
      }
      
      catch(IOException be)
      {
         System.out.println("출력 에러 발생!");
      }
      
      finally
      {
         if(out != null)
         {
            out.close();
         }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        msgView = new javax.swing.JTextArea();
        s = new javax.swing.JButton();
        r = new javax.swing.JButton();
        p = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        playerName = new javax.swing.JLabel();
        player2 = new javax.swing.JLabel();
        playerIcon = new javax.swing.JLabel();
        pcIcon = new javax.swing.JLabel();
        winLose = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        msgView.setColumns(20);
        msgView.setRows(5);
        jScrollPane1.setViewportView(msgView);

        s.setIcon(new javax.swing.ImageIcon("/Users/alisherka7/NetBeansProjects/tcpNetworkGame/src/main/s.png")); // NOI18N
        s.setText("jButton1");
        s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sActionPerformed(evt);
            }
        });

        r.setIcon(new javax.swing.ImageIcon("/Users/alisherka7/NetBeansProjects/tcpNetworkGame/src/main/r.png")); // NOI18N
        r.setText("jButton1");
        r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rActionPerformed(evt);
            }
        });

        p.setIcon(new javax.swing.ImageIcon("/Users/alisherka7/NetBeansProjects/tcpNetworkGame/src/main/p.png")); // NOI18N
        p.setText("jButton1");
        p.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("싱글 게임");

        playerName.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        playerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerName.setText("Player1");

        player2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        player2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2.setText("컴퓨터");

        playerIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pcIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        winLose.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        winLose.setForeground(new java.awt.Color(102, 102, 102));
        winLose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        winLose.setText("You WIN");

        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(playerName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(player2)
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(r, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(exitButton)
                        .addGap(73, 73, 73)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(playerIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(winLose, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pcIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerName)
                    .addComponent(player2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(winLose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(playerIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pcIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(r, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        try {
            // TODO add your handling code here:
            writer.writeUTF("OK");
            writer.flush();
            total1++;
            checkOption(0);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sActionPerformed

    private void rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rActionPerformed
        try {
            // TODO add your handling code here:
            writer.writeUTF("OK");
            writer.flush();
            total1++;
            checkOption(1);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rActionPerformed

    private void pActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pActionPerformed
        try {
            // TODO add your handling code here:
            writer.writeUTF("OK");
            writer.flush();
            total1++;
            checkOption(2);
        } catch (IOException ex) {
            Logger.getLogger(SinglePlay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_pActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        writerList(ID,total1, total2);
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

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
            java.util.logging.Logger.getLogger(SinglePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SinglePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SinglePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SinglePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SinglePlay sp = new SinglePlay(name);
                    sp.setVisible(false);
                    sp.connect();
                } catch (Exception ex) {
                    Logger.getLogger(SinglePlay.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea msgView;
    private javax.swing.JButton p;
    private javax.swing.JLabel pcIcon;
    private javax.swing.JLabel player2;
    private javax.swing.JLabel playerIcon;
    private javax.swing.JLabel playerName;
    private javax.swing.JButton r;
    private javax.swing.JButton s;
    private javax.swing.JLabel winLose;
    // End of variables declaration//GEN-END:variables
}
