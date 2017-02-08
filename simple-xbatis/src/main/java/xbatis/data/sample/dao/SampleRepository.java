package xbatis.data.sample.dao;

import xbatis.base.BaseAbstractRepository;
import xbatis.base.Criteria;
import xbatis.data.sample.entity.Sample;

public class SampleRepository extends BaseAbstractRepository {

	/** 封装业务Dao查询方法 */
	public Sample businessQuery(Criteria criteria) {
		return get(Sample.class, criteria);
	}
}
