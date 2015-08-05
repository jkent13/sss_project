package sss.services;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import sss.domain.NonEditableTableModel;

public class ChartBuilder {

	public static final int CHART_TYPE_BAR = 0;
	public static final int CHART_TYPE_LINE = 1;
	
	private ChartBuilder() {
		
	}
	
	public static void showSingleDayBarChart(String reportType, String date, NonEditableTableModel dataModel) {
		switch(reportType) {
		case "dollar" :
			DefaultCategoryDataset dollarData = DatasetConverter.convertSingleDayDollar(dataModel);
			ChartPanel dollarChartPanel = createSaleDollarChart(dollarData, ChartBuilder.CHART_TYPE_BAR);
				    
	    JFrame dollarChartFrame = new JFrame();
	    dollarChartFrame.setContentPane(dollarChartPanel);
	    dollarChartFrame.setTitle("Viewing Sales by Dollar for: " + date);
	    dollarChartFrame.pack();
	    dollarChartFrame.setLocationRelativeTo(null);

	    dollarChartFrame.setVisible(true);
	    break;
		case "volume" :
	
			DefaultCategoryDataset volumeData = DatasetConverter.convertSingleDayVolume(dataModel);
			ChartPanel volumeChartPanel = createSaleVolumeChart(volumeData, ChartBuilder.CHART_TYPE_BAR);

			JFrame volumeChartFrame = new JFrame();
			volumeChartFrame.setContentPane(volumeChartPanel);
			volumeChartFrame.setTitle("Viewing Sales by Volume for: " + date);
			volumeChartFrame.pack();
			volumeChartFrame.setLocationRelativeTo(null);

			volumeChartFrame.setVisible(true);
			break;
		case "profit" :
		
			DefaultCategoryDataset profitData = DatasetConverter.convertSingleDayGrossProfit(dataModel);
			ChartPanel grossProfitChartPanel = createGrossProfitChart(profitData, ChartBuilder.CHART_TYPE_BAR);

			JFrame grossProfitChartFrame = new JFrame();
			grossProfitChartFrame.setContentPane(grossProfitChartPanel);
			grossProfitChartFrame.setTitle("Viewing Gross Profit by Dollar for: " + date);
			grossProfitChartFrame.setLocationRelativeTo(null);
			grossProfitChartFrame.pack();

			grossProfitChartFrame.setVisible(true);
			break;
		case "refundDollar" :
			
			DefaultCategoryDataset refundDollarData = DatasetConverter.convertSingleDayDollar(dataModel);
			ChartPanel refundDollarChartPanel = createRefundDollarChart(refundDollarData, ChartBuilder.CHART_TYPE_BAR);
	    
	    JFrame refundDollarChartFrame = new JFrame();
	    refundDollarChartFrame.setContentPane(refundDollarChartPanel);
	    refundDollarChartFrame.setTitle("Viewing Refunds by Dollar for: " + date);
	    refundDollarChartFrame.pack();
	    refundDollarChartFrame.setLocationRelativeTo(null);

	    refundDollarChartFrame.setVisible(true);
			break;
		case "refundVolume" :
			
			DefaultCategoryDataset refundVolumeData = DatasetConverter.convertSingleDayRefundVolume(dataModel);
			ChartPanel refundVolumeChartPanel = createRefundVolumeChart(refundVolumeData, ChartBuilder.CHART_TYPE_BAR);

			JFrame refundVolumeChartFrame = new JFrame();
			refundVolumeChartFrame.setContentPane(refundVolumeChartPanel);
			refundVolumeChartFrame.setTitle("Viewing Refunds by Volume for: " + date);
			refundVolumeChartFrame.pack();
			refundVolumeChartFrame.setLocationRelativeTo(null);

			refundVolumeChartFrame.setVisible(true);
			break;
		default :
			break;
		}
	}
	
	public static void showSingleDayLineChart(String reportType, String date, NonEditableTableModel dataModel) {
		switch(reportType) {
		case "dollar" :
			
			DefaultCategoryDataset dollarData = DatasetConverter.convertSingleDayDollar(dataModel);
			ChartPanel dollarChartPanel = createSaleDollarChart(dollarData, ChartBuilder.CHART_TYPE_LINE);
				    
	    JFrame dollarChartFrame = new JFrame();
	    dollarChartFrame.setContentPane(dollarChartPanel);
	    dollarChartFrame.setTitle("Viewing Sales by Dollar for: " + date);
	    dollarChartFrame.pack();
	    dollarChartFrame.setLocationRelativeTo(null);

	    dollarChartFrame.setVisible(true);
	    break;
		case "volume" :
	
			DefaultCategoryDataset volumeData = DatasetConverter.convertSingleDayVolume(dataModel);
			ChartPanel volumeChartPanel = createSaleVolumeChart(volumeData, ChartBuilder.CHART_TYPE_LINE);

			JFrame volumeChartFrame = new JFrame();
			volumeChartFrame.setContentPane(volumeChartPanel);
			volumeChartFrame.setTitle("Viewing Sales by Volume for: " + date);
			volumeChartFrame.pack();
			volumeChartFrame.setLocationRelativeTo(null);

			volumeChartFrame.setVisible(true);
			break;
		case "profit" :
		
			DefaultCategoryDataset profitData = DatasetConverter.convertSingleDayGrossProfit(dataModel);
			ChartPanel grossProfitChartPanel = createGrossProfitChart(profitData, ChartBuilder.CHART_TYPE_LINE);

			JFrame grossProfitChartFrame = new JFrame();
			grossProfitChartFrame.setContentPane(grossProfitChartPanel);
			grossProfitChartFrame.setTitle("Viewing Gross Profit by Dollar for: " + date);
			grossProfitChartFrame.setLocationRelativeTo(null);
			grossProfitChartFrame.pack();

			grossProfitChartFrame.setVisible(true);
			break;
		default :
			break;
		}
	}

