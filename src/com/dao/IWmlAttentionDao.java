package com.dao;

import java.util.List;

import com.pojo.WmlAttention;

public interface IWmlAttentionDao extends IObjectDAO {
	
	public List<WmlAttention> queryWmlAttentionList(WmlAttention item);

}
