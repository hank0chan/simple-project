package xbatis.data.sample.service;

import xbatis.base.Criteria;
import xbatis.data.sample.entity.Sample;

public interface SampleService {

	public Sample get(Criteria criteria);
}
