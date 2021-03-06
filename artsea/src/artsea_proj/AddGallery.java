package artsea_proj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddGallery {

	JFrame frame;
	JLabel id,name,location;
	JTextField tname,tid,tlocation;
	JTextArea Display;
	JButton add,back;
	AddGallery(JFrame frame)
	{
		this.frame=frame;
		frame.setTitle("Add Gallery");
		add=new JButton("INSERT");
        id = new JLabel("Id");
        id.setFont(new Font("Arial", Font.PLAIN, 15));
        id.setSize(100, 20);
        id.setLocation(100, 100);
        tid = new JTextField();
        tid.setFont(new Font("Arial", Font.PLAIN, 15));
        tid.setSize(190, 20);
        tid.setLocation(200, 100);
        frame.add(tid);
        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 15));
        name.setSize(100, 20);
        name.setLocation(100, 130);
        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 130);
        frame.add(tname);
        location = new JLabel("Location");
        location.setFont(new Font("Arial", Font.PLAIN, 15));
        location.setSize(100, 20);
        location.setLocation(100, 160);
        tlocation = new JTextField();
        tlocation.setFont(new Font("Arial", Font.PLAIN, 15));
        tlocation.setSize(190, 20);
        tlocation.setLocation(200, 160);
        Display= new JTextArea();
        Display.setBounds(100, 280, 360, 120);
        Display.setEditable(false);
        Display.setBackground(Color.WHITE);
       
      add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String idstr="ID	:"+tid.getText();
				 String namestr="Name	:"+tname.getText();
				 String locationstr="Location	:"+tlocation.getText();
				
				 Connection con;
					Statement s;
					ResultSet rs;
					try {
			   		 con=ConnectionManager.getConnection();
						s = con.createStatement();
						rs = s.executeQuery("select * from gallery");
						
						int array[]=new int[20];
						int i=0,j,flag=0;
						while(rs.next())
						{
							array[i]=rs.getInt(1);
							i++;
						}
						for(j=0;j<array.length;j++)
						{
							if(array[j]==Integer.parseInt(tid.getText()))
							{
								flag=1;
								break;
							}	      
						}
						if(flag==0) {
						String query="insert into gallery values("+tid.getText()+",'"+tname.getText()+"','"+tlocation.getText()+"')";
						s.executeQuery(query); 
						s.executeQuery("commit");
						 s.close();
						 con.close();
						 Display.setText(idstr+"\n"+namestr+"\n"+locationstr+"\n"+"Row Added Successfully");
						}
						else
						{
							Display.setText("G_id already exists Try Again!");
						}
					}
					catch(Exception e1)
					{
						e1.printStackTrace();
					}
					 
					
			}
		});
       add.setBounds(230,210,100,50);
       back=new JButton("GoBack");
		back.setBounds(500,500,95,50); 
		 add.setBackground(Color.white);
		 back.setBackground(Color.white);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new Gallery(frame);
			}
		});
		frame.add(back);
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		frame.getContentPane().setBackground(new Color(125, 193, 232));
       frame.add(Display);
        frame.add(add);
        frame.add(tlocation);
        frame.add(location);
        frame.add(name);
        frame.add(id);
        
		
	}
	
}