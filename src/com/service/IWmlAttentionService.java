package com.service;

import java.util.List;

import com.grid.tool.GridSupport;
import com.pojo.WmlAttention;

public interface IWmlAttentionService {

	public List<WmlAttention> queryWmlAttentionList(WmlAttention item);
	
	public WmlAttention queryWmlAttention(WmlAttention item);
	
	public boolean updateWmlAttention(GridSupport gridData);
	


}
