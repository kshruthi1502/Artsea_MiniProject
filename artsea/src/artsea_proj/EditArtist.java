package artsea_proj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class EditArtist {

	JFrame frame;
	JLabel id,name,phn_no,address;
	JTextField tname,tid,tphn_no,taddress;
	JButton back,add;
	JTextArea Display;
	EditArtist(JFrame frame,String galleryid)
	{
		this.frame=frame;
		frame.setTitle("Edit Artist");
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
					new Artist(frame,galleryid);
				}
			});
			frame.add(back);
			back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			add=new JButton("UPDATE");
			 add.setBounds(230,230,100,50);
			 frame.add(add);
			 add.setBackground(Color.white);
			 back.setBackground(Color.white);
			 
			 
				
		add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					String warning="";
					
					warning += validatePhone(tphn_no.getText());
					warning+=validateaid();
					String idstr="ID	:"+tid.getText();
					 String namestr="Name	:"+tname.getText();
					 String phnstr="CPhn	:"+tphn_no.getText();
					 String addressstr="Address	:"+taddress.getText();
					 
					if("".equals(validateaid()) && "".equals(validatePhone(tphn_no.getText())) )
					{
						 Connection con;
							Statement s;
						
							try {
							 con=ConnectionManager.getConnection();
								s = con.createStatement();
								String query1="UPDATE artist SET a_name='"+tname.getText()+"' WHERE a_id ="+tid.getText()+"";
								String query2="UPDATE artist SET a_phone='"+tphn_no.getText()+"' WHERE a_id ="+tid.getText()+"";
								String query3="UPDATE artist SET a_address='"+taddress.getText()+"' WHERE a_id ="+tid.getText()+"";
								s.executeQuery(query1);
								s.executeQuery(query2);
								s.executeQuery(query3);
								s.executeQuery("commit");
								
								 Display.setText(idstr+"\n"+namestr+"\n"+phnstr+"\n"+addressstr+"\n"+"Row updated successfully");
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
			frame.getContentPane().setBackground(new Color(125, 193, 232));
			add.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
	int numOfDigits(String num) {
		  int number = Integer.parseInt(num);
		  int digits = 0;
		  while (number > 0) {
		   number = number / 10;
		   ++ digits;
		  }
		  return digits;
		 }
	String validatePhone (String number) 
	{
		  for (int i = 0; i < number.length(); i ++) {
		   if(!Character.isDigit(number.charAt(i))) {
			   			return "Enter only Digits";
		  }
		  }
		  if (number.length() != 10) {
			   return "Phone number should have 10 digits.\n";
			  }
		  return "";
		 
	}
	String validateaid()
	{
		Connection con;
		Statement s;
		ResultSet rs;
		int flag=0;
		try {
			 con=ConnectionManager.getConnection();
				s = con.createStatement();
				rs = s.executeQuery("select * from artist");
		while(rs.next())
		{
		String a=Integer.toString(rs.getInt(1));
		if(a.equals(tid.getText()))
		{
			flag=1;	
				break;
		}
		
		}
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
		if(flag==0)
		{
		
				return "Entered a_id doesn't exist try Again !";
		}
		return "";
	}	
	String validateaidingid(String idval,String galleryid)
	{
		Connection con;
		Statement s;
		ResultSet rs;
		String artistidingid ="";
		try
		{
			con=ConnectionManager.getConnection();
			s = con.createStatement();
			String query= "select a.a_id  from gallery g,have h,artist a where g.g_id="+galleryid+" and g.g_id=h.gid and h.aid=a.a_id ";
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
				artistidingid +="artist id already exists in specified gallery  ";
			}
			s.close();
			con.close();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}	
		return artistidingid;	
	}
}