package singleton;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public final class DatabaseConnectionManager {

	DBI dbi;

	private Handle h;
	
	public static DatabaseConnectionManager INSTANCE = new DatabaseConnectionManager();
	
	public static DatabaseConnectionManager getInstance() {
		return INSTANCE;
	}
	
	public DatabaseConnectionManager() {
    	try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        if(dbi == null) {
        	dbi = new DBI("jdbc:postgresql://localhost:5432/cadeobusao","postgres", "batata");
        	h = dbi.open();
        }
	}

	public Handle getH() {
		return h;
	}

	public void setH(Handle h) {
		this.h = h;
	}
	
}
