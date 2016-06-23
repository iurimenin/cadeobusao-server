package dao;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import model.Linha;
import model.PosicaoBusao;

public interface PosicaoBusaoDao {

	@SqlUpdate("insert into cadeobusao_posicao_busao (lat, lng, codigo_linha, datahoraregistro) VALUES(:lat, :lng, :linha.codigo, :dataHoraRegistro)")
	public void registraNovaPosicaoBusao(@BindBean PosicaoBusao posicao, @BindBean("linha") Linha linha);
	
}
