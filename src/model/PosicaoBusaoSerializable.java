package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PosicaoBusaoSerializable {

	private Integer codigo;
	private Double lat;
	private Double lng;
	private String dataHoraRegistro;
	private Linha linha;
	
	public PosicaoBusaoSerializable(PosicaoBusao posicao) {
		this.codigo = posicao.getCodigo();
		this.lat = posicao.getLat();
		this.lng = posicao.getLng();
		this.dataHoraRegistro = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss").format(posicao.getDataHoraRegistro());
		this.linha = posicao.getLinha();
	}
	
	public static List<PosicaoBusaoSerializable> from(List<PosicaoBusao> posicoes) {
		List<PosicaoBusaoSerializable> posicoesSerializaveis = new ArrayList<PosicaoBusaoSerializable>();
		for(PosicaoBusao posicao : posicoes) {
			posicoesSerializaveis.add(new PosicaoBusaoSerializable(posicao));
		}
		return posicoesSerializaveis;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getDataHoraRegistro() {
		return dataHoraRegistro;
	}

	public void setDataHoraRegistro(String dataHoraRegistro) {
		this.dataHoraRegistro = dataHoraRegistro;
	}

	public Linha getLinha() {
		return linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}
	
	
	
}
