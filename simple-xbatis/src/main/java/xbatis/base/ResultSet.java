package xbatis.base;

import java.io.Closeable;
import java.util.Iterator;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;

public class ResultSet<T> implements Iterable<T>, Closeable {

	SqlSession session;
	Cursor<T> cursor;

	public ResultSet(SqlSession session, Cursor<T> cursor) {
		this.session = session;
		this.cursor = cursor;
	}

	public int getCurrentIndex() {
		return cursor.getCurrentIndex();
	}

	@Override
	public Iterator<T> iterator() {
		return cursor.iterator();
	}

	@Override
	public void close() {
		RuntimeException exception = null;
		try {
			cursor.close();
		} catch(Exception x) {
			exception = new RuntimeException(x);
		}
		try {
			session.close();
		} catch(Exception x) {
			exception = new RuntimeException(x);
		}
		if(exception != null) throw exception;
	}

}
