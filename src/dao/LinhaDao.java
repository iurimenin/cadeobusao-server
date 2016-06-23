package dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.SqlQuery;

import model.Linha;

public interface LinhaDao {

	 @SqlQuery("select * from cadeobusao_linha")
	 List<Linha> findAll();
	
}
