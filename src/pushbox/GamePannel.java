package pushbox;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GamePannel extends javax.swing.JPanel {
 
    
    /*  old 
    0==null   11== first ball   12==second ball    13==third ball    2==gray    3==green    4==brown    5==yellow
    */
    
    /*   new
    -1 ==null 0==ball  1== second ball  2,3,4,=other ball    22==gray    23==green    24==brown    25==yellow
    */
     
    
    JButton[][] box ;//= new JButton[11][16];
    int[][] boxvalue;//=new int[12][17];
    int ball=0;
    int oldball=0;
    int temp1=0,temp2=0,temp3=0,prevb1i=0,prevb1j=0,prevb2i=0,prevb2j=0,prevb3i=0,prevb3j=0,temp=0,one,two;
    int n=0,leveli,levelj;
   public GamePannel() {
      
        initComponents();
        myinit();
        
    }
   
   public void level1()
   {   
       boxvalue=new int[12][17];
       box=new JButton[11][16];
       leveli=11;
       levelj=16;
       n=3;
       for(int i=1;i<leveli;i++)
           for(int j=1;j<levelj;j++)
           {   
               if(i==1  || i==10 || j==1 || j==15 || j==5 || (j==10 && i!=3) ||( (i==6 && j!=2) && (i==6 && j!=7) && (i==6 && j!=12)) )
                   
               { 
                   boxvalue[i][j]=22;
               }
               else           
               boxvalue[i][j]=-1;
           }
      boxvalue[9][2]=0;
      boxvalue[2][9]=1;
      boxvalue[9][13]=2;
      boxvalue[2][2]=23;
      boxvalue[5][6]=25;
      boxvalue[5][9]=25;
      boxvalue[9][4]=25;
      boxvalue[9][9]=25;
      boxvalue[6][2]=24;
      boxvalue[6][7]=24;
      boxvalue[6][12]=24;
      boxvalue[3][10]=24;
   }
    
    public final void myinit(){
        setSize(820, 620);//our standard game pannel size
        setOpaque(false);//to make buttons transparent
        controlPannel.setBounds(10,55,700,500);
        level1();
        controlPannel.setFocusable(true);
        controlPannel.setOpaque(false);
        controlPannel.setFocusTraversalKeysEnabled(false);
        createBox();//creates boxes     
    }//my init
    
    public void createBox(){
        controlPannel.setLayout(new GridLayout(10,15,0,0));
        for(int i=1;i<leveli;i++)
            for(int j=1;j<levelj;j++){
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
 
    public void drawbox()
    {    
           //JOptionPane.showMessageDialog(null,"");
         for(int i=1;i<leveli;i++)
            for(int j=1;j<levelj;j++){
             if(boxvalue[i][j]==0)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("ball.png")));
              if(boxvalue[i][j]==1)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("ball.png")));
              if(boxvalue[i][j]==2)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("ball.png")));
              if(boxvalue[i][j]==22)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("graybox.png")));
              if(boxvalue[i][j]==23)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("greenbox.png")));
              if(boxvalue[i][j]==24)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("brownbox.png")));
              if(boxvalue[i][j]==25)
                 box[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("yellowbox.png")));
             if(boxvalue[i][j]==-1)
                 box[i][j].setIcon(null);
            }
    }
    public void keyInput(KeyEvent e)
    {
        char input=e.getKeyChar();
      
            if(input=='a' || input=='A')
            {
              
                level1:
                for(int i=1;i<leveli;i++)
                    for(int j=1;j<levelj;j++)
                    {
                        if(boxvalue[i][j-1]!=0 && boxvalue[i][j-1]!=1 && boxvalue[i][j-1]!=2 && boxvalue[i][j-1]!=3 &&boxvalue[i][j-1]!=4 ){
                        if(boxvalue[i][j]==ball && j>1 && boxvalue[i][j-1]!=22 && boxvalue[i][j-1]!=24 )
                        {
                            temp=111;
                            wincheck(i,j-1);
                            if(boxvalue[i][j-1]==25)
                           {
                              if(ball==0)
                              {
                                  temp1++;
                                   prevb1i=i;
                                  prevb1j=j-1;
                              }
                              if(ball==1)
                              {
                                  temp2++;
                                   prevb2i=i;
                                  prevb2j=j-1; 
                              }
                              if(ball==2)
                              {
                                  temp3++;
                                   prevb3i=i;
                                  prevb3j=j-1; 
                              }
                                
                           }
                            boxvalue[i][j]=-1;
                            boxvalue[i][j-1]=ball;
                             level1open(i,j-1);
                         
                            break level1;
                        }
                        }
                       
                        
                    }
               if(temp1==0 && ball ==0 && temp==111 ) prev(prevb1i,prevb1j);
               if(temp2==0 && ball ==1 && temp==111 ) prev(prevb2i,prevb2j);
               if(temp3==0 && ball ==2 && temp==111 ) prev(prevb3i,prevb3j);
                drawbox();
                if(ball==0 && temp==111)  temp1=0;
                if(ball==1 && temp==111)  temp2=0;
                if(ball==2 && temp==111)  temp3=0;
                temp=0;
                 
            }
            if(input=='w' || input=='W')
            {
                  
                level1:
                for(int i=1;i<leveli;i++)
                    for(int j=1;j<levelj;j++)
                    {
                       if(boxvalue[i-1][j]!=0 && boxvalue[i-1][j]!=1 && boxvalue[i-1][j]!=2 && boxvalue[i-1][j]!=3 && boxvalue[i-1][j]!=4 ){
                        if(boxvalue[i][j]==ball && i>1 && boxvalue[i-1][j]!=22 && boxvalue[i-1][j]!=24)
                        {
                            temp=222;
                          if(boxvalue[i-1][j]==25)
                           {
                              if(ball==0)
                              {
                                  temp1++;
                                   prevb1i=i-1;
                                  prevb1j=j;
                              }
                              if(ball==1)
                              {
                                  temp2++;
                                   prevb2i=i-1;
                                  prevb2j=j; 
                              }
                              if(ball==2)
                              {
                                  temp3++;
                                   prevb3i=i-1;
                                  prevb3j=j; 
                              }
                              
                           }
                           wincheck(i-1,j);  
                          boxvalue[i][j]=-1;
                            boxvalue[i-1][j]=ball;
                             level1open(i-1,j);
                            
                            break level1;
                        }}
                        
                    }
               if(temp1==0 && ball ==0 && temp==222 )  prev(prevb1i,prevb1j);
               if(temp2==0 && ball ==1 && temp==222 )  prev(prevb2i,prevb2j); 
               if(temp3==0 && ball ==2 && temp==222 )  prev(prevb3i,prevb3j);
                drawbox();
                if(ball==0 && temp==222)   temp1=0;
                if(ball==1 && temp==222)   temp2=0;
                if(ball==2 && temp==222)   temp3=0;
                temp=0;
                 
            }
            if(input=='s' || input=='S')
            {
                
                level1:
                for(int i=0;i<leveli;i++)
                    for(int j=1;j<levelj;j++)
                    {
                       if(boxvalue[i+1][j]!=0 && boxvalue[i+1][j]!=1 && boxvalue[i+1][j]!=2 && boxvalue[i+1][j]!=3 && boxvalue[i+1][j]!=4 ){
                        if(boxvalue[i][j]==ball && i<10 && boxvalue[i+1][j]!=22 && boxvalue[i+1][j]!=24)
                        {
                            temp=333;
                            if(boxvalue[i+1][j]==25)
                           {
                                if(ball==0)
                              {
                                  temp1++;
                                   prevb1i=i+1;
                                  prevb1j=j;
                              }
                              if(ball==1)
                              {
                                  temp2++;
                                   prevb2i=i+1;
                                  prevb2j=j; 
                              }
                              if(ball==2)
                              {
                                  temp3++;
                                   prevb3i=i+1;
                                  prevb3j=j; 
                              }
                             
                           }
                            wincheck(i+1,j);
                            boxvalue[i][j]=-1;
                            boxvalue[i+1][j]=ball;
                             level1open(i+1,j);
                             
                            break level1;
                        }}
                        
                    }
              
                if(temp1==0 && ball ==0 && temp==333 ) prev(prevb1i,prevb1j);
                if(temp2==0 && ball ==1 && temp==333 )  prev(prevb2i,prevb2j);
                if(temp3==0 && ball ==2 && temp==333 )  prev(prevb3i,prevb3j);
                drawbox();   
                if(ball==0 && temp==333)  temp1=0;
                if(ball==1 && temp==333)  temp2=0;  
                if(ball==2 && temp==333)  temp3=0;
                temp=0;
            }
            if(input=='d' || input=='D')
            { 
                level1:
                for(int i=1;i<leveli;i++)
                    for(int j=1;j<levelj;j++)
                    {
                       if(boxvalue[i][j+1]!=0 && boxvalue[i][j+1]!=1 && boxvalue[i][j+1]!=2 && boxvalue[i][j+1]!=3 && boxvalue[i][j+1]!=4 ){
                        if(boxvalue[i][j]==ball && j<15 && boxvalue[i][j+1]!=22 && boxvalue[i][j+1]!=24)
                        {
                            temp=444;
                            if(boxvalue[i][j+1]==25)
                           {
                                if(ball==0)
                              {
                                  temp1++;
                                   prevb1i=i;
                                  prevb1j=j+1;
                              }
                              if(ball==1)
                              {
                                  temp2++;
                                   prevb2i=i;
                                  prevb2j=j+1; 
                              }
                              if(ball==2)
                              {
                                  temp3++;
                                   prevb3i=i;
                                  prevb3j=j+1; 
                              }
                              
                           }
                            wincheck(i,j+1);
                               boxvalue[i][j]=-1;
                            boxvalue[i][j+1]=ball;
                             level1open(i,j+1);
                             
                            break level1;
                        }}
                    }
                if(temp1==0 && ball ==0 && temp==444 )      prev(prevb1i,prevb1j);
                if(temp2==0 && ball ==1 && temp==444 )      prev(prevb2i,prevb2j);
                if(temp3==0 && ball ==2 && temp==444 )      prev(prevb3i,prevb3j);
                drawbox();
                if(ball==0 && temp==444)    temp1=0;
                if(ball==1 && temp==444)    temp2=0;
                if(ball==2 && temp==444)    temp3=0;
                temp=0;
              
            }
            if(input==KeyEvent.VK_SPACE)  ball=(ball+1)%n;
                               
    }
     public void prev(int i,int j)
     {   
         if(boxvalue[i][j]!=0 && boxvalue[i][j]!=1 && boxvalue[i][j]!=2 && boxvalue[i][j]!=3  )
             boxvalue[i][j]=25;
         
     }
     
    public void level1open(int i, int j){
                    if(ball==0 && i==9 && j==4 ) boxvalue[6][12]=-1;
                    if(ball==0 && (i!=9 || j!=4))boxvalue[6][12]=24;  
                    if(ball==1 && i==5 && j==9) boxvalue[3][10]=-1;
                    if(ball==1 && (i!=5 || j!=9)) boxvalue[3][10]=24;
                    if(ball==2 && i==5 && j==6) boxvalue[6][7]=-1;
                    if(ball==2 &&(i!=5 || j!=6)) boxvalue[6][7]=24;
                    if(ball==1 && i==9 && j==9) boxvalue[6][2]=-1;
                    if(ball==1 && (i!=9 || j!=9)) boxvalue[6][2]=24;
    }
        
    public int  wincheck(int i,int j)
    {
        if(boxvalue[i][j]==23)  JOptionPane.showMessageDialog(null,"Yeah you win");

        return 10;
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
