package artsea_proj;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
public class DeleteGallery {
	JFrame frame;
	JLabel id,name,location;
	JTextField tname,tid,tlocation;
	JTextArea Display;
	JButton delete,back;
	DeleteGallery(JFrame frame)
	{
			this.frame=frame;
			frame.setTitle("Delete Gallery");
			delete=new JButton("DELETE");
	        id = new JLabel("Id");
	        id.setFont(new Font("Arial", Font.PLAIN, 15));
	        id.setSize(100, 20);
	        id.setLocation(100, 100);
	        tid = new JTextField();
	        tid.setFont(new Font("Arial", Font.PLAIN, 15));
	        tid.setSize(190, 20);
	        tid.setLocation(200, 100);
	        frame.add(tid);
	        
	        Display= new JTextArea();
	        Display.setBounds(100, 280, 360, 120);
	        Display.setEditable(false);
	        Display.setBackground(Color.WHITE);
	       
	      delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 String idstr="ID	:"+tid.getText();
					
					int flag=0;
					 Connection con;
						Statement s;
						ResultSet rs;
						try {
				   		 con=ConnectionManager.getConnection();
							s = con.createStatement();
							rs = s.executeQuery("select * from gallery");
		
							while(rs.next())
							{
							String a=Integer.toString(rs.getInt(1));
							String b=rs.getString(2);
							String c=rs.getString(3);
							if(a.equals(tid.getText()))
							{
								flag=1;								
							Display.setText(idstr+"\n"+"Name	:"+b+"\n"+"Location	:"+c+"\n"+"Row Deleted Successfully");
							break;
							}
							
							}
							if(flag==0)
							{
								Display.setText("Entered g_id doesn't exist try Again !");	
	
							}
							else
							{
								String query="DELETE FROM gallery  WHERE g_id ="+tid.getText()+"";
								s.executeQuery(query); 
								s.executeQuery("commit");
								
								
							}
							s.close();
							 con.close();
						}
						catch(Exception e1)
						{
							e1.printStackTrace();
						}
						 
				}
			});
	       delete.setBounds(230,210,100,50);
	       back=new JButton("GoBack");
			back.setBounds(500,500,95,50);  
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.getContentPane().removeAll();
					frame.repaint();
					new Gallery(frame);
				}
			});
			frame.add(back);
			back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			delete.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			frame.getContentPane().setBackground(new Color(125, 193, 232));
			frame.add(Display);
	        frame.add(delete);
	     
	        frame.add(id);
		
	}
}
