package swift.dao.drds;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;

/**
 * DRDS 基础操作类
 * @author hankChan
 * @Email hankchan101@gmail.com
 * @time 17:59:16 - 4 Jan 2017
 * @detail 更新中。。。
 */
public class DRDSRepository {

	protected SqlSessionFactory sqlSessionFactory;
	
	protected DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	protected TransactionFactory transactionFactory;
	public void setTransactionFactory(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}

}
