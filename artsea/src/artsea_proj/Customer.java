package artsea_proj;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Customer
{
	public JButton viewc,addc,editc,delc,back;
	Label ll;
	JFrame frame;
	Customer(JFrame frame)
	{
		this.frame=frame;
		frame.setTitle("Customer");
		ll = new Label();
		ll.setAlignment(Label.CENTER);
		ll.setBounds(110,250,500,200);
		ll.setFont(new Font("Platino",Font.ITALIC,40));
		ll.setText("CUSTOMER");
		Panel p=new Panel(new GridLayout(4,1));
		addc = new JButton("Add Customer");
		editc=new JButton("Edit Customer");
		delc=new JButton("Delete Customer");
		viewc=new JButton("View Customer");
		back=new JButton("GoBack");
		back.setBounds(500,150,95,50);  
		 viewc.setBackground(Color.white);
		 addc.setBackground(Color.white);
		 editc.setBackground(Color.white);
		 delc.setBackground(Color.white);
		 back.setBackground(Color.white);
		p.add(viewc);
		p.add(addc);
		p.add(editc);
		p.add(delc);
		frame.add(back);
		
		p.setBounds(5,30,200,100);
		frame.add(ll);
		frame.add(p);
		frame.setTitle("Customer");
		frame.setLayout(null);
		frame.setSize(700,700);
		
	frame.getContentPane().setBackground(new Color(161, 222, 111));
		
		frame.setVisible(true);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new Homepage(frame);
			}
		});
		
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		addc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		editc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		delc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		viewc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		viewc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
			new ViewCustomer(frame);
			}
		});
		
		addc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new AddCustomer(frame);
			}
		});
		
		editc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
			new EditCustomer(frame);
			}
		});
		
		delc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
			new DeleteCustomer(frame);
			}
		});
		
		
	}
}
