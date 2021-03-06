//Homepage
package artsea_proj;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Homepage  {

	Label ll;
	public JButton gallery,artist,painting,customer;
	public JFrame frame;
	public Homepage(JFrame frame)
	{
		this.frame=frame;
	Label	ll = new Label();
		ll.setAlignment(Label.CENTER);
		ll.setBounds(110,250,500,200);
		ll.setFont(new Font("Platino",Font.ITALIC,40));
		ll.setText("ART SEA");
		frame.add(ll);
		gallery = new JButton("Gallery");
		artist = new JButton("Artist");
		painting= new JButton("Painting");
		customer=new JButton("Customer");
		 gallery.setBackground(Color.white);
		 artist.setBackground(Color.white);
		 painting.setBackground(Color.white);
		 customer.setBackground(Color.white);
		Panel p=new Panel(new GridLayout(4,1));
		p.add(gallery);
		p.add(artist);
		p.add(painting);
		p.add(customer);
		p.setBounds(5,30,200,100);
		frame.add(p);
		frame.addWindowListener(new WindowAdapter(){
		public void
		windowClosing(WindowEvent we)
		{
			System.exit(0);
		}
	});
		gallery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new Gallery(frame);
			}
		});
		artist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new Artist_gid(frame);
			}
		});
		painting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new Painting_aid(frame);
			}
		});
		customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.repaint();
				new Customer(frame);
			}
		});
		gallery.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		artist.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		customer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		painting.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		frame.getContentPane().setBackground(new Color(255, 234, 156));
		frame.setFont(new Font("Sanserif",Font.BOLD,14));
		frame.setTitle("Homepage");
		frame.setLayout(null);
		frame.setSize(700,700);
		frame.setVisible(true);
}
}
	
	

