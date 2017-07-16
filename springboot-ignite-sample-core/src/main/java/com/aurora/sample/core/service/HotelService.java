package com.aurora.sample.core.service;

import com.aurora.sample.db.entity.HotelEntity;
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
public interface HotelService {

	List<HotelEntity> getAll() throws Exception;

	HotelEntity getById(Integer hotelId) throws Exception;

	boolean exist(HotelEntity hotelEntity) throws Exception;

	boolean create(HotelEntity hotelEntity) throws Exception;

	boolean update(HotelEntity hotelEntity) throws Exception;

	boolean delete(Integer hotelId) throws Exception;

}
