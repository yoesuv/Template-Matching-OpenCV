package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import proses.ProsesMatching;

public class GUI_App {

	private JFrame FrameApp;
	private JLabel lblTemplate,lblInput,lblResult;
	private JButton btnTemplate,btnInput,btnTemplateMatching;

	private JFileChooser jfc;
	private File file_sumber;
	private BufferedImage bfrImg;
	private ImageIcon imgIco;
	
	private String inFile,templFile;
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_App window = new GUI_App();
					window.FrameApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GUI_App() {
		initialize();
	}

	private void initialize() {
		FrameApp = new JFrame();
		FrameApp.setTitle("Template Matching");
		FrameApp.setResizable(false);
		FrameApp.setBounds(100, 100, 991, 506);
		FrameApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FrameApp.getContentPane().setLayout(null);
		
		jfc = new JFileChooser();
		jfc.setMultiSelectionEnabled(false);
		FileNameExtensionFilter fil = new FileNameExtensionFilter("Images Only (*.jpg,*.gif,*.png)", "jpg","JPG","gif","png");
		jfc.setFileFilter(fil);
		
		btnTemplate = new JButton("TEMPLATE");
		btnTemplate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(jfc.showOpenDialog(FrameApp)==JFileChooser.APPROVE_OPTION){
					file_sumber = jfc.getSelectedFile();
					try {
						bfrImg = ImageIO.read(file_sumber);
						imgIco = new ImageIcon(bfrImg);
						lblTemplate.setIcon(imgIco);
						
						templFile = file_sumber.getAbsolutePath();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnTemplate.setBounds(444, 440, 100, 30);
		FrameApp.getContentPane().add(btnTemplate);
		
		btnInput = new JButton("INPUT");
		btnInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(jfc.showOpenDialog(FrameApp)==JFileChooser.APPROVE_OPTION){
					file_sumber = jfc.getSelectedFile();
					try {
						bfrImg = ImageIO.read(file_sumber);
						imgIco = new ImageIcon(bfrImg);
						lblInput.setIcon(imgIco);
						
						inFile = file_sumber.getAbsolutePath();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnInput.setBounds(112, 440, 100, 30);
		FrameApp.getContentPane().add(btnInput);
		
		JScrollPane scrollPane_Input = new JScrollPane();
		scrollPane_Input.setBounds(334, 10, 316, 426);
		FrameApp.getContentPane().add(scrollPane_Input);
		scrollPane_Input.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Template", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		lblTemplate = new JLabel("");
		scrollPane_Input.setViewportView(lblTemplate);
		
		JScrollPane scrollPane_Template = new JScrollPane();
		scrollPane_Template.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Input", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_Template.setBounds(10, 10, 316, 426);
		FrameApp.getContentPane().add(scrollPane_Template);
		
		lblInput = new JLabel("");
		scrollPane_Template.setViewportView(lblInput);
		
		JScrollPane scrollPane_Result = new JScrollPane();
		scrollPane_Result.setViewportBorder(new TitledBorder(null, "Result", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_Result.setBounds(660, 11, 316, 426);
		FrameApp.getContentPane().add(scrollPane_Result);
		
		lblResult = new JLabel("");
		scrollPane_Result.setViewportView(lblResult);
		
		btnTemplateMatching = new JButton("TEMPLATE MATCHING");
		btnTemplateMatching.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(inFile==null){
						JOptionPane.showMessageDialog(FrameApp, "Input Kosong", "ERROR", JOptionPane.ERROR_MESSAGE);
					}else if(templFile==null){
						JOptionPane.showMessageDialog(FrameApp, "Template Kosong", "ERROR", JOptionPane.ERROR_MESSAGE);
					}else{
						System.out.println("Template : "+templFile);
						System.out.println("Input : "+inFile);
						ProsesMatching pm = new ProsesMatching();
						pm.getInput(inFile ,templFile);
						pm.Jalan();
						pm.Tampilkan(lblResult);
					}
				}catch(Exception err){
					JOptionPane.showMessageDialog(FrameApp, "Terjadi Kesalahan", "ERROR", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
		});
		btnTemplateMatching.setBounds(749, 440, 139, 30);
		FrameApp.getContentPane().add(btnTemplateMatching);
	}
}
