package chess2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
class gui1 extends JFrame {

    piece p;
    piece[][] ele = new piece[9][9];
    king wk, bk;
    queen wq, bq;
    rook wr, br;
    knight wkn, bkn;
    bishop wb, bb;
    pawn wp, bp;

    ImageIcon WR, WKN, WB, WK, WQ, WP, BR, BKN, BB, BK, BQ, BP;

    JFrame Frame = new JFrame();
    JPanel Panel1 = new JPanel();
    JPanel Panel2 = new JPanel();

    ButtonHandler butlstnr;
    JButton[][] Buttons;
    JButton undo;
    JLabel prompt;
    
    ArrayList<String> al = new ArrayList(0);
    ArrayList<piece> un = new ArrayList(0);
    ArrayList<String> col = new ArrayList(0);

    int flag = 1, red = 0, ac = 0,bt=1;

    String castle;
    private Object panel2;

    public gui1() {

        wk = new king("white");
        bk = new king("black");
        wq = new queen("white");
        bq = new queen("black");
        wr = new rook("white");
        br = new rook("black");
        wkn = new knight("white");
        bkn = new knight("black");
        wb = new bishop("white");
        bb = new bishop("black");
        wp = new pawn("white");
        bp = new pawn("black");

        int x, y, z;
        String c = "abcdefghi";
        Panel1.setLayout(new GridLayout(9, 9));
        Buttons = new JButton[9][9];
        setArray();
        for (x = 8; x >= 0; x--) {
            for (y = 0; y <= 8; y++) {
                if (x == 0 && y == 0) {
                    Buttons[x][y] = new JButton();
                    Buttons[x][y].setEnabled(false);

                } else {
                    z = (x + y) % 2;
                    Buttons[x][y] = new JButton("(" + x + "," + y + ")");

                    if (x == 0) {

                        Buttons[x][y] = new JButton(c.substring(y - 1, y));
                        Buttons[x][y].setBackground(Color.DARK_GRAY);
                        Buttons[x][y].setEnabled(false);
                    } else if (y == 0) {
                        Buttons[x][y] = new JButton("" + x + "");
                        Buttons[x][y].setBackground(Color.DARK_GRAY);
                        Buttons[x][y].setEnabled(false);
                    } else if (z == 0) {
                        addImage(x, y);
                        Buttons[x][y].setBackground(Color.GRAY);
                    } else {
                        addImage(x, y);
                        Buttons[x][y].setBackground(Color.WHITE);
                    }
                }
                Panel1.add(Buttons[x][y]);

            }
        }
        undo = new JButton("undo");
        Panel2.add(undo);
        undo.addActionListener(new UndoHandler());
        Panel1.setPreferredSize(new Dimension(1050, 1050));
        
    
        for (x = 8; x >= 0;x--) {
            for (y = 0; y <= 8; y++) {
                Buttons[x][y].addActionListener(new ButtonHandler(x, y));
            }
        }

        Frame.add(Panel1, BorderLayout.EAST);
        Frame.add(Panel2, BorderLayout.WEST);

        Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
         //Frame.setSize(1650,1080);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//Frame.pack();
        Frame.setVisible(true);

    }