	private static ChartPanel createSaleDollarChart(DefaultCategoryDataset data, int chartType) {
		if(chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart dollarChart = ChartFactory.createBarChart(
					"",
					"Hour","Sale Amount ($)",
					data,
					PlotOrientation.VERTICAL,
					false,true,false);

			CategoryPlot plot = (CategoryPlot)dollarChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

			ChartPanel dollarChartPanel = new ChartPanel(dollarChart);
			dollarChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));

			return dollarChartPanel;
		}

		else { 
			JFreeChart dollarChart = ChartFactory.createLineChart(
					"",
					"Hour","Sale Amount ($)",
					data,
					PlotOrientation.VERTICAL,
					false,true,false);

			CategoryPlot plot = (CategoryPlot)dollarChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

			ChartPanel dollarChartPanel = new ChartPanel(dollarChart);
			dollarChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));

			return dollarChartPanel;
		}
	}
	
	private static ChartPanel createSaleVolumeChart(DefaultCategoryDataset data, int chartType) {
		if(chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart volumeChart = ChartFactory.createBarChart(
					"",
					"Hour","No. of Transactions",
					data,
					PlotOrientation.VERTICAL,
					false,true,false);

			CategoryPlot plot = (CategoryPlot)volumeChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			
			ChartPanel volumeChartPanel = new ChartPanel(volumeChart);
			volumeChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
			
			return volumeChartPanel;
		}
		else { 
			JFreeChart volumeChart = ChartFactory.createLineChart(
					"",
					"Hour","No. of Transactions",
					data,
					PlotOrientation.VERTICAL,
					false,true,false);

			CategoryPlot plot = (CategoryPlot)volumeChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			
			ChartPanel volumeChartPanel = new ChartPanel(volumeChart);
			volumeChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
			
			return volumeChartPanel;
		}
	}
	
	private static ChartPanel createGrossProfitChart(DefaultCategoryDataset data, int chartType) {
		if(chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart grossProfitChart = ChartFactory.createBarChart(
					"",
					"Hour","GP Amount ($)",
					data,
					PlotOrientation.VERTICAL,
					false,true,false);

			CategoryPlot plot = (CategoryPlot)grossProfitChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			
			ChartPanel grossProfitChartPanel = new ChartPanel(grossProfitChart);
			grossProfitChartPanel.setPreferredSize(new java.awt.Dimension(600, 400 ));
			
			return grossProfitChartPanel;
		}
		else { 
			JFreeChart grossProfitChart = ChartFactory.createLineChart(
					"",
					"Hour","GP Amount ($)",
					data,
					PlotOrientation.VERTICAL,
					false,true,false);

			CategoryPlot plot = (CategoryPlot)grossProfitChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			
			ChartPanel grossProfitChartPanel = new ChartPanel(grossProfitChart);
			grossProfitChartPanel.setPreferredSize(new java.awt.Dimension(600, 400 ));
			
			return grossProfitChartPanel;
		}
	}
	
	private static ChartPanel createRefundDollarChart(DefaultCategoryDataset data, int chartType) {
		if(chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart refundDollarChart = ChartFactory.createBarChart(
	        "",
	        "Hour","Refund Amount ($)",
	        data,
	        PlotOrientation.VERTICAL,
	        false,true,false);
			
	    CategoryPlot plot = (CategoryPlot)refundDollarChart.getPlot();
	    plot.setDomainGridlinesVisible(true);
	    CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
	    xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	    
	    ChartPanel refundDollarChartPanel = new ChartPanel(refundDollarChart);
	    refundDollarChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
	    
	    return refundDollarChartPanel;
		}
		else {
			return null;
		}
	}
	
	private static ChartPanel createRefundVolumeChart(DefaultCategoryDataset data, int chartType) {
		if(chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart refundVolumeChart = ChartFactory.createBarChart(
					"",
					"Hour","No. of Refunds",
					data,
					PlotOrientation.VERTICAL,
					false,true,false);

			CategoryPlot plot = (CategoryPlot)refundVolumeChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			CategoryAxis xAxis = (CategoryAxis)plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
			
			ChartPanel refundVolumeChartPanel = new ChartPanel(refundVolumeChart);
			refundVolumeChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
			
			return refundVolumeChartPanel;
		}
		else { 
			return null;
		}
	}
	
}