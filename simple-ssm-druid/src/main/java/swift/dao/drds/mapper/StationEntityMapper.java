package swift.dao.drds.mapper;

import java.util.List;

import swift.dao.drds.entity.StationEntity;

public interface StationEntityMapper {

	/**
	 * 根据cityCode获取站点数据
	 * @param cityCode
	 * @return
	 */
	public List<StationEntity> queryStationsByCity(String cityCode);
	/**
	 * 根据cityCode获取一条站点数据
	 * @param cityCode
	 * @return
	 */
	public StationEntity getStationByCity(String cityCode);
	/**
	 * 根据stationId更新站点数据
	 * @param entity
	 */
	public int update(StationEntity entity);
	/**
	 * 根据stationId删除数据
	 * @param entity
	 */
	public int delete(StationEntity entity);
	
}
