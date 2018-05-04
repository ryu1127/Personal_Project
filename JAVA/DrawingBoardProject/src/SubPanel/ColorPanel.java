package SubPanel;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import Frame.MainFrame;
import Frame.main;
import information.Information;

public class ColorPanel extends JPanel{
	
	private JButton moreColorButton;
	private JPanel choosePanel;
	

	public ColorPanel()
	{	
		this.setLayout(new BorderLayout());
		
		choosePanel = new JPanel();
		
		GridLayout colorLayout = new GridLayout(7,11);
		choosePanel.setLayout(colorLayout);

		for(int i=0;i<7;i++){
			for(int j=0;j<11;j++)
			{
				JButton temp = new JButton();
				Color tempColor = null;
				switch(i)
				{
				case 0: tempColor= new Color(255/10*(j%11),255/10*(j%11),255/10*(j%11)); break;
				case 1: tempColor= new Color(255,255/10*(j%11),0); break;
				case 2: tempColor= new Color(255,0,255/10*(j%11)); break;
				case 3: tempColor= new Color(255/10*(j%11),255,0); break;
				case 4: tempColor= new Color(0,255,255/10*(j%11)); break;
				case 5: tempColor= new Color(0,255/10*(j%11),255); break;
				case 6: tempColor= new Color(255/10*(j%11),255/10*(j%11),255); break;
				}
				
				temp.setBorderPainted(false);
				
				temp.setBackground(tempColor);
				temp.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JButton current = (JButton)e.getSource();
						Color curColor=current.getBackground();
						Information.setCurrentColor(curColor.getRed(), curColor.getGreen(), curColor.getBlue());
						MainFrame.getInstance().setBottomLabel(curColor);

					}
					
				});
				choosePanel.add(temp);
			}
		}
		

		this.add(choosePanel,BorderLayout.CENTER);
		moreColorButton = new JButton("More Color");
		moreColorButton.setBackground(Color.DARK_GRAY);
		moreColorButton.setFont(new Font("More Color",Font.BOLD,20));
		moreColorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MainFrame.getInstance().callColorFrame();
				
			}
		});
		this.add(moreColorButton,BorderLayout.SOUTH);
		
		
	}
	
	
	

}
