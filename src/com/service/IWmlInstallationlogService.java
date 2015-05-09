package com.service;

import java.util.List;

import com.grid.tool.GridSupport;
import com.pojo.ClinetInsertType;
import com.pojo.WmlInstallationlog;

public interface IWmlInstallationlogService {

	public List<WmlInstallationlog> queryWmlInstallationlogList(WmlInstallationlog item);

	public WmlInstallationlog queryWmlInstallationlog(WmlInstallationlog item);
	
	public boolean updateWmlInstallationlog(GridSupport gridData);
	
	public List<ClinetInsertType> queryInstallClientType(String clientType);
}
