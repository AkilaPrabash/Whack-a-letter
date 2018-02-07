
package whackamole;


import javax.swing.*;
import java.sql.*;



public class jframe extends javax.swing.JFrame{
    
    protected int score;
    protected char currChar;
    public int lettCount;
    protected Thread updateLab;
    public JLabel prevLab;
    public JLabel currLab;
    protected char pressed;
    protected int missCount;
    protected boolean status = true;
    protected int sleepTime = 3000;
    protected int highScore;
    
    private String dbHost = "jdbc:mysql://localhost:3306/HScore";
    private String user = "root";
    private String pass = "";
    
    
    protected void database(){
        try{
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con = DriverManager.getConnection(dbHost, user, pass);
        Statement stmt=con.createStatement();  
        ResultSet rs=stmt.executeQuery("select max(Score) from highScore"); 
        rs.next();
        highScore = rs.getInt(1);
        if(score<highScore){
            
            jLabel2.setText("Highest Score:" + " " + highScore);
        }
        
        }catch(Exception e){ 
            System.out.println(e);
        } 
    }
    
       void UpLab(){
           updateLab = new Thread(new Runnable(){
                            public void run(){
                                Runnable run =new Runnable(){                
                                    public void run() {
                                            missCount();
                                            database();
                                            labelUpdate();
                                           
                                            
                                    }
                                };
                                while (status){
                                       
                                        lettCount+=1;
                                        
                                        javax.swing.SwingUtilities.invokeLater(run);
                                        
                                        
                                        upHS();
                                        try{
                                            
                                            Thread.sleep(2000);
                                            checkSleep();
                                            updateScore();
                                            resetLabel();
                                            jLabel1.setText("Score:" + " " + String.valueOf(score));
                                            
                                        } catch (InterruptedException ex) {  
                                        return;
                                    }
                                }   
                            }
                    }
            );
            
           updateLab.start();
        }
    
    protected void upDb(){
        
        try{
        Class.forName("com.mysql.jdbc.Driver");  
        Connection con = DriverManager.getConnection(dbHost, user, pass);
        Statement stmt=con.createStatement();  
        stmt.executeUpdate("delete from highScore");
        stmt.executeUpdate("insert into highScore(Score) value('"+highScore+"')");
        
        
        }catch(Exception e){ 
            System.out.println(e);
        } 
        
    }
    
    public synchronized void missCount(){
        if(missCount>2){
        status = false;
        try{
           
        }catch(Exception e){ 
            System.out.println(e);
        } 
        int dButton = JOptionPane.YES_NO_OPTION;
        int dResult =JOptionPane.showConfirmDialog (null, "Oops you missed too many letters","Wanna restart?",dButton);

        if(dResult == JOptionPane.YES_OPTION){
           
           score = 0;
           missCount = 0;
           this.dispose();
           new jframe();
           
           //updateLab.start();
           
        }
        if(dResult == JOptionPane.NO_OPTION){ 
           score = 0;
           System.exit(0);

        }       
        
        }
    
    } 
    
    
    protected void checkSleep(){
    
        if(sleepTime > 600){
            
            sleepTime-=200;
            
        }
        
    }
    
    
    public char pCurrChar(){
        
        return currChar;
    }
    
    public void updateScore(){
    
        if(pressed == currChar){
            
            score+=10;
            
        }
        else{
            
            missCount+=1;  
        }
    }
    
    protected void upHS(){
        
        if(score>highScore){
            
            highScore = score;
            jLabel2.setText("Highest Score:" + " " + highScore);
            upDb();
        }
        
    }
    
    public jframe(){
        initComponents();
        UpLab();
        
    }
    
    public void resetLabel() {
            currLab.setText("");
    }
    
    
    
    public void labelUpdate(){
        JLabel[] labels = {jL1_1, jL1_2, jL1_3, jL1_4, jL1_5, jL2_1, jL2_2, jL2_3, jL2_4, jL2_5, jL3_1, jL3_2, jL3_3, jL3_4, jL3_5, jL4_1, jL4_2, jL4_3, jL4_4, jL4_5, jL5_1 ,jL5_2, jL5_3, jL5_4, jL5_5, jL6_1, jL6_2, jL6_3, jL6_4, jL6_5};
        
        Location l1 = new Location(2,5);
        currLab = labels[l1.inRand()];
        currChar = checkChar(); 
            
        currLab.setText(String.valueOf(currChar));
        
    }
    
