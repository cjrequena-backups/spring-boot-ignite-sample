
package  com.aurora.sample.core.mapper;

import  com.aurora.sample.db.entity.HotelEntity;
import  com.aurora.sample.dto.HotelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


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
@Mapper
public interface HotelMapper extends  IEntityDTOMapper<HotelDTO, HotelEntity> {

   HotelMapper INSTANCE = Mappers.getMapper( HotelMapper.class);

}
