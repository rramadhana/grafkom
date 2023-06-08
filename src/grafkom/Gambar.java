/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package grafkom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author User
 */

public class Gambar extends JFrame{
    
    private final JPanel panel1;
    private final JPanel panel2;
    private final JPanel panel3;
    
    private Bentuk shaping = new Bentuk();
    Color lineColor = Color.BLACK;
    Color fillColor = Color.WHITE;

    Box box = new Box(BoxLayout.Y_AXIS);
    JLabel lben = new JLabel("Bentuk : ");
    JLabel lstr = new JLabel("Tipe Line : ");
    JLabel lteb = new JLabel("Tebal : ");

    JLabel ltrans = new JLabel("Translasi");
    JLabel tspace = new JLabel("   ");
    JLabel tspace2 = new JLabel("   ");
    JLabel ltransx = new JLabel(" X : ");
    JLabel ltransy = new JLabel(" Y : ");
    JSpinner intransx = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));
    JSpinner intransy = new JSpinner(new SpinnerNumberModel(0, -500, 500, 1));
    JButton transbtn = new JButton("Translasi");

    JLabel lScale = new JLabel("Scaling : ");
    JLabel sspace = new JLabel("   ");
    JSpinner jScale = new JSpinner(new SpinnerNumberModel(1, 0.25, 2, 0.25));
    JButton btnScale = new JButton("Scaling");

    JLabel lrot = new JLabel("Rotasi : ");
    JLabel rspace = new JLabel("   ");
    JSpinner inrot = new JSpinner(new SpinnerNumberModel(0, 0, 360, 1));
    JButton rotbtn = new JButton("Rotasi");

    JLabel lskew = new JLabel("Shear/Skew");
    JLabel skspace = new JLabel("   ");
    JLabel lskewx = new JLabel(" X : ");
    JLabel lskewy = new JLabel(" Y : ");
    JSpinner inskewx = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
    JSpinner inskewy = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
    JButton skewbtn = new JButton("Shear");

    String ObjDat[][] = {{"Drawline", "C:\\Users\\rayra\\Documents\\grafika\\GAFKOM-C-9\\src\\grafkom\\icon\\pencil.png"},
            {"Square", "C:\\Users\\rayra\\Documents\\grafika\\GAFKOM-C-9\\src\\grafkom\\icon\\square.png"},
            {"Rectangle", "C:\\Users\\rayra\\Documents\\grafika\\GAFKOM-C-9\\src\\grafkom\\icon\\rectangle.png"},
            {"Triangle", "C:\\Users\\rayra\\Documents\\grafika\\GAFKOM-C-9\\src\\grafkom\\icon\\segitiga.png"},
            {"Circle", "C:\\Users\\rayra\\Documents\\grafika\\GAFKOM-C-9\\src\\grafkom\\icon\\circle.png"},
            {"Oval", "C:\\Users\\rayra\\Documents\\grafika\\GAFKOM-C-9\\src\\grafkom\\icon\\oval.png"},
            {"Line", "C:\\Users\\rayra\\Documents\\grafika\\GAFKOM-C-9\\src\\grafkom\\icon\\line.png"}
    };

    String CusLineDat[][] = {{"Line", "C:\\Users\\rayra\\Documents\\grafika\\GAFKOM-C-9\\src\\grafkom\\icon\\dashline.png"},
            {"Dash", "C:\\Users\\rayra\\Documents\\grafika\\GAFKOM-C-9\\src\\grafkom\\icon\\dashdotline.png"},
            {"Dot", "C:\\Users\\rayra\\Documents\\grafika\\GAFKOM-C-9\\src\\grafkom\\icon\\dotline.png"},

    };

    String StrDat[] = {"1f", "3f", "5f", "7f"};


    public Gambar() {
        setTitle("grafkom kelompok 9");
        setSize(1250, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        container.add(panel1, BorderLayout.SOUTH);
        container.add(panel2, BorderLayout.NORTH);
        container.add(panel3, BorderLayout.WEST);
        container.add(shaping, BorderLayout.CENTER);

      
        box.add(ltrans);
        box.add(ltransx);
        box.add(intransx);
        box.add(tspace2);
        box.add(ltransy);
        box.add(intransy);

        box.add(transbtn);
        box.add(tspace);
        transbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesTranslasi();
            }
        });
        box.add(lrot);
        box.add(inrot);
        box.add(rotbtn);
        box.add(rspace);
        rotbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesRotasi();
            }
        });
 
        box.add(lScale);
        box.add(jScale);
        box.add(btnScale);
        box.add(sspace);
        btnScale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesScaling();
            }
        });

        box.add(lskew);
        box.add(lskewx);
        box.add(inskewx);
        box.add(skspace);
        box.add(lskewy);
        box.add(inskewy);
        box.add(skewbtn);
        skewbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!getSkewX().equals("0") && !getSkewY().equals("0")){
                    JOptionPane.showMessageDialog(null, "Pilih salah satu sisi (X/Y)");
                } else {
                    prosesSkew();
                }
            }
        });
        
        panel2.add(lben);
        for (int i = 0; i < ObjDat.length; i++) {
            makeObjBtn(ObjDat[i][1], ObjDat[i][0]);
        }

        panel2.add(lteb);
        for (int i = 0; i < StrDat.length; i++) {
            makeStrLineBtn(StrDat[i]);
        }

        panel2.add(lstr);
        for (int i = 0; i < CusLineDat.length; i++) {
            makeCusLineBtn(CusLineDat[i][1], CusLineDat[i][0]);
        }
      

        JButton btnFillColor = new JButton("Fill");
        Image fillcl;
        try {
            fillcl = ImageIO.read(new File("")).getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            btnFillColor.setIcon(new ImageIcon(fillcl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel1.add(btnFillColor);
        btnFillColor.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent evt) {
                Color color = JColorChooser.showDialog(Gambar.this, "Choose a color", fillColor);
                if (color != null) { // new color selected
                    fillColor = color;
                    shaping.fillColor = color;
                    shaping.lineColor = Color.black;
                }
            }
        });

        JButton btnLineColor = new JButton("Line") ;
        Image linecl;
        try {
            linecl = ImageIO.read(new File("")).getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            btnLineColor.setIcon(new ImageIcon(linecl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel1.add(btnLineColor);
        btnLineColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Color color = JColorChooser.showDialog(Gambar.this, "Choose a color", lineColor);
                if (color != null) {
                    lineColor = color;
                    shaping.lineColor = color;
                    shaping.fillColor = Color.white;
                }
            }
        });

        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(75,35));
        panel1.add(clearButton);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AffineTransform af = new AffineTransform();
                shaping.graphics2D.setTransform(af);
                shaping.clearAll();
            }
        });

        panel3.add(box);

    }

    public void makeObjBtn(String path, String object) {
        Image scaled;
        JButton objButton = new JButton();
        objButton.setPreferredSize(new Dimension(50, 50));
        try {
            scaled = ImageIO.read(new File(path)).getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            objButton.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel2.add(objButton);
        objButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shaping.selected = 1;
                shaping.objectType = object;
            }
        });


    }

    public void makeCusLineBtn(String path, String object) {
        Image scaled;
        JButton objButton = new JButton();
        objButton.setPreferredSize(new Dimension(50, 50));
        try {
            scaled = ImageIO.read(new File(path)).getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            objButton.setIcon(new ImageIcon(scaled));
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel2.add(objButton);
        objButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shaping.selected = 1;
                shaping.lineType = object;
//                System.out.println(object);
            }
        });


    }

    public void makeStrLineBtn(String object) {
        JButton objButton = new JButton(object);
        objButton.setPreferredSize(new Dimension(50, 50));
        panel2.add(objButton);
        objButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shaping.selected = 1;
                shaping.strokeType = object;            
            }
        });
    }

    public void prosesTranslasi() {
        try{
            String sX, sY;
            int x, y;
            sX = getTransX();
            sY = getTransY();

            x = Integer.parseInt(sX);
            y = Integer.parseInt(sY);

            AffineTransform af = new AffineTransform();
            af.translate(x, -y);
            shaping.graphics2D.transform(af);
            shaping.redrawingObjek();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void prosesScaling() {
        try {
            String sScale;
            double scale;
            sScale = getScale();
            scale = Double.parseDouble(sScale);

            AffineTransform af = new AffineTransform();
            af.translate(-(shaping.midX * (scale - 1)), -(shaping.midY * (scale - 1)));
            af.scale(scale, scale);
            shaping.clearAll();
            shaping.graphics2D.transform(af);
            shaping.redrawingObjek();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void prosesRotasi() {
        try {
            String sRot;
            double rot;
            sRot = getRot();
            rot = Math.toRadians(Double.parseDouble(sRot));
            AffineTransform af = new AffineTransform();
            af.rotate(rot, shaping.midX, shaping.midY);
            shaping.graphics2D.transform(af);
            shaping.redrawingObjek();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void prosesSkew(){
        String skewX, skewY;
        double x, y;
        skewX = getSkewX();
        skewY = getSkewY();

        x = Double.parseDouble(skewX);
        y = Double.parseDouble(skewY);

        int jarakX, jarakY;
        if(x == 0){
            jarakX = 0;
            jarakY = (int) shaping.midY*2;
        } else {
            jarakX = (int) -shaping.midX/2;
            jarakY = 0;
        }

        AffineTransform af = new AffineTransform();
        af.translate(jarakX, -jarakY);
        af.shear(x, y);
        shaping.clearAll();
        shaping.graphics2D.transform(af);
        shaping.redrawingObjek();
    }

    public String getTransX() {
        return String.valueOf(intransx.getValue());
    }

    public String getTransY() {
        return String.valueOf(intransy.getValue());
    }

    public String getScale() {
        return String.valueOf(jScale.getValue());
    }

    public String getRot() {
        return String.valueOf(inrot.getValue());
    }

    public String getSkewX() {
        return String.valueOf(inskewx.getValue());
    }

    public String getSkewY() {
        return String.valueOf(inskewy.getValue());
    }
}