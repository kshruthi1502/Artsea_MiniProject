package artsea_proj;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class AddCustomer {

	JFrame frame;
	JLabel id,name,phn_no,address;
	JTextField tname,tid,tphn_no,taddress;
	JButton back,add;
	JTextArea Display;
	AddCustomer(JFrame frame)
	{
		this.frame=frame;
		 id = new JLabel("Id");
	        id.setFont(new Font("Arial", Font.PLAIN, 15));
	        id.setSize(100, 20);
	        id.setLocation(100, 100);
	        tid = new JTextField();
	        tid.setFont(new Font("Arial", Font.PLAIN, 15));
	        tid.setSize(190, 20);
	        tid.setLocation(200, 100);
	        frame.add(tid);
	        frame.add(id);
	        name = new JLabel("Name");
	        name.setFont(new Font("Arial", Font.PLAIN, 15));
	        name.setSize(100, 20);
	        name.setLocation(100, 130);
	        tname = new JTextField();
	        tname.setFont(new Font("Arial", Font.PLAIN, 15));
	        tname.setSize(190, 20);
	        tname.setLocation(200, 130);
	        frame.add(tname);
	        frame.add(name);
	        phn_no=new JLabel("Phone_no");
	        phn_no.setFont(new Font("Arial", Font.PLAIN, 15));
	        phn_no.setSize(100, 20);
	        phn_no.setLocation(100, 160);
	        tphn_no = new JTextField();
	        tphn_no.setFont(new Font("Arial", Font.PLAIN, 15));
	        tphn_no.setSize(190, 20);
	        tphn_no.setLocation(200, 160);
	        frame.add(tphn_no);
	        frame.add(phn_no);
	        address = new JLabel("Address");
	        address.setFont(new Font("Arial", Font.PLAIN, 15));
	        address.setSize(100, 20);
	        address.setLocation(100, 190);
	        taddress = new JTextField();
	        taddress.setFont(new Font("Arial", Font.PLAIN, 15));
	        taddress.setSize(190, 20);
	        taddress.setLocation(200, 190);
	        frame.add(address);
	        frame.add(taddress);
	        Display= new JTextArea();
	        Display.setBounds(100, 300, 360, 120);
	        Display.setEditable(false);
	        Display.setBackground(Color.WHITE);
	       frame.add(Display);
	        back=new JButton("GoBack");
			back.setBounds(500,500,95,50);  
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.getContentPane().removeAll();
					frame.repaint();
					new Customer(frame);
				}
			});
			frame.add(back);
			frame.setTitle("Add Customer");
			frame.getContentPane().setBackground(new Color(125, 193, 232));
			back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			add=new JButton("INSERT");
			 add.setBounds(230,230,100,50);
			 frame.add(add);
			 add.setBackground(Color.white);
			 back.setBackground(Color.white);
			 add.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 String idstr="ID	:"+tid.getText();
						 String namestr="Name	:"+tname.getText();
						 String phnstr="CPhn	:"+tphn_no.getText();
						 String addressstr="Address	:"+taddress.getText();

							String warning="";
							
							warning += validatePhone(tphn_no.getText());
							warning+=validatecid(tid.getText());
						if(("".equals(validatecid(tid.getText())) && "".equals(validatePhone(tphn_no.getText())))
								)
						{
						 Connection con;
							Statement s;
							
							try {
					   		 con=ConnectionManager.getConnection();
								s = con.createStatement();
								String query1="insert into customer values("+tid.getText()+",'"+tname.getText()+"','"+taddress.getText()+"','"+tphn_no.getText()+"')";
								s.executeQuery(query1); 
								s.executeQuery("commit");
								 s.close();
								 con.close();
								 Display.setText(idstr+"\n"+namestr+"\n"+phnstr+"\n"+addressstr+"\n"+"Row added successfully");
								
							}
							catch(Exception e1)
							{
								e1.printStackTrace();
							}
							 
						}
						else
						{
							Display.setText(warning);
						}
					}
				});
	}
	String validatecid(String idval)
	{
		Connection con;
		Statement s;
		ResultSet rs;
		String customerid ="";
		try
		{
			con=ConnectionManager.getConnection();
			s = con.createStatement();
			String query="select * from customer";
			rs = s.executeQuery(query);
			int array[]=new int[20];
			int i=0,j,flag=0;
			while(rs.next())
			{
				array[i]=rs.getInt(1);
				i++;
			}
			for(j=0;j<array.length;j++)
			{
				if(array[j]==Integer.parseInt(idval))
				{
					
					flag=1;
					break;
				}	      
			}
			if(flag==1)
			{
				customerid +="customer id already exists";
			}
			s.close();
			con.close();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}	
		return customerid;
		
	}
	String validatePhone (String number) 
	{
		  for (int i = 0; i < number.length(); i ++) {
		   if(!Character.isDigit(number.charAt(i)))
			   return "Enter only digits.\n";
		  }
		  if (number.length()!= 10) {
		   return "Phone number should have 10 digits.\n";
		  }
		  
		  return "";
		 
	}
}
