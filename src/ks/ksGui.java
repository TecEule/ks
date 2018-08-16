package ks;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.RenderingHints.Key;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import uz.uzPanel;
import vl.vlPanel;
import wa.waPanel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import net.miginfocom.swing.MigLayout;
import rf.RssFeedPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ksGui extends JFrame implements Runnable {

	RssFeedPanel rssNtv;
	RssFeedPanel rssWelt;
	RssFeedPanel rssCnn;
	RssFeedPanel rssKicker;
	uzPanel uz;
	waPanel weatherPanel;
	vlPanel vl;

	Thread threadWetter;
	Thread threadRss;
	Thread threadVerkehr;

	private static String RssUrlNTv = "https://www.n-tv.de/rss";
	private static String RssUrlWelt = "https://www.welt.de/feeds/topnews.rss";
	private static String RssUrlCnn = "http://rss.cnn.com/rss/edition_world.rss";
	private static String RssUrlKicker = "http://rss.kicker.de/news/aktuell";
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ksGui frame = new ksGui();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setUndecorated(true);
					frame.setBackground(Color.BLACK);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ksGui() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getContentPane().requestFocus();
			}
		});
		getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 463);
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(null);
		getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		uz = new uzPanel();
		uz.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		uz.setBounds(0, 0, 810, 160);
		uz.setVisible(true);
		int offSet = uz.getWidth();
		uz.setBounds(screenWidth - offSet, 10, uz.getBreite(), uz.getHoehe());
		getContentPane().add(uz);

		weatherPanel = new waPanel();
		weatherPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		weatherPanel.setBounds(10, 10, 480, 300);
		weatherPanel.setVisible(true);
		getContentPane().add(weatherPanel);

		vl = new vlPanel();
		vl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		vl.setVisible(true);
		vl.setBounds(10, 10, 340, 500);
		offSet = vl.getWidth();
		vl.setBounds(screenWidth - offSet, uz.getHeight(), 340, 500);
		getContentPane().add(vl);

		int rssHoehe = 280;

		rssNtv = new RssFeedPanel();
		rssNtv.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		rssNtv.setBounds(10, 10, 307, rssHoehe);
		rssNtv.setVisible(true);
		offSet = rssNtv.getHeight();
		rssNtv.setBounds(10, screenHeight - offSet, 307, rssHoehe);
		getContentPane().add(rssNtv);
		rssNtv.initRss(RssUrlNTv);

		rssWelt = new RssFeedPanel();
		rssWelt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		rssWelt.setBounds(322, 10, 307, rssHoehe);
		rssWelt.setVisible(true);
		rssWelt.setBounds(rssNtv.getWidth() + 10, screenHeight - rssWelt.getHeight(), 307, rssHoehe);
		getContentPane().add(rssWelt);
		rssWelt.initRss(RssUrlWelt);

		rssCnn = new RssFeedPanel();
		rssCnn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		rssCnn.setBounds(634, 10, 307, rssHoehe);
		rssCnn.setVisible(true);
		rssCnn.setBounds((rssWelt.getWidth() + rssNtv.getWidth() + 10), screenHeight - rssWelt.getHeight(), 307,
				rssHoehe);
		getContentPane().add(rssCnn);
		rssCnn.initRss(RssUrlCnn);

		rssKicker = new RssFeedPanel();
		rssKicker.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		rssKicker.setBounds(946, 10, 307, rssHoehe);
		rssKicker.setVisible(true);
		rssKicker.setBounds((rssCnn.getWidth() + rssWelt.getWidth() + rssNtv.getWidth() + 10),
				screenHeight - rssKicker.getHeight(), 307, rssHoehe);
		getContentPane().add(rssKicker);
		rssKicker.initRss(RssUrlKicker);

		getContentPane().requestFocus();

		
		initThread();
	}

	private void initThread() {
		
		
		threadWetter = new Thread(() -> {
			while (true) {
				try {
					weatherPanel.initwaPanel();
					System.out.println("Wetter");
					Thread.sleep(600000); // 10 Minuten
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});
		
		threadWetter.start();
		
		threadVerkehr = new Thread(() ->  {
			while(true)
			{
				try
				{
				vl.setBilder();
				System.out.println("Verkehr");
					Thread.sleep(600000);// (600000); // 10 Minuten
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		});
		
		threadVerkehr.start();
		
		threadRss = new Thread(() ->
		{
			while(true)
			{
				try
				{
					rssNtv.initRss(RssUrlNTv);
					rssWelt.initRss(RssUrlWelt);
					rssCnn.initRss(RssUrlCnn);
					rssKicker.initRss(RssUrlKicker);
					System.out.println("Rss");
					Thread.sleep(600000); // 10 Minuten
				}catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		});
		
		threadRss.start();
	}

	@Override
	public void run() {
		System.out.println("Run");

	}
}
