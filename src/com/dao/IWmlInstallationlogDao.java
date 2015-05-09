package com.dao;

import java.util.List;

import com.pojo.ClinetInsertType;
import com.pojo.WmlInstallationlog;

public interface IWmlInstallationlogDao extends IObjectDAO {
	
	public List<WmlInstallationlog> queryWmlInstallationlogList(WmlInstallationlog item);
	
	
	public List<ClinetInsertType> queryInstallClientType(String clientType);

}