    public char checkChar(){
        Character c1 = new Character(2,5);
        return c1.getChar();
        
    }
     
    public void initComponents() {
        
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jL1_1 = new javax.swing.JLabel();
        jL1_2 = new javax.swing.JLabel();
        jL1_3 = new javax.swing.JLabel();
        jL1_4 = new javax.swing.JLabel();
        jL1_5 = new javax.swing.JLabel();
        jL2_1 = new javax.swing.JLabel();
        jL2_2 = new javax.swing.JLabel();
        jL2_3 = new javax.swing.JLabel();
        jL2_4 = new javax.swing.JLabel();
        jL2_5 = new javax.swing.JLabel();
        jL3_1 = new javax.swing.JLabel();
        jL3_2 = new javax.swing.JLabel();
        jL3_3 = new javax.swing.JLabel();
        jL3_4 = new javax.swing.JLabel();
        jL3_5 = new javax.swing.JLabel();
        jL4_1 = new javax.swing.JLabel();
        jL4_2 = new javax.swing.JLabel();
        jL4_3 = new javax.swing.JLabel();
        jL4_4 = new javax.swing.JLabel();
        jL4_5 = new javax.swing.JLabel();
        jL5_1 = new javax.swing.JLabel();
        jL5_2 = new javax.swing.JLabel();
        jL5_3 = new javax.swing.JLabel();
        jL5_4 = new javax.swing.JLabel();
        jL5_5 = new javax.swing.JLabel();
        jL6_1 = new javax.swing.JLabel();
        jL6_2 = new javax.swing.JLabel();
        jL6_3 = new javax.swing.JLabel();
        jL6_4 = new javax.swing.JLabel();
        jL6_5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        
       jL1_1.setFocusable(true);
        jL1_1.addKeyListener(new KeyEv(this));
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Whack A Letter");
        setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new java.awt.BorderLayout());
        setVisible(true);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Score: 0");
        jLabel1.setMinimumSize(new java.awt.Dimension(38, 30));
        jLabel1.setPreferredSize(new java.awt.Dimension(550, 80));
        jPanel2.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(546, 581));
        jPanel1.setPreferredSize(new java.awt.Dimension(546, 550));
        jPanel1.setLayout(new java.awt.GridLayout(6, 6, 1, 1));

        jL1_1.setBackground(new java.awt.Color(0, 51, 204));
        jL1_1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jL1_1.setForeground(new java.awt.Color(102, 102, 102));
        jL1_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        jL1_1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL1_1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL1_1);

        jL1_2.setBackground(new java.awt.Color(0, 51, 153));
        jL1_2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL1_2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL1_2);

        jL1_3.setBackground(new java.awt.Color(0, 51, 153));
        jL1_3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL1_3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL1_3);

        jL1_4.setBackground(new java.awt.Color(0, 51, 153));
        jL1_4.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL1_4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL1_4);

        jL1_5.setBackground(new java.awt.Color(0, 51, 153));
        jL1_5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL1_5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL1_5);

        jL2_1.setBackground(new java.awt.Color(0, 51, 153));
        jL2_1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL2_1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL2_1);

        jL2_2.setBackground(new java.awt.Color(0, 51, 153));
        jL2_2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL2_2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL2_2);

        jL2_3.setBackground(new java.awt.Color(0, 51, 153));
        jL2_3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL2_3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL2_3);

        jL2_4.setBackground(new java.awt.Color(0, 51, 153));
        jL2_4.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL2_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL2_4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL2_4);

        jL2_5.setBackground(new java.awt.Color(0, 51, 153));
        jL2_5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL2_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL2_5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL2_5);

        jL3_1.setBackground(new java.awt.Color(0, 51, 153));
        jL3_1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL3_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL3_1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL3_1);

        jL3_2.setBackground(new java.awt.Color(0, 51, 153));
        jL3_2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL3_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL3_2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL3_2);

        jL3_3.setBackground(new java.awt.Color(0, 51, 153));
        jL3_3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL3_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL3_3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL3_3);

        jL3_4.setBackground(new java.awt.Color(0, 51, 153));
        jL3_4.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL3_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL3_4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL3_4);
        
        jL3_5.setBackground(new java.awt.Color(0, 51, 153));
        jL3_5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL3_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL3_5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.add(jL3_5);
        jL4_1.setBackground(new java.awt.Color(0, 51, 153));
        jL4_1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL4_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL4_1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL4_1);

        jL4_2.setBackground(new java.awt.Color(0, 51, 153));
        jL4_2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL4_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL4_2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jL4_2);

        jL4_3.setBackground(new java.awt.Color(0, 51, 153));
        jL4_3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL4_3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL4_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL4_3);
        
        jL4_4.setBackground(new java.awt.Color(0, 51, 153));
        jL4_4.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL4_4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL4_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL4_4);
        
        jL4_5.setBackground(new java.awt.Color(0, 51, 153));
        jL4_5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL4_5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL4_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL4_5);
        
        jL5_1.setBackground(new java.awt.Color(0, 51, 153));
        jL5_1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL5_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL5_1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL5_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL5_1);

        jL5_2.setBackground(new java.awt.Color(0, 51, 153));
        jL5_2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL5_2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL5_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL5_2);

        jL5_3.setBackground(new java.awt.Color(0, 51, 153));
        jL5_3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL5_3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL5_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL5_3);

        jL5_4.setBackground(new java.awt.Color(0, 51, 153));
        jL5_4.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL5_4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL5_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL5_4);

        jL5_5.setBackground(new java.awt.Color(0, 51, 153));
        jL5_5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL5_5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL5_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL5_5);

        jL6_1.setBackground(new java.awt.Color(0, 51, 153));
        jL6_1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL6_1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL6_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL6_1);
        
        jL6_2.setBackground(new java.awt.Color(0, 51, 153));
        jL6_2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL6_2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL6_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL6_2);
        
        jL6_3.setBackground(new java.awt.Color(0, 51, 153));
        jL6_3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL6_3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL6_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL6_3);
        
        jL6_4.setBackground(new java.awt.Color(0, 51, 153));
        jL6_4.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL6_4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL6_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL6_4);

        jL6_5.setBackground(new java.awt.Color(0, 51, 153));
        jL6_5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jL6_5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jL6_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jL6_5);

        jPanel2.add(jPanel1, java.awt.BorderLayout.CENTER);

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Highest Score: ");
        jPanel2.add(jLabel2, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);
      
       // setVisible(true);
        pack();
        setLocationRelativeTo(null);

       
        
        
        
    }      
    
    
    protected javax.swing.JLabel jL1_1;
    protected javax.swing.JLabel jL1_2;
    protected javax.swing.JLabel jL1_3;
    protected javax.swing.JLabel jL1_4;
    protected javax.swing.JLabel jL1_5;
    protected javax.swing.JLabel jL2_1;
    protected javax.swing.JLabel jL2_2;
    protected javax.swing.JLabel jL2_3;
    protected javax.swing.JLabel jL2_4;
    protected javax.swing.JLabel jL2_5;
    protected javax.swing.JLabel jL3_1;
    protected javax.swing.JLabel jL3_2;
    protected javax.swing.JLabel jL3_3;
    protected javax.swing.JLabel jL3_4;
    protected javax.swing.JLabel jL3_5;
    protected javax.swing.JLabel jL4_1;
    protected javax.swing.JLabel jL4_2;
    protected javax.swing.JLabel jL4_3;
    protected javax.swing.JLabel jL4_4;
    protected javax.swing.JLabel jL4_5;
    protected javax.swing.JLabel jL5_1;
    protected javax.swing.JLabel jL5_2;
    protected javax.swing.JLabel jL5_3;
    protected javax.swing.JLabel jL5_4;
    protected javax.swing.JLabel jL5_5;
    protected javax.swing.JLabel jL6_1;
    protected javax.swing.JLabel jL6_2;
    protected javax.swing.JLabel jL6_3;
    protected javax.swing.JLabel jL6_4;
    protected javax.swing.JLabel jL6_5;
    protected javax.swing.JLabel jLabel1;
    protected javax.swing.JLabel jLabel2;
    protected javax.swing.JPanel jPanel1;
    protected javax.swing.JPanel jPanel2;
    // End of variables declaration   
    public void setLabel(){
      
    }
    
     


}        

