package artsea_proj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class Painting {
	JFrame frame;
	JButton viewp,addp,editp,delp,back;
	Label ll;
	String id;
	Painting(JFrame frame,String artistid)
	{

		this.frame=frame;
		frame.setTitle("Painting");
		ll = new Label();
		ll.setAlignment(Label.CENTER);
		ll.setBounds(110,250,500,200);
		ll.setFont(new Font("Platino",Font.ITALIC,40));
		ll.setText("PAINTING");
		Panel p=new Panel(new GridLayout(4,1));
		addp= new JButton("Add Painting");
		editp=new JButton("Edit Painting");
		delp=new JButton("Delete Painting");
		viewp=new JButton("View Painting");
		back=new JButton("Goback");
		back.setBounds(500,150,95,50);  
		p.add(viewp);
		p.add(addp);
		p.add(editp);
		p.add(delp);
		 delp.setBackground(Color.white);
		 back.setBackground(Color.white);
		 viewp.setBackground(Color.white);
		 addp.setBackground(Color.white);
		 editp.setBackground(Color.white);
		p.setBounds(5,30,200,100);
		frame.add(back);
		frame.add(ll);
		frame.add(p);
		frame.setTitle("Painting");
		frame.setLayout(null);
		frame.setSize(700,700);
		frame.getContentPane().setBackground(new Color(255, 234, 156));
		frame.setVisible(true);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new Painting_aid(frame);
			}
		});
	viewp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new ViewPainting(frame,artistid);
			}
		});
	
		addp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new AddPainting(frame,artistid);
			}
		});
		
		editp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
					frame.repaint();
			new EditPainting(frame,artistid);
			}
		});
		
		delp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
			new DeletePainting(frame,artistid);
			}
		});
		
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		viewp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		editp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		delp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		addp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
	}
}