    public void setArray() {
        p = new piece("nocolor", "piece");
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                ele[x][y] = p;

            }
        }
    }

    public void addImage(int x, int y) {

        WR = new ImageIcon("C:\\Users\\Thirtharaj\\Documents\\NetBeansProjects\\chess1\\src\\chess1\\images\\WR.png");
        WKN = new ImageIcon("C:\\Users\\Thirtharaj\\Documents\\NetBeansProjects\\chess1\\src\\chess1\\images\\WKN.png");
        WB = new ImageIcon("C:\\Users\\Thirtharaj\\Documents\\NetBeansProjects\\chess1\\src\\chess1\\images\\WB.png");
        WQ = new ImageIcon("C:\\Users\\Thirtharaj\\Documents\\NetBeansProjects\\chess1\\src\\chess1\\images\\WQ.png");
        WP = new ImageIcon("C:\\Users\\Thirtharaj\\Documents\\NetBeansProjects\\chess1\\src\\chess1\\images\\WP.png");
        WK = new ImageIcon("C:\\Users\\Thirtharaj\\Documents\\NetBeansProjects\\chess1\\src\\chess1\\images\\WK.png");
        BP = new ImageIcon("C:\\Users\\Thirtharaj\\Documents\\NetBeansProjects\\chess1\\src\\chess1\\images\\BP.png");
        BR = new ImageIcon("C:\\Users\\Thirtharaj\\Documents\\NetBeansProjects\\chess1\\src\\chess1\\images\\BR.png");
        BKN = new ImageIcon("C:\\Users\\Thirtharaj\\Documents\\NetBeansProjects\\chess1\\src\\chess1\\images\\BKN.png");
        BB = new ImageIcon("C:\\Users\\Thirtharaj\\Documents\\NetBeansProjects\\chess1\\src\\chess1\\images\\BB.png");
        BQ = new ImageIcon("C:\\Users\\Thirtharaj\\Documents\\NetBeansProjects\\chess1\\src\\chess1\\images\\BQ.png");
        BK = new ImageIcon("C:\\Users\\Thirtharaj\\Documents\\NetBeansProjects\\chess1\\src\\chess1\\images\\BK.png");
        if (x == 1 && (y == 1 || y == 8)) {
            ele[x][y] = wr;

            Buttons[x][y] = new JButton(WR);
        } else if (x == 1 && (y == 2 || y == 7)) {
            ele[x][y] = wkn;

            Buttons[x][y] = new JButton(WKN);
        } else if (x == 1 && (y == 3 || y == 6)) {
            ele[x][y] = wb;

            Buttons[x][y] = new JButton(WB);
        } else if (x == 1 && y == 4) {
            ele[x][y] = wq;

            Buttons[x][y] = new JButton(WQ);
        } else if (x == 1 && y == 5) {
            ele[x][y] = wk;

            Buttons[x][y] = new JButton(WK);
        } else if (x == 2) {
            ele[x][y] = wp;

            Buttons[x][y] = new JButton(WP);
        } else if (x == 7) {
            ele[x][y] = bp;

            Buttons[x][y] = new JButton(BP);
        } else if (x == 8 && (y == 1 || y == 8)) {
            ele[x][y] = br;

            Buttons[x][y] = new JButton(BR);
        } else if (x == 8 && (y == 2 || y == 7)) {
            ele[x][y] = bkn;

            Buttons[x][y] = new JButton(BKN);
        } else if (x == 8 && (y == 3 || y == 6)) {
            ele[x][y] = bb;

            Buttons[x][y] = new JButton(BB);
        } else if (x == 8 && y == 4) {
            ele[x][y] = bq;

            Buttons[x][y] = new JButton(BQ);
        } else if (x == 8 && y == 5) {
            ele[x][y] = bk;

            Buttons[x][y] = new JButton(BK);
        }

    }

    public void addcol() {
        ac = 0;
        if (col.size() == 0) {

        } else {
            String str, q1, w1;
            int x1, y1;
            ac = 1;
            System.out.println("entering else");
            while (col.size() != 0) {
                System.out.println("entering while");
                str = col.get(col.size() - 1);
                if ("b".equals(str.substring(2, 3))) {
                    q1 = col.get(col.size() - 1).substring(0, 1);
                    w1 = col.get(col.size() - 1).substring(1, 2);
                    x1 = Integer.parseInt(q1);
                    y1 = Integer.parseInt(w1);
                    Buttons[x1][y1].setBackground(Color.BLUE);
                    System.out.println("entering add col and col is" + Buttons[x1][y1].getBackground());
                } else 
                  if ("r".equals(str.substring(2, 3))){
                    q1 = col.get(col.size() - 1).substring(0, 1);
                    w1 = col.get(col.size() - 1).substring(1, 2);
                    x1 = Integer.parseInt(q1);
                    y1 = Integer.parseInt(w1);
                    Buttons[x1][y1].setBackground(Color.RED);
                    System.out.println("entering add col and col is" + Buttons[x1][y1].getBackground());
                }
                  else{
                       q1 = col.get(col.size() - 1).substring(0, 1);
                    w1 = col.get(col.size() - 1).substring(1, 2);
                    x1 = Integer.parseInt(q1);
                    y1 = Integer.parseInt(w1);
                    Buttons[x1][y1].setBackground(Color.CYAN);
                    System.out.println("entering add col and col is" + Buttons[x1][y1].getBackground());
                  }
                    col.remove(col.size() - 1);
            }
        }
    }

    public void check() {
        int i, j, wx = 0, wy = 0, bx = 0, by = 0;

        if (flag == 1) {
            System.out.println("in checking for black");
        } else {
            System.out.println("in checking for white");
        }

        for (i = 1; i <= 8; i++) {
            for (j = 1; j <= 8; j++) {
                if ("king".equals(ele[i][j].type) && "white".equals(ele[i][j].color)) {
                    wx = i;
                    wy = j;
                } else if ("king".equals(ele[i][j].type) && "black".equals(ele[i][j].color)) {
                    bx = i;
                    by = j;
                }

            }
        }

        for (i = 1; i <= 8; i++) {
            for (j = 1; j <= 8; j++) {

                if (flag == 1 && "black".equals(ele[i][j].color)) {
                    if (red == 1) {
                        al.add("" + i + j + "");
                    }
                    red = 0;
                    ele[i][j].perform_action(i, j);
                    if (Color.RED == Buttons[wx][wy].getBackground()) {
                        red = 1;
                        color_flush();
                        Buttons[wx][wy].setBackground(Color.MAGENTA);

                        return;
                    }
                    color_flush();
                } else if (flag == 0 && "white".equals(ele[i][j].color)) {

                    if (red == -1) {
                        al.add("" + i + j + "");
                        System.out.println("getlost");
                    }
                    red = 0;
                    ele[i][j].perform_action(i, j);
                    if (Color.RED == Buttons[bx][by].getBackground()) {
                        red = -1;
                        color_flush();
                        Buttons[bx][by].setBackground(Color.MAGENTA);

                        return;
                    }
                    color_flush();
                }
                red = 0;
            }
        }
        System.out.println("in check red is" + red);

    }

    void checkmate() {
        int i, j;
        for (i = 1; i < 9; i++) {
            for (j = 1; j < 9; j++) {
                if (red == 1 && "white".equals(ele[i][j].color) && flag == 1) {
                    al.add("" + i + j + "");
                    ele[i][j].perform_action(i, j);
                    al.remove(al.size() - 1);
                    if (ac == 1) {
                        return;
                    }
                } else if (red == -1 && "black".equals(ele[i][j].color) && flag == 0) {
                    al.add("" + i + j + "");
                    ele[i][j].perform_action(i, j);
                    al.remove(al.size() - 1);
                    if (ac == 1) {
                        return;
                    }
                }

            }
        }
        System.out.println("game over");
    }

    private class UndoHandler implements ActionListener {

        int x1, y1, x2, y2;

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("i am undo  " + red);
            if (al.size() == 0) {
                System.out.println("You are at the starting point");
            } else {
                flag = (flag + 1) % 2;
                if (al.size() % 2 == 1) {
                    System.out.println("You are at the starting point" + al.get(al.size() - 1));
                    al.remove(al.size() - 1);
                    color_flush();
                }

                String q1 = al.get(al.size() - 1).substring(0, 1);
                String w1 = al.get(al.size() - 1).substring(1, 2);
                x1 = Integer.parseInt(q1);
                y1 = Integer.parseInt(w1);

                q1 = al.get(al.size() - 2).substring(0, 1);
                w1 = al.get(al.size() - 2).substring(1, 2);
                x2 = Integer.parseInt(q1);
                y2 = Integer.parseInt(w1);

                 if(al.get(al.size() - 1).length()==3&&"p".equals(al.get(al.size() - 1).substring(2,3)))
                {
                    if("white".equals(ele[x1][y1].color))
                    {
                    ele[x1][y1]=wp;
                    Buttons[x1][y1].setIcon(WP);
                    }
                    else
                    {
                    ele[x1][y1]=bp;
                    Buttons[x1][y1].setIcon(BP);
                    }

                }
                Buttons[x2][y2].setIcon(Buttons[x1][y1].getIcon());
                ele[x2][y2] = ele[x1][y1];

                if(al.get(al.size() - 1).length()==3&&"c".equals(al.get(al.size() - 1).substring(2,3)))
                {
                    Buttons[x1][y1].setIcon(null);
                    ele[x1][y1]=p;
                    if(y1==7)
                    { 
                        ele[x1][6]=p;
                        Buttons[x1][6].setIcon(null);
                        y1=8;
                    }
                    else
                        if(y1==3)
                        {
                           ele[x1][4]=p;
                           Buttons[x1][4].setIcon(null);
                           y1=1;
                        }
                }


                ele[x1][y1] = un.get(un.size() - 1);

                al.remove(al.size() - 1);
                al.remove(al.size() - 1);
                un.remove(un.size() - 1);
                String type = ele[x1][y1].type;
                String color = ele[x1][y1].color;
                type = type + color;
                System.out.println("" + type);
                System.out.println("from" + x1 + y1 + "to" + x2 + y2);

                switch (type) {
                    case "piecenocolor":
                        Buttons[x1][y1].setIcon(null);
                        break;
                    case "kingwhite":
                        Buttons[x1][y1].setIcon(WK);
                        break;
                    case "kingblack":
                        Buttons[x1][y1].setIcon(BK);
                        break;
                    case "queenwhite":
                        Buttons[x1][y1].setIcon(WQ);
                        break;
                    case "queenblack":
                        Buttons[x1][y1].setIcon(BQ);
                        break;
                    case "knightwhite":
                        Buttons[x1][y1].setIcon(WKN);
                        break;
                    case "knightblack":
                        Buttons[x1][y1].setIcon(BKN);
                        break;
                    case "bishopwhite":
                        Buttons[x1][y1].setIcon(WB);
                        break;
                    case "bishopblack":
                        Buttons[x1][y1].setIcon(BB);
                        break;
                    case "rookwhite":
                        Buttons[x1][y1].setIcon(WR);
                        break;
                    case "rookblack":
                        Buttons[x1][y1].setIcon(BR);
                        break;
                    case "pawnwhite":
                        Buttons[x1][y1].setIcon(WP);
                        break;
                    case "pawnblack":
                        Buttons[x1][y1].setIcon(BP);
                        break;
                }
            }
            color_flush();
            if (red == 0) {
                check();
            }

            /* if(red==0)
             {
             Buttons[x1][x1].doClick();
             Buttons[x1][y1].doClick();
             }*/
        }
    }

    private class ButtonHandler implements ActionListener {

        int x, y;

        public ButtonHandler(int x, int y) {

            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            //System.out.println("button handler"+x+y+al.size());
            if (al.size() % 2 == 0) {
                if ("piece".equals(ele[x][y].type) || (flag == 1 && "black".equals(ele[x][y].color)) || flag == 0 && "white".equals(ele[x][y].color)) {

                } else {
                    System.out.println("button handler" + x + y + al.size());
                    String str = "" + x + y + "";
                    al.add(str);
                    red = 0;
                    check();
                    ele[x][y].perform_action(x, y);

                    System.out.println("but" + al.get(al.size() - 1) + al.size());
                }
            } else {
                String q1 = al.get(al.size() - 1).substring(0, 1);
                String w1 = al.get(al.size() - 1).substring(1, 2);
                int x1 = Integer.parseInt(q1);
                int y1 = Integer.parseInt(w1);
                bt=1;
                shuffle(x, y, x1, y1);

            }

        }

    }

    public void shuffle(int x, int y, int x1, int y1) {
        int red1 = red;
        Color color = Buttons[x][y].getBackground();
        Color color1 = Color.BLUE;
        Color color2 = Color.RED;
        Color color3=Color.CYAN;
        if (color == color1) {

            un.add(p);
            Buttons[x][y].setIcon(Buttons[x1][y1].getIcon());
            Buttons[x1][y1].setIcon(null);
            ele[x][y] = ele[x1][y1];
            ele[x1][y1] = p;
            color_flush();
            String str = "" + x + y + "";
            al.add(str);
            flag = (flag + 1) % 2;

        } else if (color == color2) {

            un.add(ele[x][y]);
            Buttons[x][y].setIcon(Buttons[x1][y1].getIcon());
            Buttons[x1][y1].setIcon(null);
            ele[x][y] = ele[x1][y1];
            ele[x1][y1] = p;
            color_flush();
            String str = "" + x + y + "";
            al.add(str);
            flag = (flag + 1) % 2;
        } else if(color==color3){
              System.out.println(""+x1);
            if(y==3&&((x1==1)||(x1==8)))
            {

                 un.add(ele[x][1]);
                 String str = "" + x + 3 + "c";
                 al.add(str);
                 Buttons[x][3].setIcon(Buttons[x][5].getIcon());
                 Buttons[x][4].setIcon(Buttons[x][1].getIcon());
                 Buttons[x][5].setIcon(null);
                 Buttons[x][1].setIcon(null);
                 ele[x][3]=ele[x][5];
                 ele[x][4]=ele[x][1];
                 ele[x][1]=ele[x][5]=p;
                   flag = (flag + 1) % 2;
                   color_flush();
            }
            if(y==7&&((x1==1)||(x1==8)))
            {

                 un.add(ele[x][8]);
                 String str = "" + x + 7 + "c";
                 al.add(str);
                 Buttons[x][7].setIcon(Buttons[x][5].getIcon());
                 Buttons[x][6].setIcon(Buttons[x][8].getIcon());
                 Buttons[x][5].setIcon(null);
                 Buttons[x][8].setIcon(null);
                 ele[x][7]=ele[x][5];
                 ele[x][6]=ele[x][8];
                 ele[x][8]=ele[x][5]=p;
                   flag = (flag + 1) % 2;
                   color_flush();
            } 
                   if(x1==2||x1==7)
                   {
                       System.out.println("enter the piece you want the pawn to become 5");
                       Scanner sc=new Scanner(System.in);
                       String st=sc.nextLine();
                       st=st+ele[x1][y1].color;
                       un.add(ele[x][y]);
                       //Buttons[x][y].setIcon(Buttons[x1][y1].getIcon());
                       Buttons[x1][y1].setIcon(null);
                       //ele[x][y] = ele[x1][y1];
                       ele[x1][y1] = p;
                       color_flush();
                       String str = "" + x + y + "p";
                       al.add(str);
                       flag = (flag + 1) % 2;


                       switch (st) {
                    case "queenwhite":
                        Buttons[x][y].setIcon(WQ);
                         ele[x][y]=wq;
                        break;
                    case "queenblack":
                        Buttons[x][y].setIcon(BQ);
                         ele[x][y]=bq;
                        break;
                    case "knightwhite":
                        Buttons[x][y].setIcon(WKN);
                         ele[x][y]=wkn;
                        break;
                    case "knightblack":
                        Buttons[x][y].setIcon(BKN);
                         ele[x][y]=bkn;
                        break;
                    case "bishopwhite":
                        Buttons[x][y].setIcon(WB);
                         ele[x][y]=wb;
                        break;
                    case "bishopblack":
                        Buttons[x][y].setIcon(BB);
                         ele[x][y]=bb;
                        break;
                    case "rookwhite":
                        Buttons[x][y].setIcon(WR);
                         ele[x][y]=wr;
                        break;
                    case "rookblack":
                        Buttons[x][y].setIcon(BR);
                         ele[x][y]=br;
                        break;
                }

                   }

        }
           else {
            al.remove(al.size() - 1);
            color_flush();
        }

        //red = 0;
       // flag=(flag+1)%2;
       // check();
        //if(red!=0)
        //{
        //    System.out.println("invalid move");
        //    undo.doClick();
        //}
        // flag=(flag+1)%2;
        
        red = 0;
        check();
        System.out.println("shuffle" + x + y + "form" + x1 + y1 + "size is" + al.size() + "ele re" + al.get(al.size() - 1) + al.get(al.size() - 2) + al.get(al.size() - 1));

        if (red != 0) {
            if ((red == 1 && red1 == -1) || (red == -1 && red1 == 1)) {

            } else {
                color_flush();
                  bt=0;
                checkmate();
              
                   
                red = 0;
                check();
                System.out.println("" + al.size() + "ele re" + al.get(al.size() - 1) + al.get(al.size() - 2) + al.get(al.size() - 3));
                System.out.println("" + red);
            }
        }
        else
         if(bt==1)
        {
        red = 0;
        flag=(flag+1)%2;
        check();
        if(red!=0)
        {
            System.out.println("invalid move");
            undo.doClick();
        }
        flag=(flag+1)%2;
           red = 0;
           check();
        }
        
    }

    public void color_flush() {
        int z;

        for (int x = 1; x < 9; x++) {
            for (int y = 1; y < 9; y++) {
                z = (x + y) % 2;
                if (z == 0) {
                    Buttons[x][y].setBackground(Color.GRAY);
                } else {
                    Buttons[x][y].setBackground(Color.WHITE);
                }
            }
        }

    }

    class piece {

        String color, type;

        public piece(String color, String type) {
            this.color = color;
            this.type = type;
        }

        public void perform_action(int x, int y) {

        }

    }

    class king extends piece {

        public king(String color) {
            super(color, "king");
        }

        @Override

        public void perform_action(int x, int y) {
            if ("white".equals(this.color)) {
                if (x == 1 && y == 5) {
                    int flg = 0;
                    for (int i = al.size() - 2; i >= 0; i--) {
                        String str = al.get(i);
                        if ("15".equals(str)) {
                            flg = 1;
                            break;
                        }

                    }
                    if (flg == 0) {
                        if ("piece".equals(ele[x][y - 1].type) && "piece".equals(ele[x][y - 2].type) && "piece".equals(ele[x][y - 3].type) && "white".equals(ele[x][y - 4].color)) {
                            
                             for (int i = al.size() - 1; i >= 0; i--) {
                              String str = al.get(i);
                              if ("11".equals(str)) {
                                flg = 1;
                               break;
                              }

                            }
                            if ("rook".equals(ele[x][y - 4].type)&&red==0&&flg==0) 
                            {
                                Buttons[x][y - 2].setBackground(Color.CYAN);
                            }
                        }

                        if ("piece".equals(ele[x][y + 1].type) && "piece".equals(ele[x][y + 2].type) && "white".equals(ele[x][y + 3].color)) {
                            flg=0;
                            for (int i = al.size() - 1; i >= 0; i--) {
                              String str = al.get(i);
                              if ("18".equals(str)) {
                                flg = 1;
                               break;
                              }
                            }
                            if ("rook".equals(ele[x][y + 3].type)&&red==0&&flg==0) 
                            {
                                Buttons[x][y + 2].setBackground(Color.CYAN);
                            }
                        
                    }

                }
                }
                if (x + 1 < 9) {
                    if (y - 1 > 0) {
                        if ("piece".equals(ele[x + 1][y - 1].type)) {
                            Buttons[x + 1][y - 1].setBackground(Color.BLUE);
                            if (red == 1) {
                                shuffle(x + 1, y - 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = 1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x + 1][y - 1].setBackground(Color.BLUE);
                                    col.add("" + (x + 1) + (y - 1) + "b");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                        if ("black".equals(ele[x + 1][y - 1].color)) {
                            Buttons[x + 1][y - 1].setBackground(Color.RED);
                            if (red == 1) {
                                shuffle(x + 1, y - 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = 1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x + 1][y - 1].setBackground(Color.RED);
                                    col.add("" + (x + 1) + (y - 1) + "r");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                    }
                    if (y + 1 < 9) {
                        if ("piece".equals(ele[x + 1][y + 1].type)) {
                            Buttons[x + 1][y + 1].setBackground(Color.BLUE);
                            if (red == 1) {
                                shuffle(x + 1, y + 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = 1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x + 1][y + 1].setBackground(Color.BLUE);
                                    col.add("" + (x + 1) + (y + 1) + "b");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                        if ("black".equals(ele[x + 1][y + 1].color)) {
                            Buttons[x + 1][y + 1].setBackground(Color.RED);
                            if (red == 1) {
                                shuffle(x + 1, y + 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = 1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x + 1][y + 1].setBackground(Color.RED);
                                    col.add("" + (x + 1) + (y + 1) + "r");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                    }

                    if ("piece".equals(ele[x + 1][y].type)) {
                        Buttons[x + 1][y].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x + 1, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + 1][y].setBackground(Color.BLUE);
                                col.add("" + (x + 1) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                    if ("black".equals(ele[x + 1][y].color)) {
                        Buttons[x + 1][y].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x + 1, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + 1][y].setBackground(Color.RED);
                                col.add("" + (x + 1) + (y) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }
                if (x - 1 > 0) {
                    if (y - 1 > 0) {
                        if ("piece".equals(ele[x - 1][y - 1].type)) {
                            Buttons[x - 1][y - 1].setBackground(Color.BLUE);
                            if (red == 1) {
                                shuffle(x - 1, y - 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = 1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x - 1][y - 1].setBackground(Color.BLUE);
                                    col.add("" + (x - 1) + (y - 1) + "b");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                        if ("black".equals(ele[x - 1][y - 1].color)) {
                            Buttons[x - 1][y - 1].setBackground(Color.RED);
                            if (red == 1) {
                                shuffle(x - 1, y - 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = 1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x + 1][y - 1].setBackground(Color.RED);
                                    col.add("" + (x - 1) + (y - 1) + "r");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                    }
                    if (y + 1 < 9) {
                        if ("piece".equals(ele[x - 1][y + 1].type)) {
                            Buttons[x - 1][y + 1].setBackground(Color.BLUE);
                            if (red == 1) {
                                shuffle(x - 1, y + 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = 1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x - 1][y + 1].setBackground(Color.BLUE);
                                    col.add("" + (x - 1) + (y + 1) + "b");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                        if ("black".equals(ele[x - 1][y + 1].color)) {
                            Buttons[x - 1][y + 1].setBackground(Color.RED);
                            if (red == 1) {
                                shuffle(x - 1, y + 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = 1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x - 1][y + 1].setBackground(Color.RED);
                                    col.add("" + (x - 1) + (y + 1) + "r");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                    }

                    if ("piece".equals(ele[x - 1][y].type)) {
                        Buttons[x - 1][y].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x - 1, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - 1][y].setBackground(Color.BLUE);
                                col.add("" + (x - 1) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                    if ("black".equals(ele[x - 1][y].color)) {
                        Buttons[x - 1][y].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x - 1, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - 1][y].setBackground(Color.RED);
                                col.add("" + (x - 1) + (y) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }
                if (y + 1 < 9) {
                    if ("piece".equals(ele[x][y + 1].type)) {
                        Buttons[x][y + 1].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x, y + 1, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x][y + 1].setBackground(Color.BLUE);
                                col.add("" + (x) + (y + 1) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                    if ("black".equals(ele[x][y + 1].color)) {
                        Buttons[x][y + 1].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x, y + 1, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x][y + 1].setBackground(Color.RED);
                                col.add("" + (x) + (y + 1) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }
                if (y - 1 > 0) {
                    if ("piece".equals(ele[x][y - 1].type)) {
                        Buttons[x][y - 1].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x, y - 1, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x][y - 1].setBackground(Color.BLUE);
                                col.add("" + (x) + (y - 1) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                    if ("black".equals(ele[x][y - 1].color)) {
                        Buttons[x][y - 1].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x, y - 1, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x][y - 1].setBackground(Color.RED);
                                col.add("" + (x) + (y - 1) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }
            } else {

                if (x == 8 && y == 5) {
                    int flg = 0;
                    for (int i = al.size() - 2; i >= 0; i--) {
                        String str = al.get(i);
                        if ("85".equals(str)) {
                            flg = 1;
                            break;
                        }

                    }
                    if (flg == 0) {
                        if ("piece".equals(ele[x][y - 1].type) && "piece".equals(ele[x][y - 2].type) && "piece".equals(ele[x][y - 3].type) && "black".equals(ele[x][y - 4].color)) {
                            
                            for (int i = al.size() - 2; i >= 0; i--) {
                        String str = al.get(i);
                        if ("81".equals(str)) {
                            flg = 1;
                            break;
                          }

                        }
                            
                            
                            if ("rook".equals(ele[x][y - 4].type)&&red==0&&flg==0) 
                            {
                                Buttons[x][y - 2].setBackground(Color.CYAN);
                            }
                        }

                        if ("piece".equals(ele[x][y + 1].type) && "piece".equals(ele[x][y + 2].type) && "black".equals(ele[x][y + 3].color)) {
                            flg=0;
                            for (int i = al.size() - 2; i >= 0; i--) {
                             String str = al.get(i);
                             if ("88".equals(str)) {
                              flg = 1;
                               break;
                             }

                           }
                            if ("rook".equals(ele[x][y + 3].type)&&red==0) 
                            {
                                Buttons[x][y + 2].setBackground(Color.CYAN);
                            }
                        }
                    }

                }

                if (x + 1 < 9) {
                    if (y - 1 > 0) {
                        if ("piece".equals(ele[x + 1][y - 1].type)) {
                            Buttons[x + 1][y - 1].setBackground(Color.BLUE);
                            if (red == -1) {
                                shuffle(x + 1, y - 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = -1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x + 1][y - 1].setBackground(Color.BLUE);
                                    col.add("" + (x + 1) + (y - 1) + "b");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                        if ("white".equals(ele[x + 1][y - 1].color)) {
                            Buttons[x + 1][y - 1].setBackground(Color.RED);
                            if (red == -1) {
                                shuffle(x + 1, y - 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = -1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x + 1][y - 1].setBackground(Color.RED);
                                    col.add("" + (x + 1) + (y - 1) + "r");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                    }
                    if (y + 1 < 9) {
                        if ("piece".equals(ele[x + 1][y + 1].type)) {
                            Buttons[x + 1][y + 1].setBackground(Color.BLUE);
                            if (red == -1) {
                                shuffle(x + 1, y + 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = -1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x + 1][y + 1].setBackground(Color.BLUE);
                                    col.add("" + (x + 1) + (y + 1) + "b");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                        if ("white".equals(ele[x + 1][y + 1].color)) {
                            Buttons[x + 1][y + 1].setBackground(Color.RED);
                            if (red == -1) {
                                shuffle(x + 1, y + 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = -1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x + 1][y + 1].setBackground(Color.RED);
                                    col.add("" + (x + 1) + (y + 1) + "r");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                    }

                    if ("piece".equals(ele[x + 1][y].type)) {
                        Buttons[x + 1][y].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x + 1, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + 1][y].setBackground(Color.BLUE);
                                col.add("" + (x + 1) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                    if ("white".equals(ele[x + 1][y].color)) {
                        Buttons[x + 1][y].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x + 1, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + 1][y].setBackground(Color.RED);
                                col.add("" + (x + 1) + (y) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }
                if (x - 1 > 0) {
                    if (y - 1 > 0) {
                        if ("piece".equals(ele[x - 1][y - 1].type)) {
                            Buttons[x - 1][y - 1].setBackground(Color.BLUE);
                            if (red == -1) {
                                shuffle(x - 1, y - 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = -1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x - 1][y - 1].setBackground(Color.BLUE);
                                    col.add("" + (x - 1) + (y - 1) + "b");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                        if ("white".equals(ele[x - 1][y - 1].color)) {
                            Buttons[x - 1][y - 1].setBackground(Color.RED);
                            if (red == -1) {
                                shuffle(x - 1, y - 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = -1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x - 1][y - 1].setBackground(Color.RED);
                                    col.add("" + (x - 1) + (y - 1) + "r");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                    }
                    if (y + 1 < 9) {
                        if ("piece".equals(ele[x - 1][y + 1].type)) {
                            Buttons[x - 1][y + 1].setBackground(Color.BLUE);
                            if (red == -1) {
                                shuffle(x - 1, y + 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = -1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x - 1][y + 1].setBackground(Color.BLUE);
                                    col.add("" + (x - 1) + (y + 1) + "b");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                        if ("white".equals(ele[x - 1][y + 1].color)) {
                            Buttons[x - 1][y + 1].setBackground(Color.RED);
                            if (red == -1) {
                                shuffle(x - 1, y + 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = -1;
                                    undo.doClick();
                                    System.out.println("hey");

                                    Buttons[x - 1][y + 1].setBackground(Color.RED);
                                    col.add("" + (x - 1) + (y + 1) + "r");
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                    }

                    if ("piece".equals(ele[x - 1][y].type)) {
                        Buttons[x - 1][y].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x - 1, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - 1][y].setBackground(Color.BLUE);
                                col.add("" + (x - 1) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                    if ("white".equals(ele[x - 1][y].color)) {
                        Buttons[x - 1][y].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x - 1, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - 1][y].setBackground(Color.RED);
                                col.add("" + (x - 1) + (y) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }
                if (y + 1 < 9) {
                    if ("piece".equals(ele[x][y + 1].type)) {
                        Buttons[x][y + 1].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x, y + 1, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x][y + 1].setBackground(Color.BLUE);
                                col.add("" + (x) + (y + 1) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                    if ("white".equals(ele[x][y + 1].color)) {
                        Buttons[x][y + 1].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x, y + 1, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x][y + 1].setBackground(Color.RED);
                                col.add("" + (x) + (y + 1) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }
                if (y - 1 > 0) {
                    if ("piece".equals(ele[x][y - 1].type)) {
                        Buttons[x][y - 1].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x, y - 1, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x][y - 1].setBackground(Color.BLUE);
                                col.add("" + (x) + (y - 1) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                    if ("white".equals(ele[x][y - 1].color)) {
                        Buttons[x][y - 1].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x, y - 1, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x][y - 1].setBackground(Color.RED);
                                col.add("" + (x) + (y - 1) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }
            }
            if (red != 0) {
                color_flush();
                addcol();
            }
        }
    }

    class queen extends piece {

        public queen(String color) {
            super(color, "queen");
        }

        @Override
        public void perform_action(int x, int y) {
            int i = 1;

            if ("white".equals(this.color)) {
                while ((x + i < 9 && y + i < 9) && !"white".equals(ele[x + i][y + i].color)) {
                    if ("piece".equals(ele[x + i][y + i].type)) {
                        Buttons[x + i][y + i].setBackground(Color.BLUE);

                        if (red == 1) {
                            shuffle(x + i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y + i].setBackground(Color.BLUE);
                                col.add("" + (x + i) + (y + i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if ((x + i < 9 && y + i < 9) && "black".equals(ele[x + i][y + i].color)) {
                        Buttons[x + i][y + i].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x + i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y + i].setBackground(Color.RED);
                                col.add("" + (x + i) + (y + i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x + i < 9 && y - i > 0 && !"white".equals(ele[x + i][y - i].color)) {
                    if ("piece".equals(ele[x + i][y - i].type)) {
                        Buttons[x + i][y - i].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x + i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y - i].setBackground(Color.BLUE);
                                col.add("" + (x + i) + (y - i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;

                        }
                        i++;
                    }
                    if (x + i < 9 && y - i > 0 && "black".equals(ele[x + i][y - i].color)) {
                        Buttons[x + i][y - i].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x + i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y - i].setBackground(Color.RED);
                                col.add("" + (x + i) + (y - i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x - i > 0 && y + i < 9 && !"white".equals(ele[x - i][y + i].color)) {
                    if ("piece".equals(ele[x - i][y + i].type)) {
                        Buttons[x - i][y + i].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x - i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y + i].setBackground(Color.BLUE);
                                col.add("" + (x - i) + (y + i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if (x - i > 0 && y + i < 9 && "black".equals(ele[x - i][y + i].color)) {
                        Buttons[x - i][y + i].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x - i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y + i].setBackground(Color.RED);
                                col.add("" + (x - i) + (y + i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x - i > 0 && y - i > 0 && !"white".equals(ele[x - i][y - i].color)) {
                    if ("piece".equals(ele[x - i][y - i].type)) {
                        Buttons[x - i][y - i].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x - i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y - i].setBackground(Color.BLUE);
                                col.add("" + (x - i) + (y - i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if (x - i > 0 && y - i > 0 && "black".equals(ele[x - i][y - i].color)) {
                        Buttons[x - i][y - i].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x - i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y - i].setBackground(Color.RED);
                                col.add("" + (x - i) + (y - i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }

                i = 1;
            } else {
                while ((x + i < 9 && y + i < 9) && !"black".equals(ele[x + i][y + i].color)) {
                    if ("piece".equals(ele[x + i][y + i].type)) {
                        Buttons[x + i][y + i].setBackground(Color.BLUE);

                        if (red == -1) {
                            shuffle(x + i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y + i].setBackground(Color.BLUE);
                                col.add("" + (x + i) + (y + i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if ((x + i < 9 && y + i < 9) && "white".equals(ele[x + i][y + i].color)) {
                        Buttons[x + i][y + i].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x + i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y + i].setBackground(Color.RED);
                                col.add("" + (x + i) + (y + i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x + i < 9 && y - i > 0 && !"black".equals(ele[x + i][y - i].color)) {
                    if ("piece".equals(ele[x + i][y - i].type)) {
                        Buttons[x + i][y - i].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x + i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y - i].setBackground(Color.BLUE);
                                col.add("" + (x + i) + (y - i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if (x + i < 9 && y - i > 0 && "white".equals(ele[x + i][y - i].color)) {
                        Buttons[x + i][y - i].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x + i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y - i].setBackground(Color.RED);
                                col.add("" + (x + i) + (y - i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x - i > 0 && y + i < 9 && !"black".equals(ele[x - i][y + i].color)) {
                    if ("piece".equals(ele[x - i][y + i].type)) {
                        Buttons[x - i][y + i].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x - i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y + i].setBackground(Color.BLUE);
                                col.add("" + (x - i) + (y + i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;

                        }
                        i++;
                    }
                    if (x - i > 0 && y + i < 9 && "white".equals(ele[x - i][y + i].color)) {
                        Buttons[x - i][y + i].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x - i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y + i].setBackground(Color.RED);
                                col.add("" + (x - i) + (y + i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x - i > 0 && y - i > 0 && !"black".equals(ele[x - i][y - i].color)) {
                    if ("piece".equals(ele[x - i][y - i].type)) {
                        Buttons[x - i][y - i].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x - i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y - i].setBackground(Color.BLUE);
                                col.add("" + (x - i) + (y - i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if (x - i > 0 && y - i > 0 && "white".equals(ele[x - i][y - i].color)) {
                        Buttons[x - i][y - i].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x - i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y - i].setBackground(Color.RED);
                                col.add("" + (x - i) + (y - i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }

                i = 1;
            }

            if ("white".equals(this.color)) {
                while (x + i < 9 && !"white".equals(ele[x + i][y].color)) {
                    if ("piece".equals(ele[x + i][y].type)) {
                        Buttons[x + i][y].setBackground(Color.BLUE);
                        if (red == 1) {

                            shuffle(x + i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x + i][y].setBackground(Color.BLUE);
                                col.add("" + (x + i) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }

                    if (x + i < 9 && "black".equals(ele[x + i][y].color)) {
                        Buttons[x + i][y].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x + i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y].setBackground(Color.RED);
                                col.add("" + (x + i) + (y) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }

                }
                i = 1;
                while (x - i > 0 && !"white".equals(ele[x - i][y].color)) {
                    if ("piece".equals(ele[x - i][y].type)) {
                        Buttons[x - i][y].setBackground(Color.BLUE);
                        if (red == 1) {

                            shuffle(x - i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x - i][y].setBackground(Color.BLUE);
                                col.add("" + (x - i) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if (x - i > 0 && "black".equals(ele[x - i][y].color)) {
                        Buttons[x - i][y].setBackground(Color.RED);
                        if (red == 1) {

                            shuffle(x - i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x - i][y].setBackground(Color.RED);
                                col.add("" + (x - i) + (y) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (y + i < 9 && !"white".equals(ele[x][y + i].color)) {
                    if ("piece".equals(ele[x][y + i].type)) {
                        Buttons[x][y + i].setBackground(Color.BLUE);
                        if (red == 1) {

                            shuffle(x, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y + i].setBackground(Color.BLUE);
                                col.add("" + (x) + (y + i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if (y + i < 9 && "black".equals(ele[x][y + i].color)) {
                        Buttons[x][y + i].setBackground(Color.RED);
                        if (red == 1) {

                            shuffle(x, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y + i].setBackground(Color.RED);
                                col.add("" + (x) + (y + i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (y - i > 0 && !"white".equals(ele[x][y - i].color)) {
                    if ("piece".equals(ele[x][y - i].type)) {
                        Buttons[x][y - i].setBackground(Color.BLUE);
                        if (red == 1) {

                            shuffle(x, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y - i].setBackground(Color.BLUE);
                                col.add("" + (x) + (y - i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }

                    if (y - i > 0 && "black".equals(ele[x][y - i].color)) {
                        Buttons[x][y - i].setBackground(Color.RED);
                        if (red == 1) {

                            shuffle(x, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y - i].setBackground(Color.RED);
                                col.add("" + (x) + (y - i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }

            } else {
                while (x + i < 9 && !"black".equals(ele[x + i][y].color)) {
                    if ("piece".equals(ele[x + i][y].type)) {
                        Buttons[x + i][y].setBackground(Color.BLUE);
                        if (red == -1) {

                            shuffle(x + i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x + i][y].setBackground(Color.BLUE);
                                col.add("" + (x + i) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }

                    if (x + i < 9 && "white".equals(ele[x + i][y].color)) {
                        Buttons[x + i][y].setBackground(Color.RED);
                        if (red == -1) {

                            shuffle(x + i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x + i][y].setBackground(Color.RED);
                                col.add("" + (x + i) + (y) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x - i > 0 && !"black".equals(ele[x - i][y].color)) {
                    if ("piece".equals(ele[x - i][y].type)) {
                        Buttons[x - i][y].setBackground(Color.BLUE);
                        if (red == -1) {

                            shuffle(x - i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x - i][y].setBackground(Color.BLUE);
                                col.add("" + (x - i) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }

                    if (x - i > 0 && "white".equals(ele[x - i][y].color)) {
                        Buttons[x - i][y].setBackground(Color.RED);
                        if (red == -1) {

                            shuffle(x - i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x - i][y].setBackground(Color.RED);
                                col.add("" + (x - i) + (y) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (y + i < 9 && !"black".equals(ele[x][y + i].color)) {
                    if ("piece".equals(ele[x][y + i].type)) {
                        Buttons[x][y + i].setBackground(Color.BLUE);
                        if (red == -1) {

                            shuffle(x, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y + i].setBackground(Color.BLUE);
                                col.add("" + (x) + (y + i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }

                    if (y + i < 9 && "white".equals(ele[x][y + i].color)) {
                        Buttons[x][y + i].setBackground(Color.RED);
                        if (red == -1) {

                            shuffle(x, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y + i].setBackground(Color.RED);
                                col.add("" + (x) + (y + i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (y - i > 0 && !"black".equals(ele[x][y - i].color)) {
                    if ("piece".equals(ele[x][y - i].type)) {
                        Buttons[x][y - i].setBackground(Color.BLUE);
                        if (red == -1) {

                            shuffle(x, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y - i].setBackground(Color.BLUE);
                                col.add("" + (x) + (y - i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }

                    if (y - i > 0 && "white".equals(ele[x][y - i].color)) {
                        Buttons[x][y - i].setBackground(Color.RED);
                        if (red == -1) {

                            shuffle(x, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y - i].setBackground(Color.RED);
                                col.add("" + (x) + (y - i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
            }
            if (red != 0) {
                color_flush();
                addcol();
            }

        }
    }

    class bishop extends piece {

        public bishop(String color) {
            super(color, "bishop");
        }

        @Override
        public void perform_action(int x, int y) {
            int i = 1;

            if ("white".equals(this.color)) {
                while ((x + i < 9 && y + i < 9) && !"white".equals(ele[x + i][y + i].color)) {
                    if ("piece".equals(ele[x + i][y + i].type)) {
                        Buttons[x + i][y + i].setBackground(Color.BLUE);

                        if (red == 1) {
                            shuffle(x + i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y + i].setBackground(Color.BLUE);
                                col.add("" + (x + i) + (y + i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if ((x + i < 9 && y + i < 9) && "black".equals(ele[x + i][y + i].color)) {
                        Buttons[x + i][y + i].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x + i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y + i].setBackground(Color.RED);
                                col.add("" + (x + i) + (y + i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x + i < 9 && y - i > 0 && !"white".equals(ele[x + i][y - i].color)) {
                    if ("piece".equals(ele[x + i][y - i].type)) {
                        Buttons[x + i][y - i].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x + i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y - i].setBackground(Color.BLUE);
                                col.add("" + (x + i) + (y - i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;

                        }
                        i++;
                    }
                    if (x + i < 9 && y - i > 0 && "black".equals(ele[x + i][y - i].color)) {
                        Buttons[x + i][y - i].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x + i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y - i].setBackground(Color.RED);
                                col.add("" + (x + i) + (y - i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x - i > 0 && y + i < 9 && !"white".equals(ele[x - i][y + i].color)) {
                    if ("piece".equals(ele[x - i][y + i].type)) {
                        Buttons[x - i][y + i].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x - i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y + i].setBackground(Color.BLUE);
                                col.add("" + (x - i) + (y + i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if (x - i > 0 && y + i < 9 && "black".equals(ele[x - i][y + i].color)) {
                        Buttons[x - i][y + i].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x - i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y + i].setBackground(Color.RED);
                                col.add("" + (x - i) + (y + i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x - i > 0 && y - i > 0 && !"white".equals(ele[x - i][y - i].color)) {
                    if ("piece".equals(ele[x - i][y - i].type)) {
                        Buttons[x - i][y - i].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x - i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y - i].setBackground(Color.BLUE);
                                col.add("" + (x - i) + (y - i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if (x - i > 0 && y - i > 0 && "black".equals(ele[x - i][y - i].color)) {
                        Buttons[x - i][y - i].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x - i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y - i].setBackground(Color.RED);
                                col.add("" + (x - i) + (y - i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }

                i = 1;
            } else {
                while ((x + i < 9 && y + i < 9) && !"black".equals(ele[x + i][y + i].color)) {
                    if ("piece".equals(ele[x + i][y + i].type)) {
                        Buttons[x + i][y + i].setBackground(Color.BLUE);

                        if (red == -1) {
                            shuffle(x + i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y + i].setBackground(Color.BLUE);
                                col.add("" + (x + i) + (y + i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if ((x + i < 9 && y + i < 9) && "white".equals(ele[x + i][y + i].color)) {
                        Buttons[x + i][y + i].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x + i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y + i].setBackground(Color.RED);
                                col.add("" + (x + i) + (y + i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x + i < 9 && y - i > 0 && !"black".equals(ele[x + i][y - i].color)) {
                    if ("piece".equals(ele[x + i][y - i].type)) {
                        Buttons[x + i][y - i].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x + i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y - i].setBackground(Color.BLUE);
                                col.add("" + (x + i) + (y - i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if (x + i < 9 && y - i > 0 && "white".equals(ele[x + i][y - i].color)) {
                        Buttons[x + i][y - i].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x + i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y - i].setBackground(Color.RED);
                                col.add("" + (x + i) + (y - i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x - i > 0 && y + i < 9 && !"black".equals(ele[x - i][y + i].color)) {
                    if ("piece".equals(ele[x - i][y + i].type)) {
                        Buttons[x - i][y + i].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x - i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y + i].setBackground(Color.BLUE);
                                col.add("" + (x - i) + (y + i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;

                        }
                        i++;
                    }
                    if (x - i > 0 && y + i < 9 && "white".equals(ele[x - i][y + i].color)) {
                        Buttons[x - i][y + i].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x - i, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y + i].setBackground(Color.RED);
                                col.add("" + (x - i) + (y + i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x - i > 0 && y - i > 0 && !"black".equals(ele[x - i][y - i].color)) {
                    if ("piece".equals(ele[x - i][y - i].type)) {
                        Buttons[x - i][y - i].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x - i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y - i].setBackground(Color.BLUE);
                                col.add("" + (x - i) + (y - i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if (x - i > 0 && y - i > 0 && "white".equals(ele[x - i][y - i].color)) {
                        Buttons[x - i][y - i].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x - i, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x - i][y - i].setBackground(Color.RED);
                                col.add("" + (x - i) + (y - i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }

                i = 1;
            }
            if (red != 0) {
                color_flush();
                addcol();
            }
        }
    }

    class knight extends piece {

        public knight(String color) {
            super(color, "knight");

        }

        @Override

        public void perform_action(int x, int y) {
            if ("white".equals(this.color)) {
                if ((x + 2 < 9) && (y - 1 > 0)) {
                    if ("piece".equals(ele[x + 2][y - 1].type)) {
                        Buttons[x + 2][y - 1].setBackground(Color.BLUE);

                        if (red == 1) {
                            shuffle(x + 2, y - 1, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + 2][y - 1].setBackground(Color.BLUE);
                                col.add("" + (x + 2) + (y - 1) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        //return;
                    } else if ("black".equals(ele[x + 2][y - 1].color)) {
                        Buttons[x + 2][y - 1].setBackground(Color.RED);

                        if (red == 1) {
                            shuffle(x + 2, y - 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x + 2][y - 1].setBackground(Color.RED);
                                col.add("" + (x + 2) + (y - 1) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }

                    }

                }

                if ((x + 2 < 9) && (y + 1 < 9)) {
                    if ("piece".equals(ele[x + 2][y + 1].type)) {
                        Buttons[x + 2][y + 1].setBackground(Color.BLUE);

                        if (red == 1) {
                            shuffle(x + 2, y + 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x + 2][y + 1].setBackground(Color.BLUE);
                                col.add("" + (x + 2) + (y + 1) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }

                    } else if ("black".equals(ele[x + 2][y + 1].color)) {
                        Buttons[x + 2][y + 1].setBackground(Color.RED);

                        if (red == 1) {
                            shuffle(x + 2, y + 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x + 2][y + 1].setBackground(Color.RED);
                                col.add("" + (x + 2) + (y + 1) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }

                    }
                }

                if ((x - 2 > 0) && (y - 1 > 0)) {
                    if ("piece".equals(ele[x - 2][y - 1].type)) {
                        Buttons[x - 2][y - 1].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x - 2, y - 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x - 2][y - 1].setBackground(Color.BLUE);
                                col.add("" + (x - 2) + (y - 1) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }

                    } else if ("black".equals(ele[x - 2][y - 1].color)) {
                        Buttons[x - 2][y - 1].setBackground(Color.RED);

                        if (red == 1) {
                            shuffle(x - 2, y - 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x - 2][y - 1].setBackground(Color.RED);
                                col.add("" + (x - 2) + (y - 1) + "r");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }

                    }
                }

                if ((x - 2 > 0) && (y + 1 < 9)) {
                    if ("piece".equals(ele[x - 2][y + 1].type)) {
                        Buttons[x - 2][y + 1].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x - 2, y + 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x - 2][y + 1].setBackground(Color.BLUE);
                                col.add("" + (x - 2) + (y + 1) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    } else if ("black".equals(ele[x - 2][y + 1].color)) {
                        Buttons[x - 2][y + 1].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x - 2, y + 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x - 2][y + 1].setBackground(Color.RED);
                                col.add("" + (x - 2) + (y + 1) + "r");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }

                if ((x + 1 < 9) && (y - 2 > 0)) {
                    if ("piece".equals(ele[x + 1][y - 2].type)) {
                        Buttons[x + 1][y - 2].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x + 1, y - 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x + 1][y - 2].setBackground(Color.BLUE);
                                col.add("" + (x + 1) + (y - 2) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }

                    } else if ("black".equals(ele[x + 1][y - 2].color)) {
                        Buttons[x + 1][y - 2].setBackground(Color.RED);

                        if (red == 1) {
                            shuffle(x + 1, y - 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x + 1][y - 2].setBackground(Color.RED);
                                col.add("" + (x + 1) + (y - 2) + "r");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }

                if ((x + 1 < 9) && (y + 2 < 9)) {
                    if ("piece".equals(ele[x + 1][y + 2].type)) {
                        Buttons[x + 1][y + 2].setBackground(Color.BLUE);

                        if (red == 1) {
                            shuffle(x + 1, y + 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x + 1][y + 2].setBackground(Color.BLUE);
                                col.add("" + (x + 1) + (y + 2) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    } else if ("black".equals(ele[x + 1][y + 2].color)) {
                        Buttons[x + 1][y + 2].setBackground(Color.RED);

                        if (red == 1) {
                            shuffle(x + 1, y + 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x + 1][y + 2].setBackground(Color.RED);
                                col.add("" + (x + 1) + (y + 2) + "r");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }

                if ((x - 1 > 0) && (y - 2 > 0)) {
                    if ("piece".equals(ele[x - 1][y - 2].type)) {
                        Buttons[x - 1][y - 2].setBackground(Color.BLUE);
                        if (red != 0) {
                            shuffle(x - 1, y - 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x - 1][y - 2].setBackground(Color.BLUE);
                                col.add("" + (x - 1) + (y - 2) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    } else if ("black".equals(ele[x - 1][y - 2].color)) {
                        Buttons[x - 1][y - 2].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x - 1, y - 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x - 1][y - 2].setBackground(Color.RED);
                                col.add("" + (x - 1) + (y - 2) + "r");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }

                if ((x - 1 > 0) && (y + 2 < 9)) {
                    if ("piece".equals(ele[x - 1][y + 2].type)) {
                        Buttons[x - 1][y + 2].setBackground(Color.BLUE);
                        if (red == 1) {
                            shuffle(x - 1, y + 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x - 1][y + 2].setBackground(Color.BLUE);
                                col.add("" + (x - 1) + (y + 2) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    } else if ("black".equals(ele[x - 1][y + 2].color)) {
                        Buttons[x - 1][y + 2].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x - 1, y + 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = 1;
                                undo.doClick();

                                Buttons[x - 1][y + 2].setBackground(Color.RED);
                                col.add("" + (x - 1) + (y + 2) + "r");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }

            } else {
                if ((x + 2 < 9) && (y - 1 > 0)) {
                    if ("piece".equals(ele[x + 2][y - 1].type)) {
                        Buttons[x + 2][y - 1].setBackground(Color.BLUE);

                        if (red == -1) {
                            shuffle(x + 2, y - 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x + 2][y - 1].setBackground(Color.BLUE);
                                col.add("" + (x + 2) + (y - 1) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }

                    } else if ("white".equals(ele[x + 2][y - 1].color)) {
                        Buttons[x + 2][y - 1].setBackground(Color.RED);

                        if (red == -1) {
                            shuffle(x + 2, y - 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x + 2][y - 1].setBackground(Color.RED);
                                col.add("" + (x + 2) + (y - 1) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }

                    }

                }

                if ((x + 2 < 9) && (y + 1 < 9)) {
                    if ("piece".equals(ele[x + 2][y + 1].type)) {
                        Buttons[x + 2][y + 1].setBackground(Color.BLUE);

                        if (red == -1) {
                            shuffle(x + 2, y + 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x + 2][y + 1].setBackground(Color.BLUE);
                                col.add("" + (x + 2) + (y + 1) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }

                    } else if ("white".equals(ele[x + 2][y + 1].color)) {
                        Buttons[x + 2][y + 1].setBackground(Color.RED);

                        if (red == -1) {
                            shuffle(x + 2, y + 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x + 2][y + 1].setBackground(Color.RED);
                                col.add("" + (x + 2) + (y + 1) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }

                    }
                }

                if ((x - 2 > 0) && (y - 1 > 0)) {
                    if ("piece".equals(ele[x - 2][y - 1].type)) {
                        Buttons[x - 2][y - 1].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x - 2, y - 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x - 2][y - 1].setBackground(Color.BLUE);
                                col.add("" + (x - 2) + (y - 1) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }

                    } else if ("white".equals(ele[x - 2][y - 1].color)) {
                        Buttons[x - 2][y - 1].setBackground(Color.RED);

                        if (red == -1) {
                            shuffle(x - 2, y - 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x - 2][y - 1].setBackground(Color.RED);
                                col.add("" + (x - 2) + (y - 1) + "r");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }

                    }
                }

                if ((x - 2 > 0) && (y + 1 < 9)) {
                    if ("piece".equals(ele[x - 2][y + 1].type)) {
                        Buttons[x - 2][y + 1].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x - 2, y + 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x - 2][y + 1].setBackground(Color.BLUE);
                                col.add("" + (x - 2) + (y + 1) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    } else if ("white".equals(ele[x - 2][y + 1].color)) {
                        Buttons[x - 2][y + 1].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x - 2, y + 1, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x - 2][y + 1].setBackground(Color.RED);
                                col.add("" + (x - 2) + (y + 1) + "r");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }

                if ((x + 1 < 9) && (y - 2 > 0)) {
                    if ("piece".equals(ele[x + 1][y - 2].type)) {
                        Buttons[x + 1][y - 2].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x + 1, y - 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x + 1][y - 2].setBackground(Color.BLUE);
                                col.add("" + (x + 1) + (y - 2) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }

                    } else if ("white".equals(ele[x + 1][y - 2].color)) {
                        Buttons[x + 1][y - 2].setBackground(Color.RED);

                        if (red == -1) {
                            shuffle(x + 1, y - 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x + 1][y - 2].setBackground(Color.RED);
                                col.add("" + (x + 1) + (y - 2) + "r");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }

                if ((x + 1 < 9) && (y + 2 < 9)) {
                    if ("piece".equals(ele[x + 1][y + 2].type)) {
                        Buttons[x + 1][y + 2].setBackground(Color.BLUE);

                        if (red == -1) {
                            shuffle(x + 1, y + 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x + 1][y + 2].setBackground(Color.BLUE);
                                col.add("" + (x + 1) + (y + 2) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    } else if ("white".equals(ele[x + 1][y + 2].color)) {
                        Buttons[x + 1][y + 2].setBackground(Color.RED);

                        if (red == -1) {
                            shuffle(x + 1, y + 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x + 1][y + 2].setBackground(Color.RED);
                                col.add("" + (x + 1) + (y + 2) + "r");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }

                if ((x - 1 > 0) && (y - 2 > 0)) {
                    if ("piece".equals(ele[x - 1][y - 2].type)) {
                        Buttons[x - 1][y - 2].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x - 1, y - 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x - 1][y - 2].setBackground(Color.BLUE);
                                col.add("" + (x - 1) + (y - 2) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    } else if ("white".equals(ele[x - 1][y - 2].color)) {
                        Buttons[x - 1][y - 2].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x - 1, y - 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x - 1][y - 2].setBackground(Color.RED);
                                col.add("" + (x - 1) + (y - 2) + "r");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }

                if ((x - 1 > 0) && (y + 2 < 9)) {
                    if ("piece".equals(ele[x - 1][y + 2].type)) {
                        Buttons[x - 1][y + 2].setBackground(Color.BLUE);
                        if (red == -1) {
                            shuffle(x - 1, y + 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x - 1][y + 2].setBackground(Color.BLUE);
                                col.add("" + (x - 1) + (y + 2) + "b");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    } else if ("white".equals(ele[x - 1][y + 2].color)) {
                        Buttons[x - 1][y + 2].setBackground(Color.RED);
                        if (red == -1) {
                            shuffle(x - 1, y + 2, x, y);
                            flag = (flag + 1) % 2;
                            check();
                            if (red == 0) {
                                red = -1;
                                undo.doClick();

                                Buttons[x - 1][y + 2].setBackground(Color.RED);
                                col.add("" + (x - 1) + (y + 2) + "r");
                            } else {
                                undo.doClick();
                            }
                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }

            }
            if (red != 0) {
                color_flush();
                addcol();
            }
        }

    }

    class rook extends piece {

        public rook(String color) {
            super(color, "rook");
        }

        @Override
        public void perform_action(int x, int y) {
            int i = 1;
            if ("white".equals(this.color)) {
                while (x + i < 9 && !"white".equals(ele[x + i][y].color)) {
                    if ("piece".equals(ele[x + i][y].type)) {
                        Buttons[x + i][y].setBackground(Color.BLUE);
                        if (red == 1) {

                            shuffle(x + i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x + i][y].setBackground(Color.BLUE);
                                col.add("" + (x + i) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }

                    if (x + i < 9 && "black".equals(ele[x + i][y].color)) {
                        Buttons[x + i][y].setBackground(Color.RED);
                        if (red == 1) {
                            shuffle(x + i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey");

                                Buttons[x + i][y].setBackground(Color.RED);
                                col.add("" + (x + i) + (y) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }

                }
                i = 1;
                while (x - i > 0 && !"white".equals(ele[x - i][y].color)) {
                    if ("piece".equals(ele[x - i][y].type)) {
                        Buttons[x - i][y].setBackground(Color.BLUE);
                        if (red == 1) {

                            shuffle(x - i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x - i][y].setBackground(Color.BLUE);
                                col.add("" + (x - i) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if (x - i > 0 && "black".equals(ele[x - i][y].color)) {
                        Buttons[x - i][y].setBackground(Color.RED);
                        if (red == 1) {

                            shuffle(x - i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x - i][y].setBackground(Color.RED);
                                col.add("" + (x - i) + (y) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (y + i < 9 && !"white".equals(ele[x][y + i].color)) {
                    if ("piece".equals(ele[x][y + i].type)) {
                        Buttons[x][y + i].setBackground(Color.BLUE);
                        if (red == 1) {

                            shuffle(x, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y + i].setBackground(Color.BLUE);
                                col.add("" + (x) + (y + i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }
                    if (y + i < 9 && "black".equals(ele[x][y + i].color)) {
                        Buttons[x][y + i].setBackground(Color.RED);
                        if (red == 1) {

                            shuffle(x, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y + i].setBackground(Color.RED);
                                col.add("" + (x) + (y + i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (y - i > 0 && !"white".equals(ele[x][y - i].color)) {
                    if ("piece".equals(ele[x][y - i].type)) {
                        Buttons[x][y - i].setBackground(Color.BLUE);
                        if (red == 1) {

                            shuffle(x, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y - i].setBackground(Color.BLUE);
                                col.add("" + (x) + (y - i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }

                    if (y - i > 0 && "black".equals(ele[x][y - i].color)) {
                        Buttons[x][y - i].setBackground(Color.RED);
                        if (red == 1) {

                            shuffle(x, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y - i].setBackground(Color.RED);
                                col.add("" + (x) + (y - i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }

            } else {
                while (x + i < 9 && !"black".equals(ele[x + i][y].color)) {
                    if ("piece".equals(ele[x + i][y].type)) {
                        Buttons[x + i][y].setBackground(Color.BLUE);
                        if (red == -1) {

                            shuffle(x + i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x + i][y].setBackground(Color.BLUE);
                                col.add("" + (x + i) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }

                    if (x + i < 9 && "white".equals(ele[x + i][y].color)) {
                        Buttons[x + i][y].setBackground(Color.RED);
                        if (red == -1) {

                            shuffle(x + i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x + i][y].setBackground(Color.RED);
                                col.add("" + (x + i) + (y) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (x - i > 0 && !"black".equals(ele[x - i][y].color)) {
                    if ("piece".equals(ele[x - i][y].type)) {
                        Buttons[x - i][y].setBackground(Color.BLUE);
                        if (red == -1) {

                            shuffle(x - i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x - i][y].setBackground(Color.BLUE);
                                col.add("" + (x - i) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }

                    if (x - i > 0 && "white".equals(ele[x - i][y].color)) {
                        Buttons[x - i][y].setBackground(Color.RED);
                        if (red == -1) {

                            shuffle(x - i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x - i][y].setBackground(Color.RED);
                                col.add("" + (x - i) + (y) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (y + i < 9 && !"black".equals(ele[x][y + i].color)) {
                    if ("piece".equals(ele[x][y + i].type)) {
                        Buttons[x][y + i].setBackground(Color.BLUE);
                        if (red == -1) {

                            shuffle(x, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y + i].setBackground(Color.BLUE);
                                col.add("" + (x) + (y + i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }

                    if (y + i < 9 && "white".equals(ele[x][y + i].color)) {
                        Buttons[x][y + i].setBackground(Color.RED);
                        if (red == -1) {

                            shuffle(x, y + i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y + i].setBackground(Color.RED);
                                col.add("" + (x) + (y + i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
                i = 1;
                while (y - i > 0 && !"black".equals(ele[x][y - i].color)) {
                    if ("piece".equals(ele[x][y - i].type)) {
                        Buttons[x][y - i].setBackground(Color.BLUE);
                        if (red == -1) {

                            shuffle(x, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y - i].setBackground(Color.BLUE);
                                col.add("" + (x) + (y - i) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                    }

                    if (y - i > 0 && "white".equals(ele[x][y - i].color)) {
                        Buttons[x][y - i].setBackground(Color.RED);
                        if (red == -1) {

                            shuffle(x, y - i, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x][y - i].setBackground(Color.RED);
                                col.add("" + (x) + (y - i) + "r");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        break;
                    }
                }
            }
            if (red != 0) {
                color_flush();
                addcol();
            }
        }
    }

    class pawn extends piece {

        public pawn(String color) {
            super(color, "pawn");
        }

        @Override
        public void perform_action(int x, int y) {
            int i = 1;
            if ("white".equals(this.color)) {
                if (x == 2) {
                    while ("piece".equals(ele[x + i][y].type)) {
                        Buttons[x + i][y].setBackground(Color.BLUE);
                        if (red == 1) {

                            shuffle(x + i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x + i][y].setBackground(Color.BLUE);
                                col.add("" + (x + i) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                        if (i > 2) {
                            break;
                        }
                    }
                } else {
                    if (x + 1 < 9 && "piece".equals(ele[x + 1][y].type)) {
                        Buttons[x + 1][y].setBackground(Color.BLUE);
                        if((x+1)==8&&red==0)
                            Buttons[x + 1][y].setBackground(Color.CYAN);
                        if (red == 1) {

                            shuffle(x + 1, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = 1;
                                undo.doClick();
                                System.out.println("hey1");
                                 if((x+1)==8)
                                 {
                                  Buttons[x + 1][y].setBackground(Color.CYAN);
                                  col.add("" + (x + 1) + (y) + "c");
                                 }
                                 else
                                 {
                                   Buttons[x + 1][y].setBackground(Color.BLUE);
                                   col.add("" + (x + 1) + (y) + "b");
                                 }
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }
                if ((x + 1 >= 9 || y - 1 <= 0) && (y + 1 >= 9 || x - 1 <= 0)) {

                } else {
                    if (x + 1 < 9 && y - 1 > 0 && y + 1 < 9 && "piece".equals(ele[x + 1][y - 1].type) && ("piece".equals(ele[x + 1][y + 1].type))) {

                    } else {
                        if (x + 1 < 9 && y - 1 > 0 && "black".equals(ele[x + 1][y - 1].color)) {
                            Buttons[x + 1][y - 1].setBackground(Color.RED);
                             if((x+1)==8&&!("king".equals(ele[x+1][y-1].type))&&red==0)
                              Buttons[x + 1][y-1].setBackground(Color.CYAN);
                            if (red == 1) {

                                shuffle(x + 1, y - 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = 1;
                                    undo.doClick();
                                    System.out.println("hey1");
                                     if((x+1)==8&&!("king".equals(ele[x+1][y-1].type)))
                                     {
                                     Buttons[x + 1][y-1].setBackground(Color.CYAN);
                                      col.add("" + (x + 1) + (y - 1) + "c");
                                     }
                                     else
                                     {
                                    Buttons[x + 1][y - 1].setBackground(Color.RED);
                                    col.add("" + (x + 1) + (y - 1) + "r");
                                     }
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }

                        if ((x + 1 < 9 && y + 1 < 9) && "black".equals(ele[x + 1][y + 1].color)) {
                            Buttons[x + 1][y + 1].setBackground(Color.RED);
                             if((x+1)==8&&!("king".equals(ele[x+1][y+1].type))&&red==0)
                               Buttons[x + 1][y+1].setBackground(Color.CYAN);
                            if (red == 1) {

                                shuffle(x + 1, y + 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = 1;
                                    undo.doClick();
                                    System.out.println("hey1");

                                    if((x+1)==8&&!("king".equals(ele[x+1][y+1].type)))
                                     {
                                     Buttons[x + 1][y+1].setBackground(Color.CYAN);
                                  col.add("" + (x + 1) + (y + 1) + "c");
                                    }
                                     else
                                     {
                                    Buttons[x + 1][y + 1].setBackground(Color.RED);
                                    col.add("" + (x + 1) + (y + 1) + "r");
                                     }
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                    }
                }
            } else {

                if (x == 7) {
                    while ("piece".equals(ele[x - i][y].type)) {
                        Buttons[x - i][y].setBackground(Color.BLUE);
                        if (red == -1) {

                            shuffle(x - i, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                Buttons[x - i][y].setBackground(Color.BLUE);
                                col.add("" + (x - i) + (y) + "b");
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                        i++;
                        if (i > 2) {
                            break;
                        }
                    }
                } else {
                    if (x - 1 > 0 && "piece".equals(ele[x - 1][y].type)) {
                        Buttons[x - 1][y].setBackground(Color.BLUE);
                        if((x-1)==1&&red==0)
                             Buttons[x - 1][y].setBackground(Color.CYAN);
                        if (red == -1) {

                            shuffle(x - 1, y, x, y);
                            flag = (flag + 1) % 2;
                            check();

                            if (red == 0) {
                                red = -1;
                                undo.doClick();
                                System.out.println("hey1");

                                 if((x-1)==1)
                                 {
                                 Buttons[x - 1][y].setBackground(Color.CYAN);
                                 col.add("" + (x - 1) + (y) + "c");
                                 }
                                 else
                                 {
                                Buttons[x - 1][y].setBackground(Color.BLUE);
                                col.add("" + (x - 1) + (y) + "b");
                                 }
                            } else {
                                undo.doClick();
                            }

                            String str = "" + x + y + "";
                            al.add(str);
                            flag = (flag + 1) % 2;
                        }
                    }
                }
                if ((x - 1 <= 0 || y - 1 <= 0) && (y + 1 >= 9 || x - 1 <= 0)) {

                } else {
                    if (x - 1 > 0 && y - 1 > 0 && y + 1 < 9 && "piece".equals(ele[x - 1][y - 1].type) && ("piece".equals(ele[x - 1][y + 1].type))) {

                    } else {
                        if (x - 1 > 0 && y - 1 > 0 && "white".equals(ele[x - 1][y - 1].color)) {
                            Buttons[x - 1][y - 1].setBackground(Color.RED);
                                 if((x-1)==1&&!("king".equals(ele[x-1][y-1].type))&&red==0)
                                 Buttons[x - 1][y-1].setBackground(Color.CYAN);
                            if (red == -1) {

                                shuffle(x - 1, y - 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = -1;
                                    undo.doClick();
                                    System.out.println("hey1");

                                     if((x-1)==1&&!("king".equals(ele[x-1][y-1].type)))
                                 {
                                 Buttons[x - 1][y-1].setBackground(Color.CYAN);
                                 col.add("" + (x - 1) + (y-1) + "c");
                                 }
                                 else
                                 {
                                Buttons[x - 1][y-1].setBackground(Color.BLUE);
                                col.add("" + (x - 1) + (y-1) + "b");
                                 }
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }

                        if (x - 1 > 0 && y + 1 < 9 && "white".equals(ele[x - 1][y + 1].color)) {

                            Buttons[x - 1][y + 1].setBackground(Color.RED);
                              if((x-1)==1&&!("king".equals(ele[x-1][y+1].type))&&red==0)
                                 Buttons[x - 1][y+1].setBackground(Color.CYAN);
                            if (red == -1) {

                                shuffle(x - 1, y + 1, x, y);
                                flag = (flag + 1) % 2;
                                check();

                                if (red == 0) {
                                    red = -1;
                                    undo.doClick();
                                    System.out.println("hey1");
                                 if((x-1)==1&&!("king".equals(ele[x-1][y+1].type)))
                                 {
                                 Buttons[x - 1][y+1].setBackground(Color.CYAN);
                                 col.add("" + (x - 1) + (y+1) + "c");
                                 }
                                 else
                                 {
                                Buttons[x - 1][y+1].setBackground(Color.BLUE);
                                col.add("" + (x - 1) + (y+1) + "b");
                                 }
                                } else {
                                    undo.doClick();
                                }

                                String str = "" + x + y + "";
                                al.add(str);
                                flag = (flag + 1) % 2;
                            }
                        }
                    }

                }
            }
            if (red != 0) {
                color_flush();
                addcol();
            }

        }
    }
}

public class chess2 {

    public static void main(String[] args) {
        gui1 GUI = new gui1();

    }
}


