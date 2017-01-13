package br.com.algaworks.pedidovenda.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named
@RequestScoped
public class GraficosPedidosCriadosBean {

	private LineChartModel chartModelPedidos;

	@PostConstruct
	public void init() {
		createLineModels();
	}

	private void createLineModels() {

		chartModelPedidos = initChartModelPedidos();
		chartModelPedidos.setTitle("Pedido de Venda");
		chartModelPedidos.setLegendPosition("e");
		chartModelPedidos.setAnimate(true);
		Axis yAxis = chartModelPedidos.getAxis(AxisType.Y);
		yAxis.setMin(0);
	}

	private LineChartModel initChartModelPedidos() {
		LineChartModel model = new LineChartModel();

		LineChartSeries seriesTodosPedidos = new LineChartSeries();
		seriesTodosPedidos.setLabel("Todos os Pedidos");

		seriesTodosPedidos.set(1, Math.random() * 100);
		seriesTodosPedidos.set(2, Math.random() * 100);
		seriesTodosPedidos.set(3, Math.random() * 100);
		seriesTodosPedidos.set(4, Math.random() * 100);
		seriesTodosPedidos.set(5, Math.random() * 100);

		LineChartSeries seriesMeusPedidos = new LineChartSeries();
		seriesMeusPedidos.setLabel("Todos os Pedidos");

		seriesMeusPedidos.set("1", Math.random() * 100);
		seriesMeusPedidos.set("2", Math.random() * 100);
		seriesMeusPedidos.set("3", Math.random() * 100);
		seriesMeusPedidos.set("4", Math.random() * 100);
		seriesMeusPedidos.set("5", Math.random() * 100);

		model.addSeries(seriesTodosPedidos);
		model.addSeries(seriesMeusPedidos);

		return model;
	}

	public LineChartModel getChartModelPedidos() {
		return chartModelPedidos;
	}

	public void setChartModelPedidos(LineChartModel chartModelPedidos) {
		this.chartModelPedidos = chartModelPedidos;
	}

}
