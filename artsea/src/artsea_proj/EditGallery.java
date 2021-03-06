package artsea_proj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EditGallery {

	JFrame frame;
	JLabel id,name,location;
	JTextField tname,tid,tlocation;
	JTextArea Display;
	JButton edit,back;
	EditGallery(JFrame frame)
	{
			this.frame=frame;
			frame.setTitle("Edit Gallery");
			edit=new JButton("UPDATE");
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
	       
	      edit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 String idstr="ID	:"+tid.getText();
					 String namestr="Name	:"+tname.getText();
					 String locationstr="Location	:"+tlocation.getText();
					int flag=0;
					 Connection con;
						Statement s;
						ResultSet rs;
						try {
				   		 con=ConnectionManager.getConnection();
							s = con.createStatement();
							String query= "select g_id from gallery ";
							rs = s.executeQuery(query);
							
							while(rs.next())
							{
							String a=Integer.toString(rs.getInt(1));
							if(a.equals(tid.getText()))
							{
								flag=1;								
								Display.setText(idstr+"\n"+namestr+"\n"+locationstr+"\n"+"Row Edited Successfully");
								
							break;
							}
							
							}
							if(flag==0)
							{
								Display.setText("Entered gallery id doesn't exist try again !");	
	
							}
							else {
							String query1="UPDATE gallery SET g_name='"+tname.getText()+"' WHERE g_id ="+tid.getText()+"";
							String query2="UPDATE gallery SET location='"+tlocation.getText()+"' WHERE g_id ="+tid.getText()+"";
							s.executeQuery(query1);
							s.executeQuery(query2);
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
	       edit.setBounds(230,210,100,50);
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
			 edit.setBackground(Color.white);
			 back.setBackground(Color.white);
			back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			edit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			frame.getContentPane().setBackground(new Color(125, 193, 232));
	       frame.add(Display);
	        frame.add(edit);
	        frame.add(tlocation);
	        frame.add(location);
	        frame.add(name);
	        frame.add(id);
		
	}
	
}
