import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class TimePeriodSaleFrame extends JFrame {

	public TimePeriodSaleFrame()
	{
			
			setTitle("Time-Period Sale Report");
			//			myFrame.setSize(900,500);
			setExtendedState(Frame.MAXIMIZED_BOTH);
			//Parameters: numRows, numColumns, Hgap, Vgap
			setLocationRelativeTo(null);

			//Full Screen Panel
			JPanel fullScreenPanel = new JPanel();
			TitledBorder fullScreenTitle = new TitledBorder("Full Screen:");
			fullScreenPanel.setBorder(fullScreenTitle);
			fullScreenPanel.setLayout(new GridLayout(1,2,10,10));
			add(fullScreenPanel);


			//SECTION PANELS

			JPanel leftPanel = new JPanel();
			TitledBorder leftPanelTitle = new TitledBorder("Product Inventory:");
			leftPanel.setBorder(leftPanelTitle);
			leftPanel.setLayout(new GridLayout(1,1,10,10));
			fullScreenPanel.add(leftPanel);

			JPanel rightPanel = new JPanel();
			TitledBorder rightPanelTitle = new TitledBorder("Right Panel:");
			rightPanel.setBorder(rightPanelTitle);
			rightPanel.setLayout(new GridLayout(5,1,10,10));
			fullScreenPanel.add(rightPanel);

			String[] colNames = {"Product id","Barcode","Name","Category","Sale Price"};
			Object[][] data = {
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"},
					{"DGKF353","4256985216","Cat","Pet","$40"}
			};

			JTable lookUpTable = new JTable(data, colNames);
			JScrollPane scrlPane = new JScrollPane(lookUpTable);
			leftPanel.add(scrlPane);
			
			JPanel datePanel = new JPanel();
			TitledBorder datePanelTitle = new TitledBorder("Time-Period View:");
			datePanel.setBorder(datePanelTitle);
			datePanel.setLayout(new GridLayout(1,2,10,10));
			
			
			JPanel rightDatePanel = new JPanel();
			TitledBorder rightDatePanelTitle = new TitledBorder("Select Date:");
			rightDatePanel.setBorder(rightDatePanelTitle);
			rightDatePanel.setLayout(new GridLayout(3,1,10,10));
			
			JPanel leftDatePanel = new JPanel();
			TitledBorder leftDatePanelTitle = new TitledBorder("Select Date:");
			leftDatePanel.setBorder(leftDatePanelTitle);
			leftDatePanel.setLayout(new GridLayout(3,1,10,10));
			
			JTextField viewStartDate = new JTextField();
			JLabel viewStartDateLabel = new JLabel("Start Date:");
			JLabel viewStartDateExample = new JLabel("e.g. 01/03/2014");
			rightDatePanel.add(viewStartDateLabel);
			rightDatePanel.add(viewStartDate);
			rightDatePanel.add(viewStartDateExample);
			
			JTextField viewEndDate = new JTextField();
			JLabel viewEndDateLabel = new JLabel("End Date:");
			JLabel viewEndDateExample = new JLabel("e.g. 24/03/2014");
			leftDatePanel.add(viewEndDateLabel);
			leftDatePanel.add(viewEndDate);
			leftDatePanel.add(viewEndDateExample);

			datePanel.add(rightDatePanel);
			datePanel.add(leftDatePanel);
			rightPanel.add(datePanel);
			
			JPanel reportTypePanel = new JPanel();
			TitledBorder reportTypePanelTitle = new TitledBorder("Report Type:");
			reportTypePanel.setBorder(reportTypePanelTitle);
			reportTypePanel.setLayout(new GridLayout(3,1,10,10));
			rightPanel.add(reportTypePanel);

			JRadioButton salesByDollar = new JRadioButton("Sales by Dollar", true);
			JRadioButton salesByVolume = new JRadioButton("Sales by Volume", false);
			JRadioButton profitByDollar = new JRadioButton("Gross profit by Dollar", false);
			ButtonGroup reportTypeGroup = new ButtonGroup();

			reportTypeGroup.add(salesByDollar);
			reportTypeGroup.add(salesByVolume);
			reportTypeGroup.add(profitByDollar);

			reportTypePanel.add(salesByDollar);
			reportTypePanel.add(salesByVolume);
			reportTypePanel.add(profitByDollar);

			JPanel groupTypePanel = new JPanel();
			TitledBorder groupTypePanelTitle = new TitledBorder("Group by:");
			groupTypePanel.setBorder(groupTypePanelTitle);
			groupTypePanel.setLayout(new GridLayout(3,1,10,10));
			rightPanel.add(groupTypePanel);

			JRadioButton dayRadio = new JRadioButton("Day", true);
			JRadioButton weekRadio = new JRadioButton("Week", false);
			JRadioButton monthRadio = new JRadioButton("Month", false);
			ButtonGroup groupTypeGroup = new ButtonGroup();

			groupTypeGroup.add(dayRadio);
			groupTypeGroup.add(weekRadio);
			groupTypeGroup.add(monthRadio);

			groupTypePanel.add(dayRadio);
			groupTypePanel.add(weekRadio);
			groupTypePanel.add(monthRadio);
			
			JPanel shownAsPanel = new JPanel();
			TitledBorder shownAsPanelTitle = new TitledBorder("Shown as:");
			shownAsPanel.setBorder(shownAsPanelTitle);
			shownAsPanel.setLayout(new GridLayout(1,2,10,10));
			rightPanel.add(shownAsPanel);

			JButton barGraph = new JButton("Bar Graph");
			shownAsPanel.add(barGraph);
			JButton lineGraph = new JButton("Line Graph");
			shownAsPanel.add(lineGraph);

			JPanel resultsButtonPanel = new JPanel();
			//								TitledBorder resultsButtonPanelTitle = new TitledBorder("Shown as:");
			//								resultsButtonPanel.setBorder(resultsButtonPanelTitle);
			resultsButtonPanel.setLayout(new GridLayout(3,1,10,10));
			rightPanel.add(resultsButtonPanel);

			JLabel blank = new JLabel();
			resultsButtonPanel.add(blank);
			JButton getResultsButton = new JButton("Get Results");
			resultsButtonPanel.add(getResultsButton);

			getResultsButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
//					myFrame.dispose();
				}
			});

			JButton backButton = new JButton("Back");
			resultsButtonPanel.add(backButton);

			backButton.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					dispose();
				}
			});


			setVisible(true);
		}
	
	}
