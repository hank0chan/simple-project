package cache.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

	@Autowired
	DataDao dataDao;
	
	public DataEntity get(String firstAttr) {
		return dataDao.get(firstAttr);
	}
}
