package sss.services;

import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import sss.domain.NonEditableTableModel;

public class ChartBuilder {

	public static final int CHART_TYPE_BAR = 0;
	public static final int CHART_TYPE_LINE = 1;



	private ChartBuilder() {

	}

	public static void showTopSellerBarChart(String[] dateRange, NonEditableTableModel dataModel) {
		DefaultCategoryDataset topSellerData = DatasetConverter.convertTopSeller(dataModel);
		ChartPanel topSellerChartPanel = createTopSellerChart(topSellerData);
		
		JFrame topSellerChartFrame = new JFrame();
		topSellerChartFrame.setContentPane(topSellerChartPanel);
		topSellerChartFrame.setTitle("Viewing Top Sellers for: "
				+ dateRange[0] + " - " + dateRange[1]);
		topSellerChartFrame.pack();
		RefineryUtilities.centerFrameOnScreen(topSellerChartFrame);

		topSellerChartFrame.setVisible(true);
	}

	public static void showTimePeriodBarChart(String reportType, String groupBy,
			String[] dateRange, NonEditableTableModel dataModel) {
		switch (reportType) {
		case "dollar":
			if (groupBy.equals("day")) {
				DefaultCategoryDataset dollarData = DatasetConverter
						.convertSalesDollarByDay(dataModel);
				ChartPanel dollarChartPanel = createSaleDollarByDayChart(dollarData,
						ChartBuilder.CHART_TYPE_BAR);

				JFrame dollarChartFrame = new JFrame();
				dollarChartFrame.setContentPane(dollarChartPanel);
				dollarChartFrame.setTitle("Viewing Sales by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				dollarChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(dollarChartFrame);

				dollarChartFrame.setVisible(true);
			}
			else if (groupBy.equals("week")) {
				DefaultCategoryDataset dollarData = DatasetConverter
						.convertSalesDollarByWeek(dataModel);
				ChartPanel dollarChartPanel = createSaleDollarByWeekChart(dollarData,
						ChartBuilder.CHART_TYPE_BAR);

				JFrame dollarChartFrame = new JFrame();
				dollarChartFrame.setContentPane(dollarChartPanel);
				dollarChartFrame.setTitle("Viewing Sales by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				dollarChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(dollarChartFrame);

				dollarChartFrame.setVisible(true);
			}
			else {
				DefaultCategoryDataset dollarData = DatasetConverter
						.convertSalesDollarByMonth(dataModel);
				ChartPanel dollarChartPanel = createSaleDollarByMonthChart(dollarData,
						ChartBuilder.CHART_TYPE_BAR);

				JFrame dollarChartFrame = new JFrame();
				dollarChartFrame.setContentPane(dollarChartPanel);
				dollarChartFrame.setTitle("Viewing Sales by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				dollarChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(dollarChartFrame);

				dollarChartFrame.setVisible(true);
			}
			break;
		case "volume":
			if (groupBy.equals("day")) {
				DefaultCategoryDataset volumeData = DatasetConverter
						.convertSalesVolumeByDay(dataModel);
				ChartPanel volumeChartPanel = createSaleVolumeByDayChart(volumeData,
						ChartBuilder.CHART_TYPE_BAR);

				JFrame volumeChartFrame = new JFrame();
				volumeChartFrame.setContentPane(volumeChartPanel);
				volumeChartFrame.setTitle("Viewing Sales by Volume for: "
						+ dateRange[0] + " - " + dateRange[1]);
				volumeChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(volumeChartFrame);

				volumeChartFrame.setVisible(true);
			}
			else if (groupBy.equals("week")) {
				DefaultCategoryDataset volumeData = DatasetConverter
						.convertSalesVolumeByWeek(dataModel);
				ChartPanel volumeChartPanel = createSaleVolumeByWeekChart(volumeData,
						ChartBuilder.CHART_TYPE_BAR);

				JFrame volumeChartFrame = new JFrame();
				volumeChartFrame.setContentPane(volumeChartPanel);
				volumeChartFrame.setTitle("Viewing Sales by Volume for: "
						+ dateRange[0] + " - " + dateRange[1]);
				volumeChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(volumeChartFrame);

				volumeChartFrame.setVisible(true);
			}
			else {
				DefaultCategoryDataset volumeData = DatasetConverter
						.convertSalesVolumeByMonth(dataModel);
				ChartPanel volumeChartPanel = createSaleVolumeByMonthChart(volumeData,
						ChartBuilder.CHART_TYPE_BAR);

				JFrame volumeChartFrame = new JFrame();
				volumeChartFrame.setContentPane(volumeChartPanel);
				volumeChartFrame.setTitle("Viewing Sales by Volume for: "
						+ dateRange[0] + " - " + dateRange[1]);
				volumeChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(volumeChartFrame);

				volumeChartFrame.setVisible(true);
			}
			break;
		case "profit":
			if (groupBy.equals("day")) {
				DefaultCategoryDataset profitData = DatasetConverter
						.convertGrossProfitByDay(dataModel);
				ChartPanel grossProfitChartPanel = createGrossProfitByDayChart(
						profitData, ChartBuilder.CHART_TYPE_BAR);

				JFrame grossProfitChartFrame = new JFrame();
				grossProfitChartFrame.setContentPane(grossProfitChartPanel);
				grossProfitChartFrame.setTitle("Viewing Gross Profit by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				grossProfitChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(grossProfitChartFrame);

				grossProfitChartFrame.setVisible(true);
			}
			else if (groupBy.equals("week")) {
				DefaultCategoryDataset profitData = DatasetConverter
						.convertGrossProfitByWeek(dataModel);
				ChartPanel grossProfitChartPanel = createGrossProfitByWeekChart(
						profitData, ChartBuilder.CHART_TYPE_BAR);

				JFrame grossProfitChartFrame = new JFrame();
				grossProfitChartFrame.setContentPane(grossProfitChartPanel);
				grossProfitChartFrame.setTitle("Viewing Gross Profit by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				grossProfitChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(grossProfitChartFrame);

				grossProfitChartFrame.setVisible(true);
			}
			else {
				DefaultCategoryDataset profitData = DatasetConverter
						.convertGrossProfitByMonth(dataModel);
				ChartPanel grossProfitChartPanel = createGrossProfitByMonthChart(
						profitData, ChartBuilder.CHART_TYPE_BAR);

				JFrame grossProfitChartFrame = new JFrame();
				grossProfitChartFrame.setContentPane(grossProfitChartPanel);
				grossProfitChartFrame.setTitle("Viewing Gross Profit by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				grossProfitChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(grossProfitChartFrame);

				grossProfitChartFrame.setVisible(true);
			}
			break;
		case "refundDollar":
			if (groupBy.equals("day")) {
				DefaultCategoryDataset refundDollarData = DatasetConverter
						.convertSalesDollarByDay(dataModel);
				ChartPanel refundDollarChartPanel = createRefundDollarByDayChart(refundDollarData);

				JFrame refundDollarChartFrame = new JFrame();
				refundDollarChartFrame.setContentPane(refundDollarChartPanel);
				refundDollarChartFrame.setTitle("Viewing Refunds by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				refundDollarChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(refundDollarChartFrame);

				refundDollarChartFrame.setVisible(true);
				break;
			}
			else if (groupBy.equals("week")) {
				DefaultCategoryDataset refundDollarData = DatasetConverter
						.convertSalesDollarByWeek(dataModel);
				ChartPanel refundDollarChartPanel = createRefundDollarByWeekChart(refundDollarData);

				JFrame refundDollarChartFrame = new JFrame();
				refundDollarChartFrame.setContentPane(refundDollarChartPanel);
				refundDollarChartFrame.setTitle("Viewing Refunds by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				refundDollarChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(refundDollarChartFrame);

				refundDollarChartFrame.setVisible(true);
			}
			else {
				DefaultCategoryDataset refundDollarData = DatasetConverter
						.convertSalesDollarByMonth(dataModel);
				ChartPanel refundDollarChartPanel = createRefundDollarByMonthChart(refundDollarData);

				JFrame refundDollarChartFrame = new JFrame();
				refundDollarChartFrame.setContentPane(refundDollarChartPanel);
				refundDollarChartFrame.setTitle("Viewing Refunds by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				refundDollarChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(refundDollarChartFrame);

				refundDollarChartFrame.setVisible(true);
			}
			break;
		case "refundVolume":
			if (groupBy.equals("day")) {
				DefaultCategoryDataset refundVolumeData = DatasetConverter
						.convertRefundVolumeByDay(dataModel);
				ChartPanel refundVolumeChartPanel = createRefundVolumeByDayChart(refundVolumeData);

				JFrame refundVolumeChartFrame = new JFrame();
				refundVolumeChartFrame.setContentPane(refundVolumeChartPanel);
				refundVolumeChartFrame.setTitle("Viewing Refunds by Volume for: "
						+ dateRange[0] + " - " + dateRange[1]);
				refundVolumeChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(refundVolumeChartFrame);

				refundVolumeChartFrame.setVisible(true);
			}
			else if (groupBy.equals("week")) {
				DefaultCategoryDataset refundVolumeData = DatasetConverter
						.convertRefundVolumeByWeek(dataModel);
				ChartPanel refundVolumeChartPanel = createRefundVolumeByWeekChart(refundVolumeData);

				JFrame refundVolumeChartFrame = new JFrame();
				refundVolumeChartFrame.setContentPane(refundVolumeChartPanel);
				refundVolumeChartFrame.setTitle("Viewing Refunds by Volume for: "
						+ dateRange[0] + " - " + dateRange[1]);
				refundVolumeChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(refundVolumeChartFrame);

				refundVolumeChartFrame.setVisible(true);
			}
			else {
				DefaultCategoryDataset refundVolumeData = DatasetConverter
						.convertRefundVolumeByMonth(dataModel);
				ChartPanel refundVolumeChartPanel = createRefundVolumeByMonthChart(refundVolumeData);

				JFrame refundVolumeChartFrame = new JFrame();
				refundVolumeChartFrame.setContentPane(refundVolumeChartPanel);
				refundVolumeChartFrame.setTitle("Viewing Refunds by Volume for: "
						+ dateRange[0] + " - " + dateRange[1]);
				refundVolumeChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(refundVolumeChartFrame);

				refundVolumeChartFrame.setVisible(true);
			}
			break;
		default:
			break;
		}
	}



	public static void showTimePeriodLineChart(String reportType, String groupBy,
			String[] dateRange, NonEditableTableModel dataModel) {
		switch (reportType) {
		case "dollar":
			if (groupBy.equals("day")) {
				DefaultCategoryDataset dollarData = DatasetConverter
						.convertSalesDollarByDay(dataModel);
				ChartPanel dollarChartPanel = createSaleDollarByDayChart(dollarData,
						ChartBuilder.CHART_TYPE_LINE);

				JFrame dollarChartFrame = new JFrame();
				dollarChartFrame.setContentPane(dollarChartPanel);
				dollarChartFrame.setTitle("Viewing Sales by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				dollarChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(dollarChartFrame);

				dollarChartFrame.setVisible(true);
			}
			else if (groupBy.equals("week")) {
				DefaultCategoryDataset dollarData = DatasetConverter
						.convertSalesDollarByWeek(dataModel);
				ChartPanel dollarChartPanel = createSaleDollarByWeekChart(dollarData,
						ChartBuilder.CHART_TYPE_LINE);

				JFrame dollarChartFrame = new JFrame();
				dollarChartFrame.setContentPane(dollarChartPanel);
				dollarChartFrame.setTitle("Viewing Sales by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				dollarChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(dollarChartFrame);

				dollarChartFrame.setVisible(true);
			}
			else {
				DefaultCategoryDataset dollarData = DatasetConverter
						.convertSalesDollarByMonth(dataModel);
				ChartPanel dollarChartPanel = createSaleDollarByMonthChart(dollarData,
						ChartBuilder.CHART_TYPE_LINE);

				JFrame dollarChartFrame = new JFrame();
				dollarChartFrame.setContentPane(dollarChartPanel);
				dollarChartFrame.setTitle("Viewing Sales by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				dollarChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(dollarChartFrame);

				dollarChartFrame.setVisible(true);

			}
			break;
		case "volume":
			if (groupBy.equals("day")) {
				DefaultCategoryDataset volumeData = DatasetConverter
						.convertSalesVolumeByDay(dataModel);
				ChartPanel volumeChartPanel = createSaleVolumeByDayChart(volumeData,
						ChartBuilder.CHART_TYPE_LINE);

				JFrame volumeChartFrame = new JFrame();
				volumeChartFrame.setContentPane(volumeChartPanel);
				volumeChartFrame.setTitle("Viewing Sales by Volume for: "
						+ dateRange[0] + " - " + dateRange[1]);
				volumeChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(volumeChartFrame);

				volumeChartFrame.setVisible(true);
			}
			else if (groupBy.equals("week")) {
				DefaultCategoryDataset volumeData = DatasetConverter
						.convertSalesVolumeByWeek(dataModel);
				ChartPanel volumeChartPanel = createSaleVolumeByWeekChart(volumeData,
						ChartBuilder.CHART_TYPE_LINE);

				JFrame volumeChartFrame = new JFrame();
				volumeChartFrame.setContentPane(volumeChartPanel);
				volumeChartFrame.setTitle("Viewing Sales by Volume for: "
						+ dateRange[0] + " - " + dateRange[1]);
				volumeChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(volumeChartFrame);

				volumeChartFrame.setVisible(true);
			}
			else {
				DefaultCategoryDataset volumeData = DatasetConverter
						.convertSalesVolumeByMonth(dataModel);
				ChartPanel volumeChartPanel = createSaleVolumeByMonthChart(volumeData,
						ChartBuilder.CHART_TYPE_LINE);

				JFrame volumeChartFrame = new JFrame();
				volumeChartFrame.setContentPane(volumeChartPanel);
				volumeChartFrame.setTitle("Viewing Sales by Volume for: "
						+ dateRange[0] + " - " + dateRange[1]);
				volumeChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(volumeChartFrame);

				volumeChartFrame.setVisible(true);

			}
			break;
		case "profit":
			if (groupBy.equals("day")) {
				DefaultCategoryDataset profitData = DatasetConverter
						.convertGrossProfitByDay(dataModel);
				ChartPanel grossProfitChartPanel = createGrossProfitByDayChart(
						profitData, ChartBuilder.CHART_TYPE_LINE);

				JFrame grossProfitChartFrame = new JFrame();
				grossProfitChartFrame.setContentPane(grossProfitChartPanel);
				grossProfitChartFrame.setTitle("Viewing Gross Profit by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				grossProfitChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(grossProfitChartFrame);

				grossProfitChartFrame.setVisible(true);
			}
			else if (groupBy.equals("week")) {
				DefaultCategoryDataset profitData = DatasetConverter
						.convertGrossProfitByWeek(dataModel);
				ChartPanel grossProfitChartPanel = createGrossProfitByWeekChart(
						profitData, ChartBuilder.CHART_TYPE_LINE);

				JFrame grossProfitChartFrame = new JFrame();
				grossProfitChartFrame.setContentPane(grossProfitChartPanel);
				grossProfitChartFrame.setTitle("Viewing Gross Profit by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				grossProfitChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(grossProfitChartFrame);

				grossProfitChartFrame.setVisible(true);
			}
			else {
				DefaultCategoryDataset profitData = DatasetConverter
						.convertGrossProfitByMonth(dataModel);
				ChartPanel grossProfitChartPanel = createGrossProfitByMonthChart(
						profitData, ChartBuilder.CHART_TYPE_LINE);

				JFrame grossProfitChartFrame = new JFrame();
				grossProfitChartFrame.setContentPane(grossProfitChartPanel);
				grossProfitChartFrame.setTitle("Viewing Gross Profit by Dollar for: "
						+ dateRange[0] + " - " + dateRange[1]);
				grossProfitChartFrame.pack();
				RefineryUtilities.centerFrameOnScreen(grossProfitChartFrame);

				grossProfitChartFrame.setVisible(true);

			}
			break;
		default:
			break;
		}
	}



	public static void showSingleDayBarChart(String reportType, String date,
			NonEditableTableModel dataModel) {
		switch (reportType) {
		case "dollar":
			DefaultCategoryDataset dollarData = DatasetConverter
					.convertSalesDollarByHour(dataModel);
			ChartPanel dollarChartPanel = createSaleDollarByHourChart(dollarData,
					ChartBuilder.CHART_TYPE_BAR);

			JFrame dollarChartFrame = new JFrame();
			dollarChartFrame.setContentPane(dollarChartPanel);
			dollarChartFrame.setTitle("Viewing Sales by Dollar for: " + date);
			dollarChartFrame.pack();
			RefineryUtilities.centerFrameOnScreen(dollarChartFrame);

			dollarChartFrame.setVisible(true);
			break;
		case "volume":

			DefaultCategoryDataset volumeData = DatasetConverter
					.convertSalesVolumeByHour(dataModel);
			ChartPanel volumeChartPanel = createSaleVolumeByHourChart(volumeData,
					ChartBuilder.CHART_TYPE_BAR);

			JFrame volumeChartFrame = new JFrame();
			volumeChartFrame.setContentPane(volumeChartPanel);
			volumeChartFrame.setTitle("Viewing Sales by Volume for: " + date);
			volumeChartFrame.pack();
			RefineryUtilities.centerFrameOnScreen(volumeChartFrame);

			volumeChartFrame.setVisible(true);
			break;
		case "profit":

			DefaultCategoryDataset profitData = DatasetConverter
					.convertGrossProfitByHour(dataModel);
			ChartPanel grossProfitChartPanel = createGrossProfitByHourChart(
					profitData, ChartBuilder.CHART_TYPE_BAR);

			JFrame grossProfitChartFrame = new JFrame();
			grossProfitChartFrame.setContentPane(grossProfitChartPanel);
			grossProfitChartFrame.setTitle("Viewing Gross Profit by Dollar for: "
					+ date);
			grossProfitChartFrame.pack();
			RefineryUtilities.centerFrameOnScreen(grossProfitChartFrame);

			grossProfitChartFrame.setVisible(true);
			break;
		case "refundDollar":

			DefaultCategoryDataset refundDollarData = DatasetConverter
					.convertSalesDollarByHour(dataModel);
			ChartPanel refundDollarChartPanel = createRefundDollarByHourChart(refundDollarData);

			JFrame refundDollarChartFrame = new JFrame();
			refundDollarChartFrame.setContentPane(refundDollarChartPanel);
			refundDollarChartFrame.setTitle("Viewing Refunds by Dollar for: " + date);
			refundDollarChartFrame.pack();
			RefineryUtilities.centerFrameOnScreen(refundDollarChartFrame);

			refundDollarChartFrame.setVisible(true);
			break;
		case "refundVolume":

			DefaultCategoryDataset refundVolumeData = DatasetConverter
					.convertRefundVolumeByHour(dataModel);
			ChartPanel refundVolumeChartPanel = createRefundVolumeByHourChart(refundVolumeData);

			JFrame refundVolumeChartFrame = new JFrame();
			refundVolumeChartFrame.setContentPane(refundVolumeChartPanel);
			refundVolumeChartFrame.setTitle("Viewing Refunds by Volume for: " + date);
			refundVolumeChartFrame.pack();
			RefineryUtilities.centerFrameOnScreen(refundVolumeChartFrame);

			refundVolumeChartFrame.setVisible(true);
			break;
		default:
			break;
		}
	}



	public static void showSingleDayLineChart(String reportType, String date,
			NonEditableTableModel dataModel) {
		switch (reportType) {
		case "dollar":

			DefaultCategoryDataset dollarData = DatasetConverter
					.convertSalesDollarByHour(dataModel);
			ChartPanel dollarChartPanel = createSaleDollarByHourChart(dollarData,
					ChartBuilder.CHART_TYPE_LINE);

			JFrame dollarChartFrame = new JFrame();
			dollarChartFrame.setContentPane(dollarChartPanel);
			dollarChartFrame.setTitle("Viewing Sales by Dollar for: " + date);
			dollarChartFrame.pack();
			RefineryUtilities.centerFrameOnScreen(dollarChartFrame);

			dollarChartFrame.setVisible(true);
			break;
		case "volume":

			DefaultCategoryDataset volumeData = DatasetConverter
					.convertSalesVolumeByHour(dataModel);
			ChartPanel volumeChartPanel = createSaleVolumeByHourChart(volumeData,
					ChartBuilder.CHART_TYPE_LINE);

			JFrame volumeChartFrame = new JFrame();
			volumeChartFrame.setContentPane(volumeChartPanel);
			volumeChartFrame.setTitle("Viewing Sales by Volume for: " + date);
			volumeChartFrame.pack();
			RefineryUtilities.centerFrameOnScreen(volumeChartFrame);

			volumeChartFrame.setVisible(true);
			break;
		case "profit":

			DefaultCategoryDataset profitData = DatasetConverter
					.convertGrossProfitByHour(dataModel);
			ChartPanel grossProfitChartPanel = createGrossProfitByHourChart(
					profitData, ChartBuilder.CHART_TYPE_LINE);

			JFrame grossProfitChartFrame = new JFrame();
			grossProfitChartFrame.setContentPane(grossProfitChartPanel);
			grossProfitChartFrame.setTitle("Viewing Gross Profit by Dollar for: "
					+ date);
			grossProfitChartFrame.pack();
			RefineryUtilities.centerFrameOnScreen(grossProfitChartFrame);

			grossProfitChartFrame.setVisible(true);
			break;
		default:
			break;
		}
	}



	private static ChartPanel createSaleDollarByHourChart(
			DefaultCategoryDataset data, int chartType) {
		if (chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart dollarChart = ChartFactory
					.createBarChart("", "Hour", "Sale Amount ($)", data,
							PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) dollarChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			BarRenderer.setDefaultBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer())
					.setBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer()).setSeriesPaint(0, new Color(93, 212,
					121));
			((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0,
					0, 0));
			((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel dollarChartPanel = new ChartPanel(dollarChart);
			dollarChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));

			return dollarChartPanel;
		}

		else {
			JFreeChart dollarChart = ChartFactory
					.createLineChart("", "Hour", "Sale Amount ($)", data,
							PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) dollarChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			plot.getRenderer().setSeriesPaint(0, new Color(93, 212, 121));

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel dollarChartPanel = new ChartPanel(dollarChart);
			dollarChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));

			return dollarChartPanel;
		}
	}



	private static ChartPanel createSaleVolumeByHourChart(
			DefaultCategoryDataset data, int chartType) {
		if (chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart volumeChart = ChartFactory.createBarChart("", "Hour",
					"No. of Transactions", data, PlotOrientation.VERTICAL, false, true,
					false);

			CategoryPlot plot = (CategoryPlot) volumeChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			BarRenderer.setDefaultBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer())
					.setBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer()).setSeriesPaint(0, new Color(93, 212,
					121));
			((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0,
					0, 0));
			((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel volumeChartPanel = new ChartPanel(volumeChart);
			volumeChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));

			return volumeChartPanel;
		}
		else {
			JFreeChart volumeChart = ChartFactory.createLineChart("", "Hour",
					"No. of Transactions", data, PlotOrientation.VERTICAL, false, true,
					false);

			CategoryPlot plot = (CategoryPlot) volumeChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			plot.getRenderer().setSeriesPaint(0, new Color(93, 212, 121));

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel volumeChartPanel = new ChartPanel(volumeChart);
			volumeChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));

			return volumeChartPanel;
		}
	}



	private static ChartPanel createGrossProfitByHourChart(
			DefaultCategoryDataset data, int chartType) {
		if (chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart grossProfitChart = ChartFactory.createBarChart("", "Hour",
					"GP Amount ($)", data, PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) grossProfitChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			BarRenderer.setDefaultBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer())
					.setBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer()).setSeriesPaint(0, new Color(93, 212,
					121));
			((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0,
					0, 0));
			((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel grossProfitChartPanel = new ChartPanel(grossProfitChart);
			grossProfitChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));

			return grossProfitChartPanel;
		}
		else {
			JFreeChart grossProfitChart = ChartFactory.createLineChart("", "Hour",
					"GP Amount ($)", data, PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) grossProfitChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			plot.getRenderer().setSeriesPaint(0, new Color(93, 212, 121));

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel grossProfitChartPanel = new ChartPanel(grossProfitChart);
			grossProfitChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));

			return grossProfitChartPanel;
		}
	}



	private static ChartPanel createRefundDollarByHourChart(
			DefaultCategoryDataset data) {
		JFreeChart refundDollarChart = ChartFactory
				.createBarChart("", "Hour", "Refund Amount (-$)", data,
						PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot plot = (CategoryPlot) refundDollarChart.getPlot();
		plot.setDomainGridlinesVisible(true);
		BarRenderer.setDefaultBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer())
				.setSeriesPaint(0, new Color(214, 48, 15));
		((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0, 0,
				0));
		((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

		CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		ChartPanel refundDollarChartPanel = new ChartPanel(refundDollarChart);
		refundDollarChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));

		return refundDollarChartPanel;
	}



	private static ChartPanel createRefundVolumeByHourChart(
			DefaultCategoryDataset data) {
		JFreeChart refundVolumeChart = ChartFactory.createBarChart("", "Hour",
				"No. of Refunds", data, PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot plot = (CategoryPlot) refundVolumeChart.getPlot();
		plot.setDomainGridlinesVisible(true);
		BarRenderer.setDefaultBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer())
				.setSeriesPaint(0, new Color(214, 48, 15));
		((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0, 0,
				0));
		((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

		CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		ChartPanel refundVolumeChartPanel = new ChartPanel(refundVolumeChart);
		refundVolumeChartPanel.setPreferredSize(new java.awt.Dimension(600, 400));

		return refundVolumeChartPanel;
	}



	private static ChartPanel createSaleDollarByDayChart(
			DefaultCategoryDataset data, int chartType) {
		if (chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart dollarChart = ChartFactory
					.createBarChart("", "Day", "Sale Amount ($)", data,
							PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) dollarChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			BarRenderer.setDefaultBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer())
					.setBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer()).setSeriesPaint(0, new Color(93, 212,
					121));
			((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0,
					0, 0));
			((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel dollarChartPanel = new ChartPanel(dollarChart);
			dollarChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return dollarChartPanel;
		}

		else {
			JFreeChart dollarChart = ChartFactory
					.createLineChart("", "Day", "Sale Amount ($)", data,
							PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) dollarChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			plot.getRenderer().setSeriesPaint(0, new Color(93, 212, 121));

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel dollarChartPanel = new ChartPanel(dollarChart);
			dollarChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return dollarChartPanel;
		}
	}



	private static ChartPanel createSaleVolumeByDayChart(
			DefaultCategoryDataset data, int chartType) {
		if (chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart volumeChart = ChartFactory.createBarChart("", "Day",
					"No. of Transactions", data, PlotOrientation.VERTICAL, false, true,
					false);

			CategoryPlot plot = (CategoryPlot) volumeChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			BarRenderer.setDefaultBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer())
					.setBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer()).setSeriesPaint(0, new Color(93, 212,
					121));
			((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0,
					0, 0));
			((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel volumeChartPanel = new ChartPanel(volumeChart);
			volumeChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return volumeChartPanel;
		}
		else {
			JFreeChart volumeChart = ChartFactory.createLineChart("", "Day",
					"No. of Transactions", data, PlotOrientation.VERTICAL, false, true,
					false);

			CategoryPlot plot = (CategoryPlot) volumeChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			plot.getRenderer().setSeriesPaint(0, new Color(93, 212, 121));

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel volumeChartPanel = new ChartPanel(volumeChart);
			volumeChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return volumeChartPanel;
		}
	}



	private static ChartPanel createGrossProfitByDayChart(
			DefaultCategoryDataset data, int chartType) {
		if (chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart grossProfitChart = ChartFactory.createBarChart("", "Day",
					"GP Amount ($)", data, PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) grossProfitChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			BarRenderer.setDefaultBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer())
					.setBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer()).setSeriesPaint(0, new Color(93, 212,
					121));
			((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0,
					0, 0));
			((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel grossProfitChartPanel = new ChartPanel(grossProfitChart);
			grossProfitChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return grossProfitChartPanel;
		}
		else {
			JFreeChart grossProfitChart = ChartFactory.createLineChart("", "Day",
					"GP Amount ($)", data, PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) grossProfitChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			plot.getRenderer().setSeriesPaint(0, new Color(93, 212, 121));

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel grossProfitChartPanel = new ChartPanel(grossProfitChart);
			grossProfitChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return grossProfitChartPanel;
		}
	}



	private static ChartPanel createSaleDollarByWeekChart(
			DefaultCategoryDataset data, int chartType) {
		if (chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart dollarChart = ChartFactory
					.createBarChart("", "Week", "Sale Amount ($)", data,
							PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) dollarChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			BarRenderer.setDefaultBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer())
					.setBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer()).setSeriesPaint(0, new Color(93, 212,
					121));
			((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0,
					0, 0));
			((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel dollarChartPanel = new ChartPanel(dollarChart);
			dollarChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return dollarChartPanel;
		}

		else {
			JFreeChart dollarChart = ChartFactory
					.createLineChart("", "Week", "Sale Amount ($)", data,
							PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) dollarChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			plot.getRenderer().setSeriesPaint(0, new Color(93, 212, 121));

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel dollarChartPanel = new ChartPanel(dollarChart);
			dollarChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return dollarChartPanel;
		}
	}



	private static ChartPanel createSaleVolumeByWeekChart(
			DefaultCategoryDataset data, int chartType) {
		if (chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart volumeChart = ChartFactory.createBarChart("", "Week",
					"No. of Transactions", data, PlotOrientation.VERTICAL, false, true,
					false);

			CategoryPlot plot = (CategoryPlot) volumeChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			BarRenderer.setDefaultBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer())
					.setBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer()).setSeriesPaint(0, new Color(93, 212,
					121));
			((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0,
					0, 0));
			((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel volumeChartPanel = new ChartPanel(volumeChart);
			volumeChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return volumeChartPanel;
		}
		else {
			JFreeChart volumeChart = ChartFactory.createLineChart("", "Week",
					"No. of Transactions", data, PlotOrientation.VERTICAL, false, true,
					false);

			CategoryPlot plot = (CategoryPlot) volumeChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			plot.getRenderer().setSeriesPaint(0, new Color(93, 212, 121));

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel volumeChartPanel = new ChartPanel(volumeChart);
			volumeChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return volumeChartPanel;
		}
	}



	private static ChartPanel createGrossProfitByWeekChart(
			DefaultCategoryDataset data, int chartType) {
		if (chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart grossProfitChart = ChartFactory.createBarChart("", "Week",
					"GP Amount ($)", data, PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) grossProfitChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			BarRenderer.setDefaultBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer())
					.setBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer()).setSeriesPaint(0, new Color(93, 212,
					121));
			((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0,
					0, 0));
			((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel grossProfitChartPanel = new ChartPanel(grossProfitChart);
			grossProfitChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return grossProfitChartPanel;
		}
		else {
			JFreeChart grossProfitChart = ChartFactory.createLineChart("", "Week",
					"GP Amount ($)", data, PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) grossProfitChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			plot.getRenderer().setSeriesPaint(0, new Color(93, 212, 121));

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel grossProfitChartPanel = new ChartPanel(grossProfitChart);
			grossProfitChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return grossProfitChartPanel;
		}
	}



	private static ChartPanel createSaleDollarByMonthChart(
			DefaultCategoryDataset data, int chartType) {
		if (chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart dollarChart = ChartFactory
					.createBarChart("", "Month", "Sale Amount ($)", data,
							PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) dollarChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			BarRenderer.setDefaultBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer())
					.setBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer()).setSeriesPaint(0, new Color(93, 212,
					121));
			((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0,
					0, 0));
			((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel dollarChartPanel = new ChartPanel(dollarChart);
			dollarChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return dollarChartPanel;
		}

		else {
			JFreeChart dollarChart = ChartFactory
					.createLineChart("", "Month", "Sale Amount ($)", data,
							PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) dollarChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			plot.getRenderer().setSeriesPaint(0, new Color(93, 212, 121));

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel dollarChartPanel = new ChartPanel(dollarChart);
			dollarChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return dollarChartPanel;
		}
	}



	private static ChartPanel createSaleVolumeByMonthChart(
			DefaultCategoryDataset data, int chartType) {
		if (chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart volumeChart = ChartFactory.createBarChart("", "Month",
					"No. of Transactions", data, PlotOrientation.VERTICAL, false, true,
					false);

			CategoryPlot plot = (CategoryPlot) volumeChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			BarRenderer.setDefaultBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer())
					.setBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer()).setSeriesPaint(0, new Color(93, 212,
					121));
			((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0,
					0, 0));
			((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel volumeChartPanel = new ChartPanel(volumeChart);
			volumeChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return volumeChartPanel;
		}
		else {
			JFreeChart volumeChart = ChartFactory.createLineChart("", "Month",
					"No. of Transactions", data, PlotOrientation.VERTICAL, false, true,
					false);

			CategoryPlot plot = (CategoryPlot) volumeChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			plot.getRenderer().setSeriesPaint(0, new Color(93, 212, 121));

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel volumeChartPanel = new ChartPanel(volumeChart);
			volumeChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return volumeChartPanel;
		}
	}



	private static ChartPanel createGrossProfitByMonthChart(
			DefaultCategoryDataset data, int chartType) {
		if (chartType == ChartBuilder.CHART_TYPE_BAR) {
			JFreeChart grossProfitChart = ChartFactory.createBarChart("", "Month",
					"GP Amount ($)", data, PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) grossProfitChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			BarRenderer.setDefaultBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer())
					.setBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer()).setSeriesPaint(0, new Color(93, 212,
					121));
			((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0,
					0, 0));
			((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel grossProfitChartPanel = new ChartPanel(grossProfitChart);
			grossProfitChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return grossProfitChartPanel;
		}
		else {
			JFreeChart grossProfitChart = ChartFactory.createLineChart("", "Month",
					"GP Amount ($)", data, PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) grossProfitChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			plot.getRenderer().setSeriesPaint(0, new Color(93, 212, 121));

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel grossProfitChartPanel = new ChartPanel(grossProfitChart);
			grossProfitChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return grossProfitChartPanel;
		}
	}



	private static ChartPanel createRefundDollarByDayChart(
			DefaultCategoryDataset data) {
		JFreeChart refundDollarChart = ChartFactory
				.createBarChart("", "Day", "Refund Amount (-$)", data,
						PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot plot = (CategoryPlot) refundDollarChart.getPlot();
		plot.setDomainGridlinesVisible(true);
		BarRenderer.setDefaultBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer())
				.setSeriesPaint(0, new Color(214, 48, 15));
		((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0, 0,
				0));
		((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

		CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		ChartPanel refundDollarChartPanel = new ChartPanel(refundDollarChart);
		refundDollarChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

		return refundDollarChartPanel;
	}



	private static ChartPanel createRefundVolumeByDayChart(
			DefaultCategoryDataset data) {
		JFreeChart refundVolumeChart = ChartFactory.createBarChart("", "Day",
				"No. of Refunds", data, PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot plot = (CategoryPlot) refundVolumeChart.getPlot();
		plot.setDomainGridlinesVisible(true);
		BarRenderer.setDefaultBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer())
				.setSeriesPaint(0, new Color(214, 48, 15));
		((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0, 0,
				0));
		((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

		CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		ChartPanel refundVolumeChartPanel = new ChartPanel(refundVolumeChart);
		refundVolumeChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

		return refundVolumeChartPanel;
	}



	private static ChartPanel createRefundDollarByWeekChart(
			DefaultCategoryDataset data) {
		JFreeChart refundDollarChart = ChartFactory
				.createBarChart("", "Week", "Refund Amount (-$)", data,
						PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot plot = (CategoryPlot) refundDollarChart.getPlot();
		plot.setDomainGridlinesVisible(true);
		BarRenderer.setDefaultBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer())
				.setSeriesPaint(0, new Color(214, 48, 15));
		((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0, 0,
				0));
		((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

		CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		ChartPanel refundDollarChartPanel = new ChartPanel(refundDollarChart);
		refundDollarChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

		return refundDollarChartPanel;
	}



	private static ChartPanel createRefundVolumeByWeekChart(
			DefaultCategoryDataset data) {
		JFreeChart refundVolumeChart = ChartFactory.createBarChart("", "Week",
				"No. of Refunds", data, PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot plot = (CategoryPlot) refundVolumeChart.getPlot();
		plot.setDomainGridlinesVisible(true);
		BarRenderer.setDefaultBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer())
				.setSeriesPaint(0, new Color(214, 48, 15));
		((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0, 0,
				0));
		((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

		CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		ChartPanel refundVolumeChartPanel = new ChartPanel(refundVolumeChart);
		refundVolumeChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

		return refundVolumeChartPanel;
	}



	private static ChartPanel createRefundDollarByMonthChart(
			DefaultCategoryDataset data) {
		JFreeChart refundDollarChart = ChartFactory
				.createBarChart("", "Month", "Refund Amount (-$)", data,
						PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot plot = (CategoryPlot) refundDollarChart.getPlot();
		plot.setDomainGridlinesVisible(true);
		BarRenderer.setDefaultBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer())
				.setSeriesPaint(0, new Color(214, 48, 15));
		((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0, 0,
				0));
		((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

		CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		ChartPanel refundDollarChartPanel = new ChartPanel(refundDollarChart);
		refundDollarChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

		return refundDollarChartPanel;
	}



	private static ChartPanel createRefundVolumeByMonthChart(
			DefaultCategoryDataset data) {
		JFreeChart refundVolumeChart = ChartFactory.createBarChart("", "Month",
				"No. of Refunds", data, PlotOrientation.VERTICAL, false, true, false);

		CategoryPlot plot = (CategoryPlot) refundVolumeChart.getPlot();
		plot.setDomainGridlinesVisible(true);
		BarRenderer.setDefaultBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		((BarRenderer) plot.getRenderer())
				.setSeriesPaint(0, new Color(214, 48, 15));
		((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0, 0,
				0));
		((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

		CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);

		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		ChartPanel refundVolumeChartPanel = new ChartPanel(refundVolumeChart);
		refundVolumeChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

		return refundVolumeChartPanel;
	}
	
	private static ChartPanel createTopSellerChart(DefaultCategoryDataset data) {
			JFreeChart topSellerChart = ChartFactory
					.createBarChart("", "Product Name", "Units Sold", data,
							PlotOrientation.VERTICAL, false, true, false);

			CategoryPlot plot = (CategoryPlot) topSellerChart.getPlot();
			plot.setDomainGridlinesVisible(true);
			BarRenderer.setDefaultBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer())
					.setBarPainter(new StandardBarPainter());
			((BarRenderer) plot.getRenderer()).setSeriesPaint(0, new Color(111, 111,
					209));
			((BarRenderer) plot.getRenderer()).setSeriesOutlinePaint(0, new Color(0,
					0, 0));
			((BarRenderer) plot.getRenderer()).setDrawBarOutline(true);

			CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
			xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

			NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			ChartPanel topSellerChartPanel = new ChartPanel(topSellerChart);
			topSellerChartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

			return topSellerChartPanel;
	}
}