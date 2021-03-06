package artsea_proj;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
public class DeletePainting{
	JFrame frame;
	JLabel id,name,location;
	JTextField tname,tid,tlocation;
	JTextArea Display;
	JButton delete,back;
	DeletePainting(JFrame frame,String artistid)
	{
			this.frame=frame;
			frame.setTitle("Delete Painting");
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
							String query= "select p.p_id,title,p_year,p_type from artist a,paintby pb,painting p where a.a_id="+artistid+" and a.a_id=pb.aid and pb.pid=p.p_id ";
							rs = s.executeQuery(query);
		
							while(rs.next())
							{
							String a=Integer.toString(rs.getInt(1));
							String b=rs.getString(2);
							String c=Integer.toString(rs.getInt(3));
							String d=rs.getString(4);
							if(a.equals(tid.getText()))
							{
								flag=1;								
							Display.setText(idstr+"\n"+"Title	:"+b+"\n"+"P_Year	:"+c+"\n"+"PType	:"+d+"\n"+"Row Deleted Successfully");
							break;
							}
							
							}
							if(flag==0)
							{
								Display.setText("Entered p_id doesn't exist in specified artistid try Again !");	
	
							}
							else
							{
								String query1="DELETE FROM painting  WHERE p_id ="+tid.getText()+"";
								s.executeQuery(query1); 
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
					new Painting(frame,artistid);
				}
			});
			frame.add(back);
			 delete.setBackground(Color.white);
			 back.setBackground(Color.white);
			back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			delete.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			frame.getContentPane().setBackground(new Color(125, 193, 232));
	       frame.add(Display);
	        frame.add(delete);
	     
	        frame.add(id);
		
	}
}

