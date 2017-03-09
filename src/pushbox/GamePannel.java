package pushbox;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GamePannel extends javax.swing.JPanel {
    Border darkborder = new LineBorder(Color.DARK_GRAY, 1);
    Border lightborder = new LineBorder(Color.lightGray, 1);
    Border nullborder = new LineBorder(Color.darkGray, 0);   
    
    /*
    0==null
    11== first ball
    12==second ball
    13==third ball
    2==gray
    3==green
    4==brown
    5==yellow
    */
    
    JButton[][] box = new JButton[11][16];
    int[][] boxvalue=new int[11][16];
    int ball=11;
    int oldball=11;
    int temp1=0,temp2=0,temp3=0,prevb1i=0,prevb1j=0,prevb2i=0,prevb2j=0,prevb3i=0,prevb3j=0,temp=0;
   public GamePannel() {
      
        initComponents();
        myinit();
        
    }//constructor
   //int previ=0,prevj=0;
   public void level1()
   {
       for(int i=1;i<11;i++)
           for(int j=1;j<16;j++)
           {   
               if(i==1  || i==10 || j==1 || j==15 || j==5 || (j==10 && i!=3) ||( (i==6 && j!=2) && (i==6 && j!=7) && (i==6 && j!=12)) )
                   
               { 
                   boxvalue[i][j]=2;
               }
               else           
               boxvalue[i][j]=0;
           }
      boxvalue[9][2]=11;
      boxvalue[2][9]=12;
      boxvalue[9][13]=13;
      boxvalue[2][2]=3;
      boxvalue[5][6]=5;
      boxvalue[5][9]=5;
      boxvalue[9][4]=5;
      boxvalue[9][9]=5;
      boxvalue[6][2]=4;
      boxvalue[6][7]=4;
      boxvalue[6][12]=4;
      boxvalue[3][10]=4;
   }
    
    public final void myinit(){
        setSize(820, 620);//our standard game pannel size
        setOpaque(false);//to make buttons transparent
        
        controlPannel.setBounds(10,55,700,500);//ratio 585:495 :: 13:11
       // controlPannel.setLayout(card);
        level1();
               controlPannel.setFocusable(true);
        controlPannel.setOpaque(false);//to make buttons transparent
        controlPannel.setFocusTraversalKeysEnabled(false);
              
        createBox();//creates boxes
       
        
    }//my init
    public void createBox(){
        controlPannel.setLayout(new GridLayout(10,15,0,0));
        for(int i=1;i<11;i++)
            for(int j=1;j<16;j++){
                box[i][j]=new JButton();
                box[i][j].setSize(45,45);
                box[i][j].setBackground(Color.white);
                controlPannel.add(box[i][j]);
            }
        controlPannel.grabFocus();
        controlPannel.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                keyInput(e);
            }
        }); 
     
        drawbox();
    }
    public void level1open(int i, int j)
    {
        if(ball==11 && i==9 && j==4 ) boxvalue[6][12]=0;
        if(ball==11 && (i!=9 || j!=4))boxvalue[6][12]=4;  
        if(ball==12 && i==5 && j==9) boxvalue[3][10]=0;
        if(ball==12 && (i!=5 || j!=9)) boxvalue[3][10]=4;
        if(ball==13 && i==5 && j==6) boxvalue[6][7]=0;
        if(ball==13 &&(i!=5 || j!=6)) boxvalue[6][7]=4;
        if(ball==12 && i==9 && j==9) boxvalue[6][2]=0;
        if(ball==12 && (i!=9 || j!=9)) boxvalue[6][2]=4;

    }
    public int  wincheck(int i,int j)
    {
       
        System.out.println("hello world");
        if(boxvalue[i][j]==3)
        {
            JOptionPane.showMessageDialog(null,"Yeah you win");
                    System.out.println("inside");
        }
        return 10;
    }
    public void drawbox()
    {    
           //JOptionPane.showMessageDialog(null,"");
         for(int i=1;i<11;i++)
            for(int j=1;j<16;j++){
             if(boxvalue[i][j]==11)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("ball.png")));
              if(boxvalue[i][j]==12)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("ball.png")));
              if(boxvalue[i][j]==13)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("ball.png")));
              if(boxvalue[i][j]==2)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("graybox.png")));
              if(boxvalue[i][j]==3)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("greenbox.png")));
              if(boxvalue[i][j]==4)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("brownbox.png")));
              if(boxvalue[i][j]==5)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("yellowbox.png")));
             if(boxvalue[i][j]==0)
                 box[i][j].setIcon(null);
            }
    }
    public void keyInput(KeyEvent e)
    {
        char input=e.getKeyChar();
            if(input=='a' || input=='A')
            {
              
                level1:
                for(int i=1;i<11;i++)
                    for(int j=1;j<16;j++)
                    {
                        
                        if(boxvalue[i][j]==ball && j>1 && boxvalue[i][j-1]!=2 && boxvalue[i][j-1]!=4)
                        {
                            temp=111;
                           if(boxvalue[i][j-1]==5)
                           {
                              if(ball==11)
                              {
                                  temp1++;
                                   prevb1i=i;
                                  prevb1j=j-1;
                              }
                              if(ball==12)
                              {
                                  temp2++;
                                   prevb2i=i;
                                  prevb2j=j-1; 
                              }
                              if(ball==13)
                              {
                                  temp3++;
                                   prevb3i=i;
                                  prevb3j=j-1; 
                              }
                                
                           }
                            boxvalue[i][j]=0;
                            boxvalue[i][j-1]=ball;
                         level1open(i,j-1);
                         int result= wincheck(i,j-1);
                            break level1;
                        }
                       
                        
                    }
               if(temp1==0 && ball ==11 && temp==111) prev(prevb1i,prevb1j);
               if(temp2==0 && ball ==12 && temp==111) prev(prevb2i,prevb2j);
               if(temp3==0 && ball ==13 && temp==111) prev(prevb3i,prevb3j);
                drawbox();
                if(ball==11 && temp==111)  temp1=0;
                if(ball==12 && temp==111)  temp2=0;
                if(ball==13 && temp==111)  temp3=0;
                temp=0;
                 
            }
            if(input=='w' || input=='W')
            {
                  
                level1:
                for(int i=1;i<11;i++)
                    for(int j=1;j<16;j++)
                    {
                       
                        if(boxvalue[i][j]==ball && i>1 && boxvalue[i-1][j]!=2 && boxvalue[i-1][j]!=4)
                        {
                            temp=222;
                          if(boxvalue[i-1][j]==5)
                           {
                              if(ball==11)
                              {
                                  temp1++;
                                   prevb1i=i-1;
                                  prevb1j=j;
                              }
                              if(ball==12)
                              {
                                  temp2++;
                                   prevb2i=i-1;
                                  prevb2j=j; 
                              }
                              if(ball==13)
                              {
                                  temp3++;
                                   prevb3i=i-1;
                                  prevb3j=j; 
                              }
                              
                           }
                            boxvalue[i][j]=0;
                            boxvalue[i-1][j]=ball;
                             level1open(i-1,j);
                             wincheck(i-1,j);
                            break level1;
                        }
                        
                    }
               if(temp1==0 && ball ==11 && temp==222)  prev(prevb1i,prevb1j);
               if(temp2==0 && ball ==12 && temp==222)  prev(prevb2i,prevb2j); 
               if(temp3==0 && ball ==13 && temp==222)  prev(prevb3i,prevb3j);
                drawbox();
                if(ball==11 && temp==222)   temp1=0;
                if(ball==12 && temp==222)   temp2=0;
                if(ball==13 && temp==222)   temp3=0;
                temp=0;
                 
            }
            if(input=='s' || input=='S')
            {
                
                level1:
                for(int i=10;i>0;i--)
                    for(int j=1;j<16;j++)
                    {
                       
                        if(boxvalue[i][j]==ball && i<10 && boxvalue[i+1][j]!=2 && boxvalue[i+1][j]!=4)
                        {
                            temp=333;
                            if(boxvalue[i+1][j]==5)
                           {
                                if(ball==11)
                              {
                                  temp1++;
                                   prevb1i=i+1;
                                  prevb1j=j;
                              }
                              if(ball==12)
                              {
                                  temp2++;
                                   prevb2i=i+1;
                                  prevb2j=j; 
                              }
                              if(ball==13)
                              {
                                  temp3++;
                                   prevb3i=i+1;
                                  prevb3j=j; 
                              }
                             
                           }
                            boxvalue[i][j]=0;
                            boxvalue[i+1][j]=ball;
                             level1open(i+1,j);
                             wincheck(i+1,j);
                            break level1;
                        }
                        
                    }
              
                if(temp1==0 && ball ==11 && temp==333) prev(prevb1i,prevb1j);
                if(temp2==0 && ball ==12 && temp==333)  prev(prevb2i,prevb2j);
                if(temp3==0 && ball ==13 && temp==333)  prev(prevb3i,prevb3j);
                drawbox();   
                if(ball==11 && temp==333)  temp1=0;
                if(ball==12 && temp==333)  temp2=0;  
                if(ball==13 && temp==333)  temp3=0;
                temp=0;
            }
            if(input=='d' || input=='D')
            { 
                level1:
                for(int i=1;i<11;i++)
                    for(int j=1;j<16;j++)
                    {
                       
                        if(boxvalue[i][j]==ball && j<15 && boxvalue[i][j+1]!=2 && boxvalue[i][j+1]!=4)
                        {
                            temp=444;
                            if(boxvalue[i][j+1]==5)
                           {
                                if(ball==11)
                              {
                                  temp1++;
                                   prevb1i=i;
                                  prevb1j=j+1;
                              }
                              if(ball==12)
                              {
                                  temp2++;
                                   prevb2i=i;
                                  prevb2j=j+1; 
                              }
                              if(ball==13)
                              {
                                  temp3++;
                                   prevb3i=i;
                                  prevb3j=j+1; 
                              }
                              
                           }
                               boxvalue[i][j]=0;
                            boxvalue[i][j+1]=ball;
                             level1open(i,j+1);
                             wincheck(i,j+1);
                            break level1;
                        }
                    }
                if(temp1==0 && ball ==11 && temp==444)      prev(prevb1i,prevb1j);
                if(temp2==0 && ball ==12 && temp==444)      prev(prevb2i,prevb2j);
                if(temp3==0 && ball ==13 && temp==444)      prev(prevb3i,prevb3j);
                drawbox();
                if(ball==11 && temp==444)    temp1=0;
                if(ball==12 && temp==444)    temp2=0;
                if(ball==13 && temp==444)    temp3=0;
                temp=0;
              
            }
            if(input==KeyEvent.VK_SPACE)
            {
               switch (ball)
               {
                   case 11:
                   {
                       oldball=11;
                       ball=12;
                       break;
                   }
                   case 12:
                   {
                       oldball=12;
                       ball=13;
                       break;
                   }
                   case 13:
                   {
                       oldball=13;
                       ball=11;
                       break;
                   }
               }
                   
            }
            
    }
     public void prev(int i,int j)
     {
         boxvalue[i][j]=5;
         
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPannel = new javax.swing.JPanel();
        background_Image = new javax.swing.JLabel();

        setLayout(null);

        controlPannel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controlPannelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                controlPannelMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout controlPannelLayout = new javax.swing.GroupLayout(controlPannel);
        controlPannel.setLayout(controlPannelLayout);
        controlPannelLayout.setHorizontalGroup(
            controlPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        controlPannelLayout.setVerticalGroup(
            controlPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(controlPannel);
        controlPannel.setBounds(20, 30, 585, 495);
        controlPannel.getAccessibleContext().setAccessibleName("");

        background_Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pushbox/vishiGamePanel.jpg"))); // NOI18N
        add(background_Image);
        background_Image.setBounds(0, 0, 820, 620);
    }// </editor-fold>//GEN-END:initComponents

    private void controlPannelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_controlPannelMouseClicked
controlPannel.grabFocus();
// TODO add your handling code here:
    }//GEN-LAST:event_controlPannelMouseClicked

    private void controlPannelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_controlPannelMouseEntered
      controlPannel.grabFocus();  // TODO add your handling code here:
    }//GEN-LAST:event_controlPannelMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background_Image;
    private javax.swing.JPanel controlPannel;
    // End of variables declaration//GEN-END:variables
}
