package com.aurora.sample.db.repository;

import com.aurora.sample.db.entity.HotelEntity;
import org.apache.ibatis.annotations.Select;
import java.util.List;


/**
 * <p>
 * <p>
 * <p>
 * <p>
 * @author cjrequena
 * @version 1.0
 * @since JDK1.8
 * @see
 *
 */
 public interface HotelRepository {

	List<HotelEntity> findAll();

	HotelEntity findById(Integer hotelId);

	@Select("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END RESULT FROM HOTEL WHERE HOTEL_ID = #{hotelId}")
	boolean exists(Integer hotelId);

	int insert(HotelEntity hotelEntity);

	int update(HotelEntity hotelEntity);

	int delete(Integer hotelId);
}
