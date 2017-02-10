package xbatis.data.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import xbatis.base.Criteria;
import xbatis.data.sample.entity.Sample;
import xbatis.data.sample.mapper.SampleMapper;
import xbatis.data.sample.service.SampleService;

public class SampleServiceImpl implements SampleService {
	
	@Autowired
	SampleMapper sampleMapper;

	@Override
	public Sample get(Criteria criteria) {
		return sampleMapper.get(criteria);
	}
	
}
